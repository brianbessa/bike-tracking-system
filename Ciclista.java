public abstract class Ciclista {
    private String email;
    private String senha;
    private String nome;
    private CartaoCredito cartaoCredito;
    private Bicicleta bicicletaAlugada;

    public Ciclista(String email, String senha, String nome, CartaoCredito cartaoCredito) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cartaoCredito = cartaoCredito;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bicicleta getBicicletaAlugada() {
        return bicicletaAlugada;
    }

    public void setBicicletaAlugada(Bicicleta bicicletaAlugada) {
        this.bicicletaAlugada = bicicletaAlugada;
    }
}