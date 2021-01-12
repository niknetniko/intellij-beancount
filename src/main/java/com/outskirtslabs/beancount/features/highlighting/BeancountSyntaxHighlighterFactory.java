package com.outskirtslabs.beancount.features.highlighting;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Creates syntax highlighters.
 */
public class BeancountSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile)
    {
        return new BeancountHighlighter();
    }
}
