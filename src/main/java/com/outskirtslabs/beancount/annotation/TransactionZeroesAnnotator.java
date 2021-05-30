package com.outskirtslabs.beancount.annotation;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.outskirtslabs.beancount.psi.BeancountIncompleteAmount;
import com.outskirtslabs.beancount.psi.BeancountMaybeNumber;
import com.outskirtslabs.beancount.psi.BeancountPosting;
import com.outskirtslabs.beancount.psi.BeancountTransaction;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Checks that a transaction sums to a nice zero number.
 *
 * @author Niko Strijbol
 */
public class TransactionZeroesAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // We only calculate for the transaction element.
        if (!(element instanceof BeancountTransaction)) {
            return;
        }

        var sumOfTransaction = ((BeancountTransaction) element)
                .getPostingOrKvList()
                .getPostingList()
                .stream()
                .map(BeancountPosting::getIncompleteAmount)
                .filter(Objects::nonNull)
                .map(BeancountIncompleteAmount::getMaybeNumber)
                .map(BeancountMaybeNumber::getNumberExpr)
                .filter(Objects::nonNull)
                .map(expression -> new BigDecimal(expression.getText()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // As a text range.
        // The sum is not zero, so add an error highlight.
        if (sumOfTransaction.compareTo(BigDecimal.ZERO) != 0) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Transaction does not balance: " + sumOfTransaction)
                    .range(((BeancountTransaction) element).getTxnStrings().getTextRange())
                    .needsUpdateOnTyping(true)
                    .create();
        }
    }
}
