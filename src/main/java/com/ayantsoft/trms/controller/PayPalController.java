package com.ayantsoft.trms.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayantsoft.trms.pojo.Candidate;
import com.ayantsoft.trms.pojo.CandidatePayment;
import com.ayantsoft.trms.pojo.CreatedBy;
import com.ayantsoft.trms.pojo.PaymentDetails;
import com.ayantsoft.trms.pojo.PaymentInfo;
import com.ayantsoft.trms.pojo.PaymentInfoAmount;
import com.ayantsoft.trms.pojo.PaymentInfoDetails;
import com.ayantsoft.trms.pojo.PaymentInfoItem;
import com.ayantsoft.trms.pojo.PaymentInfoItemList;
import com.ayantsoft.trms.pojo.PaymentInfoPayee;
import com.ayantsoft.trms.pojo.PaymentInfoPayer;
import com.ayantsoft.trms.pojo.PaymentInfoRelatedReources;
import com.ayantsoft.trms.pojo.PaymentInfoSale;
import com.ayantsoft.trms.pojo.PaymentInfoShippingAddress;
import com.ayantsoft.trms.pojo.PaymentInfoTransaction;
import com.ayantsoft.trms.pojo.PaymentInfoTransactionFee;
import com.ayantsoft.trms.pojo.PaymentPayerInfo;
import com.ayantsoft.trms.pojo.UserMst;
import com.ayantsoft.trms.service.CandidateService;
import com.ayantsoft.trms.service.PaymentDetailsService;
import com.ayantsoft.trms.service.UserMstService;
import com.ayantsoft.trms.service.impl.PayPalClient;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;

@RestController
public class PayPalController {

	@Autowired
	private PayPalClient payPalClient;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@Autowired
	private UserMstService userMstService;
	
	@Autowired
	private CandidateService candidateService;

	@PostMapping(value = "/make/payment")
	public Map<String, Object> makePayment(@RequestBody CandidatePayment payment,HttpServletRequest request){
		System.out.println("========== MAKE PAYMENT =============");
		
		Principal principal = request.getUserPrincipal();
		UserMst userMst = userMstService.getUserMstByUsername(principal.getName());
		
		Map<String, Object> response = payPalClient.createPayment(payment,userMst.getUsername());

		if(response != null){
			String url = (String) response.get("redirect_url");
			if(url != null){
				String token = url.substring(url.indexOf("token") + 6 ,url.length());
				
				PaymentDetails paymentDetails = new PaymentDetails();
				paymentDetails.setCoureseName(payment.getCoureseName());
				paymentDetails.setAmount(payment.getAmount());
				paymentDetails.setDueAmount((payment.getQuantity()*payment.getPricePerItem()) - payment.getAmount());
				paymentDetails.setCandidateName(payment.getCandidateName());
				paymentDetails.setEmail(payment.getEmail());
				paymentDetails.setWorkMobile(payment.getWorkMobile());
				paymentDetails.setPricePerItem(payment.getPricePerItem());	
				paymentDetails.setQuantity(payment.getQuantity());
				paymentDetails.setCandidateId(payment.getCandidateId());
				paymentDetails.setReturnUrl(payment.getReturnUrl());
				paymentDetails.setUsername(userMst.getUsername());
				paymentDetails.setStatus("INCOMPLETE");  

				paymentDetails.setCreatedDate(new Date());

				LocalDate currentDate = LocalDate.now();
				Month month = currentDate.getMonth(); 
				Integer year = currentDate.getYear(); 
				
				paymentDetails.setCreatedMonth(month.name());
				paymentDetails.setCreatedYear(year.toString());
				
				paymentDetails.setToken(token);
				paymentDetailsService.save(paymentDetails);
			}
		}
		return response;
	}


