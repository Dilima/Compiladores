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
public class ASTDeclaration extends ASTNo {

    private final String type;
    private final ASTConcatenaIdentificadores identificadores;

    public ASTDeclaration(ASTConcatenaIdentificadores identificadores, String type) {
        this.type = type;
        this.identificadores = identificadores;
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return "decl " + identificadores.compilar(tabelaSimbolo) + " : " + type;
    }

}
