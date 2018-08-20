package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.Specialization;
import com.ayantsoft.trms.service.SpecializationService;

@RestController
public class SpecializationController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 4774361977816416741L;
	
	@Autowired
	private SpecializationService SpecializationService;
	
	
	@RequestMapping(value = "/specializations", method = RequestMethod.GET)
	public ResponseEntity<?> getGradingSystems(){
		List<Specialization> specializations = null;
		HttpStatus httpStatus = null;
		try{
			specializations = SpecializationService.list();
			
			if(specializations == null || specializations.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Specialization>>(specializations, httpStatus);
	}

}
