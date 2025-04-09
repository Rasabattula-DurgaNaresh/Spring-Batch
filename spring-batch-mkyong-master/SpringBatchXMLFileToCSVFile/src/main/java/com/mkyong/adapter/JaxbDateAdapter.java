package com.mkyong.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbDateAdapter extends XmlAdapter<String, Date>{
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date unmarshal(String date) throws Exception {
		return DATE_FORMAT.parse(date);
	}

	@Override
	public String marshal(Date date) throws Exception {
		return DATE_FORMAT.format(date);
	}
}
