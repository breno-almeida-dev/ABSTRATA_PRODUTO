package br.com.abstrata_produto.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.abstrata_produto.conexao.ConexaoBD;
import br.com.abstrata_produto.produto.ProdutoAlimenticio;
import br.com.abstrata_produto.produto.ProdutoVestuario;

/*
 * A classe Main abriga o terminal CLI que através de loops "while" e
 * blocos "switch-case" disponibilizam para o usuário a navegação entre os
 * Menus de cada método CRUD e de cada comando SQL de cada produto.
 */
public class Main {
    public static void main(String[] args) {
    	/*
    	 * Faz a tentativa de conexão com o banco de dados e dentro
    	 * da condição "if" verifica se a conexão foi estabelecida para
    	 * então iniciar o Menu ao usuário.
    	 */
        Connection conexao = ConexaoBD.conectar();
        if (conexao != null) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    System.out.println("\n=== Menu Principal ===");
                    System.out.println("1. Inserir Produto");
                    System.out.println("2. Atualizar Produto");
                    System.out.println("3. Deletar Produto");
                    System.out.println("4. Ler Registro de Produto");
                    System.out.println("0. Sair");
                    System.out.print("Escolha uma opção: ");

                    int opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    /*
                     * Através do número da opção selecionada pelo usuário, o 
                     * "switch-case" faz o direcionamento para o método CRUD.
                     */
                    switch (opcao) {
                        case 1: // Inserir
                            while (true) {
                                /*
                                 * Caso seja selecionado para inrerir um produto, um menu de
                                 * inserir cada tipo de produto é disponibilizado ao usuário
                                 * para que ele escolha qual deseja inserir no banco de dados. 
                                 */
                                System.out.println("\n=== Menu Inserir Produto ===");
                                System.out.println("1. Inserir Produto Alimentício");
                                System.out.println("2. Inserir Produto Vestuário");
                                System.out.println("0. Voltar ao menu anterior.");
                                System.out.print("Escolha uma opção: ");
                                int opcaoInsert = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer

                                /*
                                 * Após escolher qual produto ele deseja inserir, este bloco
                                 * "switch-case" faz a chamada do método estático específico
                                 * de cada produto. 
                                 */
                                switch (opcaoInsert) {
                                    case 1:
                                        ProdutoAlimenticio.salvarProduto(scanner, conexao);
                                        break;
                                    case 2:
                                    	ProdutoVestuario.salvarProduto(scanner, conexao);
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                }
                                if (opcaoInsert == 0) break; // Sai do loop de inserção
                            }
                            break;
                        case 2: // Atualizar
                            while (true) {
                                /*
                                 * Caso seja selecionado para atualizar um produto, um menu de
                                 * atualizar cada tipo de produto é disponibilizado ao usuário
                                 * para que ele escolha qual deseja atualizar no banco de dados. 
                                 */
                                System.out.println("\n=== Menu Atualizar Produto ===");
                                System.out.println("1. Atualizar Produto Alimentício");
                                System.out.println("2. Atualizar Produto Vestuário");
                                System.out.println("0. Voltar ao menu anterior.");
                                System.out.print("Escolha uma opção: ");
                                int opcaoAtualizar = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer

                                /*
                                 * Após escolher qual produto ele deseja atualizar, este bloco
                                 * "switch-case" faz a chamada do método estático específico
                                 * de cada produto. 
                                 */
                                switch (opcaoAtualizar) {
                                    case 1:
                                    	ProdutoAlimenticio.atualizarProduto(scanner, conexao);
                                        break;
                                    case 2:
                                    	ProdutoVestuario.atualizarProduto(scanner, conexao);
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                }
                                if (opcaoAtualizar == 0) break; // Sai do loop de atualização
                            }
                            break;
                        case 3: // Deletar
                            while (true) {
                                /*
                                 * Caso seja selecionado para deletar um produto, um menu de
                                 * deletar cada tipo de produto é disponibilizado ao usuário
                                 * para que ele escolha qual deseja deletar no banco de dados. 
                                 */
                                System.out.println("\n=== Menu Deletar Produto ===");
                                System.out.println("1. Deletar Produto Alimentício");
                                System.out.println("2. Deletar Produto Vestuário");
                                System.out.println("0. Voltar ao menu anterior.");
                                System.out.print("Escolha uma opção: ");
                                int opcaoDeletar = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer

                                /*
                                 * Após escolher qual produto ele deseja deletar, este bloco
                                 * "switch-case" faz a chamada do método estático específico
                                 * de cada produto.
                                 */
                                switch (opcaoDeletar) {
                                    case 1:
                                    	ProdutoAlimenticio.deletarProduto(scanner, conexao);
                                        break;
                                    case 2:
                                    	ProdutoVestuario.deletarProduto(scanner, conexao);
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                }
                                if (opcaoDeletar == 0) break; // Sai do loop de deletação
                            }
                            break;
                        case 4: // Ler
                            while (true) {
                                /*
                                 * Caso seja selecionado para ler um produto, um menu de
                                 * ler cada tipo de produto é disponibilizado ao usuário
                                 * para que ele escolha qual deseja ler no banco de dados. 
                                 */
                                System.out.println("\n=== Menu Ler Registro de Produto ===");
                                System.out.println("1. Ler Registro de Produto Alimentício");
                                System.out.println("2. Ler Registro de Produto Vestuário");
                                System.out.println("0. Voltar ao menu anterior.");
                                System.out.print("Escolha uma opção: ");
                                int opcaoLerRegistro = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer

                                /*
                                 * Após escolher qual produto ele deseja ler, este bloco
                                 * "switch-case" faz a chamada do método estático específico
                                 * de cada produto. 
                                 */
                                switch (opcaoLerRegistro) {
                                    case 1:
                                    	ProdutoAlimenticio.lerProduto(conexao);
                                        break;
                                    case 2:
                                    	ProdutoVestuario.lerProduto(conexao);
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                }
                                if (opcaoLerRegistro == 0) break; // Sai do loop de leitura
                            }
                            break;
                        case 0:
                        	/*
                        	 * Caso o usuário opte por sair do Menu, a conexão é
                        	 * encerrada, o scanner é fechado e assim finaliza o loop.
                        	 */
                            System.out.println("Saindo...");
                            try {
                                conexao.close(); // Fecha a conexão com o banco de dados
                            } catch (SQLException e) {
                                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                            }
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    scanner.nextLine(); // Limpar o buffer
                }
            }
        } else {
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}
