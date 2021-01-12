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

public class BeancountPriceImpl extends ASTWrapperPsiElement implements BeancountPrice {

  public BeancountPriceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitPrice(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public BeancountAmount getAmount() {
    return findNotNullChildByClass(BeancountAmount.class);
  }

  @Override
  @NotNull
  public BeancountEnd getEnd() {
    return findNotNullChildByClass(BeancountEnd.class);
  }

  @Override
  @Nullable
  public BeancountKeyValueList getKeyValueList() {
    return findChildByClass(BeancountKeyValueList.class);
  }

}
