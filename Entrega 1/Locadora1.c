#include <stdint.h>
#include <stdlib.h>
#include <locale.h>


//DADOS LOCAÇÃO-------------------------------------------------------------------------------------------------------
    typedef struct {
    char nome;
    int cnh;
    int placa;
    int kmRodada;
    int numerodias;
    }locacao;
//DIARIA
    typedef struct {

    char categoria[1];
    double diaria;
    }diaria;
//DATA----------------------------------------------------------------------------------------------------------------
    typedef struct {
        int ano;
        short mes;
        short dia;
    }data;
//DADOS CARRO---------------------------------------------------------------------------------------------------------
    typedef struct {
        char modelo;
        char marca;
        char placa[7];
        int ano[4];
        int kmCarro;
        char categoria;
        double valorT;
        int disponibilidade;
    }carro;
//ENCERRAR LOCAÇÃO----------------------------------------------------------------------------------------------------

int buscaVeiculoDisponivel(carro *frota, int quantidadeCarro){
    int i=0;

    for(int i =0; i< quantidadeCarro; i++){
        if(frota[i].disponibilidade == 1){
            printf("%s\n", frota[i].marca);
            printf("%s\n", frota[i].modelo);
            printf("%s\n", frota[i].modelo);
            printf("%s\n", frota[i].modelo);
            printf("%s\n", frota[i].modelo);
            puts(" ");
        }
    }

    return i;
}
    
//Main---------------------------------------------------------------------------------------
void main(){
    carro *frota;
    
    frota = malloc(sizeof(carro)*200);
    
    setlocale(LC_ALL,"portuguese");
    printf("Bem vindo a locadora de carros da Unipampa.\n");
    printf("Selecione uma das opções abaixo para prosseguir.\n");
    printf("Opção 1: Realizar uma locação de veículo. \n");
    printf("Opção 2: Finalizar uma locação de veículo. \n");
    printf("Opção 3: Finalizar o programa.\n");
    
    int op;
    scanf("%d", &op);
    switch (op){
    case 1 : 
        printf("Locação selecionado.");
        break;
    case 2 :
        printf("Finalizar locação selecionado");
        break;
    case 3 :
        printf("Programa finalizado. ");
        break;
    default:
        printf("Digite um valor válido. ");
        break;
    }
    system("pause");
}
    
    
    


