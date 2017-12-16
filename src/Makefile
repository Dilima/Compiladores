all:
	jflex Scanner.jflex -d br/edu/ifmg/bambui/ecomp/compiladores/jbasic
	yacc -Jclass=ParserX -Jpackage=br.edu.ifmg.bambui.ecomp.compiladores.linguagemX -Jthrows=Exception -Jsemantic=ASTNo -Jnoconstruct -Jnorun .\Compiladores\compiladores\src\br\edu\ifmg\bambui\ecomp\compiladores\jbasic\tentativaNovoParser.y
	mv ParserJBasic.java br/edu/ifmg/bambui/ecomp/compiladores/jbasic