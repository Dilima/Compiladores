/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;
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
    
    
    
}
