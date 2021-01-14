package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.psi.BeancountFile;
import org.jetbrains.annotations.NotNull;


public class BeancountCurrencyCompletionProvider extends CompletionProvider<CompletionParameters>
{
    @Override
    protected void addCompletions(@NotNull final CompletionParameters parameters,
        final ProcessingContext context, @NotNull final CompletionResultSet resultSet)
    {
//        PsiElement position = parameters.getPosition();
//        BeancountFile file = (BeancountFile) position.getContainingFile();
//        file.getAllCurrenciesCached()
//            .forEach(c -> {
//                resultSet.addElement(LookupElementBuilder.create(c));
//            });

    }
}
