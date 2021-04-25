package researcher;


import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/*
 *default Port : 8682 
 *http://localhost:8682/APIGateway/api/v2/review/*
*/
@Path("/review") 
public class Review {
	@POST
	@Path("/sumbit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReview(HashMap<String, ?> reviewData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/sumbit");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, reviewData);

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
	@Path("/getreviews")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReviews() {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/getreviews");

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        output = response.getEntity(Object.class);

	      } catch (ClientHandlerException | UniformInterfaceException e) {
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
	@Path("/getreview/{reviewid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getreviewById(@PathParam("reviewid") Integer reviewid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/getreview/"+reviewid);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        output = response.getEntity(Object.class);

	      } catch (ClientHandlerException | UniformInterfaceException e) {
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
	@Path("/deletebyid/{reviewid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("reviewid") Integer reviewid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/deletebyid/"+reviewid);

	        ClientResponse response = webResource.accept("application/json")
	          .delete(ClientResponse.class);

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
	@Path("/getreiewwithdata/{reviewid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReviewwithData(@PathParam("reviewid") Integer reviewid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/getreiewwithdata/"+reviewid);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

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
	@Path("/getreiewwithdata/user/{researcherid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReviewwithDataUser(@PathParam("researcherid") Integer researcherid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8090/ResearcherService/api/v2/review/getreiewwithdata/user/"+researcherid);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

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
