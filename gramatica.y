
main : declaracoes comandos


declaracoes : TK_NEW_VAR listadeclaracaovariavel 
                        | 

listadeclaracaovariavel : variaveldec
                            | variaveldec ',' listadeclaracaovariavel

variaveldec : id ':' TYPE ';'

id : IDENTIFICADOR
           | IDENTIFICADOR ',' id


comandos : comando
                  | comando ';' comandos

comando : instacia
              | for
              | while
              | if
              | ler
              | escrever


instancia : variavel '=' expressao
            |variavel '<-' expressao

variavel ::= IDENTIFICADOR
             | IDENTIFICADOR '[' IDENTIFICADOR']'
             | IDENTIFICADOR '[' NUMERO ']'

for : TK_FOR IDENTIFICADOR TK_FROM NUMERO TK_TO NUMERO TK_DO comandos TK_DONE

while : TK_WHILE expr TK_DO comandos TK_DONE

if : TK_IF expr THEN comandos ELSE comandos TK_END_IF
      |TK_IF expr THEN comandos TK_END_IF

expr : exprsimples
               | exprsimples '==' exprsimples
               | exprsimples '<>' exprsimples
               | exprsimples '<' exprsimples
               | exprsimples '<=' exprsimples
               | exprsimples '>' exprsimples
               | exprsimples '>=' exprsimples

exprsimples ::= termo
                    | exprsimples '+' termo
                    | exprsimples '-' termo
                    | exprsimples OR termo

termo : fator
         | termo '*' fator
         | termo '/' fator
         | termo MOD fator
         | termo AND fator

fator : '(' expr ')'
           | '+' fator
           | '-' fator
           | NOT fator
           | variavel
           | NUMERO

ler : TK_READ IDENTIFICADOR
escrever : TK_PRINT STRING
