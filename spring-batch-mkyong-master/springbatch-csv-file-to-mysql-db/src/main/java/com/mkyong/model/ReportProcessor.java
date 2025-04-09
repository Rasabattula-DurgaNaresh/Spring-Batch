package com.mkyong.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;

public class ReportProcessor implements ItemProcessor<Report, Report> {

	SimpleDateFormat SDF = new SimpleDateFormat("mm/dd/yyyy");

	@Override
	public Report process(Report item) throws Exception {
		Report report = new Report();
		
		report.setDate(getFormattedDate(item.getDate()));
		report.setClicks(item.getClicks());
		report.setEarning(item.getEarning());
		report.setImpressions(item.getImpressions());

		return report;
	}
	
	private String getFormattedDate(String strDate) throws ParseException{
		java.util.Date utilDate = SDF.parse(strDate);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate.toString();
	}

}
