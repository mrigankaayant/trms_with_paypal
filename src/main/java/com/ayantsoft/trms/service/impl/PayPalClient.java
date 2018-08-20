package com.ayantsoft.trms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.ayantsoft.trms.pojo.AuthTokenInfo;
import com.ayantsoft.trms.pojo.CandidatePayment;
import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpEntity;

@Service
public class PayPalClient {

	private String clientId = "AVdQORIEP6W1kTNIkst42o_gt6BpESyQgNj3QobBKWJxUDc9t2DqFLZEcSOh2g6vYte_8bE8KlLBvDC5";
	private String clientSecret = "ENniHM2uDw-TbvOegrPNdZ2-Yhdxz-xAfD1PuTc5376mhP8iCAfLy2jy-MxguXpMTXFQrR0plmG60HsW";

	public Map<String, Object> createPayment(CandidatePayment candidatePayment,String username){

		    Map<String, Object> response = new HashMap<String, Object>();
		    
		    Details details = new Details();
			//details.setShipping("1");
		    Integer totalAmount = candidatePayment.getPricePerItem()*candidatePayment.getQuantity();
			//details.setSubtotal(totalAmount.toString()); // edit
		    details.setSubtotal(candidatePayment.getAmount().toString());
			//details.setTax("1");
		    
		    Amount amount = new Amount();
		    amount.setCurrency("USD");
		    //amount.setTotal(totalAmount.toString()); // edit
		    amount.setTotal(candidatePayment.getAmount().toString());
		    
		    amount.setDetails(details);
		    
		    Item item = new Item();
 			//item.setName(candidatePayment.getCoureseName()).setQuantity(candidatePayment.getQuantity().toString()).setCurrency("USD").setPrice(candidatePayment.getPricePerItem().toString());
		    item.setName(candidatePayment.getCoureseName()).setQuantity(candidatePayment.getQuantity().toString()).setCurrency("USD").setPrice(candidatePayment.getAmount().toString());
		    ItemList itemList = new ItemList();
 			List<Item> items = new ArrayList<Item>();
 			items.add(item);
 			itemList.setItems(items);
		    
		    
		   
		    Transaction transaction = new Transaction();
		    transaction.setAmount(amount);
		    
		    transaction.setItemList(itemList);
		    
		    
		   
		    List<Transaction> transactions = new ArrayList<Transaction>();
		    transactions.add(transaction);

		    Payer payer = new Payer();
		    payer.setPaymentMethod("paypal");
		    

		    Payment payment = new Payment();
		    payment.setIntent("sale");
		    payment.setPayer(payer);
		    payment.setTransactions(transactions);
		    

		    RedirectUrls redirectUrls = new RedirectUrls();
		    
		    redirectUrls.setCancelUrl("http://192.168.0.85:8080/trmsSOA/cancel/payment");  // SOA SYSTEM IP
		    
		   // redirectUrls.setReturnUrl("http://localhost:8080/trmsSOA/complete/payment");
		    
		   redirectUrls.setReturnUrl("http://192.168.0.85:8080/trmsSOA/complete/payment/"+username);   // SOA SYSTEM IP
		   
		   // System.out.println("FIRST REDIRECT URL :   "+redirectUrls);

		    payment.setRedirectUrls(redirectUrls);

		    Payment createdPayment;
		    try {
		        String redirectUrl = "";
		        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		        createdPayment = payment.create(context);
		        System.out.println("CHECEKING createdPayment NULL OR GET URL :   "+createdPayment +"      "+createdPayment.getRedirectUrls());
		        if(createdPayment!=null){
		            List<Links> links = createdPayment.getLinks();
		            System.out.println(links);
		            for (Links link:links) {
		                if(link.getRel().equals("approval_url")){
		                    redirectUrl = link.getHref();
		                    System.out.println("WWWWWWWWWWWWW   "+redirectUrl);
		                    break;
		                }
		            }
		            response.put("status", "success");
		            response.put("redirect_url", redirectUrl);
		        }
		    } catch (PayPalRESTException e) {
		        System.out.println("Error happened during payment creation!");
		    }
		    return response;
	}



	public Map<String, Object> completePayment(HttpServletRequest req){
		
		System.out.println("------------AFTER PAYMENT SUCCESS.............");
		
		Map<String, Object> response = new HashMap();
		Payment payment = new Payment();
		payment.setId(req.getParameter("paymentId"));
		System.out.println("Payment ID"+payment.getId());
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(req.getParameter("PayerID"));
		try {
			System.out.println("Enter Try Block In Payment");
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			Payment createdPayment = payment.execute(context, paymentExecution);
			
		    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		    System.out.println(createdPayment);
		    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			
			if(createdPayment!=null){
				response.put("status", "success");
				response.put("payment", createdPayment);
			}
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return response;
	}

}
