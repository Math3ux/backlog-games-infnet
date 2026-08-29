package br.edu.infnet.al.matheus_api.service;

import br.edu.infnet.al.matheus_api.exception.JogoNaoEncontradoException;
import br.edu.infnet.al.matheus_api.model.Jogo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JogoService {

    private final Map<Long, Jogo> repositorio = new HashMap<>();

    public Jogo incluir(Jogo jogo) {

        if (jogo.getId() == null || repositorio.containsKey(jogo.getId())) {
            throw new IllegalArgumentException("ID inválido ou Jogo já cadastrado.");
        }

        repositorio.put(jogo.getId(), jogo);
        return jogo;
    }

    public List<Jogo> obterLista() {

        return new ArrayList<>(repositorio.values());

    }

    public Jogo obterPorId(Long id) {

        return Optional.ofNullable(repositorio.get(id)).orElseThrow(() -> new JogoNaoEncontradoException("Jogo com ID " + id + " não encontrado."));
    }

    public Jogo alterar(Long id, Jogo jogoAtualizado) {
        obterPorId(id);
        repositorio.put(id, jogoAtualizado);
        return jogoAtualizado;
    }

    public void excluir(Long id) {
        obterPorId(id); // Valida se existe antes de remover
        repositorio.remove(id);
    }

    public List<Jogo> listarBacklog() {
        return repositorio.values().stream()
                .filter(jogo -> !jogo.getIsFinalizado())
                .collect(Collectors.toList());
    }

    public List<Jogo> ordenarPorNota() {
        return repositorio.values().stream()
                .sorted(Comparator.comparing(Jogo::getNota).reversed())
                .collect(Collectors.toList());
    }

    public List<String> listarTitulosPorDesenvolvedora(String nomeDev) {
        return repositorio.values().stream()
                .filter(jogo -> jogo.getDesenvolvedora() != null)
                .filter(jogo -> jogo.getDesenvolvedora().getNome().equalsIgnoreCase(nomeDev))
                .map(Jogo::getTitulo) // Transforma o objeto Jogo em String (apenas o título)
                .collect(Collectors.toList());
    }
}