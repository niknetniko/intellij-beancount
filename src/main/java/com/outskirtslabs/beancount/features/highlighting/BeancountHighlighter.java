package com.outskirtslabs.beancount.features.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.outskirtslabs.beancount.parser.LexerAdapter;
import com.outskirtslabs.beancount.psi.BeancountTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static java.util.Map.entry;

/**
 * Syntax highlighter for beancount.
 *
 * @author Niko Strijbol
 * @see <a href="https://jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.html">tutorial</a>
 */
public class BeancountHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey AMOUNT = createTextAttributesKey(
            "AMOUNT",
            DefaultLanguageHighlighterColors.NUMBER
    );
    public static final TextAttributesKey COMMENT = createTextAttributesKey(
            "BEANCOUNT_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
    );
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey(
            "BEANCOUNT_BAD_CHARACTER",
            HighlighterColors.BAD_CHARACTER
    );
    public static final TextAttributesKey DATE = createTextAttributesKey(
            "BEANCOUNT_DATE",
            DefaultLanguageHighlighterColors.CONSTANT
    );
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey(
            "BEANCOUNT_IDENT",
            DefaultLanguageHighlighterColors.IDENTIFIER
    );
    public static final TextAttributesKey CURRENCY = createTextAttributesKey(
            "BEANCOUNT_CURRENCY",
            DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
    );
    public static final TextAttributesKey DIRECTIVE = createTextAttributesKey(
            "BEANCOUNT_DIRECTIVE",
            DefaultLanguageHighlighterColors.KEYWORD
    );
    public static final TextAttributesKey KEY = createTextAttributesKey(
            "BEANCOUNT_KEY",
            DefaultLanguageHighlighterColors.DOC_COMMENT_TAG
    );
    public static final TextAttributesKey STRING = createTextAttributesKey(
            "BEANCOUNT_STRING",
            DefaultLanguageHighlighterColors.STRING
    );

    private static final Map<IElementType, TextAttributesKey> MAPPING = Map.ofEntries(
            entry(BeancountTypes.AMOUNT, AMOUNT),
            entry(BeancountTypes.CURRENCY, CURRENCY),
            entry(BeancountTypes.TXN_KEY, DIRECTIVE),
            entry(BeancountTypes.BALANCE_KEY, DIRECTIVE),
            entry(BeancountTypes.OPEN_KEY, DIRECTIVE),
            entry(BeancountTypes.CLOSE_KEY, DIRECTIVE),
            entry(BeancountTypes.COMMODITY_KEY, DIRECTIVE),
            entry(BeancountTypes.PAD_KEY, DIRECTIVE),
            entry(BeancountTypes.EVENT_KEY, DIRECTIVE),
            entry(BeancountTypes.QUERY_KEY, DIRECTIVE),
            entry(BeancountTypes.CUSTOM_KEY, DIRECTIVE),
            entry(BeancountTypes.PRICE_KEY, DIRECTIVE),
            entry(BeancountTypes.NOTE_KEY, DIRECTIVE),
            entry(BeancountTypes.DOCUMENT_KEY, DIRECTIVE),
            entry(BeancountTypes.PUSHTAG_KEY, DIRECTIVE),
            entry(BeancountTypes.POPTAG_KEY, DIRECTIVE),
            entry(BeancountTypes.PUSHMETA_KEY, DIRECTIVE),
            entry(BeancountTypes.POPMETA_KEY, DIRECTIVE),
            entry(BeancountTypes.OPTION_KEY, DIRECTIVE),
            entry(BeancountTypes.PLUGIN_KEY, DIRECTIVE),
            entry(BeancountTypes.INCLUDE_KEY, DIRECTIVE),
            entry(BeancountTypes.COMMENT, COMMENT),
            entry(BeancountTypes.ACCOUNT, IDENTIFIER),
            entry(BeancountTypes.FLAG, DIRECTIVE),
            entry(BeancountTypes.STRING, STRING),
            entry(BeancountTypes.KEY, KEY),
            entry(BeancountTypes.DATE, DATE),
            entry(BeancountTypes.ERROR, BAD_CHARACTER),
            entry(BeancountTypes.NUMBER, AMOUNT),
            entry(BeancountTypes.ASTERISK, DIRECTIVE),
            entry(BeancountTypes.HASH, DIRECTIVE)
    );

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new LexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        var attribute = MAPPING.getOrDefault(tokenType, HighlighterColors.NO_HIGHLIGHTING);
        return new TextAttributesKey[]{attribute};
    }
}
