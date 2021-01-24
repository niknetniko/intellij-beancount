package com.outskirtslabs.beancount.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.psi.*;
import com.outskirtslabs.beancount.psi.elements.BeancountExprElement;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeancountBlock extends AbstractBlock {
    private final static Alignment AMOUNT_ALIGN = Alignment
            .createAlignment(false, Alignment.Anchor.RIGHT);
    private final int longestAccountLength;
    private final int longestExprPreDecimalLength;
    private final SpacingBuilder spacingBuilder;

    protected BeancountBlock(final int longestAccountLength, final int longestExprPreDecimalLength,
                             @NotNull ASTNode node,
                             @Nullable Wrap wrap,
                             @Nullable Alignment alignment,
                             SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.longestAccountLength = longestAccountLength;
        this.longestExprPreDecimalLength = longestExprPreDecimalLength;
        this.spacingBuilder = spacingBuilder;
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return super.getSubBlocks();
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            IElementType childType = child.getElementType();
            if (childType == TokenType.WHITE_SPACE) {
                continue;
            }
            if (childType == BeancountTypes.COMMENT) {
                continue;
            }
            if (child.getTextRange().isEmpty()) {
                continue;
            }

            addChildBlocks(child, blocks);
        }
        return blocks;
    }

    private void addChildBlocks(ASTNode child, List<Block> blocks) {
        blocks.add(new BeancountBlock(longestAccountLength,
                longestExprPreDecimalLength, child, Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                spacingBuilder));
    }

    @Override
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    private Spacing getAmountCurrencySpacing(BeancountBlock exprBlock, BeancountBlock currencyBlock) {
        Optional<PsiElement> parent = BeancountTreeUtil
                .findParent(exprBlock.getNode().getPsi(), e -> e instanceof BeancountTransaction);
        if (parent.isEmpty()) {
            return Spacing.createSpacing(1, Integer.MAX_VALUE, 0, false, 0);
        }
        BeancountTransaction transactionDir = (BeancountTransaction) parent.get();
        int maxDecimalLengthInTransaction = io.vavr.collection.List
                .ofAll(transactionDir.getPostingOrKvList().getPostingList())
                .map(l -> l.getIncompleteAmount().getMaybeNumber().getNumberExpr())
                .map(BeancountExprElement::getLengthPostDecimal)
                .max().getOrElse(0);

        BeancountNumberExpr expr = (BeancountNumberExpr) exprBlock.getNode().getPsi();
        int padding = (maxDecimalLengthInTransaction + 1) - expr.getLengthPostDecimal();
        return Spacing.createSpacing(padding, padding, 0, false, 0);
    }

    private Spacing getAccountAmountSpacing(BeancountBlock accountBlock, BeancountBlock amountBlock) {
        String accountName = ((BeancountAccountSymbol) accountBlock.getNode().getPsi()).getName();
        BeancountAmount amount = (BeancountAmount) amountBlock.getNode().getPsi();
        BeancountNumberExpr expr = amount.getNumberExpr();
        int decimalPad = expr.getLengthPreDecimal();

        int targetColumn = 55;
        targetColumn = targetColumn + Math.max((2 + longestExprPreDecimalLength + 3) - targetColumn, 0);

        int padding = targetColumn - accountName.length() - decimalPad - 3;
        return Spacing.createSpacing(padding, padding, 0, false, 0);
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        if (child1 instanceof BeancountBlock && child2 instanceof BeancountBlock) {
            BeancountBlock bc1 = (BeancountBlock) child1;
            BeancountBlock bc2 = (BeancountBlock) child2;
            IElementType child1Type = bc1.getNode().getElementType();
            IElementType child2Type = bc2.getNode().getElementType();
            if (child1Type == BeancountTypes.ACCOUNT && child2Type == BeancountTypes.AMOUNT) {
                return getAccountAmountSpacing(bc1, bc2);
            } else if (bc1.getNode().getPsi() instanceof BeancountNumberExpr
                    && child2Type == BeancountTypes.CURRENCY) {
                return getAmountCurrencySpacing(bc1, bc2);
            }
        }

        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        if (myNode.getElementType().equals(BeancountTypes.CURRENCY)) {
            Option<PsiElement> sibling = BeancountTreeUtil
                    .getNonWhitespacePreviousSibling(myNode.getPsi());
            if (sibling.isDefined() && sibling.get() instanceof BeancountNumberExpr)
                return AMOUNT_ALIGN;
        }
        return super.getAlignment();
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
