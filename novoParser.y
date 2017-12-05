/* CONFIGURAÇÃO DO PARSER */
%token TK_PRINT TK_READ TK_IF TK_THEN TK_ELSE TK_END_IF TK_WHILE TK_DONE TK_DO TK_NEW_VAR TK_FOR TK_FROM TK_TO TK_INT TK_END
%token IDENTIFICADOR STRING NUMERO TYPE OR AND NOT MOD


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
/* REGRAS GRAMATICAIS */

main 
:root { raiz = $1;  System.out.println("a1");};

root: declaracoes lista_comandos {$$ = $2; System.out.println("a2");};



declaracoes 
:	TK_NEW_VAR listadeclaracaovariavel {$$ = $2;System.out.println("a5");}
|   {$$ = null;System.out.println("a6");}
;

listadeclaracaovariavel 
:	declaracaovariavel { $$ = $1; System.out.println("a7");}
| 	listadeclaracaovariavel declaracaovariavel { $$ = $1; buscarUltimaDeclaracao((ASTListaDeclaracoes)$$).setProximo((ASTListaDeclaracoes)$2); System.out.println("a8");}
;


declaracaovariavel 
:	id ':' TYPE ';' {$$ = new ASTDeclaracao((ASTIdentificador)$1, ((Token)$3).getLexema()); System.out.println("a9");}
;

id 
: 	id_final { $$ = $1; System.out.println("a11");}
|	id ',' id_final { $$ = $1; buscarUltimoIds((ASTIdentificador)$$).setProximo((ASTIdentificador)$3); System.out.println("a12");}
;

id_final 
: IDENTIFICADOR { $$ = new ASTIdentificador(((Token)$1).getLexema()); System.out.println("a13"); }
;

lista_comandos 
:	comando ';' { $$ = $1; System.out.println("comando 1");}
|   lista_comandos comando ';' { $$ = $1; buscarUltimoComando((ASTComando)$$).setProximo((ASTComando)$2); System.out.println("comadno 2");}
;

comando 
:    IDENTIFICADOR '=' expr { $$ = new ASTAtribuicao(((Token)$1).getLexema(),(ASTExpressao)$3); }
|   TK_PRINT expr { $$ = new ASTPrint((ASTExpressao)$2); }
|	TK_PRINT STRING { $$ = new ASTPrint(((Token)$2).getLexema()); }
|   TK_READ IDENTIFICADOR { $$ = new ASTRead(((Token)$2).getLexema()); }
|   TK_IF expr TK_THEN lista_comandos TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4); }
|   TK_IF expr TK_THEN lista_comandos TK_ELSE lista_comandos TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4,(ASTComando)$6); }
;

expr 
:   expr '+' expr { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '-' expr { $$ = new ASTSubtracao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '*' expr { $$ = new ASTMultiplicacao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '/' expr { $$ = new ASTDivisao((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '<' expr { $$ = new ASTMenor((ASTExpressao)$1,(ASTExpressao)$3); }
|   expr '>' expr { $$ = new ASTMaior((ASTExpressao)$1,(ASTExpressao)$3); }
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