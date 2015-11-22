package logic;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import modal.fileuploadmodal;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import dao.fileuploaddao;

public class fileuploadaction extends ActionSupport implements  ServletRequestAware, ModelDriven<fileuploadmodal> {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private fileuploadmodal obj = new fileuploadmodal();
	
private HttpServletRequest servletRequest;
public fileuploadmodal getObj() {
	return obj;
}

public void setObj(fileuploadmodal obj) {
	this.obj = obj;
}

public HttpServletRequest getServletRequest() {
	return servletRequest;
}

private fileuploaddao dao = new fileuploaddao();
private String chekuser;

	public String execute()
	{
System.out.println("::fileupload::");

	String destpath1 = servletRequest.getSession().getServletContext().getRealPath("/");
	System.out.println(destpath1);
	obj.setProductimage1Path(destpath1);
	
	if(dao.insertfile(obj)){
		setChekuser(dao.getCheckuser());
		return "success";
	}
	return "failure";
}

	@Override
	public fileuploadmodal getModel() {
		// TODO Auto-generated method stub
		return obj;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		servletRequest=arg0;
	}

	public String getChekuser() {
		return chekuser;
	}

	public void setChekuser(String chekuser) {
		this.chekuser = chekuser;
	}

}