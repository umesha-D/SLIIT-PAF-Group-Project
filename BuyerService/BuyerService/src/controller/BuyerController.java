package controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Buyer;
import service.BuyerService;

/*
 *default Port : 8080 
 *http://localhost:8080/BuyerService/api/v2/buyer/*
*/
@Path("/buyer") 
public class BuyerController {
	
	private Buyer buyer;
	private BuyerService buyerService = new BuyerService();
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(HashMap<String, ?> buyerData) {
		String userName = (String) buyerData.get("userName");
		String password = (String) buyerData.get("password");
		String email = (String) buyerData.get("email");
		buyer = new Buyer(userName, password, email);
		return buyerService.register(buyer);
	}
	
	@GET
	@Path("/getbuyers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyers() {
		return buyerService.getAllbuyers();
	}

	@GET
	@Path("/getbuyerbyid/{userId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyerById(@PathParam("userId") Integer userId) {
		return buyerService.getBuyerId(userId);
	}
	
	
	@PUT
	@Path("/update/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBuyer(HashMap<String, ?> buyerData, @PathParam("userId") Integer userId) {
		String userName = (String) buyerData.get("userName");
		String password = (String) buyerData.get("password");
		String email = (String) buyerData.get("email");
		buyer = new Buyer(userName, password, email);
		buyer.setId(userId);
		return buyerService.updateBuyer(buyer);
	}
	
	@DELETE
	@Path("/deletebyid/{userId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("userId") Integer userId) {
		return buyerService.deleteBuyer(userId);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap<String, ?> Data) {
		String email = (String) Data.get("email");
		String password = (String) Data.get("password");
		return buyerService.login(email, password);
	}
	
	@POST
	@Path("/buyertokenvertify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyerTokenVertify(HashMap<String, ?> Data) {
		String token = (String) Data.get("token");
		String email = (String) Data.get("email");
		return buyerService.vertify(email, token);
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(HashMap<String, ?> Data) {
		String email = (String) Data.get("email");
		String token = (String) Data.get("token");
		return buyerService.logout(email, token);
	}
}
