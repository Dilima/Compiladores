/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashSet;

/**
 *
 * @author Guilherme Magno
 */
public class ASTAnd extends ASTExpressao{

    public ASTAnd(ASTExpressao esquerda, ASTExpressao direita) {
        super(esquerda, direita);
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return getEsquerda().compilar(tabelaSimbolo) + " && " + getDireita().compilar(tabelaSimbolo);
    }
}
