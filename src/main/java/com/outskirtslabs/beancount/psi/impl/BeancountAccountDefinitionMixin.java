package com.outskirtslabs.beancount.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountRecursiveVisitor;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.elements.BeancountElementFactory;
import com.outskirtslabs.beancount.psi.stub.AccountStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Implements some of the methods required for account definitions.
 * 
 * @author Niko Strijbol
 */
public abstract class BeancountAccountDefinitionMixin extends StubBasedPsiElementBase<AccountStub>  implements BeancountAccountDefinition {

    public BeancountAccountDefinitionMixin(@NotNull AccountStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public BeancountAccountDefinitionMixin(@NotNull ASTNode node) {
        super(node);
    }

    public BeancountAccountDefinitionMixin(AccountStub stub, IElementType nodeType, ASTNode node) {
        super(stub, nodeType, node);
    }

    @Override
    public @Nullable String getName() {
        AccountStub stub = getGreenStub();
        if (stub != null) {
            return stub.getName();
        }
        
        return getText();
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        String text = String.format("2018-01-01 open %s USD\n", name);
        final BeancountFile file = BeancountElementFactory.createFile(getProject(), text);
        var result = new ArrayList<BeancountAccountDefinition>();
        file.acceptChildren(new BeancountRecursiveVisitor() {
            @Override
            public void visitAccountDefinition(com.outskirtslabs.beancount.psi.@NotNull BeancountAccountDefinition o) {
                result.add(o);
            }
        });
        if (result.size() != 1) {
            throw new IllegalStateException("Account was not found in fragment for replacement!");
        }

        return this.replace(result.get(0));
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return this;
    }
}
