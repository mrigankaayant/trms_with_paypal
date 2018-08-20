package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.PaymentDetailsDao;
import com.ayantsoft.trms.pojo.PaymentDetails;
import com.ayantsoft.trms.service.PaymentDetailsService;

@Service
public class PaymentDetailsServiceImpl implements Serializable,PaymentDetailsService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7912159238907104948L;
	
	@Autowired
	private PaymentDetailsDao paymentDetailsDao;

	@Override
	public void save(PaymentDetails paymentDetails) {
		paymentDetailsDao.save(paymentDetails); 
	}

	@Override
	public List<PaymentDetails> findPaymentDetailsByUsername(String userName) {
		return paymentDetailsDao.findPaymentDetailsByUsername(userName);
	}
	
	@Override
	public void updatePayment(PaymentDetails paymentDetails) {
		paymentDetailsDao.updatePayment(paymentDetails);
	}

	@Override
	public List<PaymentDetails> completedPaymentList(String userName) {
		return paymentDetailsDao.completedPaymentList(userName);
	}

	@Override
	public long numberOfPayment(String userName) {
		return paymentDetailsDao.numberOfPayment(userName);
	}

	@Override
	public Integer findTotalAmount(String userName,String courseName) {
		return paymentDetailsDao.findTotalAmount(userName,courseName);
	}

	@Override
	public List<PaymentDetails> findPaymentDetailsByCandidateId(String candidateId) {
		return paymentDetailsDao.findPaymentDetailsByCandidateId(candidateId);
	}

	@Override
	public List<PaymentDetails> findOwnCandidatePayment(String employeeId, String month, String year) {
		return paymentDetailsDao.findOwnCandidatePayment(employeeId,month,year);
	}

	@Override
	public List<PaymentDetails> findTeamCandidatePayment(String employeeId, String month, String year) {
		return paymentDetailsDao.findTeamCandidatePayment(employeeId,month,year);
	}

	@Override
	public List<PaymentDetails> findAllPaidCandidates() {
		return paymentDetailsDao.findAllPaidCandidates();
	}

}
