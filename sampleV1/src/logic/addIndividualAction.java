package logic;

import modal.addindividualmodal;
import modal.userdetailmodal;
import authentication.SendEmail;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.addindividualdao;

//public class addIndividualAction extends ActionSupport implements ModelDriven<addindividualmodal>{
	public class addIndividualAction{
	/**
	 * @author kumar rahul and shan mehrotra
	 * 
	 * this function performs functionality of add individual student by teacher
	 */
	private static final long serialVersionUID = 1L;


//	private addindividualmodal obj = new addindividualmodal();
	
	private addindividualdao dao = new addindividualdao();
	private userdetailmodal user=new userdetailmodal();
	private SendEmail sendEmail=new SendEmail();
	private String message;
	private String chkusr;
	
	public String execute(addindividualmodal obj){
		
		System.out.println("addIndividualAction inside execute"+obj.getTeacherid());
		setMessage(dao.addindividual(obj));
		setChkusr(dao.getCheckuser());
System.out.println("message in action::"+getMessage()+"chkusr in action::"+getChkusr());
		if(getMessage().equalsIgnoreCase("success")){
			user.setName(obj.getStudentname());
			user.setEmail(obj.getUseremail());
			user.setPassword("welcome");
			user.setLoginid(obj.getUseremail());
			message=sendEmail.sendUserDetailsToMail(user);
			return "success";
		}
		else if(getMessage().equalsIgnoreCase("already")){
			return "success";
		}
		else{
			return "failure";
		}
		
		
		
//		return null;
		
	}
	
	
	
/*	
	@Override
	public addindividualmodal getModel() {
		// TODO Auto-generated method stub
		return obj;
	}

*/


	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}



	public String getChkusr() {
		return chkusr;
	}



	public void setChkusr(String chkusr) {
		this.chkusr = chkusr;
	}
	
	
	
	
	

}
