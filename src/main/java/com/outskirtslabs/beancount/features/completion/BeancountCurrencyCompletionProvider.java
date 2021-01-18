package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountFile;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.CURRENCY;
import static com.outskirtslabs.beancount.psi.BeancountTypes.NUMBER;


public class BeancountCurrencyCompletionProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull final CompletionParameters parameters,
                                  final @NotNull ProcessingContext context, @NotNull final CompletionResultSet resultSet) {
        PsiElement position = parameters.getPosition();
        BeancountFile file = (BeancountFile) position.getContainingFile();
        file.getAllCurrenciesCached()
                .forEach(c -> {
                    resultSet.addElement(LookupElementBuilder.create(c));
                });

    }

    public static void register(CompletionContributor contributor) {
        // 1. For directives, such as balance
        // 1.a. When nothing has been typed yet in a directive.
        contributor.extend(CompletionType.BASIC,
                psiElement()
                        .withParent(psiElement(ERROR_ELEMENT)
                                .afterSibling(psiElement(WHITE_SPACE)
                                        .afterSibling(psiElement(NUMBER))))
                        .withLanguage(BeancountLanguage.INSTANCE)
                , new BeancountCurrencyCompletionProvider());
        // 1.b. When text has been typed.
        //      For some reason, this is already "currency", even though the PSI plugin doesn't show it.
        contributor.extend(CompletionType.BASIC,
                psiElement(CURRENCY)
                        .withLanguage(BeancountLanguage.INSTANCE)
                , new BeancountCurrencyCompletionProvider());
    }
}
