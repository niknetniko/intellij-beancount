package com.outskirtslabs.beancount.completion;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class DirectiveCompletionProvider extends CompletionProvider<CompletionParameters> {
    private static final List<String> KEYWORDS = Lists.newArrayList(
            "*",
            "balance",
            "custom",
            "document",
            "event",
            "note",
            "open",
            "pad",
            "price",
            "query"
    );

    @Override
    protected void addCompletions(@NotNull final CompletionParameters parameters,
                                  final @NotNull ProcessingContext context, @NotNull final CompletionResultSet resultSet) {
        CompletionResultSet resulting;
        var prefix = alreadyTyped(parameters);
        if (prefix != null) {
            resulting = resultSet.withPrefixMatcher(prefix);
        } else {
            resulting = resultSet;
        }

        for (var keyword : KEYWORDS) {
            resulting.addElement(LookupElementBuilder.create(keyword));
        }
    }

    @Nullable
    protected abstract String alreadyTyped(@NotNull final CompletionParameters parameters);
}
