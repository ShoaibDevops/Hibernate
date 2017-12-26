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

import com.scp.Mapping.OneToMany.Hibernateutil;
import com.scp.Mapping.OneToMany.MyException;


public class Many_To_Many_BI_JoinedColumn {

	public static void main(String[] args) throws  MyException {
		// TODO Auto-generated method stub

		Session session = Hibernateutil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();

		Student_manyTOmany_BJC student1 = new Student_manyTOmany_BJC(100, "Amar", "Ghorpade");
		Student_manyTOmany_BJC student2 = new Student_manyTOmany_BJC(101, "Ashwini", "Suryawanshi");

		Subject_manyTOmany_BJC subject1 = new Subject_manyTOmany_BJC(10, "Java");
		Subject_manyTOmany_BJC subject2 = new Subject_manyTOmany_BJC(11, "Hibernate");
		Subject_manyTOmany_BJC subject3 = new Subject_manyTOmany_BJC(12, "Spring");

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
class Student_manyTOmany_BJC {
	@Id
	@GeneratedValue
	private int studentId;
	private String firstName;
	private String lastName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Subject_manyTOmany_BJC> subjects = new ArrayList<Subject_manyTOmany_BJC>();

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

	public List<Subject_manyTOmany_BJC> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject_manyTOmany_BJC> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student_manyTOmany_BJC [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", subjects=" + subjects + "]";
	}

	public Student_manyTOmany_BJC(int studentId, String firstName, String lastName
			) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	public Student_manyTOmany_BJC() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

@Entity
@Table
class Subject_manyTOmany_BJC{
	@Id
	//@GeneratedValue
	private long subjectId;
	private String SubjectName;
	
	 @ManyToMany(mappedBy="subjects")
	    private List<Student_manyTOmany_BJC> students = new ArrayList<Student_manyTOmany_BJC>();

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public List<Student_manyTOmany_BJC> getStudents() {
		return students;
	}

	public void setStudents(List<Student_manyTOmany_BJC> students) {
		this.students = students;
	}

	public Subject_manyTOmany_BJC(long subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		SubjectName = subjectName;
		this.students = students;
	}

	@Override
	public String toString() {
		return "Subject_manyTOmany_BJC [subjectId=" + subjectId + ", SubjectName=" + SubjectName + ", students="
				+ students + "]";
	}

	public Subject_manyTOmany_BJC() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
