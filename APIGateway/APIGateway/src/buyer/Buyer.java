package buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
 *http://localhost:8682/APIGateway/api/v2/buyer/*
*/
@Path("/buyer") 
public class Buyer {	
	public boolean validate(String email, String token) {
		Object output = null;
		try {
<<<<<<< HEAD
=======

>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
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
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(HashMap<String, ?> buyerData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/register");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, buyerData);

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
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap<String, ?> Data) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/buyerlogin");

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
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@Context HttpHeaders httpheaders) {
		Object output = null;
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		Map<String, Object> Data = new HashMap<String, Object>();

		if(token == null || email == null) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("No token provided")
    		        .build();
		}
		boolean isValid = validate(email.get(0),token.get(0));
		Data.put("email", email.get(0));
		Data.put("token", token.get(0));
		
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/buyerlogout");

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
	
	@GET
	@Path("/getbuyers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyers(@Context HttpHeaders httpheaders) {
		Object output = null;
<<<<<<< HEAD
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
=======
//		List<String> token = httpheaders.getRequestHeader("token");
//		List<String> email = httpheaders.getRequestHeader("email");
//		if(token == null || email == null) {
//			return Response
//    		        .status(Response.Status.FORBIDDEN)
//    		        .entity("No token provided")
//    		        .build();
//		}
//		boolean isValid = validate(email.get(0),token.get(0));
//		
//		if(!isValid) {
//			return Response
//    		        .status(Response.Status.FORBIDDEN)
//    		        .entity("Unauthrized access")
//    		        .build();
//		}
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyers");

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
	@Path("/getbuyerbyid/{userId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyerById(@PathParam("userId") Integer userId) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyerbyid/"+userId);

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
	
	
	@PUT
	@Path("/update/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBuyer(HashMap<String, ?> buyerData, @PathParam("userId") Integer userId, @Context HttpHeaders httpheaders) {
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
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/update/"+userId);

	        ClientResponse response = webResource.accept("application/json")
	          .put(ClientResponse.class, buyerData);

	       
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
	@Path("/deletebyid/{userId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("userId") Integer deletebyid, @Context HttpHeaders httpheaders) {
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
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/deletebyid/"+deletebyid);

	        ClientResponse response = webResource.accept("application/json")
	          .delete(ClientResponse.class);

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
	@Path("/viewproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@Context HttpHeaders httpheaders) {
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
	          .resource("http://localhost:8180/ProductService/api/v2/product/getproducts");

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
	@Path("/buyproduct/{productId}/{buyerid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyProduct(@PathParam("productId") Integer productId, @PathParam("buyerid") Integer buyerid, @Context HttpHeaders httpheaders) {
		Object output = null;
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		boolean isValid = validate(email.get(0),token.get(0));
		
		if(token == null || email == null) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("No token provided")
    		        .build();
		}
		
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/buyproduct/"+productId+"/"+buyerid);

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
