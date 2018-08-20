package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.WorkAuthorization;
import com.ayantsoft.trms.service.WorkAuthorizationService;

@RestController
public class WorkAuthorizationController implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8337487423310648554L;
	
	@Autowired
	private WorkAuthorizationService workAuthorizationService;
	
	
	@RequestMapping(value = "/workAuthorizations", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeTypes(){
		List <WorkAuthorization> workAuthorizations = null;
		HttpStatus httpStatus = null;
		try{
			workAuthorizations = workAuthorizationService.list();
			if(workAuthorizations == null || workAuthorizations.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<WorkAuthorization>>(workAuthorizations, httpStatus);
	}

}
