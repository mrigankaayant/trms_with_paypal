package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="city")
public class Cities implements Serializable {
	
	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -4112297596155004181L;
	
	@Id
	private Integer id;
	private States states;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public States getStates() {
		return states;
	}
	public void setStates(States states) {
		this.states = states;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
