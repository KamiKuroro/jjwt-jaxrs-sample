

package com.auth;

import com.restservices.*;
import com.db.CRUDOps;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.json.Json;
import javax.print.DocFlavor.STRING;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONObject;


@Path("/auth")
public class AuthEndpoint {	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)

	public Response authenticateUser(@FormDataParam("username") String username, @FormDataParam("password") String password)	{
		//	Validate if user exists in DB
		CRUDOps crudops = new CRUDOps();
		Boolean is_user_present = crudops.isUserPresent(username,password);
		boolean responseResult = true;
		if(is_user_present) {
			String token = createToken(username);			
			System.out.println("token::"+token);
			JSONObject responseJSON = new JSONObject();
			responseJSON.put("access_token", token);
//			JSONObject responseJSON = Json.createObjectBuilder().add("token", createToken(username)).build();
//			return Response.ok(
//					Json.createObjectBuilder().add("token", createToken(username)).build(),MediaType.APPLICATION_JSON
//				).build();		
			return responseResult
					? Response.status(200).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.entity(responseJSON.toString()).build()
					: Response.status(200).header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
							.entity(responseJSON.toString()).build();
		}
		else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	private String createToken(String username) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 1800);
		return TokenServices.createToken(username, cal.getTimeInMillis());
	}
	
}