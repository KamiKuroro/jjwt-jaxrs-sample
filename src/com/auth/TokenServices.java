/**
 * Ardiansyah | http://ard.web.id
 *
 */

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
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class TokenServices {
	
//	private static final Key signingKey = new SecretKeySpec(
//		DatatypeConverter.parseBase64Binary("ExampleKey"), SignatureAlgorithm.HS512.getJcaName()
//	);
	
	public static String createToken(String username,long expired) {
//		Key signingkey = MacProvider.generateKey();
		Key signingkey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String token =  Jwts.builder()
					.setSubject(username)
					.signWith(signingkey)
					.setExpiration(new Date(expired))					
					.compact();
		Boolean is_token_valid = false;
		try {
			is_token_valid =  Jwts.parser().setSigningKey(signingkey).parseClaimsJws(token).getBody().getSubject().equals(username);
//			Boolean is_token_valid =  Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Jo");
			
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
	
//	public static String validateToken(String token)throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
//		Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
//		String role = null;
//		role = claims.get("role").toString();
//		Jwts.parser()
//			.requireSubject(claims.getSubject())
//			.setSigningKey(signingKey)
//			.parseClaimsJws(token);
//		
//		return role;
//	}
	
	
}
