package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.BeancountLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.DATE;

/**
 * Provides command completion for the following position:
 * 
 * 
 *      2020-10-30 
 *                 ^
 * Meaning the position after the space character after a date.
 *
 * @author Niko Strijbol
 */
public class DirectDirectiveCompletionProvider extends DirectiveCompletionProvider {

    @Override
    protected @Nullable String alreadyTyped(@NotNull CompletionParameters parameters) {
        return null;
    }

    public static ElementPattern<PsiElement> applicablePattern() {
        return psiElement().withParent(
                psiElement(ERROR_ELEMENT)
                        .afterSibling(psiElement(WHITE_SPACE)
                                .afterSibling(psiElement(DATE)))
        ).withLanguage(BeancountLanguage.INSTANCE);
    }
    
    public static void register(CompletionContributor contributor) {
        contributor.extend(
                CompletionType.BASIC,
                applicablePattern(),
                new DirectDirectiveCompletionProvider()
        );
    }
}
