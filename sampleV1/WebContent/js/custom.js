/* Theme Name: Worthy - Free Powerful Theme by HtmlCoder
 * Author:HtmlCoder
 * Author URI:http://www.htmlcoder.me
 * Version:1.0.0
 * Created:November 2014
 * License: Creative Commons Attribution 3.0 License (https://creativecommons.org/licenses/by/3.0/)
 * File Description: Place here your custom scripts
 */

/*forgotpassword*/
/*
function ajaxforgotpassword(){
	alert("inside ajax");
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
			 console.log(xmlhttp.responseText);
		    document.getElementById("forgotpassworddivid").innerHTML=xmlhttp.responseText;
		    //event.preventDefault();
		   
	    }
	  }
xmlhttp.open("POST","forgotpasswordlink.action",true);
	xmlhttp.send();
//	alert("ajax call send");
	return true;
	// event.preventDefault();
	//event.preventDefault();	
}
*/



function ajaxstudentprofile(){
	console.log("inside ajax");
	alert("inside ajax");
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
			 console.log(xmlhttp.responseText);
		    document.getElementById("studentprofileid").innerHTML=xmlhttp.responseText;
		    //event.preventDefault();
		   
	    }
	  }
xmlhttp.open("POST","studentprofile.action",true);
	xmlhttp.send();
//	alert("ajax call send");
	return true;
}


function validate()
{
	
	 var email = document.getElementById("inputEmail");
	 var mail = /^[a-z/_.\-/0-9]*[@][a-z]*\.[a-z]{2,4}$/;

	 if(email =="" || (!email.match(mail))){
		 document.getElementById('abc').innerHTML="Please enter correct email id";
		 return false;
	 }
	 else
		document.addstudentform.submit();
	}

