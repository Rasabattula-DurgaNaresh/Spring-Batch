package com.youbout.batchperformance.parallelsteps.parallelsteps;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@SpringBootApplication
@EnableBatchProcessing
public class ParallelStepsVersionPerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParallelStepsVersionPerformanceApplication.class, args);
    }

    @Bean
    public Job parallelStepsJob(JobBuilderFactory jobBuilderFactory,
                      Step transactionStep,
                      Step accountStep,
                      Step aggregationStep) {

        Flow transactionFlow = new FlowBuilder<Flow>("transactionFlow")
                .from(transactionStep)
                .end();

        Flow accountFlow = new FlowBuilder<Flow>("accountFlow")
                .from(accountStep)
                .end();


        Flow parallelFlows = new FlowBuilder<Flow>("parallelFlows")
                .split(new SimpleAsyncTaskExecutor())
                .add(transactionFlow, accountFlow).build();

        return jobBuilderFactory
                .get("Part 03: Parallel Steps: Parallel Steps JOB version")
                .incrementer(new RunIdIncrementer())
                .start(parallelFlows)
                .next(aggregationStep)
                .end()
                .build();
    }
}
