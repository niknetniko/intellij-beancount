package com.outskirtslabs.beancount.spelling;

import com.intellij.openapi.util.TextRange;
import com.intellij.spellchecker.inspections.BaseSplitter;
import com.intellij.util.Consumer;
import com.twelvemonkeys.lang.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Niko Strijbol
 */
public class AccountSplitter extends BaseSplitter {
    
    private static final AccountSplitter INSTANCE = new AccountSplitter();

    public static AccountSplitter getInstance() {
        return INSTANCE;
    }

    @Override
    public void split(@Nullable String text, @NotNull TextRange range, Consumer<TextRange> consumer) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        
        String[] parts = text.split(":");
        int currentIndex = range.getStartOffset();

        for (String part: parts) {
            addWord(consumer, false, TextRange.from(currentIndex, part.length()));
            currentIndex += part.length();
        }
    }
}
