/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ASTNo;
import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ParserX;

/**
 *
 * @author Projeto
 */
public class ASTIds extends ASTNo{
    
    private ASTIds proximo;

    public void setProximo(ASTIds proximo) {
        this.proximo = proximo;
    }
    
    public ASTIds getProximo (){
        return this.proximo;
    }
    
}
