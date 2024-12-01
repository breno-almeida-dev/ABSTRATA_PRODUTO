package br.com.abstrata_produto.produto;

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
    private int id;

    /*
     * Métodos construtores da classe que determina os parâmetros de quais tipos de dados são
     * esperados para construção do objeto da classe.
     * Os atributos são inicializados dentro do construtor para serem alterados pelos Setters
     * posteriormente.
     * Definindo os atributos que serão herdados pelas sub classes que extendem essa super classe. 
     */
    public Produto() {
    }
    
    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    /*
     * Método que calcula o lucro unitário do produto com base no preço
     * de venda menos o preço de custo e o resultado desta conta resulta
     * no valor do lucro unitário.
     */
    protected double calcularLucro() {
        double lucroUnitario = precoVenda - precoCusto;
        return lucroUnitario;
    }

    /*
     * Setters para atribuir os valores dos atributos da classe.
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
    
    public void setId(int id) {
        this.id = id;
    }    
    
    /*
     * Getters que retornam os valores dos atributos quando
     * são acessados.
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
    
    public int getId() {
        return id;
    }
}
