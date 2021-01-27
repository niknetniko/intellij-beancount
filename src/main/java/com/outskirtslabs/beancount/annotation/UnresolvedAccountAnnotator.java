package com.outskirtslabs.beancount.annotation;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceService;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.outskirtslabs.beancount.psi.BeancountTypes.ACCOUNT_SYMBOL;

/**
 * @author Niko Strijbol
 */
public class UnresolvedAccountAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode().getElementType() != ACCOUNT_SYMBOL) {
            return;
        }
        
        // We have an account. Instead of doing fancy things to resolve the reference, just
        // resolve it.
        var references = PsiReferenceService.getService().getReferences(element, PsiReferenceService.Hints.NO_HINTS);
        if (references.isEmpty()) {
            addAnnotation(element, holder);
            return;
        }
        var reference = references.get(0);
        if ( !(reference instanceof PsiPolyVariantReference)) {
            addAnnotation(element, holder);
            return;
        }

        ResolveResult[] results = ((PsiPolyVariantReference) reference).multiResolve(false);
        
        if (Arrays.stream(results).noneMatch(ResolveResult::isValidResult)) {
           addAnnotation(element, holder);
        }
    }
    
    private static void addAnnotation(PsiElement element, AnnotationHolder holder) {
        holder.newAnnotation(HighlightSeverity.ERROR, "Unknown account")
                .range(element)
                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                .create();
    }
}
