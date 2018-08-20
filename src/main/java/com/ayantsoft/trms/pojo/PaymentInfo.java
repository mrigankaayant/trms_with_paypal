package com.ayantsoft.trms.pojo;

import java.util.Date;
import java.util.List;

public class PaymentInfo {
	
	private String payemntId;
	private String intent;
	private PaymentInfoPayer payer;
	private String cart;
	private PaymentInfoTransaction transactions;
	
	
	public String getPayemntId() {
		return payemntId;
	}
	public void setPayemntId(String payemntId) {
		this.payemntId = payemntId;
	}
	private String state;
	private Date createTime;
	
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public PaymentInfoPayer getPayer() {
		return payer;
	}
	public void setPayer(PaymentInfoPayer payer) {
		this.payer = payer;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PaymentInfoTransaction getTransactions() {
		return transactions;
	}
	public void setTransactions(PaymentInfoTransaction transactions) {
		this.transactions = transactions;
	}
	
}




