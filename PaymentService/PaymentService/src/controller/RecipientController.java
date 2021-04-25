package controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Recipient;
import service.RecipientService;

/*
 *default Port : 8081 
 *http://localhost:8081/PaymentService/api/v2/recipient/*
*/


@Path("/recipient") 
public class RecipientController {
	
	private Recipient recipient;
	private RecipientService recipientService = new RecipientService();
	
	@POST
	@Path("/addrecipient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRecipient(HashMap<String, ?> recipientData) {
		String account_number = (String) recipientData.get("account_number");
		String bank = (String) recipientData.get("bank");
		String branch = (String) recipientData.get("branch");
		
		recipient = new Recipient(bank, branch, account_number);
		
		return recipientService.addRecipient(recipient);
	}
	
	@GET
	@Path("/getrecipients")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecipients() {
		return recipientService.getRecipients();
	}
	
	@GET
	@Path("/getrecipientbyid/{recipientid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecipientById(@PathParam("recipientid") Integer recipientid) {
		return recipientService.getRecipientById(recipientid);
	}
	
	
}
