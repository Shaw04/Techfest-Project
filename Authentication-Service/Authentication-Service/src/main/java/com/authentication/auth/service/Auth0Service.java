package com.authentication.auth.service;





import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTDecodeException;



@Service
public class Auth0Service {
	
	@Value("${auth0.clientid}")
    private String clientId;

    @Value("${auth0.secret}")
    private String clientSecret;


    @Value("${auth0.token-url}")
    private String tokenUrl;

   
    private final RestTemplate restTemplate;

    @Value("${auth0.audience}")
    private String audience;
    
    @Value("${auth0.user-info-url}")
    private String userInfoUrl;
    
    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.redirect-uri}")
    private String redirectUri;
    
    private String currentToken = ""; // Store the current token
    
    private String frontendRedirectUrl = "http://localhost:5173";

    public Auth0Service() {   
        this.restTemplate = new RestTemplate();
    }

    
    
    public ResponseEntity<Map<String, String>> login() {
        String loginUrl = "https://" + domain + "/authorize?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=openid%20profile%20email";
        
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", loginUrl);
        return ResponseEntity.ok(response);
    }
    
    

    public ResponseEntity<?> getUserInfo(String token) {
        try {
            // Remove "Bearer " if present
            token = token.replace("Bearer ", "");
            
            // Decode the ID token
            DecodedJWT jwt = JWT.decode(token);
            
            // Extract claims
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("sub", jwt.getSubject());
            userInfo.put("nickname", jwt.getClaim("nickname").asString());
            userInfo.put("name", jwt.getClaim("name").asString());
            userInfo.put("picture", jwt.getClaim("picture").asString());
            userInfo.put("updated_at", jwt.getClaim("updated_at").asString());
            userInfo.put("email", jwt.getClaim("email").asString());
            userInfo.put("email_verified", jwt.getClaim("email_verified").asBoolean());
            userInfo.put("iss", jwt.getIssuer());
            userInfo.put("aud", jwt.getAudience().get(0));
            userInfo.put("iat", jwt.getIssuedAt().getTime() / 1000);
            userInfo.put("exp", jwt.getExpiresAt().getTime() / 1000);
            userInfo.put("sid", jwt.getClaim("sid").asString());
            
            // Handle user_metadata separately as it's a nested object
            Map<String, Object> userMetadata = jwt.getClaim("https://your-namespace/user_metadata").asMap();
            if (userMetadata == null) {
                userMetadata = new HashMap<>();
            }
            userInfo.put("https://your-namespace/user_metadata", userMetadata);
            
            return ResponseEntity.ok(userInfo);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to decode token");
        }
    }
    
    public RedirectView callback( String code) {
        String tokenUrl = "https://" + domain + "/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("grant_type", "authorization_code");
        request.put("client_id", clientId);
        request.put("client_secret", clientSecret);
        request.put("code", code);
        request.put("redirect_uri", redirectUri);

        Map<String, String> response;
        try {
            response = restTemplate.postForObject(tokenUrl, request, Map.class);
            if (response == null || !response.containsKey("id_token")) {
                throw new RuntimeException("Failed to obtain access token");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error or redirect to an error page
            return new RedirectView(frontendRedirectUrl + "?error=token_exchange_failed");
        }

        String accessToken = response.get("id_token");
//        currentToken = accessToken;
        // Redirect back to the frontend with the token
        return new RedirectView(frontendRedirectUrl + "?token=" + accessToken);
    }
    
    public ResponseEntity<Map<String, String>> getToken() {
    	if (currentToken == null || currentToken.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Map<String, String> response = new HashMap<>();
        response.put("access_token", currentToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    public ResponseEntity<Map<String, String>> refreshToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("grant_type", "client_credentials");
        requestBody.put("client_id", clientId);
        requestBody.put("client_secret", clientSecret);
        requestBody.put("audience", audience);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, requestBody, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                currentToken = (String) response.getBody().get("access_token");

                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("access_token", currentToken);
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
}