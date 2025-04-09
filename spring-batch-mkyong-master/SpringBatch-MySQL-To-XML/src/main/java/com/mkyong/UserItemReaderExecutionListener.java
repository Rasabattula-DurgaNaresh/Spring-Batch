package com.mkyong;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * This is purely to get know how many User records it processed
 * @author PA
 *
 */
public class UserItemReaderExecutionListener implements ItemReader<String>, StepExecutionListener{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("StepExecutionListener - beforeStep");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("StepExecutionListener - afterStep");
		int readCount = stepExecution.getReadCount();
		System.out.println("Read Count ?? :: "+readCount);
		return null;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
