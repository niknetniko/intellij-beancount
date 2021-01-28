package com.outskirtslabs.beancount.psi.elements;

import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.outskirtslabs.beancount.psi.stub.AccountStub;

/**
 * Interface for the "account" definition, meaning the account names of
 * open directives. These are stubbed, to easy fast look-up.
 *
 * @author Niko Strijbol
 */
public interface BeancountAccountDefinition extends PsiNameIdentifierOwner, StubBasedPsiElement<AccountStub>, PsiNamedElement, NavigationItem {

}
