package com.outskirtslabs.beancount.features;

import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.intellij.spellchecker.tokenizer.TokenizerBase;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Provides spell checking for beancount.
 * 
 * We only handle strings ourselves; comments and account names have built-in support.
 *
 * @author Niko Strijbol
 */
public class SpellChecking extends SpellcheckingStrategy {

    @Override
    public @NotNull Tokenizer<?> getTokenizer(PsiElement element) {
        if (element.getNode().getElementType() == BeancountTypes.STRING) {
            return new TokenizerBase<>(PlainTextSplitter.getInstance());
        }
        return super.getTokenizer(element);
    }
}
