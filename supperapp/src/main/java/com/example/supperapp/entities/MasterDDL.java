package com.example.supperapp.entities;

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
@SqlResultSetMapping(name = "MasterDDL",classes = @ConstructorResult(targetClass = MasterDDL.class,columns = {
		@ColumnResult(name = "ID",type = String.class),
		@ColumnResult(name = "Text",type = String.class),
		@ColumnResult(name = "Type",type = String.class)
				
}))
public class MasterDDL {

	@Id
	private String ID;
	private String Text;
	private String Type;
}
