package br.edu.infnet.al.matheus_api.jogo;

public class JogoNaoEncontradoException extends RuntimeException {
    public JogoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}