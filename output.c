#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

int main(void){
int v[20];
int swap,i,j;
for(i=1;i<=20;i++){
v[i] = rand();

}
for(i=1;i<=20;i++){
for(j=i + 1;j<=20;j++){
if(v[j] < v[i]){
swap = v[i];
v[i] = v[j];
v[j] = swap;

}

}

}


}