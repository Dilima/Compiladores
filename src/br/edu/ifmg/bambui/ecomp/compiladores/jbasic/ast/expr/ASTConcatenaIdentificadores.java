/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ASTNo;
import java.util.HashSet;

/**
 *
 * @author Guilherme Magno
 */
public class ASTConcatenaIdentificadores extends ASTNo {

    private final ASTConcatenaIdentificadores esquerda, direita;

    public ASTConcatenaIdentificadores(ASTConcatenaIdentificadores esquerda, ASTConcatenaIdentificadores direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    protected ASTConcatenaIdentificadores getEsquerda() {
        return esquerda;
    }

    protected ASTConcatenaIdentificadores getDireita() {
        return direita;
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return getEsquerda().compilar(tabelaSimbolo) + "," + getDireita().toString();
    }    

}
