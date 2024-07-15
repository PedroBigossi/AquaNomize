import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Atividades> atividades;

    public Usuario(String nome) {
        this.nome = nome;
        this.atividades = new ArrayList<>();
    }

    public void adicionarAtividade(String descricao, float consumoPorMin, int duracao) {
        Atividades atividade = new Atividades(descricao, consumoPorMin, duracao);
        this.atividades.add(atividade);
    }

    public List<Atividades> getAtividades() {
        return atividades;
    }

    public void limparAtividades() {
        atividades.clear();
    }

    //GETTERS E SETTERS DO USUARIO
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
