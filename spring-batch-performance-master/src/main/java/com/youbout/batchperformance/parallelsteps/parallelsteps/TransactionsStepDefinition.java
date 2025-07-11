package com.youbout.batchperformance.parallelsteps.parallelsteps;

import com.youbout.batchperformance.parallelsteps.vo.SourceTransactionVO;
import com.youbout.batchperformance.parallelsteps.vo.SourceTransactionVORowMapper;
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
public class TransactionsStepDefinition {
    @Bean
    public Step transactionStep(StepBuilderFactory stepBuilderFactory,
                                ItemReader<SourceTransactionVO> transactionReader,
                                ItemProcessor<SourceTransactionVO, SourceTransactionVO> transactionProcessor,
                                JdbcBatchItemWriter<SourceTransactionVO> transactionWriter) throws Exception {
        return stepBuilderFactory
                .get("Synchronous Transactions Import : Read -> Process -> Write ")
                .<SourceTransactionVO, SourceTransactionVO>chunk(1000)
                .reader(transactionReader)
                .processor(transactionProcessor)
                .writer(transactionWriter)
                .listener(new ItemCountChunkListener())
                .build();
    }


    @Bean
    public ItemReader<SourceTransactionVO> transactionReader(DataSource dataSource) throws Exception {

        return new JdbcPagingItemReaderBuilder<SourceTransactionVO>()
                .name("Reader")
                .dataSource(dataSource)
                .selectClause("SELECT * ")
                .fromClause("FROM source_transactions ")
                .whereClause("WHERE ID <= 1000000 ")
                .sortKeys(Collections.singletonMap("ID", Order.ASCENDING))
                .rowMapper(new SourceTransactionVORowMapper())
                .build();
    }

    @Bean
    public ItemProcessor<SourceTransactionVO, SourceTransactionVO> transactionProcessor() {
        return (transaction) -> {
            Thread.sleep(1);
            return transaction;
        };
    }

    @Bean
    public JdbcBatchItemWriter<SourceTransactionVO> transactionWriter(DataSource dataSource) {
        JdbcBatchItemWriter<SourceTransactionVO> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO destination_transactions (id, transaction_date, account_id, amount, created_at) VALUES (:id, :transactionDate, :accountId, :amount, :createdAt)");
        writer.setItemSqlParameterSourceProvider(BeanPropertySqlParameterSource::new);
        writer.afterPropertiesSet();
        return writer;
    }
}
