{ Algoritmo para receber 3 notas e devolver a média }
decl n1,n2,n3 : int;
decl media : real;

write "Informe a primeira nota:";
read n1;

write "Informe a segunda nota:";
read n2;

write "Informe a terceira nota:";
read n3;

media <- (n1+n2+n3) / 3.0;

if media >= 0 and media <= 10 then
	write "A média das 3 notas é ",media;
else
	write "As notas digitadas não dão a média necessária (0 à 10).";
fi;
