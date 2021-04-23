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

import model.Category;
import model.Researcher;
import repository.DBConnection;

public class ResearcherService {
	
	private DBConnection connection = new DBConnection();

	public Response addResearcher(Researcher researcher) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "INSERT INTO researcher(name,email,password,researchCategory) VALUES (?, ?, ?, ?)";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setString(1, researcher.getName());
		      preparedStmt.setString(2, researcher.getEmail());
		      preparedStmt.setString(3, researcher.getPassword());
		      preparedStmt.setInt(4, researcher.getResearchCategory());

		      researcher.setCreatedAt("A few seconds ago");
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
		      .entity(researcher)
		      .build();
	}

	public Response getAllResearchers() {
		List <Researcher> researchers = new ArrayList<Researcher> ();

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String name = rs.getString("name");   
	        String email = rs.getString("email");
	        String password = rs.getString("password");
	        String createdAt = rs.getString("createdAt");
	        String updatedAt = rs.getString("updatedAt");
	        int researchCategory = rs.getInt("researchCategory");
	        
	        Researcher researcher = new Researcher(name, email, password, researchCategory);
	        researcher.setId(id);
	        researcher.setCreatedAt(createdAt);
	        researcher.setUpdatedAt(updatedAt);
	        
	        researchers.add(researcher);
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
	      .entity(researchers)
	      .build();
	}

	public Response getResearcherById(Integer researcherid) {
		Researcher researcher = null;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where id = " + researcherid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        int researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

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
	      .entity(researcher)
	      .build();
	}

	public Response deleteById(Integer researcherid) {
		try {
		      Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();

		      String query = "DELETE from researcher WHERE id=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);

		      preparedStmt.setInt(1, researcherid);

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
		      .entity("Succesfully Delected the Researcher data")
		      .build();
	}

	public Response getResearcherWithCat(Integer researcherid) {
		Researcher researcher = null;
		Category category = null;
		Map < String, Object > res = new HashMap < String, Object > ();
		int researchCategory = -99;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher where id = " + researcherid;
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

		        res.put("researcher", researcher);
	      }
	      
	      
	      String queryForGetCategory = "select * from category where id = " + researchCategory;
	      Statement stmtForCategory = con.createStatement();
	      ResultSet resultSet = stmtForCategory.executeQuery(queryForGetCategory);

	      while (resultSet.next()) {
	    	  	int id = resultSet.getInt("id");
		        String categoryName = resultSet.getString("categoryName");   
		        String description = resultSet.getString("description");
		        
		        category = new Category(categoryName, description);
		        category.setId(id);
		        category.setId(id);

	      }
	      
	      res.put("category", category);
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

	public Response getResearcherWithCatAll() {
		Researcher researcher = null;
		Category category = null;
		List<Map < String, Object >> finalResul = new ArrayList<>();
		
		Map < String, Object > res = new HashMap < String, Object > ();
		int researchCategory = -99;

	    try {
	      Connection con = connection.getConnection();
	      if (con == null) return Response
	        .status(Response.Status.INTERNAL_SERVER_ERROR)
	        .entity("DataBase connectivity Error")
	        .build();

	      String query = "select * from researcher";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);

	      while (rs.next()) {
	    	  	int id = rs.getInt("id");
		        String name = rs.getString("name");   
		        String email = rs.getString("email");
		        String password = rs.getString("password");
		        String createdAt = rs.getString("createdAt");
		        String updatedAt = rs.getString("updatedAt");
		        researchCategory = rs.getInt("researchCategory");
		        
		        researcher = new Researcher(name, email, password, researchCategory);
		        researcher.setId(id);
		        researcher.setCreatedAt(createdAt);
		        researcher.setUpdatedAt(updatedAt);
		        researcher.setId(id);

		        res.put("researcher", researcher);
		        
		        String queryForGetCategory = "select * from category where id = " + researchCategory;
			      Statement stmtForCategory = con.createStatement();
			      ResultSet resultSet = stmtForCategory.executeQuery(queryForGetCategory);

			      while (resultSet.next()) {
			    	  	int idT = resultSet.getInt("id");
				        String categoryName = resultSet.getString("categoryName");   
				        String description = resultSet.getString("description");
				        
				        category = new Category(categoryName, description);
				        category.setId(idT);
				        
			      }
			      
			      res.put("category", category);
			      finalResul.add(res);
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
	      .entity(finalResul)
	      .build();
	}

	public Response updateResearcher(Researcher researcher) {
		try
		  {
			  Connection con = connection.getConnection();
		      if (con == null) return Response
		        .status(Response.Status.INTERNAL_SERVER_ERROR)
		        .entity("DataBase connectivity Error")
		        .build();
		  
		  String query = "UPDATE researcher SET name=?,email=?,password=?,updatedAt=CURRENT_TIMESTAMP,researchCategory=? WHERE id=?";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		  preparedStmt.setString(1, researcher.getName());
		  preparedStmt.setString(2, researcher.getEmail());
		  preparedStmt.setString(3, researcher.getPassword());
		  preparedStmt.setInt(4, researcher.getResearchCategory());
		  preparedStmt.setInt(5, researcher.getId());
		  
		  preparedStmt.execute();
		  con.close();
		  researcher.setUpdatedAt("A few seconds ago");
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
			      .entity(researcher)
			      .build();
	}

}
