package com.outskirtslabs.beancount.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Niko Strijbol
 */
public class FavaConfigurationFactory extends ConfigurationFactory {
    protected FavaConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new FavaRunConfiguration(project, this, "Fava");
    }

    @Override
    public @NotNull
    @NonNls
    String getId() {
        return getType().getId();
    }
}
