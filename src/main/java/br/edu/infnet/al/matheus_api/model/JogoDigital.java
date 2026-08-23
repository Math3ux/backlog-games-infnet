package br.edu.infnet.al.matheus_api.model;


public class JogoDigital extends Jogo {
    private Double tamanhoDownloadGb;
    private String lojaVirtual;
    private Boolean compativelPortatil;

    public JogoDigital(Long id, String titulo, Double preco, Boolean isFinalizado, Integer nota,
                       Double tamanhoDownloadGb, String lojaVirtual, Boolean compativelPortatil) {
        super(id, titulo, preco, isFinalizado, nota);
        this.tamanhoDownloadGb = tamanhoDownloadGb;
        this.lojaVirtual = lojaVirtual;
        this.compativelPortatil = compativelPortatil;
    }

    public Double getTamanhoDownloadGb() {
        return tamanhoDownloadGb;
    }

    public void setTamanhoDownloadGb(Double tamanhoDownloadGb) {
        this.tamanhoDownloadGb = tamanhoDownloadGb;
    }

    public String getLojaVirtual() {
        return lojaVirtual;
    }

    public void setLojaVirtual(String lojaVirtual) {
        this.lojaVirtual = lojaVirtual;
    }

    public Boolean getCompativelPortatil() {
        return compativelPortatil;
    }

    public void setCompativelPortatil(Boolean compativelPortatil) {
        this.compativelPortatil = compativelPortatil;
    }

    @Override
    public String toString() {
        String portatil = compativelPortatil ? "Sim" : "Não";
        return "[DIGITAL] " + super.toString() + " | Loja: " + lojaVirtual
                + " | Tamanho: " + tamanhoDownloadGb + "GB | Verificado Portátil: " + portatil;
    }
}
