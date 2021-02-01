// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.outskirtslabs.beancount.psi.elements.BeancountElementTypeFactory;
import com.outskirtslabs.beancount.psi.elements.BeancountElementType;
import com.outskirtslabs.beancount.psi.impl.*;

public interface BeancountTypes {

  IElementType ACCOUNT_DEFINITION = BeancountElementTypeFactory.factoryAccount("ACCOUNT_DEFINITION");
  IElementType ACCOUNT_SYMBOL = new BeancountElementType("ACCOUNT_SYMBOL");
  IElementType AMOUNT = new BeancountElementType("AMOUNT");
  IElementType AMOUNT_TOLERANCE = new BeancountElementType("AMOUNT_TOLERANCE");
  IElementType ASTERISK_EXPR = new BeancountElementType("ASTERISK_EXPR");
  IElementType BALANCE = new BeancountElementType("BALANCE");
  IElementType CLOSE = new BeancountElementType("CLOSE");
  IElementType COMMODITY = new BeancountElementType("COMMODITY");
  IElementType COMPOUND_AMOUNT = new BeancountElementType("COMPOUND_AMOUNT");
  IElementType COST_COMP = new BeancountElementType("COST_COMP");
  IElementType COST_COMP_LIST = new BeancountElementType("COST_COMP_LIST");
  IElementType COST_SPEC = new BeancountElementType("COST_SPEC");
  IElementType CURRENCY_LIST = new BeancountElementType("CURRENCY_LIST");
  IElementType CURRENCY_MULTIPLE = new BeancountElementType("CURRENCY_MULTIPLE");
  IElementType CURRENCY_ONE = new BeancountElementType("CURRENCY_ONE");
  IElementType CURRENCY_SYMBOL = BeancountElementTypeFactory.factoryCurrency("CURRENCY_SYMBOL");
  IElementType CUSTOM = new BeancountElementType("CUSTOM");
  IElementType CUSTOM_VALUE = new BeancountElementType("CUSTOM_VALUE");
  IElementType CUSTOM_VALUE_LIST = new BeancountElementType("CUSTOM_VALUE_LIST");
  IElementType DECLARATIONS = new BeancountElementType("DECLARATIONS");
  IElementType DIRECTIVE = new BeancountElementType("DIRECTIVE");
  IElementType DOCUMENT = new BeancountElementType("DOCUMENT");
  IElementType END = new BeancountElementType("END");
  IElementType ENTRY = new BeancountElementType("ENTRY");
  IElementType EVENT = new BeancountElementType("EVENT");
  IElementType FILE_PATH = new BeancountElementType("FILE_PATH");
  IElementType INCLUDE = new BeancountElementType("INCLUDE");
  IElementType INCOMPLETE_AMOUNT = new BeancountElementType("INCOMPLETE_AMOUNT");
  IElementType KEY_VALUE = new BeancountElementType("KEY_VALUE");
  IElementType KEY_VALUE_LINE = new BeancountElementType("KEY_VALUE_LINE");
  IElementType KEY_VALUE_LIST = new BeancountElementType("KEY_VALUE_LIST");
  IElementType KEY_VALUE_VALUE = new BeancountElementType("KEY_VALUE_VALUE");
  IElementType LITERAL_EXPR = new BeancountElementType("LITERAL_EXPR");
  IElementType MAYBE_CURRENCY = new BeancountElementType("MAYBE_CURRENCY");
  IElementType MAYBE_NUMBER = new BeancountElementType("MAYBE_NUMBER");
  IElementType MINUS_EXPR = new BeancountElementType("MINUS_EXPR");
  IElementType NOTE = new BeancountElementType("NOTE");
  IElementType NUMBER_EXPR = new BeancountElementType("NUMBER_EXPR");
  IElementType OPEN = new BeancountElementType("OPEN");
  IElementType OPTFLAG = new BeancountElementType("OPTFLAG");
  IElementType OPTION = new BeancountElementType("OPTION");
  IElementType OPT_BOOKING = new BeancountElementType("OPT_BOOKING");
  IElementType PAD = new BeancountElementType("PAD");
  IElementType PAREN_EXPR = new BeancountElementType("PAREN_EXPR");
  IElementType PLUGIN = new BeancountElementType("PLUGIN");
  IElementType PLUS_EXPR = new BeancountElementType("PLUS_EXPR");
  IElementType POPMETA = new BeancountElementType("POPMETA");
  IElementType POPTAG = new BeancountElementType("POPTAG");
  IElementType POSTING = new BeancountElementType("POSTING");
  IElementType POSTING_OR_KV_LIST = new BeancountElementType("POSTING_OR_KV_LIST");
  IElementType PRICE = new BeancountElementType("PRICE");
  IElementType PRICE_ANNOTATION = new BeancountElementType("PRICE_ANNOTATION");
  IElementType PUSHMETA = new BeancountElementType("PUSHMETA");
  IElementType PUSHTAG = new BeancountElementType("PUSHTAG");
  IElementType QUERY = new BeancountElementType("QUERY");
  IElementType SLASH_EXPR = new BeancountElementType("SLASH_EXPR");
  IElementType TAGS_LINKS = new BeancountElementType("TAGS_LINKS");
  IElementType TRANSACTION = new BeancountElementType("TRANSACTION");
  IElementType TXN = new BeancountElementType("TXN");
  IElementType TXN_STRINGS = new BeancountElementType("TXN_STRINGS");
  IElementType UNARY_MIN = new BeancountElementType("UNARY_MIN");
  IElementType UNARY_PLUS = new BeancountElementType("UNARY_PLUS");

