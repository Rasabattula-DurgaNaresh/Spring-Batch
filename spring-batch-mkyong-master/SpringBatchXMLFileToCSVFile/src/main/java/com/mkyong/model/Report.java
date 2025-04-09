package com.mkyong.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mkyong.adapter.JaxbBigDecimalAdapter;
import com.mkyong.adapter.JaxbDateAdapter;

@XmlRootElement(name="record")
@XmlAccessorType(XmlAccessType.NONE)
public class Report {

	@XmlAttribute(name = "refId")
	private int refId;

	@XmlElement(name = "age")
	private String name;

	@XmlElement
	private int age;

	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	@XmlElement
	private Date dob;

	@XmlJavaTypeAdapter(JaxbBigDecimalAdapter.class)
	@XmlElement
	private BigDecimal income;


	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	// for csv demo only
	public String getCsvDob() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(getDob());
	}
}
