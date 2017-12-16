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
}
