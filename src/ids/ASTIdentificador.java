/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ASTNo;
/**
 *
 * @author Projeto
 */
public class ASTIdentificador extends ASTNo {
    
    private final String nome;
    private ASTIdentificador proximo;

    public void setProximo(ASTIdentificador proximo) {
        this.proximo = proximo;
    }
    
    public ASTIdentificador getProximo (){
        return this.proximo;
    }
    public ASTIdentificador(String nome){
        this.nome = nome;
    }
    
}
