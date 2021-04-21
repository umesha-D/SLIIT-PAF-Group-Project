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

import model.Product;
import repository.DBConnection;

public class ProductService {
	private DBConnection connection = new DBConnection();

	public Response addProduct(Product product) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
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
	        .entity("DataBase connectivity Error")
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
	        .entity("DataBase connectivity Error")
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
		      .entity("Succesfully Delected the payment data")
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
				        .entity("Error while updating the item")
				        .build();
		  }
		  
		  return Response
			      .status(Response.Status.CREATED)
			      .entity("Purchase succesfull")
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
		        product.setBuyerId(buyer_id);
		        product.setId(id);
		        product.setUpdated_at(updated_at);
	      }

	      res.put("product", product);

	      try {

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

	      } catch (Exception e) {

	        e.printStackTrace();

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
	      .entity(res)
	      .build();
	}

}
