public class CartaoCredito {
    private String numero;
    private String nome;
    private int mesValidade;
    private int anoValidade;

    public CartaoCredito(String numero, String nome, int mesValidade, int anoValidade) {
        this.anoValidade = anoValidade;
        this.mesValidade = mesValidade;
        this.nome = nome;
        this.numero = numero;
    }
}