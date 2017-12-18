package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.ASTComando;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Modelo de nó da árvore sintática
 *
 * @author laerte
 */
public class ASTNo {

	public ASTNo() {
		// throw new RuntimeException("Não é possível instanciar esta classe! é necessário herança para isso" + this.getClass().getName());
	}

	/**
	 * Interpreta o código gerado A chave "!" dentro da tabela de símbolos
	 * representa o valor temporário de operações aritméticas
	 *
	 * @param tabelaSimbolo Tabela de símbolos com as variáveis
	 * @throws Exception Erro na interpretação do código
	 */
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		throw new RuntimeException("Não é possível utilizar este método da classe ASTNo, é necessário sobreescrevê-lo");
	}
	
	public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
		throw new RuntimeException("Não é possível utilizar este método da classe ASTNo, é necessário sobreescrevê-lo");
	}
        public String compilarMIPS(HashSet<String> tabelaSimbolo) throws Exception {
		throw new RuntimeException("Não é possível utilizar este método da classe ASTNo, é necessário sobreescrevê-lo");
	}
        public ASTComando encontraString() throws Exception{
            throw new RuntimeException("Não é possível utilizar este método da classe ASTNo, é necessário sobreescrevê-lo");
        }
}
