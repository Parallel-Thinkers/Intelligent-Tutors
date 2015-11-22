<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <title>Login</title>
    <style>

		#heading
		{
			position:absolute;
			top:0px;
			left:0px;
			right:0px;
			height: 50px;
			line-height:50px;
			text-align: center;
			margin:0px;
			padding:0px;
			background: #6699CC;
		}

		#Content
		{
			position:absolute;
			top:60px;
			bottom:10px;
			right:470px;
			left:10px;
			background: #FFFFCC;
			border: 2px solid grey;
		}

		#LoginMain
		{
			position:absolute;
			top:60px;
			bottom:10px;
			right:20px;
			width: 430px;
			background: #FFFFCC;
			border: 2px solid grey;
		}
		form
		{
			position:absolute;
			bottom:50px;
			left:20%;
			width: 100%;
			height: 400px;
			

		}
		#login
		{
			position:absolute;
			top:30px;
			left:50%;
			width:200px;
			height: 50px;
			font-size:1.5em;
			font-weight:bold;
			margin-left:-100px;
		}

		#pwd
		{
			position:absolute;
			top:100px;
			float:right;
		}
		form label
		{
			width:100px;
			position:absolute;

		}

		form input
		{
			position:absolute;
			left:110px;
		}
		#submit
		{
			position: absolute;
			top:70px;
			right: 130px;
		}
		
	</style>


</head>

<body>

	<h2 id = "heading">Intelligent Tutor for Math Word Problems</h2>

	<div id = "Content">

	<p>
			<h3><center>Information About Intelligent Tutor</center></h3>
			<b>This is an intelligent tutoring system for teaching word problem solving. To use this student needs to register by filling a form for collecting basic information. After receiving the login information through e-mail, student can start using it. After login student's learning home page will be opened where student can find table of contents from which student can select type of problem to learn or practice and select one problem of the same type from the problem database. <br></br>&nbsp&nbsp&nbsp After the selection of the problem student can start solving the problem directly by using a tool named input editor which is available in a separate tab. If needed student can see a sample problem. If student does not know how to solve the problem and wants help then student can click on the tab 'Need Help?' and get help from the machine tutor. This system tracks student's interaction and builds student's learning profile. Student's learning profile contains a) Type of problems student have learned b)Concepts which student know very well c)Concepts which student have not understood. <br></br>&nbsp&nbsp&nbsp The tool input editor contains mathematical symbols, alphabets for variables, various mathematical operations and various shapes for drawing rough diagram needed for diagramatic representation of the problem. If student wants to know key terms or concepts which are used in the problem then student can see it by clicking on the key concepts. After solving one problem student can select another problem of the same type for practice if needed. Student can also see schematic knowledge structure of the word problem if needed. </b>
	</p>
	</div>

	<div id = "LoginMain">
		<span>
			<button id = "login" style="background-color:#ADADFF">Login</button>
		</span>
		<s:form action="loginAction.action" method="post">   
			
			<table>
			  <!--  <tr>
			    <td>
				<label id = "login">Login:</label>
				<td/>
				</tr>-->
				<tr>
				<td>
				<label>UserName</label>
				</td>
				<td>
				<s:textfield key="username" theme="simple" />
				</td>
				</tr>
				<tr>
				<td>
				<br>	
				<br>
				<label>Password</label>
				</td>
				<td>
				<br>
				<br>
				<input type="password" name="password" />
				</td>
				</tr>
				
				
				<tr>
				
				<!-- Password 
				<input type="password" name="password" /><br /> -->
				</tr>
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr />
				<tr>
				<td>
			<s:submit value="submit" name="submit"/>
			</td>
			</tr>
			</table>
		  </s:form> 
				<!--<span id = "pwd">
					<button>Change Password</button>
					<button>Forgot Password ??</button>
				</span>-->
			
		
	</div>

</body>
</html>