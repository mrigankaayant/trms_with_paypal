package com.ayantsoft.trms.pojo;

import java.util.Date;

public class Skill {
	
	private String skillName;
	private String yearsExp;
	private Date lastUsed;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getYearsExp() {
		return yearsExp;
	}
	public void setYearsExp(String yearsExp) {
		this.yearsExp = yearsExp;
	}
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
}
