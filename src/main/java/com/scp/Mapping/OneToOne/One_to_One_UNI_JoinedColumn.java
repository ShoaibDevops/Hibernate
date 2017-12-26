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
 * 
 *         child class primary key saved into parant table. seperate table
 *         created for child and parent
 *
 */
public class One_to_One_UNI_JoinedColumn {

	/**
	 * @param args
	 * @throws MyException
	 * @throws HibernateException
	 */
	public static void main(String[] args) throws MyException {
		Address address = new Address(123456, "Delhi");
		Employee e1 = new Employee(1001, "sagar", address);
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		session.save(e1);
		// session.save(ad);
		Hibernateutil.connectionClose(session, trans);
	}

}

@Entity
@Table(name = "emp_one_to_one")
class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empid;
	@Column
	private String empname;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", address=" + address + "]";
	}

	public Employee(int empid, String empname, Address address) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.address = address;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}

@Entity
class Address {
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

	public Address(int pincode, String city) {
		super();
		this.pincode = pincode;
		this.city = city;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address [pincode=" + pincode + ", city=" + city + "]";
	}
}