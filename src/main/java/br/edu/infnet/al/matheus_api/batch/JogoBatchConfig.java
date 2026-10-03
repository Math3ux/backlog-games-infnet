package br.edu.infnet.al.matheus_api.batch;

import br.edu.infnet.al.matheus_api.jogo.JogoDigital;
import br.edu.infnet.al.matheus_api.jogo.JogoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JogoBatchConfig {

    @Bean
    public FlatFileItemReader<JogoDigital> reader() {
        BeanWrapperFieldSetMapper<JogoDigital> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(JogoDigital.class);

        return new FlatFileItemReaderBuilder<JogoDigital>()
                .name("jogoItemReader")
                .resource(new ClassPathResource("jogos-importacao.csv"))
                .linesToSkip(1)
                .delimited()
                .names("titulo", "preco", "isFinalizado", "nota", "tamanhoDownloadGb", "lojaVirtual", "compativelPortatil")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public ItemProcessor<JogoDigital, JogoDigital> processor() {
        return jogo -> {
            jogo.setTitulo(jogo.getTitulo().trim());
            jogo.setLojaVirtual(jogo.getLojaVirtual() + " (Importado via Batch)");
            return jogo;
        };
    }

    @Bean
    public ItemWriter<JogoDigital> writer(JogoRepository repository) {
        return items -> repository.saveAll(items);
    }

    @Bean
    public Step stepImportarJogos(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  FlatFileItemReader<JogoDigital> reader,
                                  ItemProcessor<JogoDigital, JogoDigital> processor,
                                  ItemWriter<JogoDigital> writer) {
        return new StepBuilder("stepImportarJogos", jobRepository)
                .<JogoDigital, JogoDigital>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importarJogosJob(JobRepository jobRepository, Step stepImportarJogos) {
        return new JobBuilder("importarJogosJob", jobRepository)
                .start(stepImportarJogos)
                .build();
    }
}