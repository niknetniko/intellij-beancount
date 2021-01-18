package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountTreeUtil;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.intellij.codeInsight.completion.CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED;
import static com.outskirtslabs.beancount.psi.BeancountTreeUtil.debugTree;

@Slf4j
public class BeancountAccountCompletionProvider extends CompletionProvider<CompletionParameters> {

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters,
                                  ProcessingContext processingContext, @NotNull CompletionResultSet resultSet) {
        PsiElement position = parameters.getPosition();
        debugTree(position);

        addPrefixAccountNameCompletions(position,
                (BeancountFile) position.getContainingFile(),
                resultSet);

    }


    private void addPrefixAccountNameCompletions(PsiElement position, BeancountFile file,
                                                 CompletionResultSet resultSet) {

        // case one: completion in a valid/existing line
        Optional<PsiElement> accountParentOpt = BeancountTreeUtil
                .findParent(position, element -> element instanceof BeancountAccountSymbol);
        if (accountParentOpt.isPresent()) {
            BeancountAccountSymbol parent = (BeancountAccountSymbol) accountParentOpt.get();
            addCompletions(
                    file,
                    cleanAccountInput(parent.getText()),
                    resultSet);
            return;
        }

        // case two: completion in a new/invalid posting line
        TokenSet ACCOUNT_PRIMITIVES = TokenSet.create(BeancountTypes.ACCOUNT_SYMBOL);
        if (position.getParent() instanceof BeancountFile) {
            // this is the case where the posting is still invalid
            String accountSoFar = BeancountTreeUtil.getPreviousSiblingsWhile(position,
                    sibling -> ACCOUNT_PRIMITIVES.contains(sibling.getNode().getElementType()))
                    .map(PsiElement::getText)
                    .collect(Collectors.joining(""));

            String input = cleanAccountInput(accountSoFar + position.getText());
            addCompletions(
                    file,
                    input,
                    resultSet.withPrefixMatcher(input));
            return;
        }

    }

    private String cleanAccountInput(String partialAccount) {
        if (partialAccount.endsWith(DUMMY_IDENTIFIER_TRIMMED))
            partialAccount = partialAccount
                    .substring(0, partialAccount.indexOf(DUMMY_IDENTIFIER_TRIMMED));
        return partialAccount;
    }

    void addCompletions(BeancountFile file, String prefix, CompletionResultSet resultSet) {
        new AccountsCompleter(file)
                .getPrefixMatches(prefix)
                .forEach(s -> {
                    log.info(" " + s);
                    resultSet
                            .addElement(LookupElementBuilder.create(s));
                });

        log.info("prefix matcher: {}", resultSet.getPrefixMatcher().getPrefix());
    }
}
