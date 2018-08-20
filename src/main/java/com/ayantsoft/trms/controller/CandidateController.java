package com.ayantsoft.trms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ayantsoft.trms.doc.service.EnrollmentFormGenerateService;
import com.ayantsoft.trms.pojo.AddCandidateBean;
import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.CandidateCourse;
import com.ayantsoft.trms.pojo.CandidateInfo;
import com.ayantsoft.trms.pojo.CandidateSelectedCourse;
import com.ayantsoft.trms.pojo.CreatedBy;
import com.ayantsoft.trms.pojo.Education;
import com.ayantsoft.trms.pojo.Employee;
import com.ayantsoft.trms.pojo.EnrollmentFormDto;
import com.ayantsoft.trms.pojo.EnrollmentFormNo;
import com.ayantsoft.trms.pojo.FollowUp;
import com.ayantsoft.trms.pojo.Immigration;
import com.ayantsoft.trms.pojo.LoginCredential;
import com.ayantsoft.trms.pojo.MaxFollowup;
import com.ayantsoft.trms.pojo.PaymentDetails;
import com.ayantsoft.trms.pojo.Phone;
import com.ayantsoft.trms.pojo.PreferredLocation;
import com.ayantsoft.trms.pojo.RegistrationCandidateBean;
import com.ayantsoft.trms.pojo.Role;
import com.ayantsoft.trms.pojo.SearchCandidate;
import com.ayantsoft.trms.pojo.Skill;
import com.ayantsoft.trms.pojo.SocialMedia;
import com.ayantsoft.trms.pojo.UserMst;
import com.ayantsoft.trms.pojo.WorkExperience;
import com.ayantsoft.trms.service.CandidateService;
import com.ayantsoft.trms.service.CredentialService;
import com.ayantsoft.trms.service.EnrollmentFormService;
import com.ayantsoft.trms.service.FileUploadService;
import com.ayantsoft.trms.service.FolderPathService;
import com.ayantsoft.trms.service.FollowUpService;
import com.ayantsoft.trms.service.MailService;
import com.ayantsoft.trms.service.MaxFollowupService;
import com.ayantsoft.trms.service.PaymentDetailsService;
import com.ayantsoft.trms.service.UserMstService;


