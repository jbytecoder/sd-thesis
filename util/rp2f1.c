#include <stdio.h>

int main(){
	double recall=0,precision=0;
	scanf("%lf %lf",&recall,&precision);
	printf("%lf",2*((recall*precision)/(recall+precision)));
	return 0;
}
