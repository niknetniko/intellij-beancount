package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;

import static com.intellij.lang.parser.GeneratedParserUtilBase.DUMMY_BLOCK;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.DATE;

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
//        extend(CompletionType.BASIC,
//            psiElement(CURRENCY)
//            , new BeancountCurrencyCompletionProvider());
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
