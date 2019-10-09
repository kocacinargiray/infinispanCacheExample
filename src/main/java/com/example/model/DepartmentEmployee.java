package com.example.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infinispan.commons.marshall.AdvancedExternalizer;
import org.infinispan.commons.marshall.SerializeWith;
import org.infinispan.commons.util.Util;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dept_emp")
@SerializeWith(DepartmentEmployee.DeptEmpExternalizer.class)
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

	public DepartmentEmployee(DepartmentsCompositeKeys dcp, Date fromDate, Date toDate) {
		this.cp = dcp;
		this.fromDate = fromDate;
		this.toDate = toDate;
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

	public static class DeptEmpExternalizer implements AdvancedExternalizer<DepartmentEmployee> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void writeObject(ObjectOutput output, DepartmentEmployee deptEmp) throws IOException {
			System.out.println("DeptEmp Obje okundu.");
			output.writeObject(deptEmp.cp);
			output.writeObject(deptEmp.fromDate);
			output.writeObject(deptEmp.toDate);
		}

		@Override
		public DepartmentEmployee readObject(ObjectInput input) throws IOException, ClassNotFoundException {
			System.out.println("DeptEmp Obje yaz�ld�.");
			return new DepartmentEmployee((DepartmentsCompositeKeys) input.readObject(), (Date) input.readObject(),
					(Date) input.readObject());
		}

		@Override
		public Set<Class<? extends DepartmentEmployee>> getTypeClasses() {
			System.out.println("DepartmentEmployee Obje");
			return Util.<Class<? extends DepartmentEmployee>>asSet(DepartmentEmployee.class);
		}

		@Override
		public Integer getId() {
			return 11;
		}
	}
}
