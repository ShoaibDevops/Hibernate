package com.scp.Mapping.OneToOne;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class One_to_One_BI_PrimaryKey 
{
	public static void main(String[] args) throws MyException {
		Address_BPK address_bpk = new Address_BPK(101010, "sangli", null);
		Employee_BPK e1= new Employee_BPK(5001, "RAHUL", null);
		
		address_bpk.setEmp_bpk(e1);
		e1.setAddress_bpk(address_bpk);
		address_bpk.setPincode(e1.getEmpid());
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
	
		session.save(e1);
		//session.save(ad);
		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table
class Employee_BPK {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address_BPK address_bpk;
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
	public Address_BPK getAddress_bpk() {
		return address_bpk;
	}
	public void setAddress_bpk(Address_BPK address_bpk) {
		this.address_bpk = address_bpk;
	}
	public Employee_BPK(int empid, String empname, Address_BPK address_bpk) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address_bpk = address_bpk;
	}
	public Employee_BPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee_BPK [empid=" + empid + ", empname=" + empname + ", address_bpk=" + address_bpk + "]";
	}
	
	
}

@Entity
@Table
class Address_BPK {
	@Id
	private int pincode;
	@Column
	private String city;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Employee_BPK emp_bpk;
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
	public Employee_BPK getEmp_bpk() {
		return emp_bpk;
	}
	public void setEmp_bpk(Employee_BPK emp_bpk) {
		this.emp_bpk = emp_bpk;
	}
	public Address_BPK(int pincode, String city, Employee_BPK emp_bpk) {
		super();
		this.pincode = pincode;
		this.city = city;
		this.emp_bpk = emp_bpk;
	}
	@Override
	public String toString() {
		return "Address_BPK [pincode=" + pincode + ", city=" + city + ", emp_bpk=" + emp_bpk + "]";
	}
	public Address_BPK() {
		super();
		// TODO Auto-generated constructor stub
	}
}