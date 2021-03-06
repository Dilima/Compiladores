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



package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX;



//#line 17 "tentativaNovoParser.y"
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr.*;
//#line 23 "ParserX.java"




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
public final static short FUNCTION_RANDOM=268;
public final static short TK_NEW_VAR=269;
public final static short TK_FOR=270;
public final static short TK_FROM=271;
public final static short TK_INT=272;
public final static short TK_BOOL=273;
public final static short TK_REAL=274;
public final static short TK_END=275;
public final static short TK_BT=276;
public final static short TK_LT=277;
public final static short IDENTIFICADOR=278;
public final static short STRING=279;
public final static short REAL=280;
public final static short BOOL=281;
public final static short INT=282;
public final static short TYPE=283;
public final static short OR=284;
public final static short AND=285;
public final static short NOT=286;
public final static short MOD=287;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    4,    4,    5,    6,    6,
    7,    7,    8,    8,    8,    9,    9,    3,    3,   11,
   11,   11,   11,   11,   11,   11,   12,   12,   12,   12,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    0,    1,    2,    5,    1,    3,
    1,    4,    1,    1,    1,    1,    4,    2,    3,    3,
    2,    2,    5,    7,    9,    5,    1,    1,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    1,    2,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    6,    9,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    7,    0,    0,
   46,   28,   44,   45,    0,    0,   42,    0,    0,   22,
    0,    0,    0,    0,    0,    0,   18,   10,   13,   15,
   14,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   19,    0,    8,    0,   41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   37,   30,    0,    0,    0,    0,
   17,    0,    0,   23,   26,    0,   12,    0,    0,   24,
    0,    0,   25,
};
final static short yydgoto[] = {                          2,
    3,    4,   15,    5,    6,    8,   42,   43,   27,   28,
   17,   29,
};
final static short yysindex[] = {                      -265,
 -270,    0,    0,  -59, -265,    0,    0,  -34,  -40, -260,
    5,    5, -260,  -62,  -59, -236,  -24,    0, -241, -221,
    0,    0,    0,    0,    5,    5,    0,  416,   -2,    0,
   20,  170, -224,    5,   -1,    5,    0,    0,    0,    0,
    0,    7,  -16,  416,  -28,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,  -35,  -59,  -59,    5,  371,
    0,  416,    0, -222,    0,   12,  -31,  -22,   12,   12,
  -31,  -31,   15,   15,    0,    0,  416, -214,   63,  381,
    0,  -15,  -59,    0,    0,    5,    0,  260,  406,    0,
  -59,  223,    0,
};
final static short yyrindex[] = {                         1,
    0,    0,    0,   77,    6,    0,    0,    0,    0,    0,
    0,    0,    0,   27,   79,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -37,   24,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   26,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   36,    0,    0,    0,  121,   84,  -32,  132,  144,
   96,  109,   49,   71,    0,    0,  -18,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -55,    0,   92,    0,    0,    0,  469,  460,
   69,    0,
};
final static int YYTABLESIZE=701;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         26,
    5,   78,   79,    1,   26,    4,   27,    7,   38,   19,
   53,   38,   65,   53,   51,   54,   52,   14,   54,   53,
   51,   27,   52,   20,   54,   29,   38,   88,   34,   55,
   36,   49,   55,   50,   37,   92,   38,   49,   55,   50,
   29,   56,    9,   10,   26,   11,   59,   83,   84,   12,
   39,   40,   41,   53,   51,   13,   52,   61,   54,   82,
   38,   53,   51,   14,   52,   63,   54,   16,   16,   16,
   16,   16,   55,   16,   64,   55,    3,   87,    2,   49,
   55,   50,   21,   35,   11,   16,   16,   16,   16,   33,
   33,   33,   33,   33,   20,   33,   18,    0,   43,    0,
    0,   43,    0,    0,    0,    0,    0,   33,   33,    0,
   33,   34,   34,   34,   34,   34,   43,   34,    0,   16,
    0,    0,    0,    0,   39,    0,   39,   39,   39,   34,
   34,    0,   34,    0,    0,    0,   31,    0,   31,   31,
   31,   33,   39,   39,    0,   39,   35,   35,    0,   32,
   43,   32,   32,   32,   31,   31,   35,   31,    0,    0,
   35,   40,    0,   34,   40,    0,    0,   32,   32,    0,
   32,    0,   35,    0,    0,   35,   39,    0,    0,   40,
   40,    0,   40,    0,   36,    0,    0,   36,   31,    0,
   35,   35,    0,   35,    0,    0,    0,    9,   10,    0,
   11,   32,   36,   36,   12,   36,    0,    0,    0,    0,
   13,   53,   51,   40,   52,    0,   54,    0,   14,    0,
    0,    0,    0,    0,   35,    0,   38,   21,   38,   49,
   55,   50,   21,   38,    0,    0,   36,   14,   22,   23,
    0,   24,   14,   76,   23,   25,   24,   46,   47,    0,
   25,    0,   38,   46,   47,    0,   48,    5,    5,    0,
    5,    0,    4,    4,    5,    4,    0,    0,    0,    4,
    5,    0,   21,    0,    0,    4,    0,    0,    5,    0,
   57,    0,   14,    4,   23,   16,   24,   16,   47,    0,
   25,    0,   16,   16,    0,   46,   47,   16,    0,    0,
    0,    0,   16,   16,   48,    0,    0,   33,    0,   33,
    0,   16,    0,    0,   33,    0,   43,    0,   43,    9,
   10,    0,   11,   43,   33,   33,   12,   85,    0,   34,
    0,   34,   13,   33,    0,    0,   34,    0,    0,    0,
   14,    0,   39,    0,   39,    0,   34,   34,    0,   39,
    0,    0,    0,    0,   31,   34,   31,    0,    0,   39,
   39,   31,    0,    0,    0,    0,    0,   32,   39,   32,
    0,   31,   31,    0,   32,    0,    0,    0,    0,   40,
   31,   40,    0,    0,   32,   32,   40,    0,    0,    0,
   35,    0,   35,   32,    0,    0,   40,   35,    0,    0,
    0,    0,   36,    0,   36,   40,    0,   35,    0,   36,
    0,    0,   53,   51,    0,   52,   35,   54,    0,   36,
    0,    0,   53,   51,    0,   52,    0,   54,   36,    0,
   49,   55,   50,    0,    0,   58,    0,    0,    0,    0,
   49,   55,   50,    0,    0,   46,   47,   53,   51,    0,
   52,    0,   54,    0,   48,    0,    0,   53,   51,    0,
   52,    0,   54,   81,    0,   49,   55,   50,    0,    0,
   31,   32,   16,    0,    0,   49,   55,   50,   30,    9,
   10,   33,   11,   16,   44,   45,   12,   93,    0,    0,
    0,    0,   13,   60,    0,   62,    0,    0,    0,    0,
   14,    0,    0,    0,    0,   66,   67,   68,   69,   70,
   71,   72,   73,   74,   75,   77,    9,   10,   80,   11,
    0,    0,   90,   12,    0,   16,   16,    0,    0,   13,
    0,    0,    0,    0,    0,    0,    0,   14,    0,    0,
    0,    0,    0,    0,    0,   89,   16,   16,    0,    0,
    0,   16,    0,    0,    0,    0,   16,    0,    0,   16,
   16,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   86,
    0,    0,    0,    0,    0,    0,   46,   47,    0,    0,
    0,    0,    0,    0,    0,   48,   46,   47,    0,    0,
    0,    0,    0,    0,    0,   48,    0,    0,    0,    0,
    0,   91,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   46,   47,    0,    0,    0,    0,    0,    0,    0,
   48,   46,   47,    0,    0,    0,    0,    0,    0,    0,
   48,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   57,   58,  269,   40,    0,   44,  278,   41,   44,
   42,   44,   41,   42,   43,   47,   45,  278,   47,   42,
   43,   59,   45,   58,   47,   44,   59,   83,   91,   61,
  267,   60,   61,   62,   59,   91,  278,   60,   61,   62,
   59,   44,  257,  258,   40,  260,  271,  262,  263,  264,
  272,  273,  274,   42,   43,  270,   45,   59,   47,  282,
   93,   42,   43,  278,   45,   59,   47,   41,   42,   43,
   44,   45,   61,   47,   91,   61,    0,   93,    0,   60,
   61,   62,   59,   15,   59,   59,   60,   61,   62,   41,
   42,   43,   44,   45,   59,   47,    5,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,
   62,   41,   42,   43,   44,   45,   59,   47,   -1,   93,
   -1,   -1,   -1,   -1,   41,   -1,   43,   44,   45,   59,
   60,   -1,   62,   -1,   -1,   -1,   41,   -1,   43,   44,
   45,   93,   59,   60,   -1,   62,   78,   79,   -1,   41,
   93,   43,   44,   45,   59,   60,   88,   62,   -1,   -1,
   92,   41,   -1,   93,   44,   -1,   -1,   59,   60,   -1,
   62,   -1,   41,   -1,   -1,   44,   93,   -1,   -1,   59,
   60,   -1,   62,   -1,   41,   -1,   -1,   44,   93,   -1,
   59,   60,   -1,   62,   -1,   -1,   -1,  257,  258,   -1,
  260,   93,   59,   60,  264,   62,   -1,   -1,   -1,   -1,
  270,   42,   43,   93,   45,   -1,   47,   -1,  278,   -1,
   -1,   -1,   -1,   -1,   93,   -1,  259,  268,  261,   60,
   61,   62,  268,  266,   -1,   -1,   93,  278,  279,  280,
   -1,  282,  278,  279,  280,  286,  282,  276,  277,   -1,
  286,   -1,  285,  276,  277,   -1,  285,  257,  258,   -1,
  260,   -1,  257,  258,  264,  260,   -1,   -1,   -1,  264,
  270,   -1,  268,   -1,   -1,  270,   -1,   -1,  278,   -1,
  261,   -1,  278,  278,  280,  259,  282,  261,  277,   -1,
  286,   -1,  266,  267,   -1,  276,  277,  271,   -1,   -1,
   -1,   -1,  276,  277,  285,   -1,   -1,  259,   -1,  261,
   -1,  285,   -1,   -1,  266,   -1,  259,   -1,  261,  257,
  258,   -1,  260,  266,  276,  277,  264,  265,   -1,  259,
   -1,  261,  270,  285,   -1,   -1,  266,   -1,   -1,   -1,
  278,   -1,  259,   -1,  261,   -1,  276,  277,   -1,  266,
   -1,   -1,   -1,   -1,  259,  285,  261,   -1,   -1,  276,
  277,  266,   -1,   -1,   -1,   -1,   -1,  259,  285,  261,
   -1,  276,  277,   -1,  266,   -1,   -1,   -1,   -1,  259,
  285,  261,   -1,   -1,  276,  277,  266,   -1,   -1,   -1,
  259,   -1,  261,  285,   -1,   -1,  276,  266,   -1,   -1,
   -1,   -1,  259,   -1,  261,  285,   -1,  276,   -1,  266,
   -1,   -1,   42,   43,   -1,   45,  285,   47,   -1,  276,
   -1,   -1,   42,   43,   -1,   45,   -1,   47,  285,   -1,
   60,   61,   62,   -1,   -1,  266,   -1,   -1,   -1,   -1,
   60,   61,   62,   -1,   -1,  276,  277,   42,   43,   -1,
   45,   -1,   47,   -1,  285,   -1,   -1,   42,   43,   -1,
   45,   -1,   47,   93,   -1,   60,   61,   62,   -1,   -1,
   11,   12,    4,   -1,   -1,   60,   61,   62,   10,  257,
  258,   13,  260,   15,   25,   26,  264,  265,   -1,   -1,
   -1,   -1,  270,   34,   -1,   36,   -1,   -1,   -1,   -1,
  278,   -1,   -1,   -1,   -1,   46,   47,   48,   49,   50,
   51,   52,   53,   54,   55,   56,  257,  258,   59,  260,
   -1,   -1,  263,  264,   -1,   57,   58,   -1,   -1,  270,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   86,   78,   79,   -1,   -1,
   -1,   83,   -1,   -1,   -1,   -1,   88,   -1,   -1,   91,
   92,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,
   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  285,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  285,   -1,   -1,   -1,   -1,
   -1,  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  285,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  285,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=287;
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
"TK_THEN","TK_ELSE","TK_END_IF","TK_WHILE","TK_DONE","TK_DO","TK_ATRIBUICAO",
"FUNCTION_RANDOM","TK_NEW_VAR","TK_FOR","TK_FROM","TK_INT","TK_BOOL","TK_REAL",
"TK_END","TK_BT","TK_LT","IDENTIFICADOR","STRING","REAL","BOOL","INT","TYPE",
"OR","AND","NOT","MOD",
};
final static String yyrule[] = {
"$accept : main",
"main : program",
"program : vars_decl comando_list",
"program : vars_decl",
"vars_decl : vars_decl_list",
"vars_decl :",
"vars_decl_list : var_decl",
"vars_decl_list : vars_decl_list var_decl",
"var_decl : TK_NEW_VAR id_list ':' type_specifier ';'",
"id_list : IDENTIFICADOR",
"id_list : id_list ',' IDENTIFICADOR",
"type_specifier : type",
"type_specifier : type '[' INT ']'",
"type : TK_INT",
"type : TK_REAL",
"type : TK_BOOL",
"var : IDENTIFICADOR",
"var : IDENTIFICADOR '[' expr ']'",
"comando_list : comando ';'",
"comando_list : comando_list comando ';'",
"comando : var TK_ATRIBUICAO expr",
"comando : TK_PRINT texto",
"comando : TK_READ var",
"comando : TK_IF expr TK_THEN comando_list TK_END_IF",
"comando : TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF",
"comando : TK_FOR var TK_FROM expr TK_TO expr TK_DO comando_list TK_DONE",
"comando : TK_WHILE expr TK_DO comando_list TK_DONE",
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
"expr : expr '=' expr",
"expr : expr AND expr",
"expr : expr TK_LT expr",
"expr : expr TK_BT expr",
"expr : '(' expr ')'",
"expr : var",
"expr : NOT expr",
"expr : REAL",
"expr : INT",
"expr : FUNCTION_RANDOM",
};

