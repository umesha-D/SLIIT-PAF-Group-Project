package fundingbody;

import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;
=======
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
<<<<<<< HEAD
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
=======
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8682 
 *http://localhost:8682/APIGateway/api/v2/fbody/*
*/
<<<<<<< HEAD
@Path("/fbody") 
public class FundingBody {
	
	public boolean validate(String email, String token) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/fbodytokenvertify");

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
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap<String, ?> Data) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/fbodylogin");

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
		if(!isValid) {
			return Response
    		        .status(Response.Status.FORBIDDEN)
    		        .entity("Unauthrized access")
    		        .build();
		}
		
		
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8082/AuthenticationService/api/v2/auth/fbodylogout");

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
	
=======


@Path("/fbody") 
public class FundingBody {
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(HashMap<String, ?> fbodyData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/register");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, fbodyData);

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
	@Path("/getfbodys")
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public Response getAllfbodies(@Context HttpHeaders httpheaders) {
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
=======
	public Response getAllfbodies() {
		Object output = null;
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/getfbodys");

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
	@Path("/getfbodybyid/{fbodyidId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public Response getFBodyById(@PathParam("fbodyidId") Integer fbodyidId, @Context HttpHeaders httpheaders) {
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
=======
	public Response getFBodyById(@PathParam("fbodyidId") Integer fbodyidId) {
		Object output = null;
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/getfbodybyid/"+fbodyidId);

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
	@Path("/update/{fbodyidId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public Response updateBuyer(HashMap<String, ?> fbodyData, @PathParam("fbodyidId") Integer fbodyidId, @Context HttpHeaders httpheaders) {
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
		
=======
	public Response updateBuyer(HashMap<String, ?> fbodyData, @PathParam("fbodyidId") Integer fbodyidId) {
		Object output = null;
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/update"+fbodyidId);

	        ClientResponse response = webResource.accept("application/json")
	          .put(ClientResponse.class, fbodyData);

	       
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
	@Path("/deletebyid/{fbodyidId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public Response deleteById(@PathParam("fbodyidId") Integer fbodyidId, @Context HttpHeaders httpheaders) {
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
		
=======
	public Response deleteById(@PathParam("fbodyidId") Integer fbodyidId) {
		Object output = null;
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/deletebyid"+fbodyidId);

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
	
<<<<<<< HEAD
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
	
	@POST
	@Path("/fundproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fundProduct(HashMap<String, ?> fundingbodyData, @Context HttpHeaders httpheaders) {
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
=======
>>>>>>> b89e1ab6e6c7003368121754fa21839e60eed5a0
	
	
}
