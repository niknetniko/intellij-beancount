package com.outskirtslabs.beancount.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.outskirtslabs.beancount.BeancountIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * The custom Beancount module type that allows us to have a beancount specific
 * project in the IDE.
 */
public class BeancountModuleType
        extends ModuleType<BeancountModuleBuilder> {
    private static final String ID = "BEANCOUNT_MODULE_TYPE";

    public BeancountModuleType() {
        super(ID);
    }

    public static BeancountModuleType getInstance() {
        return (BeancountModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public BeancountModuleBuilder createModuleBuilder() {
        return new BeancountModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "Beancount";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Add support for Beancount";
    }

    @NotNull
    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return BeancountIcons.FILE;
    }
}
