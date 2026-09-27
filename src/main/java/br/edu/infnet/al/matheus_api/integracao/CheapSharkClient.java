package br.edu.infnet.al.matheus_api.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "integracao-service", url = "${servico.integracao.url}")
public interface CheapSharkClient {

    @GetMapping("/api/externa/jogos")
    List<JogoExternoDTO> buscarJogosPorTitulo(@RequestParam("titulo") String titulo);
}