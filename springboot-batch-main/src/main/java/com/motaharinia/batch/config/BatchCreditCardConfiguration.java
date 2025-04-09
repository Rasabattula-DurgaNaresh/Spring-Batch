package com.motaharinia.batch.config;


import com.motaharinia.batch.business.service.creditcard.CreditCardJobExecutionListener;
import com.motaharinia.batch.business.service.creditcard.processor.CreditCardItemProcessListener;
import com.motaharinia.batch.business.service.creditcard.processor.CreditCardItemProcessor;
import com.motaharinia.batch.business.service.creditcard.reader.CreditCardItemReader;
import com.motaharinia.batch.business.service.creditcard.reader.CreditCardItemReaderListener;
import com.motaharinia.batch.business.service.creditcard.writer.CreditCardItemWriter;
import com.motaharinia.batch.business.service.creditcard.writer.CreditCardItemWriterListener;
import com.motaharinia.batch.persistence.orm.creditcard.CreditCard;
import com.motaharinia.batch.persistence.orm.creditcard.CreditCardRepository;
import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-02 <br>
 * Time: 22:49:18 <br>
 * Description: <br>
 */
@Configuration
@EnableBatchProcessing
public class BatchCreditCardConfiguration {

    @Value("${my.reader.sourcefile}")
    private String readerSourceFile;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private CreditCardRepository respository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public CreditCardItemReader reader() throws Exception {
        return new CreditCardItemReader(resourceLoader,readerSourceFile);
    }

    @Bean
    public CreditCardItemProcessor processor() {
        return new CreditCardItemProcessor();
    }

    @Bean
    public CreditCardItemWriter writer() {
        return new CreditCardItemWriter(respository);
    }

    @Bean
    public CreditCardJobExecutionListener jobExecutionListener() {
        return new CreditCardJobExecutionListener();
    }

    @Bean
    public CreditCardItemReaderListener readerListener() {
        return new CreditCardItemReaderListener();
    }

    @Bean
    public CreditCardItemProcessListener creditCardItemProcessListener() {
        return new CreditCardItemProcessListener();
    }

    @Bean
    public CreditCardItemWriterListener writerListener() {
        return new CreditCardItemWriterListener();
    }


    @Bean
    public Job job(Step step, CreditCardJobExecutionListener jobExecutionListener) {
        Job job = jobBuilderFactory.get("job1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
        return job;
    }

    @Bean
    public Step step(CreditCardItemReader reader,
                     CreditCardItemWriter writer,
                     CreditCardItemProcessor processor,
                     CreditCardItemReaderListener readerListener,
                     CreditCardItemProcessListener creditCardItemProcessListener,
                     CreditCardItemWriterListener writerListener) {

        TaskletStep step = stepBuilderFactory.get("step1")
                .<CreditCardModel, CreditCard>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readerListener)
                .listener(creditCardItemProcessListener)
                .listener(writerListener)
                .build();
        return step;
    }
}
