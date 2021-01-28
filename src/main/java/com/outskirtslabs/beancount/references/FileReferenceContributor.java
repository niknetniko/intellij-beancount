package com.outskirtslabs.beancount.references;

import com.intellij.openapi.paths.PathReferenceProvider;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

/**
 * @author Niko Strijbol
 */
public class FileReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(FILE_PATH), new FileReferenceProvider());
    }

    private static class FileReferenceProvider extends PsiReferenceProvider {
        @Override
        public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
            if (!acceptsTarget(element)) {
                return new PsiReference[0];
            }
            assert element.getNode().getElementType() == FILE_PATH;
                    
            var ref = new FileReferenceSet(element.getText(), element, 1, this, false);
            return ref.getAllReferences();
        }

        @Override
        public boolean acceptsTarget(@NotNull PsiElement target) {
            return target.getParent() != null && target.getParent().getNode().getElementType() == INCLUDE;
        }
    }
}
