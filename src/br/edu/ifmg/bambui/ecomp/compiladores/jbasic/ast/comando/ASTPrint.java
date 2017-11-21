package br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.jbasic.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Imprime expressão na saída padrão
 *
 * @author laerte
 */
public class ASTPrint extends ASTComando {

    private final ASTExpressao expressao;
    private final String mensagem;

    public ASTPrint(ASTExpressao expressao) {
        this.expressao = expressao;
        this.mensagem = null;
    }

    public ASTPrint(String mensagem) {
        this.expressao = null;
        this.mensagem = mensagem;
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        if (expressao != null) {
            expressao.interpretar(tabelaSimbolo);
            System.out.println(tabelaSimbolo.get("!"));
        } else {
            System.out.println(mensagem);
        }

        if (getProximo() != null) {
            getProximo().interpretar(tabelaSimbolo);
        }
    }

    @Override
    public String compilar(HashSet<String> tabelaSimbolo) throws Exception {
        String output = "System.out.println(";

        if (expressao != null) {
            output += expressao.compilar(tabelaSimbolo);
        } else {
            output += "\"" + mensagem + "\"";
        }

        output += ");";

        if (getProximo() != null) {
            output += getProximo().compilar(tabelaSimbolo);
        }

        return output;
    }

}
