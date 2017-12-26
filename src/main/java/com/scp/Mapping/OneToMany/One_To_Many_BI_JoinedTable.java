package com.scp.Mapping.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scp.Mapping.OneToOne.Hibernateutil;
import com.scp.Mapping.OneToOne.MyException;

public class One_To_Many_BI_JoinedTable {

	public static void main(String[] args) throws HibernateException, MyException {
		
		  Patient_BJT patient1 = new Patient_BJT(201,"SAGAR",null);
		  Patient_BJT patient2 = new Patient_BJT(202,"RAJU",null); 
		  Hospital_BJT hosp1= new Hospital_BJT(102, "Suryakiran Hospital", null);
		 
		List<Patient_BJT> hlist = new ArrayList<Patient_BJT>();
		
		  patient1.setHsp(hosp1);
		  patient2.setHsp(hosp1); 
		   hlist.add(patient1);
		   hlist.add(patient2);
		   hosp1.setPatients(hlist);
		 
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.save(hosp1);

		Hibernateutil.connectionClose(session, trans);
	}
}

@Entity
@Table(name = "hosp_bjt")
class Hospital_BJT {
	@Id
	private int hospitalId;
	private String hospitalName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hsp")
	List<Patient_BJT> patients;

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

	public List<Patient_BJT> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient_BJT> patients) {
		this.patients = patients;
	}

	public Hospital_BJT(int hospitalId, String hospitalName, List<Patient_BJT> patients) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.patients = patients;
	}

	public Hospital_BJT() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Hospital_BJT [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", patients=" + patients
				+ "]";
	}
}

@Entity
@Table(name = "Patnt_bjt")
class Patient_BJT {
	@Id
	private int patientId;
	private String patientName;
	@ManyToOne
	@JoinTable(name="join_bi")
	Hospital_BJT hsp;

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

	public Hospital_BJT getHsp() {
		return hsp;
	}

	public void setHsp(Hospital_BJT hsp) {
		this.hsp = hsp;
	}

	public Patient_BJT(int patientId, String patientName, Hospital_BJT hsp) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.hsp = hsp;
	}

	@Override
	public String toString() {
		return "Patient_BJT [patientId=" + patientId + ", patientName=" + patientName + ", hsp=" + hsp + "]";
	}

	public Patient_BJT() {
		super();
		// TODO Auto-generated constructor stub
	}

}