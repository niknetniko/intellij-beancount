package com.outskirtslabs.beancount.formatter;

import com.google.common.base.Stopwatch;
import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.completion.AccountsCompleter;
import com.outskirtslabs.beancount.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

public class BeancountFormattingModelBuilder implements FormattingModelBuilder {
    private static final Logger LOG = Logger.getInstance(BeancountFormattingModelBuilder.class);


    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        
        return null;
    }

    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        LOG.info("createModel");
        Stopwatch watch = Stopwatch.createStarted();
        int longestAccountLength = -1;
        int longestExprLength = -2;
        if (element.getContainingFile() instanceof BeancountFile) {
            BeancountFile file = (BeancountFile) element.getContainingFile();
            longestAccountLength = new AccountsCompleter(file).lengthOfLongestAccount();
            LOG.info("indexed accoutns: " + watch.elapsed(TimeUnit.SECONDS));
            longestExprLength = BeancountTreeUtil
                    .findMatchesRecursively(file, el -> el instanceof BeancountAmount)
                    .map(BeancountAmount.class::cast)
                    .map(BeancountAmount::getNumberExpr)
                    .map(BeancountNumberExpr::getLengthPreDecimalWithAccount)
                    .max().getOrElse(-1);
            LOG.info("longestexpr: " + watch.elapsed(TimeUnit.SECONDS));

        }

        return FormattingModelProvider
                .createFormattingModelForPsiFile(element.getContainingFile(),
                        new BeancountBlock(longestAccountLength, longestExprLength, element.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(settings)),
                        settings);

    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, BeancountLanguage.INSTANCE)
                .between(NUMBER_EXPR, CURRENCY)
                .spaces(1)
                .after(DATE)
                .spaces(1)
                .after(BeancountTypeUtil.DIRECTIVE_KEYWORDS)
                .spaces(1)
                .after(POPMETA_KEY)
                .none()
                .after(POPMETA_KEY)
                .spaces(1)
                .between(UNARY_MIN, BeancountTypes.LITERAL_EXPR)
                .none()
                .between(UNARY_PLUS, BeancountTypes.LITERAL_EXPR)
                .none()
                .between(BeancountTypes.AMOUNT, BeancountTypes.COMMA)
                .spaces(1)
                .between(BeancountTypes.LCURL, BeancountTypes.COST_COMP_LIST)
                .none()
                .between(BeancountTypes.COST_COMP_LIST, BeancountTypes.RCURL)
                .none()
                .between(BeancountTypes.COST_SPEC, CURRENCY)
                .spaces(1)
                .between(BeancountTypes.AT, NUMBER_EXPR)
                .spaces(1);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
