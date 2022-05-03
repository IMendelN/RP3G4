#include <stdint.h>
#include <stdlib.h>
#include <locale.h>
#include <stdio.h>

//Main---------------------------------------------------------------------------------------
void main(){
    setlocale(LC_ALL,"portuguese");
    printf("Bem vindo a locadora de carros da Unipampa.\n");
    printf("Selecione uma das opções abaixo para prosseguir.\n");
    printf("Opção 1: Realizar uma locação de veículo. \n");
    printf("Opção 2: Finalizar uma locação de veículo. \n");
    printf("opção 3: Gerar Relatório. \n");
    printf("opção 4: Cadastrar veículo. \n");
    printf("Opção 5: Finalizar o programa.\n");
    
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
        printf("Gerando relatório. ");
        relatorio();
        break;
    case 4 :
        printf("Cadastrar Veiculos");
        consultar_arquivo();
        break;
    case 5 :
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
 
 //LEITURA DE ARQUIVO-------------------------------------------------------------------------------------------------

int consultar_arquivo(void) {
    FILE *arq_carros;
    char palavra[50];
  //abrindo o arquivo
  arq_carros = fopen("arquivo.txt", "a");
     if (arq_carros==NULL)
    {
        printf("Erro ao abrir o arquivo");
  return 1;
    }else
  //pedir pro usuário digitar cadastrar 
  
    printf("escreva quantos carro voce quer cadastrar?\n");
    scanf("%s", palavra );
    printf("Qual a placa do carro?\n");
    scanf("%s", palavra);
    printf("Qual a marca do carro?\n");
    scanf("%s", palavra);
    printf("Qual o modelo do carro?\n");
    scanf("%s", palavra);
    printf("Qual o ano do carro?\n");
    scanf("%s", palavra);
    printf("Qual a quilometragem do carro?\n");
    scanf("%s", palavra);
    printf("Qual a categoria do carro?\n");
    scanf("%s", palavra);

    fprintf(arq_carros,"%s", palavra);

  // fechando arquivo
    fclose(arq_carros);
  
  //mensagem para o usuário
     printf("O arquivo foi salvo com sucesso!");
    return 0; 
 };
//ENCERRAR LEITURA DE ARQUIVO------------------------------------------------------------------------------------------------- 

//RELATÓRIO-------------------------------------------------------------------------------------------------------------------
void relatorio(){
    printf("\n O total de veiculos disponivel eh :\n");
    printf("O total de veiculos alugados eh: \n");

}
