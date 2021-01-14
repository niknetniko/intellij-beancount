package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionContributor;

public class BeancountCompletionContributor extends CompletionContributor {
    public BeancountCompletionContributor() {
        // :: accounts
//        extend(
//            CompletionType.BASIC,
//            psiElement(ACCOUNT_WORD)
//                .andNot(psiElement(ACCOUNT_WORD).afterSibling(psiElement(NUMBER)))
//                .withLanguage(BeancountLanguage.INSTANCE),
//            new BeancountAccountCompletionProvider()
//        );
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
}
