package com.mkyong.converter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.mkyong.model.Report;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

//http://xstream.codehaus.org/converter-tutorial.html
public class ReportConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		//we only need "Report" object
		return type.equals(Report.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

		Report report = new Report();

		//get attribute
		report.setId(Integer.valueOf(reader.getAttribute("id")));
		reader.moveDown(); //get date

		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(reader.getValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		report.setDate(date);
		// Select the parent node as current node.
		reader.moveUp();
		// Select the current child as current node
		reader.moveDown(); //get impression

		String impression = reader.getValue();
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number = 0;
		try {
			number = format.parse(impression);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		report.setImpression(number.longValue());

		// Select the parent node as current node.
		reader.moveUp();
		// Select the current child as current node
		reader.moveDown(); //get click
		report.setClicks(Integer.valueOf(reader.getValue()));
		
		// Select the parent node as current node.
		reader.moveUp();
		// Select the current child as current node
		reader.moveDown(); //get earning
		report.setEarning(new BigDecimal(reader.getValue()));
		
		// Select the parent node as current node.
		reader.moveUp();
		return report;
	}
}