//package com.example.TechFest.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class JWTAuthorizationFilter extends OncePerRequestFilter {
//
//    private String audience;
//    private String issuer;
//    private String secret;
//
//    public JWTAuthorizationFilter(String audience, String issuer, String secret) {
//        this.audience = audience;
//        this.issuer = issuer;
//        this.secret = secret;
//    }
//
//
//    @Override
//    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
//    		jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
//    				throws jakarta.servlet.ServletException, IOException {
//    	
//    	String token = request.getHeader("Authorization");
//    	
//    	if (token != null && token.startsWith("Bearer ")) {
//    		try {
//    			UsernamePasswordAuthenticationToken authentication = getAuthentication(token.substring(7));
//    			SecurityContextHolder.getContext().setAuthentication(authentication);
//    		} catch (JWTVerificationException exception) {
//    			// Log the exception and return unauthorized response
//    			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//    			return;
//    		}
//    	}
//    	
//    	filterChain.doFilter(request, response);
//    }
//    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws JWTVerificationException {
//        Algorithm algorithm = Algorithm.HMAC256(secret);
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withAudience(audience)
//                .withIssuer(issuer)
//                .build();
//        
//        DecodedJWT jwt = verifier.verify(token);
//        String user = jwt.getSubject();
//        
//        if (user != null) {
//            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//        }
//        
//        return null;
//    }
//
//
//	
//}
