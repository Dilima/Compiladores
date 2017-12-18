package br.edu.ifmg.bambui.ecomp.compiladores.linguagemX;

import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * Classe que inicializa o compilador
 * @author laerte
 */
public class Main {

	public static void main(String[] args) throws Exception {
            String opcao="";
		JFileChooser chooser = new JFileChooser();
		ParserX parser;
                try{
		parser = new ParserX(new FileReader(args[1]));
                if(args[0].compareTo("-C")==0){
                    parser.compilarC();
                }else{
                    parser.compilarMIPS();
                }  
            }catch(Exception e){
                 chooser.showOpenDialog(null);
                    parser = new ParserX(new FileReader(chooser.getSelectedFile()));
                    System.out.println("C ou M?");
                    Scanner s = new Scanner(System.in);
                    opcao = s.next();
                    if(opcao.compareTo("C")==0){
                        parser.compilarC();
                    }else{
                        parser.compilarMIPS();
                    }
                }
                finally{
                    System.out.println("OBRIGADO POR USAR O NOSSO COMPILADOR!");
                    System.out.println("DESENVOLVIDO POR:");
                    System.out.println("DIOGO LIMA");
                    System.out.println("GUSTAVO NUNES");
                    System.out.println("GUILHERME MAGNO");
                    System.out.println("YAGO SOARES");
                }
	}
}
