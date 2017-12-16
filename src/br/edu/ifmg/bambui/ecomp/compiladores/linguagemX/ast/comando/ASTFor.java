/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Projeto
 */
public class ASTFor extends ASTComando {
    private final ASTExpressao expr1;
    private final ASTExpressao expr2;
    private final ASTExpressao expr3;
    private final ASTComando blococomando;

    public ASTFor(ASTExpressao expr1, ASTExpressao expr2, ASTExpressao expr3, ASTComando blococomando) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.blococomando = blococomando;
    }

    public ASTExpressao getExpr1() {
        return expr1;
    }

    public ASTExpressao getExpr2() {
        return expr2;
    }

    public ASTExpressao getExpr3() {
        return expr3;
    }

    public ASTComando getBlococomando() {
        return blococomando;
    }
    
    
    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "for("+getExpr1().compilarC(tabelaSimbolo)+"="+
                getExpr2().compilarC(tabelaSimbolo)+";"+
                getExpr1().compilarC(tabelaSimbolo)+"<="+
                getExpr3().compilarC(tabelaSimbolo)+";"+
                getExpr1().compilarC(tabelaSimbolo)+"++){\n"+
                getBlococomando().compilarC(tabelaSimbolo)+"\n}\n";
        
        if(getProximo()!=null){
            output += getProximo().compilarC(tabelaSimbolo);
        }
        
        return output;
    }
    
}
