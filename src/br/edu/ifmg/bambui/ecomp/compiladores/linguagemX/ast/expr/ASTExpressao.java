package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ASTNo;

/**
 * Representação base do nó da árvore de expressão
 * @author laerte
 */
public abstract class ASTExpressao extends ASTNo {

	private final ASTExpressao esquerda;
	private final ASTExpressao direita;

	public ASTExpressao(ASTExpressao esquerda, ASTExpressao direita) {
		this.esquerda = esquerda;
		this.direita = direita;
	}

	protected ASTExpressao getEsquerda() {
		return esquerda;
	}

	protected ASTExpressao getDireita() {
		return direita;
	}
	
	
}
