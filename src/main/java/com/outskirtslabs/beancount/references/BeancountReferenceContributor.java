package com.outskirtslabs.beancount.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.reference.BeancountAccountReference;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import static com.outskirtslabs.beancount.psi.BeancountTypes.ACCOUNT_SYMBOL;

public class BeancountReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(ACCOUNT_SYMBOL).withLanguage(BeancountLanguage.INSTANCE),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext
                                                                                   context) {
                        var accountSymbol = (BeancountAccountSymbol) element;
                        String value = accountSymbol.getText();
                        if (StringUtils.isBlank(value)) {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        return new PsiReference[]{new BeancountAccountReference(accountSymbol, new TextRange(0, value.length()))};
                    }
                });
    }
}

