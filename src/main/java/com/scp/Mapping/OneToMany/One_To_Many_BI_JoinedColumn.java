package com.scp.Mapping.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scp.Mapping.OneToOne.Hibernateutil;
import com.scp.Mapping.OneToOne.MyException;

public class One_To_Many_BI_JoinedColumn {

	public static void main(String[] args) throws HibernateException, MyException 
	{
		Patient_BJC patient1 = new Patient_BJC(201,"SAGAR",null);
		Patient_BJC patient2 = new Patient_BJC(202,"RAJU",null);
		Hospital_BJC hosp1= new Hospital_BJC(102, "Suryakiran Hospital", null);
		List<Patient_BJC> hlist= new ArrayList<Patient_BJC>();
		patient1.setHsp(hosp1);
		patient2.setHsp(hosp1);
		hlist.add(patient1);
		hlist.add(patient2);
		hosp1.setPatients(hlist);
		Session session = Hibernateutil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.save(hosp1);
		
		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table(name="hosp")
class Hospital_BJC {
	@Id
	private int hospitalId;
	private String hospitalName;
	@OneToMany(cascade=CascadeType.ALL ,mappedBy="hsp" )
	List<Patient_BJC> patients;
	
	public Hospital_BJC() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public List<Patient_BJC> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient_BJC> patients) {
		this.patients = patients;
	}
	public Hospital_BJC(int hospitalId, String hospitalName, List<Patient_BJC> patients) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.patients = patients;
	}
	@Override
	public String toString() {
		return "Hospital_BJC [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", patients=" + patients + "]";
	}  
}

@Entity
@Table(name="Patnt")
class Patient_BJC {
	@Id
	private int patientId;
	private String patientName;
	@ManyToOne
	@JoinColumn
	Hospital_BJC hsp;
	public Patient_BJC(int patientId, String patientName, Hospital_BJC hsp) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.hsp = hsp;
	}
	@Override
	public String toString() {
		return "Patient_BJC [patientId=" + patientId + ", patientName=" + patientName + ", hsp=" + hsp + "]";
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Hospital_BJC getHsp() {
		return hsp;
	}
	public void setHsp(Hospital_BJC hsp) {
		this.hsp = hsp;
	}
}