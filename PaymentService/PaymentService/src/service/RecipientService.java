package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import model.Payment;
import model.Recipient;
import repository.DBConnection;

public class RecipientService {
	
	private DBConnection connection = new DBConnection();
	
	//add recipient
	
	public Response addRecipient(Recipient recipient) {
		int insertedId = -99;
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("Database connectivity Error")
		        .build();

		      String query = "INSERT INTO recipient(account_number,bank, branch) VALUES (?, ?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		      preparedStmt.setString(1, recipient.getAccountNumber());
		      preparedStmt.setString(2, recipient.getBank());
		      preparedStmt.setString(3, recipient.getBranch());
		      
		      preparedStmt.execute();
		      ResultSet rs = preparedStmt.getGeneratedKeys();
		      if (rs.next()){
		    	  insertedId  = Integer.parseInt(rs.getString(1));
		      }
		      con.close();
		      
		      recipient.setId(insertedId);
		      recipient.setIssuedAt("A few seconds ago");
		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(recipient)
		      .build();
	}
	
	//get recipients

	public Response getRecipients() {
		List <Recipient> recipients = new ArrayList <Recipient> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();

	      String query = "select * from recipient";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String account_number = rs.getString("account_number");
	        String bank = rs.getString("bank");
	        String branch = rs.getString("branch");
	        String issuedAt = rs.getString("issuedAt");

	        Recipient recipient = new Recipient(bank,branch,account_number);
	        recipient.setIssuedAt(issuedAt);
	        recipient.setId(id);
	        recipients.add(recipient);

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
      .entity(recipients)
      .build();
	}

    
	//get recipient by id

	public Response getRecipientById(Integer recipientid) {
		Recipient recipient = null;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("Database connectivity Error")
	        .build();

	      String query = "select * from recipient where id = " + recipientid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  int id = rs.getInt("id");
		        String account_number = rs.getString("account_number");
		        String bank = rs.getString("bank");
		        String branch = rs.getString("branch");
		        String issuedAt = rs.getString("issuedAt");

		        recipient = new Recipient(bank,branch,account_number);
		        recipient.setIssuedAt(issuedAt);
		        recipient.setId(id);
		        
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
	      .entity(recipient)
	      .build();
	}
}
