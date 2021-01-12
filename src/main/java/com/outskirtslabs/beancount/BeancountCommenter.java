package com.outskirtslabs.beancount;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/**
 * Allows commenting lines of code.
 * 
 * @author Niko Strijbol
 */
public class BeancountCommenter implements Commenter {
    
    @Override
    public @Nullable String getLineCommentPrefix() {
        return ";";
    }

    @Override
    public @Nullable String getBlockCommentPrefix() {
        return null;
    }

    @Override
    public @Nullable String getBlockCommentSuffix() {
        return null;
    }

    @Override
    public @Nullable String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Override
    public @Nullable String getCommentedBlockCommentSuffix() {
        return null;
    }
}
