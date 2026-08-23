package br.edu.infnet.al.matheus_api.runner;

import br.edu.infnet.al.matheus_api.model.Desenvolvedora;
import br.edu.infnet.al.matheus_api.model.JogoDigital;
import br.edu.infnet.al.matheus_api.model.JogoFisico;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InicializacaoRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {


        Desenvolvedora devRemedy = new Desenvolvedora(1L, "Remedy Entertainment", "Finlândia", LocalDate.of(1995, 8, 18));
        Desenvolvedora devMisfits = new Desenvolvedora(2L, "Misfits Attic", "EUA", LocalDate.of(2011, 1, 1));


        JogoDigital control = new JogoDigital(101L, "Control", 129.90, true, 9, 42.0, "Steam", true);
        JogoDigital duskers = new JogoDigital(102L, "Duskers", 37.99, false, 8, 0.2, "Steam", true);

        JogoFisico alanWake = new JogoFisico(103L, "Alan Wake Remastered", 150.00, false, 8, "Novo", true);


        devRemedy.adicionarJogo(control);
        devRemedy.adicionarJogo(alanWake);
        devMisfits.adicionarJogo(duskers);


        System.out.println("\n--- Desenvolvedoras ---");
        System.out.println(devRemedy);
        System.out.println(devMisfits);

        System.out.println("\n--- Catálogo de Jogos ---");
        System.out.println(control);
        System.out.println(duskers);
        System.out.println(alanWake);
    }
}