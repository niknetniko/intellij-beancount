// This lexer is adapter from the flex lexer at
// https://github.com/beancount/beancount/blob/v2/beancount/parser/lexer.l
package com.outskirtslabs.beancount.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;

%%

//%debug
%class BeancountLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{  return;
%eof}

%unicode

NEWLINE=[\n\r]

DATE=[0-9]{4}[\-/][0-9]+[\-/][0-9]+
CURRENCY=[A-Z][A-Z0-9\'\.\_\-]{0,22}[A-Z0-9]
LITERAL=\"([^\\\"]|\\.)*\"
NUMBER=([0-9]+|[0-9][0-9,]+[0-9])(\.[0-9]*)?
TAG=#[A-Za-z0-9\-_/.]+
LINK=\^[A-Za-z0-9\-_/.]+
KEY=[a-z][a-zA-Z0-9\-_]+:


ASCII=[\x00-\x7f]
UTF_8_1=[\x80-\xbf]
UTF_8_2=[\xc2-\xdf]{UTF_8_1}
UTF_8_3=\xe0[\xa0-\xbf]{UTF_8_1}|[\xe1-\xec]{UTF_8_1}{UTF_8_1}|\xed[\x80-\x9f]{UTF_8_1}|[\xee-\xef]{UTF_8_1}{UTF_8_1}
UTF_8_4=\xf0[\x90-\xbf]{UTF_8_1}{UTF_8_1}|[\xf1-\xf3]{UTF_8_1}{UTF_8_1}{UTF_8_1}|\xf4[\x80-\x8f]{UTF_8_1}{UTF_8_1}
UTF_8_ONLY={UTF_8_2}|{UTF_8_3}|{UTF_8_4}
UTF_8={ASCII}|{UTF_8_ONLY}

ACCOUNTTYPE=([A-Z]|{UTF_8_ONLY})([A-Za-z0-9\-]|{UTF_8_ONLY})*
ACCOUNTNAME=([A-Z0-9]|{UTF_8_ONLY})([A-Za-z0-9\-]|{UTF_8_ONLY})*

FLAGS=[!&#?%PSTCURM]

%state sIGNORE sINVALID

%%

 /* Newlines matter. */

<YYINITIAL> {
{NEWLINE}                 { return EOL; }

/* Whitespace: ignored, except when found at the beginning of a line
 * and followed by a regular character. This is how we detect an
 * initial indent and thus group syntax elements in the grammar. */
^[ \t]+/[^ \t\r\n] { return INDENT; }
[ \t\r]+ { return WHITE_SPACE; }

 /* Comments. */
;.* { return COMMENT; }
      
 /* Characters with special meanings. */
"|"		              { return PIPE; }
"@@"                { return ATAT; }
"@"		              { return AT; }
"{{"		            { return LCURLCURL; }
"}}"		            { return RCURLCURL; }
"{"		              { return LCURL; }
"}"		              { return RCURL; }
","                 { return COMMA; }
"~"		              { return TILDE; }
"+"		              { return PLUS; }
"-"	    	          { return MINUS; }
"/"		              { return SLASH; }
"("		              { return LPAREN; }
")"		              { return RPAREN; }
"#"		              { return HASH; }
"*"		              { return ASTERISK; }
//":"		              { return COLON; }

{FLAGS}             { return FLAG; }

/* Keywords. */
"txn"		            { return TXN_KEY; }
"balance"		        { return BALANCE_KEY; }
"open"		          { return OPEN_KEY; }
"close"		          { return CLOSE_KEY; }
"commodity"	        { return COMMODITY_KEY; }
"pad"		            { return PAD_KEY; }
"event"		          { return EVENT_KEY; }
"query"		          { return QUERY_KEY; }
"custom"		        { return CUSTOM_KEY; }
"price"		          { return PRICE_KEY; }
"note"		          { return NOTE_KEY; }
"document"	        { return DOCUMENT_KEY; }
"pushtag"	          { return PUSHTAG_KEY; }
"poptag"		        { return POPTAG_KEY; }
"pushmeta"	        { return PUSHMETA_KEY; }
"popmeta"		        { return POPMETA_KEY; }
"option"		        { return OPTION_KEY; }
"plugin"		        { return PLUGIN_KEY; }
"include"		        { return INCLUDE_KEY; }


 /* Dates. */
{DATE}              { return DATE; }

{ACCOUNTTYPE}(:{ACCOUNTNAME})+ { return ACCOUNT; }

 /* Currencies. These are defined as uppercase only in order to
  * disambiguate the syntax. This is to be kept in sync with
  * beancount.core.amount.CURRENCY_RE. */
{CURRENCY} { return CURRENCY; }

 /* String literals. */
{LITERAL} {
    return STRING;
}

/* Numbers. */
{NUMBER} {
    return NUMBER;
}

 /* Tags. */
{TAG} {
    return TAG;
}
 /* Links. */
{LINK} {
    return LINK;
}
 /* Keys. */
{KEY} {
    return KEY;
}

 /* Lines starting with an asterisk, a colon, an hash, or a character
  * in the FLAGS characters set are ignored. This rule is inserted
  * here to give higher precedence to rules matching valid tokens. */
^[*:#].+	{ 
  return IGNORED;
}
^{FLAGS}.+	{ 
  return IGNORED;
}
}

 /* Default rule. {bf253a29a820} */
. { 
  yybegin(sINVALID);
  return BAD_CHARACTER; 
}

<sINVALID>[^ \t\n\r]* {
  yybegin(YYINITIAL);
  return BAD_CHARACTER;
}

 /* Ignore input till the newline. */
//<sIGNORE>.* {
//    yybegin(YYINITIAL);
//    return IGNORED;
//}
