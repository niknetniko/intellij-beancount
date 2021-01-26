package com.outskirtslabs.beancount.psi;

import com.intellij.psi.tree.TokenSet;

import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

public class BeancountTypeUtil {
    public final static TokenSet ACCOUNT_TOKENS = TokenSet.create(
            ACCOUNT
    );
    public final static TokenSet DIRECTIVE_KEYWORDS = TokenSet.create(
            BALANCE_KEY,
            COMMODITY_KEY,
            CUSTOM_KEY,
            DOCUMENT_KEY,
            EVENT_KEY,
            INCLUDE_KEY,
            NOTE_KEY,
            OPEN_KEY,
            OPTION_KEY,
            PAD_KEY,
            PLUGIN_KEY,
            PRICE_KEY,
            QUERY_KEY,
            TXN_KEY,
            TXN,
            KEY
    );

}
