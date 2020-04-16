<%@page import="java.sql.Date"%>
<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


 <%
    
  //Insert Appointment------------------------------------ 
	if (request.getParameter("itemCode") != null) {

		session.setAttribute("date", request.getParameter("date"));
		session.setAttribute("time", request.getParameter("time"));
		session.setAttribute("patient_patient_id", Integer.parseInt(request.getParameter("	patient_patient_id")));
		session.setAttribute("doctor_doc_id ", Integer.parseInt(request.getParameter("doctor_doc_id ")));
		session.setAttribute("hospital_hosp_id ", Integer.parseInt(request.getParameter("hospital_hosp_id  ")));

		Appointment App1 = new Appointment();

		String stsMsg = App1.addAppointment(
				Date.valueOf(request.getParameter("date")),
				request.getParameter("time"),
				Integer.parseInt(request.getParameter("patient_patient_id")),
				Integer.parseInt(request.getParameter("doctor_doc_id")),
				Integer.parseInt(request.getParameter("hospital_hosp_id")));

		session.setAttribute("statusMsg", stsMsg);

	}

	//Delete Appointment------------------------------------ 
	if (request.getParameter("appoinment_id ") != null) {
		Appointment App1 = new Appointment();
		String stsMsg = App1.DeleteAppointment(Integer.parseInt(request.getParameter("appoinment_id ")));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Appointment</title>
</head>
<body>


<div class="container">
			<div class="row">
				<div class="col-6">
				
				<h1>Request an Appointment</h1>
				
					<form id="formAppo" name="formAppo" method="post" action="RequestAppo.jsp">
						Doctor Name: <input id="DoctorName" name="DoctorName" type="text"
							class="form-control form-control-sm"> <br> 
							Hospital name: <input id="hosName" name="hosName" type="text"
							class="form-control form-control-sm"> <br> 
						Appointment Date : <input id="AppoDate" name="AppoDate" type="text"
							class="form-control form-control-sm"> <br> 
						Appointment Time: <input id="AppoTime" name="AppoTime" type="text"
							class="form-control form-control-sm"> <br> <input
							id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hidAppIDSave" name="hidAppIDSave" value="">
					</form>
					
					<div id= "alertSuccess"class="alert alert-success">
					
					   <%
		              	out.print(session.getAttribute("statusMsg"));
		               %>
				</div>
			<div id= "alertSuccess"class="alert alert-success"></div>
	
		<br>
	
	         	<%
	         	Appointment App1 = new Appointment();
					out.print(App1.ReadAppointments());
	           	%>
		
	  </div>
    </div>
    </div>


</body>
</html>