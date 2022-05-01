#include <stdint.h>
#include <stdlib.h>
#include <locale.h>

//Main---------------------------------------------------------------------------------------
void main(){
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
//DADOS LOCAÇÃO-------------------------------------------------------------------------------------------------------
    typedef struct locacao{
    char nome;
    int cnh;
    int placa;
    int kmRodada;
    int numerodias;
    };
//DIARIA
    typedef struct diaria{

    char categoria[1];
    double diaria;
    };
//DATA----------------------------------------------------------------------------------------------------------------
    typedef struct data{
        int ano;
        short mes;
        short dia;
    };
//DADOS CARRO---------------------------------------------------------------------------------------------------------
    typedef struct carro{
        char modelo;
        char marca;
        char placa[7];
        int ano[4];
        int kmCarro;
        char categoria;
        double valorT;
    };
//ENCERRAR LOCAÇÃO----------------------------------------------------------------------------------------------------
    
    

    

    
    
    