  IElementType ACCOUNT = new BeancountTokenType("ACCOUNT");
  IElementType ASTERISK = new BeancountTokenType("ASTERISK");
  IElementType AT = new BeancountTokenType("AT");
  IElementType ATAT = new BeancountTokenType("ATAT");
  IElementType BALANCE_KEY = new BeancountTokenType("BALANCE_KEY");
  IElementType BOOL = new BeancountTokenType("BOOL");
  IElementType CLOSE_KEY = new BeancountTokenType("CLOSE_KEY");
  IElementType COMMA = new BeancountTokenType("COMMA");
  IElementType COMMENT = new BeancountTokenType("COMMENT");
  IElementType COMMODITY_KEY = new BeancountTokenType("COMMODITY_KEY");
  IElementType CURRENCY = new BeancountTokenType("CURRENCY");
  IElementType CUSTOM_KEY = new BeancountTokenType("CUSTOM_KEY");
  IElementType DATE = new BeancountTokenType("DATE");
  IElementType DOCUMENT_KEY = new BeancountTokenType("DOCUMENT_KEY");
  IElementType EOL = new BeancountTokenType("EOL");
  IElementType EVENT_KEY = new BeancountTokenType("EVENT_KEY");
  IElementType FLAG = new BeancountTokenType("FLAG");
  IElementType HASH = new BeancountTokenType("HASH");
  IElementType IGNORED = new BeancountTokenType("IGNORED");
  IElementType INCLUDE_KEY = new BeancountTokenType("INCLUDE_KEY");
  IElementType INDENT = new BeancountTokenType("INDENT");
  IElementType KEY = new BeancountTokenType("KEY");
  IElementType LCURL = new BeancountTokenType("LCURL");
  IElementType LCURLCURL = new BeancountTokenType("LCURLCURL");
  IElementType LINK = new BeancountTokenType("LINK");
  IElementType LPAREN = new BeancountTokenType("LPAREN");
  IElementType MINUS = new BeancountTokenType("MINUS");
  IElementType NONE = new BeancountTokenType("NONE");
  IElementType NOTE_KEY = new BeancountTokenType("NOTE_KEY");
  IElementType NUMBER = new BeancountTokenType("NUMBER");
  IElementType OPEN_KEY = new BeancountTokenType("OPEN_KEY");
  IElementType OPTION_KEY = new BeancountTokenType("OPTION_KEY");
  IElementType PAD_KEY = new BeancountTokenType("PAD_KEY");
  IElementType PIPE = new BeancountTokenType("PIPE");
  IElementType PLUGIN_KEY = new BeancountTokenType("PLUGIN_KEY");
  IElementType PLUS = new BeancountTokenType("PLUS");
  IElementType POPMETA_KEY = new BeancountTokenType("POPMETA_KEY");
  IElementType POPTAG_KEY = new BeancountTokenType("POPTAG_KEY");
  IElementType PRICE_KEY = new BeancountTokenType("PRICE_KEY");
  IElementType PUSHMETA_KEY = new BeancountTokenType("PUSHMETA_KEY");
  IElementType PUSHTAG_KEY = new BeancountTokenType("PUSHTAG_KEY");
  IElementType QUERY_KEY = new BeancountTokenType("QUERY_KEY");
  IElementType RCURL = new BeancountTokenType("RCURL");
  IElementType RCURLCURL = new BeancountTokenType("RCURLCURL");
  IElementType RPAREN = new BeancountTokenType("RPAREN");
  IElementType SLASH = new BeancountTokenType("SLASH");
  IElementType STRING = new BeancountTokenType("STRING");
  IElementType TAG = new BeancountTokenType("TAG");
  IElementType TILDE = new BeancountTokenType("TILDE");
  IElementType TXN_KEY = new BeancountTokenType("TXN_KEY");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACCOUNT_DEFINITION) {
        return new BeancountAccountDefinitionImpl(node);
      }
      else if (type == ACCOUNT_SYMBOL) {
        return new BeancountAccountSymbolImpl(node);
      }
      else if (type == AMOUNT) {
        return new BeancountAmountImpl(node);
      }
      else if (type == AMOUNT_TOLERANCE) {
        return new BeancountAmountToleranceImpl(node);
      }
      else if (type == ASTERISK_EXPR) {
        return new BeancountAsteriskExprImpl(node);
      }
      else if (type == BALANCE) {
        return new BeancountBalanceImpl(node);
      }
      else if (type == CLOSE) {
        return new BeancountCloseImpl(node);
      }
      else if (type == COMMODITY) {
        return new BeancountCommodityImpl(node);
      }
      else if (type == COMPOUND_AMOUNT) {
        return new BeancountCompoundAmountImpl(node);
      }
      else if (type == COST_COMP) {
        return new BeancountCostCompImpl(node);
      }
      else if (type == COST_COMP_LIST) {
        return new BeancountCostCompListImpl(node);
      }
      else if (type == COST_SPEC) {
        return new BeancountCostSpecImpl(node);
      }
      else if (type == CURRENCY_MULTIPLE) {
        return new BeancountCurrencyMultipleImpl(node);
      }
      else if (type == CURRENCY_ONE) {
        return new BeancountCurrencyOneImpl(node);
      }
      else if (type == CURRENCY_SYMBOL) {
        return new BeancountCurrencySymbolImpl(node);
      }
      else if (type == CUSTOM) {
        return new BeancountCustomImpl(node);
      }
      else if (type == CUSTOM_VALUE) {
        return new BeancountCustomValueImpl(node);
      }
      else if (type == CUSTOM_VALUE_LIST) {
        return new BeancountCustomValueListImpl(node);
      }
      else if (type == DECLARATIONS) {
        return new BeancountDeclarationsImpl(node);
      }
      else if (type == DIRECTIVE) {
        return new BeancountDirectiveImpl(node);
      }
      else if (type == DOCUMENT) {
        return new BeancountDocumentImpl(node);
      }
      else if (type == END) {
        return new BeancountEndImpl(node);
      }
      else if (type == ENTRY) {
        return new BeancountEntryImpl(node);
      }
      else if (type == EVENT) {
        return new BeancountEventImpl(node);
      }
      else if (type == FILE_PATH) {
        return new BeancountFilePathImpl(node);
      }
      else if (type == INCLUDE) {
        return new BeancountIncludeImpl(node);
      }
      else if (type == INCOMPLETE_AMOUNT) {
        return new BeancountIncompleteAmountImpl(node);
      }
      else if (type == KEY_VALUE) {
        return new BeancountKeyValueImpl(node);
      }
      else if (type == KEY_VALUE_LINE) {
        return new BeancountKeyValueLineImpl(node);
      }
      else if (type == KEY_VALUE_LIST) {
        return new BeancountKeyValueListImpl(node);
      }
      else if (type == KEY_VALUE_VALUE) {
        return new BeancountKeyValueValueImpl(node);
      }
      else if (type == LITERAL_EXPR) {
        return new BeancountLiteralExprImpl(node);
      }
      else if (type == MAYBE_CURRENCY) {
        return new BeancountMaybeCurrencyImpl(node);
      }
      else if (type == MAYBE_NUMBER) {
        return new BeancountMaybeNumberImpl(node);
      }
      else if (type == MINUS_EXPR) {
        return new BeancountMinusExprImpl(node);
      }
      else if (type == NOTE) {
        return new BeancountNoteImpl(node);
      }
      else if (type == OPEN) {
        return new BeancountOpenImpl(node);
      }
      else if (type == OPTFLAG) {
        return new BeancountOptflagImpl(node);
      }
      else if (type == OPTION) {
        return new BeancountOptionImpl(node);
      }
      else if (type == OPT_BOOKING) {
        return new BeancountOptBookingImpl(node);
      }
      else if (type == PAD) {
        return new BeancountPadImpl(node);
      }
      else if (type == PAREN_EXPR) {
        return new BeancountParenExprImpl(node);
      }
      else if (type == PLUGIN) {
        return new BeancountPluginImpl(node);
      }
      else if (type == PLUS_EXPR) {
        return new BeancountPlusExprImpl(node);
      }
      else if (type == POPMETA) {
        return new BeancountPopmetaImpl(node);
      }
      else if (type == POPTAG) {
        return new BeancountPoptagImpl(node);
      }
      else if (type == POSTING) {
        return new BeancountPostingImpl(node);
      }
      else if (type == POSTING_OR_KV_LIST) {
        return new BeancountPostingOrKvListImpl(node);
      }
      else if (type == PRICE) {
        return new BeancountPriceImpl(node);
      }
      else if (type == PRICE_ANNOTATION) {
        return new BeancountPriceAnnotationImpl(node);
      }
      else if (type == PUSHMETA) {
        return new BeancountPushmetaImpl(node);
      }
      else if (type == PUSHTAG) {
        return new BeancountPushtagImpl(node);
      }
      else if (type == QUERY) {
        return new BeancountQueryImpl(node);
      }
      else if (type == SLASH_EXPR) {
        return new BeancountSlashExprImpl(node);
      }
      else if (type == TAGS_LINKS) {
        return new BeancountTagsLinksImpl(node);
      }
      else if (type == TRANSACTION) {
        return new BeancountTransactionImpl(node);
      }
      else if (type == TXN) {
        return new BeancountTxnImpl(node);
      }
      else if (type == TXN_STRINGS) {
        return new BeancountTxnStringsImpl(node);
      }
      else if (type == UNARY_MIN) {
        return new BeancountUnaryMinImpl(node);
      }
      else if (type == UNARY_PLUS) {
        return new BeancountUnaryPlusImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
