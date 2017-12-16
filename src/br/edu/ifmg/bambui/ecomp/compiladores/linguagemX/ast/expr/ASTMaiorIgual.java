package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

public class ASTMaiorIgual extends ASTExpressao {

	public ASTMaiorIgual(ASTExpressao esquerda, ASTExpressao direita) {
		super(esquerda, direita);
	}

	@Override
	public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
		return getEsquerda().compilarC(tabelaSimbolo)+" >= "+getDireita().compilarC(tabelaSimbolo);
	}
}
