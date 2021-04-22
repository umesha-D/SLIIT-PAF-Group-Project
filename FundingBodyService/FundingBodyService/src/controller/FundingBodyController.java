package controller;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.FundingBody;
import service.FundingBodyService;


@Path("/fbody") 
public class FundingBodyController {
	
	private FundingBody fundingBody;
	private FundingBodyService fundingBodyService = new FundingBodyService();
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(HashMap<String, ?> buyerData) {
		String userName = (String) buyerData.get("userName");
		String password = (String) buyerData.get("password");
		String email = (String) buyerData.get("email");
		FundingBody fundingBody  = new FundingBody(userName, password, email);
		return fundingBodyService.register(fundingBody);
	}
	
	
	@GET
	@Path("/getfbodys")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllfbodies() {
		return fundingBodyService.getAllfbodies();
	}
	
	
	
	@GET
	@Path("/getfbodybyid/{fbodyidId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFBodyById(@PathParam("fbodyidId") Integer fbodyidId) {
		return fundingBodyService.getFBodyById(fbodyidId);
	}
	
	
	@PUT
	@Path("/update/{fbodyidId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBuyer(HashMap<String, ?> fbodyData, @PathParam("fbodyidId") Integer fbodyidId) {
		String userName = (String) fbodyData.get("userName");
		String password = (String) fbodyData.get("password");
		String email = (String) fbodyData.get("email");
		fundingBody = new FundingBody(userName, password, email);
		fundingBody.setId(fbodyidId);
		return fundingBodyService.updateFundingBody(fundingBody);
	}
	
	
	
	@DELETE
	@Path("/deletebyid/{fbodyidId}" )
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("fbodyidId") Integer fbodyidId) {
		return fundingBodyService.deleteFBody(fbodyidId);
	}
	

	
	
}
