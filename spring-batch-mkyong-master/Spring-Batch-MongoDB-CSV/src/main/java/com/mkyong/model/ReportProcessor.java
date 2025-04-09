package com.mkyong.model;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

public class ReportProcessor implements ItemProcessor<Report, Report> {
//	SimpleDateFormat output= new SimpleDateFormat("yyyy/MM/dd");
//	final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-ddThh:mm:sssZ");
	
	@Override
	public Report process(Report item) throws Exception {
		/*Report report = new Report();
		report.setClicks(item.getClicks());
		report.setEarning(item.getEarning());
		report.setId(item.getId());
		report.setImpression(item.getImpression());
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final ZonedDateTime parsed = ZonedDateTime.parse(item.getDate().toString(), inputFormat);
		String dateStr = format.format(parsed);
		report.setDate(output.parse(dateStr));*/
		
		return item;
	}
}
