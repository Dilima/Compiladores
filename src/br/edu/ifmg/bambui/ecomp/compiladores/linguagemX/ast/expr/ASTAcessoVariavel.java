package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output = "lw $a0, " +vars.get(3).indexOf(identificador)*4
                + "($s7)\n";
        output += "add $s"+vars.get(2).size()+ ", $zero, $a0 \n";
        vars.get(4).add(output);
        return vars;
    }
        

    public String getIdentificador() {
        return identificador;
    }
        
        
}
