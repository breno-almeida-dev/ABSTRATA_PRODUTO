package produto;

/*
 * Para poder executar os métodos do projeto é importada duas classe
 * da biblioteca SQL, sendo a Connection para estabelecer conexão com
 * o banco de dados e a SQLException para que uma exceção ocorra caso
 * ocorra algum erro relacionado ao banco de dados.
 * 
 * Também é importado o método de formato de retorno dos valores
 * com dois dígitos após a 
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;


/*
 * Define esta super classe como abstrata para que assim não possa ocorrer
 * uma instância direta da classe, pois ela apenas fornece a estrutura de classe
 * para suas sub classes que herdam os membros desta classe.
 * 
 * Também é definido os atributos da classe como private para que apenas 
 * possam ser modificados e acessados através de setters e getters.
 */
public abstract class Produto {
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private int quantidadeVendas;
    DecimalFormat df = new DecimalFormat("#0.00");

    /*
     * Construtor da classe que recebe 4 parâmetros e os inicializa.
     */
    public Produto(String nome, double precoCusto, double precoVenda, int quantidadeVendas) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeVendas = quantidadeVendas;
    }

    /*
     * Método que calcula o lucro total do produto com base no preço
     * de venda menos o preço de custo e o resultado desta conta é
     * multiplicado pela quantidade de vendas.
     */
    public double calcularLucro() {
        double lucroPorUnidade = precoVenda - precoCusto;
        double lucroTotal = lucroPorUnidade * quantidadeVendas;
        return Double.parseDouble(df.format(lucroTotal));
    }

    /*
     * Esses métodos abstratados são definidos como molde para
     * que as sub classes utilizem para sobrescrever de acordo
     * com suas informações únicas.
     */
    public abstract void salvarProduto(Connection conn) throws SQLException;
    public abstract void atualizarProduto(Connection conn, int id) throws SQLException;
    public abstract void deletarProduto(Connection conn, int id) throws SQLException;

    
    /*
     * Setter para definir os valores dos atributos de fora da classe.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }
    
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    public void setQuantidadeVendas(int quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }
    
    
    /*
     * Getters que retornam os valores dos atributos quando chamados.
     */
    public String getNome() {
        return nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
    
    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }
}
