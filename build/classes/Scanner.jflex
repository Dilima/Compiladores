/* Regras do analisador léxico do JBasic */

package br.edu.ifmg.bambui.ecomp.compiladores.jbasic;

import java.io.Reader;

%%

%class ScannerJBasic
%public
%ignorecase
%line
%byaccj

%{

private ParserJBasic parser;

public ScannerJBasic(Reader reader, ParserJBasic parser) {
    this(reader);
    this.parser = parser;
}

%}

%%

[0-9]+(\.[0-9]+)?(E[+-]?[0-9])? {
    parser.yylval = new Token(yyline,yytext());
    return ParserJBasic.NUMERO;
}

"write" { return ParserJBasic.TK_PRINT; }
"read" { return ParserJBasic.TK_READ; }
"if" { return ParserJBasic.TK_IF; }
"then" { return ParserJBasic.TK_THEN; }
"else" { return ParserJBasic.TK_ELSE; }
"fi" { return ParserJBasic.TK_END_IF; }
"while" { return ParserJBasic.TK_WHILE; }
"done" { return ParserJBasic.TK_WHILE_DONE; }
"do" { return ParserJBasic.TK_DO; }
"{" { return ParserJBasic.TK_COMMENT_START; }
"}" { return ParserJBasic.TK_COMMENT_END; }
"decl" { return ParserJBasic.TK_NEW_VAR; }
"for" { return ParserJBasic.TK_FOR; }
"from" { return ParserJBasic.TK_FOR_FROM; }
"to" { return ParserJBasic.TK_FOR_TO; }
"int" { return ParserJBasic.TK_INT; }

[a-z$_][a-z0-9$_]* {
    parser.yylval = new Token(yyline,yytext());
    return ParserJBasic.IDENTIFICADOR;
}

\" [^\"]* \" {
    parser.yylval = new Token(yyline, yytext().substring(1,yytext().length()-1));
    return ParserJBasic.STRING;
}

[ \t\n]+ { /* IGNORE */ }

. { return yytext().charAt(0); }