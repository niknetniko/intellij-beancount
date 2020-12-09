package com.outskirtslabs.beancount.psi;

import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.BeancountLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class BeancountTokenType
        extends IElementType {
    public BeancountTokenType(@NotNull @NonNls String debugName) {
        super(debugName, BeancountLanguage.INSTANCE);
    }
}
