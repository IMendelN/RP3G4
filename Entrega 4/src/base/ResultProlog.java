package base;

import java.util.InputMismatchException;
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
        boolean error;

        do {
            error = false;
            
            try {
                App.printf(Color.YELLOW, "Digite o código do cliente: ");
                int code = input.nextInt();
                boolean hasSolution = Prolog.consult(String.format("list_client(%d)", code));
                
                if (!hasSolution) {
                    App.printf(Color.RED, "\nCliente não encontrado na base de dados.\n");
                }
            } catch (InputMismatchException e) {
                App.clearScreen();
                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n\n");
                Menu.show();
                App.println("2");
                error = true;
                input.next();
            }
        } while (error);
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
        App.clearScreen();
        boolean error;

        do {
            error = false;

            try {
                Menu.listAgencies();
                int option = input.nextInt();

                switch (option) {
                    case 1 -> {
                        Prolog.consult("list_types_by_agency('Alegrete')");
                    }
                    case 2 -> {
                        Prolog.consult("list_types_by_agency('Baita Chao')"); 
                    }
                    case 3 -> {
                        Prolog.consult("list_types_by_agency('Ibirapuita')");
                    }
                    default -> {
                        App.clearScreen();
                        App.printf(Color.RED, "Por favor, escolha uma opção válida.\n\n");
                        error = true;
                    }
                }
            } catch (InputMismatchException e) {
                App.clearScreen();
                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n\n");
                error = true;
                input.next();
            }
        } while (error);
    }

    /**
     * 2.4: Listar todos os clientes de uma determinada profissão.
     */
    public static void listClientsByCareer(Scanner input) {
        App.clearScreen();
        boolean error;

        do {
            error = false;

            try {
                Menu.listCareers();
                int option = input.nextInt();

                switch (option) {
                    case 1 -> {
                        Prolog.consult("list_clients_by_career('Veterinario')");
                    }
                    case 2 -> {
                        Prolog.consult("list_clients_by_career('Militar')");
                    }
                    case 3 -> {
                        Prolog.consult("list_clients_by_career('Medico')");
                    }
                    case 4 -> {
                        Prolog.consult("list_clients_by_career('Advogado')");
                    }
                    case 5 -> {
                        Prolog.consult("list_clients_by_career('Professor')");
                    }
                    default -> {
                        App.clearScreen();
                        App.printf(Color.RED, "Por favor, escolha uma opção válida.\n\n");
                        error = true;
                    }
                }
            } catch (InputMismatchException e) {
                App.clearScreen();
                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n\n");
                error = true;
                input.next();
            }
        } while (error);
    }

    /**
     * 2.5: Listar o valor médio de vendas de imóveis de uma determinada imobiliária.
     */
    public static void listAverageAgencies() {
        Prolog.consult("list_average_agencies");
    }

    private static void showTryCatchError(int code) {
        App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n");
        Prolog._consult(String.format("list_client(%d)", code));
    }

    /**
     * 2.6: Alterar informação de um determinado cliente. [OK].
     */
    public static void alterClient(Scanner input) {
        boolean error;
        boolean invalidOption;
        boolean hasSolution = false;

        do {
            error = false;
            
            try {
                App.printf(Color.YELLOW, "Digite o código do cliente a ser alterado: ");
                int code = input.nextInt();
                int option = 0;

                /* Se não encontrar o cliente. */
                if (!listClient(code)) {
                    return;
                }

                do {
                    invalidOption = error = false;

                    try {
                        Menu.changeClientMenu();
                        option = input.nextInt();

                        if (option <= 0 || option > 3) {
                            App.clearScreen();
                            App.printf(Color.RED, "Por favor, escolha uma opção válida.\n");
                            Prolog._consult(String.format("list_client(%d)", code));
                            error = true;
                        }
                    } catch (InputMismatchException e) {
                        App.clearScreen();
                        showTryCatchError(code);
                        invalidOption = true;
                        input.next();
                    }
                } while (invalidOption || error);

                switch (option) {
                    case 1 -> {
                        do {
                            error = false;

                            try {
                                App.printf(Color.YELLOW, "Digite a nova idade: ");
                                int age = input.nextInt();

                                if (age < 0) {
                                    App.clearScreen();
                                    App.printf(Color.RED, "Digite uma idade válida.\n");
                                    Prolog._consult(String.format("list_client(%d)", code));
                                    Menu.changeClientMenu();
                                    App.println("1");
                                    error = true;
                                } else {
                                    hasSolution = Prolog.consult(String.format("change_age_client(%d, %d)", code, age));
                                }
                            } catch (InputMismatchException e) {
                                App.clearScreen();
                                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n");
                                Prolog._consult(String.format("list_client(%d)", code));
                                Menu.changeClientMenu();
                                App.println("1");
                                error = true;
                                input.next();
                            }
                        } while (error);
                    }
                    case 2 -> {
                        App.clearScreen();
                        Prolog._consult(String.format("list_client(%d)", code));
                        App.println("");

                        do {
                            error = false;

                            try {
                                Menu.listCareers();
                                int careers = input.nextInt();

                                switch (careers) {
                                    case 1 -> {
                                        hasSolution = Prolog.consult(String.format("change_career_client(%d, 'Veterinario')", code));
                                    }
                                    case 2 -> {
                                        hasSolution = Prolog.consult(String.format("change_career_client(%d, 'Militar')", code));
                                    }
                                    case 3 -> {
                                        hasSolution = Prolog.consult(String.format("change_career_client(%d, 'Medico')", code));
                                    }
                                    case 4 -> {
                                        hasSolution = Prolog.consult(String.format("change_career_client(%d, 'Advogado')", code));
                                    }
                                    case 5 -> {
                                        hasSolution = Prolog.consult(String.format("change_career_client(%d, 'Professor')", code));
                                    }
                                    default -> {
                                        App.clearScreen();
                                        App.printf(Color.RED, "Por favor, escolha uma opção válida.\n");
                                        Prolog._consult(String.format("list_client(%d)", code));
                                        App.println("");
                                        error = true;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                App.clearScreen();
                                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n");
                                Prolog._consult(String.format("list_client(%d)", code));
                                App.println("");
                                error = true;
                                input.next();
                            }
                        } while (error);
                    }
                    case 3 -> {
                        int age = 0;
                        String career = "";

                        do {
                            error = false;

                            try {
                                App.printf(Color.YELLOW, "Digite a nova idade: ");
                                age = input.nextInt();

                                if (age < 0) {
                                    App.clearScreen();
                                    App.printf(Color.RED, "Digite uma idade válida.\n");
                                    Prolog._consult(String.format("list_client(%d)", code));
                                    Menu.changeClientMenu();
                                    App.println("3");
                                    error = true;
                                }
                            } catch (InputMismatchException e) {
                                App.clearScreen();
                                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n");
                                Prolog._consult(String.format("list_client(%d)", code));
                                Menu.changeClientMenu();
                                App.println("3");
                                error = true;
                                input.next();
                            }
                        } while (error);

                        App.println("");
                        do {
                            error = false;

                            try {
                                Menu.listCareers();
                                int careers = input.nextInt();

                                switch (careers) {
                                    case 1 -> {
                                        career = "'Veterinario'";
                                    }
                                    case 2 -> {
                                        career = "'Militar'";                                        
                                    }
                                    case 3 -> {
                                        career = "'Medico'";                                        
                                    }
                                    case 4 -> {
                                        career = "'Advogado'";
                                    }
                                    case 5 -> {
                                        career = "'Professor'";                                       
                                    }
                                    default -> {
                                        App.clearScreen();
                                        App.printf(Color.RED, "Por favor, escolha uma opção válida.\n");
                                        Prolog._consult(String.format("list_client(%d)", code));
                                        Menu.changeClientMenu();
                                        App.println("3");
                                        App.printf(Color.YELLOW, "Digite a nova idade: ");
                                        App.printf("%d\n\n", age);
                                        error = true;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                App.clearScreen();
                                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n");
                                Prolog._consult(String.format("list_client(%d)", code));
                                Menu.changeClientMenu();
                                App.println("3");
                                App.printf(Color.YELLOW, "Digite a nova idade: ");
                                App.printf("%d\n\n", age);
                                error = true;
                                input.next();
                            }
                        } while (error);

                        App.clearScreen();
                        hasSolution = Prolog.consult(String.format("change_client(%d, %d, %s)", code, age, career));
                    }
                    default -> {
                        App.clearScreen();
                        App.printf(Color.RED, "Por favor, escolha uma opção válida.\n\n");
                        error = true;
                    }
                }

                if (!hasSolution) {
                    App.printf(Color.RED, "\nCliente não pôde ser alterado.\n");
                } else {
                    App.printf(Color.GREEN, "\nCliente alterado com sucesso.\n");
                }
            } catch (InputMismatchException e) {
                App.clearScreen();
                App.printf(Color.RED, "Por favor, verifique o tipo de dado inserido.\n\n");
                Menu.show();
                App.println("6");
                error = true;
                input.next();
            }
        } while (error);
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
