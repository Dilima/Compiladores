/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaveis;


import java.util.HashMap;
import java.util.HashSet;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ASTNo;
import declaracoes.ASTListaDeclaracoes;

/**
 *
 * @author Projeto
 */
public class ASTDeclaracao extends ASTListaDeclaracoes{
    private final int ids;
    private final String type;

    public ASTDeclaracao(int ids, String type) {
        this.ids = ids;
        this.type = type;
    }
    
    
}