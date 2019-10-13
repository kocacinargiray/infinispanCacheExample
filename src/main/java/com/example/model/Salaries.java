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
@Table(name = "salaries")
@SerializeWith(Salaries.SalaryExternalizer.class)
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

	public Salaries(SalariesCompositeKeys cp, int salary, Date toDate) {
		this.cp = cp;
		this.salary = salary;
		this.toDate = toDate;
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

	public static class SalaryExternalizer implements AdvancedExternalizer<Salaries> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void writeObject(ObjectOutput output, Salaries salary) throws IOException {
			output.writeObject(salary.cp);
			output.writeInt(salary.salary);
			output.writeObject(salary.toDate);
		}

		@Override
		public Salaries readObject(ObjectInput input) throws IOException, ClassNotFoundException {
			return new Salaries((SalariesCompositeKeys) input.readObject(), input.readInt(), (Date) input.readObject());
		}

		@Override
		public Set<Class<? extends Salaries>> getTypeClasses() {
			return Util.<Class<? extends Salaries>>asSet(Salaries.class);
		}

		@Override
		public Integer getId() {
			return 55;
		}
	}
}
