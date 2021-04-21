package controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;
import service.CategoryService;

@Path("/category") 
public class CategoryContoller {
	private Category category;
	private CategoryService categoryService = new CategoryService();
	
	@POST
	@Path("/addcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(HashMap<String, ?> categoryData) {
		String categoryName = (String) categoryData.get("categoryName");
		String description = (String) categoryData.get("description");
	    category = new Category(categoryName, description);
		return categoryService.addCategory(category);
	}
}
