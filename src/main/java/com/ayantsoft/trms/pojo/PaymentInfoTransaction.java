package com.ayantsoft.trms.pojo;



public class PaymentInfoTransaction {
	
	private PaymentInfoRelatedReources relatedResources; 
    private PaymentInfoAmount amount;
    private PaymentInfoPayee payee;
    private PaymentInfoItemList itemList;
    
    
	
	public PaymentInfoRelatedReources getRelatedResources() {
		return relatedResources;
	}
	public void setRelatedResources(PaymentInfoRelatedReources relatedResources) {
		this.relatedResources = relatedResources;
	}
	public PaymentInfoAmount getAmount() {
		return amount;
	}
	public void setAmount(PaymentInfoAmount amount) {
		this.amount = amount;
	}
	public PaymentInfoPayee getPayee() {
		return payee;
	}
	public void setPayee(PaymentInfoPayee payee) {
		this.payee = payee;
	}
	public PaymentInfoItemList getItemList() {
		return itemList;
	}
	public void setItemList(PaymentInfoItemList itemList) {
		this.itemList = itemList;
	} 
}
