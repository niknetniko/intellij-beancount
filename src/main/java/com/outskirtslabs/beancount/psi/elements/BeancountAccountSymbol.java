package com.outskirtslabs.beancount.psi.elements;

import com.intellij.psi.PsiElement;

/**
 * @author Niko Strijbol
 */
public interface BeancountAccountSymbol extends PsiElement {
    
    String getCleanText();
}
