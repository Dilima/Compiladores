package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Nó da árvore que representa a estrutura de condição IF
 * @author laerte
 */
public class ASTIf extends ASTComando {
	
	private final ASTExpressao condicao;
	private final ASTComando blocoThen;
	private final ASTComando blocoElse;

	public ASTIf(ASTExpressao condicao, ASTComando blocoThen, ASTComando blocoElse) {
		this.condicao = condicao;
		this.blocoThen = blocoThen;
		this.blocoElse = blocoElse;
	}

	public ASTIf(ASTExpressao condicao, ASTComando blocoThen) {
		this.condicao = condicao;
		this.blocoThen = blocoThen;
		this.blocoElse = null;
	}

	@Override
	public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
		condicao.interpretar(tabelaSimbolo);
		
		if(!(tabelaSimbolo.get("!") instanceof Boolean)) {
			throw new Exception("A expressão em condicionais deve ser um booleano.");
		}
		
		if((Boolean)tabelaSimbolo.get("!")) {
			blocoThen.interpretar(tabelaSimbolo);
		}else if(blocoElse != null) {
			blocoElse.interpretar(tabelaSimbolo);
		}
		
		if(getProximo()!=null) {
			getProximo().interpretar(tabelaSimbolo);
		}
	}

    public ASTExpressao getCondicao() {
        return condicao;
    }

    public ASTComando getBlocoThen() {
        return blocoThen;
    }

    public ASTComando getBlocoElse() {
        return blocoElse;
    }
        
	@Override
	public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
		String output = "if("+getCondicao().compilarC(tabelaSimbolo)+"){\n"+
                        getBlocoThen().compilarC(tabelaSimbolo)+"\n}\n";
                    
                if(getBlocoElse()!=null){
                    output+="else{\n "+getBlocoElse().compilarC(tabelaSimbolo)+"\n}\n";
                }
                    
                
		if(getProximo() != null) {
			output+=getProximo().compilarC(tabelaSimbolo);
		}
		
		return output;
	}

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        vars = getCondicao().compilarMIPS(vars);
        vars.get(1).add("a");
        String aux;
        aux = (getBlocoElse()!= null)? "else":"fimif";
        int auxsize = vars.get(1).size();
        output = "beq $s0, $zero, "+ aux+auxsize+"\n";
        vars.get(4).add(output);
        vars = getBlocoThen().compilarMIPS(vars);
        
        output = "j fimif"+ auxsize + "\n";
        vars.get(4).add(output);
        
        if(getBlocoElse()!= null){
         output = "else"+auxsize+":\n";
         vars = getBlocoElse().compilarMIPS(vars);
        }
        output = "fimif"+ auxsize + ":\n";
        vars.get(4).add(output);
        
        return vars;
    }
        
}
