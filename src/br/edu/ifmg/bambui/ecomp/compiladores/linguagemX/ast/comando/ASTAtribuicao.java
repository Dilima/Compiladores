package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Nó da árvore abstrata que representa uma atribuição
 * @author laerte
 */
public class ASTAtribuicao extends ASTComando {

	private final ASTVar identificador;
	private final ASTExpressao expressao;

	public ASTAtribuicao(ASTVar identificador, ASTExpressao expressao) {
		this.identificador = identificador;
		this.expressao = expressao;
	}

    public ASTVar getIdentificador() {
        return identificador;
    }

    public ASTExpressao getExpressao() {
        return expressao;
    }

	
	@Override
	public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
		String output;
		
		/*if(!tabelaSimbolo.contains(get)) {
			tabelaSimbolo.add(identificador);
		}
		*/
                
		output = getIdentificador().compilar(tabelaSimbolo)+" = "+getExpressao().compilar(tabelaSimbolo)+";";
		output+="\n";
                
		if(getProximo() != null) {
			output+=getProximo().compilar(tabelaSimbolo);
		}
		
		return output;
	}
	
	
}
