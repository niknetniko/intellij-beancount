package com.outskirtslabs.beancount.psi.stub.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import org.jetbrains.annotations.NotNull;

public class BeancountAccountKeyIndex extends StringStubIndexExtension<BeancountAccountSymbol> {
    public static final StubIndexKey<String, BeancountAccountSymbol> KEY = StubIndexKey
            .createIndexKey("beancount.account.index");

    @NotNull
    @Override
    public StubIndexKey<String, BeancountAccountSymbol> getKey() {
        return KEY;
    }
}
