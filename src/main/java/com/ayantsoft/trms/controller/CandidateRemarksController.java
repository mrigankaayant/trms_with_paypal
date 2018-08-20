package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.CandidateRemarks;

import com.ayantsoft.trms.service.CandidateRemarksService;

@RestController
public class CandidateRemarksController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 116727535884138029L;
	
	
	@Autowired
	private CandidateRemarksService candidateRemarksService;
	
	
	@RequestMapping(value = "/remarks", method = RequestMethod.GET)
	public ResponseEntity<?> getremarksForRecruiter(){
		List<CandidateRemarks> remarks = null;
		HttpStatus httpStatus = null;
		try{
			//remarks = candidateRemarksService.remarksList();
			remarks = candidateRemarksService.findCandidateRemarks("Recruiter");
	
			if(remarks == null || remarks.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<CandidateRemarks>>(remarks, httpStatus);
	}

}
