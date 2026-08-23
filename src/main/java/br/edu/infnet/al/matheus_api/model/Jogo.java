package br.edu.infnet.al.matheus_api.model;

public abstract class Jogo {
    private Long id;
    private String titulo;
    private Double preco;
    private Boolean isFinalizado;
    private Integer nota;

    private Desenvolvedora desenvolvedora;

    public Jogo(Long id, String titulo, Double preco, Boolean isFinalizado, Integer nota) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.isFinalizado = isFinalizado;
        this.nota = nota;
    }

    public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Desenvolvedora getDesenvolvedora() {
        return desenvolvedora;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Boolean getFinalizado() {
        return isFinalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        isFinalizado = finalizado;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String status = isFinalizado ? "Finalizado" : "No Backlog";
        String dev = (desenvolvedora != null) ? desenvolvedora.getNome() : "Desconhecida";
        return titulo + " (" + dev + ") - " + status + " | Nota: " + nota + " | Preço: R$" + preco;
    }
}
