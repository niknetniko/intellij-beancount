package com.outskirtslabs.beancount.navigation;

import com.intellij.navigation.ItemPresentation;
import com.outskirtslabs.beancount.BeancountIcons;
import com.outskirtslabs.beancount.psi.BeancountAccountDefinition;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Niko Strijbol
 */
public class AccountSymbolPresentation implements ItemPresentation {
    
    private final BeancountAccountDefinition symbol;

    public AccountSymbolPresentation(BeancountAccountDefinition symbol) {
        this.symbol = symbol;
    }

    @Override
    public @Nullable String getPresentableText() {
        return symbol.getText();
    }

    @Override
    public @Nullable String getLocationString() {
        return symbol.getContainingFile().getName();
    }

    @Override
    public @Nullable Icon getIcon(boolean unused) {
        return BeancountIcons.BEAN;
    }
}
