package com.ayantsoft.trms.pojo;


public class PaymentInfoItemList {
	
	private PaymentInfoItem item;
	private PaymentInfoShippingAddress shippingAddress;
	
	
	public PaymentInfoShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(PaymentInfoShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public PaymentInfoItem getItem() {
		return item;
	}
	public void setItem(PaymentInfoItem item) {
		this.item = item;
	}
	
	
}
