package com.ayantsoft.trms.doc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.ayantsoft.trms.pojo.EnrollmentFormDto;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class EnrollmentFormGenerateService {
	
	public static InputStream createenrollmentFormPdf(EnrollmentFormDto enrollmentFormDto)
	{
		InputStream inputStream = null;
		try
		{
			Document doc = new Document(PageSize.A4);
			File f1=new File(enrollmentFormDto.getFolderPathWithFile());
			OutputStream file = new FileOutputStream(f1);
			
			PdfWriter writer = PdfWriter.getInstance(doc, file);

			HeaderFooter header = new HeaderFooter(new Phrase("GLOBAL IT EXPERTS INC TRAINING ENROLLMENT FORM" ,FontFactory.getFont(FontFactory.HELVETICA,10 )), false);  

			HeaderFooter footer = new HeaderFooter(new Phrase("Global IT Experts Inc.-All Rights Reserved "),false );  
			header.setAlignment(1);
			footer.setAlignment(1);

			doc.setHeader(header); 
			doc.setFooter(footer); 
			Font fontHead = FontFactory.getFont("Times-Roman", 12, Font.BOLD|Font.UNDERLINE);    
			Font fontcontent = FontFactory.getFont("Times-Roman", 10);    

			doc.open();

			Paragraph para1 = new Paragraph("Reference # GITES"+enrollmentFormDto.getYear()+"/T"+enrollmentFormDto.getEnrollmentFormNo(),fontcontent);
			para1.setAlignment(1);
			para1.setSpacingBefore(1);
			para1.setSpacingAfter(6);
			doc.add(para1);
			PdfPTable section = new PdfPTable(1);
			PdfPCell sectionCell = new PdfPCell(new Phrase("SECTION -I(For Candidate's Use Only)"));
			section.addCell(sectionCell);
			doc.add(section);
			Paragraph sp = new Paragraph("    ");
			doc.add(sp);
			PdfPTable canInfo = new PdfPTable(1);
			PdfPCell cell1= new PdfPCell(new Phrase("CANDIDATE'S INFORMATION :"));
			canInfo.addCell(cell1);


			doc.add(canInfo);
			PdfPTable candiInfo = new PdfPTable(4);
			PdfPCell  c5=new PdfPCell(new Phrase("Student Full Name :",fontcontent));
			c5.setColspan(4);
			PdfPCell  Address=new PdfPCell(new Phrase("Address :",fontcontent));
			PdfPCell  city=new PdfPCell(new Phrase("City :",fontcontent));
			PdfPCell  state=new PdfPCell(new Phrase("State :",fontcontent));
			PdfPCell  Zip=new PdfPCell(new Phrase("Zip :",fontcontent));
			PdfPCell  country=new PdfPCell(new Phrase("Country :",fontcontent));
			PdfPCell  mobile=new PdfPCell(new Phrase("Mobile :",fontcontent));
			PdfPCell  landPhone=new PdfPCell(new Phrase("Phone :",fontcontent));
			PdfPCell  email=new PdfPCell(new Phrase("Email :",fontcontent));

			Address.setColspan(4);
			email.setColspan(2);
			candiInfo.addCell(c5);
			candiInfo.addCell(Address);
			candiInfo.addCell(city);
			candiInfo.addCell(state);
			candiInfo.addCell(Zip);
			candiInfo.addCell(country);
			candiInfo.addCell(mobile);
			candiInfo.addCell(landPhone);
			candiInfo.addCell(email);
			doc.add(candiInfo);

			PdfPTable trainingInfo = new PdfPTable(2);

			PdfPCell  heading=new PdfPCell(new Phrase("TRAINING & COURSE DETAILS :"));
			heading.setColspan(2);
			PdfPCell  course=new PdfPCell(new Phrase("Course Name :"+enrollmentFormDto.getCourseName(),fontcontent));
			PdfPCell  doe=new PdfPCell(new Phrase("Date of Enrollment :"+enrollmentFormDto.getDateOfEnrollment(),fontcontent));
			trainingInfo.addCell(heading);
			trainingInfo.addCell(course);
			trainingInfo.addCell(doe);
			if("Training & Markering".equals(enrollmentFormDto.getService())){
				PdfPCell  stDate=new PdfPCell(new Phrase("Tentative Training Start Date :"+enrollmentFormDto.getTentiveTrainingStartDate(),fontcontent));
				PdfPCell  tth=new PdfPCell(new Phrase("Total Training Hours/Week :5-6 Weeks",fontcontent));
				trainingInfo.addCell(stDate);
				trainingInfo.addCell(tth);
			}
			
			doc.add(trainingInfo);

			PdfPTable terms = new PdfPTable(1);
			PdfPCell termsCell = new PdfPCell(new Phrase("GENERAL TERMS OF SERVICE :"));
			//  termsCell.setBorder(PdfCell.NO_BORDER);
			terms.addCell(termsCell);
			terms.setSpacingAfter(4);
			doc.add(terms);

			PdfPTable contentHead = new PdfPTable(1);
			PdfPCell contentCell = new PdfPCell(new Phrase("EXHIBIT A",fontHead));
			contentCell.setBorder(PdfCell.NO_BORDER);
			contentHead.addCell(contentCell);
			doc.add(contentHead);

			PdfPTable content = new PdfPTable(1);
			PdfPCell contentCellA = new PdfPCell(new Phrase("The purpose of this form is to ensure"
					+ " that the element and commitments are in place to provide the online training delivered to the candidate by Global IT Experts Inc",fontcontent));
			contentCellA.setBorder(PdfCell.NO_BORDER);
			content.addCell(contentCellA);


			PdfPCell contentCellB = new PdfPCell(new Phrase("EXHIBIT B",fontHead));
			contentCellB.setBorder(PdfCell.NO_BORDER);
			content.addCell(contentCellB);
			PdfPCell contentCellB1 = new PdfPCell(new Phrase("Service Scope"));
			contentCellB1.setBorder(PdfCell.NO_BORDER);
			content.addCell(contentCellB1);

			PdfPCell contentCellB2 = new PdfPCell(new Phrase("1. Online training through web based software Cisco WebEx."
					+ "2. Dedicated Industry oriented trainers to provide online training in respective skill set",fontcontent));
			contentCellB2.setBorder(PdfCell.NO_BORDER);
			content.addCell(contentCellB2);
			PdfPCell contentCellB3 = new PdfPCell(new Phrase("2. Dedicated Industry oriented trainers to provide online training in respective skill set",fontcontent));
			contentCellB3.setBorder(PdfCell.NO_BORDER);
			content.addCell(contentCellB3);

			PdfPCell exC = new PdfPCell(new Phrase("EXHIBIT C",fontHead));
			exC.setBorder(PdfCell.NO_BORDER);
			content.addCell(exC);

			PdfPCell exCContent = new PdfPCell(new Phrase("Service Availability :"));
			exCContent.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent);

			PdfPCell exCContent1 = new PdfPCell(new Phrase("Service coverage parameters specific to the services covered in this agreement are as follows :",fontcontent));
			exCContent1.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent1);

			PdfPCell exCContent2 = new PdfPCell(new Phrase("1. Telephone Support: 10.30 AM to 6.30 PM from Monday to Friday(Central Standard Time)",fontcontent));
			exCContent2.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent2);

			PdfPCell exCContent3 = new PdfPCell(new Phrase("2. Online Chat Support: 10.30 AM to 6.30 PM from Monday to Friday(Central Standard Time) ",fontcontent));
			exCContent3.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent3);

			PdfPCell exCContent4 = new PdfPCell(new Phrase("3. Voicemail Support :Calls received after the office hours will be forwarded to voicemail and voicemails will be followed up the next working day ",fontcontent));
			exCContent4.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent4);

			PdfPCell exCContent5 = new PdfPCell(new Phrase("4. Email Support:24 x 7 email support",fontcontent));
			exCContent5.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent5);

			PdfPCell exCContent6 = new PdfPCell(new Phrase("Note:Best way to reach is via email if not reachable through phone or online chat support.",fontcontent));
			exCContent6.setBorder(PdfCell.NO_BORDER);
			content.addCell(exCContent6);


			PdfPCell exD = new PdfPCell(new Phrase("EXHIBIT D",fontHead));
			exD.setBorder(PdfCell.NO_BORDER);
			content.addCell(exD);

			PdfPCell exDcontent1 = new PdfPCell(new Phrase("1) It is the candidate's responsibility to acquire all"
					+ " necessary software if required to maintain connectivity and to combat the menace of Internet hacking, such as but not limited to anti-virus, firewalls, vpn etc",fontcontent));
			exDcontent1.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent1);
			PdfPCell exDcontent2 = new PdfPCell(new Phrase("2) The company is not responsible anyway for the software you are using for the training purpose and also the company is not responsible for the info "
					+ "you are providing for your background verification check.",fontcontent));
			exDcontent2.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent2);

			PdfPCell exDcontent3 = new PdfPCell(new Phrase("3) You will not use the information obtained as a result of this training in any way against "
					+ "Global IT Experts Inc. or its employees.",fontcontent));
			exDcontent3.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent3);

			PdfPCell exDcontent4 = new PdfPCell(new Phrase("4) In case the training has to be stopped or cancelled "
					+ "because of 'Acts- of-God' such as but not limited to sickness, earthquake, storm or any "
					+ "other natural disaster or reasons, Global IT Experts Inc. "
					+ "will refund to candidates prorated training fees",fontcontent));
			exDcontent4.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent4);

			PdfPCell exDcontent5 = new PdfPCell(new Phrase("5) If Global IT Experts Inc. cannot start the training after 30 days of the tentative start date of the training, Global IT Experts Inc. "
					+ "will refund the full money collected as training fees "
					+ "within 7 business days.",fontcontent));
			exDcontent5.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent5);


			PdfPCell exDcontent6 = new PdfPCell(new Phrase("6) Global IT Experts Inc. will only entertain any claims for refunds if and only if for a valid reason with supporting documents you cannot take the training "
					+ "or quit the training program and accordingly if you inform Global IT Experts Inc. within 3 calendar days of enrollment. Global IT Experts Inc. will refund you 70% of your training fees/ Security deposit which you have paid and if after 3 days of enrollment/ Training start date, you want to quit or discontinue with the training program for any reason whatsoever Global IT Experts Inc. will not refund you any money "
					+ "which you have paid as the training fees/ Security Deposit.",fontcontent));
			exDcontent6.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent6);


			PdfPCell exDcontent7 = new PdfPCell(new Phrase("7) Global IT Experts Inc. reserves 48 hours every month for servicing and maintenance. These 48 hours can be together spread over the 30-day cycle. If for any reason the outage of service lasts more than the scheduled hours, Global IT Experts Inc."
					+ " will compensate lost time with equal time.",fontcontent));
			exDcontent7.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent7);

			PdfPCell exDcontent8 = new PdfPCell(new Phrase("8) Global IT Experts Inc. reserves the right to pull any candidate out of the training with assigning a valid reason as per its own discretion. In such an incident Global IT Experts Inc."
					+ " will prorate the number of hours attended by the candidate and refunds the rest of the money on a prorate basis.",fontcontent));
			exDcontent8.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent8);

			PdfPCell exDcontent9 = new PdfPCell(new Phrase("9) Please understand that all communications should be via E-Mail and no E-Mails received from any other E-Mail addresses other than "
					+ "the one/ones registered while subscribing will be entertained",fontcontent));
			exDcontent9.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent9);

			PdfPCell exDcontent10 = new PdfPCell(new Phrase("10) Global IT Experts Inc. will entertain any issues or "
					+ "queries only from the registered candidate only. Issues of registered candidate's spouse, husband, wife, son, daughter, friends, fiance or colleagues will not be entertained at all.",fontcontent));
			exDcontent10.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent10);

			PdfPCell exDcontent11 = new PdfPCell(new Phrase("11) Re-Scheduling of classes will be at the sole discretion of Global IT Experts Inc. management depending on trainer's availability.",fontcontent));
			exDcontent11.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent11);

			PdfPCell exDcontent12 = new PdfPCell(new Phrase("12) If the candidate is absconding without any notice, Global IT Experts Inc. will not refund "
					+ "you any money which you have paid as the training fees.",fontcontent));
			exDcontent12.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent12);

			PdfPCell exDcontent13 = new PdfPCell(new Phrase("13) After finishing the training Global IT Experts Inc. "
					+ "agrees to assist you for Job placement as per your location preferences.",fontcontent));
			exDcontent13.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent13);

			PdfPCell exDcontent14 = new PdfPCell(new Phrase("14) All the payment will be process through Global IT Experts's "
					+ "partner Ayant Software Pvt. Ltd.",fontcontent));
			exDcontent14.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent14);

			PdfPCell exDcontent15 = new PdfPCell(new Phrase("15) The training Fee/Security deposit is only refundable in your first paycheck once "
					+ "you start working in our client's project.",fontcontent));
			exDcontent15.setBorder(PdfCell.NO_BORDER);
			content.addCell(exDcontent15);

			doc.add(content);

			Paragraph paraGap = new Paragraph(" ");
			para1.setSpacingAfter(6);
			doc.add(paraGap);

			PdfPTable canAccept = new PdfPTable(1);
			PdfPCell canAcceptCell = new PdfPCell(new Phrase("Acceptance by the Candidate:"));
			canAccept.addCell(canAcceptCell);

			PdfPCell canAcceptCellPara = new PdfPCell(new Phrase("I understand that all payments are final and "
					+ "I agree to the above 'General Terms of Service' and "
					+ "acknowledge the receipt of the service. I agree that "
					+ "I am making the total payment of $"+enrollmentFormDto.getTotalAmount()+" through Credit/Debit Card/eCommerce/Internet Banking/PayPal/EFT/eCheck/ as "+enrollmentFormDto.getCourseName()+
					"Training fees / Security Deposit only and this payment should not be mistaken as guaranteed job placement offers.",fontcontent));
			canAccept.addCell(canAcceptCellPara);

			PdfPCell canAcceptCellPara1 = new PdfPCell(new Phrase("Candidate's Signature: _____________________ Date: _______________",fontcontent));
			canAcceptCellPara1.setMinimumHeight(30f);
			canAccept.addCell(canAcceptCellPara1);
			doc.add(canAccept);
			Paragraph paraGap1 = new Paragraph(" ");
			para1.setSpacingAfter(6);
			doc.add(paraGap1);

			PdfPTable section2 = new PdfPTable(1);
			PdfPCell section2Cell = new PdfPCell(new Phrase("SECTION -II(For Payee's Use Only)"));
			section2.addCell(section2Cell);

			doc.add(section2);
			Paragraph paraGap2 = new Paragraph(" ");
			para1.setSpacingAfter(6);
			doc.add(paraGap2);

			PdfPTable payeeInfo = new PdfPTable(4);
			PdfPCell  payeeInfoCell1=new PdfPCell(new Phrase("PAYEE'S INFORMATION :"));
			payeeInfoCell1.setColspan(4);
			PdfPCell  pName=new PdfPCell(new Phrase("Payee Full Name:",fontcontent));

			PdfPCell  pAddress=new PdfPCell(new Phrase("Address :",fontcontent));
			PdfPCell  pCity=new PdfPCell(new Phrase("City :",fontcontent));
			PdfPCell  pState=new PdfPCell(new Phrase("State :",fontcontent));
			PdfPCell  pZip=new PdfPCell(new Phrase("Zip :",fontcontent));
			PdfPCell  pCountry=new PdfPCell(new Phrase("Country :",fontcontent));
			PdfPCell  pMobile=new PdfPCell(new Phrase("Mobile :",fontcontent));
			PdfPCell  pLandPhone=new PdfPCell(new Phrase("Phone :",fontcontent));
			PdfPCell  pEmail=new PdfPCell(new Phrase("Email :",fontcontent));

			pName.setColspan(4);
			pAddress.setColspan(4);
			pEmail.setColspan(2);

			payeeInfo.addCell(payeeInfoCell1);
			payeeInfo.addCell(pName);
			payeeInfo.addCell(pAddress);
			payeeInfo.addCell(pCity);
			payeeInfo.addCell(pState);
			payeeInfo.addCell(pZip);
			payeeInfo.addCell(pCountry);
			payeeInfo.addCell(pMobile);
			payeeInfo.addCell(pLandPhone);
			payeeInfo.addCell(pEmail);
			doc.add(payeeInfo);

			doc.newPage(); 

			Paragraph para2 = new Paragraph("Reference # GITES"+enrollmentFormDto.getYear()+"/T"+enrollmentFormDto.getEnrollmentFormNo(),fontcontent);
			para2.setAlignment(1);
			para2.setSpacingBefore(1);
			para2.setSpacingAfter(6);
			doc.add(para2);


			PdfPTable accPaye = new PdfPTable(1);
			PdfPCell accPayeCell = new PdfPCell(new Phrase("Acceptance by the Payee:"));
			accPayeCell.setBorder(PdfCell.NO_BORDER);
			accPaye.addCell(accPayeCell);

			PdfPCell accPayeCell1 = new PdfPCell(new Phrase("I understand that all payments are final and I agree to the above 'General Terms of Service' "
					+ "and acknowledge the receipt of the service",fontcontent));
			accPayeCell1.setBorder(PdfCell.NO_BORDER);
			accPaye.addCell(accPayeCell1);

			PdfPCell accPayeCell2 = new PdfPCell(new Phrase("I __________________________________ hereby, "
					+ "declare that the following payment has been made by me on"
					+ " behalf of ___________________________________________ (Candidate's Name/Self) towards the training program/ Direct Marketing "
					+ "via ___________________ (Payment Mode) on ",fontcontent));
			accPayeCell2.setBorder(PdfCell.NO_BORDER);
			accPaye.addCell(accPayeCell2);

			doc.add(accPaye);
			Paragraph paraGap3 = new Paragraph(" ");
			paraGap3.setSpacingAfter(6);
			doc.add(paraGap3);

			PdfPTable payeSec = new PdfPTable(1);
			PdfPCell payeSecCell1 = new PdfPCell(new Phrase("Payment Date : "+enrollmentFormDto.getPaymentDate()));
			payeSecCell1.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell1);

			PdfPCell payeSecCell2 = new PdfPCell(new Phrase("Amount Payable : $"+enrollmentFormDto.getTotalAmount()));
			payeSecCell2.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell2);

			PdfPCell payeSecCell3 = new PdfPCell(new Phrase("Amount Paid : $"+enrollmentFormDto.getAmountPaid()));
			payeSecCell3.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell3);

			PdfPCell payeSecCell4 = new PdfPCell(new Phrase("Transaction ID : "+enrollmentFormDto.getTransactionId()));
			payeSecCell4.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell4);

			PdfPCell payeSecCell5 = null;
			if(enrollmentFormDto.getDueAmount().equals("N/A")){
				payeSecCell5 = new PdfPCell(new Phrase("Due Amount : "+enrollmentFormDto.getDueAmount()));
			}else{
				payeSecCell5 = new PdfPCell(new Phrase("Due Amount : $"+enrollmentFormDto.getDueAmount()));
			}
			payeSecCell5.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell5);

			PdfPCell payeSecCell6 = new PdfPCell(new Phrase("Due Date : "+enrollmentFormDto.getDueDate()));
			payeSecCell6.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell6);

			PdfPCell payeSecCell7 = new PdfPCell(new Phrase("Payment Mode : Paypal"));
			payeSecCell7.setBorder(PdfCell.NO_BORDER);
			payeSec.addCell(payeSecCell7);

			doc.add(payeSec);

			Paragraph paraGap4 = new Paragraph("   ");
			doc.add(paraGap4);

			PdfPTable idProof = new PdfPTable(2);
			idProof.setWidthPercentage(100);
			PdfPCell idProofCell1 = new PdfPCell(new Phrase("Candidate's ID Proof"
					+ "                                                                                                    "
					+ "While scanning this document,pleace attach your ID proof here.",fontcontent));
			idProofCell1.setMinimumHeight(250f);
			idProof.addCell(idProofCell1);
			PdfPCell idProofCell2 = new PdfPCell(new Phrase("Payee's ID Proof  "
					+ "                                                                                                     "
					+ " While scanning this document,please attach your ID proof here        ",fontcontent));
			idProof.addCell(idProofCell2);
			idProofCell2.setMinimumHeight(250f);
			doc.add(idProof);

			Paragraph paye1 = new Paragraph("Payee's Signature:______________________Date:_____",fontcontent);
			paye1.setAlignment(0);
			paye1.setSpacingBefore(1);
			paye1.setSpacingAfter(6);
			doc.add(paye1);

			Paragraph paye2 = new Paragraph("Candidate's Signature:______________________Date:_____",fontcontent);
			paye2.setAlignment(0);
			paye2.setSpacingBefore(1);
			paye2.setSpacingAfter(6);
			doc.add(paye2);

			doc.close();
			file.close();
			
			inputStream = new FileInputStream(f1);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return inputStream;
	}
}
