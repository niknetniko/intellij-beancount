package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import com.outskirtslabs.beancount.psi.BeancountCurrencySymbol;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;

public class CurrencySymbolStubImpl extends BeancountStubImpl<BeancountCurrencySymbol>
        implements CurrencySymbolStub {
    public CurrencySymbolStubImpl(@NotNull final StubElement parent, @NotNull String currencyName) {
        super(parent, (IStubElementType) BeancountTypes.CURRENCY_SYMBOL,
                StringRef.fromString(currencyName));
    }
}
