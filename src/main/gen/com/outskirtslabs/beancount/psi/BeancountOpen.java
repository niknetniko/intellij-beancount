// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountOpen extends PsiElement {

  @NotNull
  BeancountAccountDefinition getAccountDefinition();

  @Nullable
  BeancountCurrencyList getCurrencyList();

  @NotNull
  BeancountEnd getEnd();

  @Nullable
  BeancountKeyValueList getKeyValueList();

  @NotNull
  BeancountOptBooking getOptBooking();

}
