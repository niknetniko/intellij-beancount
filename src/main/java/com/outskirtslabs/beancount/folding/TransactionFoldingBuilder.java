package com.outskirtslabs.beancount.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.outskirtslabs.beancount.psi.BeancountTransaction;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * Allows folding transactions, hiding the postings and tags, if any.
 * 
 * This implementation inherits from the correct class to support custom
 * folding regions.
 *
 * @author Niko Strijbol
 */
public class TransactionFoldingBuilder extends CustomFoldingBuilder implements DumbAware {

    @Override
    protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors, @NotNull PsiElement root, @NotNull Document document, boolean quick) {
        Collection<BeancountTransaction> elements = PsiTreeUtil.collectElementsOfType(root, BeancountTransaction.class);
        for (var transaction : elements) {
            var range = transaction.getTextRange();
            // Reduce transaction by one line, to keep lines.
            var newRange = range.grown(-1);
            descriptors.add(new FoldingDescriptor(transaction, newRange));
        }
    }

    @Override
    protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
        StringBuilder replacement = new StringBuilder();
        var filter = TokenSet.create(BeancountTypes.DATE, BeancountTypes.TXN_STRINGS);
        var children = node.getChildren(filter);
        for (var child : children) {
            if (child.getElementType() == BeancountTypes.TXN_STRINGS) {
                replacement.append(txnStringAsString(child));
            } else {
                replacement.append(child.getText());
            }
        }
        if (replacement.length() == 0) {
            return null;
        }
        return replacement.toString();
    }

    private String txnStringAsString(ASTNode node) {
        var strings = TokenSet.create(BeancountTypes.STRING);
        var children = node.getChildren(strings);
        StringBuilder result = new StringBuilder();
        for (ASTNode child : children) {
            var text = child.getText();
            result.append(" ").append(text, 1, text.length() - 1);
        }

        return result.toString();
    }

    @Override
    protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
