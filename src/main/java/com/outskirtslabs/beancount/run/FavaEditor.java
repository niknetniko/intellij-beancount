package com.outskirtslabs.beancount.run;

import com.intellij.execution.ExecutionBundle;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Niko Strijbol
 */
public class FavaEditor extends SettingsEditor<FavaRunConfiguration> {
    private JPanel myPanel;
    private LabeledComponent<ComponentWithBrowseButton> myMainClass;
    
    public FavaEditor() {
        this.myPanel = new JPanel();
        myPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        myPanel.add(new JLabel(ExecutionBundle.message("this.configuration.cannot.be.edited"), SwingConstants.CENTER));
    }

    @Override
    protected void resetEditorFrom(FavaRunConfiguration demoRunConfiguration) {

    }

    @Override
    protected void applyEditorTo(FavaRunConfiguration demoRunConfiguration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

    private void createUIComponents() {
        myMainClass = new LabeledComponent<>();
        myMainClass.setComponent(new TextFieldWithBrowseButton());
    }
}