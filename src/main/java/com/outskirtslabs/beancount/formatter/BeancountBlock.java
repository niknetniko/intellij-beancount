package com.outskirtslabs.beancount.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

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

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            IElementType childType = child.getElementType();
            if (childType == TokenType.WHITE_SPACE) {
                continue;
            }
            if (childType == END) {
                continue;
            }
            if (childType == INDENT) {
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
                null,
                spacingBuilder));
    }

    @Override
    public Indent getIndent() {
        if (getNode().getElementType() == POSTING_OR_KV_LIST || getNode().getElementType() == KEY_VALUE_LIST) {
            return Indent.getNormalIndent();
        }
        return Indent.getNoneIndent();
    }

    @Override
    public boolean isIncomplete() {
        // Assume a transaction itself is always incomplete.
        if (isTransaction()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isTransaction() {
        if (getNode().getElementType() == TRANSACTION) {
            return true;
        }
        if (getNode().getElementType() == ENTRY) {
            return getNode().getPsi(BeancountEntry.class).getTransaction() != null;
        }
        
        return false;
    }

    @Override
    protected @Nullable Indent getChildIndent() {
        if (isTransaction()) {
            return Indent.getNormalIndent();
        }
        return Indent.getNoneIndent();
    }

    private Spacing getAmountCurrencySpacing(BeancountBlock exprBlock, BeancountBlock currencyBlock) {
        BeancountNumberExpr expression;
        var node = exprBlock.getNode();
        if (node.getPsi() instanceof BeancountNumberExpr) {
            expression = node.getPsi(BeancountNumberExpr.class);
        } else {
            expression = Objects.requireNonNull(node.getPsi(BeancountMaybeNumber.class).getNumberExpr());
        }
        
        var maxNumberOfDecimals = 2;
        
        // The spacing between the number and the currency is calculated as follows:
        // After the last digit of the post decimals, we want at least one space.
        // At most we want maxNumberOfDecimals + 1 (for the point) + 1 (for the space).
        var padding = Math.max(1, maxNumberOfDecimals + 2 - expression.getLengthPostDecimal());
        return Spacing.createSpacing(padding, padding, 0, false, 0);
    }
    
    private BeancountNumberExpr getExpression(ASTNode node) {
        if (node.getElementType() == INCOMPLETE_AMOUNT) {
            var am = node.getPsi(BeancountIncompleteAmount.class);
            return am.getMaybeNumber().getNumberExpr();
        } else if (node.getElementType() == AMOUNT_TOLERANCE) {
            var am = node.getPsi(BeancountAmountTolerance.class);
            var list = am.getNumberExprList();
            return list.size() >= 1 ? list.get(0) : null;
        }
        return null;
    }

    private Spacing getAccountAmountSpacing(BeancountBlock accountBlock, BeancountBlock amountBlock) {
        String accountName = accountBlock.getNode().getPsi().getText();
        BeancountNumberExpr expr = getExpression(amountBlock.getNode());
        if (expr == null) {
            return Spacing.getReadOnlySpacing();
        }
        int decimalPad = expr.getLengthPreDecimal();
        // Very ugly :(
        var doc = Objects.requireNonNull(accountBlock.getNode().getPsi().getContainingFile().getViewProvider().getDocument());
        var offset = accountBlock.getNode().getStartOffset();
        var line = doc.getLineNumber(offset);
        var prev = doc.getLineEndOffset(Math.max(line - 1, 0));
        int targetColumn = 65 - Math.max(0, (offset - prev));
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
            if (child1Type == ACCOUNT_SYMBOL && (child2Type == AMOUNT_TOLERANCE || child2Type == INCOMPLETE_AMOUNT)) {
                return getAccountAmountSpacing(bc1, bc2);
            } else if (child1Type == MAYBE_NUMBER && child2Type == MAYBE_CURRENCY) {
                return getAmountCurrencySpacing(bc1, bc2);
            } else if (child1Type == LITERAL_EXPR && child2Type == CURRENCY_SYMBOL) {
                return getAmountCurrencySpacing(bc1, bc2);
            }
        }

        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        if (myNode.getElementType().equals(BeancountTypes.CURRENCY)) {
            Optional<PsiElement> sibling = BeancountTreeUtil
                    .getNonWhitespacePreviousSibling(myNode.getPsi());
            if (sibling.isPresent() && sibling.get() instanceof BeancountNumberExpr) {
                return AMOUNT_ALIGN;
            }
        }
        return super.getAlignment();
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
