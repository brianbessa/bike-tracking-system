import java.util.List;
import java.util.ArrayList;

public class Totem {
    private List<Tranca> trancas = new ArrayList<>();

    public Totem(int quantidadeTrancas){
        for(int i = 1; i <= quantidadeTrancas; i++) {
            trancas.add(new Tranca(i));
        }
    }

    public Tranca encontrarTranca(){
        for (Tranca t : trancas) {
            if (t.getDisponivel()) {
                return t;
            }
        }
        return null;
    }

    public List<Tranca> getTrancas() {
        return trancas;
    }

}