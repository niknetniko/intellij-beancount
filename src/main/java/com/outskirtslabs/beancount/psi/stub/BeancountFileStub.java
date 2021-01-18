package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.stubs.PsiFileStubImpl;
import com.intellij.psi.tree.IStubFileElementType;
import com.outskirtslabs.beancount.psi.BeancountFile;
import org.jetbrains.annotations.NotNull;

public class BeancountFileStub
        extends PsiFileStubImpl<BeancountFile> {
    public BeancountFileStub(BeancountFile file) {
        super(file);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public @NotNull IStubFileElementType getType() {
        return BeancountStubFileElementType.INSTANCE;
    }
}
