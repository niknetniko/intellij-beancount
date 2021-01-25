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
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CURRENCY_LIST, CURRENCY_MULTIPLE, CURRENCY_ONE),
    create_token_set_(ASTERISK_EXPR, LITERAL_EXPR, MINUS_EXPR, NUMBER_EXPR,
      PAREN_EXPR, PLUS_EXPR, SLASH_EXPR, UNARY_MIN,
      UNARY_PLUS),
  };

  /* ********************************************************** */
  // ACCOUNT
  public static boolean account_definition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_definition")) return false;
    if (!nextTokenIs(b, ACCOUNT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCOUNT);
    exit_section_(b, m, ACCOUNT_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // ACCOUNT
  public static boolean account_symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "account_symbol")) return false;
    if (!nextTokenIs(b, ACCOUNT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ACCOUNT);
    exit_section_(b, m, ACCOUNT_SYMBOL, r);
    return r;
  }

  /* ********************************************************** */
  // number_expr currency_symbol
  public static boolean amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AMOUNT, "<amount>");
    r = number_expr(b, l + 1, -1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // number_expr currency_symbol | number_expr TILDE number_expr currency_symbol
  public static boolean amount_tolerance(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_tolerance")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AMOUNT_TOLERANCE, "<amount tolerance>");
    r = amount_tolerance_0(b, l + 1);
    if (!r) r = amount_tolerance_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // number_expr currency_symbol
  private static boolean amount_tolerance_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_tolerance_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = number_expr(b, l + 1, -1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // number_expr TILDE number_expr currency_symbol
  private static boolean amount_tolerance_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "amount_tolerance_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = number_expr(b, l + 1, -1);
    r = r && consumeToken(b, TILDE);
    r = r && number_expr(b, l + 1, -1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DATE BALANCE_KEY account_symbol amount_tolerance end key_value_list?
  public static boolean balance(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "balance")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, BALANCE_KEY);
    r = r && account_symbol(b, l + 1);
    r = r && amount_tolerance(b, l + 1);
    r = r && end(b, l + 1);
    r = r && balance_5(b, l + 1);
    exit_section_(b, m, BALANCE, r);
    return r;
  }

  // key_value_list?
  private static boolean balance_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "balance_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DATE CLOSE_KEY account_symbol end key_value_list?
  public static boolean close(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "close")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, CLOSE_KEY);
    r = r && account_symbol(b, l + 1);
    r = r && end(b, l + 1);
    r = r && close_4(b, l + 1);
    exit_section_(b, m, CLOSE, r);
    return r;
  }

  // key_value_list?
  private static boolean close_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "close_4")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DATE COMMODITY_KEY currency_symbol end key_value_list?
  public static boolean commodity(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commodity")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, COMMODITY_KEY);
    r = r && currency_symbol(b, l + 1);
    r = r && end(b, l + 1);
    r = r && commodity_4(b, l + 1);
    exit_section_(b, m, COMMODITY, r);
    return r;
  }

  // key_value_list?
  private static boolean commodity_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commodity_4")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // maybe_number currency_symbol
  //     | number_expr maybe_currency
  //     | maybe_number HASH maybe_number currency_symbol
  public static boolean compound_amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_amount")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPOUND_AMOUNT, "<compound amount>");
    r = compound_amount_0(b, l + 1);
    if (!r) r = compound_amount_1(b, l + 1);
    if (!r) r = compound_amount_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // maybe_number currency_symbol
  private static boolean compound_amount_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_amount_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = maybe_number(b, l + 1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // number_expr maybe_currency
  private static boolean compound_amount_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_amount_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = number_expr(b, l + 1, -1);
    r = r && maybe_currency(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // maybe_number HASH maybe_number currency_symbol
  private static boolean compound_amount_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compound_amount_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = maybe_number(b, l + 1);
    r = r && consumeToken(b, HASH);
    r = r && maybe_number(b, l + 1);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // compound_amount
  //     | DATE
  //     | STRING
  //     | ASTERISK
  public static boolean cost_comp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COST_COMP, "<cost comp>");
    r = compound_amount(b, l + 1);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, ASTERISK);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // cost_comp (COMMA cost_comp)*
  public static boolean cost_comp_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_comp_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COST_COMP_LIST, "<cost comp list>");
    r = cost_comp(b, l + 1);
    r = r && cost_comp_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
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
  // [LCURL cost_comp_list RCURL
  //     | LCURLCURL cost_comp_list RCURLCURL]
  public static boolean cost_spec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec")) return false;
    Marker m = enter_section_(b, l, _NONE_, COST_SPEC, "<cost spec>");
    cost_spec_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // LCURL cost_comp_list RCURL
  //     | LCURLCURL cost_comp_list RCURLCURL
  private static boolean cost_spec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = cost_spec_0_0(b, l + 1);
    if (!r) r = cost_spec_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LCURL cost_comp_list RCURL
  private static boolean cost_spec_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURL);
    r = r && cost_comp_list(b, l + 1);
    r = r && consumeToken(b, RCURL);
    exit_section_(b, m, null, r);
    return r;
  }

  // LCURLCURL cost_comp_list RCURLCURL
  private static boolean cost_spec_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cost_spec_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLCURL);
    r = r && cost_comp_list(b, l + 1);
    r = r && consumeToken(b, RCURLCURL);
    exit_section_(b, m, null, r);
    return r;
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
  // DATE CUSTOM_KEY STRING custom_value_list end key_value_list?
  public static boolean custom(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, CUSTOM_KEY, STRING);
    r = r && custom_value_list(b, l + 1);
    r = r && end(b, l + 1);
    r = r && custom_5(b, l + 1);
    exit_section_(b, m, CUSTOM, r);
    return r;
  }

  // key_value_list?
  private static boolean custom_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING
  //     | DATE
  //     | BOOL
  //     | amount
  //     | number_expr
  //     | account_symbol
  public static boolean custom_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CUSTOM_VALUE, "<custom value>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = amount(b, l + 1);
    if (!r) r = number_expr(b, l + 1, -1);
    if (!r) r = account_symbol(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // custom_value*
  public static boolean custom_value_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "custom_value_list")) return false;
    Marker m = enter_section_(b, l, _NONE_, CUSTOM_VALUE_LIST, "<custom value list>");
    while (true) {
      int c = current_position_(b);
      if (!custom_value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "custom_value_list", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // (directive | entry | end | error)*
  public static boolean declarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations")) return false;
    Marker m = enter_section_(b, l, _NONE_, DECLARATIONS, "<declarations>");
    while (true) {
      int c = current_position_(b);
      if (!declarations_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "declarations", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // directive | entry | end | error
  private static boolean declarations_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "declarations_0")) return false;
    boolean r;
    r = directive(b, l + 1);
    if (!r) r = entry(b, l + 1);
    if (!r) r = end(b, l + 1);
    if (!r) r = consumeToken(b, ERROR);
    return r;
  }

  /* ********************************************************** */
  // pushtag
  //     | poptag
  //     | pushmeta
  //     | popmeta
  //     | option
  //     | include
  //     | plugin
  public static boolean directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE, "<directive>");
    r = pushtag(b, l + 1);
    if (!r) r = poptag(b, l + 1);
    if (!r) r = pushmeta(b, l + 1);
    if (!r) r = popmeta(b, l + 1);
    if (!r) r = option(b, l + 1);
    if (!r) r = include(b, l + 1);
    if (!r) r = plugin(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DATE DOCUMENT_KEY account_symbol filename tags_links end key_value_list?
  public static boolean document(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, DOCUMENT_KEY);
    r = r && account_symbol(b, l + 1);
    r = r && filename(b, l + 1);
    r = r && tags_links(b, l + 1);
    r = r && end(b, l + 1);
    r = r && document_6(b, l + 1);
    exit_section_(b, m, DOCUMENT, r);
    return r;
  }

  // key_value_list?
  private static boolean document_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "document_6")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // EOL
  public static boolean end(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "end")) return false;
    if (!nextTokenIs(b, EOL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOL);
    exit_section_(b, m, END, r);
    return r;
  }

  /* ********************************************************** */
  // (transaction
  //     | balance
  //     | open
  //     | close
  //     | pad
  //     | document
  //     | note
  //     | event
  //     | price
  //     | commodity
  //     | query
  //     | custom) COMMENT?
  public static boolean entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entry_0(b, l + 1);
    r = r && entry_1(b, l + 1);
    exit_section_(b, m, ENTRY, r);
    return r;
  }

  // transaction
  //     | balance
  //     | open
  //     | close
  //     | pad
  //     | document
  //     | note
  //     | event
  //     | price
  //     | commodity
  //     | query
  //     | custom
  private static boolean entry_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry_0")) return false;
    boolean r;
    r = transaction(b, l + 1);
    if (!r) r = balance(b, l + 1);
    if (!r) r = open(b, l + 1);
    if (!r) r = close(b, l + 1);
    if (!r) r = pad(b, l + 1);
    if (!r) r = document(b, l + 1);
    if (!r) r = note(b, l + 1);
    if (!r) r = event(b, l + 1);
    if (!r) r = price(b, l + 1);
    if (!r) r = commodity(b, l + 1);
    if (!r) r = query(b, l + 1);
    if (!r) r = custom(b, l + 1);
    return r;
  }

  // COMMENT?
  private static boolean entry_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entry_1")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // DATE EVENT_KEY STRING STRING end key_value_list?
  public static boolean event(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, EVENT_KEY, STRING, STRING);
    r = r && end(b, l + 1);
    r = r && event_5(b, l + 1);
    exit_section_(b, m, EVENT, r);
    return r;
  }

  // key_value_list?
  private static boolean event_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // declarations end?
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = declarations(b, l + 1);
    r = r && file_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // end?
  private static boolean file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1")) return false;
    end(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING
  public static boolean filename(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "filename")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, FILENAME, r);
    return r;
  }

  /* ********************************************************** */
  // INCLUDE_KEY STRING end
  public static boolean include(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "include")) return false;
    if (!nextTokenIs(b, INCLUDE_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INCLUDE_KEY, STRING);
    r = r && end(b, l + 1);
    exit_section_(b, m, INCLUDE, r);
    return r;
  }

  /* ********************************************************** */
  // maybe_number maybe_currency
  public static boolean incomplete_amount(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "incomplete_amount")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INCOMPLETE_AMOUNT, "<incomplete amount>");
    r = maybe_number(b, l + 1);
    r = r && maybe_currency(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEY key_value_value
  public static boolean key_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY);
    r = r && key_value_value(b, l + 1);
    exit_section_(b, m, KEY_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // INDENT key_value end
  public static boolean key_value_line(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_line")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && key_value(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, KEY_VALUE_LINE, r);
    return r;
  }

  /* ********************************************************** */
  // (INDENT end | key_value_line)+
  public static boolean key_value_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_list")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key_value_list_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!key_value_list_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "key_value_list", c)) break;
    }
    exit_section_(b, m, KEY_VALUE_LIST, r);
    return r;
  }

  // INDENT end | key_value_line
  private static boolean key_value_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key_value_list_0_0(b, l + 1);
    if (!r) r = key_value_line(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT end
  private static boolean key_value_list_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_list_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [STRING
  //     | account_symbol
  //     | DATE
  //     | currency_symbol
  //     | TAG
  //     | BOOL
  //     | NONE
  //     | number_expr
  //     | amount]
  public static boolean key_value_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_value")) return false;
    Marker m = enter_section_(b, l, _NONE_, KEY_VALUE_VALUE, "<key value value>");
    key_value_value_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // STRING
  //     | account_symbol
  //     | DATE
  //     | currency_symbol
  //     | TAG
  //     | BOOL
  //     | NONE
  //     | number_expr
  //     | amount
  private static boolean key_value_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key_value_value_0")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = account_symbol(b, l + 1);
    if (!r) r = consumeToken(b, DATE);
    if (!r) r = currency_symbol(b, l + 1);
    if (!r) r = consumeToken(b, TAG);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, NONE);
    if (!r) r = number_expr(b, l + 1, -1);
    if (!r) r = amount(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // currency_symbol?
  public static boolean maybe_currency(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_currency")) return false;
    Marker m = enter_section_(b, l, _NONE_, MAYBE_CURRENCY, "<maybe currency>");
    currency_symbol(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // number_expr?
  public static boolean maybe_number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "maybe_number")) return false;
    Marker m = enter_section_(b, l, _NONE_, MAYBE_NUMBER, "<maybe number>");
    number_expr(b, l + 1, -1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // DATE NOTE_KEY account_symbol STRING end key_value_list?
  public static boolean note(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "note")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, NOTE_KEY);
    r = r && account_symbol(b, l + 1);
    r = r && consumeToken(b, STRING);
    r = r && end(b, l + 1);
    r = r && note_5(b, l + 1);
    exit_section_(b, m, NOTE, r);
    return r;
  }

  // key_value_list?
  private static boolean note_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "note_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DATE OPEN_KEY account_definition currency_list? opt_booking end key_value_list?
  public static boolean open(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, OPEN_KEY);
    r = r && account_definition(b, l + 1);
    r = r && open_3(b, l + 1);
    r = r && opt_booking(b, l + 1);
    r = r && end(b, l + 1);
    r = r && open_6(b, l + 1);
    exit_section_(b, m, OPEN, r);
    return r;
  }

  // currency_list?
  private static boolean open_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_3")) return false;
    currency_list(b, l + 1, -1);
    return true;
  }

  // key_value_list?
  private static boolean open_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "open_6")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING?
  public static boolean opt_booking(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opt_booking")) return false;
    Marker m = enter_section_(b, l, _NONE_, OPT_BOOKING, "<opt booking>");
    consumeToken(b, STRING);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // [ASTERISK | HASH | FLAG]
  public static boolean optflag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optflag")) return false;
    Marker m = enter_section_(b, l, _NONE_, OPTFLAG, "<optflag>");
    optflag_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // ASTERISK | HASH | FLAG
  private static boolean optflag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "optflag_0")) return false;
    boolean r;
    r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, HASH);
    if (!r) r = consumeToken(b, FLAG);
    return r;
  }

  /* ********************************************************** */
  // OPTION_KEY STRING STRING end
  public static boolean option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option")) return false;
    if (!nextTokenIs(b, OPTION_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPTION_KEY, STRING, STRING);
    r = r && end(b, l + 1);
    exit_section_(b, m, OPTION, r);
    return r;
  }

  /* ********************************************************** */
  // DATE PAD_KEY account_symbol account_symbol end key_value_list?
  public static boolean pad(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pad")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, PAD_KEY);
    r = r && account_symbol(b, l + 1);
    r = r && account_symbol(b, l + 1);
    r = r && end(b, l + 1);
    r = r && pad_5(b, l + 1);
    exit_section_(b, m, PAD, r);
    return r;
  }

  // key_value_list?
  private static boolean pad_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pad_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // PLUGIN_KEY STRING end | PLUGIN_KEY STRING STRING end
  public static boolean plugin(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin")) return false;
    if (!nextTokenIs(b, PLUGIN_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = plugin_0(b, l + 1);
    if (!r) r = plugin_1(b, l + 1);
    exit_section_(b, m, PLUGIN, r);
    return r;
  }

  // PLUGIN_KEY STRING end
  private static boolean plugin_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PLUGIN_KEY, STRING);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUGIN_KEY STRING STRING end
  private static boolean plugin_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plugin_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PLUGIN_KEY, STRING, STRING);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // POPMETA_KEY KEY end
  public static boolean popmeta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "popmeta")) return false;
    if (!nextTokenIs(b, POPMETA_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POPMETA_KEY, KEY);
    r = r && end(b, l + 1);
    exit_section_(b, m, POPMETA, r);
    return r;
  }

  /* ********************************************************** */
  // POPTAG_KEY TAG end
  public static boolean poptag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "poptag")) return false;
    if (!nextTokenIs(b, POPTAG_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, POPTAG_KEY, TAG);
    r = r && end(b, l + 1);
    exit_section_(b, m, POPTAG, r);
    return r;
  }

  /* ********************************************************** */
  // INDENT optflag account_symbol incomplete_amount cost_spec end
  //     | INDENT optflag account_symbol incomplete_amount cost_spec AT price_annotation end
  //     | INDENT optflag account_symbol incomplete_amount cost_spec ATAT price_annotation end
  //     | INDENT optflag account_symbol end
  public static boolean posting(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting")) return false;
    if (!nextTokenIs(b, INDENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = posting_0(b, l + 1);
    if (!r) r = posting_1(b, l + 1);
    if (!r) r = posting_2(b, l + 1);
    if (!r) r = posting_3(b, l + 1);
    exit_section_(b, m, POSTING, r);
    return r;
  }

  // INDENT optflag account_symbol incomplete_amount cost_spec end
  private static boolean posting_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && optflag(b, l + 1);
    r = r && account_symbol(b, l + 1);
    r = r && incomplete_amount(b, l + 1);
    r = r && cost_spec(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT optflag account_symbol incomplete_amount cost_spec AT price_annotation end
  private static boolean posting_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && optflag(b, l + 1);
    r = r && account_symbol(b, l + 1);
    r = r && incomplete_amount(b, l + 1);
    r = r && cost_spec(b, l + 1);
    r = r && consumeToken(b, AT);
    r = r && price_annotation(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT optflag account_symbol incomplete_amount cost_spec ATAT price_annotation end
  private static boolean posting_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && optflag(b, l + 1);
    r = r && account_symbol(b, l + 1);
    r = r && incomplete_amount(b, l + 1);
    r = r && cost_spec(b, l + 1);
    r = r && consumeToken(b, ATAT);
    r = r && price_annotation(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT optflag account_symbol end
  private static boolean posting_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && optflag(b, l + 1);
    r = r && account_symbol(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (INDENT end
  //     | INDENT tags_links end
  //     | key_value_line
  //     | posting)*
  public static boolean posting_or_kv_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_or_kv_list")) return false;
    Marker m = enter_section_(b, l, _NONE_, POSTING_OR_KV_LIST, "<posting or kv list>");
    while (true) {
      int c = current_position_(b);
      if (!posting_or_kv_list_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "posting_or_kv_list", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // INDENT end
  //     | INDENT tags_links end
  //     | key_value_line
  //     | posting
  private static boolean posting_or_kv_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_or_kv_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = posting_or_kv_list_0_0(b, l + 1);
    if (!r) r = posting_or_kv_list_0_1(b, l + 1);
    if (!r) r = key_value_line(b, l + 1);
    if (!r) r = posting(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT end
  private static boolean posting_or_kv_list_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_or_kv_list_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INDENT tags_links end
  private static boolean posting_or_kv_list_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "posting_or_kv_list_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && tags_links(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DATE PRICE_KEY currency_symbol amount end key_value_list?
  public static boolean price(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "price")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, PRICE_KEY);
    r = r && currency_symbol(b, l + 1);
    r = r && amount(b, l + 1);
    r = r && end(b, l + 1);
    r = r && price_5(b, l + 1);
    exit_section_(b, m, PRICE, r);
    return r;
  }

  // key_value_list?
  private static boolean price_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "price_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // incomplete_amount
  public static boolean price_annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "price_annotation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PRICE_ANNOTATION, "<price annotation>");
    r = incomplete_amount(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PUSHMETA_KEY key_value end
  public static boolean pushmeta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pushmeta")) return false;
    if (!nextTokenIs(b, PUSHMETA_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PUSHMETA_KEY);
    r = r && key_value(b, l + 1);
    r = r && end(b, l + 1);
    exit_section_(b, m, PUSHMETA, r);
    return r;
  }

  /* ********************************************************** */
  // PUSHTAG_KEY TAG end
  public static boolean pushtag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pushtag")) return false;
    if (!nextTokenIs(b, PUSHTAG_KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PUSHTAG_KEY, TAG);
    r = r && end(b, l + 1);
    exit_section_(b, m, PUSHTAG, r);
    return r;
  }

  /* ********************************************************** */
  // DATE QUERY_KEY STRING STRING end key_value_list?
  public static boolean query(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DATE, QUERY_KEY, STRING, STRING);
    r = r && end(b, l + 1);
    r = r && query_5(b, l + 1);
    exit_section_(b, m, QUERY, r);
    return r;
  }

  // key_value_list?
  private static boolean query_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "query_5")) return false;
    key_value_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (LINK | TAG)*
  public static boolean tags_links(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags_links")) return false;
    Marker m = enter_section_(b, l, _NONE_, TAGS_LINKS, "<tags links>");
    while (true) {
      int c = current_position_(b);
      if (!tags_links_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tags_links", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // LINK | TAG
  private static boolean tags_links_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tags_links_0")) return false;
    boolean r;
    r = consumeToken(b, LINK);
    if (!r) r = consumeToken(b, TAG);
    return r;
  }

  /* ********************************************************** */
  // DATE txn txn_strings tags_links end posting_or_kv_list
  public static boolean transaction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transaction")) return false;
    if (!nextTokenIs(b, DATE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATE);
    r = r && txn(b, l + 1);
    r = r && txn_strings(b, l + 1);
    r = r && tags_links(b, l + 1);
    r = r && end(b, l + 1);
    r = r && posting_or_kv_list(b, l + 1);
    exit_section_(b, m, TRANSACTION, r);
    return r;
  }

  /* ********************************************************** */
  // TXN_KEY | FLAG | ASTERISK | HASH
  public static boolean txn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "txn")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TXN, "<txn>");
    r = consumeToken(b, TXN_KEY);
    if (!r) r = consumeToken(b, FLAG);
    if (!r) r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, HASH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (STRING | PIPE)+
  public static boolean txn_strings(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "txn_strings")) return false;
    if (!nextTokenIs(b, "<txn strings>", PIPE, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TXN_STRINGS, "<txn strings>");
    r = txn_strings_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!txn_strings_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "txn_strings", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STRING | PIPE
  private static boolean txn_strings_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "txn_strings_0")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, PIPE);
    return r;
  }

  /* ********************************************************** */
  // Expression root: currency_list
  // Operator priority table:
  // 0: ATOM(currency_one)
  // 1: POSTFIX(currency_multiple)
  public static boolean currency_list(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "currency_list")) return false;
    addVariant(b, "<currency list>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<currency list>");
    r = currency_one(b, l + 1);
    p = r;
    r = r && currency_list_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean currency_list_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "currency_list_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 1 && currency_multiple_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CURRENCY_MULTIPLE, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // currency_symbol
  public static boolean currency_one(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "currency_one")) return false;
    if (!nextTokenIsSmart(b, CURRENCY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = currency_symbol(b, l + 1);
    exit_section_(b, m, CURRENCY_ONE, r);
    return r;
  }

  // COMMA currency_symbol
  private static boolean currency_multiple_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "currency_multiple_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && currency_symbol(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: number_expr
  // Operator priority table:
  // 0: ATOM(literal_expr)
  // 1: BINARY(plus_expr)
  // 2: BINARY(minus_expr)
  // 3: BINARY(asterisk_expr)
  // 4: BINARY(slash_expr)
  // 5: PREFIX(unary_min)
  // 6: PREFIX(unary_plus)
  // 7: PREFIX(paren_expr)
  public static boolean number_expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "number_expr")) return false;
    addVariant(b, "<number expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<number expr>");
    r = literal_expr(b, l + 1);
    if (!r) r = unary_plus(b, l + 1);
    if (!r) r = unary_min(b, l + 1);
    if (!r) r = paren_expr(b, l + 1);
    p = r;
    r = r && number_expr_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean number_expr_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "number_expr_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 1 && consumeTokenSmart(b, PLUS)) {
        r = number_expr(b, l, 1);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, MINUS)) {
        r = number_expr(b, l, 2);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, ASTERISK)) {
        r = number_expr(b, l, 3);
        exit_section_(b, l, m, ASTERISK_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, SLASH)) {
        r = number_expr(b, l, 4);
        exit_section_(b, l, m, SLASH_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // NUMBER
  public static boolean literal_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_expr")) return false;
    if (!nextTokenIsSmart(b, NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, NUMBER);
    exit_section_(b, m, LITERAL_EXPR, r);
    return r;
  }

  public static boolean unary_plus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plus")) return false;
    if (!nextTokenIsSmart(b, PLUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, PLUS);
    p = r;
    r = p && number_expr(b, l, 6);
    exit_section_(b, l, m, UNARY_PLUS, r, p, null);
    return r || p;
  }

  public static boolean unary_min(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_min")) return false;
    if (!nextTokenIsSmart(b, MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, MINUS);
    p = r;
    r = p && number_expr(b, l, 5);
    exit_section_(b, l, m, UNARY_MIN, r, p, null);
    return r || p;
  }

  public static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r;
    r = p && number_expr(b, l, -1);
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    exit_section_(b, l, m, PAREN_EXPR, r, p, null);
    return r || p;
  }

}
