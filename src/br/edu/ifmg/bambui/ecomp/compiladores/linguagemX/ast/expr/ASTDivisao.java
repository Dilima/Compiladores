package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Nó que representa a divisão entre 2 valores
 * @author laerte
 */
public class ASTDivisao extends ASTExpressao {

	public ASTDivisao(ASTExpressao esquerda, ASTExpressao direita) {
		super(esquerda, direita);
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		double t1,t2;
		
		getEsquerda().interpretar(tabelaSimbolo);
		t1 = (Double) tabelaSimbolo.get("!");
		getDireita().interpretar(tabelaSimbolo);
		t2 = (Double) tabelaSimbolo.get("!");
		tabelaSimbolo.put("!", t1/t2);
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		return getEsquerda().compilar(tabelaSimbolo)+" / "+getDireita().compilar(tabelaSimbolo);
	}

	
}
