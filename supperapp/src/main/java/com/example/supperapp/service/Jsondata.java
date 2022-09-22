package com.example.supperapp.service;




import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
public class Jsondata {
 @Id
 private String name;
	private String subname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public Jsondata(String name, String subname) {
		super();
		this.name = name;
		this.subname = subname;
	}
	public Jsondata() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Jsondata [name=" + name + ", subname=" + subname + "]";
	}
 




	
}
