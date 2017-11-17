package br.edu.ifmg.bambui.ecomp.compiladores.jbasic;

/**
 * Classe que representa o Token recuperado do analisador léxico
 * @author laerte
 */
public class Token extends ASTNo {
	private final int linha;
	private final String lexema;

	public Token() {
		this.linha = 0;
		this.lexema = null;
	}

	
    public Token(int linha, String lexema) {
        this.linha = linha;
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public String getLexema() {
        return lexema;
    }

	@Override
	public String toString() {
		return lexema+","+linha;
	}
	
	
}