## sample-jaxrs-jwt

This is a simple demo app for demonstrating how to use JWT tokens for token-based authentication in Java using JJWT library. 

### Technologies:
 - [Jersey Framework for developing REST APIs.](https://jersey.github.io/)
 - JAX-RS REST Specification
 - MySQL DB
 - [JJWT Library](https://github.com/jwtk/jjwt)
 
### Instructions to use:

This is just a server side implementation and does not include client side code. So, you'll need [POSTMAN](https://www.getpostman.com/) or relevant tool in order to test this on you local system.  

Clone this project and set it up in Eclipse. You'll also need to set up environment variable for storing your secret key. Using Eclipse, you can easily do so by going to `Run->Run Configurations` under `Environment` tab. You must set the name of the variable as `SECRET_KEY_JWT` and restart IDE after setting the env. var.  It must be strong enough for HMAC-SHA256 Algorithm (>=256bits).

You'll also need MySQL 5.7+. Run the [dump SQL files](https://github.com/nileshprasad137/jjwt-jaxrs-sample/tree/master/db-dump) to set up your DB.

Hit `/auth_control/auth/` endpoint to generate token and `/services_control/todo/list_todos` to access your Resources.

### References:

 - [https://github.com/agoncal/agoncal-sample-jaxrs/tree/master/04-JWT](https://github.com/agoncal/agoncal-sample-jaxrs/tree/master/04-JWT)
 - [https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey](https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)
 - [https://stackoverflow.com/questions/31309759/what-is-secret-key-for-jwt-based-authentication-and-how-to-generate-it/31313582](https://stackoverflow.com/questions/31309759/what-is-secret-key-for-jwt-based-authentication-and-how-to-generate-it/31313582)
 - [https://stackoverflow.com/questions/30523238/best-practices-for-server-side-handling-of-jwt-tokens](https://stackoverflow.com/questions/30523238/best-practices-for-server-side-handling-of-jwt-tokens)
 - [https://stackoverflow.com/questions/27301557/if-you-can-decode-jwt-how-are-they-secure](https://stackoverflow.com/questions/27301557/if-you-can-decode-jwt-how-are-they-secure)
 - [https://stackoverflow.com/questions/23808460/jwt-json-web-token-library-for-java](https://stackoverflow.com/questions/23808460/jwt-json-web-token-library-for-java)
 
 ### License
 
 [MIT](https://github.com/nileshprasad137/jjwt-jaxrs-sample/blob/master/LICENSE)
 


 
