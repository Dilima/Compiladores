/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package declaracoes;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ASTNo;

/**
 *
 * @author Projeto
 */
public class ASTListaDeclaracoes extends ASTNo{
    
    private ASTListaDeclaracoes proximo;

    public void ASTDeclaracoes(ASTListaDeclaracoes proximo) {
        this.proximo = proximo;
    }

    public ASTListaDeclaracoes getProximo() {
        return proximo;
    }

    public void setProximo(ASTListaDeclaracoes proximo) {
        this.proximo = proximo;
    }
}
