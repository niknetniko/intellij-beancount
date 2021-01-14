package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.psi.BeancountFile;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.MAYBE_CURRENCY;
import static com.outskirtslabs.beancount.psi.BeancountTypes.NUMBER;


public class BeancountCurrencyCompletionProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull final CompletionParameters parameters,
                                  final @NotNull ProcessingContext context, @NotNull final CompletionResultSet resultSet) {
        PsiElement position = parameters.getPosition();
        BeancountFile file = (BeancountFile) position.getContainingFile();
        file.getAllCurrenciesCached()
                .forEach(c -> {
                    System.out.println("Found ");
                    System.out.println(c);
                    resultSet.addElement(LookupElementBuilder.create(c));
                });

    }

    public static void register(CompletionContributor contributor) {
        contributor.extend(CompletionType.BASIC,
                psiElement(MAYBE_CURRENCY)
                , new BeancountCurrencyCompletionProvider());
    }
}
