{
	Algoritmo para mostrar quantos moedas são necessário para um troco
	usando moedas de 50, 25, 10, 5 e 1
}
decl total,qtde : int;

WRITE "informe o valor (em centavos) para o troco:";
REAd total;

while total > 0 do
	if total >= 50 then
		qtde <- total / 50;
		total <- total - (qtde * 50);
		write qtde," moedas de 50";
	else
		if total >= 25 then
			qtde <- total / 25;
			total <- total - (qtde * 25);
			write qtde," moedas de 25";
		else
			if total >= 10 then
				qtde <- total / 10;
				total <- total - (qtde * 10);
				write qtde," moedas de 10";
			else
				if total >= 5 then
					qtde <- total / 5;
					total <- total - (qtde * 5);
					write qtde," moedas de 5";
				else
					write total," moedas de 1";
					total <- 0;
				fi;
			fi;
		fi;
	fi;
done;