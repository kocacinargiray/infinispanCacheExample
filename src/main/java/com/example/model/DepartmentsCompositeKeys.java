package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.infinispan.distribution.group.Group;
import org.json.JSONObject;

@Embeddable
public class DepartmentsCompositeKeys implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_no", nullable = false)
	private int empNo;

	@Column(name = "dept_no", nullable = false)
	private String deptNo;

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	@Group
	public String toString() {
		return String.valueOf(empNo) + deptNo;
	}

	public DepartmentsCompositeKeys getFromJson(JSONObject json) throws Exception {
		this.setEmpNo(json.getInt("emp_no"));
		this.setDeptNo(json.getString("dept_no"));
		return this;
	}
}
