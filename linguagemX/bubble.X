{ Algoritmo para ordenação por bubble sort }
decl v : int[20];
decl swap, i, j : int;

{ Gera os números aleatórios }
for i from 1 to 20 do
	v[i] <- random();
done;

{ Bubble sort }
for i from 1 to 20 do
	for j from i+1 to 20 do
		if v[j] < v[i] then
			swap <- v[i];
			v[i] <- v[j];
			v[j] <- swap;
		fi;
	done;
done;
