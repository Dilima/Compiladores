/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr.*;
import java.util.HashSet;

/**
 *
 * @author Projeto
 */
public class ASTProgram extends ASTNo{
    private ASTDeclaracao declr;
    private ASTComando comando;

    public ASTProgram(ASTDeclaracao declr, ASTComando comando) {
        this.declr = declr;
        this.comando = comando;
    }

    public ASTProgram(ASTDeclaracao declr) {
        this.declr = declr;
        this.comando = null;
    }

    public ASTProgram() {
    }

    public ASTDeclaracao getDeclr() {
        return declr;
    }

    public void setDeclr(ASTDeclaracao declr) {
        this.declr = declr;
    }

    public ASTComando getComando() {
        return comando;
    }

    public void setComando(ASTComando comando) {
        this.comando = comando;
    }
    
    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output="";
        if(getDeclr()!= null){
            output += getDeclr().compilar(tabelaSimbolo);
        }
        
        if(getComando()!=null){
            output +=getComando().compilar(tabelaSimbolo);
        }
        return output;
    }
    
}
