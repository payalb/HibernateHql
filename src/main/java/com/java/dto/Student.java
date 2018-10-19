package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="phone")
@EqualsAndHashCode
@Entity(name="st")
@Table(name="student_details")
public class Student {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	@Column(name="stName",unique=true)
	private String name;
	@ManyToMany(mappedBy="student")
	List<Phone> phone= new ArrayList<>();	
	public Student(String name, List<Phone> phone) {
		this.name= name;
		this.phone=phone;
	}
}
/*student_phone*/