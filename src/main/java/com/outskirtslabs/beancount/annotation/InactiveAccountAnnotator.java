package com.outskirtslabs.beancount.annotation;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceService;
import com.intellij.psi.ResolveResult;
import com.outskirtslabs.beancount.psi.BeancountOpen;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.outskirtslabs.beancount.psi.BeancountTypes.ACCOUNT_SYMBOL;

/**
 * Annotates postings with accounts that are not active.
 *
 * TODO: at the moment, this only takes open directives into account,
 *   not close directives.
 *
 * @author Niko Strijbol
 */
public class InactiveAccountAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode().getElementType() != ACCOUNT_SYMBOL) {
            return;
        }

        PsiElement dateElement = element.getParent().getParent().getParent().getFirstChild();
        if (dateElement == null) {
            return;
        }
        LocalDate date;
        try {
             date = LocalDate.parse(dateElement.getText());
        } catch (DateTimeParseException e) {
            return;
        }

        // We have an account. Instead of doing fancy things to resolve the reference, just
        // resolve it.
        var references = PsiReferenceService.getService().getReferences(element, PsiReferenceService.Hints.NO_HINTS);
        if (references.isEmpty()) {
            return;
        }
        var reference = references.get(0);
        if (!(reference instanceof PsiPolyVariantReference)) {
            return;
        }

        ResolveResult[] results = ((PsiPolyVariantReference) reference).multiResolve(false);

        // One of the dates of the results must be after the date of the element.
        var dates = Arrays.stream(results)
                .map(ResolveResult::getElement)
                .filter(Objects::nonNull)
                .map(r -> (BeancountOpen) r.getParent())
                .filter(Objects::nonNull)
                .map(PsiElement::getFirstChild)
                .map(d -> {
                    try {
                        return LocalDate.parse(d.getText());
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (dates.isEmpty()) {
            return;
        }

        var lastDate = dates.stream().max(Comparator.naturalOrder());

        if (dates.stream().allMatch(d -> d.isAfter(date))) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Reference to inactive account, opened on " + lastDate.get())
                    .range(element)
                    .needsUpdateOnTyping(true)
                    .create();
        }
    }
}
