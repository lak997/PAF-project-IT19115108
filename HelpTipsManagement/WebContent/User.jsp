<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/User.js"></script>

<meta charset="ISO-8859-1">
<title>Help Tips Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Help Tips Management</h1>

	<form id="formItem" name="formItem">
		
		 Related Area:
		<input id="relatedArea" name="relatedArea" type="text" class="form-control form-control-sm"><br> 
		Tip Detail:
		<input id="tipDetail" name="tipDetail" type="text" class="form-control form-control-sm"><br>
		 Date:
		<input id="date" name="date" type="text" class="form-control form-control-sm"><br>
		
		
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	User TipsObj = new User(); 
	 out.print(TipsObj.readTips()); 
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>