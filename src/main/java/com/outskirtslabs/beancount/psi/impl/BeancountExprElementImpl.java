package com.outskirtslabs.beancount.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountLiteralExpr;
import com.outskirtslabs.beancount.psi.BeancountNumberExpr;
import com.outskirtslabs.beancount.psi.BeancountTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BeancountExprElementImpl extends BeancountPsiElement implements BeancountNumberExpr {
    public BeancountExprElementImpl(@NotNull final ASTNode node) {
        super(node);
    }

    public int getLengthPostDecimal() {
        if (this instanceof BeancountLiteralExpr) {
            if (this.getText().contains(".")) {
                return this.getText().substring(this.getText().indexOf(".")).length();
            }
        } else if (this instanceof BeancountUnaryMinImpl) {
            return ((BeancountUnaryMinImpl) this).getNumberExpr().getLengthPostDecimal();
        } else if (this instanceof BeancountUnaryPlusImpl) {
            return ((BeancountUnaryPlusImpl) this).getNumberExpr().getLengthPostDecimal();
        }
        return 0;
    }

    public int getLengthPreDecimal() {
        // TODO: this is ugly!
        if (this instanceof BeancountLiteralExpr) {
            if (this.getText().contains(".")) {
                return this.getText().substring(0, this.getText().indexOf(".")).length();
            }
        } else if (this instanceof BeancountUnaryMinImpl) {
            return ((BeancountUnaryMinImpl) this).getNumberExpr().getLengthPreDecimal() + 1;
        } else if (this instanceof BeancountUnaryPlusImpl) {
            return ((BeancountUnaryPlusImpl) this).getNumberExpr().getLengthPreDecimal() + 1;
        }
        return this.getTextLength();
    }

    @Override
    public int getLengthPreDecimalWithAccount() {
        int preDecimal = getLengthPreDecimal();
        PsiElement parent = getParent();
        if (parent == null) {
            return preDecimal;
        }
        Optional<PsiElement> sibling = BeancountTreeUtil.getNonWhitespacePreviousSibling(parent);
        if (sibling.isEmpty()) {
            return preDecimal;
        }
        if (sibling.get() instanceof BeancountAccountSymbol) {
            BeancountAccountSymbol account = (BeancountAccountSymbol) sibling.get();
            return preDecimal + account.getTextLength();
        }
        return preDecimal;
    }
}
