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
@Table(name = "titles")
@SerializeWith(Titles.TitleExternalizer.class)
public class Titles implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	public TitleCompositeKey cp;

	public Titles() {
		super();
	}

	public Titles(TitleCompositeKey cp) {
		super();
		this.cp = cp;
	}

	public Titles(TitleCompositeKey cp, Date toDate) {
		this.cp = cp;
		this.toDate = toDate;
	}

	@Column(name = "to_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public static class TitleExternalizer implements AdvancedExternalizer<Titles> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void writeObject(ObjectOutput output, Titles salary) throws IOException {
			System.out.println("Titles Obje okundu");
			output.writeObject(salary.cp);
			output.writeObject(salary.toDate);
		}

		@Override
		public Titles readObject(ObjectInput input) throws IOException, ClassNotFoundException {
			System.out.println("Titles Obje yazýldý");
			return new Titles((TitleCompositeKey) input.readObject(), (Date) input.readObject());
		}

		@Override
		public Set<Class<? extends Titles>> getTypeClasses() {
			System.out.println("Titles Obje");
			return Util.<Class<? extends Titles>>asSet(Titles.class);
		}

		@Override
		public Integer getId() {
			return 66;
		}
	}
}
