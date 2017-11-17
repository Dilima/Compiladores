package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Nó da árvore abstrata que representa uma atribuição
 * @author laerte
 */
public class ASTAtribuicao extends ASTComando {

	private final String identificador;
	private final ASTExpressao expressao;

	public ASTAtribuicao(String identificador, ASTExpressao expressao) {
		this.identificador = identificador;
		this.expressao = expressao;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		expressao.interpretar(tabelaSimbolo);
		
		if(!(tabelaSimbolo.get("!") instanceof Double)) {
			throw new Exception("Atribuições só podem ser feitas por números.");
		}
		
		tabelaSimbolo.put(identificador, tabelaSimbolo.get("!"));
		
		if(getProximo()!=null) {
			getProximo().interpretar(tabelaSimbolo);
		}
	}

	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		String output;
		
		if(!tabelaSimbolo.contains(identificador)) {
			tabelaSimbolo.add(identificador);
		}
		
		output = identificador+" = "+expressao.compilar(tabelaSimbolo)+";";
		
		if(getProximo() != null) {
			output+=getProximo().compilar(tabelaSimbolo);
		}
		
		return output;
	}
	
	
}
