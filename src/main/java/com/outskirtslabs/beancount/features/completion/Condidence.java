package com.outskirtslabs.beancount.features.completion;

import com.intellij.codeInsight.completion.CompletionConfidence;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ThreeState;
import org.jetbrains.annotations.NotNull;

/**
 * @author Niko Strijbol
 */
public class Condidence extends CompletionConfidence {

    @Override
    public @NotNull ThreeState shouldSkipAutopopup(@NotNull PsiElement contextElement, @NotNull PsiFile psiFile, int offset) {
//        return super.shouldSkipAutopopup(contextElement, psiFile, offset);
        return ThreeState.NO;
    }
}
