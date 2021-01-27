package com.outskirtslabs.beancount.formatter;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

/**
 * @author Niko Strijbol
 */
public class Settings extends CustomCodeStyleSettings {

    protected Settings(CodeStyleSettings container) {
        super("BeanSettings", container);
    }
}
