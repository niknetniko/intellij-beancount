package com.outskirtslabs.beancount.psi.stub.index;

import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.psi.stubs.StubIndexKey;
import com.outskirtslabs.beancount.psi.BeancountAccountDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * Index of account definitions, for easy and fast look-up.
 */
public class AccountStubIndex extends StringStubIndexExtension<BeancountAccountDefinition> {
    public static final int VERSION = 3;

    public AccountStubIndex() {
    }

    public static Collection<BeancountAccountDefinition> find(Project project, String accountName) {
        if (DumbService.isDumb(project)) {
            // idea is indexing
            return Collections.emptyList();
        }

        GlobalSearchScope scope = GlobalSearchScope.allScope(project);
        return StubIndex.getElements(
                AccountDefinitionKeyIndex.KEY,
                accountName,
                project,
                scope,
                BeancountAccountDefinition.class
        );
    }

    public static Collection<String> findAllAccounts(Project project) {
        if (DumbService.isDumb(project)) {
            // idea is indexing
            return Collections.emptyList();
        }

        return StubIndex.getInstance().getAllKeys(AccountDefinitionKeyIndex.KEY, project);
    }

    @Override
    public int getVersion() {
        return super.getVersion() + VERSION;
    }

    @NotNull
    @Override
    public StubIndexKey<String, BeancountAccountDefinition> getKey() {
        return AccountDefinitionKeyIndex.KEY;
    }
}
