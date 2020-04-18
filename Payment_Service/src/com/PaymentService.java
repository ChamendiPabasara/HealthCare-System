package com;

import java.sql.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.Payment;

@Path("/Payment")
public class PaymentService {
	
	Payment payment = new Payment();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPayment(@FormParam("cardType") String cardType,
						   @FormParam("cardNumber") int cardNumber,
						   @FormParam("nameOnCard") String nameOnCard,
						   @FormParam("cvc") int cvc,
						   @FormParam("expireDate") Date expireDate,
						   @FormParam("status") String status,
						   @FormParam("paymentDate") Date paymentDate,
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
	public String updatePaymentById(@QueryParam("id") int id ,
		@FormParam("cardType") String cardType,
		@FormParam("cardNumber") int cardNumber,
		@FormParam("nameOnCard") String nameOnCard,
		@FormParam("cvc") int cvc,
		@FormParam("expireDate") Date expireDate,
		@FormParam("subAmount") double subAmount,
		@FormParam("status") String status,
		@FormParam("paymentDate") Date paymentDate,
		@FormParam("taxId") int taxId,
		@FormParam("appointmentId") int appointmentId ) {
		
	return this.payment.updatePayment(id,cardType,cardNumber,nameOnCard,cvc,expireDate,subAmount,status,paymentDate,taxId,appointmentId);
	}

}


