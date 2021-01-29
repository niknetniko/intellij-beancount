package com.outskirtslabs.beancount.psi.elements;

import com.intellij.psi.ContributedReferenceHost;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReference;

/**
 * @author Niko Strijbol
 */
public interface BeancountFilePath extends ContributedReferenceHost, PsiLanguageInjectionHost, PsiLiteralValue {
}
