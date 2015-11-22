package authentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modal.userdetailmodal;
import java.util.Properties;


public class SendEmail
{
	private Session session;
	private String username,password;

	public String sendUserDetailsToMail(userdetailmodal user)
    {
    	try 
    	{
    		username = "monto.intelligenttutors@gmail.com";			//write gmail id
            password = "montotutors";								//give password for above mail

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
    		 System.out.println(user.getName());
    		 System.out.println(user.getEmail());
             System.out.println(user.getPassword());
        	System.out.println("In Send User Details");
        	session= Session.getDefaultInstance(props,
                    new javax.mail.Authenticator()
        			{
                        protected PasswordAuthentication getPasswordAuthentication() {
//System.out.println("rahul");
                        	return new PasswordAuthentication(username,password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("monto.intelligenttutors@gmail.com"));		//write gmail id
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
           
            message.setSubject("Account Creation");
            message.setText("Hi  " +user.getName()+","+
                    "\n\nWelcome To INTELLIGENT TUTOR.\n\n"
            		+"User Name : "+user.getLoginid()
                    +"\nPassword  :"+user.getPassword()
                    +"\n\nThanks and Regards,\n"
                    +"MONTO Team");      

            Transport.send(message);
            System.out.println("DONE"); 

            return "success";

        }
        catch (MessagingException e) 
        {e.printStackTrace();
          System.out.println(e.getLocalizedMessage());
        }
		return "failure";
    }

	public void sendforgotpassword(userdetailmodal user) {
		// TODO Auto-generated method stub
    	try 
    	{
    		username = "monto.intelligenttutors@gmail.com";			//write gmail id
            password = "montotutors";								//give password for above mail

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
    		 System.out.println(user.getName());
    		 System.out.println(user.getEmail());
             System.out.println(user.getPassword());
        	System.out.println("In Send forgot password");
        	session= Session.getDefaultInstance(props,
                    new javax.mail.Authenticator()
        			{
                        protected PasswordAuthentication getPasswordAuthentication() {
//System.out.println("rahul");
                        	return new PasswordAuthentication(username,password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("monto.intelligenttutors@gmail.com"));		//write gmail id
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
           
            message.setSubject("Password");
            message.setText("Hi  " +user.getName()+","+
                    "\n\nWelcome To INTELLIGENT TUTOR. Your old password is:\n\n"
            		+"Password  :"+user.getPassword()
                    +"\n\nThanks and Regards,\n"
                    +"MONTO Team");      

            Transport.send(message);
            System.out.println("DONE"); 

//            return "success";

        }
        catch (MessagingException e) 
        {e.printStackTrace();
          System.out.println(e.getLocalizedMessage());
        }
//		return "failure";
		
	}
	
	public void welcome(String name,String password1,String mail) {
		// TODO Auto-generated method stub
    	try 
    	{
    		username = "monto.intelligenttutors@gmail.com";			//write gmail id
            password = "montotutors";								//give password for above mail

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
    	//	 System.out.println(user.getName());
    	//	 System.out.println(user.getEmail());
         //    System.out.println(user.getPassword());
        	System.out.println("In welcome");
        	session= Session.getDefaultInstance(props,
                    new javax.mail.Authenticator()
        			{
                        protected PasswordAuthentication getPasswordAuthentication() {
//System.out.println("rahul");
                        	return new PasswordAuthentication(username,password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("monto.intelligenttutors@gmail.com"));		//write gmail id
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail));
           
            message.setSubject("Welcome");
            message.setText("Hi  " +name+","+
                    "\n\nWelcome To INTELLIGENT TUTOR.\n\n"
            		+"User Name : "+mail
                    +"\nPassword  :"+password1
                    +"\n\nThanks and Regards,\n"
                    +"MONTO Team");          

            Transport.send(message);
            System.out.println("DONE"); 

//            return "success";

        }
        catch (MessagingException e) 
        {e.printStackTrace();
          System.out.println(e.getLocalizedMessage());
        }
//		return "failure";
		
	}
}
