package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.impl.BeancountAccountDefinitionImpl;
import com.outskirtslabs.beancount.psi.stub.index.AccountDefinitionKeyIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * Stub type for account definitions.
 * 
 * @author Niko Strijbol
 */
public class AccountStubElementType extends IStubElementType<AccountStub, BeancountAccountDefinition> {
    public AccountStubElementType() {
        super("ACCOUNT_DEFINITION", BeancountLanguage.INSTANCE);
    }

    public BeancountAccountDefinition createPsi(@NotNull final AccountStub stub) {
        return new BeancountAccountDefinitionImpl(stub, this);
    }

    @NotNull
    public AccountStub createStub(@NotNull final BeancountAccountDefinition psi, final StubElement parentStub) {
        return new AccountStubImpl(parentStub, Objects.requireNonNull(psi.getName()));
    }

    @NotNull
    public String getExternalId() {
        return "Beancount.BeancountAccountDefinition";
    }

    public void serialize(@NotNull final AccountStub stub,
                          @NotNull final StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    public AccountStub deserialize(@NotNull final StubInputStream dataStream,
                                   final StubElement parentStub) throws IOException {
        final StringRef ref = dataStream.readName();
        return new AccountStubImpl(parentStub, Objects.requireNonNull(ref).getString());
    }

    public void indexStub(@NotNull final AccountStub stub, @NotNull final IndexSink sink) {
        sink.occurrence(AccountDefinitionKeyIndex.KEY, Objects.requireNonNull(stub.getName()));
    }
}
