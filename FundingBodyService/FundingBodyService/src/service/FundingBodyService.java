package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import model.FundingBody;
import repository.DBConnection;

public class FundingBodyService {

  private DBConnection connection = new DBConnection();

  public Response register(FundingBody fundingBody) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();
		
		

      String query = "INSERT INTO fbody(userName,password, email) VALUES (?, ?, ?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setString(1, fundingBody.getUserName());
      preparedStmt.setString(2, fundingBody.getPassword());
      preparedStmt.setString(3, fundingBody.getEmail());
      
      preparedStmt.execute();
      con.close();
      
      fundingBody.setRegiteredAt("A few seconds ago");
    } catch (Exception e) {
      return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Error while inserting data")
        .build();
    }
    return Response
      .status(Response.Status.CREATED)
      .entity(fundingBody)
      .build();
  
}



public Response getAllfbodies() {
    List <FundingBody> fundingBody = new ArrayList <FundingBody> ();

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "select * from fbody";
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String regiteredAt = rs.getString("regiteredAt");
        FundingBody fbody = new FundingBody(userName, password, email);
        fbody.setId(id);
        fbody.setRegiteredAt(regiteredAt);
        fundingBody.add(fbody);
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
      .entity(fundingBody)
      .build();

  }
  
  

  public Response getFBodyById(int fbodyId) {
    FundingBody fundingBody = null;
    
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "select * from fbody where id = " + fbodyId;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String regiteredAt = rs.getString("regiteredAt");
        String updatedAt = rs.getString("updatedAt");
        fundingBody = new FundingBody(userName, password, email);
        fundingBody.setId(id);
        fundingBody.setRegiteredAt(regiteredAt);
        fundingBody.setUpdatedAt(updatedAt);
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
      .entity(fundingBody)
      .build();

  }
  
  
  
  public Response updateFundingBody(FundingBody fundingBody) {
	  try
	  {
		  Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();
	  
	  String query = "UPDATE fbody SET userName=?,password=?,email=?,updatedAt=CURRENT_TIMESTAMP WHERE id=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	  preparedStmt.setString(1, fundingBody.getUserName());
	  preparedStmt.setString(2, fundingBody.getPassword());
	  preparedStmt.setString(3, fundingBody.getEmail());
	  preparedStmt.setInt(4, fundingBody.getId());
	  
	  preparedStmt.execute();
	  con.close();
	  fundingBody.setUpdatedAt("A few seconds ago");
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
		      .entity(fundingBody)
		      .build();
  }
  
  
  
  public Response deleteFBody(int userId) {
	  try
	  {
		  Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();
	  
	  String query = "DELETE from fbody WHERE id=?";
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
		      .entity("Succesfully Deleted the funding body")
		      .build();
  }
  
  
}