/* CONFIGURAÇÃO DO PARSER */

%token TK_PRINT TK_READ TK_TO TK_IF TK_THEN TK_ELSE TK_END_IF TK_WHILE TK_DONE TK_DO TK_ATRIBUICAO TK_NEW_VAR TK_FOR TK_FROM TK_TO TK_INT TK_BOOL TK_REAL TK_END TK_BT TK_LT
%token IDENTIFICADOR STRING REAL BOOL INT TYPE OR AND NOT MOD

/* PRECEDENCIA */
%left '<' '>' TK_BT 
%left '+' '-' TK_LT
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
: program	{System.out.println(": program	");}
;

program 
:	vars_decl comando_list 						{System.out.println(":	vars_decl comando_list 						");};	
;

/* CONTROLE DAS VARIAVEIS */
vars_decl
:	vars_decl_list 								{System.out.println(":	vars_decl_list 								");}
|												{System.out.println("|												");}
;

vars_decl_list
:	var_decl 									{System.out.println(":	var_decl 									");}
|	vars_decl_list var_decl 					{System.out.println("|	vars_decl_list var_decl 					");}
;

var_decl
:	TK_NEW_VAR id_list ':' type_specifier 	';' 				{System.out.println(":	TK_NEW_VAR id_list ':' type_specifier 	';' 				");}
;

id_list
:	IDENTIFICADOR								{System.out.println(":	IDENTIFICADOR								");}
|	id_list ',' IDENTIFICADOR					{System.out.println("|	id_list ',' IDENTIFICADOR					");}
;

type_specifier
:	type 										{System.out.println(":	type 										");}
|	type '[' expr ']'							{System.out.println("|	type '[' expr ']'							");}
;

type
:	TK_INT 										{System.out.println(":	TK_INT 										");}
|	TK_REAL										{System.out.println("|	TK_REAL										");}
|	TK_BOOL										{System.out.println("|	TK_BOOL										");}
;

var
:	IDENTIFICADOR  								{System.out.println(":	IDENTIFICADOR  								");}
|	IDENTIFICADOR	'[' expr ']'				{System.out.println("|	IDENTIFICADOR	'[' expr ']'				");}
;

/*	CONTROLE DOS comandos	*/
comando_list 
:	comando  {System.out.println(":	comando ");}
|   comando_list comando {System.out.println("|   comando_list comando ");}
;

comando 
:   var TK_ATRIBUICAO expr ';' {System.out.println(":   var '=' expr ");}
|   TK_PRINT texto ';' {System.out.println("|   TK_PRINT texto ");}
|   TK_READ IDENTIFICADOR ';' {System.out.println("|   TK_READ IDENTIFICADOR ");}
|   TK_IF expr TK_THEN comando_list TK_END_IF {System.out.println("|   TK_IF expr TK_THEN comando_list TK_END_IF ");}
|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF {System.out.println("|   TK_IF expr TK_THEN comando_list TK_ELSE comando_list TK_END_IF ");}
|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	';'{System.out.println("|	TK_FOR var TK_FROM INT TK_TO INT TK_DO comando_list TK_DONE	");}
|	TK_WHILE expr TK_DO comando_list TK_DONE ';' {System.out.println("|	TK_WHILE expr TK_DO comando_list TK_DONE					");}
;

texto
:	expr {System.out.println(":	expr ");}
|	STRING {System.out.println("|	STRING ");}
| 	texto ',' expr {System.out.println("| 	texto ',' expr ");}
| 	texto ',' STRING {System.out.println("| 	texto ',' STRING ");}
;


expr /* corrigir? separar expr booleanas*/
:   expr '+' expr {System.out.println(":   expr '+' expr ");}
|   expr '-' expr {System.out.println("|   expr '-' expr ");}
|   expr '*' expr {System.out.println("|   expr '*' expr ");}
|   expr '/' expr {System.out.println("|   expr '/' expr ");}
|   expr '<' expr {System.out.println("|   expr '<' expr ");}
|   expr '>' expr {System.out.println("|   expr '>' expr ");}
|	expr TK_BT expr													{System.out.println("|	expr TK_BT expr													");}
|	expr TK_LT expr													{System.out.println("|	expr TK_LT expr													");}
|	'(' expr ')' {System.out.println("|	'(' expr ')' ");}
|	IDENTIFICADOR {System.out.println("|	IDENTIFICADOR ");}
|	REAL {System.out.println("|	REAL ");}
|	INT {System.out.println("|	INT ");}
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