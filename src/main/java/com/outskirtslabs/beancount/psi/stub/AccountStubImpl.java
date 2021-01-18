package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;

public class AccountStubImpl extends BeancountStubImpl<BeancountAccountSymbol> implements AccountStub {
    public AccountStubImpl(@NotNull final StubElement parent, @NotNull String accountName) {
        super(parent, (IStubElementType) BeancountTypes.ACCOUNT_SYMBOL, StringRef.fromString(accountName));
    }
}
