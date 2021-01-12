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
    return findChildByClass(BeancountBalance.class);
  }

  @Override
  @Nullable
  public BeancountClose getClose() {
    return findChildByClass(BeancountClose.class);
  }

  @Override
  @Nullable
  public BeancountCommodity getCommodity() {
    return findChildByClass(BeancountCommodity.class);
  }

  @Override
  @Nullable
  public BeancountCustom getCustom() {
    return findChildByClass(BeancountCustom.class);
  }

  @Override
  @Nullable
  public BeancountDocument getDocument() {
    return findChildByClass(BeancountDocument.class);
  }

  @Override
  @Nullable
  public BeancountEvent getEvent() {
    return findChildByClass(BeancountEvent.class);
  }

  @Override
  @Nullable
  public BeancountNote getNote() {
    return findChildByClass(BeancountNote.class);
  }

  @Override
  @Nullable
  public BeancountOpen getOpen() {
    return findChildByClass(BeancountOpen.class);
  }

  @Override
  @Nullable
  public BeancountPad getPad() {
    return findChildByClass(BeancountPad.class);
  }

  @Override
  @Nullable
  public BeancountPrice getPrice() {
    return findChildByClass(BeancountPrice.class);
  }

  @Override
  @Nullable
  public BeancountQuery getQuery() {
    return findChildByClass(BeancountQuery.class);
  }

  @Override
  @Nullable
  public BeancountTransaction getTransaction() {
    return findChildByClass(BeancountTransaction.class);
  }

}
