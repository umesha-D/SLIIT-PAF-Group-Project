package buyer;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8682 
 *http://localhost:8682/APIGateway/api/v2/favourite/*
*/
@Path("/favourite") 
public class Favourites {
	
	public boolean validate(String email, String token) {
		Object output = null;
		try {
	        Client client = Client.create();
	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/buyertokenvertify");

	        ClientResponse response = webResource.accept("application/json")
	        	.header("token", token)
	        	.header("email", email)
	            .post(ClientResponse.class);

	        output = response.getEntity(Object.class);
	        
	        if (response.getStatus() == 403) {
	        	return false;
		    }else if(response.getStatus() == 200){
		    	return true;
		    }else {
		    	return false;
		    }

	      } catch (Exception e) {
	    	  return false;
	      }
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAsAFavourite(HashMap<String, ?> Data, @Context HttpHeaders httpheaders) {
		Object output = null;
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		if(token == null || email == null) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("No token provided")
    		        .build();
		}
		
		boolean isValid = validate(email.get(0),token.get(0));
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/favourite/add");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, Data);

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
	
	
	@DELETE
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromFavourite(HashMap<String, ?> Data, @Context HttpHeaders httpheaders) {
		Object output = null;
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		if(token == null || email == null) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("No token provided")
    		        .build();
		}
		
		boolean isValid = validate(email.get(0),token.get(0));
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/favourite/remove");

	        ClientResponse response = webResource.accept("application/json")
	          .delete(ClientResponse.class, Data);

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
	@Path("/getfavouritesdetails/{buyerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavoutesDetails(@PathParam("buyerid") Integer buyerid, @Context HttpHeaders httpheaders) {
		Object output = null;
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		if(token == null || email == null) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("No token provided")
    		        .build();
		}
		
		boolean isValid = validate(email.get(0),token.get(0));
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/favourite/getfavouritesdetails/"+buyerid);

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
