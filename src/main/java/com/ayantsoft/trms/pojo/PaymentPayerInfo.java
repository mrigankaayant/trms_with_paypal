package com.ayantsoft.trms.pojo;

public class PaymentPayerInfo {
	
	private String email;
    private String firstName;
    private String lastName;
    private String payerId;
    private String countryCode;
    private PaymentInfoShippingAddress shippingAddress;
    
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public PaymentInfoShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(PaymentInfoShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
