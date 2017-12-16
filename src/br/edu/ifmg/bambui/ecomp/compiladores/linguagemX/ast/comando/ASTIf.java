package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

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
}
