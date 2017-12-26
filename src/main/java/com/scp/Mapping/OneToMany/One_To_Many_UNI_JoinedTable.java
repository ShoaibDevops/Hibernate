package com.scp.Mapping.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scp.Mapping.OneToOne.Hibernateutil;

public class One_To_Many_UNI_JoinedTable {

	public static void main(String[] args) 
	{
		Patient_UJT patient1 = new Patient_UJT(201,"SAGAR");
		Patient_UJT patient2 = new Patient_UJT(202,"RAJU");
		Patient_UJT patient3 = new Patient_UJT(203,"SANGRAM");
		Patient_UJT patient4 = new Patient_UJT(204,"DEEPA");
		List<Patient_UJT> list1= new ArrayList();
		list1.add(patient1);
		list1.add(patient3);
		List<Patient_UJT> list2= new ArrayList();
		list2.add(patient2);
		list2.add(patient4);
		Hospital_UJT hosp1= new Hospital_UJT(101, "Suryakiran Hospital", list1);
		Hospital_UJT hosp2= new Hospital_UJT(501, "Bharati Hospital", list2);
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.save(hosp1);
		session.save(hosp2);
		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table
class Hospital_UJT {
	@Id
	private int hospitalId;
	private String hospitalName;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable
	List<Patient_UJT> patients;
	
	
	
	public Hospital_UJT() {
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
	public List<Patient_UJT> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient_UJT> patients) {
		this.patients = patients;
	}
	public Hospital_UJT(int hospitalId, String hospitalName, List<Patient_UJT> patients) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.patients = patients;
	}
	@Override
	public String toString() {
		return "Hospital_UJT [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", patients=" + patients + "]";
	}  
	
}

@Entity
@Table
class Patient_UJT {
	@Id
	private int patientId;
	private String patientName;

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

	public Patient_UJT(int patientId, String patientName) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
	}

	public Patient_UJT() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient_UJT [patientId=" + patientId + ", patientName=" + patientName + "]";
	}
}