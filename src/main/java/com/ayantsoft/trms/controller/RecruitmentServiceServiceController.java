package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.RecruitmentService;
import com.ayantsoft.trms.service.RecruitmentServiceService;

@RestController
public class RecruitmentServiceServiceController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3740219476982223265L;
	
	@Autowired
	private RecruitmentServiceService recruitmentServiceService;
	
	@RequestMapping(value = "/recruitmentService", method = RequestMethod.GET)
	public ResponseEntity<?> getRecruitmentServices(){
		List<RecruitmentService> recruitmentServiceList = null;
		HttpStatus httpStatus = null;
		try{
			recruitmentServiceList = recruitmentServiceService.list();
			if(recruitmentServiceList == null || recruitmentServiceList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<RecruitmentService>>(recruitmentServiceList, httpStatus);
	}
}
