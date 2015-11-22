<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<s:set name="message" value="message"/>
 
<s:if test="%{#message=='student'}">
	This is Struts 2
</s:if>
<s:else>
    Other framework
</s:else>
 
-->

rdxtcfghbjnk

			  	<div class="form-group">
					<div class="col-lg-2"></div>
    					<label  class="col-lg-4 control-label">Sub-Sub Category ID</label>
    	 					 <div class="col-lg-4">
								<select class="form-control" name="selectedsubsubcategoryid"  required>
									<option value="default" selected>Select below</option>
     						 		<s:iterator value="subsubcategorylist">
     						 		<option ><s:property value="subsubcategoryid"/> <s:property value="subsubcategoryname"/></option>		
     						 		</s:iterator>
								</select>
							</div>
     					  <br><br> 
				</div>
					
					
					


</body>
</html>