package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import model.Category;
import repository.DBConnection;

public class CategoryService {
	private DBConnection connection = new DBConnection();

	public Response addCategory(Category category) {
		int insertedId = -99;
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO category(name,description) VALUES (?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		      preparedStmt.setString(1, category.getName());
		      preparedStmt.setString(2, category.getDescription());
		      
		      preparedStmt.execute();
		      ResultSet rs = preparedStmt.getGeneratedKeys();
		      if (rs.next()){
		    	  insertedId  = Integer.parseInt(rs.getString(1));
		      }
		      con.close();
		      
		      category.setId(insertedId);
		    } catch (Exception e) {
		      return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity(e)
		        .build();
		    }
		    return Response
		      .status(Response.Status.CREATED)
		      .entity(category)
		      .build();
	}

	public Response getCategorys() {
		List<Category> categories = new ArrayList<Category> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from category";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String name = rs.getString("name");
	        String description = rs.getString("description");
	        Category category = new Category(name, description);
	        category.setId(id);
	        categories.add(category);

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
	      .entity(categories)
	      .build();
	}

	public Response getBuyerById(Integer categoryid) {
		Category category = null;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from category where id = " + categoryid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  int id = rs.getInt("id");
		        String name = rs.getString("name");
		        String description = rs.getString("description");
		        category = new Category(name, description);
		        category.setId(id);
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
	      .entity(category)
	      .build();
	}

	public Response deleteCategory(Integer categoryid) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from category WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, categoryid);

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

}
