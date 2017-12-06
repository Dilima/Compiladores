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
public final static short IDENTIFICADOR=274;
public final static short STRING=275;
public final static short REAL=276;
public final static short BOOL=277;
public final static short INT=278;
public final static short TYPE=279;
public final static short OR=280;
public final static short AND=281;
public final static short NOT=282;
public final static short MOD=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    4,    4,    5,    6,    6,    7,
    7,    7,    8,    8,    8,   10,   10,   10,    3,    3,
   11,   11,   11,   11,   11,   11,   11,   11,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    1,    2,    5,    1,    3,    1,
    4,    4,    1,    1,    1,    1,    4,    4,    2,    3,
    3,    2,    2,    2,    5,    7,    9,    5,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    5,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
   38,   23,   39,    0,    0,   24,    0,    0,    0,    0,
    0,    0,   19,    9,   13,   15,   14,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   20,    0,    7,    0,   37,    0,    0,
    0,    0,    0,    0,   31,   32,    0,    0,    0,   17,
   18,    0,    0,    0,   25,   28,    0,   11,   12,    0,
    0,   26,    0,    0,   27,
};
final static short yydgoto[] = {                          2,
    3,    4,   15,    5,    6,    8,   38,   39,   25,   16,
   17,
};
final static short yysindex[] = {                      -262,
 -267,    0,    0,  -66, -262,    0,    0,  -16,   -9, -251,
    9,    9, -245,  -48,  -66,  -28,  -19,    0, -228, -159,
    0,    0,    0,    9,   54,    0,   17,   24, -219,  -39,
   -8,    9,    0,    0,    0,    0,    0,   -6,  -26,  -30,
    9,    9,    9,    9,    9,    9,    9,    9,  -66,  -66,
 -222,  -32,   38,    0,   54,    0,    8,    0,   63,   63,
   63,   63,  -33,  -33,    0,    0, -108,  -96, -196,    0,
    0,  -23,   47,  -66,    0,    0, -205,    0,    0,  -87,
 -190,    0,  -66,  -75,    0,
};
final static short yyrindex[] = {                       -51,
    0,    0,    0,    0,  -36,    0,    0,    0,    0,    0,
    0,    0,    0,  -58,   82,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   34,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   36,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,  -25,  -21,
  -15,   -5,  -41,  -35,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   68,    0,  114,    0,    0,    0,   91,  107,
    7,
};
final static int YYTABLESIZE=339;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   24,   29,   16,   29,    1,   30,    7,   30,   47,   30,
   58,   47,   45,   48,   46,   33,   48,   29,   29,   34,
   29,   31,   26,   30,   30,   36,   30,   19,   14,   41,
   24,   42,   32,   33,   33,   35,   33,   34,   34,   33,
   34,   20,   30,   36,   36,   34,   36,   24,   24,   51,
   54,   29,   56,   35,   35,   69,   35,   30,   47,   45,
   70,   46,   77,   48,   57,   47,   45,   33,   46,   78,
   48,   34,   81,   31,   31,   83,   41,   36,   42,   47,
   45,    2,   46,   41,   48,   42,   31,   35,   47,   45,
   31,   46,   22,   48,   10,   47,   45,   41,   46,   42,
   48,   27,   28,   21,   47,   45,   41,   46,   42,   48,
   35,   36,   37,   41,   40,   42,   67,   68,   18,   29,
   53,    0,   55,    0,    0,    0,    0,    0,    0,    0,
   71,   59,   60,   61,   62,   63,   64,   65,   66,   79,
    0,   80,    0,    0,    0,    0,    0,   73,    9,   10,
   84,   11,    0,   74,   75,   12,    0,    0,    0,   13,
    9,   10,    0,   11,    0,   14,    0,   12,   76,    9,
   10,   13,   11,    0,    0,   82,   12,   14,    0,    0,
   13,    9,   10,    0,   11,    0,   14,    0,   12,   85,
    9,   10,   13,   11,    0,    0,    0,   12,   14,    0,
    0,   13,    0,    0,    0,    4,    4,   14,    4,    0,
   16,    0,    4,    0,    0,    0,    4,    0,    0,   29,
    3,    3,    4,    3,   29,   30,    0,    3,    0,    0,
   30,    3,    0,    0,   21,   33,   23,    3,   52,   34,
   33,    0,   29,   29,   34,   36,    0,    0,   30,   30,
   36,    0,    0,   43,   44,   35,    0,    0,   33,   33,
   35,    0,   34,   34,   21,   22,   23,    0,   36,   36,
    0,    0,    0,    0,    0,    0,    0,   49,   35,   35,
    0,   21,   21,   23,   23,   72,    0,    0,    0,   50,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   43,   44,    0,    0,    0,    0,    0,   43,   44,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,   44,    0,    0,    0,    0,    0,    0,    0,
   43,   44,    0,    0,    0,    0,    0,   43,   44,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   40,   43,   61,   45,  267,   41,  274,   43,   42,   45,
   41,   42,   43,   47,   45,   41,   47,   59,   60,   41,
   62,   15,  274,   59,   60,   41,   62,   44,  274,   60,
   40,   62,   61,   59,   60,   41,   62,   59,   60,   59,
   62,   58,   91,   59,   60,  274,   62,   40,   40,  269,
   59,   93,   59,   59,   60,  278,   62,   93,   42,   43,
   93,   45,  259,   47,   91,   42,   43,   93,   45,   93,
   47,   93,  278,   67,   68,  266,   60,   93,   62,   42,
   43,    0,   45,   60,   47,   62,   80,   93,   42,   43,
   84,   45,   59,   47,   59,   42,   43,   60,   45,   62,
   47,   11,   12,   59,   42,   43,   60,   45,   62,   47,
  270,  271,  272,   60,   24,   62,   49,   50,    5,   13,
   30,   -1,   32,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   41,   42,   43,   44,   45,   46,   47,   48,   93,
   -1,   74,   -1,   -1,   -1,   -1,   -1,   57,  257,  258,
   83,  260,   -1,  262,  263,  264,   -1,   -1,   -1,  268,
  257,  258,   -1,  260,   -1,  274,   -1,  264,  265,  257,
  258,  268,  260,   -1,   -1,  263,  264,  274,   -1,   -1,
  268,  257,  258,   -1,  260,   -1,  274,   -1,  264,  265,
  257,  258,  268,  260,   -1,   -1,   -1,  264,  274,   -1,
   -1,  268,   -1,   -1,   -1,  257,  258,  274,  260,   -1,
  269,   -1,  264,   -1,   -1,   -1,  268,   -1,   -1,  261,
  257,  258,  274,  260,  266,  261,   -1,  264,   -1,   -1,
  266,  268,   -1,   -1,  274,  261,  276,  274,  278,  261,
  266,   -1,  284,  285,  266,  261,   -1,   -1,  284,  285,
  266,   -1,   -1,  284,  285,  261,   -1,   -1,  284,  285,
  266,   -1,  284,  285,  274,  275,  276,   -1,  284,  285,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  261,  284,  285,
   -1,  274,  274,  276,  276,  278,   -1,   -1,   -1,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  285,   -1,   -1,   -1,   -1,   -1,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  284,  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  285,   -1,   -1,   -1,   -1,   -1,  284,  285,
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
"TK_FOR","TK_FROM","TK_INT","TK_BOOL","TK_REAL","TK_END","IDENTIFICADOR",
"STRING","REAL","BOOL","INT","TYPE","OR","AND","NOT","MOD","\"<=\"","\">=\"",
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
"type_specifier : type '[' INT ']'",
"type_specifier : type '[' expr ']'",
"type : TK_INT",
"type : TK_REAL",
"type : TK_BOOL",
"var : IDENTIFICADOR",
"var : IDENTIFICADOR '[' INT ']'",
"var : IDENTIFICADOR '[' expr ']'",
"comando_list : comando ';'",
"comando_list : comando_list comando ';'",
"comando : var '=' expr",
"comando : TK_PRINT expr",
"comando : TK_PRINT STRING",
"comando : TK_READ IDENTIFICADOR",
"comando : TK_IF expr TK_THEN comando_list TK_END_IF",
"comando : TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF",
"comando : TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE",
"comando : TK_WHILE expr TK_DO comando_list TK_DONE",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : expr \">=\" expr",
"expr : expr \"<=\" expr",
"expr : '(' expr ')'",
"expr : IDENTIFICADOR",
"expr : REAL",
};

