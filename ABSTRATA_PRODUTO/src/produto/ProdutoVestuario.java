package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Esta sub classe herda os membros da super classe abstrata e
 * Define seus próprios atributos únicos.
 */
public class ProdutoVestuario extends Produto {
    private String tamanho;
    private String cor;
    private String material;

    /*
     * Construtor da classe que recebe parâmetros vindos da super classe
     * para inicializar os atributos e inicializa seus atributos únicos.
     */
    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, int quantidadeVendas, String tamanho, String cor, String material) {
        super(nome, precoCusto, precoVenda, quantidadeVendas);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    /*
     * Método para salvar um novo produto no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void salvarProduto(Connection conn) throws SQLException {
        String sql = "INSERT INTO ProdutoVestuario (nome, precoCusto, precoVenda, tamanho, cor, material) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNome());  // Insere o nome do produto
            stmt.setDouble(2, getPrecoCusto());  // Insere o preço de custo
            stmt.setDouble(3, getPrecoVenda());  // Insere o preço de venda
            stmt.setString(4, tamanho);  // Insere o tamanho
            stmt.setString(5, cor);  // Insere a cor
            stmt.setString(6, material);  // Insere o material
            stmt.executeUpdate();  // Executa a inserção no banco de dados
        }
    }

    /*
     * Método que atualiza as informações de um produto alimentício no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void atualizarProduto(Connection conn, int id) throws SQLException {
        String sql = "UPDATE ProdutoVestuario SET nome = ?, precoCusto = ?, precoVenda = ?, tamanho = ?, cor = ?, material = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNome());  // Atualiza o nome do produto
            stmt.setDouble(2, getPrecoCusto());  // Atualiza o preço de custo
            stmt.setDouble(3, getPrecoVenda());  // Atualiza o preço de venda
            stmt.setString(4, tamanho);  // Atualiza o tamanho
            stmt.setString(5, cor);  // Atualiza a cor
            stmt.setString(6, material);  // Atualiza o material
            stmt.setInt(7, id);  // Define o id do produto a ser atualizado
            stmt.executeUpdate();  // Executa a atualização no banco de dados
        }
    }

    /*
     * Método que deleta um produto do banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void deletarProduto(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM ProdutoVestuario WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);  // Define o id do produto a ser deletado
            stmt.executeUpdate();  // Executa a exclusão no banco de dados
        }
    }

    /*
     * Setters que atribuem os valores únicos da classe quando são
     * utilizados.
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
    
    
    /*
     * Getters que retornam os valores de seus respectivos atributos
     * quando são chamados.
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
}
