package com.example.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infinispan.commons.marshall.AdvancedExternalizer;
import org.infinispan.commons.util.Util;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employees")
//@SerializeWith(Employees.EmpExternalizer.class)
public class Employees implements Serializable {

	public enum Gender {
		M, F
	}

	private static final long serialVersionUID = 1L;

	public Employees(int empNo, Date birthDate, String firstName, String lastName, Gender gender, Date hireDate) {
		this.empNo = empNo;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.hireDate = hireDate;
	}

	public Employees() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "emp_no")
	private int empNo;

	@Column(name = "birth_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "hire_date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date hireDate;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public static class EmpExternalizer implements AdvancedExternalizer<Employees> {

		private static final long serialVersionUID = 1L;

		@Override
		public void writeObject(ObjectOutput output, Employees emp) throws IOException {
			output.writeInt(emp.empNo);
			output.writeObject(emp.birthDate);
			output.writeUTF(emp.firstName);
			output.writeUTF(emp.lastName);
			output.writeObject(emp.gender);
			output.writeObject(emp.hireDate);
		}

		@Override
		public Employees readObject(ObjectInput input) throws IOException, ClassNotFoundException {
			return new Employees(input.readInt(), (Date) input.readObject(), (String) input.readObject(),
					(String) input.readObject(), (Gender) input.readObject(), (Date) input.readObject());
		}

		@Override
		public Set<Class<? extends Employees>> getTypeClasses() {
			return Util.<Class<? extends Employees>>asSet(Employees.class);
		}

		@Override
		public Integer getId() {
			return 44;
		}
	}

}
