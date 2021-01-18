package com.outskirtslabs.beancount.psi.stub.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import com.outskirtslabs.beancount.psi.BeancountCurrencySymbol;
import org.jetbrains.annotations.NotNull;

public class BeancountCurrencySymbolKeyIndex
        extends StringStubIndexExtension<BeancountCurrencySymbol> {
    public static final StubIndexKey<String, BeancountCurrencySymbol> KEY = StubIndexKey
            .createIndexKey("beancount.currencysymbol.index");

    @NotNull
    @Override
    public StubIndexKey<String, BeancountCurrencySymbol> getKey() {
        return KEY;
    }
}
