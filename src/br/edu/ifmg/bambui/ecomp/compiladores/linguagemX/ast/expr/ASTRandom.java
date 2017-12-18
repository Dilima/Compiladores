/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.ASTComando;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Projeto
 */
public class ASTRandom extends ASTExpressao {

    public ASTRandom() {
        super(null, null);
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return "rand()";
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output = "add $s0, $s0, $s1 #random\n";
        vars.get(4).add(output);
        return vars; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
