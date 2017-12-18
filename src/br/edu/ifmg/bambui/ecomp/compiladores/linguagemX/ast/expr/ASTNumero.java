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
 * @author Projeto
 */
public class ASTNumero extends ASTExpressao{
    private String valor;
    public ASTNumero(String valor) {
        super(null, null);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return getValor();
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        output = "addi $a0,$zero," + getValor() + "\n";
        output += "addi $s" + vars.get(2).size()
                + ",$zero"
                + "," + getValor()+"\n";
        vars.get(4).add(output);
        return vars;
    }
   
    
    
}
