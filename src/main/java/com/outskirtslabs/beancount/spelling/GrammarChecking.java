package com.outskirtslabs.beancount.spelling;

import com.intellij.grazie.grammar.Typo;
import com.intellij.grazie.grammar.strategy.GrammarCheckingStrategy;
import com.intellij.grazie.grammar.strategy.impl.ReplaceCharRule;
import com.intellij.grazie.grammar.strategy.impl.RuleGroup;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Niko Strijbol
 */
public class GrammarChecking implements GrammarCheckingStrategy {

    @Override
    public boolean isMyContextRoot(@NotNull PsiElement psiElement) {
        IElementType type = psiElement.getNode().getElementType();
        return psiElement instanceof PsiComment || type == BeancountTypes.STRING;
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @NotNull
    @Override
    public ElementBehavior getElementBehavior(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1) {
        return ElementBehavior.TEXT;
    }

    @Nullable
    @Override
    public RuleGroup getIgnoredRuleGroup(@NotNull PsiElement root, @NotNull PsiElement child) {
        IElementType type = root.getNode().getElementType();
        if (type == BeancountTypes.STRING) {
            return RuleGroup.Companion.getPUNCTUATION();
        }
        return null;
    }

    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    @Nullable
    @Override
    public Set<Typo.Category> getIgnoredTypoCategories(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1) {
        return null;
    }

    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    @NotNull
    @Override
    public List<ReplaceCharRule> getReplaceCharRules(@NotNull PsiElement psiElement) {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public LinkedHashSet<IntRange> getStealthyRanges(@NotNull PsiElement psiElement, @NotNull CharSequence charSequence) {
        if (psiElement instanceof PsiComment) {
            var set = new LinkedHashSet<IntRange>();
            int location = charSequence.toString().indexOf(';');
            set.add(new IntRange(location, location));
            return set;
        }
        return new LinkedHashSet<>();
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public boolean isTypoAccepted(@NotNull PsiElement psiElement, @NotNull IntRange intRange, @NotNull IntRange intRange1) {
        return true;
    }
}
