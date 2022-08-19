package Utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//this class send an email to a customer in case he or she forgot their password or user name
//Recipient = destination account(customer account)
//message = the massage of the email
//title = the title of the massage.
//senderMail = the email address of the sender.
//senderPass= the email password to the sender.
//***NOTE*** sometimes the anti virus program do not allow the method to work and an exception is thrown.
public class SendMail {

	public static void send(String recepient ,String massage,String Title ,String senderMail, String SenderPass) {
		
		//protocols
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.transport.protocle", "smtp");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//the account which the email is send from
		String myEmail = senderMail;
		String pass = SenderPass;
		
		//create a session
		Session sess = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(myEmail, pass);
			}
		});
		//creating a message
		Message massg = prepareMessage(sess, myEmail, recepient, massage, Title);
		try {
			Transport.send(massg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//this method create the message
	private static Message prepareMessage(Session sess, String email,String recepient,String massage, String Title) {
		// TODO Auto-generated method stub
		Message massg = new MimeMessage(sess);
		try {
			massg.setFrom(new InternetAddress(email));
			massg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			massg.setSubject(Title);
			massg.setText(massage);
			return massg;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
