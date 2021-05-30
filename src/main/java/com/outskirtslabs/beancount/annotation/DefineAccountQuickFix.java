package com.outskirtslabs.beancount.annotation;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.outskirtslabs.beancount.psi.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.BeancountEntry;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountRecursiveVisitor;
import com.outskirtslabs.beancount.psi.elements.BeancountElementFactory;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Allows defining an account directly.
 *
 * @author Niko Strijbol
 */
public class DefineAccountQuickFix extends BaseIntentionAction {

    private final PsiElement element;

    public DefineAccountQuickFix(PsiElement element) {
        this.element = element;
        setText("Open account for " + element.getText());
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Open account";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            // Get the location where we want to invoke.
            // Ideally, we would find the location of a "near" account, and add
            // it there. However, this is difficult at the moment, e.g. because
            // of multiple files, etc.
            // A simpler alternative is the following algorithm:
            // 1. Find the "last" account in this file, add it after that.
            // 2. If none, find the last account in a random other, add it after that.
            // 3. If no other accounts, add it before the current transaction.
            BeancountAccountDefinition definition = getElement(project, file);

            // Find the date of the element.
            PsiElement dateElement = this.element.getParent().getParent().getParent().getFirstChild();
            String date;
            if (dateElement != null) {
                date = dateElement.getText();
            } else {
                date = "2000-01-01";
            }

            WriteCommandAction.writeCommandAction(project).run(() -> {
                if (definition != null) {
                    // Is definition -> open -> entry;
                    var entry = definition.getParent().getParent();
                    var parent = entry.getParent();
                    PsiElement element = createOpenAccountDeclaration(project, this.element.getText(), date);
                    element = parent.addAfter(element, entry);
                    var navigationElement = element.getNavigationElement();
                    if (navigationElement instanceof Navigatable) {
                        ((Navigatable) navigationElement).navigate(true);
                    }
                } else {
                    System.out.println("No accounts found yet, unsupported for now.");
                }
            });
        });
    }

    private static BeancountAccountDefinition getElement(Project project, PsiFile thisFile) {
        // 1. Find account in current file.
        var accounts = AccountStubIndex.findAllAccounts(project)
                .stream()
                .flatMap(accountName -> AccountStubIndex.find(project, accountName).stream())
                .collect(Collectors.toList());

        var currentFile = accounts.stream()
                .filter(s -> s.getContainingFile().getName().equals(thisFile.getName()))
                .sorted(Comparator.<BeancountAccountDefinition, Integer>comparing(def -> def.getNode().getStartOffset()).reversed())
                .collect(Collectors.toList());

        if (!currentFile.isEmpty()) {
            return currentFile.get(0);
        }

        if (accounts.isEmpty()) {
            return null;
        }

        // Get another random element.
        var random = accounts.get(0);

        var otherFile = accounts.stream()
                .filter(s -> s.getContainingFile().getName().equals(random.getContainingFile().getName()))
                .sorted(Comparator.<BeancountAccountDefinition, Integer>comparing(def -> def.getNode().getStartOffset()).reversed())
                .collect(Collectors.toList());

        return otherFile.get(0);
    }

    private static BeancountEntry createOpenAccountDeclaration(Project project, String accountName, String date) {
        String inserted = String.format("%s open %s\n", date, accountName);
        final BeancountFile file = BeancountElementFactory.createFile(project, inserted);
        var result = new ArrayList<BeancountEntry>();
        file.acceptChildren(new BeancountRecursiveVisitor() {
            @Override
            public void visitEntry(@NotNull BeancountEntry o) {
                result.add(o);
            }
        });
        if (result.size() != 1) {
            throw new IllegalStateException("File path was not found in fragment for replacement!");
        }

        return result.get(0);
    }
}
