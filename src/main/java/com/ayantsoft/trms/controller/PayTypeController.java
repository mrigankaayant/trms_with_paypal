package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ayantsoft.trms.pojo.PayType;
import com.ayantsoft.trms.service.PayTypeService;

@RestController
public class PayTypeController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -4990703116365826558L;
	
	@Autowired
	private PayTypeService payTypeService;
	
	
	@RequestMapping(value = "/paytypes", method = RequestMethod.GET)
	public ResponseEntity<?> getPaytypes(){
		List<PayType> payTypeList = null;
		HttpStatus httpStatus = null;
		try{
			payTypeList = payTypeService.payTypeList();
			
			if(payTypeList == null || payTypeList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PayType>>(payTypeList, httpStatus);
	}

}
