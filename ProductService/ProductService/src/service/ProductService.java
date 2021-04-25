package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.Product;
import repository.DBConnection;

public class ProductService {
	private DBConnection connection = new DBConnection();

	public Response addProduct(Product product) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("Database connectivity Error")
		        .build();

		      String query = "INSERT INTO product(product_name,product_price, owner_id,is_completed, category_id) VALUES (?, ?, ?, ?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setString(1, product.getProductName());
		      preparedStmt.setDouble(2, product.getProductPrice());
		      preparedStmt.setInt(3, product.getOwnerId());
		      preparedStmt.setBoolean(4, product.isCompleted());
		      preparedStmt.setInt(5, product.getCategoryId());
		      
		      preparedStmt.execute();
		      con.close();

		      product.setCreated_at("A few seconds ago");
		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(product)
		      .build();

	}

	public Response getProducts() {
		List<Product> products = new ArrayList<Product> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();

	      String query = "select * from product";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String product_name = rs.getString("product_name");
	        double product_price = rs.getDouble("product_price");
	        int owner_id = rs.getInt("owner_id");
	        String created_at = rs.getString("created_at");
	        String updated_at = rs.getString("updated_at");
	        boolean is_completed = rs.getBoolean("is_completed");
	        int category_id = rs.getInt("category_id");
	        Product product = new Product(product_name, product_price, owner_id, is_completed, category_id);
	        product.setCreated_at(created_at);
	        product.setId(id);
	        product.setUpdated_at(updated_at);
	        products.add(product);

	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	    }

	    return Response
	      .status(Response.Status.OK)
	      .entity(products)
	      .build();
	}

	public Response getBuyerById(Integer productid) {
		Product product = null;
		int buyer_id = -99;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();

	      String query = "select * from product where id = " + productid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String product_name = rs.getString("product_name");
		        double product_price = rs.getDouble("product_price");
		        int owner_id = rs.getInt("owner_id");
		        buyer_id = rs.getInt("buyer_id");
		        String created_at = rs.getString("created_at");
		        String updated_at = rs.getString("updated_at");
		        boolean is_completed = rs.getBoolean("is_completed");
		        int category_id = rs.getInt("category_id");
		        product = new Product(product_name, product_price, owner_id, is_completed, category_id);
		        product.setCreated_at(created_at);
		        product.setId(id);
		        product.setUpdated_at(updated_at);
		        product.setBuyerId(buyer_id);
	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	    }

	    return Response
	      .status(Response.Status.OK)
	      .entity(product)
	      .build();
	}

	public Response deleteProduct(Integer productid) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from product WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, productid);

		      preparedStmt.execute();
		      con.close();

		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }

		    return Response
		      .status(Response.Status.OK)
		      .entity("Product is deleted successfully")
		      .build();
	}

	public Response buyProduct(Integer productId, Integer buyerid) {
		try
		  {
			  Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();
		  
		  String query = "UPDATE product SET buyer_id=?,updated_at=CURRENT_TIMESTAMP WHERE id=? AND buyer_id IS NULL";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		  preparedStmt.setInt(1, buyerid);
		  preparedStmt.setInt(2, productId);
		  
		  preparedStmt.execute();
		  con.close();

		  }
		  catch (Exception e)
		  {
			  return Response
				        .status(Response.Status.INTERNAL_SERVER_ERROR)
				        .entity("Buying product is failed")
				        .build();
		  }
		  
		  return Response
			      .status(Response.Status.CREATED)
			      .entity("Purchase successfull")
			      .build();
	}

	public Response getProductWithBuyer(Integer productid) {
		Product product = null;
	    Map < String, Object > res = new HashMap < String, Object > ();
	    int buyer_id = -99;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from product where id = " + productid;
	      Statement statement = con.createStatement();
	      ResultSet rs = statement.executeQuery(query);

	      while (rs.next()) {
	    	    int id = rs.getInt("id");
		        String product_name = rs.getString("product_name");
		        double product_price = rs.getDouble("product_price");
		        int owner_id = rs.getInt("owner_id");
		        buyer_id = rs.getInt("buyer_id");
		        String created_at = rs.getString("created_at");
		        String updated_at = rs.getString("updated_at");
		        boolean is_completed = rs.getBoolean("is_completed");
		        int category_id = rs.getInt("category_id");
		        product = new Product(product_name, product_price, owner_id, is_completed, category_id);
		        product.setCreated_at(created_at);
		        product.setBuyerId(buyer_id);
		        product.setId(id);
		        product.setUpdated_at(updated_at);
	      }

	      res.put("product", product);

	        Client client = Client.create();

	        WebResource webResource = client
	          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyerbyid/" + buyer_id);

	        ClientResponse response = webResource.accept("application/json")
	          .get(ClientResponse.class);

	        if (response.getStatus() != 200) {
	          throw new RuntimeException("Failed : HTTP error code : " +
	            response.getStatus());
	        }

	        Object output = response.getEntity(Object.class);

	        res.put("Buyer", output);

	      con.close();

	    } catch (Exception e) {
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

	public Response getProductsWithData() {
		
	    List<Map < String, Object >> result = new ArrayList<Map<String,Object>>();
	    List<?> resultData = new ArrayList<>();
	    List<?> resultDataResearcher = new ArrayList<>();
	    
	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from product";
	      Statement statement = con.createStatement();
	      ResultSet resultset = statement.executeQuery(query);
	      
	      Client client = Client.create();
	      WebResource webResource = client
		          .resource("http://localhost:8080/BuyerService/api/v2/buyer/getbuyers/");

		        ClientResponse response = webResource.accept("application/json")
		          .get(ClientResponse.class);

		        if (response.getStatus() != 200) {
		          throw new RuntimeException("Failed : HTTP error code : " +
		            response.getStatus());
		        }

		  Object output = response.getEntity(Object.class);
		  resultData = (List<?>)output;
		  
	      WebResource webResource2 = client
		          .resource("http://localhost:8090/ResearcherService/api/v2/researcher/getresearchers");

		        ClientResponse response2 = webResource2.accept("application/json")
		          .get(ClientResponse.class);

		        if (response2.getStatus() != 200) {
		          throw new RuntimeException("Failed : HTTP error code : " +
		            response.getStatus());
		        }

		  Object output2 = response2.getEntity(Object.class);
		  resultDataResearcher = (List<?>)output2;
		  
	      

	      while (resultset.next()) {
	    	    int id = resultset.getInt("id");
		        String product_name = resultset.getString("product_name");
		        double product_price = resultset.getDouble("product_price");
		        int owner_id = resultset.getInt("owner_id");
		        int buyer_id = resultset.getInt("buyer_id");
		        String created_at = resultset.getString("created_at");
		        String updated_at = resultset.getString("updated_at");
		        boolean is_completed = resultset.getBoolean("is_completed");
		        int category_id = resultset.getInt("category_id");
		        Product product = new Product(product_name, product_price, owner_id, is_completed, category_id);
		        product.setCreated_at(created_at);
		        product.setBuyerId(buyer_id);
		        product.setId(id);
		        product.setUpdated_at(updated_at);
		        
		        Map < String, Object > res = new HashMap < String, Object > ();
		        res.put("pruduct", product);
		        
		        resultData
		        	.stream()
		        	.forEach(data -> {
		        		HashMap<String, ?> x = (HashMap<String, ?>) data;
		        		if(((int)(long)(x.get("id"))) == buyer_id) {
		        			res.put("buyer", data);
		        		}
		        	});
		        	
		        if(!res.containsKey("buyer")) {
		        	res.put("buyer", null);
		        }
		        
		        resultDataResearcher
	        	.stream()
	        	.forEach(data -> {
	        		HashMap<String, ?> x = (HashMap<String, ?>) data;
	        		if(((int)(long)(x.get("id"))) == owner_id) {
	        			res.put("owner", data);
	        		}
	        	});
	        	
		        result.add(res);
	      }
	      con.close();

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	    }
	    return Response
	      .status(Response.Status.OK)
	      .entity(result)
	      .build();
	}

	public Response updateProduct(Product product) {
		try
		  {
			  Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();
		  
		  String query = "UPDATE product SET product_name=?,product_price=?,updated_at=CURRENT_TIMESTAMP WHERE id=?";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		  preparedStmt.setString(1, product.getProductName());
		  preparedStmt.setDouble(2, product.getProductPrice());
		  preparedStmt.setInt(3, product.getId());
		  product.setCreated_at("few seconds ago");
		  preparedStmt.execute();
		  con.close();
		  }
		  catch (SQLException e)
		  {
			  return Response
				        .status(Response.Status.INTERNAL_SERVER_ERROR)
				        .entity("Error while updating the product")
				        .build();
		  }
		  
		  return Response
			      .status(Response.Status.CREATED)
			      .entity(product)
			      .build();
	}

}
