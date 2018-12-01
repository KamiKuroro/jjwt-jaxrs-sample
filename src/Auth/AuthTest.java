package Auth;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.Date; 
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
	
	@GET
	@Path("/jjwt_github_example1")
	@Produces(MediaType.TEXT_PLAIN)
	public String createJWT() {
		
		String id = "2";
		String issuer = "sdp"  ;
		String subject= "login";
		long ttlMillis = 10000;
		
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		System.out.println(key);

		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
		System.out.println(jws);
		
		Boolean is_token_valid = false;
		try {
			is_token_valid =  Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
//			Boolean is_token_valid =  Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Jo");
			
		}
		catch(JwtException e) {
			//	Problem while parsing JWT
		}
		
		System.out.println(is_token_valid);
		return jws;

	    
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