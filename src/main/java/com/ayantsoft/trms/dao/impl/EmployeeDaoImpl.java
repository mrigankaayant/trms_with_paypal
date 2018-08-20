package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.EmployeeDao;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.Employee;

@Repository
public class EmployeeDaoImpl implements Serializable,EmployeeDao {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -3853253214679564339L;

	@Autowired
	private MongoTemplate mongoTemplate;

	/*@Override
	public List<Employee> list() {
		List<Employee> employeeList = null;
		try{
			Criteria criteria = new Criteria();
			criteria.where("active").is(true);
	        Query query = new Query(criteria);
			employeeList = mongoTemplate.find(query,Employee.class,"employee");
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeList; 
	}*/
	
	

	@Override
	public Employee getEmployeeByUserId(String id) {
	    Employee employee = null;
		try{
	        Query query = new Query();
			query.addCriteria(Criteria.where("userId").is(id));
			employee = mongoTemplate.findOne(query,Employee.class,"employee"); 
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return employee; 
	}
	
	

	@Override
	public List<Employee> findEmployeeByRole(List<String> roles) {
		List<Employee> employees = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("roles.roleName").in(roles));
			employees = mongoTemplate.find(query,Employee.class,"employee");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return employees;
	}	
}
