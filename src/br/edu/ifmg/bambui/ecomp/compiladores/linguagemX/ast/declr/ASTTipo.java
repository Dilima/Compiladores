/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import java.util.HashSet;

/**
 *
 * @author Projeto
 */
public class ASTTipo extends ASTNo{
    private String tipo;

    public ASTTipo(String token) {
        this.tipo = tipo;
    }

    public String getToken() {
        return tipo;
    }

    public void setToken(Token token) {
        this.tipo = tipo;
    }
    
    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return getToken();
    }
    
}
