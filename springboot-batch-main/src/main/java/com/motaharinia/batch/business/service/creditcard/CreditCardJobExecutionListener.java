package com.motaharinia.batch.business.service.creditcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:51:47 <br>
 * Description: <br>
 */
@Component
public class CreditCardJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("afterJob: " + jobExecution.getStatus());
    }
}
