
<%@page import="model.Doctor"%>
<%@page import="config.DBConnector"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Doctor's Appointments</h1>

	<form  name="formDocApp" method="post" action="viewAppoinment.jsp">

		 Doctor ID: <input  name="docId" type="text"> <br> <br>
		 <input id="btnSave" name="btnSave" type="button" value="Save"> 
		    
	</form><br>
	
	
	<% 
		Doctor appObj = new Doctor();
		out.print(appObj.readDocAppointments(request.getParameter("docId")));
	%>	 
		 


</body>
</html>