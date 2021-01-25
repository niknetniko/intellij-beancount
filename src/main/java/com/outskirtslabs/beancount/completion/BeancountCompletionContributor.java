package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.outskirtslabs.beancount.BeancountLanguage;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

/**
 * Main entry point for completion.
 */
public class BeancountCompletionContributor extends CompletionContributor {
    public BeancountCompletionContributor() {
        // :: accounts
        extend(
            CompletionType.BASIC,
            psiElement(ERROR_ELEMENT)
                    .afterSibling(psiElement(INDENT))
                .withLanguage(BeancountLanguage.INSTANCE),
            new BeancountAccountCompletionProvider()
        );

        // :: currency
        BeancountCurrencyCompletionProvider.register(this);

        // :: directives
        DirectDirectiveCompletionProvider.register(this);
        PrefixedDirectiveCompletionProvider.register(this);
    }
}
