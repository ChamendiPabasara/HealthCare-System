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
						   @FormParam("subAmount") double subAmount,
						   @FormParam("paymentDate") Date paymentDate,
						   @FormParam("taxId") int taxId,
						   @FormParam("appointmentId") int appointmentId) {
		return this.payment.addPayment(cardType, cardNumber, nameOnCard, cvc, expireDate, status, subAmount, paymentDate, taxId, appointmentId);
		
	}
	
	@GET
	@Path("/getById")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentById(@QueryParam("id") int id) {

		return this.payment.getPaymentByPatient(id);
	}
	
	@GET
    @Path("/get")
    @Produces(MediaType.TEXT_HTML)
    public String getAllPatmentEntry(){
        return this.payment.getAllPayment();
    }	

}
