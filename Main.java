public class Main {
    public static void main(String[] args) {
        SistemaCadastro cadastro = new SistemaCadastro();

        cadastro.cadastrarBrasileiro("123456", "abc@gmail.com", "1234", "João");
        cadastro.cadastrarBrasileiro("123456", "bca@gmail.com", "1234", "Danilo");
        cadastro.listarCiclistas();
        cadastro.cadastrarBicicleta(1);
        cadastro.alugarBicicletas("abc@gmail.com", 2);
    }
}
