package com.scp.Mapping.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Many_To_Many_UNI_JoinedTable {

	public static void main(String[] args) throws MyException {
		// TODO Auto-generated method stub

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		Student_manyTOmany_UJT student1 = new Student_manyTOmany_UJT(100, "Amar", "Ghorpade");
		Student_manyTOmany_UJT student2 = new Student_manyTOmany_UJT(101, "Ashwini", "Suryawanshi");

		Subject_manyTOmany_UJT subject1 = new Subject_manyTOmany_UJT(10, "Java");
		Subject_manyTOmany_UJT subject2 = new Subject_manyTOmany_UJT(11, "Hibernate");
		Subject_manyTOmany_UJT subject3 = new Subject_manyTOmany_UJT(12, "Spring");

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
class Student_manyTOmany_UJT {
	@Id
	@GeneratedValue
	private int studentId;
	private String firstName;
	private String lastName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Student_Subject_Uni", joinColumns = { @JoinColumn(name = "studentId") }, inverseJoinColumns = {
			@JoinColumn(name = "subjectId") })
	private List<Subject_manyTOmany_UJT> subjects = new ArrayList<Subject_manyTOmany_UJT>();

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

	public List<Subject_manyTOmany_UJT> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject_manyTOmany_UJT> subjects) {
		this.subjects = subjects;
	}

	public Student_manyTOmany_UJT(int studentId, String firstName, String lastName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	public Student_manyTOmany_UJT() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student_manyTOmany_UJT [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", subjects=" + subjects + "]";
	}

}

@Entity
@Table
class Subject_manyTOmany_UJT {

	@Id
	// @GeneratedValue
	private long subjectId;
	private String name;

	public Subject_manyTOmany_UJT(long subjectId, String name) {
		super();
		this.subjectId = subjectId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subject_manyTOmany_UJT [subjectId=" + subjectId + ", name=" + name + "]";
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject_manyTOmany_UJT() {
		super();
		// TODO Auto-generated constructor stub
	}

}
