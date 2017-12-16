package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX;

import java.io.FileReader;
import javax.swing.JFileChooser;

/**
 * Classe que inicializa o compilador
 * @author laerte
 */
public class Main {

	public static void main(String[] args) throws Exception {
		JFileChooser chooser = new JFileChooser();
		ParserX parser;
		
		System.out.println("**************** JBASIC *****************");
		System.out.println("* EXEMPLO DA DISCIPLINA DE COMPILADORES *");
		System.out.println("* ESCOLHA O ARQUIVO COM O CODIGO FONTE  *");
		System.out.println("*****************************************");
		System.out.println("\n\n");
		
		chooser.showOpenDialog(null);
		parser = new ParserX(new FileReader(chooser.getSelectedFile()));
		
		parser.compilar();
	}
}
