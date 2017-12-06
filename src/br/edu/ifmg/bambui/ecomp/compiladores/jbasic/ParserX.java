//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package br.edu.ifmg.bambui.ecomp.compiladores.jbasic;



//#line 13 "tentativaNovoParser.y"
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.*;
import declaracoes.*;
import ids.*;
//#line 24 "ParserX.java"




public class ParserX
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:ASTNo
String   yytext;//user variable to return contextual strings
ASTNo yyval; //used to return semantic vals from action routines
ASTNo yylval;//the 'lval' (result) I got from yylex()
ASTNo valstk[] = new ASTNo[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new ASTNo();
  yylval=new ASTNo();
  valptr=-1;
}
final void val_push(ASTNo val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    ASTNo[] newstack = new ASTNo[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final ASTNo val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final ASTNo val_peek(int relative)
{
  return valstk[valptr-relative];
}
final ASTNo dup_yyval(ASTNo val)
{
  return val;
}
//#### end semantic value section ####
public final static short TK_PRINT=257;
public final static short TK_READ=258;
public final static short TK_TO=259;
public final static short TK_IF=260;
public final static short TK_THEN=261;
public final static short TK_ELSE=262;
public final static short TK_END_IF=263;
public final static short TK_WHILE=264;
public final static short TK_DONE=265;
public final static short TK_DO=266;
public final static short TK_NEW_VAR=267;
public final static short TK_FOR=268;
public final static short TK_FROM=269;
public final static short TK_INT=270;
public final static short TK_BOOL=271;
public final static short TK_REAL=272;
public final static short TK_END=273;
public final static short TK_BT=274;
public final static short TK_LT=275;
public final static short IDENTIFICADOR=276;
public final static short STRING=277;
public final static short REAL=278;
public final static short BOOL=279;
public final static short INT=280;
public final static short TYPE=281;
public final static short OR=282;
public final static short AND=283;
public final static short NOT=284;
public final static short MOD=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    4,    4,    5,    6,    6,    7,
    7,    8,    8,    8,   10,   10,    3,    3,   11,   11,
   11,   11,   11,   11,   11,   11,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    1,    2,    5,    1,    3,    1,
    4,    1,    1,    1,    1,    4,    1,    2,    4,    3,
    3,    3,    5,    7,   10,    6,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    5,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   17,    6,    0,    0,
   36,    0,   37,   38,    0,    0,    0,    0,    0,    0,
    0,   18,    0,    9,   12,   14,   13,    0,    0,   21,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   20,
   22,    0,    0,    0,    0,    0,    7,    0,   35,    0,
    0,    0,    0,    0,    0,   29,   30,    0,    0,    0,
   16,   19,    0,    0,   23,    0,    0,   11,    0,   26,
    0,   24,    0,    0,    0,   25,
};
final static short yydgoto[] = {                          2,
    3,    4,   15,    5,    6,    8,   38,   39,   26,   16,
   17,
};
final static short yysindex[] = {                      -260,
 -268,    0,    0,    6, -260,    0,    0,  -35,  -40, -265,
  -37,  -37, -259,  -71,    6,  -36,    0,    0, -249, -105,
    0,  -30,    0,    0,  -37,   16,  -24,  -41,   22, -232,
  -37,    0,  -37,    0,    0,    0,    0,  -20,  -49,    0,
  -29,  -37,  -37,  -37,  -37,  -37,  -37,  -37,  -37,    0,
    0,    6,    6, -236,   45,   51,    0,  -37,    0,   89,
  -32,   89,   89,  -32,  -32,    0,    0,  -78,  -69, -214,
    0,    0,   57,    6,    0,  -11, -226,    0, -112,    0,
 -210,    0,    6,  -54,    1,    0,
};
final static short yyrindex[] = {                        19,
    0,    0,    0,    0,   42,    0,    0,    0,    0,    0,
    0,    0,    0,  -56,   66,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   56,
  -19,   62,   68,  -13,   -7,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   -2,    0,   52,    0,    0,    0,  129,   55,
   74,
};
final static int YYTABLESIZE=364;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   48,   46,   25,   47,   15,   49,    1,    7,   19,   48,
   27,   59,   48,   46,   49,   47,   14,   49,   44,   31,
   45,   34,   20,   34,   33,   34,   34,   27,   40,   27,
   44,   27,   45,   28,   51,   28,   54,   28,   57,   34,
   34,   58,   34,   70,   77,   27,   27,   80,   27,   68,
   69,   28,   28,   81,   28,   83,   18,   48,   46,   86,
   47,   10,   49,   48,   46,    2,   47,   30,   49,    0,
    0,   79,    0,   34,   50,   44,    0,   45,    0,   27,
   84,   44,    0,   45,    0,   28,   48,   46,   32,   47,
    0,   49,   48,   46,    0,   47,   33,   49,   48,   46,
    0,   47,   31,   49,   44,    0,   45,    0,   32,   72,
   44,    0,   45,    0,   33,   33,   44,   33,   45,    0,
   31,   31,    0,   31,    0,    0,   32,   32,    0,   32,
   48,   46,    0,   47,    0,   49,    0,   71,    0,   28,
   29,   32,   32,    0,    9,   10,    0,   11,   33,   78,
   82,   12,   32,   41,   31,   13,    0,   32,    0,   55,
   32,   56,    0,   14,   35,   36,   37,    0,    0,    0,
   60,   61,   62,   63,   64,   65,   66,   67,    9,   10,
    0,   11,    0,   74,   75,   12,   73,    9,   10,   13,
   11,    0,    0,    0,   12,   76,    0,   14,   13,    0,
    0,    0,    9,   10,    0,   11,   14,    0,    0,   12,
   85,    0,   15,   13,    0,    0,    0,    0,    0,   52,
    0,   14,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   42,   43,    0,   21,   22,   23,   21,   24,
   23,   34,   24,    0,   42,   43,   34,   27,    0,    0,
    0,    0,   27,   28,   34,   34,    0,    0,   28,    0,
   27,   27,    9,   10,    0,   11,   28,   28,    0,   12,
    0,    0,    0,   13,    0,    4,    4,    0,    4,    0,
    0,   14,    4,    0,    0,    0,    4,   53,    0,   42,
   43,    0,    0,    0,    4,   42,   43,    0,    3,    3,
    0,    3,    0,    0,    0,    3,    0,    0,    0,    3,
    0,    0,    0,    0,    0,    0,   33,    3,   42,   43,
    0,   33,   31,    0,   42,   43,    0,   31,   32,   33,
   42,   43,    0,   32,    0,   31,    0,    0,    0,    0,
    0,   32,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   42,   43,   40,   45,   61,   47,  267,  276,   44,   42,
  276,   41,   42,   43,   47,   45,  276,   47,   60,   91,
   62,   41,   58,   43,   61,   45,  276,   41,   59,   43,
   60,   45,   62,   41,   59,   43,  269,   45,   59,   59,
   60,   91,   62,  280,  259,   59,   60,   59,   62,   52,
   53,   59,   60,  280,   62,  266,    5,   42,   43,   59,
   45,   59,   47,   42,   43,    0,   45,   13,   47,   -1,
   -1,   74,   -1,   93,   59,   60,   -1,   62,   -1,   93,
   83,   60,   -1,   62,   -1,   93,   42,   43,   15,   45,
   -1,   47,   42,   43,   -1,   45,   41,   47,   42,   43,
   -1,   45,   41,   47,   60,   -1,   62,   -1,   41,   59,
   60,   -1,   62,   -1,   59,   60,   60,   62,   62,   -1,
   59,   60,   -1,   62,   -1,   -1,   59,   60,   -1,   62,
   42,   43,   -1,   45,   -1,   47,   -1,   93,   -1,   11,
   12,   68,   69,   -1,  257,  258,   -1,  260,   93,   93,
  263,  264,   79,   25,   93,  268,   -1,   84,   -1,   31,
   93,   33,   -1,  276,  270,  271,  272,   -1,   -1,   -1,
   42,   43,   44,   45,   46,   47,   48,   49,  257,  258,
   -1,  260,   -1,  262,  263,  264,   58,  257,  258,  268,
  260,   -1,   -1,   -1,  264,  265,   -1,  276,  268,   -1,
   -1,   -1,  257,  258,   -1,  260,  276,   -1,   -1,  264,
  265,   -1,  269,  268,   -1,   -1,   -1,   -1,   -1,  261,
   -1,  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  274,  275,   -1,  276,  277,  278,  276,  280,
  278,  261,  280,   -1,  274,  275,  266,  261,   -1,   -1,
   -1,   -1,  266,  261,  274,  275,   -1,   -1,  266,   -1,
  274,  275,  257,  258,   -1,  260,  274,  275,   -1,  264,
   -1,   -1,   -1,  268,   -1,  257,  258,   -1,  260,   -1,
   -1,  276,  264,   -1,   -1,   -1,  268,  266,   -1,  274,
  275,   -1,   -1,   -1,  276,  274,  275,   -1,  257,  258,
   -1,  260,   -1,   -1,   -1,  264,   -1,   -1,   -1,  268,
   -1,   -1,   -1,   -1,   -1,   -1,  261,  276,  274,  275,
   -1,  266,  261,   -1,  274,  275,   -1,  266,  261,  274,
  274,  275,   -1,  266,   -1,  274,   -1,   -1,   -1,   -1,
   -1,  274,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  275,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=285;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"TK_PRINT","TK_READ","TK_TO","TK_IF",
"TK_THEN","TK_ELSE","TK_END_IF","TK_WHILE","TK_DONE","TK_DO","TK_NEW_VAR",
"TK_FOR","TK_FROM","TK_INT","TK_BOOL","TK_REAL","TK_END","TK_BT","TK_LT",
"IDENTIFICADOR","STRING","REAL","BOOL","INT","TYPE","OR","AND","NOT","MOD",
};
final static String yyrule[] = {
"$accept : main",
"main : program",
"program : vars_decl comando_list",
"vars_decl : vars_decl_list",
"vars_decl :",
"vars_decl_list : var_decl",
"vars_decl_list : vars_decl_list var_decl",
"var_decl : TK_NEW_VAR id_list ':' type_specifier ';'",
"id_list : IDENTIFICADOR",
"id_list : id_list ',' IDENTIFICADOR",
"type_specifier : type",
"type_specifier : type '[' expr ']'",
"type : TK_INT",
"type : TK_REAL",
"type : TK_BOOL",
"var : IDENTIFICADOR",
"var : IDENTIFICADOR '[' expr ']'",
"comando_list : comando",
"comando_list : comando_list comando",
"comando : var '=' expr ';'",
"comando : TK_PRINT expr ';'",
"comando : TK_PRINT STRING ';'",
"comando : TK_READ IDENTIFICADOR ';'",
"comando : TK_IF expr TK_THEN comando_list TK_END_IF",
"comando : TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF",
"comando : TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE ';'",
"comando : TK_WHILE expr TK_DO comando_list TK_DONE ';'",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr TK_BT expr",
"expr : expr TK_LT expr",
"expr : '(' expr ')'",
"expr : IDENTIFICADOR",
"expr : REAL",
"expr : INT",
};

