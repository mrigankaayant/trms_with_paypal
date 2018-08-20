package com.ayantsoft.trms.pojo;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "candidate_course")
public class CandidateCourse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7462361244392597410L;

    private String course;
    
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
}
