package com.outskirtslabs.beancount.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class BeancountRecursiveVisitor extends BeancountVisitor {
    @Override
    public void visitElement(@NotNull PsiElement element) {
        super.visitElement(element);
        element.acceptChildren(this);
    }
}
