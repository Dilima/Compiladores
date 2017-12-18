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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Projeto
 */
public class ASTFor extends ASTComando {
    private final ASTVar id1;
    private final ASTExpressao expr2;
    private final ASTExpressao expr3;
    private final ASTComando blococomando;

    public ASTFor(ASTVar id1, ASTExpressao expr2, ASTExpressao expr3, ASTComando blococomando) {
        this.id1 = id1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.blococomando = blococomando;
    }

    public ASTExpressao getId1() {
        return id1;
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
        String output = "for("+getId1().compilarC(tabelaSimbolo)+"="+
                getExpr2().compilarC(tabelaSimbolo)+";"+
                getId1().compilarC(tabelaSimbolo)+"<="+
                getExpr3().compilarC(tabelaSimbolo)+";"+
                getId1().compilarC(tabelaSimbolo)+"++){\n"+
                getBlococomando().compilarC(tabelaSimbolo)+"\n}\n";
        
        if(getProximo()!=null){
            output += getProximo().compilarC(tabelaSimbolo);
        }
        
        return output;
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        int auxsize =  vars.get(1).size();
        vars = getExpr2().compilarMIPS(vars);
        output = "sw $s0," + vars.get(3).indexOf(id1.getIdentificador()) * 4
                + "($s7)\n";
        output += "for" + auxsize+":\n";
        vars.get(4).add(output);
        vars.get(2).add("1");
        vars = getExpr3().compilarMIPS(vars);
        vars.get(2).remove();
        vars = id1.compilarMIPS(vars);
        output = "slt $s0, $s0, $s1\n"
                + "beq $s0, $zero, exitfor"+auxsize+"\n";
        
        vars.get(4).add(output);
        vars = getBlococomando().compilarMIPS(vars);
        vars = id1.compilarMIPS(vars);
        output = "addi $s0, $s0, 1\n";
        output += "sw $s0," + vars.get(3).indexOf(id1.getIdentificador()) * 4
                + "($s7)\n";
        output += "j for"+auxsize+"\n";
        output += "exitfor" + auxsize+":\n";
        vars.get(4).add(output);
        return vars;
    }
    
    
}
