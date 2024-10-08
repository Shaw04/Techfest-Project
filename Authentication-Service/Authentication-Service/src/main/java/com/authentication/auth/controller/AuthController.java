package com.authentication.auth.controller;




import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import com.authentication.auth.service.Auth0Service;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private JwtDecoder jwtDecoder;
    private final Auth0Service auth0Service;

    @Autowired
    public AuthController(Auth0Service auth0Service,JwtDecoder jwtDecoder) {
        this.auth0Service = auth0Service;
        this.jwtDecoder=jwtDecoder;
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login() {
    	System.out.println("called login");
        return auth0Service.login();
    }


   
    
    @GetMapping("/callback")
    public RedirectView callback(@RequestParam String code) {
    	System.out.println("called callback");
        return auth0Service.callback(code);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        return auth0Service.getUserInfo(token);
    }

    // Endpoint to get the current token
    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getToken() {
    	return auth0Service.getToken();
    }

    // Endpoint to refresh the token
    @GetMapping("/token/refresh")
    public ResponseEntity<Map<String, String>> refreshToken() {
        return auth0Service.refreshToken();
    }
    
    
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenValidationRequest request) {
        String token = request.getToken();
        
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is required");
        }

        try {
        	System.out.println("called validate token");
            jwtDecoder.decode(token);
            return ResponseEntity.ok().body("Token is valid");
        } catch (JwtException e) {
            return ResponseEntity.badRequest().body("Invalid token: " + e.getMessage());
        }
    }
    
    
}

class TokenValidationRequest {
    private String token;

    // Getter and setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
