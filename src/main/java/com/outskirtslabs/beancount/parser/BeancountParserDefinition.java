package com.outskirtslabs.beancount.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.outskirtslabs.beancount.psi.BeancountFile;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import com.outskirtslabs.beancount.psi.stub.BeancountStubFileElementType;
import org.jetbrains.annotations.NotNull;

public class BeancountParserDefinition implements ParserDefinition {
    public static final TokenSet COMMENTS = TokenSet.create(BeancountTypes.COMMENT);
    public static final TokenSet STRINGS = TokenSet.create(BeancountTypes.STRING);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new LexerAdapter();
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new BeancountParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return BeancountStubFileElementType.INSTANCE;
    }

    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new BeancountFile(viewProvider);
    }

    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return BeancountTypes.Factory.createElement(node);
    }
}

