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
@Table(name = "dept_manager")
@SerializeWith(DepartmentManager.DeptMngExternalizer.class)
public class DepartmentManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	public DepartmentsCompositeKeys cp;

	public DepartmentManager() {
		super();
	}

	public DepartmentManager(DepartmentsCompositeKeys dcp) {
		super();
		this.cp = dcp;
	}

	public DepartmentManager(DepartmentsCompositeKeys cp, Date fromDate, Date toDate) {
		this.cp = cp;
		this.fromDate = fromDate;
		this.toDate = toDate;
		// TODO Auto-generated constructor stub
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

	public static class DeptMngExternalizer implements AdvancedExternalizer<DepartmentManager> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void writeObject(ObjectOutput output, DepartmentManager deptMng) throws IOException {
			output.writeObject(deptMng.cp);
			output.writeObject(deptMng.fromDate);
			output.writeObject(deptMng.toDate);
		}

		@Override
		public DepartmentManager readObject(ObjectInput input) throws IOException, ClassNotFoundException {
			return new DepartmentManager((DepartmentsCompositeKeys) input.readObject(), (Date) input.readObject(),
					(Date) input.readObject());
		}

		@Override
		public Set<Class<? extends DepartmentManager>> getTypeClasses() {
			return Util.<Class<? extends DepartmentManager>>asSet(DepartmentManager.class);
		}

		@Override
		public Integer getId() {
			return 22;
		}
	}
}
