package com.outskirtslabs.beancount.references;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * @author Niko Strijbol
 */
public class AccountValidator implements NamesValidator {
    
    private static final Pattern ACCOUNT_REGEX = Pattern.compile("[A-Z][a-z]*(:[A-Za-z0-9\\-]*)+");
    
    @Override
    public boolean isKeyword(@NotNull String name, Project project) {
        return false;
    }

    @Override
    public boolean isIdentifier(@NotNull String name, Project project) {
        return ACCOUNT_REGEX.asMatchPredicate().test(name);
    }
}
