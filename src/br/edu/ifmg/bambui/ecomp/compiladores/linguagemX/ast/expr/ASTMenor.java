package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX.ast.expr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Representação da comparação de 'menor que'
 *
 * @author laerte
 */
public class ASTMenor extends ASTExpressao {

    public ASTMenor(ASTExpressao esquerda, ASTExpressao direita) {
        super(esquerda, direita);
    }

    @Override
    public void interpretar(HashMap<String, Object> tabelaSimbolo) throws Exception {
        double t1, t2;

        getEsquerda().interpretar(tabelaSimbolo);
        t1 = (Double) tabelaSimbolo.get("!");
        getDireita().interpretar(tabelaSimbolo);
        t2 = (Double) tabelaSimbolo.get("!");
        tabelaSimbolo.put("!", t1 < t2);
    }

    @Override
    public String compilarC(HashSet<String> tabelaSimbolo) throws Exception {
        return getEsquerda().compilarC(tabelaSimbolo) + " < " + getDireita().compilarC(tabelaSimbolo);
    }

    @Override
    public List<LinkedList<String>> compilarMIPS(List<LinkedList<String>> vars) throws Exception {
        String output;
        vars = getEsquerda().compilarMIPS(vars);
        vars.get(2).add("1");
        vars = getDireita().compilarMIPS(vars);
        vars.get(2).remove();

        output = "slt $s" + vars.get(2).size()
                + ", $s" + (vars.get(2).size())
                + ", $s" + (vars.get(2).size()+1)
                + "\n";
        output += "add $a0, $zero, $s0\n";
        vars.get(4).add(output);
        return vars;
    }
}
