package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "salaries")
public class Salaries implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	public SalariesCompositeKeys cp;

	public Salaries() {
		super();
	}

	public Salaries(SalariesCompositeKeys cp) {
		super();
		this.cp = cp;
	}

	@Column
	private int salary;

	@Column(name = "to_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
