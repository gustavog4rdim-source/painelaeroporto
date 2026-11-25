
package application;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println();
            System.out.println("=== PAINEL DE AEROPORTO ===");
            System.out.println("1 - Cadastrar voo");
            System.out.println("2 - Listar voos");
            System.out.println("3 - Atualizar status");
            System.out.println("4 - Filtrar voos");
            System.out.println("0 - Sair");
            System.out.println("Selecione qual opção você deseja:");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar voo");
                    break;

                case 2:
                    System.out.println("Listar voos");
                    break;

                case 3:
                    System.out.println("Atualizar status");
                    break;

                case 4:
                    System.out.println("Filtrar voos");
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        sc.close();
    }
}