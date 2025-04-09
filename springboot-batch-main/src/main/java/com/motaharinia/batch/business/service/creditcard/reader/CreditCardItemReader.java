package com.motaharinia.batch.business.service.creditcard.reader;


import com.motaharinia.batch.presentation.creditcard.CreditCardModel;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.stereotype.Component;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-03 <br>
 * Time: 01:39:40 <br>
 * Description: <br>
 */
public class CreditCardItemReader implements ItemReader<CreditCardModel> {

    private PoiItemReader<CreditCardModel> itemReader = new PoiItemReader<CreditCardModel>();

    @BeforeStep
    public void before(StepExecution stepExecution) {

    }

    public CreditCardItemReader(ResourceLoader resourceLoader, String readerSourceFile) throws Exception {
        System.out.println("CreditCardItemReader() readerSourceFile:"+readerSourceFile);
        itemReader.setResource(new ClassPathResource(readerSourceFile));
//       Resource resource= resourceLoader.getResource("classpath:creditcard.xlsx");
//        System.out.println("resource.exists():"+resource.exists());
//        itemReader.setResource(resource);
        itemReader.setLinesToSkip(1);
//        itemReader.setStrict(true);
        itemReader.setRowMapper(new CreditCardExcelRowMapper());
        itemReader.afterPropertiesSet();
        itemReader.open(new ExecutionContext());
    }

    @Override
    public CreditCardModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return itemReader.read();
    }

}
