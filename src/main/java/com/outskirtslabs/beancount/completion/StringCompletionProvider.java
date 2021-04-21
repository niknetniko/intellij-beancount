package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.intellij.codeInsight.completion.CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.outskirtslabs.beancount.psi.BeancountTypes.STRING;

/**
 * @author Niko Strijbol
 */
public class StringCompletionProvider extends CompletionProvider<CompletionParameters> {

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
        var prefix = parameters.getPosition().getText()
                .replace(DUMMY_IDENTIFIER_TRIMMED, "");
        prefix = prefix.substring(1, prefix.length() - 1);

        result = result.caseInsensitive();
        CompletionResultSet resulting;
        if (StringUtil.isEmpty(prefix)) {
            resulting = result;
        } else {
            resulting = result.withPrefixMatcher(prefix);
        }

        Arrays.stream(PsiTreeUtil
                .collectElements(parameters.getOriginalFile(), element -> element.getNode().getElementType() == STRING))
                .map(PsiElement::getText)
                .map(str -> str.substring(1, str.length() - 1))
                .distinct()
                .forEach(s -> resulting.addElement(LookupElementBuilder
                        .create(s)
                        .withInsertHandler(StringInsertHandler.INSTANCE)));

    }

    public static void register(CompletionContributor contributor) {
        contributor.extend(CompletionType.BASIC, psiElement(STRING), new StringCompletionProvider());
    }

    private static final class StringInsertHandler implements InsertHandler<LookupElement> {

        private static final StringInsertHandler INSTANCE = new StringInsertHandler();

        @Override
        public void handleInsert(@NotNull InsertionContext context, @NotNull LookupElement item) {
            String quoted;
            var nextChar = context.getDocument().getText(TextRange.from(context.getTailOffset(), 1));
            if (nextChar.equals("\"")) {
                quoted = "\"" + item.getLookupString();
            } else {
                quoted = "\"" + item.getLookupString() + "\"";
            }
            context.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), quoted);
        }
    }
}
