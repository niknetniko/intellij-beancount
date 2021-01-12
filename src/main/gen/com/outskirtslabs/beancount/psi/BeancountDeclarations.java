// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountDeclarations extends PsiElement {

  @NotNull
  List<BeancountDirective> getDirectiveList();

  @NotNull
  List<BeancountEnd> getEndList();

  @NotNull
  List<BeancountEntry> getEntryList();

}
