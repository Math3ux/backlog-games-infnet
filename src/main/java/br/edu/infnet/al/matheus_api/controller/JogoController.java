package br.edu.infnet.al.matheus_api.controller;

import br.edu.infnet.al.matheus_api.model.Jogo;
import br.edu.infnet.al.matheus_api.model.JogoDigital;
import br.edu.infnet.al.matheus_api.model.JogoFisico;
import br.edu.infnet.al.matheus_api.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> listarTodos() {
        return ResponseEntity.ok(jogoService.obterLista());
    }

    @GetMapping("/backlog")
    public ResponseEntity<List<Jogo>> listarBacklog() {
        return ResponseEntity.ok(jogoService.listarBacklog());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(jogoService.obterPorId(id));
    }

    @PostMapping("/digital")
    public ResponseEntity<Jogo> incluirDigital(@Valid @RequestBody JogoDigital jogo) {
        Jogo salvo = jogoService.incluir(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/fisico")
    public ResponseEntity<Jogo> incluirFisico(@Valid @RequestBody JogoFisico jogo) {
        Jogo salvo = jogoService.incluir(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/digital/{id}")
    public ResponseEntity<Jogo> alterarDigital(@PathVariable Long id, @Valid @RequestBody JogoDigital jogoAtualizado) {
        Jogo alterado = jogoService.alterar(id, jogoAtualizado);
        return ResponseEntity.ok(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        jogoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}