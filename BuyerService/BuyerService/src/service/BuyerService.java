package service;

import java.sql.Connection;
import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import model.Buyer;
import repository.DBConnection;

public class BuyerService {

  private DBConnection connection = new DBConnection();

  public Response register(Buyer buyer) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String query = "INSERT INTO buyer(userName,password, email) VALUES (?, ?, ?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setString(1, buyer.getUserName());
      preparedStmt.setString(2, buyer.getPassword());
      preparedStmt.setString(3, buyer.getEmail());
      
      preparedStmt.execute();
      con.close();
      
      buyer.setRegiteredAt("A few seconds ago");
    } catch (Exception e) {
      return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Error while inserting data")
        .build();
    }
    return Response
      .status(Response.Status.CREATED)
      .entity(buyer)
      .build();
  }

  public Response getAllbuyers() {
    List < Buyer > buyers = new ArrayList < Buyer > ();

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String query = "select * from buyer";
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String regiteredAt = rs.getString("regiteredAt");
        Buyer buyer = new Buyer(userName, password, email);
        buyer.setId(id);
        buyer.setRegiteredAt(regiteredAt);
        buyers.add(buyer);
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
      .entity(buyers)
      .build();

  }

  public Response getBuyerId(int userId) {
    Buyer buyer = null;
    
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String query = "select * from buyer where id = " + userId;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String regiteredAt = rs.getString("regiteredAt");
        String updatedAt = rs.getString("updatedAt");
        buyer = new Buyer(userName, password, email);
        buyer.setId(id);
        buyer.setRegiteredAt(regiteredAt);
        buyer.setUpdatedAt(updatedAt);
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
      .entity(buyer)
      .build();

  }
  
  public Response updateBuyer(Buyer buyerData) {
	  try
	  {
		  Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();
	  
	  String query = "UPDATE buyer SET userName=?,password=?,email=?,updatedAt=CURRENT_TIMESTAMP WHERE id=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	  preparedStmt.setString(1, buyerData.getUserName());
	  preparedStmt.setString(2, buyerData.getPassword());
	  preparedStmt.setString(3, buyerData.getEmail());
	  preparedStmt.setInt(4, buyerData.getId());
	  
	  preparedStmt.execute();
	  con.close();
	  buyerData.setUpdatedAt("A few seconds ago");
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
		      .entity(buyerData)
		      .build();
  }
  
  public Response deleteBuyer(int userId) {
	  try
	  {
		  Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();
	  
	  String query = "DELETE from buyer WHERE id=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	  preparedStmt.setInt(1, userId);
	  
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
		      .status(Response.Status.OK)
		      .entity("Succesfully Deleted the buyer")
		      .build();
  }

	public Response login(String emaill, String password) {
		String currentPassword = "";
	    
	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from buyer where email = '"+ emaill +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	         currentPassword = rs.getString("password");
	      }
 
	      
	      if(!currentPassword.equals(password)) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid credetials")
	    		        .build();
	      }else {
	    	  Random rand = new Random();
	    	  double int_random = rand.nextDouble() * 3946765F; 
	    	  
	    	  String createToken = "UPDATE buyer SET token = ?,updatedAt=CURRENT_TIMESTAMP WHERE email=?";
	    	  PreparedStatement preparedStmt = con.prepareStatement(createToken);
	    	 
	    	  preparedStmt.setString(1, String.valueOf(int_random));
	    	  preparedStmt.setString(2, emaill);

	    	  preparedStmt.execute();
	    	  con.close();
	    	  
	    	  Map<String, String> tokenResult = new HashMap<String, String>(); 
	    	  tokenResult.put("token",  String.valueOf(int_random)+emaill);
	    	  tokenResult.put("metadata",  "Add email and token in seperate headers when making requests.");
	    	  
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity(tokenResult)
	    		        .build(); 
	      }
	      
	      

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	   }
	}


	public Response vertify(String email, String token) {
		String tokenFromDB = "";
	    
	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.FORBIDDEN)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from buyer where email = '"+ email +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  tokenFromDB = rs.getString("token");
	      }
 
	      
	      if(tokenFromDB.equals(token)) {
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity("authenticated")
	    		        .build(); 
	      }else {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid token")
	    		        .build();
	      }
	      
	      

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.FORBIDDEN)
	        .entity(e)
	        .build();
	   }
	}

	public Response logout(String email, String token) {
		String currentToken = "";
	    
	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from buyer where email = '"+ email +"'";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	         currentToken = rs.getString("token");
	      }
 
	      
	      if(!currentToken.equals(token)) {
	    	  return Response
	    		        .status(Response.Status.FORBIDDEN)
	    		        .entity("Invalid credetials")
	    		        .build();
	      }else {
	    	  Random rand = new Random();
	    	  
	    	  String createToken = "UPDATE buyer SET token = ?,updatedAt=CURRENT_TIMESTAMP WHERE email=?";
	    	  PreparedStatement preparedStmt = con.prepareStatement(createToken);
	    	 
	    	  preparedStmt.setString(1, null);
	    	  preparedStmt.setString(2, email);

	    	  preparedStmt.execute();
	    	  con.close();
	    	  
	    	  Map<String, String> tokenResult = new HashMap<String, String>(); 
	    	  tokenResult.put("status",  "logout succesfully");
	    	  
	    	  return Response
	    		        .status(Response.Status.OK)
	    		        .entity(tokenResult)
	    		        .build(); 
	      }
	      
	      

	    } catch (Exception e) {
	      return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity(e)
	        .build();
	   }
	}
}