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
public class ASTTexto extends ASTComando{
    private ASTExpressao expressao;
    private String string;
    private ASTTexto proximo;

    public ASTTexto(ASTExpressao expressao) {
        this.expressao = expressao;
        this.string = null;
    }

    public ASTTexto(String string) {
        this.string = string;
        this.expressao = null;
    }

    public ASTExpressao getExpressao() {
        return expressao;
    }

    public void setExpressao(ASTExpressao expressao) {
        this.expressao = expressao;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ASTTexto getProximo() {
        return proximo;
    }

    public void setProximo(ASTTexto proximo) {
        this.proximo = proximo;
    }
    
    public String compilarTexto(HashSet<String> tabelaSimbolo, String texto, String variaveis) throws Exception{
        String output = "";
        if(getString()!=null){
            texto += getString();
        }
        if(getExpressao()!=null){
            variaveis += "," + getExpressao().compilarC(tabelaSimbolo)+ "* 1.0";
            texto += " %f";
        }
        if(proximo!=null){
            output = proximo.compilarTexto(tabelaSimbolo, texto, variaveis);
        } else {
            if(texto != null)
                output = "\"" +texto+ "\\n\"";
            if(variaveis != "")
                output += variaveis ;
        }
       return output;
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output = "";
        if(getString()!=null){
            vars.get(0).add(getString());
            output = 
                    "la $a0 , msg" + (vars.get(0).size()-1) +
                    "\naddi $v0 , $zero ,4\n" +
                    "syscall\n";
            vars.get(4).add(output);
        }
        if(getExpressao()!= null){
            vars = getExpressao().compilarMIPS(vars);
            output = "addi $v0, $zero, 1\n"+
                    "syscall\n";
            vars.get(4).add(output);
        }
        if(proximo!=null)
            vars = proximo.compilarMIPS(vars);
        return vars;
    }
    
    
    
}
