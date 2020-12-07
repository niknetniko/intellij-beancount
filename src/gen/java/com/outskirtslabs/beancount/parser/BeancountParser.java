// This is a generated file. Not intended for manual editing.
package com.outskirtslabs.beancount.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.outskirtslabs.beancount.psi.BeancountTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import static com.outskirtslabs.beancount.psi.impl.BeancountPsiImplUtil.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class BeancountParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return beancountFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(DIV_EXPR, EXPR, LITERAL_EXPR, MINUS_EXPR,
      MUL_EXPR, PAREN_EXPR, PLUS_EXPR, UNARY_MIN_EXPR,
      UNARY_PLUS_EXPR),
  };

  /* ********************************************************** */
  // EOL | <<eof>>
  static boolean END(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "END")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ACCOUNT_WORD account_segment*
  public static boolean account(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account")) return false;
    if (!nextTokenIs(b, ACCOUNT_WORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCOUNT_WORD);
    r = r && account_1(b, l + 1);
    exit_section_(b, m, ACCOUNT, r);
    return r;
  }

  // account_segment*
  private static boolean account_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!account_segment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "account_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // COLON
  public static boolean account_delimiter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_delimiter")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    exit_section_(b, m, ACCOUNT_DELIMITER, r);
    return r;
  }

  /* ********************************************************** */
  // ACCOUNT_WORD
  public static boolean account_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_name")) return false;
    if (!nextTokenIs(b, ACCOUNT_WORD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCOUNT_WORD);
    exit_section_(b, m, ACCOUNT_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // account_delimiter account_name
  public static boolean account_segment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_segment")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = account_delimiter(b, l + 1);
    r = r && account_name(b, l + 1);
    exit_section_(b, m, ACCOUNT_SEGMENT, r);
    return r;
  }

  /* ********************************************************** */
  // currency_symbol expr [space] | expr [space] currency_symbol
  public static boolean amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AMOUNT, "<amount>");
    r = amount_0(b, l + 1);
    if (!r) r = amount_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // currency_symbol expr [space]
  private static boolean amount_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = currency_symbol(b, l + 1);
    r = r && expr(b, l + 1, -1);
    r = r && amount_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [space]
  private static boolean amount_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_0_2")) return false;
    consumeToken(b, SPACE);
    return true;
  }

  // expr [space] currency_symbol
  private static boolean amount_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expr(b, l + 1, -1);
    r = r && amount_1_1(b, l + 1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [space]
  private static boolean amount_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_1_1")) return false;
    consumeToken(b, SPACE);
    return true;
  }

  /* ********************************************************** */
  // DATE BALANCE account amount comment? END
  public static boolean balance_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "balance_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BALANCE_DIR, null);
    r = consumeTokens(b, 2, DATE, BALANCE);
    p = r; // pin = 2
    r = r && report_error_(b, account(b, l + 1));
    r = p && report_error_(b, amount(b, l + 1)) && r;
    r = p && report_error_(b, balance_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean balance_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "balance_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // blank* item*
  static boolean beancountFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "beancountFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = beancountFile_0(b, l + 1);
    r = r && beancountFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // blank*
  private static boolean beancountFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "beancountFile_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!blank(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "beancountFile_0", c)) break;
    }
    return true;
  }

  // item*
  private static boolean beancountFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "beancountFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "beancountFile_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // comment eol | eol | skip | ASTERISK | DIVIDE | PLUS
  //     | LPAREN | RPAREN | MINUS | CARET | HASH | LCURL | RCURL | AT | NUMBER
  static boolean blank(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "blank")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, COMMENT, EOL);
    if (!r) r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, SKIP);
    if (!r) r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, DIVIDE);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, LPAREN);
    if (!r) r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, CARET);
    if (!r) r = consumeToken(b, HASH);
    if (!r) r = consumeToken(b, LCURL);
    if (!r) r = consumeToken(b, RCURL);
    if (!r) r = consumeToken(b, AT);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DATE COMMODITY currency_symbol comment? END
  public static boolean commodity_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commodity_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, COMMODITY_DIR, null);
    r = consumeTokens(b, 2, DATE, COMMODITY);
    p = r; // pin = 2
    r = r && report_error_(b, currency_symbol(b, l + 1));
    r = p && report_error_(b, commodity_dir_3(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean commodity_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commodity_dir_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // expr HASH amount
  public static boolean compound_amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_amount")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPOUND_AMOUNT, "<compound amount>");
    r = expr(b, l + 1, -1);
    r = r && consumeToken(b, HASH);
    r = r && amount(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // compound_amount | amount | DATE | STRING | ASTERISK
  public static boolean cost_comp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COST_COMP, "<cost comp>");
    r = compound_amount(b, l + 1);
    if (!r) r = amount(b, l + 1);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, ASTERISK);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // cost_comp? (COMMA cost_comp)*
  public static boolean cost_comp_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COST_COMP_LIST, "<cost comp list>");
    r = cost_comp_list_0(b, l + 1);
    r = r && cost_comp_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // cost_comp?
  private static boolean cost_comp_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp_list_0")) return false;
    cost_comp(b, l + 1);
    return true;
  }

  // (COMMA cost_comp)*
  private static boolean cost_comp_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp_list_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!cost_comp_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "cost_comp_list_1", c)) break;
    }
    return true;
  }

  // COMMA cost_comp
  private static boolean cost_comp_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && cost_comp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LCURL cost_comp_list? RCURL) | (LCURLCURL cost_comp_list? RCURLCURL)
  public static boolean cost_spec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec")) return false;
    if (!nextTokenIs(b, "<cost spec>", LCURL, LCURLCURL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COST_SPEC, "<cost spec>");
    r = cost_spec_0(b, l + 1);
    if (!r) r = cost_spec_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LCURL cost_comp_list? RCURL
  private static boolean cost_spec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURL);
    r = r && cost_spec_0_1(b, l + 1);
    r = r && consumeToken(b, RCURL);
    exit_section_(b, m, null, r);
    return r;
  }

  // cost_comp_list?
  private static boolean cost_spec_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_0_1")) return false;
    cost_comp_list(b, l + 1);
    return true;
  }

  // LCURLCURL cost_comp_list? RCURLCURL
  private static boolean cost_spec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLCURL);
    r = r && cost_spec_1_1(b, l + 1);
    r = r && consumeToken(b, RCURLCURL);
    exit_section_(b, m, null, r);
    return r;
  }

  // cost_comp_list?
  private static boolean cost_spec_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_1_1")) return false;
    cost_comp_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // CURRENCY
  public static boolean currency_symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "currency_symbol")) return false;
    if (!nextTokenIs(b, CURRENCY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CURRENCY);
    exit_section_(b, m, CURRENCY_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // DATE CUSTOM STRING (account|STRING|DATE|amount|expr|BOOLEAN)* comment? END
  public static boolean custom_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CUSTOM_DIR, null);
    r = consumeTokens(b, 2, DATE, CUSTOM, STRING);
    p = r; // pin = 2
    r = r && report_error_(b, custom_dir_3(b, l + 1));
    r = p && report_error_(b, custom_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (account|STRING|DATE|amount|expr|BOOLEAN)*
  private static boolean custom_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_dir_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!custom_dir_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "custom_dir_3", c)) break;
    }
    return true;
  }

  // account|STRING|DATE|amount|expr|BOOLEAN
  private static boolean custom_dir_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_dir_3_0")) return false;
    boolean r;
    r = account(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = amount(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, BOOLEAN);
    return r;
  }

  // comment?
  private static boolean custom_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // commodity_dir
  //     | open_dir
  //     | transaction_dir
  //     | balance_dir
  //     | event_dir
  //     | price_dir
  //     | custom_dir
  //     | pad_dir
  //     | document_dir
  //     | note_dir
  //     | query_dir
  public static boolean directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = commodity_dir(b, l + 1);
    if (!r) r = open_dir(b, l + 1);
    if (!r) r = transaction_dir(b, l + 1);
    if (!r) r = balance_dir(b, l + 1);
    if (!r) r = event_dir(b, l + 1);
    if (!r) r = price_dir(b, l + 1);
    if (!r) r = custom_dir(b, l + 1);
    if (!r) r = pad_dir(b, l + 1);
    if (!r) r = document_dir(b, l + 1);
    if (!r) r = note_dir(b, l + 1);
    if (!r) r = query_dir(b, l + 1);
    exit_section_(b, m, DIRECTIVE, r);
    return r;
  }

  /* ********************************************************** */
  // DATE DOCUMENT account STRING comment? END
  public static boolean document_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOCUMENT_DIR, null);
    r = consumeTokens(b, 2, DATE, DOCUMENT);
    p = r; // pin = 2
    r = r && report_error_(b, account(b, l + 1));
    r = p && report_error_(b, consumeToken(b, STRING)) && r;
    r = p && report_error_(b, document_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean document_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // DATE EVENT STRING STRING comment? END
  public static boolean event_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EVENT_DIR, null);
    r = consumeTokens(b, 2, DATE, EVENT, STRING, STRING);
    p = r; // pin = 2
    r = r && report_error_(b, event_dir_4(b, l + 1));
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean event_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // INCLUDE STRING comment? END
  public static boolean include_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_dir")) return false;
    if (!nextTokenIs(b, INCLUDE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INCLUDE_DIR, null);
    r = consumeTokens(b, 1, INCLUDE, STRING);
    p = r; // pin = 1
    r = r && report_error_(b, include_dir_2(b, l + 1));
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean include_dir_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include_dir_2")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // expr? currency_symbol?
  static boolean incomplete_amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "incomplete_amount")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = incomplete_amount_0(b, l + 1);
    r = r && incomplete_amount_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expr?
  private static boolean incomplete_amount_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "incomplete_amount_0")) return false;
    expr(b, l + 1, -1);
    return true;
  }

  // currency_symbol?
  private static boolean incomplete_amount_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "incomplete_amount_1")) return false;
    currency_symbol(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // !<<eof>>  (metadata_carrier|option_dir|include_dir|plugin_dir|blank)
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_0(b, l + 1);
    r = r && item_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<eof>>
  private static boolean item_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // metadata_carrier|option_dir|include_dir|plugin_dir|blank
  private static boolean item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_1")) return false;
    boolean r;
    r = metadata_carrier(b, l + 1);
    if (!r) r = option_dir(b, l + 1);
    if (!r) r = include_dir(b, l + 1);
    if (!r) r = plugin_dir(b, l + 1);
    if (!r) r = blank(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // META_KEY META_KV_DELIMITER key_value_value
  public static boolean key_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value")) return false;
    if (!nextTokenIs(b, META_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, META_KEY, META_KV_DELIMITER);
    r = r && key_value_value(b, l + 1);
    exit_section_(b, m, KEY_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // INDENT key_value END
  static boolean key_value_line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_line")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && key_value(b, l + 1);
    r = r && END(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING | account | DATE | currency_symbol | expr | amount | NUMBER | bool | tag | empty
  public static boolean key_value_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEY_VALUE_VALUE, "<key value value>");
    r = consumeToken(b, STRING);
    if (!r) r = account(b, l + 1);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = currency_symbol(b, l + 1);
    if (!r) r = expr(b, l + 1, -1);
    if (!r) r = amount(b, l + 1);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, TAG);
    if (!r) r = consumeToken(b, EMPTY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // CARET TAG_LINK_VALUE
  public static boolean link_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "link_value")) return false;
    if (!nextTokenIs(b, CARET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CARET, TAG_LINK_VALUE);
    exit_section_(b, m, LINK_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // directive metadata_list
  static boolean metadata_carrier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadata_carrier")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = directive(b, l + 1);
    r = r && metadata_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // key_value_line*
  static boolean metadata_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "metadata_list")) return false;
    while (true) {
      int c = current_position_(b);
      if (!key_value_line(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "metadata_list", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DATE NOTE account STRING comment? END
  public static boolean note_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "note_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NOTE_DIR, null);
    r = consumeTokens(b, 2, DATE, NOTE);
    p = r; // pin = 2
    r = r && report_error_(b, account(b, l + 1));
    r = p && report_error_(b, consumeToken(b, STRING)) && r;
    r = p && report_error_(b, note_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean note_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "note_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // DATE OPEN account currency_symbol? comment? END
  public static boolean open_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OPEN_DIR, null);
    r = consumeTokens(b, 2, DATE, OPEN);
    p = r; // pin = 2
    r = r && report_error_(b, account(b, l + 1));
    r = p && report_error_(b, open_dir_3(b, l + 1)) && r;
    r = p && report_error_(b, open_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // currency_symbol?
  private static boolean open_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_dir_3")) return false;
    currency_symbol(b, l + 1);
    return true;
  }

  // comment?
  private static boolean open_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // OPTION STRING STRING comment? END
  public static boolean option_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_dir")) return false;
    if (!nextTokenIs(b, OPTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OPTION_DIR, null);
    r = consumeTokens(b, 1, OPTION, STRING, STRING);
    p = r; // pin = 1
    r = r && report_error_(b, option_dir_3(b, l + 1));
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean option_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_dir_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // DATE PAD account account comment? END
  public static boolean pad_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pad_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PAD_DIR, null);
    r = consumeTokens(b, 2, DATE, PAD);
    p = r; // pin = 2
    r = r && report_error_(b, account(b, l + 1));
    r = p && report_error_(b, account(b, l + 1)) && r;
    r = p && report_error_(b, pad_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean pad_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pad_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // PLUGIN STRING STRING* comment? END
  public static boolean plugin_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin_dir")) return false;
    if (!nextTokenIs(b, PLUGIN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PLUGIN_DIR, null);
    r = consumeTokens(b, 1, PLUGIN, STRING);
    p = r; // pin = 1
    r = r && report_error_(b, plugin_dir_2(b, l + 1));
    r = p && report_error_(b, plugin_dir_3(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // STRING*
  private static boolean plugin_dir_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin_dir_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, STRING)) break;
      if (!empty_element_parsed_guard_(b, "plugin_dir_2", c)) break;
    }
    return true;
  }

  // comment?
  private static boolean plugin_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin_dir_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // INDENT account amount cost_spec? posting_price? comment? END metadata_list
  public static boolean posting_line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_line")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && account(b, l + 1);
    r = r && amount(b, l + 1);
    r = r && posting_line_3(b, l + 1);
    r = r && posting_line_4(b, l + 1);
    r = r && posting_line_5(b, l + 1);
    r = r && END(b, l + 1);
    r = r && metadata_list(b, l + 1);
    exit_section_(b, m, POSTING_LINE, r);
    return r;
  }

  // cost_spec?
  private static boolean posting_line_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_line_3")) return false;
    cost_spec(b, l + 1);
    return true;
  }

  // posting_price?
  private static boolean posting_line_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_line_4")) return false;
    posting_price(b, l + 1);
    return true;
  }

  // comment?
  private static boolean posting_line_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_line_5")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // posting_line*
  public static boolean posting_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_list")) return false;
    Marker m = enter_section_(b, l, _NONE_, POSTING_LIST, "<posting list>");
    while (true) {
      int c = current_position_(b);
      if (!posting_line(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "posting_list", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // (AT | ATAT) incomplete_amount
  public static boolean posting_price(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_price")) return false;
    if (!nextTokenIs(b, "<posting price>", AT, ATAT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, POSTING_PRICE, "<posting price>");
    r = posting_price_0(b, l + 1);
    r = r && incomplete_amount(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // AT | ATAT
  private static boolean posting_price_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_price_0")) return false;
    boolean r;
    r = consumeToken(b, AT);
    if (!r) r = consumeToken(b, ATAT);
    return r;
  }

  /* ********************************************************** */
  // DATE PRICE currency_symbol amount comment? END
  public static boolean price_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "price_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PRICE_DIR, null);
    r = consumeTokens(b, 2, DATE, PRICE);
    p = r; // pin = 2
    r = r && report_error_(b, currency_symbol(b, l + 1));
    r = p && report_error_(b, amount(b, l + 1)) && r;
    r = p && report_error_(b, price_dir_4(b, l + 1)) && r;
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean price_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "price_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // DATE QUERY STRING STRING comment? END
  public static boolean query_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, QUERY_DIR, null);
    r = consumeTokens(b, 2, DATE, QUERY, STRING, STRING);
    p = r; // pin = 2
    r = r && report_error_(b, query_dir_4(b, l + 1));
    r = p && END(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // comment?
  private static boolean query_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_dir_4")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // link_value | tag_value
  public static boolean tag_link(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_link")) return false;
    if (!nextTokenIs(b, "<tag link>", CARET, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TAG_LINK, "<tag link>");
    r = link_value(b, l + 1);
    if (!r) r = tag_value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // HASH TAG_LINK_VALUE
  public static boolean tag_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_value")) return false;
    if (!nextTokenIs(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HASH, TAG_LINK_VALUE);
    exit_section_(b, m, TAG_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // DATE (TXN|FLAG) STRING? STRING? tag_link* comment? END comment? metadata_list comment? posting_list
  public static boolean transaction_dir(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TRANSACTION_DIR, null);
    r = consumeToken(b, DATE);
    r = r && transaction_dir_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, transaction_dir_2(b, l + 1));
    r = p && report_error_(b, transaction_dir_3(b, l + 1)) && r;
    r = p && report_error_(b, transaction_dir_4(b, l + 1)) && r;
    r = p && report_error_(b, transaction_dir_5(b, l + 1)) && r;
    r = p && report_error_(b, END(b, l + 1)) && r;
    r = p && report_error_(b, transaction_dir_7(b, l + 1)) && r;
    r = p && report_error_(b, metadata_list(b, l + 1)) && r;
    r = p && report_error_(b, transaction_dir_9(b, l + 1)) && r;
    r = p && posting_list(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // TXN|FLAG
  private static boolean transaction_dir_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_1")) return false;
    boolean r;
    r = consumeToken(b, TXN);
    if (!r) r = consumeToken(b, FLAG);
    return r;
  }

  // STRING?
  private static boolean transaction_dir_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_2")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // STRING?
  private static boolean transaction_dir_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_3")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // tag_link*
  private static boolean transaction_dir_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!tag_link(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "transaction_dir_4", c)) break;
    }
    return true;
  }

  // comment?
  private static boolean transaction_dir_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_5")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // comment?
  private static boolean transaction_dir_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_7")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // comment?
  private static boolean transaction_dir_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction_dir_9")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // Expression root: expr
  // Operator priority table:
  // 0: BINARY(plus_expr) BINARY(minus_expr)
  // 1: BINARY(mul_expr) BINARY(div_expr)
  // 2: PREFIX(unary_plus_expr) PREFIX(unary_min_expr)
  // 3: ATOM(literal_expr) PREFIX(paren_expr)
  public static boolean expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expr")) return false;
    addVariant(b, "<expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expr>");
    r = unary_plus_expr(b, l + 1);
    if (!r) r = unary_min_expr(b, l + 1);
    if (!r) r = literal_expr(b, l + 1);
    if (!r) r = paren_expr(b, l + 1);
    p = r;
    r = r && expr_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expr_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expr_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && consumeTokenSmart(b, PLUS)) {
        r = expr(b, l, 0);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 0 && consumeTokenSmart(b, MINUS)) {
        r = expr(b, l, 0);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 1 && consumeTokenSmart(b, ASTERISK)) {
        r = expr(b, l, 1);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 1 && consumeTokenSmart(b, DIVIDE)) {
        r = expr(b, l, 1);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  public static boolean unary_plus_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plus_expr")) return false;
    if (!nextTokenIsSmart(b, PLUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, PLUS);
    p = r;
    r = p && expr(b, l, 2);
    exit_section_(b, l, m, UNARY_PLUS_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_min_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_min_expr")) return false;
    if (!nextTokenIsSmart(b, MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, MINUS);
    p = r;
    r = p && expr(b, l, 2);
    exit_section_(b, l, m, UNARY_MIN_EXPR, r, p, null);
    return r || p;
  }

  // NUMBER | NEGATIVE_NUMBER
  public static boolean literal_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_expr")) return false;
    if (!nextTokenIsSmart(b, NEGATIVE_NUMBER, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_EXPR, "<literal expr>");
    r = consumeTokenSmart(b, NUMBER);
    if (!r) r = consumeTokenSmart(b, NEGATIVE_NUMBER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r;
    r = p && expr(b, l, -1);
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    exit_section_(b, l, m, PAREN_EXPR, r, p, null);
    return r || p;
  }

}
