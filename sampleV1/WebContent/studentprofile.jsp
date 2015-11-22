<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<center>
    <div class="form-group">
      <label for="inputEmail" class="col-lg-4 control-label">Student List</label>
	      <div class="col-lg-8">
			<select class="form-control" name="studentlistid" required>
					<option value="default" selected>Select below</option>
     			<s:iterator value="list1">
     		 		<option><s:property value="studentname"/>/<s:property value="useremail"/></option>
    			</s:iterator>
			</select>
    	  </div>
    </div>
<br>
    <div class="form-group">
      <label for="inputEmail" class="col-lg-4 control-label">Problem Type</label>
	      <div class="col-lg-8">
			<select class="form-control" name="problemtypeid" required>
					<option value="default" selected>Select below</option>
     			<s:iterator value="list2">
     		 		<option><s:property /></option>
    			</s:iterator>
			</select>
    	  </div>
    </div>

</center>
    