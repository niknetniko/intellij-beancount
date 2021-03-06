// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;
import com.outskirtslabs.beancount.psi.*;

public class BeancountUnaryPlusImpl extends BeancountNumberExprImpl implements BeancountUnaryPlus {

  public BeancountUnaryPlusImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitUnaryPlus(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BeancountNumberExpr getNumberExpr() {
    return PsiTreeUtil.getChildOfType(this, BeancountNumberExpr.class);
  }

}
