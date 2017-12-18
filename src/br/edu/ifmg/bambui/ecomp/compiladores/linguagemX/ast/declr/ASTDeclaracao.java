/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.declr;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Projeto
 */
public class ASTDeclaracao extends ASTNo {

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
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output="";
        while (getListaID() != null) {
            int aux = 1;
            if(getTipoEspecifico().getExpressao() != null)
                aux = Integer.parseInt(getTipoEspecifico().getExpressao());
            
            for (int i = 0; i < aux; i++) {
                vars = listaID.compilarMIPS(vars);
            }
            setListaID(listaID.getProximo());
        }
        if (getProximo() != null) {
            output += getProximo().compilarMIPS(vars);
        }
        return vars;
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output = getTipoEspecifico().getTipo().compilarC(tabelaSimbolo) + " ";
        while (getListaID().getProximo() != null) {

            output += getListaID().compilarC(tabelaSimbolo);
            if (getTipoEspecifico().getExpressao() != null) {
                output += "[" + getTipoEspecifico().getExpressao() + "]";
            }
            output += ",";
            setListaID(getListaID().getProximo());
        }
        output += getListaID().compilarC(tabelaSimbolo);
        if (getTipoEspecifico().getExpressao() != null) {
            output += "[" + getTipoEspecifico().getExpressao()+ "]";
        }
        output += ";\n";

        if (getProximo() != null) {
            output += getProximo().compilarC(tabelaSimbolo);
        }
        return output;
    }

}
