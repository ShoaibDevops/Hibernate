/**
 * 
 */
package com.scp.Mapping.OneToOne;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author abhi
 *
 */
public class One_to_One_UNI_PrimaryKey {

	/**
	 * @param args
	 * @throws MyException
	 */
	public static void main(String[] args) throws MyException {
		Address_PRI address_pri = new Address_PRI(123456, "Delhi");
		Employee_PRI employee_pri = new Employee_PRI(1001, "sagar", address_pri);
		
		address_pri.setPincode(employee_pri.getEmpid());
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		
		Transaction trans = session.beginTransaction();

		session.save(employee_pri);
		session.save(address_pri);
		Hibernateutil.connectionClose(session, trans);
	}

}

@Entity
@Table
class Employee_PRI {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address_PRI address_pri;

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

	public Address_PRI getAddress_pri() {
		return address_pri;
	}

	public void setAddress_pri(Address_PRI address_pri) {
		this.address_pri = address_pri;
	}

	public Employee_PRI(int empid, String empname, Address_PRI address_pri) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address_pri = address_pri;
	}

	public Employee_PRI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee_PRI [empid=" + empid + ", empname=" + empname + ", address_pri=" + address_pri + "]";
	}

	
}

@Entity
class Address_PRI {
	@Id
	private int pincode;
	@Column
	private String city;

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

	public Address_PRI(int pincode, String city) {
		super();
		this.pincode = pincode;
		this.city = city;
	}

	public Address_PRI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address_PRI [pincode=" + pincode + ", city=" + city + "]";
	}
}