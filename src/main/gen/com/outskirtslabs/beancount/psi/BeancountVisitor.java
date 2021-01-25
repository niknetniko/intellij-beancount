// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.elements.BeancountExprElement;
import com.outskirtslabs.beancount.psi.elements.BeancountCurrencyElement;

public class BeancountVisitor extends PsiElementVisitor {

  public void visitAccountDefinition(@NotNull BeancountAccountDefinition o) {
    visitAccountDefinition(o);
  }

  public void visitAccountSymbol(@NotNull BeancountAccountSymbol o) {
    visitAccountSymbol(o);
  }

  public void visitAmount(@NotNull BeancountAmount o) {
    visitPsiElement(o);
  }

  public void visitAmountTolerance(@NotNull BeancountAmountTolerance o) {
    visitPsiElement(o);
  }

  public void visitAsteriskExpr(@NotNull BeancountAsteriskExpr o) {
    visitNumberExpr(o);
  }

  public void visitBalance(@NotNull BeancountBalance o) {
    visitPsiElement(o);
  }

  public void visitClose(@NotNull BeancountClose o) {
    visitPsiElement(o);
  }

  public void visitCommodity(@NotNull BeancountCommodity o) {
    visitPsiElement(o);
  }

  public void visitCompoundAmount(@NotNull BeancountCompoundAmount o) {
    visitPsiElement(o);
  }

  public void visitCostComp(@NotNull BeancountCostComp o) {
    visitPsiElement(o);
  }

  public void visitCostCompList(@NotNull BeancountCostCompList o) {
    visitPsiElement(o);
  }

  public void visitCostSpec(@NotNull BeancountCostSpec o) {
    visitPsiElement(o);
  }

  public void visitCurrencyList(@NotNull BeancountCurrencyList o) {
    visitPsiElement(o);
  }

  public void visitCurrencyMultiple(@NotNull BeancountCurrencyMultiple o) {
    visitCurrencyList(o);
  }

  public void visitCurrencyOne(@NotNull BeancountCurrencyOne o) {
    visitCurrencyList(o);
  }

  public void visitCurrencySymbol(@NotNull BeancountCurrencySymbol o) {
    visitCurrencyElement(o);
  }

  public void visitCustom(@NotNull BeancountCustom o) {
    visitPsiElement(o);
  }

  public void visitCustomValue(@NotNull BeancountCustomValue o) {
    visitPsiElement(o);
  }

  public void visitCustomValueList(@NotNull BeancountCustomValueList o) {
    visitPsiElement(o);
  }

  public void visitDeclarations(@NotNull BeancountDeclarations o) {
    visitPsiElement(o);
  }

  public void visitDirective(@NotNull BeancountDirective o) {
    visitPsiElement(o);
  }

  public void visitDocument(@NotNull BeancountDocument o) {
    visitPsiElement(o);
  }

  public void visitEnd(@NotNull BeancountEnd o) {
    visitPsiElement(o);
  }

  public void visitEntry(@NotNull BeancountEntry o) {
    visitPsiElement(o);
  }

  public void visitEvent(@NotNull BeancountEvent o) {
    visitPsiElement(o);
  }

  public void visitFilename(@NotNull BeancountFilename o) {
    visitPsiElement(o);
  }

  public void visitInclude(@NotNull BeancountInclude o) {
    visitPsiElement(o);
  }

  public void visitIncompleteAmount(@NotNull BeancountIncompleteAmount o) {
    visitPsiElement(o);
  }

  public void visitKeyValue(@NotNull BeancountKeyValue o) {
    visitPsiElement(o);
  }

  public void visitKeyValueLine(@NotNull BeancountKeyValueLine o) {
    visitPsiElement(o);
  }

  public void visitKeyValueList(@NotNull BeancountKeyValueList o) {
    visitPsiElement(o);
  }

  public void visitKeyValueValue(@NotNull BeancountKeyValueValue o) {
    visitPsiElement(o);
  }

  public void visitLiteralExpr(@NotNull BeancountLiteralExpr o) {
    visitNumberExpr(o);
  }

  public void visitMaybeCurrency(@NotNull BeancountMaybeCurrency o) {
    visitPsiElement(o);
  }

  public void visitMaybeNumber(@NotNull BeancountMaybeNumber o) {
    visitPsiElement(o);
  }

  public void visitMinusExpr(@NotNull BeancountMinusExpr o) {
    visitNumberExpr(o);
  }

  public void visitNote(@NotNull BeancountNote o) {
    visitPsiElement(o);
  }

  public void visitNumberExpr(@NotNull BeancountNumberExpr o) {
    visitExprElement(o);
  }

  public void visitOpen(@NotNull BeancountOpen o) {
    visitPsiElement(o);
  }

  public void visitOptBooking(@NotNull BeancountOptBooking o) {
    visitPsiElement(o);
  }

  public void visitOptflag(@NotNull BeancountOptflag o) {
    visitPsiElement(o);
  }

  public void visitOption(@NotNull BeancountOption o) {
    visitPsiElement(o);
  }

  public void visitPad(@NotNull BeancountPad o) {
    visitPsiElement(o);
  }

  public void visitParenExpr(@NotNull BeancountParenExpr o) {
    visitNumberExpr(o);
  }

  public void visitPlugin(@NotNull BeancountPlugin o) {
    visitPsiElement(o);
  }

  public void visitPlusExpr(@NotNull BeancountPlusExpr o) {
    visitNumberExpr(o);
  }

  public void visitPopmeta(@NotNull BeancountPopmeta o) {
    visitPsiElement(o);
  }

  public void visitPoptag(@NotNull BeancountPoptag o) {
    visitPsiElement(o);
  }

  public void visitPosting(@NotNull BeancountPosting o) {
    visitPsiElement(o);
  }

  public void visitPostingOrKvList(@NotNull BeancountPostingOrKvList o) {
    visitPsiElement(o);
  }

  public void visitPrice(@NotNull BeancountPrice o) {
    visitPsiElement(o);
  }

  public void visitPriceAnnotation(@NotNull BeancountPriceAnnotation o) {
    visitPsiElement(o);
  }

  public void visitPushmeta(@NotNull BeancountPushmeta o) {
    visitPsiElement(o);
  }

  public void visitPushtag(@NotNull BeancountPushtag o) {
    visitPsiElement(o);
  }

  public void visitQuery(@NotNull BeancountQuery o) {
    visitPsiElement(o);
  }

  public void visitSlashExpr(@NotNull BeancountSlashExpr o) {
    visitNumberExpr(o);
  }

  public void visitTagsLinks(@NotNull BeancountTagsLinks o) {
    visitPsiElement(o);
  }

  public void visitTransaction(@NotNull BeancountTransaction o) {
    visitPsiElement(o);
  }

  public void visitTxn(@NotNull BeancountTxn o) {
    visitPsiElement(o);
  }

  public void visitTxnStrings(@NotNull BeancountTxnStrings o) {
    visitPsiElement(o);
  }

  public void visitUnaryMin(@NotNull BeancountUnaryMin o) {
    visitNumberExpr(o);
  }

  public void visitUnaryPlus(@NotNull BeancountUnaryPlus o) {
    visitNumberExpr(o);
  }

  public void visitCurrencyElement(@NotNull BeancountCurrencyElement o) {
    visitPsiElement(o);
  }

  public void visitExprElement(@NotNull BeancountExprElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
