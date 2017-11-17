package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Representação de uma constante numérica na árvore sintática
 * @author laerte
 */
public class ASTNumero extends ASTExpressao {

	private final Double valor;

	public ASTNumero(Double valor) {
		super(null, null);
		this.valor = valor;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		tabelaSimbolo.put("!", valor);
	}
	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		return valor.toString();
	}
}
