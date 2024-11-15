import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaCadastro cadastro = new SistemaCadastro();
        Scanner scanner = new Scanner(System.in);

        // Cadastrar ciclistas para teste
        CartaoCredito cartao1 = new CartaoCredito("1234-5678-9101", "João Silva", 12, 2025, 50.0f);
        cadastro.cadastrarBrasileiro("123456", "abc@gmail.com", "1234", "João", cartao1);

        Totem totem = new Totem(5);
        cadastro.adicionarTotem(totem);

        cadastro.cadastrarBicicleta(1);
        cadastro.cadastrarBicicleta(2);
        cadastro.alugarBicicletas("abc@gmail.com", 1, "17:30");
        cadastro.alugarBicicletas("abc@gmail.com", 2, "17:30");
        cadastro.devolverBicicleta(1, 15f, 3, "21h", 180);
        cadastro.alugarBicicletas("abc@gmail.com", 1, "17:30");

        boolean executar = true;

        while (executar) {
            System.out.println("\nMenu do Sistema:");
            System.out.println("1. Alterar dados do ciclista");
            System.out.println("2. Listar ciclistas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            switch (opcao) {
                case 1:
                    System.out.print("Digite seu e-mail: ");
                    String email = scanner.nextLine();

                    System.out.print("Digite sua senha atual: ");
                    String senha = scanner.nextLine();

                    // Verificar se o e-mail e a senha correspondem a um ciclista cadastrado
                    Ciclista ciclista = null;
                    for (Ciclista c : cadastro.getCiclistas()) {
                        if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
                            ciclista = c;
                            break;
                        }
                    }

                    if (ciclista == null) {
                        System.out.println("E-mail ou senha inválidos.");
                        break;
                    }

                    // Solicitar novos dados
                    System.out.print("Digite seu novo nome (ou pressione Enter para manter): ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Digite sua nova senha (ou pressione Enter para manter): ");
                    String novaSenha = scanner.nextLine();

                    // Atualizar os dados
                    cadastro.alterarDadosCiclista(email, novoNome, novaSenha);
                    break;

                case 2:
                    cadastro.listarCiclistas();
                    break;

                case 3:
                    executar = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}