package com.inform_set.model;

import java.io.Serializable;

public class Inform_SetVO implements Serializable{

	private static final long serialVersionUID = -8947956968263648314L;

	private String is_no;
	private String emp_no;
	private String is_cont;
	private java.sql.Date is_date;
	
	public String getIs_no() {
		return is_no;
	}
	
	public void setIs_no(String is_no) {
		this.is_no = is_no;
	}
	
	public String getEmp_no() {
		return emp_no;
	}
	
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
	public String getIs_cont() {
		return is_cont;
	}
	
	public void setIs_cont(String is_cont) {
		this.is_cont = is_cont;
	}
	
	public java.sql.Date getIs_date() {
		return is_date;
	}
	
	public void setIs_date(java.sql.Date is_date) {
		this.is_date = is_date;
	}
	
}
