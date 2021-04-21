package com.outskirtslabs.beancount.psi.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import com.outskirtslabs.beancount.BeancountIcons;
import com.outskirtslabs.beancount.psi.BeancountAccountSymbol;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountRecursiveVisitor;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import com.outskirtslabs.beancount.psi.elements.BeancountElementFactory;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class AccountReference extends PsiPolyVariantReferenceBase<BeancountAccountSymbol> {
    
    public AccountReference(BeancountAccountSymbol element, TextRange range) {
        super(element, range);
    }

    private Collection<BeancountAccountDefinition> getFromCache(final Project project, final String elementText) {
        return AccountStubIndex.findAllAccounts(project)
                .stream()
                .filter(name -> name.contains(elementText) && !name.equals(elementText))
                .distinct()
                .flatMap(n -> AccountStubIndex.find(project, n).stream())
                .collect(Collectors.toList());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        // If incomplete, include all definitions.
        var found = new ArrayList<BeancountAccountDefinition>();
        if (incompleteCode) {
            found.addAll(getFromCache(getElement().getProject(), getElement().getCleanText()));
        } else {
            found.addAll(AccountStubIndex.find(getElement().getProject(), getElement().getCleanText()));
        }
        
        var results = new ArrayList<ResolveResult>();
        for (var foundDefinition: found) {
            results.add(new PsiElementResolveResult(foundDefinition, StringUtils.equals(foundDefinition.getName(), getElement().getCleanText())));
        }
        
        return results.toArray(ResolveResult[]::new);
    }

    @Override
    public Object @NotNull [] getVariants() {
        var cached = AccountStubIndex.findAllAccounts(getElement().getProject())
                .stream()
                .distinct()
                .flatMap(n -> AccountStubIndex.find(getElement().getProject(), n).stream())
                .collect(Collectors.toList());

        List<LookupElement> variants = new ArrayList<>();
        for (var element : cached) {
            if (StringUtils.isNotBlank(element.getName())) {
                variants.add(LookupElementBuilder.create(element)
                        .withIcon(BeancountIcons.FILE)
                        .withCaseSensitivity(false)
                        .withLookupStrings(Arrays.asList(element.getName().split(":")))
                        .withTypeText(element.getContainingFile().getName()));
            }
        }
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        String text = String.format("2018-01-01 balance %s      563.30 EUR\n", newElementName);
        final BeancountFile file = BeancountElementFactory.createFile(getElement().getProject(), text);
        var result = new ArrayList<BeancountAccountSymbol>();
        file.acceptChildren(new BeancountRecursiveVisitor() {
            @Override
            public void visitAccountSymbol(@NotNull BeancountAccountSymbol o) {
                result.add(o);
            }
        });
        if (result.size() != 1) {
            throw new IllegalStateException("Account was not found in fragment for replacement!");
        }
        return getElement().replace(result.get(0));
    }
}
