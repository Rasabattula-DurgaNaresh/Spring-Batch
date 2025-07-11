package com.youbout.batchperformance.parallelsteps.sequentialsteps;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SequentialSynchronousVersionPerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SequentialSynchronousVersionPerformanceApplication.class, args);
    }

    @Bean
    public Job synJob(JobBuilderFactory jobBuilderFactory,
                      Step transactionStep,
                      Step accountStep,
                      Step aggregationStep) {
        return jobBuilderFactory
                .get("Part 03: Parallel Steps: Sequential JOB version")
                .incrementer(new RunIdIncrementer())
                .flow(transactionStep)
                .next(accountStep)
                .next(aggregationStep)
                .end()
                .build();
    }
}
