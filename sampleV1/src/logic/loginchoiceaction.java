package logic;

public class loginchoiceaction {
	
	private String choice;
	private String message;
	
	public String rahul(){

		System.out.println("rahul");
		System.out.println("inside loginchoiceaction::"+getChoice());
		
		
		if(choice.equals("student")){
			setMessage(getChoice());
			System.out.println("success"+getMessage());
			return "success";
		}
		else if(choice.equals("teacher")){
			setMessage(getChoice());
			System.out.println("success"+getMessage());
			return "success";
		}

		return "failure";
	}
	
	
	
	
	
	
	
	
	
	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}









	public String getMessage() {
		return message;
	}









	public void setMessage(String message) {
		this.message = message;
	}
	
	

	
	
	
	
}
