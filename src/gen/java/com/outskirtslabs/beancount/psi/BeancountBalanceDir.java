// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountBalanceDir extends PsiElement {

  @Nullable
  BeancountAccount getAccount();

  @Nullable
  BeancountAmount getAmount();

  @Nullable
  PsiElement getComment();

}