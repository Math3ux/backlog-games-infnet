package br.edu.infnet.al.matheus_api.jogo;

import br.edu.infnet.al.matheus_api.integracao.CheapSharkClient;
import br.edu.infnet.al.matheus_api.integracao.JogoExternoDTO;
import br.edu.infnet.al.matheus_api.mensageria.JogoEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CheapSharkClient cheapSharkClient;
    private final JogoEventPublisher eventPublisher;

    public JogoService(JogoRepository jogoRepository, CheapSharkClient cheapSharkClient, JogoEventPublisher eventPublisher) {
        this.jogoRepository = jogoRepository;
        this.cheapSharkClient = cheapSharkClient;
        this.eventPublisher = eventPublisher;
    }

    public Jogo incluir(Jogo jogo) {

        try {
            List<JogoExternoDTO> resultados = cheapSharkClient.buscarJogosPorTitulo(jogo.getTitulo());

            if (resultados != null && !resultados.isEmpty()) {
                jogo.setUrlCapa(resultados.get(0).getThumb());
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados na API externa: " + e.getMessage());
        }

        Jogo jogoSalvo = jogoRepository.save(jogo);
        eventPublisher.publicarJogoCadastrado(jogoSalvo.getTitulo());
        return jogoSalvo;
    }

    public List<Jogo> obterLista() {
        return jogoRepository.findAll();
    }

    public Jogo obterPorId(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() -> new JogoNaoEncontradoException("Jogo não encontrado."));
    }

    public Jogo alterar(Long id, Jogo jogoAtualizado) {

        if (!jogoRepository.existsById(id)) {
            throw new JogoNaoEncontradoException("Jogo com ID " + id + " não encontrado para alteração.");
        }

        jogoAtualizado.setId(id);

        return jogoRepository.save(jogoAtualizado);
    }

    public void excluir(Long id) {
        if (!jogoRepository.existsById(id)) {
            throw new JogoNaoEncontradoException("Jogo não encontrado.");
        }
        jogoRepository.deleteById(id);
    }

    public List<Jogo> listarBacklog() {
        return jogoRepository.findByIsFinalizadoFalse();
    }
}