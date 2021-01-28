package com.outskirtslabs.beancount.navigation;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import org.jetbrains.annotations.NotNull;

/**
 * @author Niko Strijbol
 */
public class GoToAccountSymbol implements ChooseByNameContributor {
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        return AccountStubIndex.findAllAccounts(project).toArray(String[]::new);
    }

    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        return AccountStubIndex.find(project, name).toArray(NavigationItem[]::new);
    }
}
