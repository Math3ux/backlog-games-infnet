package br.edu.infnet.al.matheus_api.exception;

public class JogoNaoEncontradoException extends RuntimeException {
    public JogoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}