	@GetMapping(value = "/complete/payment/{username}")
	public void completePayment(@PathVariable String username,HttpServletRequest request,HttpServletResponse response){
		System.out.println("=========== COMPLETE PAYMENT ==========");
		List<PaymentDetails> paymentDetailsList = paymentDetailsService.findPaymentDetailsByUsername(username);
		
		if(paymentDetailsList != null && paymentDetailsList.size() >0){
			PaymentDetails details = paymentDetailsList.get(0);
			PaymentInfo paymentInfo = null;
			PaymentInfoPayer paymentInfoPayer = null;
			PaymentPayerInfo paymentPayerInfo = null;
			PaymentInfoShippingAddress paymentInfoShippingAddress = null;
			PaymentInfoTransaction paymentInfoTransaction = null;
			PaymentInfoRelatedReources paymentInfoRelatedReources = null;
			PaymentInfoSale paymentInfoSale = null;
			PaymentInfoAmount paymentInfoAmount = null;
			PaymentInfoDetails paymentInfoDetails = null;
			PaymentInfoTransactionFee paymentInfoTransactionFee = null;
			PaymentInfoPayee paymentInfoPayee = null;
			PaymentInfoItemList paymentInfoItemList = null;
			PaymentInfoItem paymentInfoItem = null;
			if("INCOMPLETE".equals(details.getStatus())){
				Map<String, Object> res = payPalClient.completePayment(request);
				Payment payment = (Payment) res.get("payment");
				
				if(payment != null){
					paymentInfo = new PaymentInfo();
					paymentInfoPayer = new PaymentInfoPayer();
					paymentPayerInfo = new PaymentPayerInfo();
					paymentInfoShippingAddress = new PaymentInfoShippingAddress();
					paymentInfoTransaction = new PaymentInfoTransaction();
					//paymentInfoTransaction = new PaymentInfoTransaction();
					paymentInfoRelatedReources = new PaymentInfoRelatedReources();
					paymentInfoSale = new PaymentInfoSale();
					paymentInfoAmount = new PaymentInfoAmount();
					paymentInfoDetails = new PaymentInfoDetails();
					paymentInfoTransactionFee = new PaymentInfoTransactionFee();
					paymentInfoPayee = new PaymentInfoPayee();
					paymentInfoItemList = new PaymentInfoItemList();
					paymentInfoItem = new PaymentInfoItem();
					
					if(payment.getId() != null){
						paymentInfo.setPayemntId(payment.getId());
					}
					if(payment.getIntent() != null){
					paymentInfo.setIntent(payment.getIntent());
					}
					if(payment.getCart() != null){
					paymentInfo.setCart(payment.getCart());
					}
					if(payment.getPayer() != null){

						Payer payer =payment.getPayer();
			      	    if(payer.getPaymentMethod() != null){
							paymentInfoPayer.setPaymentMethod(payer.getPaymentMethod());
						}
						if(payer.getStatus() != null){
							paymentInfoPayer.setStatus(payer.getStatus());
						}
						if(payer.getPayerInfo() != null){
							PayerInfo payerInfo = payer.getPayerInfo();
							if(payerInfo.getFirstName() != null){
								paymentPayerInfo.setFirstName(payerInfo.getFirstName());
							}
							if(payerInfo.getLastName() != null){
								paymentPayerInfo.setLastName(payerInfo.getLastName());
							}
							if(payerInfo.getEmail() != null){
								paymentPayerInfo.setEmail(payerInfo.getEmail());
							}
							if(payerInfo.getPayerId() != null){
								paymentPayerInfo.setEmail(payerInfo.getPayerId());
							}
							if(payerInfo.getCountryCode() != null){
								paymentPayerInfo.setEmail(payerInfo.getCountryCode());
							}
							if(payerInfo.getShippingAddress() != null){
								ShippingAddress shippingAddress = payerInfo.getShippingAddress();
								if(shippingAddress.getRecipientName() != null){
									paymentInfoShippingAddress.setRecipientName(shippingAddress.getRecipientName());
								}
								if(shippingAddress.getLine1() != null){
									paymentInfoShippingAddress.setLine1(shippingAddress.getLine1());
								}
								if(shippingAddress.getLine2() != null){
									paymentInfoShippingAddress.setLine2(shippingAddress.getLine2());
								}
								if(shippingAddress.getCity() != null){
									paymentInfoShippingAddress.setCity(shippingAddress.getCity());
								}
								if(shippingAddress.getCountryCode() != null){
									paymentInfoShippingAddress.setCountryCode(shippingAddress.getCountryCode());
								}
								if(shippingAddress.getPostalCode() != null){
									paymentInfoShippingAddress.setPostalCode(shippingAddress.getPostalCode());
								}
								if(shippingAddress.getState() != null){
									paymentInfoShippingAddress.setState(shippingAddress.getState());
								}
								if(paymentInfoShippingAddress!=null){
									paymentPayerInfo.setShippingAddress(paymentInfoShippingAddress);
								}
							}
							if(paymentPayerInfo!=null){
								paymentInfoPayer.setPayerInfo(paymentPayerInfo);
							}
						}
						if(paymentInfoPayer!=null){
							paymentInfo.setPayer(paymentInfoPayer);
						}
					}
					if(payment.getTransactions() != null && payment.getTransactions().size()>0){
						List<Transaction> transactions = payment.getTransactions();
						for(Transaction t:transactions){
							if(t.getRelatedResources()!=null && t.getRelatedResources().size()>0){
							List<RelatedResources> relatedReources = t.getRelatedResources();
							for(RelatedResources r:relatedReources){
								if(r.getSale()!=null){
								Sale sale = r.getSale();
									if(sale.getPaymentMode()!=null){
									paymentInfoSale.setPaymentMode(sale.getPaymentMode());
									}
									if(sale.getState()!=null){
									paymentInfoSale.setState(sale.getState());
									}
									if(sale.getProtectionEligibility() !=null){
									paymentInfoSale.setProtectionEligibility(sale.getProtectionEligibility());
									}
									if(sale.getProtectionEligibilityType() !=null){
									paymentInfoSale.setProtectionEligibilityType(sale.getProtectionEligibilityType());
									}
									if(sale.getReceiptId() !=null){
										paymentInfoSale.setReceiptId(sale.getReceiptId());
									}
									if(sale.getParentPayment() !=null){
										paymentInfoSale.setParentPayment(sale.getParentPayment());
									}
									if(sale.getCreateTime() !=null){
										paymentInfoSale.setCreateTime(sale.getCreateTime());
									}
									if(sale.getUpdateTime() !=null){
										paymentInfoSale.setUpdateTime(sale.getUpdateTime());
									}
									if(sale.getAmount() !=null){
										Amount amount = sale.getAmount();
										if(amount.getCurrency() != null){
											paymentInfoAmount.setCurrency(amount.getCurrency());
										}
										if(amount.getTotal() != null){
											paymentInfoAmount.setTotal(amount.getTotal());
										}
										if(amount.getDetails() != null){
											Details amountDetails = amount.getDetails();
											if(amountDetails.getSubtotal() != null){
												paymentInfoDetails.setSubtotal(amountDetails.getSubtotal());
											}
											
										}
										if(paymentInfoDetails!=null){
											paymentInfoAmount.setDetails(paymentInfoDetails);
										}
										
										if(paymentInfoAmount!=null){
											paymentInfoSale.setAmount(paymentInfoAmount);
										}
									}
									if(sale.getTransactionFee() !=null){
										Currency transactionFee = sale.getTransactionFee();
										if(transactionFee.getCurrency() != null){
											paymentInfoTransactionFee.setCurrency(transactionFee.getCurrency());
										}
										if(transactionFee.getValue() != null){
											paymentInfoTransactionFee.setValue(transactionFee.getValue());
										}
										if(paymentInfoTransactionFee != null){
											paymentInfoSale.setTransactionFee(paymentInfoTransactionFee);
										}
									}
								}
								if(paymentInfoSale != null){
								paymentInfoRelatedReources.setSale(paymentInfoSale);
								}
							}
							if(t.getAmount() != null){
							Amount amount = t.getAmount();
	
								if(amount.getCurrency() != null){
									paymentInfoAmount.setCurrency(amount.getCurrency());
								}
								if(amount.getTotal() != null){
									paymentInfoAmount.setTotal(amount.getTotal());
								}
								if(amount.getDetails() != null){
									Details amountDetails = amount.getDetails();
									if(amountDetails.getSubtotal() != null){
										paymentInfoDetails.setSubtotal(amountDetails.getSubtotal());
									}
									
								}
								if(paymentInfoDetails!=null){
									paymentInfoAmount.setDetails(paymentInfoDetails);
								}
								
								if(paymentInfoAmount!=null){
									paymentInfoTransaction.setAmount(paymentInfoAmount);
								}
							}
							if(t.getPayee() != null){
								Payee payee = t.getPayee();
								if(payee.getEmail()!=null){
									paymentInfoPayee.setEmail(payee.getEmail());
								}
								if(payee.getMerchantId() != null){
									paymentInfoPayee.setMerchantId(payee.getMerchantId());
								}
								if(paymentInfoPayee!=null){
									paymentInfoTransaction.setPayee(paymentInfoPayee);
								}
								
							}
							if(t.getItemList() != null){
								ItemList itemList = t.getItemList();
								List<Item> items = itemList.getItems();
								for(Item i:items){
									if(i.getCurrency() != null){
									paymentInfoItem.setCurrency(i.getCurrency());
									}
									if(i.getName() != null){
									paymentInfoItem.setName(i.getName());
									}
									if(i.getPrice() != null){
										paymentInfoItem.setPrice(i.getPrice());
									}
									if(i.getQuantity() != null){
										paymentInfoItem.setQuantity(i.getQuantity());
									}
								}
								if(paymentInfoItem != null){
									paymentInfoItemList.setItem(paymentInfoItem);
								}
								if(itemList.getShippingAddress() != null){
									ShippingAddress shippingAddress = itemList.getShippingAddress();
									if(shippingAddress.getRecipientName() != null){
										paymentInfoShippingAddress.setRecipientName(shippingAddress.getRecipientName());
									}
									if(shippingAddress.getLine1() != null){
										paymentInfoShippingAddress.setLine1(shippingAddress.getLine1());
									}
									if(shippingAddress.getLine2() != null){
										paymentInfoShippingAddress.setLine2(shippingAddress.getLine2());
									}
									if(shippingAddress.getCity() != null){
										paymentInfoShippingAddress.setCity(shippingAddress.getCity());
									}
									if(shippingAddress.getCountryCode() != null){
										paymentInfoShippingAddress.setCountryCode(shippingAddress.getCountryCode());
									}
									if(shippingAddress.getPostalCode() != null){
										paymentInfoShippingAddress.setPostalCode(shippingAddress.getPostalCode());
									}
									if(shippingAddress.getState() != null){
										paymentInfoShippingAddress.setState(shippingAddress.getState());
									}
									if(paymentInfoShippingAddress!=null){
										paymentInfoItemList.setShippingAddress(paymentInfoShippingAddress);
									}
								
								}
								if(paymentInfoItemList != null){
								paymentInfoTransaction.setItemList(paymentInfoItemList);
								}
							}
							
						}
					  }
					 paymentInfo.setTransactions(paymentInfoTransaction);
					}
					//paymentInfo.setPayer();
				}
				
			    details.setPaymentInfo(paymentInfo);
				details.setStatus("COMPLETED");
				
				Candidate candidate = candidateService.findCandidateById(details.getCandidateId());
				
				CreatedBy createdBy = candidate.getCreatedBy();
				if(createdBy != null){
					details.setCreatedBy(createdBy);
				}
				
			    paymentDetailsService.updatePayment(details);
			    
			    List<PaymentDetails> paymentList = paymentDetailsService.findPaymentDetailsByUsername(username);
				if(paymentList != null && paymentList.size() >0){
					PaymentDetails lastPayment = paymentDetailsList.get(0);
					Integer totalAmount = paymentDetailsService.findTotalAmount(details.getUsername(),details.getCoureseName());
					lastPayment.setDueAmount(details.getPricePerItem()-totalAmount);
					paymentDetailsService.updatePayment(lastPayment);
				}
			    
			    Long numberOfPayment = paymentDetailsService.numberOfPayment(details.getUsername());
			    if(numberOfPayment != null){
					candidate.setPaymentCounter(numberOfPayment.toString());
					candidateService.updateCandidate(candidate);
			    }
			
				try{
					response.sendRedirect(details.getReturnUrl());
				} catch (IOException e) {
					e.printStackTrace();
					// here will be error page
				}
			}else{
               // here will be error page 
			}
		}else{
			try {
				response.sendRedirect("http://192.168.0.85:3000"); // CLIENT SYSTEM IP
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@GetMapping(value = "/cancel/payment")
	public void cancelPayment(HttpServletResponse response){
		System.out.println("=========== CANCEL PAYMENT ==========");
		try {
			response.sendRedirect("http://192.168.0.85:3000/candidate/cancelpayment");    // CLIENT SYSTEM IP
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
