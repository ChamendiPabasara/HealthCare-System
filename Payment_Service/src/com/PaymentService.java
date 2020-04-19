package com;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

import model.Payment;

@Path("/Payment")
public class PaymentService {
	
	Payment payment = new Payment();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPayment(@Pattern(regexp="^[a-z A-Z]*$", message="Can't use numbers on Card Type feild") @NotEmpty(message = "Card type can't be empty") @FormParam("cardType") String cardType,
			@NotNull(message = "Card Number can't be empty") @FormParam("cardNumber") int cardNumber,
			@Pattern(regexp="^[a-z A-Z]*$", message="Can't use numbers on Name on Card feild") @NotEmpty(message = "Name on card can't be empty") @FormParam("nameOnCard") String nameOnCard,
			@Digits(integer=3,fraction =0, message = "cvc can't be empty") @FormParam("cvc") int cvc,
			@NotNull(message = "Expire date can't be empty") @FormParam("expireDate") Date expireDate,
			@NotEmpty(message = "Status can't be empty") @FormParam("status") String status,
			@NotNull(message = "Payment Date can't be empty") @FormParam("paymentDate") Date paymentDate,
			@FormParam("appointmentId") int appointmentId) {
		return this.payment.addPayment(cardType, cardNumber, nameOnCard, cvc, expireDate, status, paymentDate, appointmentId);	
	}
	
	
	@GET
	@Path("/getById")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentById(@QueryParam("patientId") int patientId) {

		return this.payment.getPaymentByPatient(patientId);
	}
	
	
	@GET
    @Path("/get")
    @Produces(MediaType.TEXT_HTML)
    public String getAllPatmentEntry(){
        return this.payment.getAllPayment();
    }
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentById(@QueryParam("paymentId") int id ,
			@Pattern(regexp="^[a-z A-Z]*$", message="Can't use numbers on Card Type feild") @NotEmpty(message = "Card type can't be empty") @FormParam("cardType") String cardType,
			@NotNull(message = "Card Number can't be empty") @FormParam("cardNumber") int cardNumber,
			@Pattern(regexp="^[a-z A-Z]*$", message="Can't use numbers on Name on Card feild") @NotEmpty(message = "Name on card can't be empty") @FormParam("nameOnCard") String nameOnCard,
			@Digits(integer=3,fraction =0, message = "cvc can't be empty") @FormParam("cvc") int cvc,
			@NotNull(message = "Expire date can't be empty") @FormParam("expireDate") Date expireDate,
			@NotEmpty(message = "Status can't be empty") @FormParam("status") String status,
			@NotNull(message = "Payment Date can't be empty") @FormParam("paymentDate") Date paymentDate,
			@Digits(integer=5,fraction =0,message = "Appointment can't be empty") @FormParam("appointmentId") int appointmentId ) {
		
	return this.payment.updatePayment(id,cardType,cardNumber,nameOnCard,cvc,expireDate,status,paymentDate,appointmentId);
	}

}


