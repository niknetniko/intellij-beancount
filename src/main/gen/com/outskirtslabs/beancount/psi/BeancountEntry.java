// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountEntry extends PsiElement {

  @Nullable
  BeancountBalance getBalance();

  @Nullable
  BeancountClose getClose();

  @Nullable
  BeancountCommodity getCommodity();

  @Nullable
  BeancountCustom getCustom();

  @Nullable
  BeancountDocument getDocument();

  @Nullable
  BeancountEvent getEvent();

  @Nullable
  BeancountNote getNote();

  @Nullable
  BeancountOpen getOpen();

  @Nullable
  BeancountPad getPad();

  @Nullable
  BeancountPrice getPrice();

  @Nullable
  BeancountQuery getQuery();

  @Nullable
  BeancountTransaction getTransaction();

}
