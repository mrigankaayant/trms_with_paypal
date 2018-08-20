package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.RecruitmentSource;
import com.ayantsoft.trms.service.RecruitmentSourceService;

@RestController
public class RecruitmentSourceController implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8468549889955998693L;
	
	@Autowired
	private RecruitmentSourceService recruitmentSourceService;
	
	@RequestMapping(value = "/recruitmentsources", method = RequestMethod.GET)
	public ResponseEntity<?> getRecruitmentSources(){
		List<RecruitmentSource> sources = null;
		HttpStatus httpStatus = null;
		try{
			sources = recruitmentSourceService.recruitmentSourcelist();
			
			if(sources == null || sources.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<RecruitmentSource>>(sources, httpStatus);
	}

}
