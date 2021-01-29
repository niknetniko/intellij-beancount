package com.outskirtslabs.beancount.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.outskirtslabs.beancount.BeancountIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Niko Strijbol
 */
public class FavaRunConfigurationType implements ConfigurationType {
    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Fava";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Sentence) String getConfigurationTypeDescription() {
        return "Run Fava with a given file.";
    }

    @Override
    public Icon getIcon() {
        return BeancountIcons.BEAN;
    }

    @Override
    public @NotNull
    @NonNls
    String getId() {
        return "beancount-fava";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{
                new FavaConfigurationFactory(this)
        };
    }
}
