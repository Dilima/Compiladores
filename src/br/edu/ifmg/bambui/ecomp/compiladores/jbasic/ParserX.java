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
public final static short TK_ATRIBUICAO=267;
public final static short TK_NEW_VAR=268;
public final static short TK_FOR=269;
public final static short TK_FROM=270;
public final static short TK_INT=271;
public final static short TK_BOOL=272;
public final static short TK_REAL=273;
public final static short TK_END=274;
public final static short TK_BT=275;
public final static short TK_LT=276;
public final static short IDENTIFICADOR=277;
public final static short STRING=278;
public final static short REAL=279;
public final static short BOOL=280;
public final static short INT=281;
public final static short TYPE=282;
public final static short OR=283;
public final static short AND=284;
public final static short NOT=285;
public final static short MOD=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    4,    4,    5,    6,    6,    7,
    7,    8,    8,    8,   10,   10,    3,    3,   11,   11,
   11,   11,   11,   11,   11,   12,   12,   12,   12,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,
};
final static short yylen[] = {                            2,
    1,    2,    1,    0,    1,    2,    5,    1,    3,    1,
    4,    1,    1,    1,    1,    4,    1,    2,    4,    3,
    3,    5,    7,   10,    6,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    5,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   17,    6,    0,    0,
   39,   27,   40,   41,    0,    0,    0,    0,    0,    0,
    0,    0,   18,    0,    9,   12,   14,   13,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   20,   21,    0,    0,    0,    0,    0,    7,    0,   38,
    0,    0,    0,    0,    0,    0,   32,   33,   29,    0,
    0,    0,    0,   16,   19,    0,    0,   22,    0,    0,
   11,    0,   25,    0,   23,    0,    0,    0,   24,
};
final static short yydgoto[] = {                          2,
    3,    4,   15,    5,    6,    8,   39,   40,   26,   16,
   17,   27,
};
final static short yysindex[] = {                      -265,
 -270,    0,    0,   23, -265,    0,    0,  -31,  -40, -254,
  -32,  -32, -253,  -58,   23, -232,    0,    0, -236, -100,
    0,    0,    0,    0,  -32,   82,  -34,   -8,  -41,   27,
 -216,  -32,    0,  -32,    0,    0,    0,    0,   -3,  -26,
   21,  -32,  -32,  -32,  -32,  -32,  -32,  -32,  -32,  -35,
    0,    0,   23,   23, -214,   48,   54,    0,  -32,    0,
  -25,    3,  -25,  -25,    3,    3,    0,    0,    0,   82,
  -71,  -61, -188,    0,    0,   60,   23,    0,   14, -204,
    0, -101,    0, -187,    0,   23,  -47,   25,    0,
};
final static short yyrindex[] = {                        41,
    0,    0,    0,    0,   51,    0,    0,    0,    0,    0,
    0,    0,    0, -258,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   29,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,  -13,   71,   77,   -7,   -1,    0,    0,    0,  -30,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -38,    0,   93,    0,    0,    0,  135,   69,
   79,    0,
};
final static int YYTABLESIZE=358;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   48,   46,    1,   47,   25,   49,    7,   25,   15,   50,
   26,   15,   19,   28,   71,   72,   48,   46,   44,   47,
   45,   49,   28,   14,   51,   26,   20,   37,   28,   37,
   37,   37,   32,   30,   34,   30,   30,   30,   82,   31,
   35,   31,   31,   31,   48,   37,   37,   87,   37,   49,
   52,   30,   30,   55,   30,   58,   36,   31,   31,   36,
   31,   60,   48,   46,   59,   47,   73,   49,   48,   46,
   80,   47,   83,   49,   36,   36,   84,   36,   86,   37,
   44,   31,   45,   89,    2,   30,   44,   10,   45,   48,
   46,   31,   47,   33,   49,   48,   46,   18,   47,    0,
   49,   48,   46,    0,   47,    0,   49,   44,   36,   45,
    0,   34,   75,   44,   34,   45,    0,   35,    0,   44,
   35,   45,    0,   48,   46,    0,   47,    0,   49,   34,
   34,    0,   34,    0,    0,   35,   35,    0,   35,    0,
   74,   44,    0,   45,    0,   29,   30,    0,    0,   33,
   33,    0,   81,    0,    0,    9,   10,    0,   11,   41,
   33,   85,   12,   34,    0,   33,   56,   13,   57,   35,
   36,   37,   38,    0,    0,   14,   61,   62,   63,   64,
   65,   66,   67,   68,   70,    9,   10,    0,   11,    0,
   77,   78,   12,   76,    0,    9,   10,   13,   11,    0,
    0,    0,   12,   79,    0,   14,    0,   13,    0,    9,
   10,    0,   11,    0,    0,   14,   12,   88,    0,   53,
    0,   13,    0,    0,    0,    0,    0,    0,    0,   14,
    0,    0,    0,   42,   43,    0,   21,   22,   23,    0,
   24,   21,   69,   23,   21,   24,   23,   37,   24,    0,
   43,    0,   37,   30,    0,    0,    0,    0,   30,   31,
    0,   37,   37,    0,   31,    0,    0,   30,   30,    0,
    0,    0,    0,   31,   31,    0,   36,    0,    0,    9,
   10,   36,   11,    0,    0,    0,   12,    0,    0,    0,
   36,   13,   54,    0,    0,   42,   43,    4,    4,   14,
    4,   42,   43,    0,    4,    0,    0,    3,    3,    4,
    3,    0,    0,    0,    3,    0,    0,    4,    0,    3,
    0,    0,   42,   43,    0,    0,    0,    3,   42,   43,
    0,   34,    0,    0,   42,   43,   34,   35,    0,    0,
    0,    0,   35,    0,    0,   34,    0,    0,    0,    0,
    0,   35,    0,    0,    0,    0,   42,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   42,   43,  268,   45,   40,   47,  277,   40,  267,   44,
   44,  270,   44,   44,   53,   54,   42,   43,   60,   45,
   62,   47,  277,  277,   59,   59,   58,   41,   59,   43,
   44,   45,   91,   41,  267,   43,   44,   45,   77,   41,
  277,   43,   44,   45,   42,   59,   60,   86,   62,   47,
   59,   59,   60,  270,   62,   59,   41,   59,   60,   44,
   62,   41,   42,   43,   91,   45,  281,   47,   42,   43,
  259,   45,   59,   47,   59,   60,  281,   62,  266,   93,
   60,   13,   62,   59,    0,   93,   60,   59,   62,   42,
   43,   93,   45,   15,   47,   42,   43,    5,   45,   -1,
   47,   42,   43,   -1,   45,   -1,   47,   60,   93,   62,
   -1,   41,   59,   60,   44,   62,   -1,   41,   -1,   60,
   44,   62,   -1,   42,   43,   -1,   45,   -1,   47,   59,
   60,   -1,   62,   -1,   -1,   59,   60,   -1,   62,   -1,
   93,   60,   -1,   62,   -1,   11,   12,   -1,   -1,   71,
   72,   -1,   93,   -1,   -1,  257,  258,   -1,  260,   25,
   82,  263,  264,   93,   -1,   87,   32,  269,   34,   93,
  271,  272,  273,   -1,   -1,  277,   42,   43,   44,   45,
   46,   47,   48,   49,   50,  257,  258,   -1,  260,   -1,
  262,  263,  264,   59,   -1,  257,  258,  269,  260,   -1,
   -1,   -1,  264,  265,   -1,  277,   -1,  269,   -1,  257,
  258,   -1,  260,   -1,   -1,  277,  264,  265,   -1,  261,
   -1,  269,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
   -1,   -1,   -1,  275,  276,   -1,  277,  278,  279,   -1,
  281,  277,  278,  279,  277,  281,  279,  261,  281,   -1,
  276,   -1,  266,  261,   -1,   -1,   -1,   -1,  266,  261,
   -1,  275,  276,   -1,  266,   -1,   -1,  275,  276,   -1,
   -1,   -1,   -1,  275,  276,   -1,  261,   -1,   -1,  257,
  258,  266,  260,   -1,   -1,   -1,  264,   -1,   -1,   -1,
  275,  269,  266,   -1,   -1,  275,  276,  257,  258,  277,
  260,  275,  276,   -1,  264,   -1,   -1,  257,  258,  269,
  260,   -1,   -1,   -1,  264,   -1,   -1,  277,   -1,  269,
   -1,   -1,  275,  276,   -1,   -1,   -1,  277,  275,  276,
   -1,  261,   -1,   -1,  275,  276,  266,  261,   -1,   -1,
   -1,   -1,  266,   -1,   -1,  275,   -1,   -1,   -1,   -1,
   -1,  275,   -1,   -1,   -1,   -1,  275,  276,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=286;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'",null,"'>'",null,null,null,null,null,null,null,null,null,null,null,null,
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
"TK_THEN","TK_ELSE","TK_END_IF","TK_WHILE","TK_DONE","TK_DO","TK_ATRIBUICAO",
"TK_NEW_VAR","TK_FOR","TK_FROM","TK_INT","TK_BOOL","TK_REAL","TK_END","TK_BT",
"TK_LT","IDENTIFICADOR","STRING","REAL","BOOL","INT","TYPE","OR","AND","NOT",
"MOD",
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
"comando : var TK_ATRIBUICAO expr ';'",
"comando : TK_PRINT texto ';'",
"comando : TK_READ IDENTIFICADOR ';'",
"comando : TK_IF expr TK_THEN comando_list TK_END_IF",
"comando : TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF",
"comando : TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE ';'",
"comando : TK_WHILE expr TK_DO comando_list TK_DONE ';'",
"texto : expr",
"texto : STRING",
"texto : texto ',' expr",
"texto : texto ',' STRING",
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

