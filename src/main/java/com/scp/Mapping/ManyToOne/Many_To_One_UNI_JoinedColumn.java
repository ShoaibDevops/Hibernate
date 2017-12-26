package com.scp.Mapping.ManyToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Many_To_One_UNI_JoinedColumn 
{

	public static void main(String[] args) throws MyException 
	{
		// TODO Auto-generated method stub
		Session session = Hibernateutil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
			
		Company_UJC cmp=new Company_UJC(1, "KPIT");

		Department_UJC st1=new Department_UJC(10, "Production", cmp);
		Department_UJC st2=new Department_UJC(11, "Finance", cmp);
		    
		session.save(st1);
		session.save(st2);
		trans.commit();
	}

}

@Entity
@Table
class Department_UJC{
	@Id
	int depttId;
	String deptName;
	Company_UJC company;
	public int getDepttId() {
		return depttId;
	}
	public void setDepttId(int depttId) {
		this.depttId = depttId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Company_UJC getCompany() {
		return company;
	}
	public void setCompany(Company_UJC company) {
		this.company = company;
	}
	public Department_UJC(int depttId, String deptName, Company_UJC company) {
		super();
		this.depttId = depttId;
		this.deptName = deptName;
		this.company = company;
	}
	public Department_UJC() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Department_UJC [depttId=" + depttId + ", deptName=" + deptName + ", company=" + company + "]";
	}
	
	
		
}


@Entity
@Table
class Company_UJC{
	@Id
	int compId;
	String compName;
	public Company_UJC() {
		super();
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public Company_UJC(int compId, String compName) {
		super();
		this.compId = compId;
		this.compName = compName;
	}
	@Override
	public String toString() {
		return "Company_UJC [compId=" + compId + ", compName=" + compName + "]";
	}
}