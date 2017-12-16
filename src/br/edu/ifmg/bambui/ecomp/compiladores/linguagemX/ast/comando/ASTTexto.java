/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import java.util.HashSet;
/**
 *
 * @author Projeto
 */
public class ASTTexto extends ASTNo{
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
    
}
