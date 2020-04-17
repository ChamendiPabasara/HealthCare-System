$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


//SAVE 
$(document).on("click", "#btnSave", function(event)
{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();	
		 
		 // Form validation-------------------
		 var status = validateItemForm();
		 
		 if (status != true)
		 {
			 $("#alertError").text(status);
			 $("#alertError").show();
		 return;
		 }
		 
		// If valid------------------------
		 $("#formHospital").submit();
		});

		// UPDATE
		$(document).on("click", ".btnUpdate", function(event)
		{
		 $("#hidIHospIDSave").val($(this).closest("tr").find('#hidHospIDUpdate').val());
		 $("#hospName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#hospAddr").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#hospEmail").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#hospPhone").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#hospRegDate").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#hospCharge").val($(this).closest("tr").find('td:eq(5)').text());
});
		

		// CLIENTMODEL
		function validateItemForm()
		{
			// Hospital Name
			if ($("#hospName").val().trim() == "")
			 {
			 return "Insert Hospital Name.";
			 }
			
			// Hospital Address
			if ($("#hospAddr").val().trim() == "")
			 {
			 return "Insert Hospital Address.";
			 }
			
			//Hospital Email
			if ($("#hospEmail").val().trim() == "")
			 {
			 return "Insert Hospital Email.";
			 }

			//Hospital Phone
			if ($("#hospPhone").val().trim() == "")
			 {
			 return "Insert Hospital Phone.";
			 }
			
			//Hospital Registered Date
			if ($("#hospRegDate").val().trim() == "")
			 {
			 return "Insert Hospital Registered Date.";
			 }
			
			//Hospital Charge
			if ($("#hospCharge").val().trim() == "")
			 {
			 return "Insert Hospital Charge.";
			 }
			
			//Check for numeric value
			var charge = $("#hospCharge").val().trim();
			if(!$.isNumeric(charge))
			{
				return "Insert a numeric value for hospital charge";
			}
			$("#hospCharge").val(parseFloat(charge).toFixed(2));
			
		return true;
		}