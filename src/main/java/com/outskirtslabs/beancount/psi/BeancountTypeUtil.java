package com.outskirtslabs.beancount.psi;

import com.intellij.psi.tree.TokenSet;

import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

public class BeancountTypeUtil
{
    public final static TokenSet ACCOUNT_TOKENS = TokenSet.create(
        ACCOUNT
    );
    public final static TokenSet DIRECTIVE_KEYWORDS = TokenSet.create(
        BALANCE,
        COMMODITY,
        CUSTOM,
        DOCUMENT,
        EVENT,
        INCLUDE,
        NOTE,
        OPEN,
        OPTION,
        PAD,
        PLUGIN,
        PRICE,
        QUERY,
        TXN
    );

}
