//package com.outskirtslabs.beancount.features.folding;
//
//import com.intellij.lang.ASTNode;
//import com.intellij.lang.folding.FoldingBuilderEx;
//import com.intellij.lang.folding.FoldingDescriptor;
//import com.intellij.openapi.editor.Document;
//import com.intellij.openapi.editor.FoldingGroup;
//import com.intellij.openapi.project.DumbAware;
//import com.intellij.openapi.util.TextRange;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.tree.TokenSet;
//import com.intellij.psi.util.PsiTreeUtil;
//import com.outskirtslabs.beancount.psi.BeancountTransactionDir;
//import com.outskirtslabs.beancount.psi.BeancountTypes;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//
///**
// * Allows folding transactions to one line, hiding the postings.
// * 
// * @author Niko Strijbol
// */
//public class TransactionFoldingBuilder extends FoldingBuilderEx implements DumbAware {
//    @Override
//    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
//        Collection<BeancountTransactionDir> elements = PsiTreeUtil.collectElementsOfType(root, BeancountTransactionDir.class);
//        var descriptors = new ArrayList<FoldingDescriptor>();
//        for (var transaction: elements) {
//            // TODO: improve lexer to not include comments in previous block.
//            // For now, exclude the last two new lines if present.
//            var range = transaction.getTextRange();
//            var postingList = transaction.getPostingList();
//            if (postingList == null) {
//                continue;
//            }
//            var lastLine = postingList.getLastChild();
//            if (lastLine == null) {
//                continue;
//            }
//            var eol = lastLine.getLastChild();
//            if (eol != null) {
//                if (eol.getNode().getElementType() == BeancountTypes.EOL) {
//                    range = TextRange.create(range.getStartOffset(), eol.getTextRange().getStartOffset());
//                }
//            }
//            var descriptor = new FoldingDescriptor(transaction, range);
//            descriptors.add(descriptor);
//        }
//        return descriptors.toArray(FoldingDescriptor[]::new);
//    }
//
//    @Override
//    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
//        String replacement = "";
//        var filter = TokenSet.create(BeancountTypes.DATE, BeancountTypes.STRING);
//        var children = node.getChildren(filter);
//        if (children.length < 2) {
//            return null;
//        }
//        replacement += children[0].getText();
//        replacement += " " + children[1].getText().replace("\"", "");
//        if (children.length >= 3) {
//            replacement += " | " + children[2].getText().replace("\"", "");
//        }
//        return replacement;
//    }
//
//    @Override
//    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
//        return false;
//    }
//}
