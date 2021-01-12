package com.outskirtslabs.beancount.parser;

import com.intellij.lexer.FlexAdapter;

public class BeancountLexerAdapter extends FlexAdapter {
    public BeancountLexerAdapter() {
        super(new BeancountLexer(null));
    }
}
