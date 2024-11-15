public class Devolucao {
    private float valor;
    private Bicicleta bicicleta;
    private String dataHora;

    public Devolucao(Bicicleta bicicleta, float valor, String dataHora){
        this.valor = valor;
        this.bicicleta = bicicleta;
        this.dataHora = dataHora;
    }

    public float getDevolucao() {
        return valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public Bicicleta getBicicleta(){
        return bicicleta;
    }
}