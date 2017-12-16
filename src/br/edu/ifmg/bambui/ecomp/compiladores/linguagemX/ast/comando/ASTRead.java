package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Comando para atribuir em variável número informado na entrada padrão
 * @author laerte
 */
public class ASTRead extends ASTComando {
	
	private final ASTVar identificador;

	public ASTRead(ASTVar identificador) {
		this.identificador = identificador;
	}

    public ASTVar getIdentificador() {
        return identificador;
    }
        
	
	@Override
	public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
		String output = "scanf(\"%d\",&";
		
		/*if(!tabelaSimbolo.contains(getIdentificador())) {
			tabelaSimbolo.add(getIdentificador());
		}
		*/
		output += getIdentificador().compilarC(tabelaSimbolo)+");";
		output+="\n";
                
                if(getProximo() != null) {
			output+=getProximo().compilarC(tabelaSimbolo);
		}
		
		return output;
	}
}
