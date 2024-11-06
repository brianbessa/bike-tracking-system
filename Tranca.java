public class Tranca {
    private int numero;
    private boolean disponivel = true;

    public Tranca(int numero) {
        this.numero = numero;
    }

    public int getTranca(){
        return numero;
    }

    public boolean getDisponivel(){
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}