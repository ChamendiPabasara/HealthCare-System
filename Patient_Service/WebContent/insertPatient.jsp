<%@ page import ="model.Patient" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
//Insert Patient----------------------------------
if (request.getParameter("p_nic") != null)
{
Patient pp = new Patient();
String stsMsg = pp.insertPatient(request.getParameter("p_nic"), 
		request.getParameter("p_fname"),
		request.getParameter("p_lname"),
		request.getParameter("p_dob"),
		request.getParameter("p_address"), 
		request.getParameter("p_phone"), 
		request.getParameter("p_gender"), 
		request.getParameter("p_gender"), 
		request.getParameter("user_user_id"));

session.setAttribute("statusMsg", stsMsg);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Patient</title>
</head>
<body>


<form id="formPatient" name="formPatient" method="post" action="insertPatient.jsp">
		NIC: 
		<input  name="p_nic" type="text" > <br><br>
		
		First Name:
		<input  name="p_fname" type="text"> <br> <br>
		
		Last Name: 
		<input  name="p_lname" type="text" > <br> <br>
		
		Date of Birth: 
		<input  name="p_dob" type="text" > <br> <br>
		
		Address: 
		<input  name="p_address" type="text" > <br> <br>
		
		Phone:
		<input  name="p_phone" type="text" > <br> <br>
		
		Email:
		<input  name="p_email" type="text" > <br> <br>
		
		Gender:
		<input  name="p_gender" type="text" > <br> <br>
	       
		
		User ID:
		<input  name="user_user_id" type="text"> <br><br>
		
		<input name="btnSubmit" type="submit" value="Save"> 
		
	</form>

</body>
</html>