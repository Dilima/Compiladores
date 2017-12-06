/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package variaveis;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.ASTExpressao;

/**
 *
 * @author Projeto
 */
public class ASTVar {
    private final String nome;
    private final ASTExpressao expressao;
    private final ASTExpressao tipo;

    public ASTVar(String nome, ASTExpressao expressao, ASTExpressao tipo) {
        this.nome = nome;
        this.expressao = expressao;
        this.tipo = tipo;
    }
    
    
}