//#line 98 "tentativaNovoParser.y"
/* PARTE INTERNA DA CLASSE */

private ASTNo raiz;
private ScannerX scanner;
public ParserX(Reader reader) {
    scanner = new ScannerX(reader,this);
}
private int yylex() throws IOException {
    return scanner.yylex();
}
private void yyerror(String msg) throws Exception {
    throw new Exception(msg);
}
private ASTComando buscarUltimocomando(ASTComando cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}
	return cmd;
}
private ASTListaDeclaracoes buscarUltimaDeclaracao(ASTListaDeclaracoes cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}
	return cmd;
}
private ASTIdentificador buscarUltimoIds(ASTIdentificador cmd) {
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
	/*saida = raiz.compilar(tabelaSimbolo);
	
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
	printWriter = new PrintWriter("output.x","UTF-8");
	printWriter.print(output);
	printWriter.close();*/
}
//#line 394 "ParserX.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 23 "tentativaNovoParser.y"
{System.out.println(": program	");}
break;
case 2:
//#line 27 "tentativaNovoParser.y"
{System.out.println(":	vars_decl comando_list 						");}
break;
case 3:
//#line 32 "tentativaNovoParser.y"
{System.out.println(":	vars_decl_list 								");}
break;
case 4:
//#line 33 "tentativaNovoParser.y"
{System.out.println("|												");}
break;
case 5:
//#line 37 "tentativaNovoParser.y"
{System.out.println(":	var_decl 									");}
break;
case 6:
//#line 38 "tentativaNovoParser.y"
{System.out.println("|	vars_decl_list var_decl 					");}
break;
case 7:
//#line 42 "tentativaNovoParser.y"
{System.out.println(":	TK_NEW_VAR id_list ':' type_specifier 	';' 				");}
break;
case 8:
//#line 46 "tentativaNovoParser.y"
{System.out.println(":	IDENTIFICADOR								");}
break;
case 9:
//#line 47 "tentativaNovoParser.y"
{System.out.println("|	id_list ',' IDENTIFICADOR					");}
break;
case 10:
//#line 51 "tentativaNovoParser.y"
{System.out.println(":	type 										");}
break;
case 11:
//#line 52 "tentativaNovoParser.y"
{System.out.println("|	type '[' expr ']'							");}
break;
case 12:
//#line 56 "tentativaNovoParser.y"
{System.out.println(":	TK_INT 										");}
break;
case 13:
//#line 57 "tentativaNovoParser.y"
{System.out.println("|	TK_REAL										");}
break;
case 14:
//#line 58 "tentativaNovoParser.y"
{System.out.println("|	TK_BOOL										");}
break;
case 15:
//#line 62 "tentativaNovoParser.y"
{System.out.println(":	IDENTIFICADOR  								");}
break;
case 16:
//#line 63 "tentativaNovoParser.y"
{System.out.println("|	IDENTIFICADOR	'[' expr ']'				");}
break;
case 17:
//#line 68 "tentativaNovoParser.y"
{System.out.println(":	comando ");}
break;
case 18:
//#line 69 "tentativaNovoParser.y"
{System.out.println("|   comando_list comando ");}
break;
case 19:
//#line 73 "tentativaNovoParser.y"
{System.out.println(":   var '=' expr ");}
break;
case 20:
//#line 74 "tentativaNovoParser.y"
{System.out.println("|   TK_PRINT expr ");}
break;
case 21:
//#line 75 "tentativaNovoParser.y"
{System.out.println("|	TK_PRINT STRING ");}
break;
case 22:
//#line 76 "tentativaNovoParser.y"
{System.out.println("|   TK_READ IDENTIFICADOR ");}
break;
case 23:
//#line 77 "tentativaNovoParser.y"
{System.out.println("|   TK_IF expr TK_THEN comando_list TK_END_IF ");}
break;
case 24:
//#line 78 "tentativaNovoParser.y"
{System.out.println("|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF ");}
break;
case 25:
//#line 79 "tentativaNovoParser.y"
{System.out.println("|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	");}
break;
case 26:
//#line 80 "tentativaNovoParser.y"
{System.out.println("|	TK_WHILE expr TK_DO comando_list TK_DONE					");}
break;
case 27:
//#line 84 "tentativaNovoParser.y"
{System.out.println(":   expr '+' expr ");}
break;
case 28:
//#line 85 "tentativaNovoParser.y"
{System.out.println("|   expr '-' expr ");}
break;
case 29:
//#line 86 "tentativaNovoParser.y"
{System.out.println("|   expr '*' expr ");}
break;
case 30:
//#line 87 "tentativaNovoParser.y"
{System.out.println("|   expr '/' expr ");}
break;
case 31:
//#line 88 "tentativaNovoParser.y"
{System.out.println("|   expr '<' expr ");}
break;
case 32:
//#line 89 "tentativaNovoParser.y"
{System.out.println("|   expr '>' expr ");}
break;
case 33:
//#line 90 "tentativaNovoParser.y"
{System.out.println("|	expr TK_BT expr													");}
break;
case 34:
//#line 91 "tentativaNovoParser.y"
{System.out.println("|	expr TK_LT expr													");}
break;
case 35:
//#line 92 "tentativaNovoParser.y"
{System.out.println("|	'(' expr ')' ");}
break;
case 36:
//#line 93 "tentativaNovoParser.y"
{System.out.println("|	IDENTIFICADOR ");}
break;
case 37:
//#line 94 "tentativaNovoParser.y"
{System.out.println("|	REAL ");}
break;
case 38:
//#line 95 "tentativaNovoParser.y"
{System.out.println("|	INT ");}
break;
//#line 695 "ParserX.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
