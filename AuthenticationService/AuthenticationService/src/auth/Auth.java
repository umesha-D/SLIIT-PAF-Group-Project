package auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8082 
 *http://localhost:8082/AuthenticationService/api/v2/auth/*
*/
@Path("/auth") 
public class Auth {
	
	@POST
	@Path("/buyerlogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyerLogin(HashMap<String, ?> buyerData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/login");

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
	@Path("/buyertokenvertify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyerTokenVertify(@Context HttpHeaders httpheaders) {
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		Map < String, Object > res = new HashMap < String, Object > ();
		
		res.put("token", token.get(0));
		res.put("email", email.get(0));

		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/buyertokenvertify");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, res);

	        output = response.getEntity(Object.class);
	        
	        if (response.getStatus() == 403) {
	        	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }else if(response.getStatus() == 200){
		    	return Response
	    		        .status(Response.Status.OK)
	    		        .entity("authorized")
	    		        .build();
		    }else {
		    	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("unauthorized access")
	    		        .build();
	      }	
	}
	
	@POST
	@Path("/buyerlogout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyerLogout(HashMap<String, ?> buyerData) {
		Object output = null;
		try {
	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/logout");

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
	@Path("/researcherlogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response researcherLogin(HashMap<String, ?> researcherData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/login");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, researcherData);

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
	@Path("/researchertokenvertify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response researcherTokenVertify(@Context HttpHeaders httpheaders) {
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		Map < String, Object > res = new HashMap < String, Object > ();
		
		res.put("token", token.get(0));
		res.put("email", email.get(0));

		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/researchertokenvertify");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, res);

	        output = response.getEntity(Object.class);
	        
	        if (response.getStatus() == 403) {
	        	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }else if(response.getStatus() == 200){
		    	return Response
	    		        .status(Response.Status.OK)
	    		        .entity("authorized")
	    		        .build();
		    }else {
		    	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("unauthorized access")
	    		        .build();
	      }	
	}
	
	@POST
	@Path("/researcherlogout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response researcherLogout(HashMap<String, ?> researcherData) {
		Object output = null;
		try {
	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/logout");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, researcherData);

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
	@Path("/fbodylogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fbodyLogin(HashMap<String, ?> Data) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/login");

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
	@Path("/fbodytokenvertify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fbodyTokenVertify(@Context HttpHeaders httpheaders) {
		List<String> token = httpheaders.getRequestHeader("token");
		List<String> email = httpheaders.getRequestHeader("email");
		Map < String, Object > res = new HashMap < String, Object > ();
		
		res.put("token", token.get(0));
		res.put("email", email.get(0));

		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/fbodytokenvertify");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, res);

	        output = response.getEntity(Object.class);
	        
	        if (response.getStatus() == 403) {
	        	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }else if(response.getStatus() == 200){
		    	return Response
	    		        .status(Response.Status.OK)
	    		        .entity("authorized")
	    		        .build();
		    }else {
		    	return Response
				        .status(Response.Status.FORBIDDEN)
				        .entity("unauthorized access")
				        .build();
		    }

	      } catch (Exception e) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("unauthorized access")
	    		        .build();
	      }	
	}
	
	@POST
	@Path("/fbodylogout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fbodyLogout(HashMap<String, ?> Data) {
		Object output = null;
		try {
	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/logout");

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
	

	
}
