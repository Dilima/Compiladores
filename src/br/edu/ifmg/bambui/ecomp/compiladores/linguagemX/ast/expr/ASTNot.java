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
public class ASTNot extends ASTExpressao {

    public ASTNot(ASTExpressao expre) {
        super(expre, null);
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return "! " + getEsquerda().compilarC(tabelaSimbolo);
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        vars = getEsquerda().compilarMIPS(vars);

        output = "nor $s" + vars.get(2).size()
                + ", $s" + vars.get(2).size()
                + ", $s" + vars.get(2).size()
                + "\n";
        output += "add $a0, $zero, $s0\n";
        vars.get(4).add(output);
        return vars;
    }
}
