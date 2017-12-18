/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Projeto
 */
public class ASTVar extends ASTExpressao{
    private String identificador;
    private ASTExpressao expressao;

    public ASTVar(String identificador) {
        super(null, null);
        this.identificador = identificador;
    }

    public ASTVar(String identificador, ASTExpressao expressao) {
        super(null, null);
        this.identificador = identificador;
        this.expressao = expressao;
    }
   

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ASTExpressao getExpressao() {
        return expressao;
    }

    public void setExpressao(ASTExpressao expressao) {
        this.expressao = expressao;
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output = getIdentificador();
        if(getExpressao()!=null){
            output+="["+getExpressao().compilarC(tabelaSimbolo)+"]";
        }
        return output;
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        output = "lw $s"+vars.get(2).size()+","+vars.get(3).indexOf(identificador)*4
                + "($s7)\n";
        vars.get(4).add(output);
        return vars;
    }

}
