import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaCadastro cadastro = new SistemaCadastro();
        Scanner user = new Scanner(System.in);
        
        int opcao = -1;
        do {
            try {
                System.out.println("\nSistema de Gerenciamento de Bicicletas - CicloGo");
                System.out.println("1. Logar como Administrador");
                System.out.println("2. Logar como Ciclista");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = user.nextInt();
                user.nextLine();

                switch (opcao) {
                    case 1:
                        menuAdministrador(cadastro, user);
                        break;
                    case 2:
                        menuCiclista(cadastro, user);
                        break;
                    case 0:
                        System.out.println("Finalizando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                user.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);

        user.close();
    }

    private static void menuAdministrador(SistemaCadastro cadastro, Scanner user) {
        int opcao = -1;
        do {
            try {
                System.out.println("\nMenu do Administrador");
                System.out.println("1. Cadastrar Ciclista");
                System.out.println("2. Cadastrar Bicicleta");
                System.out.println("3. Cadastrar Totem");
                System.out.println("4. Listar Ciclistas");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = user.nextInt();
                user.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarCiclista(cadastro, user);
                        break;
                    case 2:
                        cadastrarBicicleta(cadastro, user);
                        break;
                    case 3:
                        cadastrarTotem(cadastro, user);
                        break;
                    case 4:
                        cadastro.listarCiclistas();
                        break;
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                user.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado no menu do administrador: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void menuCiclista(SistemaCadastro cadastro, Scanner user) {
        try {
            System.out.print("\nDigite seu e-mail: ");
            String email = user.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = user.nextLine();

            Ciclista ciclista = autenticarCiclista(cadastro, email, senha);
            if (ciclista == null) {
                System.out.println("E-mail ou senha incorretos.");
                return;
            }

            int opcao = -1;
            do {
                try {
                    System.out.println("\nMenu do Ciclista");
                    System.out.println("1. Alugar Bicicleta");
                    System.out.println("2. Devolver Bicicleta");
                    System.out.println("3. Verificar Saldo");
                    System.out.println("4. Alterar Dados");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha uma opção: ");
                    opcao = user.nextInt();
                    user.nextLine();

                    switch (opcao) {
                        case 1:
                            alugarBicicleta(cadastro, ciclista, user);
                            break;
                        case 2:
                            devolverBicicleta(cadastro, ciclista, user);
                            break;
                        case 3:
                            verificarSaldo(ciclista);
                            break;
                        case 4:
                            alterarDados(ciclista, user);
                            break;
                        case 0:
                            System.out.println("Voltando ao menu principal...");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    user.nextLine();
                }
            } while (opcao != 0);
        } catch (Exception e) {
            System.out.println("Erro inesperado no menu do ciclista: " + e.getMessage());
        }
    }

    private static void cadastrarCiclista(SistemaCadastro cadastro, Scanner user) {
        try {
            System.out.println("\n=== Cadastro de Ciclistas ===");
            System.out.println("1. Cadastrar Brasileiro");
            System.out.println("2. Cadastrar Estrangeiro");
            System.out.print("Escolha o tipo de ciclista: ");
            int tipo = user.nextInt();
            user.nextLine();
    
            System.out.print("Digite o nome: ");
            String nome = user.nextLine();
            System.out.print("Digite o e-mail: ");
            String email = user.nextLine();
            System.out.print("Digite a senha: ");
            String senha = user.nextLine();
            System.out.print("Digite o número do cartão: ");
            String numero = user.nextLine();
            System.out.print("Digite o nome no cartão: ");
            String nomeCartao = user.nextLine();
            System.out.print("Digite o mês de validade do cartão: ");
            int mes = user.nextInt();
            System.out.print("Digite o ano de validade do cartão: ");
            int ano = user.nextInt();
            System.out.print("Digite o saldo inicial do cartão: ");
            float saldo = user.nextFloat();
            user.nextLine();
    
            CartaoCredito cartao = new CartaoCredito(numero, nomeCartao, mes, ano, saldo);
    
            switch (tipo) {
                case 1:
                    System.out.print("Digite o CPF: ");
                    String cpf = user.nextLine();
                    cadastro.cadastrarBrasileiro(cpf, email, senha, nome, cartao);
                    break;
                case 2:
                    System.out.print("Digite o número do passaporte: ");
                    String passaporte = user.nextLine();
                    cadastro.cadastrarEstrangeiro(passaporte, email, senha, nome, cartao);
                    break;
                default:
                    System.out.println("Opção inválida. Ciclista não cadastrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira os dados corretamente.");
            user.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar ciclista: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar ciclista: " + e.getMessage());
        }
    }    

    private static void cadastrarBicicleta(SistemaCadastro cadastro, Scanner user) {
        try {
            System.out.print("Digite o número da bicicleta: ");
            int numero = user.nextInt();
            user.nextLine();

            cadastro.cadastrarBicicleta(numero);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. O número da bicicleta deve ser um número inteiro.");
            user.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar bicicleta: " + e.getMessage());
        }
    }

    private static void cadastrarTotem(SistemaCadastro cadastro, Scanner user) {
        try {
            System.out.print("Digite o número de trancas do totem: ");
            int quantidade = user.nextInt();
            user.nextLine();

            cadastro.adicionarTotem(new Totem(quantidade));
            System.out.println("Totem cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
            user.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar totem: " + e.getMessage());
        }
    }

    private static Ciclista autenticarCiclista(SistemaCadastro cadastro, String email, String senha) {
        try {
            for (Ciclista c : cadastro.getCiclistas()) {
                if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
                    return c;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao autenticar ciclista: " + e.getMessage());
            return null;
        }
    }

    private static void alugarBicicleta(SistemaCadastro cadastro, Ciclista ciclista, Scanner user) {
        try {
            System.out.print("Digite o número da bicicleta que deseja alugar: ");
            int numero = user.nextInt();
            user.nextLine();
            System.out.print("Digite a hora do aluguel: ");
            String hora = user.nextLine();

            cadastro.alugarBicicletas(ciclista.getEmail(), numero, hora);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. O número da bicicleta deve ser um número inteiro.");
            user.nextLine(); 
        } catch (Exception e) {
            System.out.println("Erro inesperado ao alugar bicicleta: " + e.getMessage());
        }
    }

    private static void devolverBicicleta(SistemaCadastro cadastro, Ciclista ciclista, Scanner user) {
        try {
            System.out.print("Digite o número da bicicleta que deseja devolver: ");
            int numero = user.nextInt();
            System.out.print("Digite o número da tranca para devolução: ");
            int numeroTranca = user.nextInt();
            user.nextLine();
            System.out.print("Digite o horário da devolução: ");
            String horario = user.nextLine();
            System.out.print("Digite a duração do uso em minutos: ");
            int duracao = user.nextInt();
            user.nextLine();

            cadastro.devolverBicicleta(numero, numeroTranca, horario, duracao);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, insira números válidos.");
            user.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao devolver bicicleta: " + e.getMessage());
        }
    }

    private static void verificarSaldo(Ciclista ciclista) {
        try {
            System.out.println("Saldo no cartão: R$" + ciclista.getCartaoCredito().getSaldo());
        } catch (Exception e) {
            System.out.println("Erro ao verificar saldo: " + e.getMessage());
        }
    }

    private static void alterarDados(Ciclista ciclista, Scanner user) {
        try {
            System.out.print("Digite o novo nome: ");
            String novoNome = user.nextLine();
            System.out.print("Digite o novo e-mail: ");
            String novoEmail = user.nextLine();
            System.out.print("Digite a nova senha: ");
            String novaSenha = user.nextLine();

            ciclista.setNome(novoNome);
            ciclista.setEmail(novoEmail);
            ciclista.setSenha(novaSenha);

            System.out.println("Dados alterados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar dados do ciclista: " + e.getMessage());
        }
    }
}
