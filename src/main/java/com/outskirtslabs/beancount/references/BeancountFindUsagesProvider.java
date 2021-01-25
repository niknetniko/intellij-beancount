package com.outskirtslabs.beancount.references;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.outskirtslabs.beancount.parser.LexerAdapter;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountCurrencySymbol;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.elements.BeancountNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.intellij.lang.HelpID.FIND_OTHER_USAGES;

public class BeancountFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new LexerAdapter(),
                TokenSet.create(BeancountTypes.ACCOUNT, BeancountTypes.ACCOUNT_SYMBOL, BeancountTypes.CURRENCY, BeancountTypes.ACCOUNT_DEFINITION),
                TokenSet.create(BeancountTypes.COMMENT),
                TokenSet.create(BeancountTypes.STRING));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof BeancountNamedElement || psiElement instanceof BeancountAccountDefinition;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return FIND_OTHER_USAGES;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof BeancountAccountDefinition)
            return "account";
        else if (element instanceof BeancountCurrencySymbol)
            return "currency symbol";
        else
            return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof BeancountNamedElement)
            return Objects.requireNonNull(((BeancountNamedElement) element).getName());
        else
            return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof BeancountNamedElement) {
            return Objects.requireNonNull(((BeancountNamedElement) element).getName());
        } else {
            return "";
        }
    }
}

