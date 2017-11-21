package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr;

import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme Magno
 */
public class ASTDeclaraVariavel extends ASTConcatenaIdentificadores {

    private final String identificador;
    
    public ASTDeclaraVariavel(String identificador) {
        super(null, null);
        this.identificador = identificador;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        if (tabelaSimbolo.containsKey(identificador)) {
            throw new Exception("Variável já foi declarada anteriormente.");
        }

        tabelaSimbolo.put(identificador, tabelaSimbolo.get("!"));
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        if (tabelaSimbolo.contains(identificador)) {
            throw new Exception("Variável já foi declarada anteriormente.");
        }

        return identificador;
    }

}
