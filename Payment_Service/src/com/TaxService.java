package com;

import model.Tax;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Tax")
public class TaxService {
	
	Tax tax = new Tax();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String addTaxEntry(@FormParam("amount")float amount){
        return this.tax.addTaxEntry(amount);
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
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTaxEntryById(@QueryParam("id") int id, @FormParam("amount") float amount){
        return this.tax.updateTaxEntryById(id, amount);
    }
    
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTaxEntryById(@QueryParam("id") int id){
        return this.tax.deleteTaxEntryById(id);
    }

}
