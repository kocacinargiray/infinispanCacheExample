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
@Table(name = "dept_emp")
public class DepartmentEmployee implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	public DepartmentsCompositeKeys cp;

	public DepartmentEmployee() {
		super();
	}

	public DepartmentEmployee(DepartmentsCompositeKeys dcp) {
		super();
		this.cp = dcp;
	}

	@Column(name = "from_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;

	@Column(name = "to_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
