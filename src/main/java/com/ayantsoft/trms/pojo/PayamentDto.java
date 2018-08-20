package com.ayantsoft.trms.pojo;

import java.util.List;

public class PayamentDto {
	
	private List<PaymentDetails> paymentList;
	private double totalAmountInDoller;
	private double totalIncentiveInInr;
	
	public List<PaymentDetails> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PaymentDetails> paymentList) {
		this.paymentList = paymentList;
	}
	public double getTotalAmountInDoller() {
		return totalAmountInDoller;
	}
	public void setTotalAmountInDoller(double totalAmountInDoller) {
		this.totalAmountInDoller = totalAmountInDoller;
	}
	public double getTotalIncentiveInInr() {
		return totalIncentiveInInr;
	}
	public void setTotalIncentiveInInr(double totalIncentiveInInr) {
		this.totalIncentiveInInr = totalIncentiveInInr;
	}
}
