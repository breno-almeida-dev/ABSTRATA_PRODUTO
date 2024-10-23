package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProdutoAlimenticio extends Produto {
    private LocalDate dataValidade;
    private String infoNutricionais;

    /*
     * Construtor da classe ProdutoAlimenticio que inicializa os atributos da superclasse
     * e também os atributos únicos desta classe.
     */
    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, int quantidadeVendas, LocalDate dataValidade, String infoNutricionais) {
        super(nome, precoCusto, precoVenda, quantidadeVendas);
        this.dataValidade = dataValidade;
        this.infoNutricionais = infoNutricionais;
    }
    
    /*
     * Método que salva um novo produto alimentício no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void salvarProduto(Connection conn) throws SQLException {
        String sql = "INSERT INTO ProdutoAlimenticio (nome, precoCusto, precoVenda, dataValidade, infoNutricionais) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNome());  // Insere o nome
            stmt.setDouble(2, getPrecoCusto());  // Insere o preço de custo
            stmt.setDouble(3, getPrecoVenda());  // Insere o preço de venda
            stmt.setDate(4, java.sql.Date.valueOf(getDataValidade()));  // Converte LocalDate para java.sql.Date
            stmt.setString(5, getInfoNutricionais());  // Insere as informações nutricionais
            stmt.executeUpdate();  // Executa o comando de inserção
        }
    }

    /*
     * Método que atualiza as informações de um produto alimentício no banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void atualizarProduto(Connection conn, int id) throws SQLException {
        String sql = "UPDATE ProdutoAlimenticio SET nome = ?, precoCusto = ?, precoVenda = ?, dataValidade = ?, infoNutricionais = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, getNome());  // Atualiza o nome
            stmt.setDouble(2, getPrecoCusto());  // Atualiza o preço de custo
            stmt.setDouble(3, getPrecoVenda());  // Atualiza o preço de venda
            stmt.setDate(4, java.sql.Date.valueOf(getDataValidade()));  // Converte LocalDate para java.sql.Date
            stmt.setString(5, getInfoNutricionais());  // Atualiza as informações nutricionais
            stmt.setInt(6, id);  // Identifica o produto pelo id
            stmt.executeUpdate();  // Executa a atualização
        }
    }

    /*
     * Método que deleta um produto do banco de dados.
     * E caso a conexão falhe retorna a mensagem de erro para o usuário.
     */
    @Override
    public void deletarProduto(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM ProdutoAlimenticio WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    /*
     * Setter para atribuir os valores das variáveis únicas da classe.
     */
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    public void setInfoNutricionais(String infoNutricionais) {
        this.infoNutricionais = infoNutricionais;
    }
    
    /*
     * Getters que retornam os valores de seus respectivos atributos
     * quando chamados.
     */
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public String getInfoNutricionais() {
        return infoNutricionais;
    }
}
