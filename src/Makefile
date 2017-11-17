all:
	jflex Scanner.jflex -d br/edu/ifmg/bambui/ecomp/compiladores/jbasic
	byaccj -Jclass=ParserJBasic -Jpackage=br.edu.ifmg.bambui.ecomp.compiladores.jbasic -Jthrows=Exception -Jsemantic=ASTNo -Jnoconstruct -Jnorun Parser.y
	mv ParserJBasic.java br/edu/ifmg/bambui/ecomp/compiladores/jbasic