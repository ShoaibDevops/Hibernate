package com.scp.Mapping.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Many_To_Many_UNI_PrimaryKey {

	public static void main(String[] args) throws  MyException {
		// TODO Auto-generated method stub
		Session session = Hibernateutil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		Student_manyTOmany_UPK student1 = new Student_manyTOmany_UPK(100, "Amar", "Ghorpade");
		Student_manyTOmany_UPK student2 = new Student_manyTOmany_UPK(101, "Ashwini", "suryawanshi");

		Subject_manyTOmany_UPK subject1 = new Subject_manyTOmany_UPK(10, "Java");
		Subject_manyTOmany_UPK subject2 = new Subject_manyTOmany_UPK(11, "Hibernate");
		Subject_manyTOmany_UPK subject3 = new Subject_manyTOmany_UPK(12, "Spring");

		student1.getSubjects().add(subject1);
		student1.getSubjects().add(subject2);
		student1.getSubjects().add(subject3);

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
class Student_manyTOmany_UPK {
	@Id
	@GeneratedValue
	private int studentId;
	private String firstName;
	private String lastName;

	@ManyToMany(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List<Subject_manyTOmany_UPK> subjects = new ArrayList<Subject_manyTOmany_UPK>();

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

	public List<Subject_manyTOmany_UPK> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject_manyTOmany_UPK> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student_manyTOmany_UPK [studentId=" + studentId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", subjects=" + subjects + "]";
	}

	public Student_manyTOmany_UPK(int studentId, String firstName, String lastName
			) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	public Student_manyTOmany_UPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

@Entity
@Table
class Subject_manyTOmany_UPK{
	@Id
	// @GeneratedValue
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
		return "Subject_manyTOmany_UPK [subjectId=" + subjectId + ", subjectName=" + subjectName + "]";
	}
	public Subject_manyTOmany_UPK(long subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
	public Subject_manyTOmany_UPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
