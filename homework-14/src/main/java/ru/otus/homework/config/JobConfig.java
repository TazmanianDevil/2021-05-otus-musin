package ru.otus.homework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.lang.NonNull;
import ru.otus.homework.model.mongo.Book;
import ru.otus.homework.service.BookTransformer;
import ru.otus.homework.service.LibraryService;

import java.util.HashMap;
import java.util.List;


@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    public static final String MIGRATE_LIBRARY_JOB = "migrateLibraryJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public MongoItemReader<Book> mongoItemReader(MongoOperations mongoOperations) {
        logger.info("mO: {}", mongoOperations);
        return new MongoItemReaderBuilder<Book>()
                .name("mongoPersonItemReader")
                .template(mongoOperations)
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, ru.otus.homework.model.jpa.Book> processor(BookTransformer transformer) {
        return transformer::transform;
    }

    @StepScope
    @Bean
    public ItemWriter<ru.otus.homework.model.jpa.Book> itemWriter(LibraryService libraryService) {
        return books -> books.forEach(libraryService::saveBook);
    }

    @Bean
    public Job migrateBookJob(Step transformBookStep, Step cleanUpStep) {
        return jobBuilderFactory.get(MIGRATE_LIBRARY_JOB)
                .incrementer(new RunIdIncrementer())
                .flow(transformBookStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step transformBookStep(@Qualifier("mongoItemReader") ItemReader<Book> reader, ItemWriter<ru.otus.homework.model.jpa.Book> writer,
                                  ItemProcessor<Book, ru.otus.homework.model.jpa.Book> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Book, ru.otus.homework.model.jpa.Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(@NonNull Book o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи");
                    }

                    public void afterWrite(@NonNull List list) {
                        logger.info("Конец записи");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Book o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(@NonNull Book o, ru.otus.homework.model.jpa.Book o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(@NonNull Book o, @NonNull Exception e) {
                        logger.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Начало пачки");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Конец пачки");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        logger.info("Ошибка пачки");
                    }
                })
//                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

}
