public class Atividades {
    private String descricao;
    private float consumoPorMin;
    private int duracao;

    public Atividades(String descricao, float consumoPorMin, int duracao) {
        this.descricao = descricao;
        this.consumoPorMin = consumoPorMin;
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getConsumoPorMin() {
        return consumoPorMin;
    }
    public void setConsumoPorMin(float consumoPorMin) {
        this.consumoPorMin = consumoPorMin;
    }

    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }
}
