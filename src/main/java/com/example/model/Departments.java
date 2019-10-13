package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
//@SerializeWith(Departments.DeptExternalizer.class)
public class Departments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dept_no")
	private String dept_no;

	@Column(name = "dept_name")
	private String dept_name;

	public Departments(String dept_no, String dept_name) {
		this.dept_no = dept_no;
		this.dept_name = dept_name;
	}

	public Departments() {
		// TODO Auto-generated constructor stub
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	/*
	 * public static class DeptExternalizer implements
	 * AdvancedExternalizer<Departments> { private static final long
	 * serialVersionUID = 1L;
	 * 
	 * @Override public void writeObject(ObjectOutput output, Departments dept)
	 * throws IOException { output.writeUTF(dept.dept_no);
	 * output.writeUTF(dept.dept_name); }
	 * 
	 * @Override public Departments readObject(ObjectInput input) throws
	 * IOException, ClassNotFoundException { return new Departments((String)
	 * input.readObject(), (String) input.readObject()); }
	 * 
	 * @Override public Set<Class<? extends Departments>> getTypeClasses() { return
	 * Util.<Class<? extends Departments>>asSet(Departments.class); }
	 * 
	 * @Override public Integer getId() { return 33; } }
	 */
}
