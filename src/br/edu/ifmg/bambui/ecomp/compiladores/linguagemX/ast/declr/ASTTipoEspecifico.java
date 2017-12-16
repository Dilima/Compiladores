/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.*;
import java.util.HashSet;

/**
 *
 * @author Projeto
 */
public class ASTTipoEspecifico extends ASTNo{
    private ASTTipo tipo;
    private ASTExpressao expressao;

    public ASTTipoEspecifico(ASTTipo tipo) {
        this.tipo = tipo;
        this.expressao = null;
       
    }

    public ASTTipoEspecifico(ASTTipo tipo,ASTExpressao expressao) {
        this.tipo = tipo;
        this.expressao =expressao;
    }

    public ASTTipo getTipo() {
        return tipo;
    }

    public void setTipo(ASTTipo tipo) {
        this.tipo = tipo;
    }

    public ASTExpressao getExpressao() {
        return expressao;
    }

    public void setExpressao(ASTExpressao expressao) {
        this.expressao = expressao;
    }
    
}
