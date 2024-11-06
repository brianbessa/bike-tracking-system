public class Devolucao {
    private float valor;
    private Bicicleta bicicleta;

    public Devolucao (float valor, Bicicleta bicicleta){
        this.valor = valor;
        this.bicicleta = bicicleta;
    }

    public float getDevolucao() {
        return valor;
    }

    public Bicicleta getBicicleta(){
        return bicicleta;
    }
}