public class Brasileiro extends Ciclista {
    private String cpf;

    public Brasileiro(String cpf, String email, String senha, String nome, CartaoCredito cartaoCredito) {
        super(email, senha, nome, cartaoCredito);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}