package com.outskirtslabs.beancount.psi.reference;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountEntry;
import com.outskirtslabs.beancount.psi.BeancountTreeUtil;
import com.outskirtslabs.beancount.psi.elements.BeancountNamedElement;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Optional;


public class BeancountAccountReference extends BeancountReference<BeancountAccountSymbol> {
    public BeancountAccountReference(BeancountAccountSymbol element, TextRange range) {
        super(element, range);
    }

    @Override
    Collection<BeancountAccountSymbol> getFromCache(final Project project, final String elementText) {
        return AccountStubIndex.find(project, elementText);
    }

    @Override
    protected boolean isElementToResolve(final BeancountAccountSymbol elementToSearch,
                                         final BeancountNamedElement maybeElementDeclaration) {
        if (maybeElementDeclaration instanceof BeancountAccountSymbol) {
            return isAccountDeclaration(elementToSearch,
                    (BeancountAccountSymbol) maybeElementDeclaration);
        }
        return false;
    }

    /**
     * Is maybeAccountDeclaration the instance that declares the account in accountToSearch?
     *
     * @param accountToSearch
     * @param maybeAccountDeclaration
     * @return
     */
    private static boolean isAccountDeclaration(BeancountAccountSymbol accountToSearch,
                                                BeancountAccountSymbol maybeAccountDeclaration) {

        if (StringUtils.equals(maybeAccountDeclaration.getName(), accountToSearch.getName())) {
            Optional<PsiElement> maybeOpenDirective = BeancountTreeUtil
                    .findParent(maybeAccountDeclaration,
                            parent -> parent instanceof BeancountEntry && ((BeancountEntry) parent).getOpen() != null);

            return maybeOpenDirective.isPresent();
        }
        return false;
    }

}
