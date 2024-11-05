import java.util.List;
import java.util.ArrayList;

public class SistemaCadastro {
    private List<Ciclista> ciclistas = new ArrayList<>();
    private List<Bicicleta> bicicletas = new ArrayList<>();

    public void cadastrarBrasileiro(String cpf, String email, String senha, String nome) {
        Brasileiro brasileiro = new Brasileiro(cpf, email, senha, nome);
        ciclistas.add(brasileiro);
        System.out.println("Brasileiro cadastrado.");
    }

    public void cadastrarEstrangeiro(String passaporte, String email, String senha, String nome) {
        Estrangeiro estrangeiro = new Estrangeiro(passaporte, email, senha, nome);
        ciclistas.add(estrangeiro);
        System.out.println("Estrangeiro cadastrado.");
    }

    public void cadastrarBicicleta(int numero) {
        bicicletas.add(new Bicicleta(numero));
        System.out.println("Bicicleta número " + numero + " cadastrada.");
    }

    public void listarCiclistas() {
        for (Ciclista ciclista : ciclistas) {
            System.out.println("Nome: " + ciclista.getNome() + " - Email: " + ciclista.getEmail());
        }
    }

    public void alugarBicicletas(String email, int numero) {
        Ciclista ciclista = null;
        for (Ciclista c : ciclistas) {
            if (c.getEmail().equals(email)) {
                ciclista = c;
                break;
            }
        }

        if (ciclista == null) {
            System.out.println("Ciclista não encontrado.");
            return;
        }

        Bicicleta bicicleta = null;
        for (Bicicleta b : bicicletas) {
            if (b.getNumero() == numero && b.getDisponivel()) {
                bicicleta = b;
                break;
            }
        }

        if (bicicleta == null) {
            System.out.println("Bicicleta não encontrado.");
            return;
        }

        bicicleta.setDisponivel(false);
        System.out.println("Bicicleta número " + numero + " alugada para " + ciclista.getNome());
    }

}