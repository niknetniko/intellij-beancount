package com.outskirtslabs.beancount.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountLiteralExpr;
import com.outskirtslabs.beancount.psi.BeancountNumberExpr;
import com.outskirtslabs.beancount.psi.BeancountTreeUtil;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;

public class BeancountExprElementImpl extends BeancountPsiElement implements BeancountNumberExpr {
    public BeancountExprElementImpl(
            @NotNull final ASTNode node) {
        super(node);
    }

    public int getLengthPostDecimal() {
        if (this instanceof BeancountLiteralExpr) {
            if (this.getText().contains("."))
                return this.getText().substring(this.getText().indexOf(".")).length();
        }
        return 0;
    }

    public int getLengthPreDecimal() {
        if (this instanceof BeancountLiteralExpr) {
            if (this.getText().contains("."))
                return this.getText().substring(0, this.getText().indexOf(".")).length();
        }
        return this.getTextLength();
    }

    @Override
    public int getLengthPreDecimalWithAccount() {
        int preDecimal = getLengthPreDecimal();
        PsiElement parent = getParent();
        if (parent == null) return preDecimal;
        Option<PsiElement> sibling = BeancountTreeUtil.getNonWhitespacePreviousSibling(parent);
        if (sibling.isEmpty()) return preDecimal;
        if (sibling.get() instanceof BeancountAccountSymbol) {
            BeancountAccountSymbol account = (BeancountAccountSymbol) sibling.get();
            return preDecimal + account.getTextLength();
        }
        return preDecimal;
    }
}
