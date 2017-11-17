/* CONFIGURAÇÃO DO PARSER */

%token TK_PRINT TK_READ TK_IF TK_END TK_THEN TK_ELSE
%token IDENTIFICADOR STRING NUMERO


/* PRECEDENCIA */
%left '<' '>'
%left '+' '-'
%left '*' '/'

%{
import java.io.*;
import java.util.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.*;
%}

%%
/* REGRAS GRAMATICAIS */

main : lista_comandos { raiz = $1; }

lista_comandos :
    comando ';' { $$ = $1; }
|   lista_comandos comando ';' { $$ = $1; buscarUltimoComando((ASTComando)$$).setProximo((ASTComando)$2); }
;


comando :
    IDENTIFICADOR '=' expr { $$ = new ASTAtribuicao(((Token)$1).getLexema(),(ASTExpressao)$3); }
|   TK_PRINT expr { $$ = new ASTPrint((ASTExpressao)$2); }
|	TK_PRINT STRING { $$ = new ASTPrint(((Token)$2).getLexema()); }
|   TK_READ IDENTIFICADOR { $$ = new ASTRead(((Token)$2).getLexema()); }
|   TK_IF expr TK_THEN lista_comandos TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4); }
|   TK_IF expr TK_THEN lista_comandos TK_ELSE lista_comandos TK_END { $$ = new ASTIf((ASTExpressao)$2,(ASTComando)$4,(ASTComando)$6); }
;

expr :
    expr '+' expr { $$ = new ASTSoma((ASTExpressao)$1,(ASTExpressao)$3); }
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

private ScannerJBasic scanner;

public ParserJBasic(Reader reader) {
    scanner = new ScannerJBasic(reader,this);
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

	printWriter = new PrintWriter("/tmp/JBasicOutput.java","UTF-8");
	printWriter.print(output);
	printWriter.close();
}