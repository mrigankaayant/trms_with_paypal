package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.Role;
import com.ayantsoft.trms.service.CredentialService;
import com.ayantsoft.trms.service.EmployeeService;

@RestController
public class EmployeeController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5982088566379632801L;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CredentialService credentialService;
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeByUserId(@PathVariable String id) {
	    Employee employee = null;
		HttpStatus httpStatus = null;
		try{
			employee = employeeService.getEmployeeByUserId(id);
			
			if(employee != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception pe){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Employee>(employee,httpStatus);
	}
	
	
	
	@RequestMapping(value = "/employee/roles", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeRoles(HttpServletRequest request){
	    List<Role> roles = null;
		HttpStatus httpStatus = null;
		try{
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			roles = emp.getRoles();
			if(roles != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception pe){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Role>>(roles,httpStatus);
	}
	
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployees(){
	    List<Employee> employees = null;
		HttpStatus httpStatus = null;
		try{
			List<String> roles = new ArrayList<String>();
			roles.add("Recruiter");
			roles.add("Recruiter Manager");
			employees = employeeService.findEmployeeByRole(roles);
			if(employees != null){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.EXPECTATION_FAILED;
			}
		}catch(Exception pe){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Employee>>(employees,httpStatus);
	}
}
