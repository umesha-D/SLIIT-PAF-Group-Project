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
import service.CategoryService;


public class CategoryService {

	private DBConnection connection = new DBConnection();
	
	public Response addCategory(Category category) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO category(categoryName,description) VALUES (?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setString(1, category.getCategoryName());
		      preparedStmt.setString(2, category.getDescription());


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
		      .entity(category)
		      .build();
	}

	public Response getAllCategories() {
		List <Category> categories = new ArrayList<Category> ();

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
	        String categoryName = rs.getString("categoryName");   
	        String description = rs.getString("description");
	   
	        
	        Category category = new Category(categoryName, description);
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

	public Response getCategoryById(Integer categorytid) {
		Category category = null;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from category where id = " + categorytid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  int id = rs.getInt("id");
		        String categoryName = rs.getString("categoryName");   
		        String description = rs.getString("description");
		        category = new Category(categoryName, description);
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

	public Response deletePayment(Integer categorytid) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from category WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, categorytid);

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
		      .entity("Succesfully Delected the category data")
		      .build();
	}

	public Response updateCategory(Category categoryData) {
		try
		  {
			  Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();
		  
		  String query = "UPDATE category SET categoryName=?,description=? WHERE id=?";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		  preparedStmt.setString(1, categoryData.getCategoryName());
		  preparedStmt.setString(2, categoryData.getDescription());
		  preparedStmt.setInt(3, categoryData.getId());
		  
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
			      .entity(categoryData)
			      .build();
	}
	
	

	
}
