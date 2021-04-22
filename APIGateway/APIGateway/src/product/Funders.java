package product;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8682 
 *http://localhost:8682/APIGateway/api/v2/productfunder/*
*/
@Path("/productfunder") 
public class Funders {
	@POST
	@Path("/addfundingbody")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFundingBody(HashMap<String, ?> fundingbodyData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/productfunders/addfundingbody");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, fundingbodyData);

	        output = response.getEntity(Object.class);

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.INTERNAL_SERVER_ERROR)
	    		        .entity(e)
	    		        .build();
	      }
			return Response
			        .status(Response.Status.OK)
			        .entity(output)
			        .build();
	}
	
	@GET
	@Path("/getproductwithfunder/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductWithFunder(@PathParam("productid") Integer productid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/productfunders/getproductwithfunder/"+productid);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        if (response.getStatus() != 200) {
	          throw new RuntimeException("Failed : HTTP error code : " +
	            response.getStatus());
	        }
	        output = response.getEntity(Object.class);

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.INTERNAL_SERVER_ERROR)
	    		        .entity(e)
	    		        .build();
	      }
			return Response
			        .status(Response.Status.OK)
			        .entity(output)
			        .build();
	}
	
	@GET
	@Path("/getallwithfunder")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProductWithFunders() {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/productfunders/getallwithfunder");

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        if (response.getStatus() != 200) {
	          throw new RuntimeException("Failed : HTTP error code : " +
	            response.getStatus());
	        }
	        output = response.getEntity(Object.class);

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.INTERNAL_SERVER_ERROR)
	    		        .entity(e)
	    		        .build();
	      }
			return Response
			        .status(Response.Status.OK)
			        .entity(output)
			        .build();
	}
	
	
	@GET
	@Path("/getproductwithbuyer/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductWithBuyer(@PathParam("productid") Integer productid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/getproductwithbuyer/"+productid);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        if (response.getStatus() != 200) {
	          throw new RuntimeException("Failed : HTTP error code : " +
	            response.getStatus());
	        }
	        output = response.getEntity(Object.class);

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.INTERNAL_SERVER_ERROR)
	    		        .entity(e)
	    		        .build();
	      }
			return Response
			        .status(Response.Status.OK)
			        .entity(output)
			        .build();
	}
}
