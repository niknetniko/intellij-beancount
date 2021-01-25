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
import com.outskirtslabs.beancount.psi.stub.AccountStub;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;

public class BeancountAccountDefinitionImpl extends BeancountAccountDefinitionMixin implements BeancountAccountDefinition {

  public BeancountAccountDefinitionImpl(@NotNull AccountStub stub, @NotNull IStubElementType type) {
    super(stub, type);
  }

  public BeancountAccountDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public BeancountAccountDefinitionImpl(AccountStub stub, IElementType type, ASTNode node) {
    super(stub, type, node);
  }

  public void accept(@NotNull BeancountVisitor visitor) {
    visitor.visitAccountDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof BeancountVisitor) accept((BeancountVisitor)visitor);
    else super.accept(visitor);
  }

}
