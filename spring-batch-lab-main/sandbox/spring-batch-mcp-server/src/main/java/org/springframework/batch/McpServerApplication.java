package org.springframework.batch;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
public class McpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider toolCallbackProvider(BatchService batchService) {
		return MethodToolCallbackProvider.builder().toolObjects(batchService).build();
	}

	@Bean
	public DataSource dataSource(Environment environment) {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUser(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

}