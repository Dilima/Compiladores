package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Leitura de valor de uma variável dentro de uma expressão
 * @author laerte
 */
public class ASTAcessoVariavel extends ASTExpressao {

	private final String identificador;
	
	public ASTAcessoVariavel(String identificador) {
		super(null,null);
		this.identificador = identificador;
	}

	@Override
	public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
		if(!tabelaSimbolo.contains(identificador)) {
			throw new Exception("Variável não foi declarada anteriormente.");
		}
		
		return identificador;
	}
}
