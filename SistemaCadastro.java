import java.util.List;
import java.util.ArrayList;

public class SistemaCadastro {
    private List<Ciclista> ciclistas = new ArrayList<>();
    private List<Bicicleta> bicicletas = new ArrayList<>();
    private List<Devolucao> devolucoes = new ArrayList<>();
    private List<Totem> totems = new ArrayList<>();

    private final float TAXA_BASICA = 25.0f;
    private final float TAXA_EXTRA = 10.0f;

    public List<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void cadastrarBrasileiro(String cpf, String email, String senha, String nome, CartaoCredito cartaoCredito) {
        Brasileiro brasileiro = new Brasileiro(cpf, email, senha, nome, cartaoCredito);
        ciclistas.add(brasileiro);
        System.out.println("Brasileiro cadastrado.");
    }

    public void cadastrarEstrangeiro(String passaporte, String email, String senha, String nome, CartaoCredito cartaoCredito) {
        Estrangeiro estrangeiro = new Estrangeiro(passaporte, email, senha, nome, cartaoCredito);
        ciclistas.add(estrangeiro);
        System.out.println("Estrangeiro cadastrado.");
    }

    public void cadastrarBicicleta(int numero) {
        for (Bicicleta bicicleta : bicicletas) {
            if (bicicleta.getNumero() == numero) {
                System.out.println("Erro: Já existe uma bicicleta cadastrada com o número " + numero + ".");
                return;
            }
        }
        
        bicicletas.add(new Bicicleta(numero));
        System.out.println("Bicicleta número " + numero + " cadastrada com sucesso.");
    }

    public void listarCiclistas() {
        for (Ciclista ciclista : ciclistas) {
            System.out.println("Nome: " + ciclista.getNome() + " - Email: " + ciclista.getEmail());
        }
    }

    public void alugarBicicletas(String email, int numero, String horaAluguel) {
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
    
        if (ciclista.getBicicletaAlugada() != null) {
            System.out.println("O ciclista já possui uma bicicleta alugada e não pode alugar outra.");
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
            System.out.println("Bicicleta não encontrada.");
            return;
        }
    
        boolean pagamentoSucesso = ciclista.getCartaoCredito().cobrar(TAXA_BASICA);
    
        if (pagamentoSucesso) {
            bicicleta.setDisponivel(false);
            bicicleta.setCiclistaAtual(ciclista);
            bicicleta.setHoraAluguel(horaAluguel);
            ciclista.setBicicletaAlugada(bicicleta);
            System.out.println("Bicicleta número " + numero + " alugada para " + ciclista.getNome());
    
            String mensagem = ciclista.getNome() +
                    " alugou a bicicleta número " + numero + " às " + horaAluguel + ".\n" +
                    "Valor cobrado: R$" + TAXA_BASICA;
            enviarMensagem(email, mensagem);
        } else {
            System.out.println("Aluguel da bicicleta não efetuado.");
        }
    }    

    public void devolverBicicleta(int numeroBicicleta, int numeroTranca, String horarioDevolucao, int duracaoMinutos) {
        try {
            Bicicleta bicicleta = null;
            for (Bicicleta b : bicicletas) {
                if (b.getNumero() == numeroBicicleta && !b.getDisponivel()) {
                    bicicleta = b;
                    break;
                }
            }
    
            if (bicicleta == null) {
                System.out.println("Bicicleta número " + numeroBicicleta + " não está alugada ou não existe.");
                return;
            }
    
            Tranca tranca = null;
            for (Totem totem : totems) {
                for (Tranca t : totem.getTrancas()) {
                    if (t.getTranca() == numeroTranca && t.getDisponivel()) {
                        tranca = t;
                        break;
                    }
                }
                if (tranca != null) break;
            }
    
            if (tranca == null) {
                System.out.println("Tranca número " + numeroTranca + " não está disponível ou não existe.");
                return;
            }
    
            int minutosExcedentes = duracaoMinutos - 120;
            float valorExtra = 0;
            if (minutosExcedentes > 0) {
                int periodosExtras = (int) Math.ceil(minutosExcedentes / 30.0);
                valorExtra = periodosExtras * TAXA_EXTRA;
            }

            boolean pagamentoSucesso = bicicleta.getCiclistaAtual().getCartaoCredito().cobrar(valorExtra);
            if (!pagamentoSucesso) {
                System.out.println("Cobrança do valor extra falhou. A devolução será concluída, mas o valor deve ser regularizado.");
                registrarFalhaCobranca(bicicleta.getCiclistaAtual(), valorExtra);  
            }
    
            bicicleta.setDisponivel(true);
    
            Ciclista ciclista = bicicleta.getCiclistaAtual();
            if (ciclista != null) {
                ciclista.devolverBicicleta();
                bicicleta.setCiclistaAtual(null);
            }
    
            tranca.setDisponivel(false);
    
            Devolucao devolucao = new Devolucao(bicicleta, valorExtra, horarioDevolucao);
            devolucoes.add(devolucao);
    
            System.out.println("Bicicleta número " + numeroBicicleta + " devolvida com sucesso na tranca " + numeroTranca + ".");
            if (valorExtra > 0) {
                System.out.println("Valor extra pela devolução atrasada: R$" + valorExtra);
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado ao processar devolução: " + e.getMessage());
        }
    }     

    private void registrarFalhaCobranca(Ciclista ciclista, double valorExtra) {
        System.out.println("Registro de falha: Ciclista " + ciclista.getNome() + 
                           " - Valor pendente: R$" + valorExtra);
    }
    
    public void alterarDadosCiclista(String email, String novoNome, String novaSenha) {
        Ciclista ciclista = null;

        for (Ciclista c : ciclistas) {
            if (c.getEmail().equals(email)) {
                ciclista = c;
                break;
            }
        }

        if (ciclista == null) {
            System.out.println("Ciclista com e-mail " + email + " não encontrado.");
            return;
        }

        if (novoNome != null && !novoNome.isEmpty()) {
            ciclista.setNome(novoNome);
        }
        if (novaSenha != null && !novaSenha.isEmpty()) {
            ciclista.setSenha(novaSenha);
        }

        System.out.println("Dados do ciclista atualizados com sucesso.");
    }

    public void enviarMensagem(String email, String mensagem) {
        System.out.println("Mensagem enviada para " + email + ":");
        System.out.println(mensagem);
        System.out.println();
    }

    public void adicionarTotem(Totem totem){
        totems.add(totem);
    }

}