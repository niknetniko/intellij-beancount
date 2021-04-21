package com.outskirtslabs.beancount.psi.stub;

import com.intellij.psi.PsiFile;
import com.intellij.psi.StubBuilder;
import com.intellij.psi.stubs.DefaultStubBuilder;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.tree.IStubFileElementType;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.BeancountFile;
import org.jetbrains.annotations.NotNull;

public class BeancountStubFileElementType
        extends IStubFileElementType<BeancountFileStub> {
    public static final int VERSION = 1;
    public static final IStubFileElementType<BeancountFileStub> INSTANCE = new BeancountStubFileElementType();

    public BeancountStubFileElementType() {
        super("BEANCOUNT_FILE", BeancountLanguage.INSTANCE);
    }

    @Override
    public StubBuilder getBuilder() {
        return new DefaultStubBuilder() {
            @Override
            protected @NotNull StubElement<?> createStubForFile(@NotNull PsiFile file) {
                if (file instanceof BeancountFile) {
                    return new BeancountFileStub((BeancountFile) file);
                }
                return super.createStubForFile(file);
            }
        };
    }

    @Override
    public int getStubVersion() {
        return VERSION;
    }

    @NotNull
    @Override
    public BeancountFileStub deserialize(@NotNull StubInputStream dataStream,
                                         StubElement parentStub) {
        return new BeancountFileStub(null);
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "beancount.FILE";
    }
}
