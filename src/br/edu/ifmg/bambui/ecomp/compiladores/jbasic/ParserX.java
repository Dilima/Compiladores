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



//#line 14 "./Compiladores/novoParser.y"
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
public final static short TK_IF=259;
public final static short TK_THEN=260;
public final static short TK_ELSE=261;
public final static short TK_END_IF=262;
public final static short TK_WHILE=263;
public final static short TK_DONE=264;
public final static short TK_DO=265;
public final static short TK_NEW_VAR=266;
public final static short TK_FOR=267;
public final static short TK_FROM=268;
public final static short TK_TO=269;
public final static short TK_INT=270;
public final static short TK_END=271;
public final static short IDENTIFICADOR=272;
public final static short STRING=273;
public final static short NUMERO=274;
public final static short TYPE=275;
public final static short OR=276;
public final static short AND=277;
public final static short NOT=278;
public final static short MOD=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    3,    3,    2,    2,    5,    5,    6,    7,
    7,    8,    4,    4,    4,    4,    4,    4,    9,    9,
    9,    9,    9,    9,    9,    9,    9,
};
final static short yylen[] = {                            2,
    1,    2,    2,    3,    2,    0,    1,    2,    4,    1,
    3,    1,    3,    2,    2,    2,    5,    7,    3,    3,
    3,    3,    3,    3,    3,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,   12,    0,    7,    0,   10,    0,
    0,    0,    0,    0,    0,    8,    0,    0,   26,   15,
   27,    0,    0,   16,    0,    0,    0,    3,   11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    4,
    9,   25,    0,    0,    0,    0,   21,   22,    0,    0,
   17,    0,   18,
};
final static short yydgoto[] = {                          2,
    3,    4,   14,   15,    6,    7,    8,    9,   23,
};
final static short yysindex[] = {                      -265,
 -267,    0,    0, -243,    0, -267,    0,  -26,    0,  -40,
 -259,  -37,  -27, -243,  -14,    0, -267, -222,    0,    0,
    0,  -37,   19,    0,   -3,  -37,    4,    0,    0,    6,
   13,  -37,  -37,  -37,  -37,  -37,  -37, -243,   19,    0,
    0,    0,   50,   50,  -23,  -23,    0,    0, -189, -243,
    0, -181,    0,
};
final static short yyrindex[] = {                      -173,
    0,    0,    0,    0,    0, -170,    0,    0,    0,    0,
    0,    0,    0,   67,    0,    0,    0,    0,    0,    0,
    0,    0,   12,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   21,    0,
    0,    0,  -29,  -24,  -39,  -34,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -28,   -6,    0,   68,    0,   77,   15,
};
final static int YYTABLESIZE=257;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         22,
    1,   19,   22,   19,    5,   19,   20,   27,   20,   49,
   20,   23,   24,   10,   11,   12,   24,   17,   36,   19,
   19,   52,   19,   37,   20,   20,   25,   20,   13,   23,
   23,   18,   23,   26,   24,   24,   31,   24,   36,   34,
   39,   35,   27,   37,   28,   27,   43,   44,   45,   46,
   47,   48,   30,   42,   36,   34,   32,   35,   33,   37,
   36,   34,   40,   35,   41,   37,    2,   10,   11,   12,
   14,   50,   32,   16,   33,   10,   11,   12,   32,   13,
   33,   51,   13,    6,    6,    6,    5,    5,    5,   53,
   13,   36,   34,   29,   35,    0,   37,    0,    6,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   19,    0,    0,    0,    0,   20,    0,    0,    0,    0,
   23,   19,   20,   21,   19,   24,   21,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   38,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
  266,   41,   40,   43,  272,   45,   41,   14,   43,   38,
   45,   41,  272,  257,  258,  259,   41,   44,   42,   59,
   60,   50,   62,   47,   59,   60,   12,   62,  272,   59,
   60,   58,   62,   61,   59,   60,   22,   62,   42,   43,
   26,   45,   49,   47,   59,   52,   32,   33,   34,   35,
   36,   37,  275,   41,   42,   43,   60,   45,   62,   47,
   42,   43,   59,   45,   59,   47,    0,  257,  258,  259,
   59,  261,   60,    6,   62,  257,  258,  259,   60,   59,
   62,  271,  272,  257,  258,  259,  257,  258,  259,  271,
  272,   42,   43,   17,   45,   -1,   47,   -1,  272,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,   -1,   -1,   -1,   -1,  260,   -1,   -1,   -1,   -1,
  260,  272,  273,  274,  272,  260,  274,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=281;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"TK_PRINT","TK_READ","TK_IF","TK_THEN","TK_ELSE",
