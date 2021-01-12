package com.outskirtslabs.beancount.features.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.outskirtslabs.beancount.BeancountIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class BeancountColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Amounts", BeancountHighlighter.AMOUNT),
            new AttributesDescriptor("Comments", BeancountHighlighter.COMMENT),
            new AttributesDescriptor("Account names", BeancountHighlighter.IDENTIFIER),
            new AttributesDescriptor("Strings", BeancountHighlighter.STRING),
            new AttributesDescriptor("Metadata key", BeancountHighlighter.KEY),
            new AttributesDescriptor("Directives", BeancountHighlighter.DIRECTIVE),
            new AttributesDescriptor("Dates", BeancountHighlighter.DATE),
            new AttributesDescriptor("Currencies", BeancountHighlighter.CURRENCY)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return BeancountIcons.FILE;
    }

    @NotNull
    @Override
    public BeancountHighlighter getHighlighter() {
        return new BeancountHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "2016-01-01 * \"Opening Balance for checking account\"\n"
                + "  omg: \"yes\"\n"
                + "  Assets:US:BofA:Checking                         3460.77 USD\n"
                + "    omg: \"yes\"\n"
                + "  Equity:Opening-Balances                        -3460.77 USD\n"
                + ";; this is a comment\n"
                + "\n"
                + "option \"title\" \"Example Beancount file\"\n"
                + "\n"
                + "1792-01-01 commodity USD\n"
                + "  omg: \"yes\"\n"
                + "\n"
                + "2016-12-06 balance Assets:US:BofA:Checking        2917.06 USD";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Beancount";
    }
}
