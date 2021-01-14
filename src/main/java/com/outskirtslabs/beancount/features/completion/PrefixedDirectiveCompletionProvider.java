package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.codeInsight.completion.CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED;
import static com.intellij.lang.parser.GeneratedParserUtilBase.DUMMY_BLOCK;
import static com.intellij.patterns.PlatformPatterns.psiElement;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.DATE;

/**
 * @author Niko Strijbol
 */
public class PrefixedDirectiveCompletionProvider extends DirectiveCompletionProvider {

    public static ElementPattern<PsiElement> applicablePattern() {
        return psiElement().withParent(
                psiElement(DUMMY_BLOCK)
                        .afterSibling(psiElement(ERROR_ELEMENT)
                                .afterSibling(psiElement(WHITE_SPACE)
                                        .afterSibling(psiElement(DATE))))
        );
    }

    public static void register(CompletionContributor contributor) {
        contributor.extend(
                CompletionType.BASIC,
                applicablePattern(),
                new PrefixedDirectiveCompletionProvider()
        );
    }

    @Override
    protected @Nullable String alreadyTyped(@NotNull CompletionParameters parameters) {
        var dummyBlock = parameters.getPosition().getParent();
        if (dummyBlock == null) {
            return null;
        }
        var errorElement = dummyBlock.getPrevSibling();
        if (errorElement == null) {
            return null;
        }

        var previous = parameters.getPosition().getText().replace(DUMMY_IDENTIFIER_TRIMMED, "");
        
        // We might not be the first token, so get all typed text in the dummy as well.
        return errorElement.getText() + previous;
    }
}
