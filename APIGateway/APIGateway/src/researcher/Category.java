package researcher;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8682 
 *http://localhost:8682/APIGateway/api/v2/researchcategory/*
*/
@Path("/researchcategory") 
public class Category {
	@POST
	@Path("/addcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(HashMap<String, ?> categoryData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/category/addcategory");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, categoryData);

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
	@Path("/update/{categoryid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(HashMap<String, ?> categoryData, @PathParam("categoryid") Integer categoryid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/category/update");

	        ClientResponse response = webResource.accept("application/json")
	          .put(ClientResponse.class, categoryData);

	       
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
	@Path("/getcategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/category/getcategories");

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
	@Path("/getcategory/{categorytid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryById(@PathParam("categorytid") Integer categorytid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/category/getcategory/"+categorytid);

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
	

	@DELETE
	@Path("/deletebyid/{categorytid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("categorytid") Integer categorytid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/category/deletebyid/"+categorytid);

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

}
