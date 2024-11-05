public class Pais {
    private Integer codigo;
    private Integer nome;

    public Pais(Integer codigo, Integer nome){
        this.codigo = codigo;
        this.nome = codigo;
    }

    public Integer getNome() {
        return nome;
    }

    public void setNome(Integer nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}