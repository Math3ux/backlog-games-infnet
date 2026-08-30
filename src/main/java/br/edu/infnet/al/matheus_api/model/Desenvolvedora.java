package br.edu.infnet.al.matheus_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Desenvolvedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da desenvolvedora é obrigatório")
    private String nome;
    private String paisOrigem;
    private LocalDate dataFundacao;

    @JsonIgnore
    @OneToMany(mappedBy = "desenvolvedora", cascade = CascadeType.ALL)
    private List<Jogo> jogos = new ArrayList<>();

    public Desenvolvedora() {}

    public Desenvolvedora(Long id, String nome, String paisOrigem, LocalDate dataFundacao) {
        this.id = id;
        this.nome = nome;
        this.paisOrigem = paisOrigem;
        this.dataFundacao = dataFundacao;
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
        jogo.setDesenvolvedora(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    @Override
    public String toString() {
        return "Desenvolvedora [ID=" + id + ", Nome=" + nome + ", País=" + paisOrigem
                + ", Fundada em=" + dataFundacao + ", Quantidade de Jogos=" + jogos.size() + "]";
    }
}
