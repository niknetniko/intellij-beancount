package com.outskirtslabs.beancount.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Consumer;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.intellij.lang.parser.GeneratedParserUtilBase.DUMMY_BLOCK;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.outskirtslabs.beancount.psi.BeancountTypes.NUMBER;

/**
 * @author Niko Strijbol
 */
public class DateCompletionProvider extends CompletionProvider<CompletionParameters> {
    
    private static final DateTimeFormatter YEAR_FORMAT = DateTimeFormatter.ofPattern("u");
    
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
        // We have only typed one number a this point.
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
        findSiblingsBackward(parameters.getPosition(), BAD_CHARACTER, element -> reversedPrefix.append(element.getText()));
        prefix += reversedPrefix.reverse().toString();
        System.out.println("Date completion");
        
        if (!prefix.startsWith("2")) {
            return;
        } else {
            result = result.withPrefixMatcher(prefix);
        }

        // Get potential dates.
        var now = LocalDate.now();
        result.addElement(LookupElementBuilder.create(now.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        result.addElement(LookupElementBuilder.create(now.format(YEAR_FORMAT)));
    }

    public static void register(CompletionContributor contributor) {
        contributor.extend(CompletionType.BASIC,
                psiElement(BAD_CHARACTER)
                        .withParent(psiElement(DUMMY_BLOCK)
                                .afterSibling(psiElement(ERROR_ELEMENT)
                                        .withChild(psiElement(NUMBER)))),
                new DateCompletionProvider());
    }

    public static void findSiblingsBackward(final @NotNull PsiElement element,
                                            final @NotNull IElementType elementType,
                                            final @NotNull Consumer<? super PsiElement> consumer) {
        for (PsiElement e = element.getPrevSibling(); e != null; e = e.getPrevSibling()) {
            consumer.consume(e);
        }
    }
}
