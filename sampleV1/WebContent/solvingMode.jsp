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
		
/*		var ele2 = document.getElementById("instruction2");
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
		}, false)*/;
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
		function test1()
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
			
		}
		
		function test2()
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
		#DialogueFrame
		{
			position:absolute;
			top:1%;
			bottom:1%;
			right:1%;
			left:1%;
			background: #CCFFCC;
			border: 2px solid grey;
		}
		
		#message4
		{
			background:rgb(216,216,216) ;
			position: absolute;
			height: 60%;
			width: 32%;
			left: 10%;
			margin-left: -100px;
			top: 36%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		#question
		{
			position:absolute;
			top:2%;
			bottom:70%;
			right:5%;
			left:5%;
			background: #CCFFCC;
			
		}
		#InstructionFrame
		{
			position:absolute;
			top:5%;
			bottom:55%;
			right:1%;
			left:1%;
			background: #66CCFF;
			border: 2px solid grey;
		}	
		
		#Content
		{
			position:absolute;
			top:8%;
			bottom:30%;
			right:750px;
			left:10px;
			background: #CCFFFF;
			border: 2px solid grey;
			
		}
		
		#Content2
		{
			position:absolute;
			top:30%;
			bottom:0%;
			right:750px;
			left:10px;
			background: #CCFFFF;
			border: 2px solid grey;
			overflow:auto;
			
		}
		
		#ProblemSolver
		{
			position:absolute;
			top:60px;
			bottom:10px;
			right:10px;
			width:725px;
			background: #CCFFFF;
			border: 2px solid grey;
			overflow:auto;
		}
		
		#message1
		{
			background:#CCFFCC;
			position: absolute;
			height: 50%;
			width: 30%;
			left: 20%;
			margin-left: -200px;
			top: 40%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		
		#rightnavigation
		{
			position: absolute;
			z-index:100;
			top:10px;
			right: 20px;
			height:50px;

		}
		#leftnavigation
		{
			position: absolute;
			z-index:100;
			top:10px;
			left: 20px;
			height:50px;

		}
		#centernavigation
		{
			position: absolute;
			z-index:100;
			top:10px;
			left: 20px;
			height:50px;

		}
		#input1
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
		#ProblemFrame
		{
			position:absolute;
			top:10%;
			bottom:67%;
			right:10%;
			left:5%;
			background: #CCFFCC;
			border: 2px solid grey;
                        overflow:auto;
			
		}
		textarea {
    background-image: -webkit-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
    background-image: -moz-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
    background-image: -ms-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
    background-image: -o-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
    background-image: linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
    background-size: 100% 31px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
    line-height: 31px;
    font-family: Arial, Helvetica, Sans-serif;
    padding: 8px;
}

textarea:focus {
    outline: none;
}
		
	
textarea.hidden {
    visibility: hidden;
}
	
		
	</style>
	

</head>
<body>
<h2 id = "heading">Intelligent Tutor for Math Word Problems</h2>
		<div id = "rightnavigation">
		   <table>
			<tr>
			<td>
		    <button id = "instruction1" onclick="test2();">My Learning Profile</button>
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
			     <b><s:property value="problemType" /></b>
			    &nbsp; <button onclick="window.open('Illustration/Illustration for Better Understanding.html','Help','width=650,height=500,top=150,left=650')">Illustration</button>
			    
		</div>
		<div id = "Content">
		<s:form action="learningAction">
		<s:submit name="Learning Mode" value="Learning Mode" theme="simple"></s:submit>
		</s:form>
		    <div id = "ProblemFrame">
			     <b><s:property value="problem" /></b>
			</div>
		</div>
		<div id="Content2">
		<table>
		<tr>
		 <td>
			<s:form action="schematic.action" >
			<s:submit name="Schematic" id="sk" value="Schematic Knowledge" onclick="myFunction()" theme="simple"></s:submit>
			</s:form>
		</td>
		<td>
			<s:form action="details.action" >
			<s:submit name="details" id="sm" value="Semantic Knowledge" onclick="myFunction()" theme="simple"></s:submit>
			</s:form>
		</td>  
		<td>
			<s:form action="formula.action" >
			<s:submit name="Formula" value="Formula" theme="simple"></s:submit>
				</s:form>
		</td>
		<td>
		<s:form action="graph.action" >
			<s:submit name="Graph" value="Graph" theme="simple"></s:submit>
			</s:form>
		</td>	
		<!-- <td>
		  <button id = "instruction4" onclick="test1();" >Image</button>
		</td> -->
		</tr>
		</table>
		<table>
		<tr>
		<td>
<s:set name="msg" value="msg"/>
 
<s:if test="%{#msg=='schematic'}">
  			<s:textarea cssStyle="color:blue" id="rahul" name="displaySchema" readonly="true" rows="12" cols="70" theme="simple" />
</s:if>
<s:else>
<!--   			<s:textarea cssClass="hidden" cssStyle="color:green" id="rahul" name="displaySchema" readonly="true" rows="12" cols="70" theme="simple" />	 -->
  			<s:textarea cssStyle="color:green" id="rahul1" name="displaySchema" readonly="true" rows="12" cols="70" theme="simple" />
</s:else>
<!--   			<s:textarea cssStyle="color:green" id="rahul" name="displaySchema" readonly="true" rows="12" cols="70" theme="simple" />	 -->
			<s:textarea name="displayFormula" cssStyle="color:red" readonly="true" rows="12" cols="70" theme="simple" />
		</td>
		</tr>
		
		</table>
		</div>
		<div id ="message4">
		      <img src="<s:property value="problemImage" />" alt="Diagramatic Representation" width ="410" height = "410"/>
	        </div>
	          <div id ="message1">
		        <ul>
		              <li><a href="sprogress.action">Problem Solving Progress</a></li>
		           <li><a href="smissconcept.action">Missing Concepts</a></li>
		            <li><a href="smissconception.action">MisConceptions</a></li>
			       <li><a href="scovering.action">Learned concepts </a></li>
			       <li><a href="smensuration.action">Learning Profile of Mensuration Domain</a></li>
			       <li><a href="sactivity.action">Activity</a></li>
		        </ul>
	        </div>
		<div id ="ProblemSolver">
		<s:label value = "Given:" />
			<s:form action="givenSubmit.action" method="post">
			<input type="text" name="givenText" size="75"/>
			<s:submit name="Submit" value="Enter" theme="simple"></s:submit>
		    <s:textarea rows="4" cols="80" name="givenArea" readonly="true" theme="simple">
		    </s:textarea>
		    </s:form>
		
		<s:label value = "To Find:" />
			<s:form action="tofindSubmit.action" method="post">
			<input type="text" name="tofindText" size="75"/>
			<s:submit name="Submit" value="Enter" theme="simple"></s:submit>
		    <s:textarea rows="4" cols="80" name="tofindArea" readonly="true" theme="simple">
		    </s:textarea>
		    </s:form>
		
		<s:label value="Solution"/>
			<s:form action="solutionSubmit.action" method="post">
			<input type="text" name="solutionText" size="75"/>
			<s:submit name="Submit" value="Enter" theme="simple"></s:submit>

	<s:textarea rows="20" cols="80" name="solutionArea" readonly="true" theme="simple"></s:textarea>

		    </s:form>
		</div>
		
	
		 <!--     <div id ="message1">
		        <ul>
		              <li><a href="progress.action">1. Problem solving progress</a></li>
		           <li><a href="missconcept.action">2. Missing Concepts</a></li>
		            <li><a href="missconception.action">3. MissConceptions</a></li>
			       <li><a href="covering.action">4. Learned concepts for covering problem</a></li>
			       <li><a href="mensuration.action">5. Learning Profile of Mensuration Domain</a></li>
		        </ul>
	        </div>
	        -->
		<!-- JavaScript files placed at the end of the document so the pages load faster
		================================================== -->
		<!-- Jquery and Bootstap core js files -->
		<script type="text/javascript" src="plugins/jquery.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

		<!-- Modernizr javascript -->
		<script type="text/javascript" src="plugins/modernizr.js"></script>

		<!-- Isotope javascript -->
		<script type="text/javascript" src="plugins/isotope/isotope.pkgd.min.js"></script>
		
		<!-- Backstretch javascript -->
		<script type="text/javascript" src="plugins/jquery.backstretch.min.js"></script>

		<!-- Appear javascript -->
		<script type="text/javascript" src="plugins/jquery.appear.js"></script>

		<!-- Initialization of Plugins -->
		<script type="text/javascript" src="js/template.js"></script>

		<!-- Custom Scripts -->
		<script type="text/javascript" src="js/custom.js"></script>
	        
<script>
 //   $("#sk").click(function(){
 //       $("#rahul").css("color", "red");
 //   });
</script>
<script>
//$(document).ready(function(){
//    $("#sm").click(function(){
//        $("#rahul").css("color", "blue");
//    });
//});
</script>
	        
<script>
/*function myFunction() {
    var str = document.getElementById("rahul").value;
    alert(str);
    var res = new Array();
    res = str.split(":");
    alert(res[0]+res[1]);
    document.getElementById("rahul1").innerHTML=res[0];
//    res[1].style.color = 'red';
}
*/
</script>
	        
			
</body>
</html>