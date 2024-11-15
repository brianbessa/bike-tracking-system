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

    public void enviarMensagem(String email, String mensagem) {
        System.out.println("Mensagem para " + email + ": " + mensagem);
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
        bicicletas.add(new Bicicleta(numero));
        System.out.println("Bicicleta número " + numero + " cadastrada.");
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
            ciclista.setBicicletaAlugada(bicicleta); // Associar o ciclista à bicicleta
            System.out.println("Bicicleta número " + numero + " alugada para " + ciclista.getNome());

            String mensagem = "Você alugou a bicicleta número " + numero + " às " + horaAluguel + ".";
            enviarMensagem(email, mensagem);
        } else {
            System.out.println("Aluguel da bicicleta não efetuado.");
        }

    }

    public void devolverBicicleta(int numero, float valor, int numeroTranca, String dataHora, int duracaoMinutos) {
        Bicicleta bicicleta = null;
        Tranca trancaEscolhida = null;
    
        // Encontrar a bicicleta
        for (Bicicleta b : bicicletas) {
            if (b.getNumero() == numero && !b.getDisponivel()) {
                bicicleta = b;
                break;
            }
        }
    
        if (bicicleta == null) {
            System.out.println("Erro: Bicicleta número " + numero + " não encontrada ou já está disponível.");
            return;
        }
    
        // Encontrar a tranca
        for (Totem totem : totems) {
            for (Tranca tranca : totem.getTrancas()) {
                if (tranca.getTranca() == numeroTranca) {
                    trancaEscolhida = tranca;
                    break;
                }
            }
            if (trancaEscolhida != null) break;
        }
    
        if (trancaEscolhida == null) {
            System.out.println("Erro: Tranca número " + numeroTranca + " não encontrada.");
            return;
        }
    
        if (!trancaEscolhida.getDisponivel()) {
            System.out.println("Erro: Tranca número " + numeroTranca + " já está ocupada.");
            return;
        }
    
        // Calcular valor extra
        int minutosMais = duracaoMinutos - 120;
        float valorExtra = 0;
        if (minutosMais > 0) {
            int periodosExtras = (int) Math.ceil(minutosMais / 30.0);
            valorExtra = periodosExtras * TAXA_EXTRA;
        }
    
        // Verificar ciclista associado à bicicleta
        Ciclista ciclistaAtual = bicicleta.getCiclistaAtual();
        if (ciclistaAtual == null) {
            System.out.println("Erro: Nenhum ciclista associado à bicicleta.");
            return;
        }
    
        // Cobrar taxa extra, se aplicável
        if (valorExtra > 0) {
            boolean pagamentoExtraSucesso = ciclistaAtual.getCartaoCredito().cobrar(valorExtra);
            if (!pagamentoExtraSucesso) {
                System.out.println("Falha na cobrança da taxa extra. A devolução será processada, mas o valor permanecerá pendente.");
            }
        }
    
        // Registrar devolução
        Devolucao devolucao = new Devolucao(bicicleta, valor + valorExtra, dataHora);
        devolucoes.add(devolucao);
    
        // Atualizar disponibilidade
        trancaEscolhida.setDisponivel(false);
        bicicleta.setDisponivel(true);
        bicicleta.setCiclistaAtual(null);
        ciclistaAtual.setBicicletaAlugada(null);
    
        System.out.println("Bicicleta " + numero + " devolvida com sucesso na tranca " + numeroTranca + ".");
    }    

    public void alterarDadosCiclista(String email, String novoNome, String novaSenha) {
        Ciclista ciclista = null;

        // Buscar ciclista pelo e-mail
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

        // Alterar os dados do ciclista
        if (novoNome != null && !novoNome.isEmpty()) {
            ciclista.setNome(novoNome);
        }
        if (novaSenha != null && !novaSenha.isEmpty()) {
            ciclista.setSenha(novaSenha);
        }

        System.out.println("Dados do ciclista atualizados com sucesso.");
    }

    public void adicionarTotem(Totem totem){
        totems.add(totem);

    }

}