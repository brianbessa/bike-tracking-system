public class CartaoCredito {
    private String numero;
    private String nome;
    private int mesValidade;
    private int anoValidade;
    private float saldo;

    public CartaoCredito(String numero, String nome, int mesValidade, int anoValidade, float saldo) {
        this.anoValidade = anoValidade;
        this.mesValidade = mesValidade;
        this.nome = nome;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public int getMesValidade() {
        return mesValidade;
    }

    public int getAnoValidade() {
        return anoValidade;
    }

    public float getSaldo() {
        return saldo;
    }

    public boolean cobrar(float valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Cobrança de " + valor + " realizada com sucesso no cartão " + numero);
            return true;
        } else {
            System.out.println("Saldo insuficiente no cartão " + numero);
            return false;
        }
    }
}