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
import model.ProductFunders;
import repository.DBConnection;

public class ProductFundersService {
  private DBConnection connection = new DBConnection();

  public Response addFundingBody(ProductFunders productFunder) {
    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String query = "INSERT INTO funders(id,funder_id) VALUES (?, ?)";
      PreparedStatement preparedStmt = con.prepareStatement(query);

      preparedStmt.setInt(1, productFunder.getId());
      preparedStmt.setInt(2, productFunder.getFunderId());

      preparedStmt.execute();
      con.close();

    } catch (Exception e) {
      return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(e)
        .build();
    }
    return Response
      .status(Response.Status.CREATED)
      .entity(productFunder)
      .build();
  }

  public Response getProductWithFunder(Integer productid) {
    Map < String, Object > res = new HashMap < String, Object > ();
    List < Object > funders = new ArrayList < > ();
    Product product = null;

    try {
      Connection con = connection.getConnection();
      if (con == null) return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("DataBase connectivity Error")
        .build();

      String queryForGetProductData = "SELECT * FROM product WHERE id = " + productid;
      Statement statement = con.createStatement();
      ResultSet result = statement.executeQuery(queryForGetProductData);

      while (result.next()) {
        int id = result.getInt("id");
        String product_name = result.getString("product_name");
        double product_price = result.getDouble("product_price");
        int owner_id = result.getInt("owner_id");
        String created_at = result.getString("created_at");
        String updated_at = result.getString("updated_at");
        boolean is_completed = result.getBoolean("is_completed");
        int category_id = result.getInt("category_id");
        product = new Product(product_name, product_price, owner_id, is_completed, category_id);
        product.setCreated_at(created_at);
        product.setId(id);
        product.setUpdated_at(updated_at);
      }

      String query = "SELECT funders.id, funders.funder_id FROM funders WHERE funders.id = " + productid;
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        int funder_id = rs.getInt("funder_id");

        try {

          Client client = Client.create();

          WebResource webResource = client
            .resource("http://localhost:8282/FundingBodyService/api/v2/fbody/getfbodybyid/" + id);

          ClientResponse response = webResource.accept("application/json")
            .get(ClientResponse.class);

          if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " +
              response.getStatus());
          }

          Object output = response.getEntity(Object.class);

          funders.add(output);

        } catch (Exception e) {

          e.printStackTrace();

        }
      }

      res.put("product", product);
      res.put("founders", funders);
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