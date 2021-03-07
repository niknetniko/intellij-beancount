package com.outskirtslabs.beancount.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeancountTreeUtil {

    public static Optional<PsiElement> getNonWhitespacePreviousSibling(PsiElement element) {
        PsiElement sibling = element.getPrevSibling();
        while (sibling != null && sibling.getNode().getElementType().equals(TokenType.WHITE_SPACE))
            sibling = sibling.getPrevSibling();
        return Optional.ofNullable(sibling);
    }

    public static Optional<PsiElement> findParent(PsiElement element,
                                                  Predicate<PsiElement> predicate) {
        return getParentStream(element)
                .filter(elem -> elem.map(predicate::test).orElse(true))
                .findFirst()
                .orElse(Optional.empty());
    }

    private static Stream<Optional<PsiElement>> getParentStream(PsiElement element) {
        return Stream.iterate(
                Optional.ofNullable(element),
                prev -> prev.map(PsiElement::getParent));
    }

}