@RestController
public class CandidateController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8725001961202191256L;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private FollowUpService followUpService; 

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private MaxFollowupService maxFollowupService;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserMstService userMstService;

	@Autowired
	private EnrollmentFormService enrollmentFormService;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@Autowired
	private FolderPathService folderPathService;
	
	@Autowired
	private FileUploadService fileUploadService;



	@RequestMapping(value = "/recruiting/candidate", method = RequestMethod.POST)
	public ResponseEntity<?> addCandidate(@RequestBody AddCandidateBean addCandidateBean,HttpServletRequest request){
		HttpStatus httpStatus = null; 	

		try{
			Candidate candidate = new Candidate();
			SocialMedia socialMedia = new SocialMedia();
			List<Phone> phoneList = new ArrayList<Phone>();
			List<Immigration> immigrationList = new ArrayList<Immigration>();
			List<PreferredLocation> preferredlocationList = new ArrayList<PreferredLocation>();
			List<CandidateCourse> courseList = new ArrayList<CandidateCourse>();
			LoginCredential loginCredential = new LoginCredential();
			CreatedBy createdBy = new CreatedBy();
			candidate.setCandidateName(addCandidateBean.getCandidateName());
			if(addCandidateBean.getPayType() != null){
				candidate.setPayType(addCandidateBean.getPayType());
			}
			if(addCandidateBean.getEmail() != null){
				socialMedia.setEmail(addCandidateBean.getEmail());
			}
			if(addCandidateBean.getPayRate() != null){
				candidate.setPayRate(addCandidateBean.getPayRate());
			}
			if(addCandidateBean.getAlternateEmail() != null && addCandidateBean.getAlternateEmail().length() >1){
				socialMedia.setAlternateEmail(addCandidateBean.getAlternateEmail());
			}else{
				socialMedia.setAlternateEmail("N/A");
			}
			if(addCandidateBean.getGraduationDate() != null){
				candidate.setGraduationDate(addCandidateBean.getGraduationDate());
			}
			if(addCandidateBean.getWorkMobile() != null){
				Phone phone = new Phone();
				phone.setType("Work Mobile");
				phone.setNumber(addCandidateBean.getWorkMobile());
				phoneList.add(phone);
			}
			if(addCandidateBean.getSkill() != null && addCandidateBean.getSkill().size() > 0){
				for(String s:addCandidateBean.getSkill()){
					CandidateCourse course = new CandidateCourse();
					course.setCourse(s);
					courseList.add(course);
				}
			}
			if(addCandidateBean.getHomeMobile() != null){
				Phone phone = new Phone();
				phone.setType("Home Mobile");
				if(addCandidateBean.getHomeMobile() != null && addCandidateBean.getHomeMobile().length() >1){
					phone.setNumber(addCandidateBean.getHomeMobile());
				}else{
					phone.setNumber("N/A");		
				}
				phoneList.add(phone);
			}
			Immigration immigration = new Immigration();
			if(addCandidateBean.getVisa() != null){
				immigration.setImmigrationType(addCandidateBean.getVisa());
				immigration.setStatus("Active");

			}
			if(addCandidateBean.getVisaStartDate() != null){
				immigration.setStartDate(addCandidateBean.getVisaStartDate());
			}



			immigrationList.add(immigration);
			if(addCandidateBean.getRecruitmentSource() != null){
				candidate.setRecruitmentSource(addCandidateBean.getRecruitmentSource());
			}
			if(addCandidateBean.getCourseFee() != null){
				candidate.setCourseFee(addCandidateBean.getCourseFee());
			}
			if(addCandidateBean.getCurrentLocation() != null){
				candidate.setCurrentLocation(addCandidateBean.getCurrentLocation());
			}
			if(addCandidateBean.getServiceName() != null){
				candidate.setServiceName(addCandidateBean.getServiceName());
			}
			List<PreferredLocation> list = addCandidateBean.getPreferredLocations();
			if(list != null && list.size() >0){
				for(PreferredLocation p:list){
					PreferredLocation pl = new PreferredLocation();
					pl.setLocation(p.getLocation());
					preferredlocationList.add(pl);
				}	
			}
			candidate.setCandidateCourses(courseList);
			candidate.setPhones(phoneList);
			candidate.setSocialMedia(socialMedia);
			candidate.setLoginCredential(loginCredential);
			candidate.setPreferredLocations(preferredlocationList);
			candidate.setImmigrations(immigrationList);
			candidate.setCreatedDate(new Date());
			candidate.setEnrollmentStstus(addCandidateBean.getEnrollmentStstus());
			candidate.setNextFollowupDate(new Date());

			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
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
				candidate.setCreatedBy(createdBy);
			}

			candidateService.addCandidate(candidate);

			FollowUp followUp = new FollowUp();
			followUp.setFollowupDate(new Date());
			followUp.setFollowupRemarks("Candidate Created");
			followUp.setCandidateRemarks(candidate.getEnrollmentStstus());
			followUp.setScheduledDate(new Date());
			followUp.setEmployeeId(candidate.getCreatedBy().getEmployeeId());
			followUp.setFollowUpType("App");
			CandidateInfo candidateInfo = new CandidateInfo();
			candidateInfo.setCandidateId(candidate.getCandidateId());
			candidateInfo.setCandidateName(candidate.getCandidateName());	
			candidateInfo.setEmail(candidate.getSocialMedia().getEmail());
			candidateInfo.setWorkMobile(addCandidateBean.getWorkMobile());
			candidateInfo.setEnrollmentStstus(candidate.getEnrollmentStstus());
			candidateInfo.setRecruiterName(candidate.getCreatedBy().getName());
			followUp.setCandidateInfo(candidateInfo);

			followUpService.addFollowUp(followUp);

			// max followup save

			MaxFollowup maxFollowup = new MaxFollowup();
			maxFollowup.setFollowupDate(new Date());
			maxFollowup.setEmployeeId(candidate.getCreatedBy().getEmployeeId());
			maxFollowup.setScheduledDate(new Date());
			maxFollowup.setCandidateId(candidate.getCandidateId());
			maxFollowup.setCandidateName(candidate.getCandidateName());
			maxFollowup.setEmail(candidate.getSocialMedia().getEmail());
			maxFollowup.setWorkMobile(addCandidateBean.getWorkMobile());
			maxFollowup.setEnrollmentStstus(candidate.getEnrollmentStstus());
			maxFollowup.setRecruiterName(candidate.getCreatedBy().getName());

			maxFollowupService.save(maxFollowup);

			addCandidateBean.setCandidateId(candidate.getCandidateId());
			addCandidateBean.setNextFollowUpDate(candidate.getNextFollowupDate());

			httpStatus = HttpStatus.CREATED;
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<AddCandidateBean>(addCandidateBean, httpStatus);
	}



	/*@RequestMapping(value = "/recruiting/candidate/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCandidateById(@PathVariable String id){
		Candidate candidate = null;
		HttpStatus httpStatus = null; 
		try{
			candidate = candidateService.findCandidateById(id);
			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				followupCandidateInfo = new FollowupCandidateInfo();
				followupCandidateInfo.setCandidateId(candidate.getCandidateId());
				followupCandidateInfo.setCandidateName(candidate.getCandidateName());
				if(candidate.getSocialMedia() != null){
					if(candidate.getSocialMedia().getEmail() != null){
						followupCandidateInfo.setEmail(candidate.getSocialMedia().getEmail());
					}
				}
				List<Phone> phones = candidate.getPhones();
				if(phones != null && phones.size()>0){
					for(Phone p:phones){
						if(p.getType().equals("Work Mobile")){
							followupCandidateInfo.setWorkMobile(p.getNumber());
						}
					}
				}
				List<Immigration> immigrations = candidate.getImmigrations();
				if(immigrations != null && immigrations.size()>0){
					String im = null;
					for(Immigration i:immigrations){
						if(im == null){
							im = i.getImmigrationType();
						}else{
							im = im+","+i.getImmigrationType();
						}	
					}
					followupCandidateInfo.setVisa(im);
				}

				List<CandidateCourse> courses = candidate.getCandidateCourses();
				if(courses != null && courses.size()>0){
					String cList = null;
					for(CandidateCourse c:courses){
						if(cList == null){
							cList = c.getCourse();
						}else{
							cList = cList+","+c.getCourse();
						}	
					}
					followupCandidateInfo.setSkill(cList);
				}

				if(candidate.getEnrollmentStstus() != null){
					followupCandidateInfo.setEnrollmentStstus(candidate.getEnrollmentStstus());
				}

				if(candidate.getNextFollowupDate() != null){
					followupCandidateInfo.setNextFollowupDate(candidate.getNextFollowupDate());
				} 

				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<FollowupCandidateInfo>(followupCandidateInfo, httpStatus);
	}
	 */

	@RequestMapping(value = "/recruiting/candidates", method = RequestMethod.GET)
	public ResponseEntity<?> findCandidates(HttpServletRequest request){
		List<Candidate> candidates = null;
		HttpStatus httpStatus = null;
		try{	
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			candidates = candidateService.candidatesList(isRole("Admin",emp.getRoles()),emp.getEmployeeId());
			if(candidates == null || candidates.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Candidate>>(candidates, httpStatus);
	}
	
	@RequestMapping(value = "/hr/candidateList", method = RequestMethod.GET)
	public ResponseEntity<?> findEligableCandidatesForTraning(HttpServletRequest request){
		List<Candidate> candidates = null;
		HttpStatus httpStatus = null;
		try{	
			System.out.println("Hit");
			candidates = candidateService.findTraningEligableCandidateList();
			if(candidates == null || candidates.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Candidate>>(candidates, httpStatus);
	}



	/*@RequestMapping(value = "/recruiting/candidates", method = RequestMethod.POST)
	public ResponseEntity<?> findCandidates(@RequestBody LazyCandidateDto lazyCandidateDto,HttpServletRequest request){
		List<Candidate> candidates = null;
		HttpStatus httpStatus = null;
		try{	
			Principal principal = request.getUserPrincipal();
			Employee emp = credentialService.getEmployee(principal.getName());
			//candidates = candidateService.candidatesList(isRole("Admin",emp.getRoles()),emp.getEmployeeId());

			candidates = candidateService.candidatesList(lazyCandidateDto.getFirst(),lazyCandidateDto.getRows(),lazyCandidateDto.getSortField(),lazyCandidateDto.getSortOrder(),lazyCandidateDto.getFilters(),isRole("Admin",emp.getRoles()),emp.getEmployeeId());


			if(candidates == null || candidates.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Candidate>>(candidates, httpStatus);
	}*/



	@RequestMapping(value = "/recruiting/view/candidate/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCandidateByIdForShow(@PathVariable String id){
		Candidate candidate = null;
		HttpStatus httpStatus = null;
		try{
			candidate = candidateService.findCandidateById(id);

			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}



	@RequestMapping(value = "/recruiting/searchCandidate", method = RequestMethod.POST)
	public ResponseEntity<?> searchCandidate(@RequestBody SearchCandidate searchCandidate){

		HttpStatus httpStatus = null;  
		List<Candidate> candidates = null;
		try{
			candidates = candidateService.findSearchingCandidate(searchCandidate);
			if(candidates == null || candidates.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Candidate>>(candidates, httpStatus);
	}




	@RequestMapping(value = "/recruiting/update/candidate/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCandidate(@PathVariable String id){
		HttpStatus httpStatus = null; 
		AddCandidateBean addCandidateBean = new AddCandidateBean();
		try{
			Candidate candi = candidateService.findCandidateById(id);
			if(candi != null){
				addCandidateBean.setCandidateId(candi.getCandidateId());
				addCandidateBean.setCandidateName(candi.getCandidateName());
				if(candi.getPayType() != null){
					addCandidateBean.setPayType(candi.getPayType());
				}
				if(candi.getSocialMedia() != null){
					if(candi.getSocialMedia().getEmail() !=null){
						addCandidateBean.setEmail(candi.getSocialMedia().getEmail());
					}
				}
				if(candi.getPayRate() != null){
					addCandidateBean.setPayRate(candi.getPayRate());
				}
				if(candi.getSocialMedia() != null){
					if(candi.getSocialMedia().getAlternateEmail() !=null){
						addCandidateBean.setAlternateEmail(candi.getSocialMedia().getAlternateEmail());
					}
				}
				if(candi.getGraduationDate() != null){
					addCandidateBean.setGraduationDate(candi.getGraduationDate());
				}
				List<Phone> phoneList = candi.getPhones();
				if(phoneList != null && phoneList.size() >0){
					for(Phone p:phoneList){
						if(p.getType().equals("Work Mobile")){
							addCandidateBean.setWorkMobile(p.getNumber());
						}
						if(p.getType().equals("Home Mobile")){
							addCandidateBean.setHomeMobile(p.getNumber());
						}
					}
				}

				List<CandidateCourse> courseList = candi.getCandidateCourses();
				if(courseList != null && courseList.size() >0){
					List<String> skillList = new ArrayList<String>();
					for(CandidateCourse c:courseList){
						skillList.add(c.getCourse());
					}
					addCandidateBean.setSkill(skillList);
				}	


				if(candi.getEnrollmentStstus() != null){
					addCandidateBean.setEnrollmentStstus(candi.getEnrollmentStstus());
				}

				List<Immigration> immigrationList = candi.getImmigrations();
				if(immigrationList != null && immigrationList.size() >0){
					for(Immigration i:immigrationList){
						addCandidateBean.setVisa(i.getImmigrationType());
						if(i.getStartDate() != null){
							addCandidateBean.setVisaStartDate(i.getStartDate());
						}
					}
				}

				if(candi.getRecruitmentSource() != null){
					addCandidateBean.setRecruitmentSource(candi.getRecruitmentSource());
				}
				if(candi.getCourseFee() != null){
					addCandidateBean.setCourseFee(candi.getCourseFee());
				}

				if(candi.getServiceName() != null){
					addCandidateBean.setServiceName(candi.getServiceName());
				}

				if(candi.getCurrentLocation() != null){
					addCandidateBean.setCurrentLocation(candi.getCurrentLocation());
				}
				List<PreferredLocation> preferredLocations = candi.getPreferredLocations();
				if(preferredLocations != null && preferredLocations.size()>0 ){
					addCandidateBean.setPreferredLocations(preferredLocations);
				} 
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<AddCandidateBean>(addCandidateBean, httpStatus);
	}



	@RequestMapping(value = "/recruiting/update/candidate", method = RequestMethod.POST)
	public ResponseEntity<?> updateCandidate(@RequestBody AddCandidateBean addCandidateBean){

		HttpStatus httpStatus = null; 
		Candidate candidate = null;
		try{
			candidate = candidateService.findCandidateById(addCandidateBean.getCandidateId());
			if(candidate != null){
				candidate.setCandidateName(addCandidateBean.getCandidateName());
				if(addCandidateBean.getPayType() != null){
					candidate.setPayType(addCandidateBean.getPayType());
				}
				if(addCandidateBean.getEmail() != null){
					if(candidate.getSocialMedia() != null){
						candidate.getSocialMedia().setEmail(addCandidateBean.getEmail());
						candidate.getSocialMedia().setAlternateEmail(addCandidateBean.getAlternateEmail());
					}
				}
				if(addCandidateBean.getPayRate() != null){
					candidate.setPayRate(addCandidateBean.getPayRate());
				}
				if(addCandidateBean.getGraduationDate() != null){
					candidate.setGraduationDate(addCandidateBean.getGraduationDate());
				}

				List<Phone> phoneList = candidate.getPhones();
				for(Phone p:phoneList){
					if(p.getType().equals("Work Mobile")){
						if(addCandidateBean.getWorkMobile() != null){
							p.setNumber(addCandidateBean.getWorkMobile());
						}
					}
					if(p.getType().equals("Home Mobile")){
						if(addCandidateBean.getHomeMobile() != null){
							p.setNumber(addCandidateBean.getHomeMobile());
						}
					}
				}


				List<CandidateCourse> courseList = new ArrayList<CandidateCourse>();
				if(addCandidateBean.getSkill() != null && addCandidateBean.getSkill().size() > 0){
					for(String s:addCandidateBean.getSkill()){
						CandidateCourse course = new CandidateCourse();
						course.setCourse(s);
						courseList.add(course);
					}
				}

				if(courseList != null && courseList.size() >0){
					candidate.setCandidateCourses(courseList);
				}


				if(addCandidateBean.getEnrollmentStstus() != null){
					candidate.setEnrollmentStstus(addCandidateBean.getEnrollmentStstus());
				}

				List<Immigration> immigrationList = candidate.getImmigrations();
				for(Immigration i:immigrationList){
					if(addCandidateBean.getVisa() != null){
						i.setImmigrationType(addCandidateBean.getVisa());
					}
					if(addCandidateBean.getVisaStartDate() != null){
						i.setStartDate(addCandidateBean.getVisaStartDate());
					}
				}

				if(addCandidateBean.getServiceName() != null){
					candidate.setServiceName(addCandidateBean.getServiceName());
				}

				if(addCandidateBean.getRecruitmentSource() != null){
					candidate.setRecruitmentSource(addCandidateBean.getRecruitmentSource());
				}
				if(addCandidateBean.getCourseFee() != null){
					candidate.setCourseFee(addCandidateBean.getCourseFee());
				}
				if(addCandidateBean.getCurrentLocation() != null){
					candidate.setCurrentLocation(addCandidateBean.getCurrentLocation());
				}

				if(addCandidateBean.getPreferredLocations() != null && addCandidateBean.getPreferredLocations().size() >0){
					candidate.setPreferredLocations(addCandidateBean.getPreferredLocations());
				}

				if(addCandidateBean.getEnrollmentStstus().equals("Enrolled")){
					candidate.setEnrollmentDate(new Date());
				}

				candidateService.updateCandidate(candidate);

				httpStatus = HttpStatus.CREATED;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}



	@RequestMapping(value = "/recruiting/freepool/candidates", method = RequestMethod.GET)
	public ResponseEntity<?> getFreePoolCandidates(){
		HttpStatus httpStatus = null;
		List<MaxFollowup> freePoolsCandidates = null;
		try{

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -30);
			Date dateBefore30Days = cal.getTime();

			freePoolsCandidates = maxFollowupService.findFreePoolCandidates(dateBefore30Days);

			if(freePoolsCandidates == null || freePoolsCandidates.isEmpty()){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<MaxFollowup>>(freePoolsCandidates, httpStatus);
	}




	@RequestMapping(value = "/recruiting/checkEmail/{email:.+}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> checkEmail(@PathVariable String email,@PathVariable String id){
		String val = null;
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] b = decoder.decode(email);
			val = new String(b); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Hit In Email"+email+"   id"+id);
		HttpStatus httpStatus = null; 
		Candidate candidate = null;
		try{
			candidate = candidateService.checkEmail(val,id);
			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}	


	@RequestMapping(value = "/recruiting/checkMobile/{mobile}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> checkMobile(@PathVariable String mobile,@PathVariable String id){

		System.out.println("Hit In Mobile"+mobile);
		HttpStatus httpStatus = null; 
		Candidate candidate = null;
		try{
			candidate = candidateService.checkMobile(mobile,id);
			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{
				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}



	@RequestMapping(value = "/sendEnrollmentForm/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> sendingEnrollmentForm(@PathVariable String id){

		HttpStatus httpStatus = null;
		Candidate candidate = null;
		UserMst userMast = null;
		try{
			candidate = candidateService.findCandidateById(id);
			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{

				if(candidate.getLoginCredential() != null && candidate.getLoginCredential().getUsername() != null){
					userMast = userMstService.getUserMstByUsername(candidate.getLoginCredential().getUsername());
				}

				if(candidate.getSocialMedia().getEmail() != null){
					Calendar now = Calendar.getInstance();  
					int year = now.get(Calendar.YEAR);
					String password = candidate.getCandidateName().replaceAll("\\s","")+"GIT"+year;	
					String message = "Your UserName : "+candidate.getSocialMedia().getEmail()+" Password : "+password;

					if(userMast == null){
						userMast = new UserMst();
					}
					userMast.setUsername(candidate.getSocialMedia().getEmail());
					userMast.setPassword(password);
					userMast.setActive(true);
					userMast.setUserType("Candidate");
					if(userMast.getId() == null){
						userMast = userMstService.createUser(userMast);
					}else{
						userMstService.updateUserMast(userMast); 
					}
					candidate.setUserMstId(userMast.getUserId());

					LoginCredential loginCredential = new LoginCredential();
					loginCredential.setUsername(userMast.getUsername());
					loginCredential.setPassword(userMast.getPassword());
					loginCredential.setActive(userMast.isActive());

					candidate.setLoginCredential(loginCredential);

					candidateService.updateCandidate(candidate);

					mailService.sendMail("sghosh@globalitexperts.com",candidate.getSocialMedia().getEmail(),message,"Here will be subject");
				}else{
					candidate = null;
				}

				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
			candidate = null;
		}
		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}


	@RequestMapping(value = "/update/candidatecourse", method = RequestMethod.POST)
	public ResponseEntity<?> updateCandidateCourse(@RequestBody CandidateSelectedCourse candidateSelectedCourse){
		HttpStatus httpStatus = null; 
		Candidate candidate = null;
		try{
			candidate = candidateService.findCandidateById(candidateSelectedCourse.getCandidateId());
			if(candidate != null){
				if(candidateSelectedCourse.getCandidateFinalCourses() != null && candidateSelectedCourse.getCandidateFinalCourses().size()>0){
					candidate.setCandidateCourses(candidateSelectedCourse.getCandidateFinalCourses());
					candidateService.updateCandidate(candidate);
					httpStatus = HttpStatus.OK;
				}
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Candidate>(candidate, httpStatus);
	}



	@RequestMapping(value = "/candidate/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> findCandidateByUserId(@PathVariable String userId){
		HttpStatus httpStatus = null;
		RegistrationCandidateBean registrationCandidateBean = null;
		try{
			Candidate candidate = candidateService.findCandidateByUserId(userId);
			if(candidate == null){
				httpStatus = HttpStatus.NO_CONTENT;
			}else{  
				registrationCandidateBean = new RegistrationCandidateBean();
				registrationCandidateBean.setId(candidate.getId());
				registrationCandidateBean.setCandidateId(candidate.getCandidateId());
				registrationCandidateBean.setCandidateName(candidate.getCandidateName());
				if(candidate.getDateOfBirth() != null){
					registrationCandidateBean.setDateOfBirth(candidate.getDateOfBirth());
				}
				if(candidate.getGender() != null){
					registrationCandidateBean.setGender(candidate.getGender());
				}
				registrationCandidateBean.setEmail(candidate.getSocialMedia().getEmail());
				if(candidate.getSocialMedia().getAlternateEmail() != null){
					registrationCandidateBean.setAlternateEmail(candidate.getSocialMedia().getAlternateEmail());
				}
				List<Phone> phoneList = candidate.getPhones();
				if(phoneList != null && phoneList.size() >0){
					for(Phone p:phoneList){
						if(p.getType().equals("Work Mobile")){
							registrationCandidateBean.setWorkMobile(p.getNumber());
						}
						if(p.getType().equals("Home Mobile")){
							registrationCandidateBean.setHomeMobile(p.getNumber());
						}
					}
				}

				if(candidate.getRegistrationDate() != null){
					registrationCandidateBean.setRegistrationDate(candidate.getRegistrationDate());
				}

				if(candidate.getPaymentCounter() != null){
					registrationCandidateBean.setPaymentCounter(candidate.getPaymentCounter());
				}

				if(candidate.getCountry() != null){
					registrationCandidateBean.setCountry(candidate.getCountry());
				}
				if(candidate.getState() != null){
					registrationCandidateBean.setState(candidate.getState());
				}
				if(candidate.getCity() != null){
					registrationCandidateBean.setCity(candidate.getCity());
				}
				if(candidate.getZip() != null){
					registrationCandidateBean.setZip(candidate.getZip());
				}
				if(candidate.getCurrentLocation() != null){
					registrationCandidateBean.setCurrentLocation(candidate.getCurrentLocation());
				}
				List<PreferredLocation> preferredLocations = candidate.getPreferredLocations();
				if(preferredLocations != null && preferredLocations.size()>0 ){
					registrationCandidateBean.setPreferredLocations(preferredLocations);
				}else{
					List<PreferredLocation> pList = new ArrayList<PreferredLocation>();
					registrationCandidateBean.setPreferredLocations(pList);
				}
				if(candidate.getVisas() != null && candidate.getVisas().size() >0){
					registrationCandidateBean.setVisas(candidate.getVisas());
				}else{
					List<Immigration> vList = new ArrayList<Immigration>();
					registrationCandidateBean.setVisas(vList);
				}
				if(candidate.getEmploymentTypes() != null){
					registrationCandidateBean.setEmploymentTypes(candidate.getEmploymentTypes());
				}
				if(candidate.getWorkAuthorization() != null){
					registrationCandidateBean.setWorkAuthorization(candidate.getWorkAuthorization());
				}

				if(candidate.getWorkExperiences() != null && candidate.getWorkExperiences().size() >0){
					registrationCandidateBean.setWorkExperiences(candidate.getWorkExperiences());
				}else{
					List<WorkExperience> wList = new ArrayList<WorkExperience>();
					registrationCandidateBean.setWorkExperiences(wList);
				}

				if(candidate.getSkills() != null && candidate.getSkills().size() >0){
					registrationCandidateBean.setSkills(candidate.getSkills());
				}else{
					List<Skill> sList = new ArrayList<Skill>();
					registrationCandidateBean.setSkills(sList);
				}

				if(candidate.getCandidateCourses() != null && candidate.getCandidateCourses().size() >0){
					registrationCandidateBean.setCandidateCourses(candidate.getCandidateCourses());
				}else{
					List<CandidateCourse> courseList = new ArrayList<CandidateCourse>();
					registrationCandidateBean.setCandidateCourses(courseList);
				}

				if(candidate.getEducations() != null && candidate.getEducations().size() >0){
					registrationCandidateBean.setEducations(candidate.getEducations());
				}else{
					List<Education> eList = new ArrayList<Education>();
					registrationCandidateBean.setEducations(eList);
				}

				httpStatus = HttpStatus.OK;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<RegistrationCandidateBean>(registrationCandidateBean,httpStatus);
	}




	@RequestMapping(value = "/saveRegistration", method = RequestMethod.POST)
	public ResponseEntity<?> saveRegistration(@RequestBody RegistrationCandidateBean registrationCandidateBean){
		HttpStatus httpStatus = null;
		try{
			Candidate candidate = candidateService.findCandidateById(registrationCandidateBean.getCandidateId());
			if(candidate != null){
				if(registrationCandidateBean.getDateOfBirth() != null){
					candidate.setDateOfBirth(registrationCandidateBean.getDateOfBirth());
				}
				if(registrationCandidateBean.getGender() != null){
					candidate.setGender(registrationCandidateBean.getGender());
				}
				if(registrationCandidateBean.getAlternateEmail() != null){
					candidate.getSocialMedia().setAlternateEmail(registrationCandidateBean.getAlternateEmail());
				}else{

					candidate.getSocialMedia().setAlternateEmail("N/A");
				}
				List<Phone> phoneList = candidate.getPhones();
				if(registrationCandidateBean.getHomeMobile() != null){
					for(Phone p:phoneList){
						if(p.getType().equals("Home Mobile")){
							p.setNumber(registrationCandidateBean.getHomeMobile());
						}
					}
				}else{
					for(Phone p:phoneList){
						if(p.getType().equals("Home Mobile")){
							p.setNumber("N/A");
						}
					}
				}
				candidate.setRegistrationDate(new Date());
				if(registrationCandidateBean.getCountry() != null){
					candidate.setCountry(registrationCandidateBean.getCountry());
				}
				if(registrationCandidateBean.getState() != null){
					candidate.setState(registrationCandidateBean.getState());
				}
				if(registrationCandidateBean.getCity() != null){
					candidate.setCity(registrationCandidateBean.getCity());
				}
				if(registrationCandidateBean.getZip() != null){
					candidate.setZip(registrationCandidateBean.getZip());
				}
				if(registrationCandidateBean.getCurrentLocation() != null){
					candidate.setCurrentLocation(registrationCandidateBean.getCurrentLocation());
				}
				if(registrationCandidateBean.getVisas() != null && registrationCandidateBean.getVisas().get(0) != null){
					if(registrationCandidateBean.getVisas().get(0).getImmigrationType().length() >1){
						candidate.setVisas(registrationCandidateBean.getVisas());
					}
				}
				if(registrationCandidateBean.getPreferredLocations() != null && registrationCandidateBean.getPreferredLocations().get(0) != null){
					candidate.setPreferredLocations(registrationCandidateBean.getPreferredLocations());
				}
				if(registrationCandidateBean.getEmploymentTypes() != null){
					candidate.setEmploymentTypes(registrationCandidateBean.getEmploymentTypes());
				}else{
					candidate.setEmploymentTypes("N/A");
				}
				if(registrationCandidateBean.getWorkAuthorization() != null){
					candidate.setWorkAuthorization(registrationCandidateBean.getWorkAuthorization());
				}else{
					candidate.setWorkAuthorization("N/A");
				}
				if(registrationCandidateBean.getWorkExperiences() != null && registrationCandidateBean.getWorkExperiences().get(0) != null){
					if(registrationCandidateBean.getWorkExperiences().get(0).getJobTitle().length() >1){
						candidate.setWorkExperiences(registrationCandidateBean.getWorkExperiences());	
					}
				}
				if(registrationCandidateBean.getSkills() != null && registrationCandidateBean.getSkills().get(0) != null){
					if(registrationCandidateBean.getSkills().get(0).getSkillName().length() >1){
						candidate.setSkills(registrationCandidateBean.getSkills());
					}
				}
				if(registrationCandidateBean.getEducations() != null && registrationCandidateBean.getEducations().get(0) != null){
					if(registrationCandidateBean.getEducations().get(0).getHighestDegree().length() >1){
						candidate.setEducations(registrationCandidateBean.getEducations());
					}
				}
				if(registrationCandidateBean.getTwitterUrl() != null){
					candidate.getSocialMedia().setTwitter(registrationCandidateBean.getTwitterUrl());
				}else{
					candidate.getSocialMedia().setTwitter("N/A");
				}
				if(registrationCandidateBean.getFacebookUrl() != null){
					candidate.getSocialMedia().setFacebook(registrationCandidateBean.getFacebookUrl());
				}else{
					candidate.getSocialMedia().setFacebook("N/A");
				}
				if(registrationCandidateBean.getLinkedinUrl() != null){
					candidate.getSocialMedia().setLinkedin(registrationCandidateBean.getLinkedinUrl());
				}else{
					candidate.getSocialMedia().setLinkedin("N/A");
				}
				if(registrationCandidateBean.getPersonalWebsite() != null){
					candidate.getSocialMedia().setPersonalWebSite(registrationCandidateBean.getPersonalWebsite());
				}else{
					candidate.getSocialMedia().setPersonalWebSite("N/A");
				}
				candidateService.updateCandidate(candidate);
				httpStatus = HttpStatus.CREATED;
			}else{
				httpStatus = HttpStatus.NO_CONTENT;
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<RegistrationCandidateBean>(registrationCandidateBean, httpStatus);
	}


	@RequestMapping(value = "/candidate/generateEnrollment/{candidateId}", method = RequestMethod.GET)
	public ResponseEntity<?> generateCandidateEnrollmentForm(@PathVariable String candidateId){
 
        Candidate candidate = null;
		HttpStatus httpStatus = null;
		try{
			System.out.println("Enter");
			candidate = candidateService.findCandidateById(candidateId);
			System.out.println("Candidate_Id"+candidate.getCandidateId());
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int enrollmentFormNo = (enrollmentFormService.getMaxFormNumber("EnrollmentForm").getEnrollmentFormNumber() +1);
			System.out.println("Enrollment Form Number"+enrollmentFormNo);
			String service = candidate.getServiceName();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String enrollmentDate = df.format(new Date());
			String tentiveTrainingStartDate = "Training will be start with in 2 week";
			List<PaymentDetails> paymentList = paymentDetailsService.findPaymentDetailsByCandidateId(candidateId);
			System.out.println("Size Of Payment List"+paymentList.size());
			PaymentDetails paymentDetails = null;
			String courseName = null;
			int totalAmount = 0;
			int amountPaid = 0;
			String dueAmount = null;
			String transactionId = null;
			String paymentDate = null;
			String dueDate = null;
			String paymentMode = null;
			if(paymentList != null && paymentList.size() >0){
				//System.out.println("Enter");
				paymentDetails = paymentList.get(0);
				courseName = paymentDetails.getCoureseName();
				totalAmount = paymentDetails.getPricePerItem();
				paymentDate = paymentDetails.getCreatedDate().toString();
				amountPaid = paymentDetails.getAmount();
				transactionId = paymentDetails.getPaymentInfo().getPayemntId();
				if(paymentDetails.getDueAmount() == 0){
					dueAmount = "N/A";
				}else{
					dueAmount = paymentDetails.getDueAmount().toString();
				}				
				dueDate = "After complete Training";
				paymentMode = paymentDetails.getPaymentInfo().getPayer().getPaymentMethod();
			}

			String folderPath = folderPathService.getFolderPath("Generate Enrollment Form");
			System.out.println("Folder Path"+folderPath);

			EnrollmentFormDto enrollmentFormDto = new EnrollmentFormDto();
			enrollmentFormDto.setYear(year);
			enrollmentFormDto.setEnrollmentFormNo(enrollmentFormNo);
			enrollmentFormDto.setCourseName(courseName);
			enrollmentFormDto.setService(service);
			enrollmentFormDto.setDateOfEnrollment(enrollmentDate);
			enrollmentFormDto.setTentiveTrainingStartDate(tentiveTrainingStartDate);
			enrollmentFormDto.setTotalAmount(totalAmount);
			enrollmentFormDto.setPaymentDate(paymentDate);
			enrollmentFormDto.setAmountPaid(amountPaid);
			enrollmentFormDto.setTransactionId(transactionId);
			enrollmentFormDto.setDueAmount(dueAmount);
			enrollmentFormDto.setDueDate(dueDate);
			enrollmentFormDto.setPaymentMode(paymentMode);


			System.out.println(enrollmentFormDto);

			File file = new File(folderPath+candidate.getCandidateName()+"_"+candidate.getCandidateId());
			if(file.exists()){
				File[] listOfFiles = file.listFiles();
				if(listOfFiles != null && listOfFiles.length >0){
					file = new File(folderPath+candidate.getCandidateName()+"_"+candidate.getCandidateId()+"/"+listOfFiles[0]);
					file.delete();
				}
			}else{
				file.mkdir();
			}

			enrollmentFormDto.setFolderPathWithFile(folderPath+candidate.getCandidateName()+"_"+candidate.getCandidateId()+"/enrollment_form.pdf");

			InputStream inputStream =  EnrollmentFormGenerateService.createenrollmentFormPdf(enrollmentFormDto);
			
			if(inputStream != null){
				System.out.println("======  INPUTSTREAM NOT NULL ==========");
			}else{
				System.out.println("======== INPUTSTREAM IS NULL ===========");
			}

			EnrollmentFormNo enFormNo = enrollmentFormService.getMaxFormNumber("EnrollmentForm");

			enFormNo.setEnrollmentFormNumber(enFormNo.getEnrollmentFormNumber()+1);

			enrollmentFormService.update(enFormNo);

			String downloadPath = folderPathService.getFolderPath("Generate Enrollment Form Download");

			candidate.setGeneratedEnrollmentForm(downloadPath+candidate.getCandidateName()+"_"+candidate.getCandidateId()+"/enrollment_form.pdf"); 

			candidateService.updateCandidate(candidate);

			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			System.out.println(e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Candidate>(candidate,httpStatus);
	}



	@RequestMapping(value = "/upload/enrollmentForm/{candidateId}/{candidateName}", method = RequestMethod.POST)
	public ResponseEntity<?> uploadEnrollmentForm(@RequestParam("file") MultipartFile file,@PathVariable String candidateId,@PathVariable String candidateName){
		Candidate candidate = null;
		HttpStatus httpStatus = null;
		String filename = candidateName+"_"+candidateId;
		try{
			if(file != null){
				System.out.println(file.getOriginalFilename());
				File convFile = new File(file.getOriginalFilename());
			    convFile.createNewFile(); 
			    FileOutputStream fos = new FileOutputStream(convFile); 
			    fos.write(file.getBytes());
			    
			    
			    fos.close(); 
			    
			    System.out.println("FILE NAME : "+convFile.getName()+"     CANDIDATE ID : "+candidateId);

			    fileUploadService.uploadEnrollmentForm(candidateName+"_"+candidateId,convFile,file.getContentType());
			}
			
			/*String folderPath = folderPathService.getFolderPath("Generate Uploaded Enrollment Form");
			String folderPathForHr = folderPathService.getFolderPath("Generate Uploaded Enrollment Form For HR");
			
			//For Enrollment Form View For Hr//
			String uploadedEnrollmentFormPath = folderPathService.getFolderPath("Uploaded Enrollment Form Download");
		    fileUploadService.downLoadEnrollmentFrom(folderPathForHr,filename,candidateName,candidateId);
			System.out.println("Upload Path"+uploadedEnrollmentFormPath);
			//End Here//
			
			String extension = fileUploadService.downLoadEnrollmentFrom(folderPath,filename,candidateName,candidateId);
			*/
			candidate = candidateService.findCandidateById(candidateId);
			candidate.setUploadEnrollmentFormName(candidateName+"_"+candidateId);
			//candidate.setUploadedEnrollmentPath(uploadedEnrollmentFormPath+candidateName+"_"+candidateId+extension);
			
			candidateService.updateCandidate(candidate);
			httpStatus = HttpStatus.OK;
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Candidate>(candidate,httpStatus);
	}
	
	@RequestMapping(value = "/download/file/{fileName}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public ResponseEntity<InputStreamResource> downloadFilebyID(@PathVariable("fileName")String fileName) throws IOException {
		 
		 InputStreamResource in = null;
		 in = fileUploadService.downLoadEnrollmentFrom(fileName);
		 System.out.println("Response "+in);
		 return ResponseEntity.ok().body(in);
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