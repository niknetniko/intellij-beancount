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

public class BeancountDirectiveImpl extends ASTWrapperPsiElement implements BeancountDirective {

  public BeancountDirectiveImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitDirective(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public BeancountInclude getInclude() {
    return findChildByClass(BeancountInclude.class);
  }

  @Override
  @Nullable
  public BeancountOption getOption() {
    return findChildByClass(BeancountOption.class);
  }

  @Override
  @Nullable
  public BeancountPlugin getPlugin() {
    return findChildByClass(BeancountPlugin.class);
  }

  @Override
  @Nullable
  public BeancountPopmeta getPopmeta() {
    return findChildByClass(BeancountPopmeta.class);
  }

  @Override
  @Nullable
  public BeancountPoptag getPoptag() {
    return findChildByClass(BeancountPoptag.class);
  }

  @Override
  @Nullable
  public BeancountPushmeta getPushmeta() {
    return findChildByClass(BeancountPushmeta.class);
  }

  @Override
  @Nullable
  public BeancountPushtag getPushtag() {
    return findChildByClass(BeancountPushtag.class);
  }

}
