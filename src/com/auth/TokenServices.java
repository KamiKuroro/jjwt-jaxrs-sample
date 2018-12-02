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
		catch(Exception ex) {
			System.out.println(ex);
			System.out.println("----------------------------------------------------------------------------------");
			ex.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	
}
