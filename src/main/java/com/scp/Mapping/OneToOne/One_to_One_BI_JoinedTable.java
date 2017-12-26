package com.scp.Mapping.OneToOne;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class One_to_One_BI_JoinedTable 
{
	public static void main(String[] args) throws MyException {
		Address_JT address_jt = new Address_JT(101010, "sangli", null);
		Employee_JT e1= new Employee_JT(5001, "RAHUL", null);
		
		address_jt.setEmp_jt(e1);
		e1.setAddress(address_jt);
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
	
		session.save(e1);
		//session.save(ad);
		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table(name = "emp_one_to_one_bi")
class Employee_JT {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="joinTables")
	private Address_JT address;
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
	public Address_JT getAddress() {
		return address;
	}
	public void setAddress(Address_JT address) {
		this.address = address;
	}
	public Employee_JT(int empid, String empname, Address_JT address) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address = address;
	}
	public Employee_JT() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee_JT [empid=" + empid + ", empname=" + empname + ", address=" + address + "]";
	}

	
	
}

@Entity
@Table(name="address_bi")
class Address_JT {
	@Id
	private int pincode;
	@Column
	private String city;
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinTable()
	private Employee_JT emp_jt;
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
	public Employee_JT getEmp_jt() {
		return emp_jt;
	}
	public void setEmp_jt(Employee_JT emp_jt) {
		this.emp_jt = emp_jt;
	}
	public Address_JT(int pincode, String city, Employee_JT emp_jt) {
		super();
		this.pincode = pincode;
		this.city = city;
		this.emp_jt = emp_jt;
	}
	public Address_JT() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Address_JT [pincode=" + pincode + ", city=" + city + ", emp_jt=" + emp_jt + "]";
	}
	
	
	
}
