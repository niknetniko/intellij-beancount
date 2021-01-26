package com.outskirtslabs.beancount.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountTypeUtil;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.StreamSupport;

import static com.intellij.openapi.util.Conditions.equalTo;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

public class BeancountFormattingModelBuilder implements FormattingModelBuilder {
    private static final Logger LOG = Logger.getInstance(BeancountFormattingModelBuilder.class);

    
    @NotNull
    @Override
    @SuppressWarnings("UnstableApiUsage")
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        var file = element.getContainingFile();
        int longestAccountLength = findLongestAccountLength(file);
        int longestExprLength = findLongestExpressionLength(file);
        return FormattingModelProvider
                .createFormattingModelForPsiFile(file,
                        new BeancountBlock(longestAccountLength, longestExprLength, element.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                null,
                                createSpaceBuilder(settings)),
                        settings);

    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, BeancountLanguage.INSTANCE)
                // General beginning of line tokens
                .after(BeancountTypeUtil.DIRECTIVE_KEYWORDS)
                .spaces(1)
                // None before dates, one after.
                .before(DATE)
                .none()
                .after(DATE)
                .spaces(1)
                .between(STRING, STRING)
                .spaces(1)
                // Minus
                .between(MINUS, LITERAL_EXPR)
                .none()
                .between(PLUS, LITERAL_EXPR)
                .none()
                .between(CURRENCY, LCURL)
                .spaces(1)
                .after(LCURL)
                .none()
                .before(RCURL)
                .none();
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }

    /**
     * Find the longest account length across the project.
     * This ensures all files in the project are formatted in the same way.
     */
    private int findLongestAccountLength(PsiFile file) {
        if (file instanceof BeancountFile) {
            return ((BeancountFile) file).getAllAccountsCached()
                    .mapToInt(String::length)
                    .max()
                    .orElseGet(() -> StreamSupport.stream(SyntaxTraverser.psiTraverser(file)
                            .filterTypes(equalTo(ACCOUNT_SYMBOL))
                            .map(PsiElement::getTextLength)
                            .spliterator(), false)
                            .mapToInt(i -> i)
                            .max()
                            .orElse(0));
        } else {
            return 0;
        }
    }

    private int findLongestExpressionLength(PsiFile file) {
        return StreamSupport.stream(SyntaxTraverser.psiTraverser(file)
                .filterTypes(equalTo(NUMBER))
                .map(PsiElement::getTextLength)
                .spliterator(), false)
                .mapToInt(i -> i)
                .max()
                .orElse(0);
    }
}
