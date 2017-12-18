package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ASTNo;

/**
 * Classe base para comando da AST
 * @author laerte
 */
public abstract class ASTComando extends ASTNo {

	private ASTComando proximo;

	public void setProximo(ASTComando proximo) {
		this.proximo = proximo;
	}

	public ASTComando getProximo() {
		return proximo;
	}
        public ASTTexto getTexto()throws Exception{
            throw new RuntimeException("Não é possível utilizar este método da classe ASTComando, é necessário sobreescrevê-lo");
        }
        public void setTexto(ASTTexto texto)throws Exception{
            throw new RuntimeException("Não é possível utilizar este método da classe ASTComando, é necessário sobreescrevê-lo");
        }
}
