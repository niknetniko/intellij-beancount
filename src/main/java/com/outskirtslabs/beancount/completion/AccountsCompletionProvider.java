package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Consumer;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import org.jetbrains.annotations.NotNull;

import static com.intellij.lang.parser.GeneratedParserUtilBase.DUMMY_BLOCK;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.outskirtslabs.beancount.psi.BeancountTypes.INDENT;

/**
 * Handles providing autocompletion for accounts until the account has been found
 * by the parser. After that, the references take over.
 *
 * @author Niko Strijbol
 */
public class AccountsCompletionProvider extends CompletionProvider<CompletionParameters> {

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
        var prefix = "";

        var dummyElement = parameters.getPosition().getParent();
        if (dummyElement.getNode().getElementType() == DUMMY_BLOCK) {
            // Get a potential prefix from the error element.
            var previous = dummyElement.getPrevSibling();
            if (previous.getNode().getElementType() == ERROR_ELEMENT) {
                prefix += previous.getText();
            }
        }
        var reversedPrefix = new StringBuilder();
        // Get all bad character children and concat those.
        findSiblingsBackward(parameters.getPosition(), BAD_CHARACTER, new Consumer<PsiElement>() {
            @Override
            public void consume(PsiElement element) {
                reversedPrefix.append(element.getText());
            }
        });
        prefix += reversedPrefix.reverse().toString();

        CompletionResultSet resulting;
        if (StringUtil.isEmpty(prefix)) {
            resulting = result;
        } else {
            resulting = result.withPrefixMatcher(prefix);
        }

        PsiElement position = parameters.getPosition();
        AccountStubIndex.findAllAccounts(position.getProject())
                .stream()
                .distinct()
                .forEach(s -> resulting.addElement(LookupElementBuilder.create(s)));
    }

    public static void register(CompletionContributor contributor) {
        contributor.extend(CompletionType.BASIC,
                psiElement(BAD_CHARACTER)
                        .withParent(psiElement(DUMMY_BLOCK)
                                .afterSibling(psiElement(ERROR_ELEMENT)
                                        .afterSibling(psiElement(INDENT)))),
                new AccountsCompletionProvider());
    }

    public static void findSiblingsBackward(final @NotNull PsiElement element,
                                            final @NotNull IElementType elementType,
                                            final @NotNull Consumer<? super PsiElement> consumer) {
        for (PsiElement e = element.getPrevSibling(); e != null; e = e.getPrevSibling()) {
            consumer.consume(e);
        }
    }
}
