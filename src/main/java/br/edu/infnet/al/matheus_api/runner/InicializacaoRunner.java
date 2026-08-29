package br.edu.infnet.al.matheus_api.runner;

import br.edu.infnet.al.matheus_api.model.Desenvolvedora;
import br.edu.infnet.al.matheus_api.model.JogoDigital;
import br.edu.infnet.al.matheus_api.model.JogoFisico;
import br.edu.infnet.al.matheus_api.service.JogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InicializacaoRunner implements CommandLineRunner {

    private final JogoService jogoService;

    public InicializacaoRunner(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @Override
    public void run(String... args) {
        System.out.println("--- INICIANDO ETAPA 2: CAMADA DE SERVIÇO E STREAMS ---");

        Desenvolvedora devRemedy = new Desenvolvedora(1L, "Remedy Entertainment", "Finlândia", LocalDate.of(1995, 8, 18));
        Desenvolvedora devMisfits = new Desenvolvedora(2L, "Misfits Attic", "EUA", LocalDate.of(2011, 1, 1));

        JogoDigital control = new JogoDigital(101L, "Control", 129.90, true, 9, 42.0, "Steam", true);
        JogoDigital duskers = new JogoDigital(102L, "Duskers", 37.99, false, 8, 0.2, "Steam", true);
        JogoFisico alanWake = new JogoFisico(103L, "Alan Wake Remastered", 150.00, false, 7, "Novo", true);

        devRemedy.adicionarJogo(control);
        devRemedy.adicionarJogo(alanWake);
        devMisfits.adicionarJogo(duskers);

        jogoService.incluir(control);
        jogoService.incluir(duskers);
        jogoService.incluir(alanWake);

        System.out.println("\n--- 1. Todos os Jogos Cadastrados ---");
        jogoService.obterLista().forEach(System.out::println);

        System.out.println("\n--- 2. Filtragem: Apenas Backlog (Não finalizados) ---");
        jogoService.listarBacklog().forEach(System.out::println);

        System.out.println("\n--- 3. Ordenação: Melhores Notas ---");
        jogoService.ordenarPorNota().forEach(System.out::println);

        System.out.println("\n--- 4. Transformação: Títulos da Remedy ---");
        jogoService.listarTitulosPorDesenvolvedora("Remedy Entertainment").forEach(System.out::println);

        System.out.println("\n--- 5. Testando Exceção (Buscando ID Inexistente) ---");
        try {
            jogoService.obterPorId(999L);
        } catch (Exception e) {
            System.out.println("Erro capturado com sucesso: " + e.getMessage());
        }
    }
}