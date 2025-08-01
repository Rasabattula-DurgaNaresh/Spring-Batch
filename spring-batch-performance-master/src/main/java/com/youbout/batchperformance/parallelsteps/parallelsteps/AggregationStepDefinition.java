package com.youbout.batchperformance.parallelsteps.parallelsteps;

import com.youbout.batchperformance.parallelsteps.vo.DestinationAccountBalanceVO;
import com.youbout.batchperformance.parallelsteps.vo.DestinationAccountBalanceVORowMapper;
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
public class AggregationStepDefinition {
    @Bean
    public Step aggregationStep(StepBuilderFactory stepBuilderFactory,
                            ItemReader<DestinationAccountBalanceVO> aggregationReader,
                            ItemProcessor<DestinationAccountBalanceVO, DestinationAccountBalanceVO> aggregationProcessor,
                            JdbcBatchItemWriter<DestinationAccountBalanceVO> aggregationWriter) throws Exception {
        return stepBuilderFactory
                .get("Aggregation Step : Read -> Process -> Write ")
                .<DestinationAccountBalanceVO, DestinationAccountBalanceVO>chunk(1000)
                .reader(aggregationReader)
                .processor(aggregationProcessor)
                .writer(aggregationWriter)
                .listener(new ItemCountChunkListener())
                .build();
    }


    @Bean
    public ItemReader<DestinationAccountBalanceVO> aggregationReader(DataSource dataSource) throws Exception {

        return new JdbcPagingItemReaderBuilder<DestinationAccountBalanceVO>()
                .name("Aggregation Reader")
                .dataSource(dataSource)
                .selectClause("SELECT account_id, sum(amount) as balance ")
                .fromClause("FROM destination_transactions ")
                .whereClause("WHERE ID <= 1000000")
                .groupClause("GROUP BY account_id")
                .sortKeys(Collections.singletonMap("account_id", Order.ASCENDING))
                .rowMapper(new DestinationAccountBalanceVORowMapper())
                .build();
    }

    @Bean
    public ItemProcessor<DestinationAccountBalanceVO, DestinationAccountBalanceVO> aggregationProcessor() {
        return (transaction) -> {
            Thread.sleep(1);
            return transaction;
        };
    }

    @Bean
    public JdbcBatchItemWriter<DestinationAccountBalanceVO> aggregationWriter(DataSource dataSource) {
        JdbcBatchItemWriter<DestinationAccountBalanceVO> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO destination_accounts_balance (account_id, balance) VALUES (:accountId, :balance)");
        writer.setItemSqlParameterSourceProvider(BeanPropertySqlParameterSource::new);
        writer.afterPropertiesSet();
        return writer;
    }
}
