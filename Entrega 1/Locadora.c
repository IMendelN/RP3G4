#include <stdint.h>
#include <stdlib.h>
#include <locale.h>
#include <stdio.h>
#define basico 150
#define intermedi 250
#define luxo 500

//DADOS LOCAÇÃO-------------------------------------------------------------------------------------------------------
    typedef struct {
    char nome[30];
    int cnh;
    char placa[7];
    int kmCat;
    int dias;
    }locacao;

//DADOS CARRO---------------------------------------------------------------------------------------------------------
    typedef struct {
        char modelo;
        char marca;
        char placa[7];
        int ano[4];
        int kmCarro;
        int categoria;
    }carro;
//ENCERRAR LOCAÇÃO----------------------------------------------------------------------------------------------------
        typedef struct {
            int kmEx;
            int fidelidade;
        }fLocacao;
//ENTREGA VEICULO
void EncerraLoc(locacao*frota, int cnh, carro*carros, carro*placaCli){
    int buscaCli = buscaCliente(frota , cnh);
    char buscaCar = buscaCarro(carros, placaCli);
    int posicaoCar;
    int valorCat = 0;
    fLocacao fim;
    
    
    for (int i = 0; i < 4; i++)
    {
       if (strcmp(frota[buscaCli].placa, carros[i].placa)== 0){
           posicaoCar = i;
        break;
    } 
    }
    if (carros[posicaoCar].categoria == 1)
    {
        valorCat = luxo*frota[buscaCli].dias;
    }
    else if (carros[posicaoCar].categoria == 2){
        valorCat = intermedi*frota[buscaCli].dias;
    }
    else if(carros[posicaoCar].categoria == 3){
        valorCat = basico*frota[buscaCli].dias;
    }
    fim.kmEx =carros[posicaoCar].kmCarro - frota[buscaCli].kmCat;
    valorCat += fim.kmEx;
    printf("O valor a ser pago é de: %d",valorCat);

}
//BUSCA CARRO---------------------------------------------------------------------------------------------------
int buscaCarro(carro*carros, char*placaCli){
    int tamanho = 4;
    int retorna;
    for (int i = 0; i < tamanho; i++){
        if (strcmp(carros[i].placa, placaCli)== 0){
            return i;
        }
    }
    printf("Placa nao encontrada.");
    return -1;
    
}
//BUSCA CLIENTE-------------------------------------------------------------------------------------------------
int buscaCliente(locacao*frota, int cnh){
 int tamanho = 5;
 int retorna;
 for (int i = 0; i <tamanho; i++){
     if (frota[i].cnh == cnh){
         retorna = 1;
     }
     if (retorna == 1){
         return i;
     }
     
 }
 printf("CNH nao encontrada.");
 return -1;
}


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
            printf("Informe a categoria do veículo que deseja locar:");
            printf("Opcao 1: Luxuoso\n Opcao 2: Intermediario\n Opcao 3: Básico");
            int catLoc;
            scanf("%d", &catLoc);
            switch (catLoc){
            case 1 :
                printf("Categoria Luxo.");
                break;
            case 2 :
                printf("Categoria Intermediaria.");
                break;
            case 3:
                printf("Categoria Básica.");
                break;
            default:
                printf("Digite um valor válido.");
                break;
            }
            break;
        default:
            printf("Digite um valor válido.");
            break;
        }
        break;
    case 2 : 
        printf("Finalizar locacao selecionado\n");
        printf("Informe o numero da CNH: \n");
        int nrCNH;
        scanf("%d", &nrCNH);
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

    

