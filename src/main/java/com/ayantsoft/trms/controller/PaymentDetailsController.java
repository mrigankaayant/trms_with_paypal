package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.service.CredentialService;
import com.ayantsoft.trms.service.EmployeeService;
import com.ayantsoft.trms.service.IncentiveStructureService;
import com.ayantsoft.trms.service.PaymentDetailsService;
import com.ayantsoft.trms.service.UserMstService;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.PayamentDto;
import com.ayantsoft.trms.pojo.PaymentDetails;
import com.ayantsoft.trms.pojo.UserMst;

@RestController
public class PaymentDetailsController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1564087256429200535L;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private IncentiveStructureService incentiveStructureService;
	
	@Autowired
	private UserMstService userMstService;
	
	@RequestMapping(value = "/paymentList", method = RequestMethod.GET)
	public ResponseEntity<?> findPaymentDetails(HttpServletRequest request){
		
		List<PaymentDetails> paymentDetailsList = null;
		HttpStatus httpStatus = null;
		try{
			
			Principal principal = request.getUserPrincipal();
			UserMst userMst = userMstService.getUserMstByUsername(principal.getName());
			
			paymentDetailsList = paymentDetailsService.completedPaymentList(userMst.getUsername());
			
			if(paymentDetailsList == null || paymentDetailsList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PaymentDetails>>(paymentDetailsList, httpStatus);
	}
	

	
	@RequestMapping(value = "/paymentList/{candidateId}", method = RequestMethod.GET)
	public ResponseEntity<?> findPaymentDetails(@PathVariable String candidateId){
		
		List<PaymentDetails> paymentDetailsList = null;
		HttpStatus httpStatus = null;
		try{
			paymentDetailsList = paymentDetailsService.findPaymentDetailsByCandidateId(candidateId);
		
			if(paymentDetailsList == null || paymentDetailsList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PaymentDetails>>(paymentDetailsList, httpStatus);
	}
	
	
	@RequestMapping(value = "/owncandidatepayment", method = RequestMethod.GET)
	public ResponseEntity<?> findOwnCandidatePayment(HttpServletRequest request){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			
			LocalDate currentDate = LocalDate.now();
			Month month = currentDate.getMonth(); 
			Integer year = currentDate.getYear();            
			
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findOwnCandidatePayment(emp.getEmployeeId(), month.name(),year.toString());
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Individual");
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/teamcandidatepayment", method = RequestMethod.GET)
	public ResponseEntity<?> findTeamCandidatePayment(HttpServletRequest request){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			
			LocalDate currentDate = LocalDate.now();
			Month month = currentDate.getMonth(); 
			Integer year = currentDate.getYear();            
			
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findTeamCandidatePayment(emp.getEmployeeId(), month.name(),year.toString());
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Team Leader");
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}	
	
	
	
	
	@RequestMapping(value = "/owncandidatepayment/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<?> findOwnCandidatePaymentByMonthYear(HttpServletRequest request,@PathVariable String month,@PathVariable String year){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findOwnCandidatePayment(emp.getEmployeeId(),month,year);
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Individual");
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/teamcandidatepayment/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<?> findTeamCandidatePaymentByMonthYear(HttpServletRequest request,@PathVariable String month,@PathVariable String year){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());            
			
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findTeamCandidatePayment(emp.getEmployeeId(),month,year);
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Team Leader");
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}
	
	
	
	
	@RequestMapping(value = "/allpaidcandidate", method = RequestMethod.GET)
	public ResponseEntity<?> findAllPaidCandidate(){
		List<PaymentDetails> paymentDetailsList = null;
		HttpStatus httpStatus = null;
		try{         
			paymentDetailsList = paymentDetailsService.findAllPaidCandidates();
			if(paymentDetailsList == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<PaymentDetails>>(paymentDetailsList, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/owncandidatepayment/{employeeId}/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<?> findOwnCandidatePaymentByMonthYearEmpId(@PathVariable String employeeId,@PathVariable String month,@PathVariable String year){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findOwnCandidatePayment(employeeId,month,year);
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Individual");
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/teamcandidatepayment/{employeeId}/{month}/{year}", method = RequestMethod.GET)
	public ResponseEntity<?> findTeamCandidatePaymentByMonthYearEmpId(@PathVariable String employeeId,@PathVariable String month,@PathVariable String year){
		
		PayamentDto paymentDto = null;
		HttpStatus httpStatus = null;
		try{            
			List<PaymentDetails> paymentDetailsList = paymentDetailsService.findTeamCandidatePayment(employeeId,month,year);
			
			double totalAmountInDoller = 0.0;
			
			if(paymentDetailsList != null && paymentDetailsList.size()>0){
				paymentDto = new PayamentDto();
				for(PaymentDetails p:paymentDetailsList){
					totalAmountInDoller = totalAmountInDoller + p.getAmount();
				}
				paymentDto.setPaymentList(paymentDetailsList);
				paymentDto.setTotalAmountInDoller(totalAmountInDoller);
				double incentive = incentiveStructureService.getIncentive(totalAmountInDoller,"Team Leader");
				
				paymentDto.setTotalIncentiveInInr(incentive);
			}
			
			if(paymentDto == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<PayamentDto>(paymentDto, httpStatus);
	}
	
	
}
