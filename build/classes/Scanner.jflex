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

"print" { return ParserJBasic.TK_PRINT; }
"read" { return ParserJBasic.TK_READ; }
"if" { return ParserJBasic.TK_IF; }
"then" { return ParserJBasic.TK_THEN; }
"else" { return ParserJBasic.TK_ELSE; }
"end" { return ParserJBasic.TK_END; }

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