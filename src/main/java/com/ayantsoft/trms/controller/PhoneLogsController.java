package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.PhoneLogs;
import com.ayantsoft.trms.pojo.Role;
import com.ayantsoft.trms.service.CredentialService;
import com.ayantsoft.trms.service.PhoneLogsService;

@RestController
public class PhoneLogsController implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1361206966539169531L;
	
	@Autowired
	private PhoneLogsService phoneLogsService;
	
	@Autowired
	private CredentialService credentialService;
	
	@RequestMapping(value = "/phonecalls/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPhoneCallsById(@PathVariable String id) {
		
	    List<PhoneLogs> phoneLogs = null;
		HttpStatus httpStatus = null;
		try{
			phoneLogs = phoneLogsService.getPhoneLogsByCandidateId(id);
			
			if(phoneLogs != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception pe){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PhoneLogs>>(phoneLogs,httpStatus);
	}
	
	
	
	
	@RequestMapping(value = "/phonelogs", method = RequestMethod.GET)
	public ResponseEntity<?> findPhoneLogs(HttpServletRequest request){
		List<PhoneLogs> phoneLogs = null;
		HttpStatus httpStatus = null;
		try{	
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			phoneLogs = phoneLogsService.getPhoneLogsByEmployeeId(emp.getEmployeeId(),isRole("Admin",emp.getRoles()));
			if(phoneLogs == null || phoneLogs.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PhoneLogs>>(phoneLogs, httpStatus);
	}
	

	
	public boolean isRole(String role,List<Role> roles){
		boolean hasRole = false;
		try{
			for(Role r:roles){
				if(r.getRoleName().equals(role)){
					hasRole = true;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return hasRole;
	}	

}
