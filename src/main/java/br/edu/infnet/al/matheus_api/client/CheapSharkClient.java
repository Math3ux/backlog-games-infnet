package br.edu.infnet.al.matheus_api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "cheapshark", url = "https://www.cheapshark.com/api/1.0", configuration = FeignConfig.class)
public interface CheapSharkClient {

    @GetMapping("/games")
    List<JogoExternoDTO> buscarJogosPorTitulo(@RequestParam("title") String titulo);
}