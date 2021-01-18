package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.impl.BeancountAccountSymbolImpl;
import com.outskirtslabs.beancount.psi.stub.index.BeancountAccountKeyIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AccountStubElementType extends IStubElementType<AccountStub, BeancountAccountSymbol> {
    public AccountStubElementType() {
        super("ACCOUNT", BeancountLanguage.INSTANCE);
    }

    public BeancountAccountSymbol createPsi(@NotNull final AccountStub stub) {
        return new BeancountAccountSymbolImpl(stub, this);
    }

    @NotNull
    public AccountStub createStub(@NotNull final BeancountAccountSymbol psi, final StubElement parentStub) {
        return new AccountStubImpl(parentStub, psi.getName());
    }

    @NotNull
    public String getExternalId() {
        return "Beancount.Account";
    }

    public void serialize(@NotNull final AccountStub stub,
                          @NotNull final StubOutputStream dataStream) throws
            IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    public AccountStub deserialize(@NotNull final StubInputStream dataStream,
                                   final StubElement parentStub) throws IOException {
        final StringRef ref = dataStream.readName();
        return new AccountStubImpl(parentStub, ref.getString());
    }

    public void indexStub(@NotNull final AccountStub stub, @NotNull final IndexSink sink) {
        sink.occurrence(BeancountAccountKeyIndex.KEY, stub.getName());
    }

}
