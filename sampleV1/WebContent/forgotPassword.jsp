<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/sis-bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
 <link href="<%=request.getContextPath()%>/sis-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
   <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/sis-bootstrap/css/stylish-portfolio.css" rel="stylesheet" type="text/css"/>
     <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/sis-bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
      <script src="<%=request.getContextPath()%>/sis-bootstrap/js/bootstrap.js"></script>
        <script src="<%=request.getContextPath()%>/sis-bootstrap/js/bootstrap.min.js"></script>
        <link rel="icon" type="image/ico" href="icon.ico"/>
        <title>Welcome to  Student Information System</title>
</head>
<body>
<br><br>
<div class="col-lg-2">
</div>
<div class="col-lg-7">
<form method="post" action="forgotPassword" class="form-horizental">
		 <div class="form-group">
      	
      	<div class="col-lg-6">
     		     <center> 	<label class="col-lg-12 control-label"><s:property value="message"/></label></center>						  
      	</div>
      	<br><br>
      </div>
	
	<div class="form-group">
      	<label  class="col-lg-2 control-label">User Name</label>
      	<div class="col-lg-4">
     		<div class="input-group">
     			<span class="input-group-addon" id="basic-addon1">
     			  <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 
     			</span>
       			<input type="text" class="form-control" placeholder="Type UserName here" name="rollNumber">
       		 </div>       							
       							  
      	</div>
      	<br><br>
      </div>	 
	
	<div class="form-group">
      	<div class="col-lg-10 col-lg-offset-2"><input type="submit" value="Submit"  class="btn btn-primary" />
     	 </div>
     </div>
	
</form>
</div>
<div class="col-lg-3"></div>
</body>
</html>