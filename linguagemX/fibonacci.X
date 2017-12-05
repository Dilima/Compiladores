{ Sequencia de fibonacci }
decl t1, t2, temp, i, n : int;

write "Informe a posicao da serie de fibonacci:";
read n;

if n <= 0 then
	write "A posição na série deve ser >= 1";
else
	if n = 1 then
		write "O primeiro elemento é 0";
	else
		if n = 2 then
			write "O segundo elemento é 1";
		else
			t1 <- 0;
			t2 <- 1;
			
			for i from 2 to n do
				temp <- t1;
				t1 <- t2;
				t2 <- t1 + temp;
			done
			
			write "O elemento da posição ",n," da série é ",t2;
		fi
	fi
fi
