package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="country")
public class Countries implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5264853189394800618L;
	
	@Id
	private Integer id;
	private String sortname;
	private String name;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
