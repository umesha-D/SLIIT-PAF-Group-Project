package controller;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Product;
import service.ProductService;

@Path("/product") 
public class ProductController {	
	
	private Product product;
	private ProductService productService = new ProductService();
	
	@POST
	@Path("/addproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(HashMap<String, ?> productData) {

		String productName = (String) productData.get("productName");
		
		Long productPrice = new Long((long) productData.get("productPrice"));
			
		Long ownerIdTemp = new Long((long) productData.get("ownerId"));
		int ownerId = ownerIdTemp.intValue();
		
		boolean isCompleted = (boolean) productData.get("isCompleted");
		
		Long categoryIdTemp = new Long((long) productData.get("categoryId"));
		int categoryId = categoryIdTemp.intValue();
		
		product = new Product(productName, productPrice, ownerId, isCompleted, categoryId);
		
		return productService.addProduct(product);
	}
	
	@GET
	@Path("/getproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		return productService.getProducts();
	}
	
	@GET
	@Path("/getproductbyid/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuyerById(@PathParam("productid") Integer productid) {
		return productService.getBuyerById(productid);
	}
	
	@DELETE
	@Path("/deletebyid/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("productid") Integer productid) {
		return productService.deletePayment(productid);
	}
	
	
	@GET
	@Path("/buyproduct/{productId}/{buyerid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buyProduct(@PathParam("productId") Integer productId, @PathParam("buyerid") Integer buyerid) {
		return productService.buyProduct(productId, buyerid);
	}
	
	
	@GET
	@Path("/getproductwithbuyer/{productid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductWithBuyer(@PathParam("productid") Integer productid) {
		return productService.getProductWithBuyer(productid);
	}

}
