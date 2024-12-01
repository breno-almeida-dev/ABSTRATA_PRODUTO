package br.com.abstrata_produto.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

/*
 * Uma sub classe da super classe Produto, essa herda os atributos presentes em
 * todos os produtos, e adiciona os atributos únicos de produtos alimentícios.
 * Define os atributos como privados para serem processados apenas de dentro da classe.
 */
public class ProdutoAlimenticio extends Produto {
    private LocalDate dataValidade;
    private String infoNutricionais;
    private int id;

    /*
     * Métodos construtores da classe que determina os parâmetros de quais tipos de dados são
     * esperados para construção do objeto da classe.
     * Os atributos são inicializados dentro do construtor para serem alterados pelos Setters
     * posteriormente. Além disso, herda os atributos da superclasse.
     */
    public ProdutoAlimenticio() {
    }
    
    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda) {
        super(nome, precoCusto, precoVenda);
    }
    
    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, LocalDate dataValidade, String infoNutricionais) {
        super(nome, precoCusto, precoVenda);
        this.dataValidade = dataValidade;
        this.infoNutricionais = infoNutricionais;
    }
    
    /*
     * Setters para atribuir os valores dos atributos da classe.
     */
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    public void setInfoNutricionais(String infoNutricionais) {
        this.infoNutricionais = infoNutricionais;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    /*
     * Getters que retornam os valores dos atributos quando
     * são acessados.
     */
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public String getInfoNutricionais() {
        return infoNutricionais;
    }
    
    public int getId() {
        return id;
    }
        
    /*
     * Método que salva um novo produto alimentício no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void salvarProduto(Scanner scanner, Connection conexao) {
    	ProdutoAlimenticio produtoAlimenticio = new ProdutoAlimenticio();

        System.out.print("Digite o nome do produto: ");
        produtoAlimenticio.setNome(scanner.nextLine());

        System.out.print("Digite o preço de custo: ");
        produtoAlimenticio.setPrecoCusto(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite o preço de venda: ");
        produtoAlimenticio.setPrecoVenda(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite a data de validade (yyyy-MM-dd): ");
        produtoAlimenticio.setDataValidade(LocalDate.parse(scanner.nextLine()));

        System.out.print("Digite as informações nutricionais: ");
        produtoAlimenticio.setInfoNutricionais(scanner.nextLine());

        // Comando SQL para inserir o produto no banco de dados
        String sqlInserir = "INSERT INTO ProdutoAlimenticio (nome, precoCusto, precoVenda, lucroUnitario, dataValidade, infoNutricionais) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlInserir, PreparedStatement.RETURN_GENERATED_KEYS)) {
        	
            stmt.setString(1, produtoAlimenticio.getNome());
            stmt.setDouble(2, produtoAlimenticio.getPrecoCusto());
            stmt.setDouble(3, produtoAlimenticio.getPrecoVenda());
            stmt.setDouble(4, produtoAlimenticio.calcularLucro());
            stmt.setDate(5, java.sql.Date.valueOf(produtoAlimenticio.getDataValidade()));
            stmt.setString(6, produtoAlimenticio.getInfoNutricionais());

            stmt.executeUpdate();

            // Recupera o ID gerado no banco de dados
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Produto Alimentício inserido com sucesso! ID gerado: " + idGerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Produto Alimentício: " + e.getMessage());
        }
    }

    /*
     * Método estático que atualiza as informações de um produto alimentício no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void atualizarProduto(Scanner scanner, Connection conexao) {
    	ProdutoAlimenticio produtoAlimenticio = new ProdutoAlimenticio();

        System.out.print("Informe o ID da bicicleta a ser atualizada: ");
        int idAtualizar = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer.
        
        System.out.print("Digite o nome do produto: ");
        produtoAlimenticio.setNome(scanner.nextLine());

        System.out.print("Digite o preço de custo: ");
        produtoAlimenticio.setPrecoCusto(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite o preço de venda: ");
        produtoAlimenticio.setPrecoVenda(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite a data de validade (yyyy-MM-dd): ");
        produtoAlimenticio.setDataValidade(LocalDate.parse(scanner.nextLine()));

        System.out.print("Digite as informações nutricionais: ");
        produtoAlimenticio.setInfoNutricionais(scanner.nextLine());
        
        // Comando SQL para atualizar o produto no banco de dados.
        String sqlAtualizar = "UPDATE ProdutoAlimenticio SET nome = ?, precoCusto = ?, precoVenda = ?, dataValidade = ?, infoNutricionais = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlAtualizar)) {
            stmt.setString(1, produtoAlimenticio.getNome());
            stmt.setDouble(2, produtoAlimenticio.getPrecoCusto());
            stmt.setDouble(3, produtoAlimenticio.getPrecoVenda());
            stmt.setDate(4, java.sql.Date.valueOf(produtoAlimenticio.getDataValidade()));
            stmt.setString(5, produtoAlimenticio.getInfoNutricionais());
            stmt.setInt(6, idAtualizar);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bicicleta atualizada com sucesso.");
            } else {
                System.out.println("Nenhuma bicicleta encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar bicicleta: " + e.getMessage());
        }
    }

    /*
     * Método estático que deleta um produto do banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void deletarProduto(Scanner scanner, Connection conexao) {
        System.out.print("Informe o ID do produto a ser deletada: ");
        int idDeletar = scanner.nextInt();
        scanner.nextLine();
        
        // Comando SQL para deletar o produto no banco de dados.
        String sqlDeletar = "DELETE FROM ProdutoAlimenticio WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlDeletar)) {
            stmt.setInt(1, idDeletar);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto Alimentício deletado com sucesso.");
            } else {
                System.out.println("Nenhum Produto Alimentício encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Produto Alimentício: " + e.getMessage());
        }
    }
    
    /*
     * Método estático para ler um produto no banco
     * de dados, através do método "PreparedStatement".
     */
    public static void lerProduto(Connection conexao) {
        // Comando SQL para buscar todos os produtos alimentícios.
        String sql = "SELECT id, nome, precoCusto, precoVenda, lucroUnitario, dataValidade, infoNutricionais FROM ProdutoAlimenticio";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Verifica se existem resultados
            while (rs.next()) {
                // Recupera os dados do ResultSet
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double precoCusto = rs.getDouble("precoCusto");
                double precoVenda = rs.getDouble("precoVenda");
                double lucroUnitario = rs.getDouble("lucroUnitario");
                LocalDate validade = rs.getDate("dataValidade").toLocalDate();
                String infoNutricionais = rs.getString("infoNutricionais");

                // Exibe os dados do produto
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Preço de Custo: " + precoCusto);
                System.out.println("Preço de Venda: " + precoVenda);
                System.out.println("Lucro Unitário: " + lucroUnitario);
                System.out.println("Data de Validade: " + validade);
                System.out.println("Informações Nutricionais: " + infoNutricionais);
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler os produtos alimentícios: " + e.getMessage());
        }
    }
}
