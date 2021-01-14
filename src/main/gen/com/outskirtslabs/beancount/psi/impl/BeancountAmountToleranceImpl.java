// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.outskirtslabs.beancount.psi.*;

public class BeancountAmountToleranceImpl extends ASTWrapperPsiElement implements BeancountAmountTolerance {

  public BeancountAmountToleranceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitAmountTolerance(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BeancountCurrencySymbol getCurrencySymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BeancountCurrencySymbol.class));
  }

  @Override
  @NotNull
  public List<BeancountNumberExpr> getNumberExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, BeancountNumberExpr.class);
  }

}
