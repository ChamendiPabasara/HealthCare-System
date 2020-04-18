<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Hospital"%>

<%
	if(request.getParameter("hospName") != null){
		Hospital hospObj = new Hospital();
		String stsMsg = "";
		
		//Insert new hospital
		if(request.getParameter("hidIHospIDSave") == ""){
			stsMsg = hospObj.insertHospital(request.getParameter("hospName"), 
					request.getParameter("hospAddr"), 
					request.getParameter("hospEmail"), 
					request.getParameter("hospPhone"), 
					request.getParameter("hospRegDate"), 
					request.getParameter("hospCharge"));
			
		}
		//Update existing hospital
		else{
			stsMsg = hospObj.updateHospitals(request.getParameter("hidIHospIDSave"), 
					request.getParameter("hospName"), 
					request.getParameter("hospAddr"), 
					request.getParameter("hospEmail"), 
					request.getParameter("hospPhone"), 
					request.getParameter("hospRegDate"), 
					request.getParameter("hospCharge"));
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Deelete existing hosptal
	if(request.getParameter("hidHospIDDelete") != null){
		Hospital hospObj = new Hospital();
		String stsMsg = hospObj.deleteHospital(request.getParameter("hidHospIDDelete"));
		session.setAttribute("statusMsg" , stsMsg);
	}
		

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Service</title>

<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Component/jquery-3.5.0.min.js"></script>
<script src="Component/hospitals.js"></script>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

</head>
<body>

	<div class="container">
		<div class="row">

			<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">
				Hospital Name: <input id="hospName" name="hospName" type="text" class="form-control form-control-sm"> <br> 
				Hospital Address: <input id="hospAddr" name="hospAddr" type="text" class="form-control form-control-sm"> <br> 
				Hospital Email: <input id="hospEmail" name="hospEmail" type="email" class="form-control form-control-sm" placeholder="example@gmail.com"> <br> 
				Hospital Phone no: <input id="hospPhone" name="hospPhone" type="text" class="form-control form-control-sm" placeholder="000 000 0000"> <br>
				Hospital Registered Date: <input id="hospRegDate" name="hospRegDate" type="text" class="form-control form-control-sm" placeholder="dd-MM-yyyy"> <br>
				Hospital Charge: <input id="hospCharge" name="hospCharge" type="text" class="form-control form-control-sm"> <br>
				 
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
				<input type="hidden" id="hidIHospIDSave" name="hidIHospIDSave" value="">
			</form>
		</div>
		<br>
		<div id="alertSuccess" class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
		</div>
		<div id="alertError" class="alert alert-danger">
		
		</div>
		<br>
		<div>
			<%
				Hospital hospObj = new Hospital();
				out.print(hospObj.readHospital());
			%>
		</div>
	</div>

</body>
</html>