package controller;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Buyer;
import model.Favourite;
import service.BuyerService;
import service.FavouriteService;

/*
 *default Port : 8080 
 *http://localhost:8080/BuyerService/api/v2/favourite/*
*/
@Path("/favourite") 
public class FavouriteController {
	private Favourite favourite;
	private FavouriteService favouriteService = new FavouriteService();
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAsAFavourite(HashMap<String, ?> Data) {
		Long userIdTemp = new Long((long) Data.get("id"));
		int userId = userIdTemp.intValue();	
		Long productIdTemp = new Long((long) Data.get("productId"));
		int productId = productIdTemp.intValue();	;
		favourite = new Favourite(userId, productId);
		return favouriteService.addAsAFavourite(favourite);
	}
	
	@DELETE
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeFromFavourite(HashMap<String, ?> Data) {
		Long userIdTemp = new Long((long) Data.get("id"));
		int userId = userIdTemp.intValue();	
		Long productIdTemp = new Long((long) Data.get("productId"));
		int productId = productIdTemp.intValue();	;
		favourite = new Favourite(userId, productId);
		return favouriteService.removeFromFavourite(favourite);
	}
	
	@GET
	@Path("/getfavourites")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavoutes() {
		return favouriteService.getFavoutes();
	}
	
	@GET
	@Path("/getfavouritesdetails/{buyerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavoutesDetails(@PathParam("buyerid") Integer buyerid) {
		return favouriteService.getFavoutesDetails(buyerid);
	}

	
}
