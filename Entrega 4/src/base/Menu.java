package base;

import utils.App;
import utils.enums.Color;

public class Menu {
    /**
     * Menu principal do programa.
     */
    public static void show() {
        App.printf(Color.PURPLE, "[MENU DE OPÇÕES]\n\n");
        App.printf("    1 - Listar Clientes\n");
        App.printf("    2 - Listar Cliente\n");
        App.printf("    3 - Listar Vendas\n");
        App.printf("    4 - Listar Clientes por Profissão\n");
        App.printf("    5 - Listar Valor Médio de Venda\n");
        App.printf("    6 - Alterar Informação de Cliente\n");
        App.printf("    7 - Listar Imobiliárias em Ordem Decrescente de Vendas\n");
        App.printf("    8 - Listar Vendedor por Valor de Vendas\n");
        App.printf("    0 - Sair\n");
        App.printf(Color.YELLOW, "\nDigite uma das opções acima: ");
    }

    /**
     * Profissões cadastradas.
     */
    public static void listCareers() {
        App.printf(Color.PURPLE, "[MENU DE PROFISSÃO]\n\n");
        App.printf("    1 - Veterinário\n");
        App.printf("    2 - Militar\n");
        App.printf("    3 - Médico\n");
        App.printf("    4 - Advogado\n");
        App.printf("    5 - Professor\n");
        App.printf(Color.YELLOW, "\nDigite uma das opções acima: ");
    }

    /**
     * Imobiliárias cadastradas.
     */
    public static void listAgencies() {
        App.printf(Color.PURPLE, "[MENU DE IMOBILIÁRIAS]\n\n");
        App.printf("    1 - Alegrete\n");
        App.printf("    2 - Baita Chão\n");
        App.printf("    3 - Ibirapuitã\n");
        App.printf(Color.YELLOW, "\nDigite uma das opções acima: ");
    }

    /**
     * Menu de opções de alteração de informações de cliente.
     */
    public static void changeClientMenu() {
        App.printf(Color.PURPLE, "\n[OPÇÕES DE ALTERAÇÃO]\n\n");
        App.printf("    1 - Alterar a idade\n");
        App.printf("    2 - Alterar a profissão\n");
        App.printf("    3 - Alterar ambos\n\n");
        App.printf(Color.YELLOW, "Digite uma das opções acima: ");
    }
}
