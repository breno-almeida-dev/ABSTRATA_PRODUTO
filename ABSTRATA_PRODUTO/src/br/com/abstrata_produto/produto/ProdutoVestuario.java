package br.com.abstrata_produto.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * Uma sub classe da super classe Produto, essa herda os atributos presentes em
 * todos os produtos, e adiciona os atributos únicos de produtos vestuários.
 * Define os atributos como privados para serem processados apenas de dentro da classe.
 */
public class ProdutoVestuario extends Produto {
    private String tamanho;
    private String cor;
    private String material;
    private int id;

    /*
     * Métodos construtores da classe que determina os parâmetros de quais tipos de dados são
     * esperados para construção do objeto da classe.
     * Os atributos são inicializados dentro do construtor para serem alterados pelos Setters
     * posteriormente. Além disso, herda os atributos da superclasse.
     */
    public ProdutoVestuario() {
    }
    
    public ProdutoVestuario(String nome, double precoCusto, double precoVenda) {
        super(nome, precoCusto, precoVenda);
    }
    
    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
        super(nome, precoCusto, precoVenda);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    /*
     * Setters para atribuir os valores dos atributos da classe.
     */
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    /*
     * Getters que retornam os valores dos atributos quando
     * são acessados.
     */
    public String getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public String getMaterial() {
        return material;
    }
    
    public int getId() {
        return id;
    }

    /*
     * Método que salva um novo produto vestuário no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void salvarProduto(Scanner scanner, Connection conexao) {
    	ProdutoVestuario produtoVestuario = new ProdutoVestuario();


        // Captura dos dados do usuário usando setters
        System.out.print("Digite o nome do produto: ");
        produtoVestuario.setNome(scanner.nextLine());

        System.out.print("Digite o preço de custo: ");
        produtoVestuario.setPrecoCusto(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite o preço de venda: ");
        produtoVestuario.setPrecoVenda(scanner.nextDouble());
        scanner.nextLine(); // Limpar o buffer.

        System.out.print("Digite o tamanho: ");
        produtoVestuario.setTamanho(scanner.nextLine());

        System.out.print("Digite a cor: ");
        produtoVestuario.setCor(scanner.nextLine());

        System.out.print("Digite o material: ");
        produtoVestuario.setMaterial(scanner.nextLine());

        // Comando SQL para inserir o produto no banco de dados
        String sqlInserir = "INSERT INTO ProdutoVestuario (nome, precoCusto, precoVenda, tamanho, cor, material) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlInserir, PreparedStatement.RETURN_GENERATED_KEYS)) {
        	
            stmt.setString(1, produtoVestuario.getNome());
            stmt.setDouble(2, produtoVestuario.getPrecoCusto());
            stmt.setDouble(3, produtoVestuario.getPrecoVenda());
            stmt.setString(4, produtoVestuario.getTamanho());
            stmt.setString(5, produtoVestuario.getCor());
            stmt.setString(6, produtoVestuario.getMaterial());

            stmt.executeUpdate();

            // Recupera o ID gerado no banco de dados
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Produto Vestuário inserido com sucesso! ID gerado: " + idGerado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Produto Vestuário: " + e.getMessage());
        }
    }

    /*
     * Método estático que atualiza as informações de um produto vestuário no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void atualizarProduto(Scanner scanner, Connection conexao) {
    	ProdutoVestuario produtoVestuario = new ProdutoVestuario();

        System.out.print("Informe o ID do produto a ser atualizado: ");
        int idAtualizar = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Captura dos dados do usuário usando setters
        System.out.print("Digite o nome do produto: ");
        produtoVestuario.setNome(scanner.nextLine());

        System.out.print("Digite o preço de custo: ");
        produtoVestuario.setPrecoCusto(scanner.nextDouble());

        System.out.print("Digite o preço de venda: ");
        produtoVestuario.setPrecoVenda(scanner.nextDouble());

        scanner.nextLine(); // Consumir a quebra de linha após o nextDouble()

        System.out.print("Digite o tamanho: ");
        produtoVestuario.setTamanho(scanner.nextLine());

        System.out.print("Digite a cor: ");
        produtoVestuario.setCor(scanner.nextLine());

        System.out.print("Digite o material: ");
        produtoVestuario.setMaterial(scanner.nextLine());

        // Comando SQL para atualizar o produto no banco de dados.
        String sqlAtualizar = "UPDATE ProdutoVestuario SET nome = ?, precoCusto = ?, precoVenda = ?, tamanho = ?, cor = ?, material = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlAtualizar)) {
            stmt.setString(1, produtoVestuario.getNome());
            stmt.setDouble(2, produtoVestuario.getPrecoCusto());
            stmt.setDouble(3, produtoVestuario.getPrecoVenda());
            stmt.setString(4, produtoVestuario.getTamanho());
            stmt.setString(5, produtoVestuario.getCor());
            stmt.setString(6, produtoVestuario.getMaterial());
            stmt.setInt(7, idAtualizar);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto Vestuário atualizado com sucesso.");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Produto Vestuário: " + e.getMessage());
        }
    }

    /*
     * Método estático que deleta um produto vestuário do banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    public static void deletarProduto(Scanner scanner, Connection conexao) {
        System.out.print("Informe o ID do produto a ser deletado: ");
        int idDeletar = scanner.nextInt();
        scanner.nextLine();

        // Comando SQL para deletar o produto no banco de dados.
        String sqlDeletar = "DELETE FROM ProdutoVestuario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlDeletar)) {
            stmt.setInt(1, idDeletar);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto Vestuário deletado com sucesso.");
            } else {
                System.out.println("Nenhum Produto Vestuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Produto Vestuário: " + e.getMessage());
        }
    }
    
    /*
     * Método estático para ler um produto no banco
     * de dados, através do método "PreparedStatement".
     */
    public static void lerProduto(Connection conexao) {
        // Comando SQL para buscar todos os produtos de vestuários.
        String sql = "SELECT id, nome, precoCusto, precoVenda, tamanho, cor, material FROM ProdutoVestuario";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            // Verifica se existem resultados
            while (rs.next()) {
                // Recupera os dados do ResultSet
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double precoCusto = rs.getDouble("precoCusto");
                double precoVenda = rs.getDouble("precoVenda");
                String tamanho = rs.getString("tamanho");
                String cor = rs.getString("cor");
                String material = rs.getString("material");
                
                // Exibe as informações do produto
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Preço de Custo: " + precoCusto);
                System.out.println("Preço de Venda: " + precoVenda);
                System.out.println("Tamanho: " + tamanho);
                System.out.println("Cor: " + cor);
                System.out.println("Material: " + material);
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
    }
}
