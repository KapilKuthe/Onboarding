package com.example.supperapp.entities;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SqlResultSetMapping(name = "ProductListing",classes = @ConstructorResult(targetClass = CardList.class,columns = {
		@ColumnResult(name = "SKU",type = String.class),
		@ColumnResult(name = "ProductName",type = String.class),
		@ColumnResult(name = "Description",type = String.class),
		@ColumnResult(name = "CurrencyCode",type = String.class),
		@ColumnResult(name = "CurrencySymbol",type = String.class),
		@ColumnResult(name = "CurrencyNumericCode",type = String.class),
		@ColumnResult(name = "URL",type = String.class),
		@ColumnResult(name = "MinPrice",type = Double.class),
		@ColumnResult(name = "MaxPrice",type = Double.class),
		@ColumnResult(name = "ThumbnailImages",type = String.class),
		@ColumnResult(name = "MobileImages",type = String.class),
		@ColumnResult(name = "BaseImages",type = String.class),
		@ColumnResult(name = "SmallImages",type = String.class),
		@ColumnResult(name = "CardType",type = String.class),
		@ColumnResult(name = "Category",type = String.class),
		@ColumnResult(name = "CreatedOnAt",type = Date.class),
		@ColumnResult(name = "UpdatedOnAt",type = Date.class),
		@ColumnResult(name = "StartDate",type = Date.class),
		@ColumnResult(name = "EndDate",type = Date.class)
				
}))
public class CardList {

	@Id
	private String SKU;
	private String ProductName;
	private String Description;
	private String CurrencyCode;
	private String CurrencySymbol;
	private String CurrencyNumericCode;
	private String URL;
	private Double MinPrice;
	private Double MaxPrice;
	private String ThumbnailImages;
	private String MobileImages;
	private String BaseImages;
	private String SmallImages;
	private String CardType;
	private String Category;
	private Date CreatedOnAt;
	private Date UpdatedOnAt;
	private Date StartDate;
	private Date EndDate;
	
		
}
