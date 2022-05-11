#include <stdint.h>
#include <stdlib.h>
#include <locale.h>
#include <stdio.h>
#include <string.h>
#define precoLux 500
#define precoInt 250
#define precoBas 150

typedef struct {
    int cnh;
    char nome[30];
    int fidelidade;
    char placa[30];
}Cliente;

typedef struct{
    char placa[30];
    int categoria;
    char modelo[30];
    char marca[30];
    int ano;
    int kmCarro;
    int disponibilidade;
}Carro;

int PainelInicial(){

    printf("Bem vindo a locadora de carros da Unipampa.\n");
    printf("Selecione uma das opcoes abaixo para prosseguir.\n");
    printf("Opcao 1: Realizar uma locacao de veiculo. \n");
    printf("Opcao 2: Finalizar uma locacao de veiculo. \n");
    printf("Opcao 3: Consultar todos veiculos.\n");
    printf("Opcao 4: Fechar o programa.");
    return 1;
}

int FazerLoc(Cliente*clientes, Carro*carros){
    int cnh;
    char placa[7];
    printf("Informe a CNH do cliente:\n");
    scanf("%d", &cnh);
    int vetorCliente = buscaCliente(clientes, cnh);
    if (vetorCliente == -1){
        return -1;
    }
    printf("Dados registrados.\n");

    printf("Veiculos disponiveis:\n");
    buscaVeiculoDisponivel(carros, 5);

    printf("Informe a placa do veiculo escolhido:\n");
    scanf("%s", placa);
    int vetorCarros = buscaCarro(carros, placa);
    if (vetorCarros == -1){
        return -1;
    }
    carros[vetorCarros].disponibilidade = 0;
    strcpy(clientes[vetorCliente].placa, carros[vetorCarros].placa);
    printf("Veiculo locado.");
}

int EncerraLoc(Cliente*clientes, Carro*carros){
    int cnh;
    printf("Informe a sua CNH: \n");
    scanf("%d", &cnh);
    int posCliente = buscaCliente(clientes, cnh);
    int posicaoCarro = buscaCarro(carros, clientes[posCliente].placa);
    if (posicaoCarro == -1)
    {
        printf("O cliente nao possui veiculos locados");
    }
    int dias;
    printf("Quantos dias o cliente ficou com o veículo?");
    scanf("%d", &dias);
    int kmAtual;
    printf("Qual a quilometragem atual do veiculo?");
    scanf("%d", &kmAtual);
    
}

int buscaCliente(Cliente*clientes, int cnh){
    int tamanho = 5;
    int retorna;
     for (int i = 0; i <tamanho; i++){
        if (clientes[i].cnh == cnh){
            retorna = i;
            return retorna;
        }  
    }
printf("CNH nao encontrada.");
return -1;
}

int buscaCarro(Carro*carros, char*placaCarro){
    int tamanho = 4;
    int retorna;
    for (int i = 0; i < tamanho; i++){
        if (strcmp(carros[i].placa, placaCarro)== 0){
            return i;
        }
    }
    printf("Placa nao encontrada.");
    return -1;
    
}

int buscaVeiculoDisponivel(Carro*frota, int quantidadeCarro){
    int i=0;
    printf("Veiculos disponiveis.\n");
    for(int i =0; i< quantidadeCarro; i++){
        if(frota[i].disponibilidade == 1){
            printf("%s ", frota[i].placa);
            printf("%d ", frota[i].categoria);
            printf("%s ", frota[i].modelo);
            printf("%s ", frota[i].marca);
            printf("%d ", frota[i].ano);
            printf("%d ", frota[i].kmCarro);
            printf("%d\n", frota[i].disponibilidade);
            puts(" ");
        }
        
    }
    printf("Veiculos indisponiveis.\n");
    for(int i =0; i< quantidadeCarro; i++){
        if(frota[i].disponibilidade == 0){
            printf("%s ", frota[i].placa);
            printf("%d ", frota[i].categoria);
            printf("%s ", frota[i].modelo);
            printf("%s ", frota[i].marca);
            printf("%d ", frota[i].ano);
            printf("%d ", frota[i].kmCarro);
            printf("%d\n", frota[i].disponibilidade);
            puts(" ");
        }
        
    }

    return i;
}
int ArqCarros(Carro*frota){
FILE *arq;
int i = 0;

arq = fopen("FrotaCarros.txt", "r");

int numerodeLinha =0;
int r;

if (arq == NULL){
printf("Problemas na Criação do arquivo\n");
    return 1;
}
fscanf(arq, "%d", &numerodeLinha);
//printf("%d", numerodeLinha);
do{
 r= fscanf(arq, "%29[^,],%d,%29[^,],%29[^,],%d,%d,%d,",frota[i].placa, &frota[i].categoria, frota[i].modelo, frota[i].marca, &frota[i].ano, &frota[i].kmCarro, &frota[i].disponibilidade); 
//printf("%s,%d,%s,%s,%d,%d,%d\n", frota[i].placa, frota[i].categoria, frota[i].modelo, frota[i].marca, frota[i].ano, frota[i].kmCarro, frota[i].disponibilidade);

if (r!=7 && !feof(arq)){
    printf("Formato invalido");
    return 1;
}

if (ferror(arq)){
    printf("Erro no arquivo");
    return 1;
}

i++;

} while (!feof(arq));

    fclose(arq);
}

int ArqClientes(Cliente*clientes){
FILE *arq;
int i = 0;

arq = fopen("Clientes.txt", "r");

int numerodeLinha;
int r;

if (arq == NULL){
printf("Problemas na Criação do arquivo\n");
    return 1;
}
fscanf(arq, "%d", &numerodeLinha);
//printf("%d", numerodeLinha);
do{
 r= fscanf(arq, "%d,%29[^,],%d,%29[^,],",&clientes[i].cnh, clientes[i].nome, &clientes[i].fidelidade, clientes[i].placa);
 //printf("%d,%s,%d,%s\n",clientes[i].cnh, clientes[i].nome, clientes[i].fidelidade, clientes[i].placa);


if (r!=4 && !feof(arq)){
    printf("Formato invalido");
    return 1;
}

if (ferror(arq)){
    printf("Erro no arquivo");
    return 1;
}

i++;

} while (!feof(arq));

    fclose(arq);
}



int main(){
    Cliente*clientes;
    Carro*carros;
    carros=malloc(sizeof(Carro)*100);
    clientes=malloc(sizeof(Cliente)*100);
    ArqCarros(carros);
    ArqClientes(clientes);
    printf("\n");
    int op;
    PainelInicial();
    scanf("%d", &op);
    do{
        
        switch (op)
        {
        case 1:
            FazerLoc(clientes, carros);
            break;
        case 2:
            EncerraLoc(clientes, carros);
        case 3:
    
        default:
            printf("Escolha uma das opcoes");
            break;
        }
    } while (op != 3);
 return 0;   
}



