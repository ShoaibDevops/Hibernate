package com.scp.Mapping.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.scp.Mapping.ManyToOne.Hibernateutil;
import com.scp.Mapping.ManyToOne.MyException;



public class Many_To_Many_UNI_JoinedColumn {

	public static void main(String[] args) throws  MyException 
	{
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		Student_manyTOmany_UJC student1 = new Student_manyTOmany_UJC(100, "Amar", "Ghorpade");
		Student_manyTOmany_UJC student2 = new Student_manyTOmany_UJC(101, "Ashwini", "Suryawanshi");
		
		Subject_manyTOmany_UJC subject1 = new Subject_manyTOmany_UJC(10, "C Language");
		Subject_manyTOmany_UJC subject2 = new Subject_manyTOmany_UJC(11, "Java");
		Subject_manyTOmany_UJC subject3 = new Subject_manyTOmany_UJC(12, "Hibernate");

		// student1 have 3 subjects
		student1.getSubjects().add(subject1);
		student1.getSubjects().add(subject2);
		student1.getSubjects().add(subject3);

		// student2 have 2 subjects
		student2.getSubjects().add(subject1);
		student2.getSubjects().add(subject2);

		session.save(student1);
		session.save(student2);

		trans.commit();
		session.close();
	}

}

@Entity
@Table
class Student_manyTOmany_UJC {
	@Id
	private int studentId;
	private String firstName;
	private String lastName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Subject_manyTOmany_UJC> subjects = new ArrayList<Subject_manyTOmany_UJC>();

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Subject_manyTOmany_UJC> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject_manyTOmany_UJC> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student_manyTOmany_UJC [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", subjects=" + subjects + "]";
	}

	public Student_manyTOmany_UJC(int studentId, String firstName, String lastName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	public Student_manyTOmany_UJC() {
		super();
		// TODO Auto-generated constructor stub
	}

}

@Entity
@Table
class Subject_manyTOmany_UJC {
	@Id
	private long subjectId;
	private String subjectName;

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject_manyTOmany_UJC [subjectId=" + subjectId + ", subjectName=" + subjectName + "]";
	}

	public Subject_manyTOmany_UJC(long subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public Subject_manyTOmany_UJC() {
		super();
		// TODO Auto-generated constructor stub
	}
}