{ Algoritmo de teste de iteração }
DeCL A, B : int;
dECL maximo : bool;

maximo = false;
writE "Informe o valor de A:";
read a;

write "Informe o valor de B (maior que A):";
read b;

while not maximo do
	A <- A + 2;
	A <- B + 1;
	Write "A: ",A," B: ",B;
	maximo <- A >= B;
done;
