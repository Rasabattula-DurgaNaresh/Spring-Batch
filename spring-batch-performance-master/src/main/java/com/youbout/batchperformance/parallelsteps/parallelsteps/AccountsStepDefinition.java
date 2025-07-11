package com.youbout.batchperformance.parallelsteps.parallelsteps;

import com.youbout.batchperformance.parallelsteps.vo.SourceAccountVO;
import com.youbout.batchperformance.parallelsteps.vo.SourceAccountVORowMapper;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class AccountsStepDefinition {
    @Bean
    public Step accountStep(StepBuilderFactory stepBuilderFactory,
                            ItemReader<SourceAccountVO> accountReader,
                            ItemProcessor<SourceAccountVO, SourceAccountVO> accountProcessor,
                            JdbcBatchItemWriter<SourceAccountVO> accountWriter) throws Exception {
        return stepBuilderFactory
                .get("Synchronous Accounts Import : Read -> Process -> Write ")
                .<SourceAccountVO, SourceAccountVO>chunk(1000)
                .reader(accountReader)
                .processor(accountProcessor)
                .writer(accountWriter)
                .listener(new ItemCountChunkListener())
                .build();
    }


    @Bean
    public ItemReader<SourceAccountVO> accountReader(DataSource dataSource) throws Exception {

        return new JdbcPagingItemReaderBuilder<SourceAccountVO>()
                .name("Accounts Reader")
                .dataSource(dataSource)
                .selectClause("SELECT * ")
                .fromClause("FROM source_accounts ")
                .whereClause("WHERE ID <= 1000000 ")
                .sortKeys(Collections.singletonMap("ID", Order.ASCENDING))
                .rowMapper(new SourceAccountVORowMapper())
                .build();
    }

    @Bean
    public ItemProcessor<SourceAccountVO, SourceAccountVO> accountProcessor() {
        return (transaction) -> {
            Thread.sleep(1);
            return transaction;
        };
    }

    @Bean
    public JdbcBatchItemWriter<SourceAccountVO> accountWriter(DataSource dataSource) {
        JdbcBatchItemWriter<SourceAccountVO> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO destination_accounts (id, account_number, created_at) VALUES (:id, :accountNumber, :createdAt)");
        writer.setItemSqlParameterSourceProvider(BeanPropertySqlParameterSource::new);
        writer.afterPropertiesSet();
        return writer;
    }
}
