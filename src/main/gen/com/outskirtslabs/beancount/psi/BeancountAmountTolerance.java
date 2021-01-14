// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountAmountTolerance extends PsiElement {

  @NotNull
  BeancountCurrencySymbol getCurrencySymbol();

  @NotNull
  List<BeancountNumberExpr> getNumberExprList();

}
