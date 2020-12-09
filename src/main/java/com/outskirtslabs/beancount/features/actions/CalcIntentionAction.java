package com.outskirtslabs.beancount.features.actions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.outskirtslabs.beancount.BeancountFileType;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CalcIntentionAction implements IntentionAction, Iconable {

    @Override
    public @IntentionName @NotNull String getText() {
        return "Calculate expression";
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Calculations";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        return file.getFileType() == BeancountFileType.INSTANCE && hasExpression(editor);
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        final SelectionModel selectionModel = editor.getSelectionModel();
        final String expression = selectionModel.getSelectedText();
        final Document document = editor.getDocument();
        if (!hasExpression(editor)) {
            return;
        }

        assert expression != null;
        Expression e = new ExpressionBuilder(expression).build();
        double value = e.evaluate();
        String result;
        if (Math.ceil(value) == value) {
            result = Integer.toString((int) value);
        } else {
            result = Double.toString(value);
        }
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        document.replaceString(start, end, result);
        selectionModel.removeSelection();
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }

    private boolean hasExpression(Editor editor) {
        return editor != null && editor.getSelectionModel().hasSelection();
    }

    @Override
    public Icon getIcon(int flags) {
        return AllIcons.Diff.MagicResolve;
    }
}