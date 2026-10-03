package br.edu.infnet.al.matheus_api.batch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
@Tag(name = "Processamento em Lote (Spring Batch)", description = "Importação em massa de jogos via CSV")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job importarJogosJob;

    public BatchController(JobLauncher jobLauncher, Job importarJogosJob) {
        this.jobLauncher = jobLauncher;
        this.importarJogosJob = importarJogosJob;
    }

    @PostMapping("/importar-jogos")
    @Operation(summary = "Dispara o Job do Spring Batch para importar jogos do arquivo CSV")
    public ResponseEntity<String> executarBatch() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            var execution = jobLauncher.run(importarJogosJob, jobParameters);
            return ResponseEntity.ok("Job executado com sucesso! Status: " + execution.getStatus());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao executar o Batch: " + e.getMessage());
        }
    }
}