package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.comando;

import br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr.ASTExpressao;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Nó da árvore abstrata que representa uma atribuição
 *
 * @author laerte
 */
public class ASTAtribuicao extends ASTComando {

    private final ASTVar identificador;
    private final ASTExpressao expressao;

    public ASTAtribuicao(ASTVar identificador, ASTExpressao expressao) {
        this.identificador = identificador;
        this.expressao = expressao;
    }

    public ASTVar getIdentificador() {
        return identificador;
    }

    public ASTExpressao getExpressao() {
        return expressao;
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        String output;
        output = getIdentificador().compilarC(tabelaSimbolo) + " = " + getExpressao().compilarC(tabelaSimbolo) + ";";
        output += "\n";

        if (getProximo() != null) {
            output += getProximo().compilarC(tabelaSimbolo);
        }

        return output;
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        vars = expressao.compilarMIPS(vars);
        String output = "sw $s0," + vars.get(3).indexOf(identificador) * 4
                + "($s7)\n";
        vars.get(4).add(output);
        if (getProximo() != null) {
            vars = getProximo().compilarMIPS(vars);
        }
        return vars;
    }

}
