package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.outskirtslabs.beancount.BeancountLanguage;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.outskirtslabs.beancount.psi.BeancountTypes.ACCOUNT_SYMBOL;
import static com.outskirtslabs.beancount.psi.BeancountTypes.NUMBER;

public class BeancountCompletionContributor extends CompletionContributor {
    public BeancountCompletionContributor() {
        // :: accounts
        extend(
            CompletionType.BASIC,
            psiElement(ACCOUNT_SYMBOL)
                .andNot(psiElement(ACCOUNT_SYMBOL).afterSibling(psiElement(NUMBER)))
                .withLanguage(BeancountLanguage.INSTANCE),
            new BeancountAccountCompletionProvider()
        );
//
//        // :: currency
        BeancountCurrencyCompletionProvider.register(this);
//
//        extend(CompletionType.BASIC,
//            psiElement().withParent(psiElement(ERROR_ELEMENT)
//                .afterSibling(psiElement(WHITE_SPACE)
//                    .afterSibling(or(psiElement(NUMBER), psiElement(NEGATIVE_NUMBER)))))
//            , new BeancountCurrencyCompletionProvider());

        // :: directives
        DirectDirectiveCompletionProvider.register(this);
        PrefixedDirectiveCompletionProvider.register(this);
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        super.fillCompletionVariants(parameters, result);
    }
}
