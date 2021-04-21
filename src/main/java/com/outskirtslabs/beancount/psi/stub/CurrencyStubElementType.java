package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountCurrencySymbol;
import com.outskirtslabs.beancount.psi.impl.BeancountCurrencySymbolImpl;
import com.outskirtslabs.beancount.psi.stub.index.BeancountCurrencySymbolKeyIndex;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class CurrencyStubElementType
        extends IStubElementType<CurrencySymbolStub, BeancountCurrencySymbol> {
    public CurrencyStubElementType() {
        super("CURRENCY_SYMBOL", BeancountLanguage.INSTANCE);
    }

    public BeancountCurrencySymbol createPsi(@NotNull final CurrencySymbolStub stub) {
        return new BeancountCurrencySymbolImpl(stub, this);
    }

    @NotNull
    public CurrencySymbolStub createStub(@NotNull final BeancountCurrencySymbol psi,
                                         final StubElement parentStub) {
        return new CurrencySymbolStubImpl(parentStub, Objects.requireNonNull(psi.getName()));
    }

    @NotNull
    public String getExternalId() {
        return "Beancount.CurrencySymbol";
    }

    public void serialize(@NotNull final CurrencySymbolStub stub,
                          @NotNull final StubOutputStream dataStream) throws
            IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    public CurrencySymbolStub deserialize(@NotNull final StubInputStream dataStream,
                                          final StubElement parentStub) throws IOException {
        final StringRef ref = dataStream.readName();
        return new CurrencySymbolStubImpl(parentStub, Objects.requireNonNull(ref).getString());
    }

    public void indexStub(@NotNull final CurrencySymbolStub stub, @NotNull final IndexSink sink) {
        sink.occurrence(BeancountCurrencySymbolKeyIndex.KEY, stub.getName());
    }

}
