public class Estrangeiro extends Ciclista {
    private String passaporte;

    public Estrangeiro(String passaporte, String email, String senha, String nome){
        super(email, senha, nome);
        this.passaporte = passaporte;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }
}