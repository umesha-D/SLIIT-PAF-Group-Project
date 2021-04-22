package controller;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.ProductFunders;
import service.ProductFundersService;

/*
 *default Port : 8180 
 *http://localhost:8180/ProductService/api/v2/productfunders/*
*/
@Path("/productfunders") 
public class ProductFundersController {
	
	private ProductFunders productFunder;
	private ProductFundersService productfundersService = new ProductFundersService();
	
	@POST
	@Path("/addfundingbody")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFundingBody(HashMap<String, ?> fundingbodyData) {

		Long ownerIdTemp = new Long((long) fundingbodyData.get("id"));
		int ownerId = ownerIdTemp.intValue();
		
		Long funderIdTemp = new Long((long) fundingbodyData.get("funder_id"));
		int funder = funderIdTemp.intValue();
		
		productFunder = new ProductFunders(ownerId, funder);
		
		return productfundersService.addFundingBody(productFunder);
	}
	
	@GET
	@Path("/getproductwithfunder/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductWithFunder(@PathParam("productid") Integer productid) {
		return productfundersService.getProductWithFunder(productid);
	}
	
	@GET
	@Path("/getallwithfunder")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProductWithFunders() {
		return productfundersService.getAllProductWithFunders();
	}
	
	
	@GET
	@Path("/getproductwithbuyer/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductWithBuyer(@PathParam("productid") Integer productid) {
		return productfundersService.getProductWithBuyer(productid);
	}
}
