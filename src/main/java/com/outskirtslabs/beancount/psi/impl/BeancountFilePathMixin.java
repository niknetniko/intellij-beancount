package com.outskirtslabs.beancount.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceService;
import com.outskirtslabs.beancount.psi.elements.BeancountFilePath;
import org.jetbrains.annotations.NotNull;

/**
 * @author Niko Strijbol
 */
public class BeancountFilePathMixin extends ASTWrapperPsiElement implements BeancountFilePath {
    
    public BeancountFilePathMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference @NotNull [] getReferences() {
        return PsiReferenceService.getService().getReferences(this, PsiReferenceService.Hints.NO_HINTS).toArray(PsiReference[]::new);
//        var set = new FileReferenceSet(getText(), this, 1, null, false);
//        
//        return set.getAllReferences();
    }
}
