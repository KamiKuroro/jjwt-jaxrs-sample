package com.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

import com.globalkeys.KeyConstants;


public class TokenServices {
	
	private static Key generatedKey = MacProvider.generateKey(SignatureAlgorithm.HS256);
	private static byte keyData[] = generatedKey.getEncoded();
	private static final Key signingKey = new SecretKeySpec(keyData, SignatureAlgorithm.HS256.getJcaName());
	
//	String key = "random_secret_key";
//	String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
//	byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);
//	private static final Key signingKey = new SecretKeySpec(
//		DatatypeConverter.parseBase64Binary(System.getenv(KeyConstants.SECRET_KEY_JWT)), SignatureAlgorithm.HS512.getJcaName()
//	);
	
	public static String createToken(String username,long expired) {
		String token =  Jwts.builder()
					.setSubject(username)
					.signWith(TokenServices.signingKey)
					.setExpiration(new Date(expired))					
					.compact();
		Boolean is_token_valid = false;
		try {
			is_token_valid =  Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject().equals(username);
			
		}
		catch(JwtException e) {
			//	Problem while parsing JWT
			System.out.println("Not able to parse JWT");
		}
		
		System.out.println(is_token_valid);
		if(is_token_valid) {
			//	Store Token
			return token;
		}
		return "";
		
	}
	
	public static Boolean isTokenValid(String token)throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		try {
			Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
			System.out.println(claims.getSubject());
			
		}		
		catch(ExpiredJwtException expiredException) {
			System.out.println("expired exception");
			//	Redirect to get new token and handle all scenarios
			return false;
			
		}		
		catch(Exception ex) {
			System.out.println(ex);
			System.out.println("----------------------------------------------------------------------------------");
			ex.printStackTrace();
			return false;
		}
		/**
		 * 	ClaimJwtException: thrown after a validation of a JTW claim failed
		 *	ExpiredJwtException: indicating that a JWT was accepted after it expired and must be rejected
		 *	MalformedJwtException: thrown when a JWT was not correctly constructed and should be rejected
		 *	PrematureJwtException: indicates that a JWT was accepted before it is allowed to be accessed and must be rejected
		 *	SignatureException: indicates that either calculating a signature or verifying an existing signature of a JWT failed
		 *	UnsupportedJwtException: thrown when receiving a JWT in a particular format/configuration that does not match the format expected by the application. 
		 *		For example, this exception would be thrown if parsing an unsigned plaintext JWT when the application requires a cryptographically signed Claims JWS instead
		 */
		
		
		return true;
	}
	
	
}
