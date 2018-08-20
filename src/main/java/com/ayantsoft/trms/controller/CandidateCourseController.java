package com.ayantsoft.trms.controller;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.CandidateCourse;
import com.ayantsoft.trms.service.CandidateCourseService;

@RestController
public class CandidateCourseController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -2907664249884789460L;
	
	
	@Autowired
	private CandidateCourseService candidateCourseService;
	
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public ResponseEntity<?> getCandidateCourses(){
		List<CandidateCourse> candidateCourses = null;
		HttpStatus httpStatus = null;
		try{
			candidateCourses = candidateCourseService.courseList();
			
			if(candidateCourses == null || candidateCourses.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<CandidateCourse>>(candidateCourses, httpStatus);
	}

}