//#line 105 "tentativaNovoParser.y"
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
//#line 399 "ParserX.java"
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
{System.out.println("|   TK_PRINT texto ");}
break;
case 21:
//#line 75 "tentativaNovoParser.y"
{System.out.println("|   TK_READ IDENTIFICADOR ");}
break;
case 22:
//#line 76 "tentativaNovoParser.y"
{System.out.println("|   TK_IF expr TK_THEN comando_list TK_END_IF ");}
break;
case 23:
//#line 77 "tentativaNovoParser.y"
{System.out.println("|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF ");}
break;
case 24:
//#line 78 "tentativaNovoParser.y"
{System.out.println("|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	");}
break;
case 25:
//#line 79 "tentativaNovoParser.y"
{System.out.println("|	TK_WHILE expr TK_DO comando_list TK_DONE					");}
break;
case 26:
//#line 83 "tentativaNovoParser.y"
{System.out.println(":	expr ");}
break;
case 27:
//#line 84 "tentativaNovoParser.y"
{System.out.println("|	STRING ");}
break;
case 28:
//#line 85 "tentativaNovoParser.y"
{System.out.println("| 	texto ',' expr ");}
break;
case 29:
//#line 86 "tentativaNovoParser.y"
{System.out.println("| 	texto ',' STRING ");}
break;
case 30:
//#line 91 "tentativaNovoParser.y"
{System.out.println(":   expr '+' expr ");}
break;
case 31:
//#line 92 "tentativaNovoParser.y"
{System.out.println("|   expr '-' expr ");}
break;
case 32:
//#line 93 "tentativaNovoParser.y"
{System.out.println("|   expr '*' expr ");}
break;
case 33:
//#line 94 "tentativaNovoParser.y"
{System.out.println("|   expr '/' expr ");}
break;
case 34:
//#line 95 "tentativaNovoParser.y"
{System.out.println("|   expr '<' expr ");}
break;
case 35:
//#line 96 "tentativaNovoParser.y"
{System.out.println("|   expr '>' expr ");}
break;
case 36:
//#line 97 "tentativaNovoParser.y"
{System.out.println("|	expr TK_BT expr													");}
break;
case 37:
//#line 98 "tentativaNovoParser.y"
{System.out.println("|	expr TK_LT expr													");}
break;
case 38:
//#line 99 "tentativaNovoParser.y"
{System.out.println("|	'(' expr ')' ");}
break;
case 39:
//#line 100 "tentativaNovoParser.y"
{System.out.println("|	IDENTIFICADOR ");}
break;
case 40:
//#line 101 "tentativaNovoParser.y"
{System.out.println("|	REAL ");}
break;
case 41:
//#line 102 "tentativaNovoParser.y"
{System.out.println("|	INT ");}
break;
//#line 712 "ParserX.java"
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
