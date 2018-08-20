package com.ayantsoft.trms.pojo;

import java.io.Serializable;

public class SearchCandidate implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5090152580550853427L;
	
	private String searchValue;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
	
}