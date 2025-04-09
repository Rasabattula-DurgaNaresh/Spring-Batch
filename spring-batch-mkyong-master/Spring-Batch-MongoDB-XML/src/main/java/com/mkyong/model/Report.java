package com.mkyong.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Report {

	@XmlAttribute(name = "id")
	private int id;

	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(JaxbDateAdapter.class) // for date formatting
	private Date date;

	@XmlElement(name = "impression")
	private long impression;

	@XmlElement(name = "clicks")
	private int clicks;

	@XmlElement(name = "earning")
	@XmlJavaTypeAdapter(JaxbBigDecimalAdapter.class)
	private BigDecimal earning;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getImpression() {
		return impression;
	}

	public void setImpression(long impression) {
		this.impression = impression;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public BigDecimal getEarning() {
		return earning;
	}

	public void setEarning(BigDecimal earning) {
		this.earning = earning;
	}
}