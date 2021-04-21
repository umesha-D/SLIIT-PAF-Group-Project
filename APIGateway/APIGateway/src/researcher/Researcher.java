package researcher;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/researcher") 
public class Researcher {
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addResearcher(HashMap<String, ?> researcherData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/register/");

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
	
	
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearchers() {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/getresearchers/");

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
	@Path("/getresearcher/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherById(@PathParam("researcherid") Integer researcherid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/getresearcher/"+researcherid);

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
	@Path("/deletebyid/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("researcherid") Integer researcherid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/deletebyid/"+researcherid);

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
