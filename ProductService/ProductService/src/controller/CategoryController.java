package controller;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;
import service.CategoryService;

/*
 *default Port : 8180 
 *http://localhost:8180/ProductService/api/v2/category/*
*/
@Path("/category") 
public class CategoryController {
	
	private Category category;
	private CategoryService categoryService = new CategoryService();
	
	@POST
	@Path("/addcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(HashMap<String, ?> categoryData) {

		String name = (String) categoryData.get("name");
		String description = (String) categoryData.get("description");
		
		category = new Category(name, description);
		
		return categoryService.addCategory(category);
	}
	
	@PUT
	@Path("/update/{categoryid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(HashMap<String, ?> categoryData, @PathParam("categoryid") Integer categoryid) {
		String name = (String) categoryData.get("name");
		String description = (String) categoryData.get("description");
	    category = new Category(name, description);
	    category.setId(categoryid);
	    
		return categoryService.updateCategory(category);
	}
	
	@GET
	@Path("/getcategorys")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorys() {
		return categoryService.getCategorys();
	}
	
	@GET
	@Path("/getcategorybyid/{categoryid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyerById(@PathParam("categoryid") Integer categoryid) {
		return categoryService.getBuyerById(categoryid);
	}
	
	@DELETE
	@Path("/deletebyid/{categoryid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("categoryid") Integer categoryid) {
		return categoryService.deleteCategory(categoryid);
	}
	
}
