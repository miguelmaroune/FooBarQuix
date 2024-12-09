package com.foo.bar.batch.data_batch_processor.infrastructure;

import com.foo.bar.batch.data_batch_processor.application.service.FooBarQuixService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class FooBarQuixBatchConfig {

    private final FooBarQuixService fooBarQuixService;

    @Autowired
    public FooBarQuixBatchConfig(FooBarQuixService fooBarQuixService) {
        this.fooBarQuixService = fooBarQuixService;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(
                new ClassPathResource("org/springframework/batch/core/schema-h2.sql")
        ));
        return initializer;
    }

    @Bean
    public FlatFileItemReader<String> reader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("numberReader")
                .resource(new FileSystemResource("input.txt"))
                .lineMapper((line, lineNumber) -> line.trim())
                .build();
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return item -> {
            try {
                int number = Integer.parseInt(item.trim());
                return fooBarQuixService.numberToFooBarQuixTransformation(number);
            } catch (NumberFormatException e) {
                return null;
            }
        };
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output.txt"));
        LineAggregator<String> lineAggregator = new PassThroughLineAggregator<>();
        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean
    public Job createJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("transformJob", jobRepository)
                .flow(createStep(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    @Bean
    public Step createStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("transformStep", jobRepository)
                .<String, String> chunk(3, platformTransactionManager)
                .allowStartIfComplete(true)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
