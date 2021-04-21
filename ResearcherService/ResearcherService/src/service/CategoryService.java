package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	

	
}
