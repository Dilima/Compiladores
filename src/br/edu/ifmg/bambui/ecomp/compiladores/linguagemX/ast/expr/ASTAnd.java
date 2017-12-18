/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Guilherme Magno
 */
public class ASTAnd extends ASTExpressao{

    public ASTAnd(ASTExpressao esquerda, ASTExpressao direita) {
        super(esquerda, direita);
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return getEsquerda().compilarC(tabelaSimbolo) + " && " + getDireita().compilarC(tabelaSimbolo);
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
    vars = getEsquerda().compilarMIPS(vars);
    vars.get(2).add("1");
    vars = getDireita().compilarMIPS(vars);
    vars.get(2).remove();
    String output = "and $s"+vars.get(2).size()+ ", $s"+vars.get(2).size()+",$s"+(vars.get(2).size()+1)+"\n";
    return vars;
    }
    
}
