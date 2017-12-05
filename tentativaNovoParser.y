/* CONFIGURAÇÃO DO PARSER */

%token TK_PRINT TK_READ TK_TO TK_IF TK_THEN TK_ELSE TK_END_IF TK_WHILE TK_DONE TK_DO TK_NEW_VAR TK_FOR TK_FROM TK_TO TK_INT TK_END
%token IDENTIFICADOR STRING REAL BOOL INT TYPE OR AND NOT MOD

/* PRECEDENCIA */
%left '<' '>' '<=' '>='
%left '+' '-'
%left '*' '/'
%left ','

%{
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.command.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.*;
import declaracoes.*;
import ids.*;
%}

%%
main 
:	vars_decl command_list 			{}	
;

/* CONTROLE DAS VARIAVEIS */
vars_decl
:	TK_NEW_VAR vars_decl_list 		{}
|									{}
;

vars_decl_list
:	var_decl 						{}
|	vars_decl_list var_decl			{}
;

var_decl
:	id_list ':' type_specifier ';'	{}
;

id_list
:	id_list ',' IDENTIFICADOR		{}
|	IDENTIFICADOR					{}
;

type_specifier
:	type 							{}
|	type '[' INT ']'				{}
;

type
:	INT 							{}
|	REAL							{}
|	BOOL							{}
;

var
:	IDENTIFICADOR array 			{}
;
/*	CONTROLE DOS commandS	*/
command_list 
:	command ';' { $$ = $1; System.out.println("command 1");}
|   command_list command ';' { $$ = $1; buscarUltimocommand((ASTcommand)$$).setProximo((ASTcommand)$2); System.out.println("comadno 2");}
;

command 
:   IDENTIFICADOR '=' expr { $$ = new ASTAtribuicao(((Token)$1).getLexema(),(ASTExpressao)$3); }
|   TK_PRINT expr { $$ = new ASTPrint((ASTExpressao)$2); }
|	TK_PRINT STRING { $$ = new ASTPrint(((Token)$2).getLexema()); }
|   TK_READ IDENTIFICADOR { $$ = new ASTRead(((Token)$2).getLexema()); }
|   TK_IF expr TK_THEN command_list TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTcommand)$4); }
|   TK_IF expr TK_THEN command_list TK_ELSE command_list TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTcommand)$4,(ASTcommand)$6); }
|	TK_FOR var TK_FROM INT TK_TO INT TK_DO command_list TK_DONE	{}
|	TK_WHILE expr TK_DO command_list TK_DONE					{}
;

expr /* corrigir? separar expr booleanas*/
:   expr '+' expr { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '-' expr { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '*' expr { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '/' expr { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '<' expr { $$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '>' expr { $$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); }
|	expr '>=' expr													{}
|	expr '<=' expr													{}
|	'(' expr ')' { $$ = $2; }
|	IDENTIFICADOR { $$ = new ASTAcessoVariavel(((Token)$1).getLexema()); }
|	NUMERO { $$ = new ASTNumero(new Double(((Token)$1).getLexema())); }
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
private ASTcommand buscarUltimocommand(ASTcommand cmd) {
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