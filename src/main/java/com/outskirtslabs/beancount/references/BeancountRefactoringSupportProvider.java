package com.outskirtslabs.beancount.references;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import org.jetbrains.annotations.NotNull;

public class BeancountRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof BeancountAccountSymbol || element instanceof BeancountAccountDefinition;
    }
}
