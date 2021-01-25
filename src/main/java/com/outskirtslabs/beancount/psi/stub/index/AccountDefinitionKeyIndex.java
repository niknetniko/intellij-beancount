package com.outskirtslabs.beancount.psi.stub.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import com.outskirtslabs.beancount.psi.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import org.jetbrains.annotations.NotNull;

/**
 * Acts as the index key for the account definition index?
 * 
 * @author Niko Strijbol
 */
public class AccountDefinitionKeyIndex extends StringStubIndexExtension<BeancountAccountDefinition> {
    public static final StubIndexKey<String, BeancountAccountDefinition> KEY = StubIndexKey
            .createIndexKey("beancount.account.index");

    @NotNull
    @Override
    public StubIndexKey<String, BeancountAccountDefinition> getKey() {
        return KEY;
    }
}
