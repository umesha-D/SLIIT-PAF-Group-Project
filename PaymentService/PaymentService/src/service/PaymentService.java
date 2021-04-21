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

  public Response addPayment(Payment payment) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String query = "INSERT INTO payement(recipient_id,total, payment_method,researcher_id, buyer_id) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setInt(1, payment.getRecipientId());
      preparedStmt.setDouble(2, payment.getTotal());
      preparedStmt.setString(3, payment.getPaymentMethod());
      preparedStmt.setInt(4, payment.getResearcheId());
      preparedStmt.setInt(5, payment.getBuyerId());

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

  public Response deletePayment(int paymentId) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
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
      .entity("Succesfully Delected the payment data")
      .build();
  }

  public Response getAllPayments() {
    List < Payment > payments = new ArrayList < Payment > ();

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
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
        Payment payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id);
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

  public Response getPaymentById(int paymentid) {
    Payment payment = null;

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
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
        payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id);
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

  public Response getPaymentWithUser(Integer paymentid) {

    Payment payment = null;
    Map < String, Object > res = new HashMap < String, Object > ();
    int buyer_id = -99;

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
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
        payment = new Payment(recipient_id, total, payment_method, researcher_id, buyer_id);
        payment.setId(id);
        payment.setPaidAt(paidAt);
      }

      res.put("Payment", payment);

      try {

        Client client = Client.create();

        WebResource webResource = client
          .resource("http://localhost:8081/BuyerService/api/v2/buyer/getbuyerbyid/" + buyer_id);

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