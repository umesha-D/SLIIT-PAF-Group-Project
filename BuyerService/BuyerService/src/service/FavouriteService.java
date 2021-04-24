package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Buyer;
import model.Favourite;
import repository.DBConnection;

public class FavouriteService {
	private DBConnection connection = new DBConnection();

	public Response addAsAFavourite(Favourite favourite) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO favourites(userId,productId) VALUES (?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, favourite.getUserid());
		      preparedStmt.setInt(2, favourite.getProductId());

		      
		      preparedStmt.execute();
		      con.close();
		      
		      favourite.setAddedAt("A few seconds ago");
		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("Error while inserting data")
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(favourite)
		      .build();
	}

	public Response getFavoutes() {
		List <Favourite> Favoutes = new ArrayList <Favourite> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from favourites";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int userId = rs.getInt("userId");
	        int productId = rs.getInt("productId");;

	        Favourite favourite = new Favourite(userId, productId);

	        Favoutes.add(favourite);
	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Error while getting data")
	        .build();
	    }

	    return Response
	      .status(Response.Status.OK)
	      .entity(Favoutes)
	      .build();

	}

	public Response getFavoutesDetails(Integer buyerid) {
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		List<?> resultDataProduct = new ArrayList<>();
		    
		 	
		Client client = Client.create();
	      WebResource webResource = client
		          .resource("http://localhost:8180/ProductService/api/v2/product/getproducts");

		        ClientResponse response = webResource.accept("application/json")
		          .get(ClientResponse.class);

		        if (response.getStatus() != 200) {
		          throw new RuntimeException("Failed : HTTP error code : " +
		            response.getStatus());
		        }

		  Object output = response.getEntity(Object.class);
		  resultDataProduct = (List<?>)output;
		  
	        
		  try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "select * from favourites where userId = "+buyerid;
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(query);
		      
		      while (rs.next()) {
		    	    int buyerId = rs.getInt("userId");
			        int productId = rs.getInt("productId");
			        String addedAt = rs.getString("addedAt");
			        
			        Favourite favourite = new Favourite();
			        
			        favourite.setAddedAt(addedAt);
			        favourite.setUserid(buyerId);
			        favourite.setProductId(productId);
			        Map<String, Object> temp = new HashMap<String, Object>();
			        temp.put("favoutite", favourite);
			        
			        resultDataProduct
		        	.stream()
		        	.forEach(data -> {
		        		HashMap<String, ?> x = (HashMap<String, ?>) data;
		        		if(((int)(long)(x.get("id"))) == productId) {
		        			temp.put("product", data);
		        		}
		        	});
		        	res.add(temp);
		      }
		      
		  }catch (Exception e) {
		      return Response
		  	        .status(Response.Status.INTERNAL_SERVER_ERROR)
		  	        .entity(e)
		  	        .build();
		  }
		  
		  return Response
		  	        .status(Response.Status.OK)
		  	        .entity(res)
		  	        .build();
	}

	public Response removeFromFavourite(Favourite favourite) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE FROM favourites WHERE userId=? AND productId=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, favourite.getUserid());
		      preparedStmt.setInt(2, favourite.getProductId());

		      
		      preparedStmt.execute();
		      con.close();
		      
		      favourite.setAddedAt("A few seconds ago");
		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("Error while updating data")
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(favourite)
		      .build();
	}
	
}