//#line 99 "tentativaNovoParser.y"
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
public void compilar() throws Exception { yydebug=true;
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
	printWriter = new PrintWriter("output.x","UTF-8");
	printWriter.print(output);
	printWriter.close();
}
//#line 387 "ParserX.java"
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
{raiz = val_peek(0);}
break;
case 2:
//#line 27 "tentativaNovoParser.y"
{System.out.println("vars_decl /*comando_list*/ 						\n");}
break;
case 3:
//#line 32 "tentativaNovoParser.y"
{System.out.println("vars_decl_list 					\n");}
break;
case 4:
//#line 33 "tentativaNovoParser.y"
{System.out.println("{\n");}
break;
case 5:
//#line 37 "tentativaNovoParser.y"
{System.out.println("TK_NEW_VAR var_decl ';'								\n");}
break;
case 6:
//#line 38 "tentativaNovoParser.y"
{System.out.println("TK_NEW_VAR vars_decl_list var_decl	';'		\n");}
break;
case 7:
//#line 42 "tentativaNovoParser.y"
{System.out.println("id_list ':' type_specifier 					\n");}
break;
case 8:
//#line 46 "tentativaNovoParser.y"
{System.out.println("IDENTIFICADOR								\n");}
break;
case 9:
//#line 47 "tentativaNovoParser.y"
{System.out.println("id_list ',' IDENTIFICADOR					\n");}
break;
case 10:
//#line 51 "tentativaNovoParser.y"
{System.out.println("type 										\n");}
break;
case 11:
//#line 52 "tentativaNovoParser.y"
{System.out.println("type '[' INT ']'							\n");}
break;
case 12:
//#line 53 "tentativaNovoParser.y"
{System.out.println("type '[' expr ']'							\n");}
break;
case 13:
//#line 57 "tentativaNovoParser.y"
{System.out.println("INT										\n");}
break;
case 14:
//#line 58 "tentativaNovoParser.y"
{System.out.println("REAL										\n");}
break;
case 15:
//#line 59 "tentativaNovoParser.y"
{System.out.println("BOOL										\n");}
break;
case 16:
//#line 63 "tentativaNovoParser.y"
{System.out.println("IDENTIFICADOR  								\n");}
break;
case 17:
//#line 64 "tentativaNovoParser.y"
{System.out.println("IDENTIFICADOR	'[' INT ']'					\n");}
break;
case 18:
//#line 65 "tentativaNovoParser.y"
{System.out.println("IDENTIFICADOR	'[' expr ']'				\n");}
break;
case 19:
//#line 70 "tentativaNovoParser.y"
{ yyval = val_peek(1); System.out.println("comando 1");}
break;
case 20:
//#line 71 "tentativaNovoParser.y"
{ yyval = val_peek(2); buscarUltimocomando((ASTComando)yyval).setProximo((ASTComando)val_peek(1)); System.out.println("comadno 2");}
break;
case 21:
//#line 75 "tentativaNovoParser.y"
{ yyval = new ASTAtribuicao(((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 22:
//#line 76 "tentativaNovoParser.y"
{ yyval = new ASTPrint((ASTExpressao)val_peek(0)); }
break;
case 23:
//#line 77 "tentativaNovoParser.y"
{ yyval = new ASTPrint(((Token)val_peek(0)).getLexema()); }
break;
case 24:
//#line 78 "tentativaNovoParser.y"
{ yyval = new ASTRead(((Token)val_peek(0)).getLexema()); }
break;
case 25:
//#line 79 "tentativaNovoParser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 26:
//#line 80 "tentativaNovoParser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(5),(ASTComando)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 27:
//#line 81 "tentativaNovoParser.y"
{System.out.println("TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	\n");}
break;
case 28:
//#line 82 "tentativaNovoParser.y"
{System.out.println("TK_WHILE expr TK_DO comando_list TK_DONE					\n");}
break;
case 29:
//#line 86 "tentativaNovoParser.y"
{ yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 30:
//#line 87 "tentativaNovoParser.y"
{ yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 31:
//#line 88 "tentativaNovoParser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 32:
//#line 89 "tentativaNovoParser.y"
{ yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 33:
//#line 90 "tentativaNovoParser.y"
{ yyval = new ASTMenor((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 34:
//#line 91 "tentativaNovoParser.y"
{ yyval = new ASTMaior((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 35:
//#line 92 "tentativaNovoParser.y"
{System.out.println("expr '>=' expr													\n");}
break;
case 36:
//#line 93 "tentativaNovoParser.y"
{System.out.println("expr '<=' expr													\n");}
break;
case 37:
//#line 94 "tentativaNovoParser.y"
{ yyval = val_peek(1); }
break;
case 38:
//#line 95 "tentativaNovoParser.y"
{ yyval = new ASTAcessoVariavel(((Token)val_peek(0)).getLexema()); }
break;
case 39:
//#line 96 "tentativaNovoParser.y"
{}
break;
//#line 692 "ParserX.java"
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
