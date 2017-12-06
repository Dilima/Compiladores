package br.edu.ifmg.bambui.ecomp.compiladores.jbasic;
import java.io.Reader;

%%

%class ScannerX
%public
%ignorecase
%line
%byaccj

%{

private ParserX parser;

public ScannerX(Reader reader, ParserX parser) {
    this(reader);
    this.parser = parser;
}

%}

%%

[0-9]+(E[+-]?[0-9])? {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.REAL;
}
[0-9]+(\.[0-9]+)? {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.REAL;    
}
[0-9]+ {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.INT; 
}
[0-9]+((\-[0-9]+)?(\+[0-9]+)?(\*[0-9]+)?)* {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.INT; 
}
(\-)?[0-9]+ {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.INT; 
}

"write" { return ParserX.TK_PRINT; }
"read" { return ParserX.TK_READ; }
"if" { return ParserX.TK_IF; }
"then" { return ParserX.TK_THEN; }
"else" { return ParserX.TK_ELSE; }
"fi" { return ParserX.TK_END_IF; }
"while" { return ParserX.TK_WHILE; }
"done" { return ParserX.TK_DONE; }
"do" { return ParserX.TK_DO; }
"decl" { return ParserX.TK_NEW_VAR; }
"for" { return ParserX.TK_FOR; }
"from" { return ParserX.TK_FROM; }
"to" { return ParserX.TK_TO; }
"int" { return ParserX.TK_INT; }
"and" { return ParserX.AND; }
"or"  { return ParserX.OR; }
"not" { return ParserX.NOT; }
"mod" { return ParserX.MOD; }

[a-z$_][a-z0-9$_]* {
    parser.yylval = new Token(yyline,yytext());
    return ParserX.IDENTIFICADOR;
}

\" [^\"]* \" {
    parser.yylval = new Token(yyline, yytext().substring(1,yytext().length()-1));
    return ParserX.STRING;
}
\{([^}]+)\} {
    /* IGNORAR OS COMENTARIOS */
}
[(\t)?(\n)?]+ { /* IGNORE */ }

. { return yytext().charAt(0); }