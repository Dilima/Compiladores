/* CONFIGURAÇÃO DO PARSER */

%token TK_PRINT TK_READ TK_TO TK_IF TK_THEN TK_ELSE TK_END_IF TK_WHILE TK_DONE TK_DO TK_ATRIBUICAO FUNCTION_RANDOM TK_NEW_VAR TK_FOR TK_FROM TK_TO TK_INT TK_BOOL TK_REAL TK_END TK_BT TK_LT
%token IDENTIFICADOR STRING REAL BOOL INT TYPE OR AND NOT MOD

/* PRECEDENCIA */
%left NOT 
%left OR 
%left AND 
%left '<' '>' TK_BT 
%left '+' '-' TK_LT
%left '*' '/'
%left ','
%left '='

%{
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr.*;
%}

%%
main 
: program	{raiz = $1; System.out.println(": program	");}
;

program 
:	vars_decl comando_list 											{$$ = new ASTProgram((ASTDeclaracao)$1,(ASTComando)$2); System.out.println(":	vars_decl comando_list 						");};	
| 	vars_decl 														{$$ = new ASTProgram((ASTDeclaracao)$1); System.out.println(":	vars_decl				");};
;

/* CONTROLE DAS VARIAVEIS */
vars_decl
:	vars_decl_list 													{System.out.println(":	vars_decl_list 								");}
|																	{System.out.println("|												");}
;

vars_decl_list
:	var_decl 														{$$ = $1; System.out.println(":	var_decl 									");}
|	vars_decl_list var_decl 										{$$ = $1; buscaUltimaDecl((ASTDeclaracao)$$).setProximo((ASTDeclaracao)$2);System.out.println("|	vars_decl_list var_decl 					");}
;

var_decl
:	TK_NEW_VAR id_list ':' type_specifier 	';' 					{$$ = new ASTDeclaracao((ASTListaID)$2,(ASTTipoEspecifico)$4); System.out.println(":	TK_NEW_VAR id_list ':' type_specifier 	';' 				");}
;

id_list
:	IDENTIFICADOR													{$$ = new ASTListaID(((Token)$1).getLexema()); System.out.println(":	IDENTIFICADOR								");}
|	id_list ',' IDENTIFICADOR										{$$  = $1; buscaUltimoID((ASTListaID)$$).setProximo(new ASTListaID(((Token)$3).getLexema())); System.out.println("|	id_list ',' IDENTIFICADOR					");}
;

type_specifier
:	type 															{$$ = new ASTTipoEspecifico((ASTTipo)$1); System.out.println(":	type 										");}
|	type '[' expr ']'												{$$ = new ASTTipoEspecifico((ASTTipo)$1,(ASTExpressao)$3); System.out.println("|	type '[' expr ']'							");}
;

type
:	TK_INT 															{$$ = new ASTTipo("int"); System.out.println(":	TK_INT 										");}
|	TK_REAL															{$$ = new ASTTipo("double"); System.out.println("|	TK_REAL										");}
|	TK_BOOL															{$$ = new ASTTipo("bool"); System.out.println("|	TK_BOOL										");}
;

var
:	IDENTIFICADOR  													{$$ = new ASTVar(((Token)$1).getLexema()); System.out.println(":	IDENTIFICADOR  								");}
|	IDENTIFICADOR	'[' expr ']'									{$$ = new ASTVar(((Token)$1).getLexema(),(ASTExpressao)$3); System.out.println("|	IDENTIFICADOR	'[' expr ']'				");}
;

/*	CONTROLE DOS comandos	*/
comando_list 
:	comando  														{$$ = $1; System.out.println(":	comando ");}
|   comando_list comando 											{$$ = $1; buscarUltimocomando((ASTComando)$$).setProximo((ASTComando)$2); System.out.println("|   comando_list comando ");}
;

