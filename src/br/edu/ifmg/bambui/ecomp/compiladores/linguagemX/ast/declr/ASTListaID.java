/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Projeto
 */
public class ASTListaID  extends ASTNo{
    
    private ASTListaID proximo;
    private String identificador;

    public ASTListaID(String identificador) {
        this.identificador = identificador;
        this.proximo = null;
    }

    public ASTListaID getProximo() {
        return proximo;
    }

    public void setProximo(ASTListaID proximo) {
        this.proximo = proximo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return getIdentificador();
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        vars.get(3).add(identificador);
        return vars;
    }
    
    
    
}
