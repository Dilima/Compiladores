package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr;

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
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		if(!tabelaSimbolo.containsKey(identificador)) {
			throw new Exception("Variável não foi declarada anteriormente.");
		}
		
		tabelaSimbolo.put("!", tabelaSimbolo.get(identificador));
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		if(!tabelaSimbolo.contains(identificador)) {
			throw new Exception("Variável não foi declarada anteriormente.");
		}
		
		return identificador;
	}
	
	

}
