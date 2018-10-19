package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.java.dto.Phone;
import com.java.dto.Student;
import com.java.dto.Type;

public class Main {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		Main obj = new Main();
		try {
			obj.insertRecords();
			obj.fetchAllData();
			obj.fetchDataById();
			obj.updateData();
			obj.deleteData();
		} finally {
			sf.close();
		}

	}

	private void fetchDataById() {
		int id= 1;
		Session s = sf.openSession();
		Query<Student> query=s.createQuery("from st where id = "+ id);
		Optional<Student> st=query.uniqueResultOptional();
		//Optional: prevent null pointer exception
		//get(): give u the data
		//orElse(): u can set the data to be returned if there is no data
		st.orElse(new Student());
		System.out.println(st.get());
		
		NativeQuery<Student> q1=s.createNativeQuery("select * from student_details where id =" + id);
		Optional<Student> st1=q1.uniqueResultOptional();
		st1.orElse(new Student());
		System.out.println(st1.get());
		s.close();
		
	}

	public void insertRecords() {
		Student st1 = new Student("payal", null);
		Student st2 = new Student("ritu", null);
		Phone p1 = new Phone(76_372l, Type.LANDLINE, Arrays.asList(st1, st2));
		Phone p2 = new Phone(7_38_47_476l, Type.MOBILE, Arrays.asList(st1));
		Session s = sf.openSession();
		s.beginTransaction();
		s.persist(p1);
		s.persist(p2);
		s.getTransaction().commit();
		s.close();
	}

	public void fetchAllData() {
		Session s = sf.openSession();
		Query<Student> query=s.createQuery("from st");
		List<Student> list=query.list();
		System.out.println(list);
		s.close();
	}
	
	public void updateData() {
		Session s = sf.openSession();
		Query<Student> query=s.createQuery("update st set name= 'ritu' where id = 1 ");
		Transaction t=s.beginTransaction();
		int noOfRowsUpdated=query.executeUpdate();
		t.commit();
		System.out.println(noOfRowsUpdated);
		s.close();
	}
	
	
	public void deleteData() {
		Session s= sf.openSession();
		Query<Student> query= s.createQuery("delete from st where id = 1");
		Transaction t=s.beginTransaction();
		int noOfRowsUpdated=query.executeUpdate();
		t.commit();
		System.out.println(noOfRowsUpdated);
		s.close();
		
	}
	//join queries 
	//subquery using hql
}
