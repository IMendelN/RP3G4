#include <stdint.h>
#include <stdlib.h>
#include <locale.h>
#include <stdio.h>

//DADOS LOCAÇÃO-------------------------------------------------------------------------------------------------------
    typedef struct {
    char nome[30];
    int cnh;
    char placa[30];
    int kmCat;
    int dias;
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
        double valorCat;
    }carro;
//ENCERRAR LOCAÇÃO----------------------------------------------------------------------------------------------------
        typedef struct {
            double valorT;
            int kmEx;
            int fidelidade;
        }fLocacao;
//ARQUIVO LOCACAO------------------------------------------------------------------------------------------------
int ArqLoc(locacao*frota){
FILE *arq;
int i = 0;

arq = fopen("Locacao.txt", "r");

int numerodeLinha;
int r;

if (arq == NULL){
printf("Problemas na Criação do arquivo\n");
    return 1;
}
fscanf(arq, "%d", &numerodeLinha);
do{
 r= fscanf(arq, "%29[^,],%d,%29[^,],%d,%d,\n",frota[i].nome, &frota[i].cnh, frota[i].placa, &frota[i].kmCat, &frota[i].dias); 


if (r!=5 && !feof(arq)){
    printf("Formato invalido");
    return 1;
}

if (ferror(arq)){
    printf("Erro no arquivo");
    return 1;
}

i++;

} while (!feof(arq));
for (i = 0; i <= numerodeLinha; i++)
{
printf("%s,%d,%s,%d,%d,\n", frota[i].nome, frota[i].cnh, frota[i].placa, frota[i].kmCat, frota[i].dias);
}

    fclose(arq);
}
//Main-----------------------------------------------------------------------------------------------------------
int main(){
    locacao*frota;
    frota=malloc(sizeof(locacao)*100);
//PAINEL INICIAL------------------------------------------------------------------------------------------------
    
    printf("Bem vindo a locadora de carros da Unipampa.\n");
    printf("Selecione uma das opcoes abaixo para prosseguir.\n");
    printf("Opcao 1: Realizar uma locacao de veiculo. \n");
    printf("Opcao 2: Finalizar uma locacao de veiculo. \n");
    printf("Opcao 3: Finalizar o programa.\n");
    
    int op;
    scanf("%d", &op);
       
    switch (op){
    case 1 : 
        
        printf("Locacao selecionado.\n");
        printf("Qual acao deseja realizar?\n");
        printf("Opcao 1: Consultar Locacoes.\n");
        printf("Opcao 2: Realizar uma locacao.\n");
        int op2;
        scanf("%d", &op2);
        switch (op2){
        case 1:
            printf("Locacoes em Andamento:\n");
            ArqLoc(frota);
            break;
        case 2:
            printf("Realizar uma Locacao:\n");
            printf("Digite o codigo da sua CNH:\n");
            int novaCnh;
            scanf("%d", &novaCnh);
            printf("Digite seu nome:\n");
            char novoNome;
            scanf("%s", novoNome);
            printf("Cadastro Realizado com sucesso.");
            break;
        default:
            printf("Digite um valor válido.");
            break;
        }
        break;
    case 2 : 
        printf("Finalizar locacao selecionado\n");
        printf("Informe o nome do cliente: \n");
        int codLoc;
        scanf("%d", &codLoc);
        break;
    
    case 3 : 
        printf("Programa finalizado. ");
        break;
    
    default:
        printf("Digite um valor valido. ");
        break;
    
    
    system("pause");
    return 0;
    
    }
}

    

