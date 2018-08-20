package com.ayantsoft.trms.pojo;

import java.util.Map;

public class LazyCandidateDto {
	
	private int first;
	private int rows;
	private String sortField;
	private int sortOrder;
	private Map<String,Object> filters;
	
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Map<String, Object> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

}
