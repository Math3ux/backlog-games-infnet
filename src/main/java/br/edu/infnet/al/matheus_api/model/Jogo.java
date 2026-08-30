package br.edu.infnet.al.matheus_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private Double preco;
    private Boolean isFinalizado;
    private Integer nota;

    // capa buscada online via API externa.
    private String urlCapa;

    @ManyToOne
    @JoinColumn(name = "desenvolvedora_id")
    private Desenvolvedora desenvolvedora;

    public Jogo() {}

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

    public Boolean getIsFinalizado() {
        return isFinalizado;
    }

    public void setIsFinalizado(Boolean isFinalizado) {
        this.isFinalizado = isFinalizado;
    }

    public String getUrlCapa() { return urlCapa; }

    public void setUrlCapa(String urlCapa) { this.urlCapa = urlCapa; }

    @Override
    public String toString() {
        String status = isFinalizado ? "Finalizado" : "No Backlog";
        String dev = (desenvolvedora != null) ? desenvolvedora.getNome() : "Desconhecida";
        return titulo + " (" + dev + ") - " + status + " | Nota: " + nota + " | Preço: R$" + preco;
    }
}
