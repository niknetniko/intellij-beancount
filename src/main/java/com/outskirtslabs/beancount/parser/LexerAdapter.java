package com.outskirtslabs.beancount.parser;

import com.intellij.lexer.FlexAdapter;

public class LexerAdapter extends FlexAdapter {
    public LexerAdapter() {
        super(new BeancountLexer(null));
    }
}
