package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.service.DepartmentService;
import com.ayantsoft.trms.pojo.Department;

@RestController
public class DepartmentController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2235840253177171678L;
	
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public ResponseEntity<?> getDepartments(){
		List <Department> departments = null;
		HttpStatus httpStatus = null;
		try{
			departments = departmentService.departmentList();
			
			if(departments == null || departments.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Department>>(departments, httpStatus);
	}

}
