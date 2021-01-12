// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface BeancountPostingOrKvList extends PsiElement {

  @NotNull
  List<BeancountEnd> getEndList();

  @NotNull
  List<BeancountKeyValueLine> getKeyValueLineList();

  @NotNull
  List<BeancountPosting> getPostingList();

  @NotNull
  List<BeancountTagsLinks> getTagsLinksList();

}
