package com.ayantsoft.trms.service;

import java.util.List;
import com.ayantsoft.trms.pojo.PaymentDetails;

public interface PaymentDetailsService {
	public void save(PaymentDetails paymentDetails);
	public List<PaymentDetails> findPaymentDetailsByUsername(String token);
	public void updatePayment(PaymentDetails paymentDetails);
	public List<PaymentDetails> completedPaymentList(String userName);
	public long numberOfPayment(String userName);
	public Integer findTotalAmount(String userName,String courseName);
	public List<PaymentDetails> findPaymentDetailsByCandidateId(String candidateId);
	public List<PaymentDetails> findOwnCandidatePayment(String employeeId,String month,String year);
	public List<PaymentDetails> findTeamCandidatePayment(String employeeId,String month,String year);
	public List<PaymentDetails> findAllPaidCandidates();
	
	
	//List<PaymentDetails> findPaymentDetailsByCandidateId(String candidateId);
}
