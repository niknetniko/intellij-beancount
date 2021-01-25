package com.outskirtslabs.beancount.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.outskirtslabs.beancount.psi.elements.BeancountNamedElement;
import com.outskirtslabs.beancount.psi.stub.AccountStub;
import org.jetbrains.annotations.NotNull;

public abstract class BeancountAccountElementImpl extends StubBasedPsiElementBase<AccountStub>
        implements BeancountNamedElement, StubBasedPsiElement<AccountStub> {
    public BeancountAccountElementImpl(@NotNull final AccountStub stub,
                                       @NotNull final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public BeancountAccountElementImpl(@NotNull final ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + this.getElementType().toString() + ")";
    }
}
