#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 14 "parser.y"
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.*;
#line 11 "y.tab.c"
#define TK_PRINT 257
#define TK_READ 258
#define TK_IF 259
#define TK_THEN 260
#define TK_ELSE 261
#define TK_END_IF 262
#define TK_WHILE 263
#define TK_DONE 264
#define TK_DO 265
#define TK_NEW_VAR 266
#define TK_FOR 267
#define TK_FROM 268
#define TK_TO 269
#define TK_INT 270
#define IDENTIFICADOR 271
#define STRING 272
#define NUMERO 273
#define TYPE 274
#define not 278
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    4,    4,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,
};
short yylen[] = {                                         2,
    1,    2,    3,    3,    3,    2,    2,    2,    5,    7,
    5,    9,    4,    3,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    2,    1,    1,
};
short yydefred[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   26,    7,   27,    0,    0,    0,    8,    0,    0,   15,
    0,    0,    0,    0,    0,    2,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,   24,    0,    0,    0,    0,    0,
    0,   18,   19,    0,    0,   14,   13,    0,    0,    9,
   11,    0,    0,    0,   10,    0,    0,   12,
};
short yydgoto[] = {                                       8,
    9,   10,   16,   21,
};
short yysindex[] = {                                    -90,
  -40, -269,  -37,  -37, -267, -265,  -60,    0,  -90,  -51,
    0,    0,    0,  -37,  -37,   37,    0,   25,   31,    0,
  -10, -258,  -37,  -37,  -47,    0,  -25,   37,  -37,  -37,
  -37,  -37,  -37,  -37,  -37,  -37,  -90,  -90, -250, -247,
 -245,   37,   37,    0,    0,  137,  137,  137,  137,  -28,
  -28,    0,    0, -134, -123,    0,    0, -238,  -90,    0,
    0, -237, -112, -227,    0,  -90, -101,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,   42,    0,
    0,    0,    0,    0,    0,   -9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   -6,   -4,    0,    0,  -16,   -8,   -2,    4,  -36,
  -30,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    3,   40,   86,    0,
};
#define YYTABLESIZE 313
short yytable[] = {                                      14,
   23,   17,   14,   20,   16,   22,   16,   26,   16,   41,
   17,   44,   17,   35,   17,   45,   35,   33,   36,   34,
   56,   36,   16,   16,   20,   16,   57,   58,   17,   17,
   62,   17,   21,   39,   29,   64,   30,   66,   23,   54,
   55,    1,   20,   20,   22,   20,   25,   40,   25,    6,
   21,   21,    4,   21,    5,    0,   23,   23,    0,   23,
    0,   63,   22,   22,   25,   22,   35,   33,   67,   34,
    0,   36,   35,   33,    0,   34,    0,   36,   35,   33,
    0,   34,    0,   36,   29,    0,   30,    0,   18,   19,
   29,    0,   30,   25,   25,    0,   29,    0,   30,   27,
   28,    0,   25,    0,    0,    0,   25,    0,   42,   43,
    0,    0,    0,    0,   46,   47,   48,   49,   50,   51,
   52,   53,    1,    2,    3,    0,   59,   60,    4,    0,
    0,    5,    6,    1,    2,    3,    7,    0,    0,    4,
   61,    0,    5,    6,    1,    2,    3,    7,    0,   65,
    4,    0,    0,    5,    6,    1,    2,    3,    7,    0,
    0,    4,   68,    0,    5,    6,    1,    2,    3,    7,
    0,    0,    4,    0,    0,    5,    6,    0,   35,   33,
    7,   34,    0,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   24,    0,    0,    0,
    0,    0,    0,   16,    0,    0,    0,    0,   16,   17,
   11,   12,   13,   11,   17,   13,    0,   15,   16,   16,
   15,    0,    0,   20,   17,   17,    0,    0,   20,   31,
   32,   21,    0,    0,    0,    0,   21,   23,   20,   20,
    0,    0,   23,   22,    0,   25,   21,   21,   22,    0,
   25,    0,   23,   23,    0,    0,    0,    0,   22,   22,
    0,    0,    0,    0,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   38,    0,    0,    0,   31,
   32,    0,    0,    0,    0,   31,   32,    0,    0,    0,
    0,   31,   32,
};
short yycheck[] = {                                      40,
   61,  271,   40,  271,   41,  271,   43,   59,   45,  268,
   41,   59,   43,   42,   45,   41,   42,   43,   47,   45,
  271,   47,   59,   60,   41,   62,  274,  273,   59,   60,
  269,   62,   41,   44,   60,  273,   62,  265,   41,   37,
   38,    0,   59,   60,   41,   62,   41,   58,    9,   59,
   59,   60,   59,   62,   59,   -1,   59,   60,   -1,   62,
   -1,   59,   59,   60,   59,   62,   42,   43,   66,   45,
   -1,   47,   42,   43,   -1,   45,   -1,   47,   42,   43,
   -1,   45,   -1,   47,   60,   -1,   62,   -1,    3,    4,
   60,   -1,   62,   54,   55,   -1,   60,   -1,   62,   14,
   15,   -1,   63,   -1,   -1,   -1,   67,   -1,   23,   24,
   -1,   -1,   -1,   -1,   29,   30,   31,   32,   33,   34,
   35,   36,  257,  258,  259,   -1,  261,  262,  263,   -1,
   -1,  266,  267,  257,  258,  259,  271,   -1,   -1,  263,
  264,   -1,  266,  267,  257,  258,  259,  271,   -1,  262,
  263,   -1,   -1,  266,  267,  257,  258,  259,  271,   -1,
   -1,  263,  264,   -1,  266,  267,  257,  258,  259,  271,
   -1,   -1,  263,   -1,   -1,  266,  267,   -1,   42,   43,
  271,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,   -1,
   -1,   -1,   -1,  260,   -1,   -1,   -1,   -1,  265,  260,
  271,  272,  273,  271,  265,  273,   -1,  278,  275,  276,
  278,   -1,   -1,  260,  275,  276,   -1,   -1,  265,  275,
  276,  260,   -1,   -1,   -1,   -1,  265,  260,  275,  276,
   -1,   -1,  265,  260,   -1,  260,  275,  276,  265,   -1,
  265,   -1,  275,  276,   -1,   -1,   -1,   -1,  275,  276,
   -1,   -1,   -1,   -1,  260,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  265,   -1,   -1,   -1,  275,
  276,   -1,   -1,   -1,   -1,  275,  276,   -1,   -1,   -1,
   -1,  275,  276,
};
#define YYFINAL 8
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 278
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,
"':'","';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,"TK_PRINT","TK_READ","TK_IF","TK_THEN","TK_ELSE","TK_END_IF",
"TK_WHILE","TK_DONE","TK_DO","TK_NEW_VAR","TK_FOR","TK_FROM","TK_TO","TK_INT",
"IDENTIFICADOR","STRING","NUMERO","TYPE","\"<=\"","\">=\"","\"<-\"","\"not\"",
};
char *yyrule[] = {
"$accept : main",
"main : lista_comandos",
"lista_comandos : comando ';'",
"lista_comandos : lista_comandos comando ';'",
"comando : IDENTIFICADOR '=' expr",
"comando : IDENTIFICADOR \"<-\" expr",
"comando : TK_PRINT expr",
"comando : TK_PRINT STRING",
"comando : TK_READ IDENTIFICADOR",
"comando : TK_IF expr TK_THEN lista_comandos TK_END_IF",
"comando : TK_IF expr TK_THEN lista_comandos TK_ELSE lista_comandos TK_END_IF",
"comando : TK_WHILE expr TK_DO lista_comandos TK_DONE",
"comando : TK_FOR IDENTIFICADOR TK_FROM NUMERO TK_TO NUMERO TK_DO lista_comandos TK_DONE",
"comando : TK_NEW_VAR list_declaration ':' TYPE",
"list_declaration : list_declaration ',' IDENTIFICADOR",
"list_declaration : IDENTIFICADOR",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr \">=\" expr",
"expr : expr \"<=\" expr",
"expr : '(' expr ')'",
"expr : \"not\" expr",
"expr : IDENTIFICADOR",
"expr : NUMERO",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 66 "parser.y"
/* PARTE INTERNA DA CLASSE */
private ASTNo raiz;
private ScannerJBasic scanner;
public ParserJBasic(Reader reader) {
    scanner = new ScannerJBasic(reader,this);
}
private int yylex() throws IOException {
    return scanner.yylex();
}
private void yyerror(String msg) throws Exception {
    throw new Exception(msg);
}
private ASTComando buscarUltimoComando(ASTComando cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}
	return cmd;
}
public void interpretar() throws Exception {
    yydebug=true;
	yyparse();
	raiz.interpretar(new HashMap<String,Object>());
}
public void compilar() throws Exception {
	HashSet<String> tabelaSimbolo = new HashSet<String>();
	PrintWriter printWriter;
    String output;
	String saida;
	yyparse();
	saida = raiz.compilar(tabelaSimbolo);
	
	output  = "import java.io.*;";
	output += "import java.util.*;";
	output += "public class JBasicOutput{";
	output += "public static void main(String[] args){";
	output += "Scanner scanner = new Scanner(System.in);";
	output += "double ";
	for(int i=0;i<tabelaSimbolo.size();i++) {
		output += tabelaSimbolo.toArray()[i];
		
		if(i < tabelaSimbolo.size()-1) {
			output += ",";
		}
	}
	output += ";";
	output += saida;
	output += "}}";
	printWriter = new PrintWriter("/tmp/JBasicOutput.java","UTF-8");
	printWriter.print(output);
	printWriter.close();
}
#line 273 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 23 "parser.y"
{ raiz = yyvsp[0]; }
break;
case 2:
#line 26 "parser.y"
{ yyval = yyvsp[-1]; }
break;
case 3:
#line 27 "parser.y"
{ yyval = yyvsp[-2]; buscarUltimoComando((ASTComando)yyval).setProximo((ASTComando)yyvsp[-1]); }
break;
case 4:
#line 32 "parser.y"
{ yyval = new ASTAtribuicao(((Token)yyvsp[-2]).getLexema(),(ASTExpressao)yyvsp[0]); }
break;
case 5:
#line 33 "parser.y"
{ yyval = new ASTAtribuicao(((Token)yyvsp[-2]).getLexema(),(ASTExpressao)yyvsp[0]); }
break;
case 6:
#line 34 "parser.y"
{ yyval = new ASTPrint((ASTExpressao)yyvsp[0]); }
break;
case 7:
#line 35 "parser.y"
{ yyval = new ASTPrint(((Token)yyvsp[0]).getLexema()); }
break;
case 8:
#line 36 "parser.y"
{ yyval = new ASTRead(((Token)yyvsp[0]).getLexema()); }
break;
case 9:
#line 37 "parser.y"
{ yyval = new ASTIf((ASTExpressao)yyvsp[-3],(ASTComando)yyvsp[-1]); }
break;
case 10:
#line 38 "parser.y"
{ yyval = new ASTIf((ASTExpressao)yyvsp[-5],(ASTComando)yyvsp[-3],(ASTComando)yyvsp[-1]); }
break;
case 11:
#line 39 "parser.y"
{ yyval = new ASTWhile((ASTExpressao)yyvsp[-3],(ASTComando)yyvsp[-1]); }
break;
case 12:
#line 40 "parser.y"
{ yyval = new ASTFor((((Token)yyvsp[-7]).getLexema()), new Double(((Token)yyvsp[-5]).getLexema()), new Double(((Token)yyvsp[-3]).getLexema()),(ASTComando)yyvsp[-1]);}
break;
case 13:
#line 41 "parser.y"
{ yyval = new ASTDeclaration((ASTConcatenaIdentificadores)yyvsp[-2], new String(((Token)yyvsp[0]).getLexema())); }
break;
case 14:
#line 46 "parser.y"
{ yyval = new ASTConcatenaIdentificadores((ASTExpressao)yyvsp[-2],(Token)yyvsp[0]); }
break;
case 15:
#line 47 "parser.y"
{ yyval = new ASTDeclaraVariavel(((Token)yyvsp[0]).getLexema()); }
break;
case 16:
#line 51 "parser.y"
{ yyval = new ASTSoma((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 17:
#line 52 "parser.y"
{ yyval = new ASTSubtracao((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 18:
#line 53 "parser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 19:
#line 54 "parser.y"
{ yyval = new ASTDivisao((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 20:
#line 55 "parser.y"
{ yyval = new ASTMenor((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 21:
#line 56 "parser.y"
{ yyval = new ASTMaior((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 22:
#line 57 "parser.y"
{ yyval = new ASTMaiorIgual((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 23:
#line 58 "parser.y"
{ yyval = new ASTMenorIgual((ASTExpressao)yyvsp[-2],(ASTExpressao)yyvsp[0]); }
break;
case 24:
#line 59 "parser.y"
{ yyval = yyvsp[-1]; }
break;
case 25:
#line 60 "parser.y"
{ yyval = new ASTNot((ASTExpressao)yyvsp[0]); }
break;
case 26:
#line 61 "parser.y"
{ yyval = new ASTAcessoVariavel(((Token)yyvsp[0]).getLexema()); }
break;
case 27:
#line 62 "parser.y"
{ yyval = new ASTNumero(new Double(((Token)yyvsp[0]).getLexema())); }
break;
#line 521 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
