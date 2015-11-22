<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
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
		#DialogueFrame
		{
			position:absolute;
			top:1%;
			bottom:1%;
			right:1%;
			left:1%;
			background: #FFFFCC;
			border: 2px solid grey;
		}
		#question
		{
			position:absolute;
			top:2%;
			bottom:70%;
			right:5%;
			left:5%;
			background: #FFFFCC;
			
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
			top:60px;
			bottom:10px;
			right:750px;
			left:10px;
			background: #FFFFCC;
			border: 2px solid grey;
			
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
			top:15%;
			bottom:65%;
			right:10%;
			left:5%;
			background: #CCFFCC;
			border: 2px solid grey;
                        overflow:auto;
			
		}
		#message1
		{
			background:#CCFFCC;
			position: absolute;
			height: 50%;
			width: 80%;
			left: 40%;
			margin-left: -200px;
			top: 10%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : block;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
        #message2
		{
			background:rgb(216,216,216) ;
			position: absolute;
			height: 45%;
			width: 45%;
			left: 35%;
			margin-left: -200px;
			top: 40%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		#message3
		{
			background:rgb(216,216,216) ;
			position: absolute;
			height: 45%;
			width: 45%;
			left: 85%;
			margin-left: -200px;
			top: 40%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
			border: 1px solid rgb(200,200,200) ;
			-moz-box-shadow: 2px 2px 5px rgb(184,184,184) ;
			overflow:auto;
		}
		
		#message4
		{
			background:rgb(216,216,216) ;
			position: absolute;
			height: 45%;
			width: 45%;
			left: 70%;
			margin-left: -200px;
			top: 45%;
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
		<!-- <caption><b>Surface Area of Cube</b></caption> -->
		<b>	<s:property value="selectedProblemType"/></b>
		</div>
		<div id = "Content">
		 <!--    <div id = "ProblemFrame">
			     <b><s:property value="problem" /></b>
			</div>
			 -->
			   <div id ="message1">
		        <ul>
		            <li><a href="sprogress.action">Problem Solving Progress</a></li>
		           <li><a href="smissconcept.action">Missingconcepts</a></li>
    	         <li><a href="smissconception.action">Misconceptions</a></li>
			       <li><a href="scovering.action">Learned Concepts </a></li>
			        <li><a href="smensuration.action">Learning Profile of Mensuration Domain</a></li>
			        <li><a href="sactivity.action">Activity</a></li>
			     
		        </ul>
	        </div>
		<!--	
                 <input type="button" value="Back" onClick="self.location='choosemode.jsp'"/>
                 
                   		
                 <button>Sample Example</button>			
			     <button id = "instruction3">Semantic Structure</button>		         
			     <button id = "instruction2">Schematic Structure</button>				 
				 <button id = "instruction4">Diagrammatic Representation</button>
				 -->
				 <div id = "leftnavigation">
			<table>
			<tr>
							 <td>
				
						  </td>
                              
				<td>			
            
             </td>
             </tr>
           	</table>
			</div>
			<!-- 
			<div id ="message2">
		      <img src="Image1.jpg" alt="Schematic Structure" width ="410" height = "410"/>
		      
	        </div> 
	        <div id ="message3">
		      <img src="Image2.jpg" alt="Semantic Structure" width ="410" height = "410"/>
	        </div> 
	         -->
	       
		</div>			
	
		<div id ="ProblemSolver">
		 <!--     <div id ="message1">
		        <ul>
		         <ul>
		              <li><a href="progress.action">1. Problem solving progress</a></li>
		           <li><a href="missconcept.action">2. Missing Concepts</a></li>
		            <li><a href="missconception.action">3. MissConceptions</a></li>
			       <li><a href="covering.action">4. Learned concepts for covering problem</a></li>
			       <li><a href="mensuration.action">5. Learning Profile of Mensuration Domain</a></li>
		       
		        </ul>
	        </div>
	        -->
			<div id ="message4">
		      <img src="Image4.jpg" alt="Diagramatic Representation" width ="410" height = "410"/>
	        </div>
	         
			
			    
		<s:form action="selectAnswer" method="post" >	    
		
		           
				
                   <table class="CSSTableGenerator" style="display: block;height: 600px	;overflow-y: scroll;width: 100%">
                   
                   <tr style="overflow:auto">
                    <td colspan="5" align="center">ERRORS Captured</td>
                   </tr>
                   <tr style="overflow:auto">
                     <td width="50%"><h4 align="center">INCOMPLETE TASK</h4></td>
                    
                     <td width="5%"><h4>Percentage Resolved</h4></td>
                      <td width="45%"><h4 align="center">Remedial Feedback</h4></td>
                   </tr>  
                   
                    
                    <s:iterator value="bugs"> 
                     <tr>
                     <td width="50%"><s:property value="description"/></td>
                     
                     <td width="5%"><s:property value="perc"/> %</td>
                      <td width="45%"><s:property value="feedback"/></td>
                   
                     </tr>
                     
                    </s:iterator> 

                    <s:iterator value="bugs1"> 
                     <tr>
                     <td width="50%"><s:property value="description"/></td>
                    
                     <td width="5%"><s:property value="perc"/> %</td>
                       <td width="45%"><s:property value="feedback"/></td>
                  
                     </tr>
                     
                    </s:iterator> 
                   </table>
                  
                            
                  
					
					<!--  <input type="button" value="Next Question" onClick="self.location='Screen5.1.html'"/>-->
				
				
				</s:form>
			</div>
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
$(document).ready(function(){
    $("#sp").on("click", function(){
        $.post("studentprofile.action", function(result){
            $("#stprofile").html(result);
        });        
    });
});
</script>		
</body>
</html>