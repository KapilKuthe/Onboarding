package com.example.supperapp.entities;

import javax.persistence.Entity;

//@Entity
public class SubTypeName {

	public String title;
    public String titleUrl;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleUrl() {
		return titleUrl;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	
	public SubTypeName(String title, String titleUrl) {
		super();
		this.title = title;
		this.titleUrl = titleUrl;
	}
	
	public SubTypeName(String title) {
		super();
		this.title = title;
		
	}
	public SubTypeName() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
