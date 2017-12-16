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
public class ASTDeclaracao extends ASTNo{
    private ASTDeclaracao proximo;
    private ASTListaID listaID;
    private ASTTipoEspecifico tipoEspecifico;

    
    public ASTDeclaracao(ASTDeclaracao proximo) {
        this.proximo = proximo;     
    }

    public ASTDeclaracao(ASTListaID listaID, ASTTipoEspecifico tipoEspecifico) {
        this.proximo = null;
        this.listaID = listaID;
        this.tipoEspecifico = tipoEspecifico;
    }
    
    public void setProximo(ASTDeclaracao proximo) {
        this.proximo = proximo;
    }

    public ASTDeclaracao getProximo() {
        return proximo;
    }

    public ASTListaID getListaID() {
        return listaID;
    }

    public void setListaID(ASTListaID listaID) {
        this.listaID = listaID;
    }

    public ASTTipoEspecifico getTipoEspecifico() {
        return tipoEspecifico;
    }

    public void setTipoEspecifico(ASTTipoEspecifico tipoEspecifico) {
        this.tipoEspecifico = tipoEspecifico;
    }
    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = getTipoEspecifico().getTipo().compilar(tabelaSimbolo)+" ";
        while(getListaID().getProximo()!=null){
                
                 output+=getListaID().compilar(tabelaSimbolo);
                 if(getTipoEspecifico().getExpressao()!=null){
                     output+="["+getTipoEspecifico().getExpressao().compilar(tabelaSimbolo)+"]";
                 }
                 output+=",";
                 setListaID(getListaID().getProximo());   
         }
         output+=getListaID().compilar(tabelaSimbolo);
         if(getTipoEspecifico().getExpressao()!=null){
               output+="["+getTipoEspecifico().getExpressao().compilar(tabelaSimbolo)+"]";
         }
         output+=";\n";
         
         if(getProximo()!=null){
           output+=getProximo().compilar(tabelaSimbolo);
         }
         return output;
    }

}
