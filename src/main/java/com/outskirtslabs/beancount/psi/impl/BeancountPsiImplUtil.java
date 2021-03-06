package com.outskirtslabs.beancount.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespacesBinders;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.psi.BeancountCurrencySymbol;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import com.outskirtslabs.beancount.psi.elements.BeancountElementFactory;
import com.outskirtslabs.beancount.psi.reference.BeancountCurrencySymbolReference;
import com.outskirtslabs.beancount.psi.stub.CurrencySymbolStub;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.BiFunction;

public class BeancountPsiImplUtil {

    public static boolean nospace(PsiBuilder b, Integer l) {
        if (space(b, l)) {
            b.mark().error("no <whitespace> allowed");
            b.mark()
                    .setCustomEdgeTokenBinders(WhitespacesBinders.GREEDY_LEFT_BINDER,
                            WhitespacesBinders.GREEDY_RIGHT_BINDER);
        }
        return true;
    }

    public static boolean space(PsiBuilder b, Integer l) {
        IElementType type = b.rawLookup(0);
        IElementType typ2 = b.rawLookup(-1);
        return type instanceof PsiWhiteSpace || typ2 instanceof PsiComment;
    }

    public static <T extends PsiElement> PsiElement setName(T element, IElementType elementType,
                                                             BiFunction<Project, String, T> elementFactory, String newName) {
        ASTNode node = element.getNode().findChildByType(elementType);
        if (node != null) {
            Optional.ofNullable(elementFactory.apply(element.getProject(), newName))
                    .ifPresent(id -> element.getNode().replaceChild(node, id.getFirstChild().getNode()));
        }
        return element;
    }

    public static String getName(BeancountCurrencySymbol element) {
        CurrencySymbolStub stub = element.getStub();
        if (stub != null)
            return stub.getName();
        return element.getText();
    }

    public static PsiElement setName(BeancountCurrencySymbol element, String newName) {
        return setName(element, BeancountTypes.CURRENCY,
                BeancountElementFactory::createCurrencySymbol, newName);
    }

    public static PsiElement getNameIdentifier(BeancountCurrencySymbol element) {
        ASTNode node = element.getNode();
        if (node != null) {
            return node.getPsi();
        } else {
            return null;
        }
    }

    public static PsiReference getReference(BeancountCurrencySymbol element) {
        if (element == null)
            return null;

        String value = element.getText();
        if (element.getStub() != null)
            value = element.getStub().getName();

        if (StringUtils.isBlank(value))
            return null;

        return new BeancountCurrencySymbolReference(element, new TextRange(0, value.length()));
    }
}
