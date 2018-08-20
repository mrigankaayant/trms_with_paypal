package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.EmploymentTypes;
import com.ayantsoft.trms.service.EmploymentTypesService;

@RestController
public class EmploymentTypesController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 3994696372102766109L;
	
	@Autowired
	private EmploymentTypesService employmentTypesService;
	
	@RequestMapping(value = "/employmentTypes", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeTypes(){
		
		List <EmploymentTypes> employeeTypes = null;
		HttpStatus httpStatus = null;
		try{
			employeeTypes = employmentTypesService.list();
			
			if(employeeTypes == null || employeeTypes.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<EmploymentTypes>>(employeeTypes, httpStatus);
	}

}
