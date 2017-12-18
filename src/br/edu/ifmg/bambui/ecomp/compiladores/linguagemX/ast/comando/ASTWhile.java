/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Projeto
 */
public class ASTWhile extends ASTComando{
    private final ASTExpressao condicao;
    private final ASTComando blococomandos;
    
    public ASTWhile(ASTExpressao condicao,ASTComando blococomando){
     this.condicao = condicao;
     this.blococomandos = blococomando;
    }

    public ASTExpressao getCondicao() {
        return condicao;
    }

    public ASTComando getBlococomandos() {
        return blococomandos;
    }
    
    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "while("+getCondicao().compilarC(tabelaSimbolo)+
                "){\n "+getBlococomandos().compilarC(tabelaSimbolo)+
                "\n}\n";
        
        if(getProximo()!=null){
            output+=getProximo().compilarC(tabelaSimbolo);
        }
        return output;
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output="";
        int auxsize =  vars.get(1).size();
        vars = getCondicao().compilarMIPS(vars);
        output = "while" + auxsize+":\n";
        output += "beq $s0, $zero, exitwhile"+auxsize+"\n";
        vars.get(4).add(output);
        vars = getBlococomandos().compilarMIPS(vars);
        vars = getCondicao().compilarMIPS(vars);
        output = "j while"+auxsize+"\n";
        output += "exitwhile" + auxsize+":\n";
        vars.get(4).add(output);
        return vars; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