"TK_END_IF","TK_WHILE","TK_DONE","TK_DO","TK_NEW_VAR","TK_FOR","TK_FROM",
"TK_TO","TK_INT","TK_END","IDENTIFICADOR","STRING","NUMERO","TYPE","OR","AND",
"NOT","MOD","\"<=\"","\">=\"",
};
final static String yyrule[] = {
"$accept : main",
"main : root",
"root : declaracoes lista_comandos",
"lista_comandos : comando ';'",
"lista_comandos : lista_comandos comando ';'",
"declaracoes : TK_NEW_VAR listadeclaracaovariavel",
"declaracoes :",
"listadeclaracaovariavel : declaracaovariavel",
"listadeclaracaovariavel : listadeclaracaovariavel declaracaovariavel",
"declaracaovariavel : id ':' TYPE ';'",
"id : id_final",
"id : id ',' id_final",
"id_final : IDENTIFICADOR",
"comando : IDENTIFICADOR '=' expr",
"comando : TK_PRINT expr",
"comando : TK_PRINT STRING",
"comando : TK_READ IDENTIFICADOR",
"comando : TK_IF expr TK_THEN lista_comandos TK_END",
"comando : TK_IF expr TK_THEN lista_comandos TK_ELSE lista_comandos TK_END",
"expr : expr '+' expr",
"expr : expr '-' expr",
"expr : expr '*' expr",
"expr : expr '/' expr",
"expr : expr '<' expr",
"expr : expr '>' expr",
"expr : '(' expr ')'",
"expr : IDENTIFICADOR",
"expr : NUMERO",
};

//#line 80 "./Compiladores/novoParser.y"
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
private ASTComando buscarUltimoComando(ASTComando cmd) {
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
	printWriter = new PrintWriter("C:/Users/Projeto/Desktop/output.x","UTF-8");
	printWriter.print(output);
	printWriter.close();
}
//#line 342 "ParserX.java"
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
//#line 25 "./Compiladores/novoParser.y"
{ raiz = val_peek(0);  System.out.println("a1");}
break;
case 2:
//#line 27 "./Compiladores/novoParser.y"
{yyval = val_peek(0); System.out.println("a2");}
break;
case 3:
//#line 30 "./Compiladores/novoParser.y"
{ yyval = val_peek(1); System.out.println("comando 1");}
break;
case 4:
//#line 31 "./Compiladores/novoParser.y"
{ yyval = val_peek(2); buscarUltimoComando((ASTComando)yyval).setProximo((ASTComando)val_peek(1)); System.out.println("comadno 2");}
break;
case 5:
//#line 35 "./Compiladores/novoParser.y"
{yyval = val_peek(0);System.out.println("a5");}
break;
case 6:
//#line 36 "./Compiladores/novoParser.y"
{yyval = null;System.out.println("a6");}
break;
case 7:
//#line 40 "./Compiladores/novoParser.y"
{ yyval = val_peek(0); System.out.println("a7");}
break;
case 8:
//#line 41 "./Compiladores/novoParser.y"
{ yyval = val_peek(1); buscarUltimaDeclaracao((ASTListaDeclaracoes)yyval).setProximo((ASTListaDeclaracoes)val_peek(0)); System.out.println("a8");}
break;
case 9:
//#line 46 "./Compiladores/novoParser.y"
{yyval = new ASTDeclaracao((ASTIdentificador)val_peek(3), ((Token)val_peek(1)).getLexema()); System.out.println("a9");}
break;
case 10:
//#line 50 "./Compiladores/novoParser.y"
{ yyval = val_peek(0); System.out.println("a11");}
break;
case 11:
//#line 51 "./Compiladores/novoParser.y"
{ yyval = val_peek(2); buscarUltimoIds((ASTIdentificador)yyval).setProximo((ASTIdentificador)val_peek(0)); System.out.println("a12");}
break;
case 12:
//#line 55 "./Compiladores/novoParser.y"
{ yyval = new ASTIdentificador(((Token)val_peek(0)).getLexema()); System.out.println("a13"); }
break;
case 13:
//#line 59 "./Compiladores/novoParser.y"
{ yyval = new ASTAtribuicao(((Token)val_peek(2)).getLexema(),(ASTExpressao)val_peek(0)); }
break;
case 14:
//#line 60 "./Compiladores/novoParser.y"
{ yyval = new ASTPrint((ASTExpressao)val_peek(0)); }
break;
case 15:
//#line 61 "./Compiladores/novoParser.y"
{ yyval = new ASTPrint(((Token)val_peek(0)).getLexema()); }
break;
case 16:
//#line 62 "./Compiladores/novoParser.y"
{ yyval = new ASTRead(((Token)val_peek(0)).getLexema()); }
break;
case 17:
//#line 63 "./Compiladores/novoParser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 18:
//#line 64 "./Compiladores/novoParser.y"
{ yyval = new ASTIf((ASTExpressao)val_peek(5),(ASTComando)val_peek(3),(ASTComando)val_peek(1)); }
break;
case 19:
//#line 68 "./Compiladores/novoParser.y"
{ yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 20:
//#line 69 "./Compiladores/novoParser.y"
{ yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 21:
//#line 70 "./Compiladores/novoParser.y"
{ yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 22:
//#line 71 "./Compiladores/novoParser.y"
{ yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 23:
//#line 72 "./Compiladores/novoParser.y"
{ yyval = new ASTMenor((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 24:
//#line 73 "./Compiladores/novoParser.y"
{ yyval = new ASTMaior((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); }
break;
case 25:
//#line 74 "./Compiladores/novoParser.y"
{ yyval = val_peek(1); }
break;
case 26:
//#line 75 "./Compiladores/novoParser.y"
{ yyval = new ASTAcessoVariavel(((Token)val_peek(0)).getLexema()); }
break;
case 27:
//#line 76 "./Compiladores/novoParser.y"
{ yyval = new ASTNumero(new Double(((Token)val_peek(0)).getLexema())); }
break;
//#line 599 "ParserX.java"
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
