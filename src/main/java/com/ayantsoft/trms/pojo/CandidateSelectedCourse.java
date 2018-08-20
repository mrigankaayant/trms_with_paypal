package com.ayantsoft.trms.pojo;

import java.util.List;

public class CandidateSelectedCourse {
	
	private String candidateId;
	private List<CandidateCourse> candidateFinalCourses;
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public List<CandidateCourse> getCandidateFinalCourses() {
		return candidateFinalCourses;
	}
	public void setCandidateFinalCourses(List<CandidateCourse> candidateFinalCourses) {
		this.candidateFinalCourses = candidateFinalCourses;
	}
}
