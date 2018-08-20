package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.GradingSystem;
import com.ayantsoft.trms.service.GradingSystemService;

@RestController
public class GradingSystemController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6542468580103720796L;
	
	@Autowired
	private GradingSystemService gradingSystemService;
	
	
	@RequestMapping(value = "/gradingsystems", method = RequestMethod.GET)
	public ResponseEntity<?> getGradingSystems(){
		List<GradingSystem> gradingSystems = null;
		HttpStatus httpStatus = null;
		try{
			gradingSystems = gradingSystemService.list();
		
			if(gradingSystems == null || gradingSystems.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<GradingSystem>>(gradingSystems, httpStatus);
	}

}
