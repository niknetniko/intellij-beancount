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

public class BeancountPostingImpl extends ASTWrapperPsiElement implements BeancountPosting {

  public BeancountPostingImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitPosting(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BeancountAccountSymbol getAccountSymbol() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BeancountAccountSymbol.class));
  }

  @Override
  @Nullable
  public BeancountCostSpec getCostSpec() {
    return PsiTreeUtil.getChildOfType(this, BeancountCostSpec.class);
  }

  @Override
  @NotNull
  public BeancountEnd getEnd() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BeancountEnd.class));
  }

  @Override
  @Nullable
  public BeancountIncompleteAmount getIncompleteAmount() {
    return PsiTreeUtil.getChildOfType(this, BeancountIncompleteAmount.class);
  }

  @Override
  @NotNull
  public BeancountOptflag getOptflag() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, BeancountOptflag.class));
  }

  @Override
  @Nullable
  public BeancountPriceAnnotation getPriceAnnotation() {
    return PsiTreeUtil.getChildOfType(this, BeancountPriceAnnotation.class);
  }

}
