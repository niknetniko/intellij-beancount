package com.outskirtslabs.beancount.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceService;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountFilePath;
import com.outskirtslabs.beancount.psi.BeancountRecursiveVisitor;
import com.outskirtslabs.beancount.psi.elements.BeancountElementFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * @author Niko Strijbol
 */
public class BeancountFilePathMixin extends ASTWrapperPsiElement implements BeancountFilePath {

    public BeancountFilePathMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference @NotNull [] getReferences() {
        return PsiReferenceService.getService().getReferences(this, PsiReferenceService.Hints.NO_HINTS).toArray(PsiReference[]::new);
    }

    @Override
    public boolean isValidHost() {
        return true;
    }

    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String text) {
        String inserted = String.format("include \"%s\"\n", text);
        final BeancountFile file = BeancountElementFactory.createFile(getProject(), inserted);
        var result = new ArrayList<BeancountFilePath>();
        file.acceptChildren(new BeancountRecursiveVisitor() {
            @Override
            public void visitFilePath(@NotNull BeancountFilePath o) {
                result.add(o);
            }
        });
        if (result.size() != 1) {
            throw new IllegalStateException("File path was not found in fragment for replacement!");
        }

        return (PsiLanguageInjectionHost) this.replace(result.get(0));
    }

    @Override
    public @NotNull LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return new LiteralTextEscaper<PsiLanguageInjectionHost>(this) {
            @Override
            public boolean decode(@NotNull TextRange rangeInsideHost, @NotNull StringBuilder outChars) {
                var newRange = TextRange.create(rangeInsideHost.getStartOffset() + 1, rangeInsideHost.getEndOffset() - 1);
                outChars.append(newRange.substring(myHost.getText()));
                return true;
            }

            @Override
            public int getOffsetInHost(int offsetInDecoded, @NotNull TextRange rangeInsideHost) {
                var offset = offsetInDecoded + rangeInsideHost.getStartOffset();
                if (offset < rangeInsideHost.getStartOffset()) {
                    return -1;
                }
                if (offset > rangeInsideHost.getEndOffset()) {
                    return -1;
                }
                return offset;
            }

            @Override
            public @NotNull TextRange getRelevantTextRange() {
                return TextRange.from(1, myHost.getTextLength() - 2);
            }

            @Override
            public boolean isOneLine() {
                return true;
            }
        };
    }

    @Override
    public @Nullable Object getValue() {
        return createLiteralTextEscaper().getRelevantTextRange().substring(getText());
    }
}
