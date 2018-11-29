package Auth;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import java.security.Key;

@Path("/AuthTest")
public class AuthTest{

	static final Logger log = Logger.getLogger(AuthTest.class);
	
	@GET
	@Path("/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		System.out.println("Hello World!");

		Key key = MacProvider.generateKey();

		String compactJws = Jwts.builder().setSubject("login")
				.claim("username", "a").claim("passwd", "b")
				.signWith(SignatureAlgorithm.HS512, key).compact();
		
		log.info(compactJws);
		System.out.println("compactJws::"+compactJws);
		
		Jwt<?, ?> jwt = Jwts.parser().setSigningKey(key).parse(compactJws);
		log.info(jwt.getBody());
		System.out.println("jwt.getBody()::"+jwt.getBody());
		log.info(jwt.getHeader());
		System.out.println("jwt.getHeader()::"+jwt.getHeader());
		
		return "hello_world";
	}

//	public static void main(String[] args) {
//		System.out.println("Hello World!");
//
//		Key key = MacProvider.generateKey();
//
//		String compactJws = Jwts.builder().setSubject("login")
//				.claim("username", "a").claim("passwd", "b")
//				.signWith(SignatureAlgorithm.HS512, key).compact();
//		
//		log.info(compactJws);
//		
//		Jwt<?, ?> jwt = Jwts.parser().setSigningKey(key).parse(compactJws);
//		log.info(jwt.getBody());
//		log.info(jwt.getHeader());	
//
//	}
}