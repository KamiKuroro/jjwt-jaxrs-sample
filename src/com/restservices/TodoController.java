package com.restservices;


import com.jwtfilter.JWTTokenNeeded;
import com.jwtfilter.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONObject;


@Path("/todo")
public class TodoController {
	
	@GET	
	@Path("/list_todos")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded
	public Response listTodoItems() {
//		System.out.println("token in todo::"+token);
		Test obj = new Test();
		obj.printTest();
		return Response.ok().build();
	}

	
}	