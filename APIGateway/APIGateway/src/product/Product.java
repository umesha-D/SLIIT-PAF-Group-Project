package product;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
 *http://localhost:8682/APIGateway/api/v2/product/*
*/

@Path("/product") 
public class Product {
	@POST
	@Path("/addproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(HashMap<String, ?> productData) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/addproduct");

	        ClientResponse response = webResource.accept("application/json")
	          .post(ClientResponse.class, productData);

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
	@Path("/getproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		Object output = null;
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
	@Path("/getproductbyid/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyerById(@PathParam("productid") Integer productid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/"+productid);

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
	@Path("/deletebyid/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("productid") Integer productid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/deletebyid/"+productid);

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
	@Path("/getproductwithbuyer/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
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
	
	@GET
	@Path("/getproductswithdata")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsWithBuyer() {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/getproductswithdata");

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
	@Path("/update/{productid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(HashMap<String, ?> productData, @PathParam("productid") Integer productid) {
		Object output = null;
		try {

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8180/ProductService/api/v2/product/update"+productid);

	        ClientResponse response = webResource.accept("application/json")
	          .put(ClientResponse.class, productData);

	       
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
