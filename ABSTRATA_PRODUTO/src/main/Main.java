package main;

import produto.ProdutoAlimenticio;
import produto.ProdutoVestuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/meuBancoDeDados";  // URL de conexão.
        String username = "teste-exemplo";  // Usuário do banco de dados.
        String password = "testeTestandoExemplo";  // Senha do banco de dados.

        /*
         * Utiliza um try-catch para testar o código primeiramente, caso falhe
         * retorna a mensagem de erro.
         */
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
        	
            /* 
             * Cria um objeto para cada sub classe do projeto.
             * Informando os valores exigidos pelos parâmetros.
             */        	
            ProdutoAlimenticio alimento = new ProdutoAlimenticio("Arroz", 5.00, 7.00, 15, LocalDate.of(2025, 12, 31), "Sem glúten");
            ProdutoVestuario vestuario = new ProdutoVestuario("Camiseta", 20.00, 35.00, 23, "M", "Azul", "Algodão");

            /*
             * Faz uma chamada do método de que salva os dados no banco.
             */
            alimento.salvarProduto(conn);
            vestuario.salvarProduto(conn);

            /* 
             * Realizando a chamada dos métodos de calcular o lucro, faz o
             * retorno do lucro obtido no terminal.
             */
            System.out.println("Lucro do alimento: " + alimento.calcularLucro());
            System.out.println("Lucro do vestuário: " + vestuario.calcularLucro());

            /*
             * Primeiramente é enviada a mensagem de que os dados estão sendo
             * atualizados, e através dos setters atualiza as informações
             * para a chamada do método de atualização realizar a atualização
             * no banco de dados.
             */
            System.out.println("Atualizando os produtos...");
            alimento.setPrecoVenda(8.00);  // Aumentando o preço de venda do alimento
            vestuario.setPrecoVenda(40.00);  // Aumentando o preço de venda do vestuário
            alimento.atualizarProduto(conn, 1);  // Supondo que o ID do alimento é 1
            vestuario.atualizarProduto(conn, 2);  // Supondo que o ID do vestuário é 2

            /*
             * Primeiramente é informado que os produtos estão sendo deletados e
             * faz a chamada do método de deletar para remove-los do banco de dados.
             */
            System.out.println("Deletando os produtos...");
            alimento.deletarProduto(conn, 1);  // Deletando o alimento pelo ID 1
            vestuario.deletarProduto(conn, 2);  // Deletando o vestuário pelo ID 2
            //Aqui informa que os dados foram excluídos.
            System.out.println("Produtos deletados com sucesso!");
        } catch (SQLException e) {
        	//Caso o bloco try não consiga estabelecer uma conexão e informada a mensagem de erro.
            e.printStackTrace();
        }
    }
}
