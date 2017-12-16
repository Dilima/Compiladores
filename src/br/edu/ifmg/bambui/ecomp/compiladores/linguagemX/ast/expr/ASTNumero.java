/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashSet;

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
    
}
