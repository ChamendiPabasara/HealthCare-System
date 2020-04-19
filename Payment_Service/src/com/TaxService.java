package com;

import model.Tax;

import java.sql.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Path("/Tax")
public class TaxService {
	
	Tax tax = new Tax();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String addTaxEntry(@FormParam("amount")float amount, 
    		@NotNull(message = "Valid from Date can't be empty") @FormParam("validFrom") Date validFrom, 
    		@NotNull(message = "Valid to Date can't be empty") @FormParam("validTo") Date validTo){
        return this.tax.addTaxEntry(amount, validFrom, validTo);
    }
    
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_HTML)
    public String getAllTaxEntry(){
        return this.tax.getAllTaxEntry();
    }

    @GET
    @Path("/getById")
    @Produces(MediaType.TEXT_HTML)
    public String getTaxEntryById(@QueryParam("id") int id){
        return this.tax.getTaxEntryById(id);
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTaxEntryById(@QueryParam("id") int id, 
    								@FormParam("amount") float amount, 
    								@NotNull(message = "Valid from Date can't be empty") @FormParam("validFrom") Date validFrom,
    								@NotNull(message = "Valid to Date can't be empty") @FormParam("validTo") Date validTo){
    	
        return this.tax.updateTaxEntryById(id, amount, validFrom, validTo );
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTaxEntryById(@QueryParam("id") int id){
        return this.tax.deleteTaxEntryById(id);
    }

}
