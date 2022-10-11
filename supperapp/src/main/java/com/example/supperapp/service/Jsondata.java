package com.example.supperapp.service;




import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@SqlResultSetMapping(name = "Jsondata",classes = @ConstructorResult(targetClass = Jsondata.class,columns = {
		@ColumnResult(name = "SubCategory",type = String.class),
		@ColumnResult(name = "Category",type = String.class)				
}))
public class Jsondata {
 @Id

 private String SubCategory;

	private String Category;
	public String getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public Jsondata(String subCategory, String category) {
		super();
		SubCategory = subCategory;
		Category = category;
	}
	public Jsondata() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Jsondata [SubCategory=" + SubCategory + ", Category=" + Category + "]";
	}
	



	
}
