
<%@page import="model.AdminDoc"%>
<%@page import="config.DBConnector"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
if (request.getParameter("nic") != null)
{
	AdminDoc ad = new AdminDoc();
	String stsMsg = ad.insertDoctor(request.getParameter("nic"),  
		request.getParameter("fname"),
		request.getParameter("lname"),
		request.getParameter("email"),
		request.getParameter("rdoGender"), 
		request.getParameter("liscen"), 
		request.getParameter("special"), 
		request.getParameter("phone"), 
		request.getParameter("charge"),
		request.getParameter("user"));

		session.setAttribute("statusMsg", stsMsg);
	
}


%>
<%
		//Deelete existing Doctor
		if(request.getParameter("hidDocIDDelete") != null){
		AdminDoc ad1 = new AdminDoc();
		String stsMsg = ad1.deleteDoctor(request.getParameter("hidDocIDDelete"));
		session.setAttribute("statusMsg" , stsMsg);
		}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Doctor</title>

<link rel="stylesheet" href="View/bootstrap.min.css">


</head>
<body>


<h1>Insert New Doctor</h1>


	<form id="formDoctor" name="formDoctor" method="post" action="insertDoctor.jsp">
		NIC: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="nic" type="text" > <br><br>
		
		First Name: &nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="fname" type="text"> <br> <br>
		
		Last Name: &nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="lname" type="text" > <br> <br>
		
		Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="email" type="text" > <br> <br>
		
	       
		<span class="input-group-text" id="lblName">Gender: </span>    
		 			  
		 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Male 
		 			<input type="radio" id="rdoGenderMale" name="rdoGender" value="Male">
		 			    
		 			&nbsp;&nbsp;Female  
		 			<input type="radio" id="rdoGenderFemale" name="rdoGender" value="Female">   
		<br><br>
		
		
		Liscen NO: &nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="liscen" type="text"> <br><br>
		
		Specialization: 
		<input  name="special" type="text" > <br><br>
		
		Phone: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="phone" type="text" > <br><br>
		
		Doctor Charge: 
		<input id="charge" name="charge" type="text" > <br><br>
		
		User ID: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input  name="user" type="text"> <br><br>
		
		
		<input name="btnSubmit" type="submit" value="Save"  class="btn btn-primary">  
		
		
	</form>
	
	
	<br>
		<div id="alertSuccess" class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
		</div>
		
		
		<br>
		
		<div>
			<%
				AdminDoc ad2 = new AdminDoc();
				out.print(ad2.readDoctors());
			%>
		</div>
	
</body>
</html>