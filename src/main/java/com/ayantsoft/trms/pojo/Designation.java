package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="designation")
public class Designation implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1771871418275715673L;
	
	@Id
	private String id;
	private String designationName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
}
