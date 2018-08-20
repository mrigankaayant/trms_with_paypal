package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.CandidateVisa;
import com.ayantsoft.trms.service.CandidateVisaService;

@RestController
public class CandidateVisaController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1523667057836666894L;
	
	@Autowired
	private CandidateVisaService candidateVisaService;
	
	
	@RequestMapping(value = "/visas", method = RequestMethod.GET)
	public ResponseEntity<?> getVises(){
		List<CandidateVisa> CandidateVises = null;
		HttpStatus httpStatus = null;
		try{
			CandidateVises = candidateVisaService.visaList();
			
			if(CandidateVises == null || CandidateVises.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<CandidateVisa>>(CandidateVises, httpStatus);
	}

}
