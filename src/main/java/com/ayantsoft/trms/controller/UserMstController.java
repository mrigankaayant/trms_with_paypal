package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.AddCandidateBean;
import com.ayantsoft.trms.pojo.UserMst;
import com.ayantsoft.trms.service.UserMstService;

@RestController
public class UserMstController implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2750735101618661798L;
	
	@Autowired
	private UserMstService userMstService;
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserMstByUserName(@PathVariable String username) {
	    UserMst userMst = null;
		HttpStatus httpStatus = null;
		try{
			userMst = userMstService.getUserMstByUsername(username);
			
			if(userMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception pe){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<UserMst>(userMst,httpStatus);
	}
	
	
	@RequestMapping(value = "/findUserType", method = RequestMethod.GET)
	public ResponseEntity<?> findUserType(HttpServletRequest request){
		HttpStatus httpStatus = null;
		UserMst userMst = null;
		try{
			
			Principal principal = request.getUserPrincipal();
			userMst = userMstService.getUserMstByUsername(principal.getName());
			
			if(userMst != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}	
		return new ResponseEntity<UserMst>(userMst,httpStatus);
	}
}