comando 
:   var TK_ATRIBUICAO expr ';' 										{$$ = new ASTAtribuicao((ASTVar)$1,(ASTExpressao)$3); System.out.println(":   var <- expr ");}
|   TK_PRINT texto ';' 												{$$ = new ASTPrint((ASTTexto)$2); System.out.println("|   TK_PRINT texto ");}
|   TK_READ var ';' 												{$$ = new ASTRead((ASTVar)$2); System.out.println("|   TK_READ IDENTIFICADOR ");}
|   TK_IF expr TK_THEN comando_list TK_END_IF {$$= new ASTIf((ASTExpressao)$2,(ASTComando)$4); System.out.println("|   TK_IF expr TK_THEN comando_list TK_END_IF ");}
|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF 	{$$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4,(ASTComando)$6); System.out.println("|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF ");}
|	TK_FOR expr TK_FROM expr TK_TO expr TK_DO comando_list TK_DONE	';' {$$ = new ASTFor((ASTExpressao)$2,(ASTExpressao)$4,(ASTExpressao)$6,(ASTComando)$8); System.out.println("|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	");}
|	TK_WHILE expr TK_DO comando_list TK_DONE ';' 					{$$ = new ASTWhile((ASTExpressao)$2,(ASTComando)$4); System.out.println("|	TK_WHILE expr TK_DO comando_list TK_DONE					");}
;

texto
:	expr 															{$$ = new ASTTexto((ASTExpressao)$1); System.out.println(":	expr ");}
|	STRING 															{$$ = new ASTTexto(((Token)$1).getLexema()); System.out.println("|	STRING ");}
| 	texto ',' expr 													{$$ = $1; buscaUltimoTexto((ASTTexto)$$).setProximo(new ASTTexto((ASTExpressao)$3)); System.out.println("| 	texto ',' expr ");}
| 	texto ',' STRING 												{$$ = $1; buscaUltimoTexto((ASTTexto)$$).setProximo(new ASTTexto(((Token)$3).getLexema())); System.out.println("| 	texto ',' STRING ");}
;


expr /* corrigir? separar expr booleanas*/
:   expr '+' expr 													{$$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); System.out.println(":   expr '+' expr ");}
|   expr '-' expr 													{$$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '-' expr ");}
|   expr '*' expr 													{$$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '*' expr ");}
|   expr '/' expr 													{$$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '/' expr ");}
|   expr '<' expr 													{$$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '<' expr ");}
|   expr '>' expr 													{$$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '>' expr ");}
|   expr '=' expr 													{$$ = new ASTIgual((ASTExpressao)$1,(ASTExpressao)$3); System.out.println("|   expr '=' expr ");}
|   expr AND expr 													{$$ = new ASTAnd((ASTExpressao)$1,(ASTExpressao)$3);System.out.println("|   expr AND expr ");}
|	expr TK_LT expr													{$$ = new ASTMenorIgual((ASTExpressao)$1,(ASTExpressao)$3);System.out.println("|	expr TK_LT expr													");}
|	expr TK_BT expr													{$$ = new ASTMaiorIgual((ASTExpressao)$1,(ASTExpressao)$3);System.out.println("|	expr TK_BT expr													");}
|	'(' expr ')' 													{$$ = new ASTParenteses((ASTExpressao)$2); System.out.println("|	'(' expr ')' ");}
|	var 															{System.out.println("|	var ");}
|	NOT expr 														{$$ = new ASTNot((ASTExpressao)$2);System.out.println("|	NOT expr ");}	
|	REAL 															{$$ = new ASTNumero(((Token)$1).getLexema()); System.out.println("|	REAL ");}
|	INT																{$$ = new ASTNumero(((Token)$1).getLexema()); System.out.println("|	INT ");}
|	FUNCTION_RANDOM 												{$$ = new ASTRandom(); System.out.println("|	random ");}
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
public void compilar() throws Exception {
	HashSet<String> tabelaSimbolo = new HashSet<String>();
	PrintWriter printWriter;
    String outputC="";
	String saidaC;
	yyparse();
	saidaC = raiz.compilar(tabelaSimbolo);
	
	outputC+= "#include<stdio.h>\n";
	outputC+= "#include<stdlib.h>\n";
	outputC+= "#include<stdbool.h>\n";
	outputC+="\nint main(void){\n";

	outputC += saidaC+"\n";
	outputC += "\n}";
	printWriter = new PrintWriter("output.c","UTF-8");
	printWriter.print(outputC);
	printWriter.close();

	String outputMIPS="";
	String saidaMIPS="";
	//saidaMIPS = raiz.compilarMIPS(tabelaSimbolo);
	
	outputMIPS+= "#include<stdio.h>\n";
	outputMIPS+= "#include<stdlib.h>\n";
	outputMIPS+= "#include<stdbool.h>\n";
	outputMIPS+="\nint main(void){\n";

	outputMIPS += saidaMIPS+"\n";
	outputMIPS += "\n}";
	printWriter = new PrintWriter("output.MIPS","UTF-8");
	printWriter.print(outputMIPS);
	printWriter.close();

}