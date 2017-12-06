/* CONFIGURAÇÃO DO PARSER */

%token TK_PRINT TK_READ TK_TO TK_IF TK_THEN TK_ELSE TK_END_IF TK_WHILE TK_DONE TK_DO TK_NEW_VAR TK_FOR TK_FROM TK_TO TK_INT TK_BOOL TK_REAL TK_END
%token IDENTIFICADOR STRING REAL BOOL INT TYPE OR AND NOT MOD

/* PRECEDENCIA */
%left '<' '>' '<=' '>='
%left '+' '-'
%left '*' '/'
%left ','

%{
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.*;
import declaracoes.*;
import ids.*;
%}

%%
main 
: program	{raiz = $1;}
;

program 
:	vars_decl comando_list 						{System.out.println("vars_decl /*comando_list*/ 						\n");};	
;

/* CONTROLE DAS VARIAVEIS */
vars_decl
:	vars_decl_list 								{System.out.println("vars_decl_list 					\n");}
|												{System.out.println("{\n");}
;

vars_decl_list
:	var_decl 									{System.out.println("TK_NEW_VAR var_decl ';'								\n");}
|	vars_decl_list var_decl 					{System.out.println("TK_NEW_VAR vars_decl_list var_decl	';'		\n");}
;

var_decl
:	TK_NEW_VAR id_list ':' type_specifier 	';' 				{System.out.println("id_list ':' type_specifier 					\n");}
;

id_list
:	IDENTIFICADOR								{System.out.println("IDENTIFICADOR								\n");}
|	id_list ',' IDENTIFICADOR					{System.out.println("id_list ',' IDENTIFICADOR					\n");}
;

type_specifier
:	type 										{System.out.println("type 										\n");}
|	type '[' INT ']'							{System.out.println("type '[' INT ']'							\n");}
|	type '[' expr ']'							{System.out.println("type '[' expr ']'							\n");}
;

type
:	TK_INT 										{System.out.println("INT										\n");}
|	TK_REAL										{System.out.println("REAL										\n");}
|	TK_BOOL										{System.out.println("BOOL										\n");}
;

var
:	IDENTIFICADOR  								{System.out.println("IDENTIFICADOR  								\n");}
|	IDENTIFICADOR	'[' INT ']'					{System.out.println("IDENTIFICADOR	'[' INT ']'					\n");}
|	IDENTIFICADOR	'[' expr ']'				{System.out.println("IDENTIFICADOR	'[' expr ']'				\n");}
;

/*	CONTROLE DOS comandos	*/
comando_list 
:	comando ';' { $$ = $1; System.out.println("comando 1");}
|   comando_list comando ';' { $$ = $1; buscarUltimocomando((ASTComando)$$).setProximo((ASTComando)$2); System.out.println("comadno 2");}
;

comando 
:   var '=' expr { $$ = new ASTAtribuicao(((Token)$1).getLexema(),(ASTExpressao)$3); }
|   TK_PRINT expr { $$ = new ASTPrint((ASTExpressao)$2); }
|	TK_PRINT STRING { $$ = new ASTPrint(((Token)$2).getLexema()); }
|   TK_READ IDENTIFICADOR { $$ = new ASTRead(((Token)$2).getLexema()); }
|   TK_IF expr TK_THEN comando_list TK_END_IF { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4); }
|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4,(ASTComando)$6); }
|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	{System.out.println("TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	\n");}
|	TK_WHILE expr TK_DO comando_list TK_DONE					{System.out.println("TK_WHILE expr TK_DO comando_list TK_DONE					\n");}
;

expr /* corrigir? separar expr booleanas*/
:   expr '+' expr { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '-' expr { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '*' expr { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '/' expr { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '<' expr { $$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '>' expr { $$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); }
|	expr '>=' expr													{System.out.println("expr '>=' expr													\n");}
|	expr '<=' expr													{System.out.println("expr '<=' expr													\n");}
|	'(' expr ')' { $$ = $2; }
|	IDENTIFICADOR { $$ = new ASTAcessoVariavel(((Token)$1).getLexema()); }
|	REAL {}
;
%%
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