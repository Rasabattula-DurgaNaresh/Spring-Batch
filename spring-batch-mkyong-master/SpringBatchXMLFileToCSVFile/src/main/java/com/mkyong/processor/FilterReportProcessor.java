package com.mkyong.processor;

import org.springframework.batch.item.ItemProcessor;

import com.mkyong.model.Report;

public class FilterReportProcessor implements ItemProcessor<Report, Report>{

	@Override
	public Report process(Report item) throws Exception {
		if(item.getAge() == 30)
			return null;
		
		return item;
	}
}