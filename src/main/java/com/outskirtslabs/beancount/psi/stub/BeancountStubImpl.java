package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import org.jetbrains.annotations.NotNull;

public class BeancountStubImpl<T extends PsiNamedElement> extends NamedStubBase<T> implements BeancountStub<T> {
    protected BeancountStubImpl(
            final StubElement parent,
            @NotNull final IStubElementType elementType,
            final StringRef name) {
        super(parent, elementType, name);
    }
}
