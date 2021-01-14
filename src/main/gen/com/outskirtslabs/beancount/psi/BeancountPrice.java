// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountPrice extends PsiElement {

  @NotNull
  BeancountAmount getAmount();

  @NotNull
  BeancountCurrencySymbol getCurrencySymbol();

  @NotNull
  BeancountEnd getEnd();

  @Nullable
  BeancountKeyValueList getKeyValueList();

}
