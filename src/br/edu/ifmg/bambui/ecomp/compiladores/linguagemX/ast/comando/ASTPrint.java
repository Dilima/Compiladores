package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Imprime expressão na saída padrão
 *
 * @author laerte
 */
public class ASTPrint extends ASTComando {

    private  ASTTexto texto;

    public ASTPrint(ASTTexto texto) {
        this.texto = texto;
    }

    public ASTTexto getTexto() {
        return texto;
    }

    public void setTexto(ASTTexto texto) {
        this.texto = texto;
    }
    
    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "printf(";
        
        while(getTexto().getProximo()!=null){
            if(getTexto().getString()!=null){
            output+="\""+getTexto().getString();
            }else{
                output+="\"";
            }
            if(getTexto().getExpressao()!=null){
            output+=" %d\","+getTexto().getExpressao().compilar(tabelaSimbolo);
            }else{
                output+="\"";
            }
            output+=",";
            setTexto(getTexto().getProximo());
        }
        
            if(getTexto().getString()!=null){
            output+="\""+getTexto().getString();
            }else{
                output+="\"";
            }
            if(getTexto().getExpressao()!=null){
            output+=" %d\","+getTexto().getExpressao().compilar(tabelaSimbolo);
            }else{
                output+="\"";
            }
        
        output+=");";output+="\n";
        
        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
