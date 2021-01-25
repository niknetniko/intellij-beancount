package com.outskirtslabs.beancount.references;

import com.intellij.psi.ElementDescriptionLocation;
import com.intellij.psi.ElementDescriptionProvider;
import com.intellij.psi.PsiElement;
import com.intellij.usageView.UsageViewLongNameLocation;
import com.intellij.usageView.UsageViewNodeTextLocation;
import com.intellij.usageView.UsageViewShortNameLocation;
import com.intellij.usageView.UsageViewTypeLocation;
import com.outskirtslabs.beancount.psi.elements.BeancountAccountDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Based heavily on BnfDescriptionProvider.
 * 
 * @author Niko Strijbol
 */
public class AccountDescriptor implements ElementDescriptionProvider {
    
    private static boolean isText(ElementDescriptionLocation location) {
        return location == UsageViewNodeTextLocation.INSTANCE 
                || location == UsageViewLongNameLocation.INSTANCE
                || location == UsageViewShortNameLocation.INSTANCE;
    }
    
    @Override
    public @Nullable String getElementDescription(@NotNull PsiElement element, @NotNull ElementDescriptionLocation location) {
        if (element instanceof BeancountAccountDefinition) {
            if (isText(location)) {
                return ((BeancountAccountDefinition) element).getName();
            } else if (location == UsageViewTypeLocation.INSTANCE) {
                return "Account";
            }
        }
        
        if (location.getDefaultProvider() != null) {
            return location.getDefaultProvider().getElementDescription(element, location);
        }
        
        return null;
    }
}