//#line 113 "tentativaNovoParser.y"
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
private ASTDeclaracao buscaUltimaDecl(ASTDeclaracao cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}
	return cmd;
}
private ASTListaID buscaUltimoID(ASTListaID cmd) {
	while(cmd.getProximo() != null) {
		cmd = cmd.getProximo();
	}
	return cmd;
}
private ASTTexto buscaUltimoTexto(ASTTexto cmd) {
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
    public void compilarMIPS() throws Exception {
        List<LinkedList<String>> vars = new ArrayList<LinkedList<String>>();
        for (int j = 0; j < 5; j++) {
            vars.add(new LinkedList<String>());
        }
        PrintWriter printWriter;
        String outputMIPS = "";
        String saidaMIPS;
        yyparse();
        vars = raiz.compilarMIPS(vars);
        if (vars.get(0).size() > 0) {
            outputMIPS = ".data\n";
            int i = 0;
            while (!vars.get(0).isEmpty()) {
                outputMIPS += "	msg" + i + ":	acsiiz	\"" + vars.get(0).remove() + "\"\n";
                i++;
            }
            outputMIPS += ".text\n";
        }
        outputMIPS += "main:\n";
        outputMIPS += "lui $s7, 0x1000\n";

        while(!vars.get(4).isEmpty())
            outputMIPS+=vars.get(4).remove();
        outputMIPS += "addi $v0 , $zero ,10\n"
                + "syscall\n";
        printWriter = new PrintWriter("output.mips", "UTF-8");
        printWriter.print(outputMIPS);
        printWriter.close();

    }
public void compilarC() throws Exception {
	HashSet<String> tabelaSimbolo = new HashSet<String>();
	PrintWriter printWriter;
    String outputC="";
	String saidaC;
	yyparse();
	saidaC = raiz.compilarC(tabelaSimbolo);
	
	outputC+= "#include<stdio.h>\n";
	outputC+= "#include<stdlib.h>\n";
	outputC+= "#include<stdbool.h>\n";
	outputC+="\nint main(void){\n";

	outputC += saidaC+"\n";
	outputC += "\n}";
	printWriter = new PrintWriter("output.c","UTF-8");
	printWriter.print(outputC);
	printWriter.close();
}
//#line 505 "ParserX.java"
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
//#line 26 "tentativaNovoParser.y"
{raiz = val_peek(0); System.out.println(": program	");}
break;
case 2:
//#line 30 "tentativaNovoParser.y"
{yyval = new ASTProgram((ASTDeclaracao)val_peek(1),(ASTComando)val_peek(0)); System.out.println(":	vars_decl comando_list 						");}
break;
case 3:
//#line 31 "tentativaNovoParser.y"
{yyval = new ASTProgram((ASTDeclaracao)val_peek(0)); System.out.println(":	vars_decl				");}
break;
case 4:
//#line 36 "tentativaNovoParser.y"
{System.out.println(":	vars_decl_list 								");}
break;
case 5:
//#line 37 "tentativaNovoParser.y"
{yyval = null; System.out.println("|												");}
break;
case 6:
//#line 41 "tentativaNovoParser.y"
{yyval = val_peek(0); System.out.println(":	var_decl 									");}
break;
case 7:
//#line 42 "tentativaNovoParser.y"
{yyval = val_peek(1); buscaUltimaDecl((ASTDeclaracao)yyval).setProximo((ASTDeclaracao)val_peek(0));System.out.println("|	vars_decl_list var_decl 					");}
break;
case 8:
//#line 46 "tentativaNovoParser.y"
{yyval = new ASTDeclaracao((ASTListaID)val_peek(3),(ASTTipoEspecifico)val_peek(1)); System.out.println(":	TK_NEW_VAR id_list ':' type_specifier 	';' 				");}
break;
case 9:
//#line 50 "tentativaNovoParser.y"
{yyval = new ASTListaID(((Token)val_peek(0)).getLexema()); System.out.println(":	IDENTIFICADOR								");}
break;
case 10:
//#line 51 "tentativaNovoParser.y"
{yyval  = val_peek(2); buscaUltimoID((ASTListaID)yyval).setProximo(new ASTListaID(((Token)val_peek(0)).getLexema())); System.out.println("|	id_list ',' IDENTIFICADOR					");}
break;
case 11:
//#line 55 "tentativaNovoParser.y"
{yyval = new ASTTipoEspecifico((ASTTipo)val_peek(0)); System.out.println(":	type 										");}
break;
case 12:
//#line 56 "tentativaNovoParser.y"
{yyval = new ASTTipoEspecifico((ASTTipo)val_peek(3),((Token)val_peek(1)).getLexema()); System.out.println("|	type '[' expr ']'							");}
break;
case 13:
//#line 60 "tentativaNovoParser.y"
{yyval = new ASTTipo("int"); System.out.println(":	TK_INT 										");}
break;
case 14:
//#line 61 "tentativaNovoParser.y"
{yyval = new ASTTipo("double"); System.out.println("|	TK_REAL										");}
break;
case 15:
//#line 62 "tentativaNovoParser.y"
{yyval = new ASTTipo("bool"); System.out.println("|	TK_BOOL										");}
break;
case 16:
//#line 66 "tentativaNovoParser.y"
{yyval = new ASTVar(((Token)val_peek(0)).getLexema()); System.out.println(":	IDENTIFICADOR  								");}
break;
case 17:
//#line 67 "tentativaNovoParser.y"
{yyval = new ASTVar(((Token)val_peek(3)).getLexema(),(ASTExpressao)val_peek(1)); System.out.println("|	IDENTIFICADOR	'[' expr ']'				");}
break;
case 18:
//#line 72 "tentativaNovoParser.y"
{yyval = val_peek(1); System.out.println(":	comando ");}
break;
case 19:
//#line 73 "tentativaNovoParser.y"
{yyval = val_peek(2); buscarUltimocomando((ASTComando)yyval).setProximo((ASTComando)val_peek(1)); System.out.println("|   comando_list comando ");}
break;
case 20:
//#line 77 "tentativaNovoParser.y"
{yyval = new ASTAtribuicao((ASTVar)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println(":   var <- expr ");}
break;
case 21:
//#line 78 "tentativaNovoParser.y"
{yyval = new ASTPrint((ASTTexto)val_peek(0)); System.out.println("|   TK_PRINT texto ");}
break;
case 22:
//#line 79 "tentativaNovoParser.y"
{yyval = new ASTRead((ASTVar)val_peek(0)); System.out.println("|   TK_READ IDENTIFICADOR ");}
break;
case 23:
//#line 80 "tentativaNovoParser.y"
{yyval= new ASTIf((ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); System.out.println("|   TK_IF expr TK_THEN comando_list TK_END_IF ");}
break;
case 24:
//#line 81 "tentativaNovoParser.y"
{yyval = new ASTIf((ASTExpressao)val_peek(5),(ASTComando)val_peek(3),(ASTComando)val_peek(1)); System.out.println("|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF ");}
break;
case 25:
//#line 82 "tentativaNovoParser.y"
{yyval = new ASTFor((ASTVar)val_peek(7),(ASTExpressao)val_peek(5),(ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); System.out.println("|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	");}
break;
case 26:
//#line 83 "tentativaNovoParser.y"
{yyval = new ASTWhile((ASTExpressao)val_peek(3),(ASTComando)val_peek(1)); System.out.println("|	TK_WHILE expr TK_DO comando_list TK_DONE					");}
break;
case 27:
//#line 87 "tentativaNovoParser.y"
{yyval = new ASTTexto((ASTExpressao)val_peek(0)); System.out.println(":	expr ");}
break;
case 28:
//#line 88 "tentativaNovoParser.y"
{yyval = new ASTTexto(((Token)val_peek(0)).getLexema()); System.out.println("|	STRING ");}
break;
case 29:
//#line 89 "tentativaNovoParser.y"
{yyval = val_peek(2); buscaUltimoTexto((ASTTexto)yyval).setProximo(new ASTTexto((ASTExpressao)val_peek(0))); System.out.println("| 	texto ',' expr ");}
break;
case 30:
//#line 90 "tentativaNovoParser.y"
{yyval = val_peek(2); buscaUltimoTexto((ASTTexto)yyval).setProximo(new ASTTexto(((Token)val_peek(0)).getLexema())); System.out.println("| 	texto ',' STRING ");}
break;
case 31:
//#line 95 "tentativaNovoParser.y"
{yyval = new ASTSoma((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println(":   expr '+' expr ");}
break;
case 32:
//#line 96 "tentativaNovoParser.y"
{yyval = new ASTSubtracao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '-' expr ");}
break;
case 33:
//#line 97 "tentativaNovoParser.y"
{yyval = new ASTMultiplicacao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '*' expr ");}
break;
case 34:
//#line 98 "tentativaNovoParser.y"
{yyval = new ASTDivisao((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '/' expr ");}
break;
case 35:
//#line 99 "tentativaNovoParser.y"
{yyval = new ASTMenor((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '<' expr ");}
break;
case 36:
//#line 100 "tentativaNovoParser.y"
{yyval = new ASTMaior((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '>' expr ");}
break;
case 37:
//#line 101 "tentativaNovoParser.y"
{yyval = new ASTIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0)); System.out.println("|   expr '=' expr ");}
break;
case 38:
//#line 102 "tentativaNovoParser.y"
{yyval = new ASTAnd((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));System.out.println("|   expr AND expr ");}
break;
case 39:
//#line 103 "tentativaNovoParser.y"
{yyval = new ASTMenorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));System.out.println("|	expr TK_LT expr													");}
break;
case 40:
//#line 104 "tentativaNovoParser.y"
{yyval = new ASTMaiorIgual((ASTExpressao)val_peek(2),(ASTExpressao)val_peek(0));System.out.println("|	expr TK_BT expr													");}
break;
case 41:
//#line 105 "tentativaNovoParser.y"
{yyval = new ASTParenteses((ASTExpressao)val_peek(1)); System.out.println("|	'(' expr ')' ");}
break;
case 42:
//#line 106 "tentativaNovoParser.y"
{System.out.println("|	var ");}
break;
case 43:
//#line 107 "tentativaNovoParser.y"
{yyval = new ASTNot((ASTExpressao)val_peek(0));System.out.println("|	NOT expr ");}
break;
case 44:
//#line 108 "tentativaNovoParser.y"
{yyval = new ASTNumero(((Token)val_peek(0)).getLexema()); System.out.println("|	REAL ");}
break;
case 45:
//#line 109 "tentativaNovoParser.y"
{yyval = new ASTNumero(((Token)val_peek(0)).getLexema()); System.out.println("|	INT ");}
break;
case 46:
//#line 110 "tentativaNovoParser.y"
{yyval = new ASTRandom(); System.out.println("|	random ");}
break;
//#line 838 "ParserX.java"
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
