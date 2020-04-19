$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).ready(
		function() {
			var date_input = $('input[name="hospRegDate"]');
			var container = $('.bootstrap-iso form').length > 0 ? $(
					'.bootstrap-iso form').parent() : "body";
			var options = {
				format : 'dd-mm-yyyy',
				container : container,
				todayHighlight : true,
				autoclose : true,
			};
			date_input.datepicker(options);
		})
// SAVE
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();

	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	$("#formHospital").submit();
});

// UPDATE
$(document)
		.on(
				"click",
				".btnUpdate",
				function(event) {
					$("#hidIHospIDSave").val(
							$(this).closest("tr").find('#hidHospIDUpdate')
									.val());
					$("#hospName").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#hospAddr").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#hospEmail").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#hospPhone").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#hospRegDate").val(
							$(this).closest("tr").find('td:eq(4)').text());
					$("#hospCharge").val(
							$(this).closest("tr").find('td:eq(5)').text());
				});

// CLIENTMODEL
function validateItemForm() {
	// Hospital Name
	if ($("#hospName").val().trim() == "") {
		return "Insert Hospital Name.";
	}

	// Hospital Address
	if ($("#hospAddr").val().trim() == "") {
		return "Insert Hospital Address.";
	}

	// Hospital Email
	if ($("#hospEmail").val().trim() == "") {
		return "Insert Hospital Email.";
	}
	//
	var re = /^(?:[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;
	var phone = $("#hospEmail").val().trim();
	if (re.test(phone) == false) {
		return "Please enter valid email address";
	}

	// Hospital Phone
	if ($("#hospPhone").val().trim() == "") {
		return "Insert Hospital Phone.";
	}
	// Check for numeric value
	var phone = $("#hospPhone").val().trim();
	if (!$.isNumeric(phone)) {
		return "Insert a correct conatct number (don't insert characters)";
	}
	// check for length
	var pattern = /^\d{10}$/;
	if (!pattern.test(phone)) {
		return "Contact number should have 10 numbers";
	}
	// Hospital Registered Date
	if ($("#hospRegDate").val().trim() == "") {
		return "Insert Hospital Registered Date.";
	}

	// Hospital Charge
	if ($("#hospCharge").val().trim() == "") {
		return "Insert Hospital Charge.";
	}

	// Check for numeric value
	var charge = $("#hospCharge").val().trim();
	if (!$.isNumeric(charge)) {
		return "Insert a numeric value for hospital charge";
	}
	$("#hospCharge").val(parseFloat(charge).toFixed(2));

	return true;
}