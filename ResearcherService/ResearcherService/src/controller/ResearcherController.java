package controller;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Researcher;
import service.ResearcherService;

/*
 *default Port : 8090 
 *http://localhost:8090/ResearcherService/api/v2/researcher/*
*/
@Path("/researcher") 
public class ResearcherController {
	private Researcher researcher;
	private ResearcherService researcherService = new ResearcherService();
	
	@GET
	@Path("/getresearchers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllResearchers() {
		return researcherService.getAllResearchers();
	}
	
	@GET
	@Path("/getresearcher/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherById(@PathParam("researcherid") Integer researcherid) {
		return researcherService.getResearcherById(researcherid);
	}
	
	@DELETE
	@Path("/deletebyid/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("researcherid") Integer researcherid) {
		return researcherService.deleteById(researcherid);
	}
	
	@PUT
	@Path("/update/{researcherid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateResearcher(HashMap<String, ?> researcherData, @PathParam("researcherid") Integer researcherid) {
		String name = (String) researcherData.get("name");
		String email = (String) researcherData.get("email");
		String password = (String) researcherData.get("password");
		Long researchCategoryTemp = new Long((long) researcherData.get("researchCategory"));
		int researchCategory = researchCategoryTemp.intValue();	
		
		researcher = new Researcher(name, email, password, researchCategory);
		researcher.setId(researcherid);
		return researcherService.updateResearcher(researcher);
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addResearcher(HashMap<String, ?> researcherData) {
		String name = (String) researcherData.get("name");
		String email = (String) researcherData.get("email");
		String password = (String) researcherData.get("password");
		Long researchCategoryTemp = new Long((long) researcherData.get("researchCategory"));
		int researchCategory = researchCategoryTemp.intValue();	
		
		researcher = new Researcher(name, email, password, researchCategory);
		
		return researcherService.addResearcher(researcher);
	}
	
	@GET
	@Path("/getresearchercat/{researcherid}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherWithCat(@PathParam("researcherid") Integer researcherid) {
		return researcherService.getResearcherWithCat(researcherid);
	}

	
	@GET
	@Path("/getresearchercat/all")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResearcherWithCatAll() {
		return researcherService.getResearcherWithCatAll();
	}
	
}
