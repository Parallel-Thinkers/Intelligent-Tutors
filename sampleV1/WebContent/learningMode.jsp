<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <title>Test</title>
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
			background: #FFFFCC;
			border: 2px solid grey;
		}
		#question
		{
			position:absolute;
			top:2%;
			bottom:75%;
			right:5%;
			left:5%;
			background: #CCFFCC;
			
		}
		#InstructionFrame
		{
			position:absolute;
			top:5%;	
			bottom:45%;
			right:1%;
			left:1%;
			background: #66CCFF;
			border: 2px solid grey;
		}	
		
		#InstructionFrame1
		{
			position:absolute;
			top:56%;
			bottom:5%;
			right:1%;
			left:0.1%;
			background: white;
			border: 2px solid grey;
			height : 250px;
			width : 700px;
			overflow : auto
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
			left: 10px;
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
			top: 40%;
			-moz-border-radius : 5px;
			z-index : 100;
			display : none;
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
			width: 35%;
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
		<!-- <caption><b>Surface Area of Cube</b></caption> -->
		<b>	<s:property value="selectedProblemType"/></b>
		</div>
		<div id = "Content">
		    <div id = "ProblemFrame">
			     <b><s:property value="problem" /></b>
			</div>
			
			 <div id ="message1">
		        <ul>
		            <li><a href="progress.action">1. Problem Solving Progress</a></li>
		           <li><a href="missconcept.action">2. Errors</a></li>
    <!--         <li><a href="missconception.action">3. Misconceptions</a></li>		 -->
			       <li><a href="covering.action">3. Learned Concepts </a></li>
			        <li><a href="mensuration.action">4. Learning Profile of Mensuration Domain</a></li>
			        <li><a href="activity.action">5. Activity</a></li>
			     
		        </ul>
	        </div>
	<!--  	<div id= "ProblemImage">
			    <img src="<s:property value="problemImage" />" alt="Problem Image" height="200" width="140" align="center"/>
			</div>
			-->	
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
				 <s:form action="solvingAction.action" method="post">	
						<s:submit value="Solve the Problem" name="submit" />
						  </s:form>
						  </td>
                              
				<td>			
             <s:form action="startques" method="post" >	
             <s:submit name="startover" value="Start over"/>   
             </s:form>
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
		 <!--    <div id ="message1">
		        <ul>
		                <li><a href="progress.action">1. Problem solving progress</a></li>
		           <li><a href="missconcept.action">2. Missing Concepts</a></li>
		            <li><a href="missconception.action">3. MissConceptions</a></li>
			      <li><a href="mensuration.action">5. Learning Profile of Mensuration Domain</a></li>
		        </ul>
			       <li><a href="covering.action">4. Learned Concepts</a></li>
	        </div>
	        --> 
			<div id ="message4">
		      <img src="Image4.jpg" alt="Diagramatic Representation" width ="410" height = "410"/>
	        </div>
	         
			<div id ="DialogueFrame">
			    
		<s:form action="selectAnswer" method="post" >	    
				<div id ="question">
                    <div id="InstructionFrame">
                         <h4>Use the following question-answer session to learn how to approach the given problem! At some places you can select multiple choices.</h4>
				    </div>
                    <br/>
                    <br/>
					<br/>
                   					
					<p>
						<b><s:property value="question"/></b>
					</p>
					
					
					<s:select list="choiceList" name="selected" listKey="key" listValue="value"/>
					<br/>
					<br/>
					
					<br/>
					<br/>
					
					<s:submit value="submit" name="submit"/>
					
					<br><br>
					<div id="InstructionFrame1">
					<h3 align="center">Questions Answered Till Now</h3>
					  <s:textarea name="concepts" readonly="true" rows="11" cols="97" theme="simple" />
					</div>		    
					 
					
					<br/>
					<br/>
					<br/>
					<br/>
					
					
					<!--  <input type="button" value="Next Question" onClick="self.location='Screen5.1.html'"/>-->
				</div>
				
				</s:form>
			</div>
		</div>
</body>
</html>