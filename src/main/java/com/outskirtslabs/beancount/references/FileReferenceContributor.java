package com.outskirtslabs.beancount.references;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.util.ProcessingContext;
import com.outskirtslabs.beancount.psi.elements.BeancountFilePath;
import org.jetbrains.annotations.NotNull;

import static com.outskirtslabs.beancount.psi.BeancountTypes.FILE_PATH;
import static com.outskirtslabs.beancount.psi.BeancountTypes.INCLUDE;

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
            BeancountFilePath path = (BeancountFilePath) element;
            var escaper = path.createLiteralTextEscaper();
            var text = escaper.getRelevantTextRange().substring(element.getText());
            var ref = new FileReferenceSet(text, element, 1, this, false);
            return ref.getAllReferences();
        }

        @Override
        public boolean acceptsTarget(@NotNull PsiElement target) {
            if (target.getParent() == null) {
                return false;
            }
            var node = target.getParent().getNode();
            if (node == null) {
                return false;
            }
            return node.getElementType() == INCLUDE;
        }
    }
}
