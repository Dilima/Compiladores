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
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        return getIdentificador();
    }
    
}
