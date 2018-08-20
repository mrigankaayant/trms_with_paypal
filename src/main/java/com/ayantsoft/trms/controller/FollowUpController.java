package com.ayantsoft.trms.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.CandidateInfo;
import com.ayantsoft.trms.pojo.CreatedBy;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.FollowUp;
import com.ayantsoft.trms.pojo.MaxFollowup;
import com.ayantsoft.trms.pojo.Phone;
import com.ayantsoft.trms.pojo.UpdateFollowup;
import com.ayantsoft.trms.service.CandidateService;
import com.ayantsoft.trms.service.CredentialService;
import com.ayantsoft.trms.service.FollowUpService;
import com.ayantsoft.trms.service.MaxFollowupService;

@RestController
public class FollowUpController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5706626799791190082L;
	
	@Autowired 
	private FollowUpService followUpService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private MaxFollowupService maxFollowupService;
	
	
	@RequestMapping(value = "/recruiting/followup", method = RequestMethod.POST)
	public ResponseEntity<?> addFollowUp(@RequestBody UpdateFollowup updateFollowup,HttpServletRequest request){
          HttpStatus httpStatus = null; 
          FollowUp followup = null;
		try{
			Candidate candidate = null;
			CandidateInfo candidateinfo = new CandidateInfo();
			followup = new FollowUp();
			if(updateFollowup.getCandidateId() != null){
				candidate = candidateService.findCandidateById(updateFollowup.getCandidateId());
			}
			followup.setFollowupDate(new Date());
			if(updateFollowup.getFollowupRemarks() != null){
				followup.setFollowupRemarks(updateFollowup.getFollowupRemarks());
			}
			if(candidate.getEnrollmentStstus() != null){
				followup.setCandidateRemarks(candidate.getEnrollmentStstus());
				candidateinfo.setEnrollmentStstus(candidate.getEnrollmentStstus());
			}
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			if(emp != null){
				followup.setEmployeeId(emp.getEmployeeId());
			}
			if(candidate.getNextFollowupDate() != null){
				followup.setScheduledDate(candidate.getNextFollowupDate());
			}
			followup.setFollowUpType("APP");
			candidateinfo.setCandidateId(candidate.getCandidateId());
			candidateinfo.setCandidateName(candidate.getCandidateName());
			if(candidate.getSocialMedia() != null){
				if(candidate.getSocialMedia().getEmail() != null){
					candidateinfo.setEmail(candidate.getSocialMedia().getEmail());
				}
			}
			List<Phone> phones = candidate.getPhones();
     	    if(phones != null && phones.size()>0){
     	    	for(Phone p:phones){
     	    		if(p.getType().equals("Work Mobile")){
     	    			candidateinfo.setWorkMobile(p.getNumber());
     	    		}
     	    	}
     	    }
     	   if(candidate.getCreatedBy() != null){
				if(candidate.getCreatedBy().getName() != null){
					candidateinfo.setRecruiterName(candidate.getCreatedBy().getName());
				}
			}
     	   followup.setCandidateInfo(candidateinfo);
     	   followUpService.addFollowUp(followup);     	   
     	   candidate.setNextFollowupDate(updateFollowup.getNextFollowUpDate());
     	   candidateService.updateCandidate(candidate);
     	   
     	   // max follow up update
     	   
     	  MaxFollowup maxFollowup = maxFollowupService.findMaxFollowup(candidate.getCandidateId());
     	  if(maxFollowup != null){
     		 maxFollowup.setFollowupDate(new Date());
        	 maxFollowup.setEmployeeId(emp.getEmployeeId());
        	 maxFollowup.setScheduledDate(candidate.getNextFollowupDate());
        	 maxFollowup.setCandidateId(candidateinfo.getCandidateId());
        	 maxFollowup.setCandidateName(candidateinfo.getCandidateName());
        	 maxFollowup.setEmail(candidateinfo.getEmail());
        	 maxFollowup.setWorkMobile(candidateinfo.getWorkMobile());
        	 maxFollowup.setEnrollmentStstus(candidateinfo.getEnrollmentStstus());
 			 maxFollowup.setRecruiterName(candidateinfo.getRecruiterName());
 			 maxFollowupService.update(maxFollowup);
     	  }else{
     		 maxFollowup = new MaxFollowup();
     		 maxFollowup.setFollowupDate(new Date());
        	 maxFollowup.setEmployeeId(emp.getEmployeeId());
        	 maxFollowup.setScheduledDate(candidate.getNextFollowupDate());
        	 maxFollowup.setCandidateId(candidateinfo.getCandidateId());
        	 maxFollowup.setCandidateName(candidateinfo.getCandidateName());
        	 maxFollowup.setEmail(candidateinfo.getEmail());
        	 maxFollowup.setWorkMobile(candidateinfo.getWorkMobile());
        	 maxFollowup.setEnrollmentStstus(candidateinfo.getEnrollmentStstus());
 			 maxFollowup.setRecruiterName(candidateinfo.getRecruiterName());
 			 maxFollowupService.save(maxFollowup);
     	  }
     	    
		   httpStatus = HttpStatus.CREATED;
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<FollowUp>(followup, httpStatus);
	}
	
	
	
	@RequestMapping(value = "/freepool/update/followup", method = RequestMethod.POST)
	public ResponseEntity<?> addFollowForFreepool(@RequestBody UpdateFollowup updateFollowup,HttpServletRequest request){
		HttpStatus httpStatus = null;
		Candidate candidate = null;
		try{
			
			// update candidate 
		    candidate = candidateService.findCandidateById(updateFollowup.getCandidateId());
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			CreatedBy createdBy = new CreatedBy();
			if(emp != null){
				if(emp.getEmployeeId() != null){
					createdBy.setEmployeeId(emp.getEmployeeId());
				}
				if(emp.getName() != null){
					createdBy.setName(emp.getName());
				}
				if(emp.getWorkEmail() != null){
					createdBy.setEmailId(emp.getWorkEmail());
				}
				if(emp.getWorkMobile() != null){
					createdBy.setWorkPhone(emp.getWorkMobile());
				}
				if(emp.getSupervisorId().equals("0")){
					createdBy.setSupervisorId("0");					
				}else{
					createdBy.setSupervisorId(emp.getSupervisorId());
				}
			}
			candidate.setCreatedBy(createdBy);
			candidate.setNextFollowupDate(updateFollowup.getNextFollowUpDate());
			candidateService.updateCandidate(candidate);
			
			// Follow up entry
			
			FollowUp followup = new FollowUp();
			followup.setFollowupDate(new Date());
			followup.setFollowupRemarks(updateFollowup.getFollowupRemarks());
			followup.setCandidateRemarks(candidate.getEnrollmentStstus());
			followup.setEmployeeId(emp.getEmployeeId());
			followup.setScheduledDate(candidate.getNextFollowupDate());
			followup.setFollowUpType("APP");
			CandidateInfo candidateInfo = new CandidateInfo();
			candidateInfo.setCandidateId(candidate.getCandidateId());
			candidateInfo.setCandidateName(candidate.getCandidateName());
			candidateInfo.setEmail(candidate.getSocialMedia().getEmail());
			List<Phone> phones = candidate.getPhones();
     	    if(phones != null && phones.size()>0){
     	    	for(Phone p:phones){
     	    		if(p.getType().equals("Work Mobile")){
     	    			candidateInfo.setWorkMobile(p.getNumber());
     	    		}
     	    	}
     	    }
     	    
     	  candidateInfo.setEnrollmentStstus(candidate.getEnrollmentStstus());
     	  candidateInfo.setRecruiterName(emp.getName());
     	  followup.setCandidateInfo(candidateInfo);
     	  
     	 followUpService.addFollowUp(followup); 
     	 
     	 // update max followup date
     	 
     	 MaxFollowup maxFollowup = maxFollowupService.findMaxFollowup(candidate.getCandidateId());
    	 if(maxFollowup != null){
    		 maxFollowup.setFollowupDate(new Date());
       	     maxFollowup.setEmployeeId(emp.getEmployeeId());
       	     maxFollowup.setScheduledDate(candidate.getNextFollowupDate());
       	     maxFollowup.setCandidateId(candidate.getCandidateId());
       	     maxFollowup.setCandidateName(candidate.getCandidateName());
       	     maxFollowup.setEmail(candidate.getSocialMedia().getEmail());
       	     maxFollowup.setWorkMobile(candidateInfo.getWorkMobile());
       	     maxFollowup.setEnrollmentStstus(candidate.getEnrollmentStstus());
			 maxFollowup.setRecruiterName(candidateInfo.getRecruiterName());
			 maxFollowupService.update(maxFollowup);
    	  }else{
    		 maxFollowup = new MaxFollowup();
    		 maxFollowup.setFollowupDate(new Date());
       	     maxFollowup.setEmployeeId(emp.getEmployeeId());
       	     maxFollowup.setScheduledDate(candidate.getNextFollowupDate());
       	     maxFollowup.setCandidateId(candidate.getCandidateId());
       	     maxFollowup.setCandidateName(candidate.getCandidateName());
       	     maxFollowup.setEmail(candidate.getSocialMedia().getEmail());
       	     maxFollowup.setWorkMobile(candidateInfo.getWorkMobile());
       	     maxFollowup.setEnrollmentStstus(candidate.getEnrollmentStstus());
			 maxFollowup.setRecruiterName(candidateInfo.getRecruiterName());
			 maxFollowupService.save(maxFollowup);
    	  }
     	 
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<Candidate>(candidate,httpStatus);
	}
	
	
	
	
	
	@RequestMapping(value = "/recruiting/followUps/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findFollowuosById(@PathVariable String id){
		List<FollowUp> followUpList = null;
		HttpStatus httpStatus = null;
		try{
			followUpList = followUpService.findFollowUpByCandidateId(id);
			if(followUpList == null || followUpList.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<FollowUp>>(followUpList, httpStatus);
	}
	
	
	@RequestMapping(value = "/recruiting/freepool/followup", method = RequestMethod.POST)
	public ResponseEntity<?> addFollowUpForFreepool(@RequestBody UpdateFollowup updateFollowup,HttpServletRequest request){
          HttpStatus httpStatus = null; 
          FollowUp followup = null;
		try{
			Candidate candidate = null;
			CandidateInfo candidateinfo = new CandidateInfo();
			followup = new FollowUp();
			if(updateFollowup.getCandidateId() != null){
				candidate = candidateService.findCandidateById(updateFollowup.getCandidateId());
			}
			followup.setFollowupDate(new Date());
			if(updateFollowup.getFollowupRemarks() != null){
				followup.setFollowupRemarks(updateFollowup.getFollowupRemarks());
			}
			if(candidate.getEnrollmentStstus() != null){
				followup.setCandidateRemarks(candidate.getEnrollmentStstus());
				candidateinfo.setEnrollmentStstus(candidate.getEnrollmentStstus());
			}
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			if(emp != null){
				followup.setEmployeeId(emp.getEmployeeId());
			}
			if(candidate.getNextFollowupDate() != null){
				followup.setScheduledDate(candidate.getNextFollowupDate());
			}
			followup.setFollowUpType("APP");
			candidateinfo.setCandidateId(candidate.getCandidateId());
			candidateinfo.setCandidateName(candidate.getCandidateName());
			if(candidate.getSocialMedia() != null){
				if(candidate.getSocialMedia().getEmail() != null){
					candidateinfo.setEmail(candidate.getSocialMedia().getEmail());
				}
			}
			List<Phone> phones = candidate.getPhones();
     	    if(phones != null && phones.size()>0){
     	    	for(Phone p:phones){
     	    		if(p.getType().equals("Work Mobile")){
     	    			candidateinfo.setWorkMobile(p.getNumber());
     	    		}
     	    	}
     	    }
     	   if(candidate.getCreatedBy() != null){
				if(candidate.getCreatedBy().getName() != null){
					candidateinfo.setRecruiterName(candidate.getCreatedBy().getName());
				}
			}
     	   followup.setCandidateInfo(candidateinfo);
     	   followUpService.addFollowUp(followup); 
     	   
     	  candidate.getCreatedBy().setEmployeeId(emp.getEmployeeId());
     	  candidate.getCreatedBy().setName(emp.getName());
     	  candidate.getCreatedBy().setWorkPhone(emp.getWorkMobile());
     	  candidate.getCreatedBy().setEmailId(emp.getWorkEmail());
     	  candidate.getCreatedBy().setSupervisorId(emp.getSupervisorId());
     	  candidate.setNextFollowupDate(updateFollowup.getNextFollowUpDate());
     	  candidateService.updateCandidate(candidate);
     	  
		  httpStatus = HttpStatus.CREATED;
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<FollowUp>(followup, httpStatus);
	}
	
}
