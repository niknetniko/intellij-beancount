package com.outskirtslabs.beancount.formatter;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.*;
import com.outskirtslabs.beancount.BeancountLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Niko Strijbol
 */
public class LanguageProvider extends LanguageCodeStyleSettingsProvider {

    @Override
    public @Nullable CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new Settings(settings);
    }

    @Override
    public @Nullable String getCodeSample(@NotNull SettingsType settingsType) {
        return "* Options\n" +
                "\n" +
                "; this is a comment\n" +
                "\n" +
                "option \"title\" \"Example Beancount file\"\n" +
                "option \"operating_currency\" \"USD\"\n" +
                "\n" +
                "* Equity Accounts\n" +
                "\n" +
                "1980-05-12 open Equity:Opening-Balances\n" +
                "1980-05-12 open Liabilities:AccountsPayable\n" +
                "2020-05-12 open Assets:US:Test\n" +
                "\n" +
                "\n" +
                "2015-01-30 balance Assets:US:BofA:Checking               4844.07 EUR\n" +
                "\n" +
                "\n" +
                "2015-01-30 balance Assets:US:Test                          42.303 EUR\n" +
                "\n" +
                "\n" +
                "2015-02-04 * \"BANK FEES\" \"Monthly bank fee\"\n" +
                "    Assets:US:BofA:Checking                                -4.00 EUR\n" +
                "    Expenses:Financial:Fees                                 4    EUR\n" +
                "    Expenses:Financial:Fees                                 4.02 EUR\n" +
                "    Expenses:Financial:Fees                                 4    EUR\n" +
                "    Expenses:Financial:Fees                                 4.00 EUR\n";
    }

    @Override
    public @NotNull Language getLanguage() {
        return BeancountLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        super.customizeSettings(consumer, settingsType);
    }

    @Override
    public @Nullable IndentOptionsEditor getIndentOptionsEditor() {
        return new IndentOptionsEditor();
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new SimpleCodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class SimpleCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

        public SimpleCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(BeancountLanguage.INSTANCE, currentSettings, settings);
        }

        @Override
        protected void initTabs(CodeStyleSettings settings) {
            addIndentOptionsTab(settings);
            addWrappingAndBracesTab(settings);
        }
    }
}
