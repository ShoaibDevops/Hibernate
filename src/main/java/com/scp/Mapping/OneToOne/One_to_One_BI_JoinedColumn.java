package com.scp.Mapping.OneToOne;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class One_to_One_BI_JoinedColumn 
{
	public static void main(String[] args) throws MyException {
		Address_BJC address_bjc = new Address_BJC(101010, "sangli", null);
		Employee_BJC e1= new Employee_BJC(5001, "RAHUL", null);
		
		address_bjc.setEmp_bjc(e1);
		e1.setAddress_bjc(address_bjc);
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
	
		session.save(e1);
		//session.save(ad);
		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table
class Employee_BJC {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private Address_BJC address_bjc;
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Address_BJC getAddress_bjc() {
		return address_bjc;
	}
	public void setAddress_bjc(Address_BJC address_bjc) {
		this.address_bjc = address_bjc;
	}
	public Employee_BJC(int empid, String empname, Address_BJC address_bjc) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address_bjc = address_bjc;
	}
	public Employee_BJC() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee_BJC [empid=" + empid + ", empname=" + empname + ", address_bjc=" + address_bjc + "]";
	}
}

@Entity
@Table
class Address_BJC {
	@Id
	private int pincode;
	@Column
	private String city;
	@OneToOne(cascade=CascadeType.ALL)
	private Employee_BJC emp_bjc;
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Employee_BJC getEmp_bjc() {
		return emp_bjc;
	}
	public void setEmp_bjc(Employee_BJC emp_bjc) {
		this.emp_bjc = emp_bjc;
	}
	public Address_BJC() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address_BJC(int pincode, String city, Employee_BJC emp_bjc) {
		super();
		this.pincode = pincode;
		this.city = city;
		this.emp_bjc = emp_bjc;
	}
	@Override
	public String toString() {
		return "Address_BJC [pincode=" + pincode + ", city=" + city + ", emp_bjc=" + emp_bjc + "]";
	}
}
