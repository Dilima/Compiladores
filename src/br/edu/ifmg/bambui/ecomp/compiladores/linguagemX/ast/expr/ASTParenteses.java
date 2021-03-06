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
public class ASTParenteses extends ASTExpressao{

    public ASTParenteses(ASTExpressao expre) {
        super(expre, null);
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return "( " + getEsquerda().compilarC(tabelaSimbolo) + " )";
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        vars = getEsquerda().compilarMIPS(vars);
        return vars;
    }
    
    
}
