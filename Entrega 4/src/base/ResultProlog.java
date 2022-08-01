package base;

import java.util.Scanner;

import utils.App;
import utils.Prolog;
import utils.enums.Color;

public class ResultProlog {
    /**
     * 2.1: Listar o código de cada cliente da imobiliária.
     */
    public static void listClients() {
        Prolog.consult("list_clients");
    }

    /**
     * 2.2: Listar os dados de um determinado cliente.
     * @param input Scanner
     */
    public static void listClient(Scanner input) {
        App.printf(Color.YELLOW, "Digite o código do cliente: ");
        int code = input.nextInt();
        boolean hasSolution = Prolog.consult(String.format("list_client(%d)", code));
        
        if (!hasSolution) {
            App.printf(Color.RED, "\nCliente não encontrado na base de dados.\n");
        }
    }
/**
     * 2.2 (para 2.6): Listar dados de um determinado cliente.
     * @param code o código do cliente
     */
    public static boolean listClient(int code) {
        boolean hasSolution = Prolog.consult(String.format("list_client(%d)", code));
        
        if (!hasSolution) {
            App.printf(Color.RED, "\nCliente não encontrado na base de dados.\n");
            return false;
        }
        return true;
    }
    
    /**
     * 2.3: Listar todos os tipos de imóveis vendidos por uma determinada imobiliária.
     */
    public static void listTypesByAgency(Scanner input) {
        App.printf(Color.YELLOW, "Digite o nome da imobiliária: ");
        String name = input.nextLine();
        boolean hasSolution = Prolog.consult(String.format("list_types_by_agency('%s')", name));

        if (!hasSolution) {
            App.printf(Color.RED, "\nImobiliária não encontrada na base de dados.\n");
        }
    }

    /**
     * 2.4: Listar todos os clientes de uma determinada profissão.
     */
    public static void listClientsByCareer(Scanner input) {
        App.printf(Color.YELLOW, "Digite a profissão do cliente: ");
        String career = input.next();
        boolean hasSolution = Prolog.consult(String.format("list_client_by_career('%s')", career));

        if (!hasSolution) {
            App.printf(Color.RED, "\nProfissão não encontrada na base de dados.\n");
        }
    }

    /**
     * 2.5: Listar o valor médio de vendas de imóveis de uma determinada imobiliária.
     */
    public static void listAverageAgencies() {
        Prolog.consult("list_average_agencies");
    }

    /**
     * 2.6: Alterar informação de um determinado cliente. [TO DO].
     */
    public static void alterClient(Scanner input) {
        App.printf(Color.YELLOW, "Digite o código do cliente a ser alterado: ");
        int code = input.nextInt();

        /* Se não encontrar o cliente. */
                  
        if (!listClient(code)) {
            return;
        }

        App.printf(Color.YELLOW, "\nDigite a nova idade do cliente: ");
        int newAge = input.nextInt();
        input.nextLine();

        App.printf(Color.YELLOW, "Digite a nova profissão do cliente: ");
        String newCareer = input.next();

        boolean hasSolution = Prolog.consult(String.format("change_client(%d, %d,'%s')", code, newAge, newCareer));

        if (!hasSolution) {
            App.printf(Color.RED, "\nCliente não pôde ser alterado.\n");
        } else {
            App.printf(Color.GREEN, "\nCliente alterado com sucesso.\n");
        }
    }

    /**
     * 2.7: Listar imobiliárias com maior valor de vendas em ordem decrescente.
     */
    public static void listAgencyByValueSold() {
        Prolog.consult("list_agency_by_value_sold");
    }

    /**
     * 2.8: Listar vendedores com maior valor de vendas em ordem decrescente.
     */
    public static void listSellersByValueSold() {
        Prolog.consult("list_sellers_by_value_sold");
    }
}
