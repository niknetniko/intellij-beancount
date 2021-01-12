// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountDirective extends PsiElement {

  @Nullable
  BeancountInclude getInclude();

  @Nullable
  BeancountOption getOption();

  @Nullable
  BeancountPlugin getPlugin();

  @Nullable
  BeancountPopmeta getPopmeta();

  @Nullable
  BeancountPoptag getPoptag();

  @Nullable
  BeancountPushmeta getPushmeta();

  @Nullable
  BeancountPushtag getPushtag();

}
