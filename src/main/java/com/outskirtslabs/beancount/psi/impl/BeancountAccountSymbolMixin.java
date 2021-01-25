package com.outskirtslabs.beancount.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.reference.AccountReference;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import static com.intellij.codeInsight.completion.CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED;

/**
 * Implements methods related to account usage.
 *
 * @author Niko Strijbol
 */
public abstract class BeancountAccountSymbolMixin extends ASTWrapperPsiElement implements BeancountAccountSymbol {

    public BeancountAccountSymbolMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        String value = getText();

        if (StringUtils.isBlank(value)) {
            return null;
        }
        
        return new AccountReference(this, TextRange.create(0, value.length()));
    }

    public String getCleanText() {
        return getText().replace(DUMMY_IDENTIFIER_TRIMMED, "");
    }

    @Override
    public boolean isEquivalentTo(PsiElement another) {
        return another instanceof BeancountAccountSymbolMixin && getCleanText().equals(((BeancountAccountSymbolMixin) another).getCleanText());
    }
}
