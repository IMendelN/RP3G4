#include <stdint.h>
#include <stdlib.h>
#include <locale.h>
#include <stdio.h>
#include <string.h>
#define precoLux 500
#define precoInt 250
#define precoBas 150

typedef struct
{
    int cnh;
    char nome[30];
    int fidelidade;
    char placa[30];
} Cliente;

typedef struct
{
    char placa[30];
    int categoria;
    char modelo[30];
    char marca[30];
    int ano;
    int kmCarro;
    int disponibilidade;
} Carro;

int PainelInicial()
{

    printf("Bem vindo a locadora de carros da Unipampa.\n");
    printf("Selecione uma das opcoes abaixo para prosseguir.\n");
    printf("Opcao 1: Realizar uma locacao de veiculo. \n");
    printf("Opcao 2: Finalizar uma locacao de veiculo. \n");
    printf("Opcao 3: Consultar todos veiculos.\n");
    printf("Opcao 4: Fechar o programa.");
    return 1;
}

int FazerLoc(Cliente *clientes, Carro *carros)
{
    int cnh;
    char placa[7];
    printf("Informe a CNH do cliente:\n");
    scanf("%d", &cnh);
    int vetorCliente = buscaCliente(clientes, cnh);
    if (vetorCliente == -1)
    {
        return -1;
    }
    printf("Dados registrados.\n");

    printf("Veiculos disponiveis:\n");
    buscaVeiculoDisponivel(carros, 5);
    int vetorCarros;
    do
    {
        printf("Informe a placa do veiculo escolhido:\n");
        scanf("%s", placa);
        fflush(stdin);
        vetorCarros = buscaCarro(carros, placa);
        if (vetorCarros == -1)
        {
            return -1;
        }
        if (carros[vetorCarros].disponibilidade == 0)
        {
            printf("Veiculo indisponivel.\n");
            
        }
        else
            break;

    } while (placa != 0);
    fflush(stdin);
    carros[vetorCarros].disponibilidade = 0;
    strcpy(clientes[vetorCliente].placa, carros[vetorCarros].placa);
    printf("Veiculo locado.");
    GravaCarros(carros, 5);
    GravaClientes(clientes, 5);
    
}

int EncerraLoc(Cliente *clientes, Carro *carros)
{
    int cnh;
    do
    {
    
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

    GravaCarros(carros, 5);
    GravaClientes(clientes, 5);
    printf("Locacao encerrada.\n");
    } while (cnh != 0);
}

int buscaCliente(Cliente *clientes, int cnh)
{
    int tamanho = 5;
    int retorna;
    for (int i = 0; i < tamanho; i++)
    {
        if (clientes[i].cnh == cnh)
        {
            retorna = i;
            return retorna;
        }
    }
    printf("CNH nao encontrada.");
    return -1;
}

int buscaCarro(Carro *carros, char *placaCarro)
{
    int tamanho = 5;
    int retorna;
    for (int i = 0; i < tamanho; i++)
    {
        retorna = verifyString(carros[i].placa, placaCarro);
        if (retorna == 0)
        {
            return i;
        }
    }
    printf("Placa nao encontrada.");
    return -1;
}

