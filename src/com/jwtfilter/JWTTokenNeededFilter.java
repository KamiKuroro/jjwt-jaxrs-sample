package com.jwtfilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.security.Keys;

import com.auth.TokenServices;
import com.globalkeys.KeyConstants;
//import org.apache.commons.codec.binary.Base64;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerException;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.spi.ContainerResponseWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


@Provider
@JWTTokenNeeded
public class JWTTokenNeededFilter implements ContainerRequestFilter, ContainerResponseWriter {
	
	public JWTTokenNeededFilter(){
		System.out.println("JWT Token Filter Initialized");
	}
	

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
 
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println(authorizationHeader);
        System.out.println(KeyConstants.SECRET_KEY_JWT);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("#### invalid authorizationHeader : " + authorizationHeader);
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        System.out.println("token in bearer::"+token);
        /*
         * Check if the token was issued by the server and if it's not expired
         * Throw an Exception if the token is invalid
         */
        Boolean is_token_valid = false;
        try {
        	
        	is_token_valid = TokenServices.isTokenValid(token);
        	System.out.println("is token valid?? :" + is_token_valid);
        	//	IF TOKEN IS NOT VALID, ABORT REQUEST AND DON'T LET USER ACCESS REST ENDPOINT
        	if(!is_token_valid) {
        		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        	}
        	// check
        	
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
        

//        try {
//
//            // Validate the token
//            Key signingkey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//            Jwt<?, ?> jwt = Jwts.parser().setSigningKey(signingkey).parseClaimsJws(token);
//            System.out.println("jwt.getBody():"+jwt.getBody());
//            System.out.println("jwt.getBody():"+jwt.getBody());
//            logger.info("#### valid token : " + token);

//        } catch (Exception e) {
////            logger.severe("#### invalid token : " + token);
//        	System.out.println(e);
////        	e.printStackTrace();
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
    }


	@Override
	public OutputStream writeResponseStatusAndHeaders(long contentLength, ContainerResponse responseContext)
			throws ContainerException {
		// TODO Auto-generated method stub
		System.out.println("writeResponseStatusAndHeaders");
		return null;
	}


	@Override
	public boolean suspend(long timeOut, TimeUnit timeUnit, TimeoutHandler timeoutHandler) {
		// TODO Auto-generated method stub
		System.out.println("writeResponseStatusAndHeaders");
		return false;
	}


	@Override
	public void setSuspendTimeout(long timeOut, TimeUnit timeUnit) throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void failure(Throwable error) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean enableResponseBuffering() {
		// TODO Auto-generated method stub
		return false;
	}
}