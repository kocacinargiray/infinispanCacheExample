package com.example.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class TitleCompositeKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_no")
	private int empNo;

	@Column
	private String title;

	@Column(name = "from_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("yyyymmdd");
		String date = format.format(fromDate);
		return String.valueOf(empNo) + title + date;
	}

	public TitleCompositeKey getFromJson(JSONObject json) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyymmdd");
		Date date = new Date();
		date = format.parse(json.getString("from_date"));
		this.setFromDate(date);
		this.setEmpNo(json.getInt("emp_no"));
		this.setTitle(json.getString("title"));
		return this;
	}

}
