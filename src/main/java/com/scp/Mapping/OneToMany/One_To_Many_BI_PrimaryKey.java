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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scp.Mapping.OneToOne.Hibernateutil;
import com.scp.Mapping.OneToOne.MyException;

public class One_To_Many_BI_PrimaryKey {

	public static void main(String[] args) throws HibernateException, MyException {

		Patient_BPK patient1 = new Patient_BPK(201, "SAGAR", null);
		Patient_BPK patient2 = new Patient_BPK(202, "RAJU", null);
		Hospital_BPK hosp1 = new Hospital_BPK(102, "Suryakiran Hospital", null);

		List<Patient_BPK> hlist = new ArrayList<Patient_BPK>();

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
@Table(name = "hosp_bpk")
class Hospital_BPK {
	@Id
	private int hospitalId;
	private String hospitalName;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hsp")
	List<Patient_BPK> patients;

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

	public List<Patient_BPK> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient_BPK> patients) {
		this.patients = patients;
	}

	public Hospital_BPK(int hospitalId, String hospitalName, List<Patient_BPK> patients) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.patients = patients;
	}

	public Hospital_BPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Hospital_BPK [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", patients=" + patients
				+ "]";
	}

}

@Entity
@Table(name = "Patnt_bpk")
class Patient_BPK {
	@Id
	private int patientId;
	private String patientName;
	@ManyToOne
	@PrimaryKeyJoinColumn
	Hospital_BPK hsp;

	public Patient_BPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient_BPK(int patientId, String patientName, Hospital_BPK hsp) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.hsp = hsp;
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

	public Hospital_BPK getHsp() {
		return hsp;
	}

	public void setHsp(Hospital_BPK hsp) {
		this.hsp = hsp;
	}

	@Override
	public String toString() {
		return "Patient_BPK [patientId=" + patientId + ", patientName=" + patientName + ", hsp=" + hsp + "]";
	}

}