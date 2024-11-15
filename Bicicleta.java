public class Bicicleta {
    private int numero;
    private boolean disponivel = true;
    private String horaAluguel;
    private Ciclista ciclistaAtual;

    public Bicicleta(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public Ciclista getCiclistaAtual() {
        return ciclistaAtual;
    }

    public void setCiclistaAtual(Ciclista ciclistaAtual) {
        this.ciclistaAtual = ciclistaAtual;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getHoraAluguel() {
        return horaAluguel;
    }

    public void setHoraAluguel(String horaAluguel) {
        this.horaAluguel = horaAluguel;
    }
}