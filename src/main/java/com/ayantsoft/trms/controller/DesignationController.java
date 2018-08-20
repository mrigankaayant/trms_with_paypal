package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.Designation;
import com.ayantsoft.trms.service.DesignationService;

@RestController
public class DesignationController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1468947060874905035L;
	
	@Autowired
	private DesignationService designationService;
	
	
	@RequestMapping(value = "/designations", method = RequestMethod.GET)
	public ResponseEntity<?> getDesignations(){
		List<Designation> designations = null;
		HttpStatus httpStatus = null;
		try{
			designations = designationService.designationList();
			
			if(designations == null || designations.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Designation>>(designations, httpStatus);
	}

}
