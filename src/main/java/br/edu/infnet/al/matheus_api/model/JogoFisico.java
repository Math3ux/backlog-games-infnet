package br.edu.infnet.al.matheus_api.model;


public class JogoFisico extends Jogo {
    private String estadoConservacao;
    private Boolean possuiCaixa;

    public JogoFisico(Long id, String titulo, Double preco, Boolean isFinalizado, Integer nota,
                      String estadoConservacao, Boolean possuiCaixa) {
        super(id, titulo, preco, isFinalizado, nota);
        this.estadoConservacao = estadoConservacao;
        this.possuiCaixa = possuiCaixa;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public Boolean getPossuiCaixa() {
        return possuiCaixa;
    }

    public void setPossuiCaixa(Boolean possuiCaixa) {
        this.possuiCaixa = possuiCaixa;
    }

    @Override
    public String toString() {
        String caixa = possuiCaixa ? "Com Caixa" : "Sem Caixa";
        return "[FÍSICO] " + super.toString() + " | Estado: " + estadoConservacao + " | " + caixa;
    }
}
