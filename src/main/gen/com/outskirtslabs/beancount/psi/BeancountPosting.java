// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountPosting extends PsiElement {

  @Nullable
  BeancountCostSpec getCostSpec();

  @NotNull
  BeancountEnd getEnd();

  @Nullable
  BeancountIncompleteAmount getIncompleteAmount();

  @NotNull
  BeancountOptflag getOptflag();

  @Nullable
  BeancountPriceAnnotation getPriceAnnotation();

}
