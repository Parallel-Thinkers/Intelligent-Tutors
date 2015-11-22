<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<meta charset="utf-8">
		<title>MONTO | INTELLIGENT TUTOR</title>
		<meta name="description" content="Worthy a Bootstrap-based, Responsive HTML5 Template">
		<meta name="author" content="htmlcoder.me">

		<!-- Mobile Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Favicon -->
		<link rel="shortcut icon" href="images/favicon.ico">

		<!-- Web Fonts -->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,400,700,300&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:700,400,300' rel='stylesheet' type='text/css'>

		<!-- Bootstrap core CSS -->
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

		<!-- Font Awesome CSS -->
		<link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

		<!-- Plugins -->
		<link href="css/animations.css" rel="stylesheet">

		<!-- Worthy core CSS file -->
		<link href="css/style.css" rel="stylesheet">

		<!-- Custom css --> 
		<link href="css/custom.css" rel="stylesheet">
  
 		<link rel="stylesheet" href="TableCSSCode.css" type="text/css" />
     <script>
		window.onload = test;
		
		function test()
		{
			var ele1 = document.getElementById("instruction1");
			ele1.addEventListener("click",function()
			{
				
				
				var dis = document.getElementById("message1").style.display;
				
				if (dis == "block")
				{
					document.getElementById("message1").style.display = "none";
				}
				else
				{
					document.getElementById("message1").style.display = "block";
				}
			}, false);
			var ele2 = document.getElementById("instruction2");
			ele2.addEventListener("click",function()
			{
				
				
				var dis = document.getElementById("message2").style.display;
				
				if (dis == "block")
				{
					document.getElementById("message2").style.display = "none";
				}
				else
				{
					document.getElementById("message2").style.display = "block";
				}
			}, false);
			var ele3 = document.getElementById("instruction3");
			ele3.addEventListener("click",function()
			{
				
				
				var dis = document.getElementById("message3").style.display;
				
				if (dis == "block")
				{
					document.getElementById("message3").style.display = "none";
				}
				else
				{
					document.getElementById("message3").style.display = "block";
				}
			}, false);
			var ele4 = document.getElementById("instruction4");
			ele4.addEventListener("click",function()
			{
				
				
				var dis = document.getElementById("message4").style.display;
				
				if (dis == "block")
				{
					document.getElementById("message4").style.display = "none";
				}
				else
				{
					document.getElementById("message4").style.display = "block";
				}
			}, false);
			var ele5 = document.getElementById("instruction5");
			ele5.addEventListener("click",function()
			{
				
				
				var dis = document.getElementById("message5").style.display;
				
				if (dis == "block")
				{
					document.getElementById("message5").style.display = "none";
				}
				else
				{
					document.getElementById("message5").style.display = "block";
				}
			}, false);
		}
		
	</script> 
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
			right:10px;
			width: 430px;
			background: #FFFFCC;
			border: 2px solid grey;
		}

		#rightnavigation
		{
			position: absolute;
			z-index:100;
			top:10px;
			right: 5px;
			height:50px;

		}
		#leftnavigation
		{
			position: absolute;
			z-index:100;
			top:10px;
			left:2px;
			height:50px;
			
		}
		#input
		{
			width:500px;
			height:200px;
			align:center;
			padding:5px;
		}
		
		#LoginMain button
		{
			height: 50px;
			width:200px;
			margin:10px;
		}
		#subLogin
		{
			top:100px;
			left:50%;
			margin-left:-100px;
			position:absolute;
		}
		#message1
		{
			background:#FFFFCC;
			position: absolute;
			height: 400px;
			width: 500px;
			left: 380px;
			margin-left: -200px;
			top: 90px;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		#message2 
		{
			background:#FFFFCC;
			position: absolute;
			height: 500px;
			width: 500px;
			left: 250px;
			margin-left: -200px;
			top: 50px;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		#message ul
		{
			list-style:none;
		}
	</style>
	

</head>

<body>

<!-- 	<h2 id = "heading">Intelligent Tutor for Math Word Problems</h2>	 -->
	<h2 id = "heading">Student Profile</h2>
		<div id = "rightnavigation">
		<table>
		<tr>
		<td>
		<button id = "instruction1">My Learning Profile</button>
		</td>
		 <td>		    
			<button onclick="window.open('Help/System Interaction Help.html','Help','width=650,height=500,top=150,left=650')">Help</button>
			</td>			
				
			
			 <td>
		<td>
		<s:form action="loadProblemTypes.action" method="post">   
			<s:submit value="HOME" name="Home"/>
		  </s:form> 
		</td>
		<td>
		<s:form action="logout.action" method="post">   
			<s:submit value="Log Out" name="logout"/>
		  </s:form> 
		</td>
		</tr>
		</table>
		</div>
		<div id = "leftnavigation">
		<table>
		<tr>
			<s:form action="ProblemTypeSelectAction.action" method="post">
			<td> 
		  	<s:select list="ProblemTypeList" name="selectedProblemType" theme="simple"/>
		  	</td>
		  	<td>
			<s:select list="levels" name="selectedlevel" theme="simple"/>
			</td>
			<td>
			<s:submit value="submit" name="submit" theme="simple"/>
			</td>
			</s:form>
			</tr>	
		</table>
		<div id ="message1">
				<b>Learning Profile (Click the relevant link to see the details)</b>
			<ul>
			    <li><a href="sprogress.action">Problem Solving Progress</a></li>
		           <li><a href="smissconcept.action">Missingconcepts</a></li>
             <li><a href="smissconception.action">Misconceptions</a></li>
			       <li><a href="scovering.action">Learned Concepts </a></li>
			       <li><a href="smensuration.action">Learning Profile of Mensuration Domain</a></li>
			       <li><a href="sactivity.action">Activity</a></li>
			</ul>
			</div>
		</div>
		<div id ="Content">
		</div>
		
		<div id ="LoginMain">
			
		</div>
</body>
</html>