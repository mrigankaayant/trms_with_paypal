package com.ayantsoft.trms.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="recruitment_source")
public class RecruitmentSource implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -197284111238283028L;
	
	@Id
	private String id;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