int buscaVeiculoDisponivel(Carro *frota, int quantidadeCarro)
{
    int i = 0;
    printf("Veiculos disponiveis.\n");
    for (int i = 0; i < quantidadeCarro; i++)
    {
        if (frota[i].disponibilidade == 1)
        {
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
    for (int i = 0; i < quantidadeCarro; i++)
    {
        if (frota[i].disponibilidade == 0)
        {
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
int ArqCarros(Carro *frota)
{
    FILE *arq;
    int i = 0;

    arq = fopen("FrotaCarros.txt", "r");

    int numerodeLinha = 0;
    int r;

    if (arq == NULL)
    {
        printf("Problemas na Criação do arquivo\n");
        return 1;
    }
    fscanf(arq, "%d", &numerodeLinha);
    // printf("%d\n", numerodeLinha);
    do
    {
        r = fscanf(arq, "%29[^,],%d,%29[^,],%29[^,],%d,%d,%d,", frota[i].placa, &frota[i].categoria, frota[i].modelo, frota[i].marca, &frota[i].ano, &frota[i].kmCarro, &frota[i].disponibilidade);
        // printf("%s,%d,%s,%s,%d,%d,%d\n", frota[i].placa, frota[i].categoria, frota[i].modelo, frota[i].marca, frota[i].ano, frota[i].kmCarro, frota[i].disponibilidade);
        if (r == 7)
        {
            i++;
        }
        
        if (r != 7 && !feof(arq))
        {
            printf("Formato invalido");
            return 1;
        }

        if (ferror(arq))
        {
            printf("Erro no arquivo");
            return 1;
        }

    } while (!feof(arq));

    fclose(arq);
}

int ArqClientes(Cliente *clientes)
{
    FILE *arq;
    int i = 0;

    arq = fopen("Clientes.txt", "r");

    int numerodeLinha;
    int r;

    if (arq == NULL)
    {
        printf("Problemas na Criação do arquivo\n");
        return 1;
    }
    fscanf(arq, "%d", &numerodeLinha);
    // printf("%d", numerodeLinha);
    do
    {
        r = fscanf(arq, "%d,%29[^,],%d,%29[^,],", &clientes[i].cnh, clientes[i].nome, &clientes[i].fidelidade, clientes[i].placa);
        // printf("%d,%s,%d,%s\n",clientes[i].cnh, clientes[i].nome, clientes[i].fidelidade, clientes[i].placa);

        if (r != 4 && !feof(arq))
        {
            printf("Formato invalido");
            return 1;
        }

        if (ferror(arq))
        {
            printf("Erro no arquivo");
            return 1;
        }

        i++;

    } while (!feof(arq));

    fclose(arq);
}
void GravaCarros(Carro *frota, int quantidadeCarro)
{   
    fflush(stdin);
    FILE *gravCar;
    int i= 0;
    
    int quantidade = 5;
    gravCar = fopen("FrotaCarros.txt", "w");

    if (gravCar == NULL)
    {
        printf("Problemas na Criação do arquivo\n");
        return 1;
    }
    fflush(stdin);
    fprintf(gravCar, "%d\n", quantidadeCarro);
    for (int i = 0; i < 5; i++)
    {
        //printf("%s,%d,%s,%s,%d,%d,%d", frota[i].placa, frota[i].categoria, frota[i].modelo, frota[i].marca, frota[i].ano, frota[i].kmCarro, frota[i].disponibilidade);
    }
    do
    {
        fprintf(gravCar, "%s,%d,%s,%s,%d,%d,%d,\n", frota[i].placa, frota[i].categoria, frota[i].modelo, frota[i].marca, frota[i].ano, frota[i].kmCarro, frota[i].disponibilidade);
        i++;
        quantidade--;
    } while (quantidade != 0);

    fclose(gravCar);
}
int verifyString(char str[30], char str2[30])
{
    fflush(stdin);
    int flag = 0;

    for (int i = 0; str[i] != '\0' || str2[i] != '\0'; i++)
    {
        // Codigo destinado a remover o \n das strings vindas dos arquivos.
        if (str[i] == '\n')
        {
            memmove(str, str + 1, strlen(str));
        }
        if (str[i] != str2[i])
        {
            flag = 1;
            return 1;
        }
    }
    if (flag == 0)
        return 0;
}

void GravaClientes(Cliente *clientes, int quantidadedeClientes)
{   
    fflush(stdin);
    FILE *gravCli;
    int i= 0;
    
    int quantidade = 5;
    gravCli = fopen("Clientes.txt", "w");

    if (gravCli == NULL)
    {
        printf("Problemas na Criação do arquivo\n");
        return 1;
    }
    fflush(stdin);
    fprintf(gravCli, "%d\n", quantidadedeClientes);
    for (int i = 0; i < 5; i++)
    {
        //printf("%d,%s,%d,%s", clientes[i].cnh, clientes[i].nome, clientes[i].fidelidade, clientes[i].placa);
    }
    do
    {
        fprintf(gravCli, "%d,%s,%d,%s,\n", clientes[i].cnh, clientes[i].nome, clientes[i].fidelidade, clientes[i].placa);
        i++;
        quantidade--;
    } while (quantidade != 0);

    fclose(gravCli);
}

int main()
{
    Cliente *clientes;
    Carro *carros;
    
    carros = malloc(sizeof(Carro) * 100);
    clientes = malloc(sizeof(Cliente) * 100);
    ArqCarros(carros);
    ArqClientes(clientes);
    printf("\n");
    int op;
    /*for (int i = 0; i < 5; i++)
    {
        printf("%s,%d,%s,%s,%d,%d,%d", carros[i].placa, carros[i].categoria, carros[i].modelo, carros[i].marca, carros[i].ano, carros[i].kmCarro, carros[i].disponibilidade);
    }
    */
    do
    {
        PainelInicial();
        printf("\n");
        scanf("%d", &op);
        switch (op)
        {
        case 1:
            FazerLoc(clientes, carros);
        case 2:
            EncerraLoc(clientes, carros);
        case 3:
            buscaVeiculoDisponivel(carros, 5);
        case 4:
            break;
        default:
            printf("Escolha uma das opcoes");
        }
    } while (op != 4);
    return 0;
}