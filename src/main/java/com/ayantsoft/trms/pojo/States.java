package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="state")
public class States implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1763331281270205075L;
	
	@Id
	private Integer id;
	private Countries countries;
	private String name;
	private String timeZone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Countries getCountries() {
		return countries;
	}
	public void setCountries(Countries countries) {
		this.countries = countries;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
