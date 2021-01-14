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

public class BeancountEntryImpl extends ASTWrapperPsiElement implements BeancountEntry {

  public BeancountEntryImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitEntry(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BeancountBalance getBalance() {
    return PsiTreeUtil.getChildOfType(this, BeancountBalance.class);
  }

  @Override
  @Nullable
  public BeancountClose getClose() {
    return PsiTreeUtil.getChildOfType(this, BeancountClose.class);
  }

  @Override
  @Nullable
  public BeancountCommodity getCommodity() {
    return PsiTreeUtil.getChildOfType(this, BeancountCommodity.class);
  }

  @Override
  @Nullable
  public BeancountCustom getCustom() {
    return PsiTreeUtil.getChildOfType(this, BeancountCustom.class);
  }

  @Override
  @Nullable
  public BeancountDocument getDocument() {
    return PsiTreeUtil.getChildOfType(this, BeancountDocument.class);
  }

  @Override
  @Nullable
  public BeancountEvent getEvent() {
    return PsiTreeUtil.getChildOfType(this, BeancountEvent.class);
  }

  @Override
  @Nullable
  public BeancountNote getNote() {
    return PsiTreeUtil.getChildOfType(this, BeancountNote.class);
  }

  @Override
  @Nullable
  public BeancountOpen getOpen() {
    return PsiTreeUtil.getChildOfType(this, BeancountOpen.class);
  }

  @Override
  @Nullable
  public BeancountPad getPad() {
    return PsiTreeUtil.getChildOfType(this, BeancountPad.class);
  }

  @Override
  @Nullable
  public BeancountPrice getPrice() {
    return PsiTreeUtil.getChildOfType(this, BeancountPrice.class);
  }

  @Override
  @Nullable
  public BeancountQuery getQuery() {
    return PsiTreeUtil.getChildOfType(this, BeancountQuery.class);
  }

  @Override
  @Nullable
  public BeancountTransaction getTransaction() {
    return PsiTreeUtil.getChildOfType(this, BeancountTransaction.class);
  }

}
