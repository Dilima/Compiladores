/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaveis.tipos;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Projeto
 */
public class ASTInt extends ASTExpressao{
    
    	private final Integer valor;
        
	public ASTInt(int valor) {
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
