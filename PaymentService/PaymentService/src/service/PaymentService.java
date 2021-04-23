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

import model.Payment;
import repository.DBConnection;

public class PaymentService {

  private DBConnection connection = new DBConnection();

//Add Payment

  public Response addPayment(Payment payment) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "INSERT INTO payement(recipient_id,total, payment_method,researcher_id, buyer_id,fundingbody_id) VALUES (?, ?, ?, ?, ?,?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setInt(1, payment.getRecipientId());
      preparedStmt.setDouble(2, payment.getTotal());
      preparedStmt.setString(3, payment.getPaymentMethod());
      preparedStmt.setInt(4, payment.getResearcheId());
      preparedStmt.setInt(5, payment.getBuyerId());
      preparedStmt.setInt(6, payment.getFundingbodyId());
      

      preparedStmt.execute();
      con.close();

      payment.setPaidAt("A few seconds ago");
    } catch (Exception e) {
      return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(e)
        .build();
    }
    return Response
      .status(Response.Status.CREATED)
      .entity(payment)
      .build();

  }
  
  
  //Delete Payment

  public Response deletePayment(int paymentId) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "DELETE from payement WHERE id=?";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setInt(1, paymentId);

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
      .entity("Succesfully Deleted the payment data")
      .build();
  }

  //Get All Payments

  public Response getAllPayments() {
    List < Payment > payments = new ArrayList < Payment > ();

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "select * from payement";
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        int recipient_id = rs.getInt("recipient_id");
        double total = rs.getDouble("total");
        String payment_method = rs.getString("payment_method");
        String paidAt = rs.getString("paidAt");
        int researcher_id = rs.getInt("researcher_id");
        int buyer_id = rs.getInt("buyer_id");
        int fundingbody_id = rs.getInt("fundingbody_id");
        Payment payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id,fundingbody_id);
        payment.setPaidAt(paidAt);
        payment.setId(id);
        payments.add(payment);

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
      .entity(payments)
      .build();

  }
  
  // Get Payment By Id

  public Response getPaymentById(int paymentid) {
    Payment payment = null;

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "select * from payement where id = " + paymentid;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        int recipient_id = rs.getInt("recipient_id");
        double total = rs.getDouble("total");
        String payment_method = rs.getString("payment_method");
        String paidAt = rs.getString("paidAt");
        int researcher_id = rs.getInt("researcher_id");
        int buyer_id = rs.getInt("buyer_id");
        int fundingbody_id = rs.getInt("fundingbody_id");
        payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id, fundingbody_id);
        payment.setId(id);
        payment.setPaidAt(paidAt);
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
      .entity(payment)
      .build();

  }

//Payment with users

  public Response getPaymentWithUser(Integer paymentid) {

    Payment payment = null;
    Map < String, Object > res = new HashMap < String, Object > ();
    int buyer_id = -99;
    int funder_id = -99;

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("Database connectivity Error")
        .build();

      String query = "select * from payement where id = " + paymentid;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        int recipient_id = rs.getInt("recipient_id");
        double total = rs.getDouble("total");
        String payment_method = rs.getString("payment_method");
        String paidAt = rs.getString("paidAt");
        int researcher_id = rs.getInt("researcher_id");
        buyer_id = rs.getInt("buyer_id");
        int fundingbody_id = rs.getInt("fundingbody_id");
        payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id, fundingbody_id);
        payment.setId(id);
        payment.setPaidAt(paidAt);
      }

      res.put("Payment", payment);

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
        
        
        WebResource webResourceForFunder = client
                .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/getfbodybyid/" + funder_id);

              ClientResponse responseForFunder = webResourceForFunder.accept("application/json")
                .get(ClientResponse.class);

              if (responseForFunder.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " +
                  response.getStatus());
              }

              Object outputFunder = responseForFunder.getEntity(Object.class);

              res.put("funder", outputFunder);

      } catch (Exception e) {

    	  return Response
    		        .status(Response.Status.INTERNAL_SERVER_ERROR)
    		        .entity(e)
    		        .build();

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

// Get All Payment Details
  
	public Response getPaymenAllDetails() {
		List<Map<String, ?>> res = new ArrayList<Map<String, ?>>();
		List<?> resultData = new ArrayList<>();
		List<?> resultDataResearcher = new ArrayList<>();
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("Database connectivity Error")
		        .build();

		      String query = "select * from payement";
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(query);
		      
		      
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
			          .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/getfbodys");

			        ClientResponse response2 = webResource2.accept("application/json")
			          .get(ClientResponse.class);

			        if (response2.getStatus() != 200) {
			          throw new RuntimeException("Failed : HTTP error code : " +
			            response.getStatus());
			        }

			  Object output2 = response2.getEntity(Object.class);
			  resultDataResearcher = (List<?>)output2;
			  

		      while (rs.next()) {
		        int id = rs.getInt("id");
		        int recipient_id = rs.getInt("recipient_id");
		        double total = rs.getDouble("total");
		        String payment_method = rs.getString("payment_method");
		        String paidAt = rs.getString("paidAt");
		        int researcher_id = rs.getInt("researcher_id");
		        int buyer_id = rs.getInt("buyer_id");
		        int fundingbody_id = rs.getInt("fundingbody_id");
		        Payment payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id, fundingbody_id);
		        Map<String, Object> temp = new HashMap<String, Object>();
		        payment.setId(id);
		        payment.setPaidAt(paidAt);
		        
		        temp.put("payment", payment);
		        
		        resultData
	        	.stream()
	        	.forEach(data -> {
	        		HashMap<String, ?> x = (HashMap<String, ?>) data;
	        		if(((int)(long)(x.get("id"))) == buyer_id) {
	        			temp.put("buyer", data);
	        		}
	        	});
	        	
		        if(!temp.containsKey("buyer")) {
		        	temp.put("buyer", null);
		        }
		        
		        resultDataResearcher
	        	.stream()
	        	.forEach(data -> {
	        		HashMap<String, ?> x = (HashMap<String, ?>) data;
	        		if(((int)(long)(x.get("id"))) == fundingbody_id) {
	        			temp.put("fundingbody", data);
	        		}
	        	});
		        
		        if(!temp.containsKey("fundingbody")) {
		        	temp.put("fundingbody", null);
		        }
		        res.add(temp);
		      }
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