package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.CompletionContributor;

/**
 * Main entry point for completion.
 */
public class BeancountCompletionContributor extends CompletionContributor {
    public BeancountCompletionContributor() {
        // :: accounts
        AccountsCompletionProvider.register(this);

        // :: currency
        BeancountCurrencyCompletionProvider.register(this);

        // :: directives
        DirectDirectiveCompletionProvider.register(this);
        PrefixedDirectiveCompletionProvider.register(this);
        
        // :: dates
        DateCompletionProvider.register(this);
        
        // ? strings
        StringCompletionProvider.register(this);
    }
}
