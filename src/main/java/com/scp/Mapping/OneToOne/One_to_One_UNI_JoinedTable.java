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
public class One_to_One_UNI_JoinedTable {

	/**
	 * @param args
	 * @throws MyException
	 */
	public static void main(String[] args) throws MyException {
		Address_UJT add_ujt = new Address_UJT(123456, "Delhi");
		Employee_UJT e1_ujt = new Employee_UJT(1001, "sagar", add_ujt);
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		session.save(e1_ujt);
		// session.save(ad);
		Hibernateutil.connectionClose(session, trans);
	}

}

@Entity
@Table
class Employee_UJT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address_UJT address_ujt;

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

	public Address_UJT getAddress_ujt() {
		return address_ujt;
	}

	public void setAddress_ujt(Address_UJT address_ujt) {
		this.address_ujt = address_ujt;
	}

	public Employee_UJT(int empid, String empname, Address_UJT address_ujt) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address_ujt = address_ujt;
	}

	public Employee_UJT() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee_UJT [empid=" + empid + ", empname=" + empname + ", address_ujt=" + address_ujt + "]";
	}

}

@Entity
class Address_UJT {
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

	public Address_UJT(int pincode, String city) {
		super();
		this.pincode = pincode;
		this.city = city;
	}

	public Address_UJT() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address_UJT [pincode=" + pincode + ", city=" + city + "]";
	}
}