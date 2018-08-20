package com.ayantsoft.trms.service;

import java.io.Serializable;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8136281986562506143L;
	
	@Autowired
	private Message message;
	
	public void sendMail(final String fromEmail,final String toEmail,final String textMessage,final String subject){
		System.out.println(fromEmail+"   "+toEmail+"   "+textMessage+"   "+subject);
		
		new Thread( new Runnable() {
			
		    @Override
		    public void run() {
		    	try{
		    		message.setFrom(new InternetAddress(fromEmail));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
					message.setSubject(subject);
					message.setText(textMessage);
					
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setContent(textMessage,"text/html");
					
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);
					
					message.setContent(multipart);
					Transport.send(message);
		    	}catch(Exception e){
		    		e.printStackTrace();
		    		try {
						throw new Exception();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		    	}
		    }
		}).start();
	}

}
