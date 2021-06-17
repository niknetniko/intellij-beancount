package com.outskirtslabs.beancount.parser;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.RestartableLexer;
import com.intellij.lexer.TokenIterator;
import org.jetbrains.annotations.NotNull;

public class LexerAdapter extends FlexAdapter implements RestartableLexer {
    public LexerAdapter() {
        super(new BeancountLexer(null));
    }

    @Override
    public int getStartState() {
        return 0;
    }

    @Override
    public boolean isRestartableState(int state) {
        return false;
    }

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState, TokenIterator tokenIterator) {
        this.start(buffer, startOffset, endOffset, initialState);
    }
}
