package utility;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import listeners.TestNgListener;

public class EmailUtils {
	public static void sendReport(String from, String password, String to, String subject) {
		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		try {
		    	//Set from address
				message.setFrom(new InternetAddress(from));
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				//Set subject
				message.setSubject(subject);
				//message.setText(body);
				BodyPart objMessageBodyPart = new MimeBodyPart();
				StringBuilder countSummary = new StringBuilder();
				StringBuilder networkErrorSummary = new StringBuilder();
				//Summary Table
				countSummary.append("<html><body>"+ "<table style=\"border-collapse:collapse\" width=\"600px\" cellspacing=\"0\" cellpadding=\"5\" border=\"1\">");
				countSummary.append("<tbody>");
				countSummary.append("<tr style=\"background-color:#406498;color:#ffffff\"><th style=\"background-color:#406498;color:#ffffff\">TOTAL COUNT</th>");
				countSummary.append("<th style=\"background-color:#406498;color:#ffffff\">PASSED</th>");
				countSummary.append("<th style=\"background-color:#406498;color:#ffffff\">FAILED</th></tr>");
				countSummary.append("<tr><td style=\"text-align:center\"><font color=\"#101010\"><b>"+TestNgListener.totalCount+"</b></font></td>");
				countSummary.append("<td style=\"text-align:center\"><font color=\"#00FF00\"><b>"+TestNgListener.totalPassed+"</b></font></td>");
				countSummary.append("<td style=\"text-align:center\"><font color=\"#FF3030\"><b>"+TestNgListener.totalFailed+"</b></font></td></tr>");
				countSummary.append("</tbody>");
				countSummary.append("</table></body></html>");
				
				//Network Error summary
				
				networkErrorSummary.append("<html><body>"+ "<table style=\"border-collapse:collapse\" width=\"600px\" cellspacing=\"0\" cellpadding=\"5\" border=\"1\">");
				networkErrorSummary.append("<tbody>");
				networkErrorSummary.append("<tr style=\"background-color:#406498;color:#ffffff\"><th style=\"background-color:#406498;color:#ffffff\">5XX Errors</th>");
				networkErrorSummary.append("<th style=\"background-color:#406498;color:#ffffff\">4XX Errors</th>");
				networkErrorSummary.append("<th style=\"background-color:#406498;color:#ffffff\">Other Errors</th></tr>");
				networkErrorSummary.append("<tr><td style=\"text-align:center\"><font color=\"#101010\"><b><a href=\"\"></a></b></font></td>");
				networkErrorSummary.append("<td style=\"text-align:center\"><font color=\"#101010\"><b><a href=\"\"></a></b></font></td>");
				networkErrorSummary.append("<td style=\"text-align:center\"><font color=\"#101010\"><b>"+"0"+"</b></font></td></tr>");
				networkErrorSummary.append("</tbody>");
				networkErrorSummary.append("</table></body></html>");
			
				objMessageBodyPart.setContent("Hi All,<br>Please find below the summary of automation run: <br><br>"
						    +"Start Time : "+TestNgListener.startDateTime+"<br>"
						    +"End Time  : "+TestNgListener.endDateTime+"<br><br>"
							+"<b>Summary:</b><br>"+countSummary.toString()+"<br>"
							+"<b>HTTP Error Statistics :</b><br>"+networkErrorSummary.toString()+"<br>"
							+"<html><body><a href=\"\">Report Link</a></body></html>"+"<br>"
							+"<b>Suite Wise Summary:</b><br>"+getSuiteDetails()+"<br>"
							+"<br><br>Thanks!<br>Automation", "text/html");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(objMessageBodyPart);
				objMessageBodyPart = new MimeBodyPart();
				message.setContent(multipart);
				Transport transport = session.getTransport("smtp");
				transport.connect(host, from, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			
		}catch (AddressException e) {
			e.printStackTrace();
		}catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getSuiteDetails() {
		StringBuilder suiteDetails = new StringBuilder();
		
		suiteDetails.append("<html><body>"+ "<table style=\"border-collapse:collapse\" width=\"600px\" cellspacing=\"0\" cellpadding=\"5\" border=\"1\">");
		suiteDetails.append("<thead>");
		suiteDetails.append("<tr style=\"background-color:#406498;color:#ffffff\"><th style=\"text-align:center\" width=\"40%\"><b>Suite Name</b></th>");
		suiteDetails.append("<th style=\"text-align:center\" width=\"15%\"><b>Passed #</b></th>");
		suiteDetails.append("<th style=\"text-align:center\" width=\"15%\"><b>Failed #</b></th>");
		suiteDetails.append("<th style=\"text-align:center\" width=\"15%\"><b>Skipped #</b></th>");
		suiteDetails.append("<th style=\"text-align:center\" width=\"15%\"><b>Total #</b></th></tr>");
		suiteDetails.append(" </thead><tbody>");
		
		for(String suite:Init.hashMap.keySet()) {
			int failed=0;
			int passed=0;
			int skipped=0;
			int total=0;
			ArrayList<Integer> arr = Init.hashMap.get(suite);
			for(int status:arr) {
				if(status == 2) {
					failed++;
				}
				if(status == 1) {
					passed++;
				}
				if(status == 3) {
					skipped++;
				}
			}	
			
			total = failed+passed+skipped;
			suiteDetails.append("<tr><td style=\"text-align:center\"><b style=\"color:#406498\"> "+suite+"</b></td>"
					+ "<td style=\"text-align:center\">"+passed+"</td>"
							+ "<td style=\"text-align:center\">"+failed+"</td>"
									+ "<td style=\"text-align:center\">"+skipped+"</td>"
											+ "<td style=\"text-align:center\">"+total+"</td></tr>");
			
		}		
		
		suiteDetails.append("</tbody></table></body></html>");
		
		return suiteDetails.toString();
	}

}
