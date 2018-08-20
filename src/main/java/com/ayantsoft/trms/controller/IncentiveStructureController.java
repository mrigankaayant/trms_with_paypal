package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.IncentiveStructure;
import com.ayantsoft.trms.service.IncentiveStructureService;

@RestController
public class IncentiveStructureController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -1899133135099927229L;
	
	@Autowired
	private IncentiveStructureService incentiveStructureService;
	
	
	@RequestMapping(value = "incentiveList/{typeFor}", method = RequestMethod.GET)
	public ResponseEntity<?> getIncentiveList(@PathVariable String typeFor){
		
		List<IncentiveStructure> incentiveList = null;
		HttpStatus httpStatus = null;
		try{
			incentiveList = incentiveStructureService.findIncentiveList(typeFor);
			
			if(incentiveList == null || incentiveList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<IncentiveStructure>>(incentiveList,httpStatus);
	}
	
	
	

}
