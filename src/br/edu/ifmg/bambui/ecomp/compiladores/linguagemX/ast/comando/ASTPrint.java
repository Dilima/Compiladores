package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Imprime expressão na saída padrão
 *
 * @author laerte
 */
public class ASTPrint extends ASTComando {

    private  ASTTexto texto;

    public ASTPrint(ASTTexto texto) {
        this.texto = texto;
    }

    public ASTTexto getTexto() {
        return texto;
    }

    public void setTexto(ASTTexto texto) {
        this.texto = texto;
    }
    
    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "printf(";
        
        output += texto.compilarTexto(tabelaSimbolo,"","");
        
        output += ");\n";

        if(getProximo()!= null)
            output += getProximo().compilarC(tabelaSimbolo);
        return output;
    }
    
    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output = "";
        
        vars = texto.compilarMIPS(vars);
            if(getProximo()!=null)
                vars = getProximo().compilarMIPS(vars);
        return vars;
    }

}
