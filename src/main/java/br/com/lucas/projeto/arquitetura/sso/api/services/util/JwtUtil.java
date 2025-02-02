package br.com.lucas.projeto.arquitetura.sso.api.services.util;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.entities.Role;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 3600000; // 1 hora

	@Value("${jwt.secret.key}")
	private String SECRET_KEY;
	
	@Value("${env.domain.name}")
	private String domain;

	public String generatePreToken(User user) {
    	
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", getRolesFromUser(user))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(buildSecretKey())
                .compact();
        
    }

    public String generateToken(User user, Application application) {
    	
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", getRolesFromUser(user))
                .claim("applicationId", application.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(buildSecretKey())
                .compact();
        
    }
    
	public String validateToken(String token) throws Exception {

		if (token != null && token.startsWith("Bearer ")) {
			String jwt = token.substring(7);

			try {
				return Jwts.parserBuilder()
						.setSigningKey(buildSecretKey())
						.build()
						.parseClaimsJws(jwt)
						.getBody()
						.getSubject();

			} catch (Exception e) {
				throw new Exception("Token inválido");
			}
		} else {
			throw new Exception("Token inválido");
		}

	}
	
	
    
    private static List<String> getRolesFromUser(User user) {
    	
		List<String> roles = user
					.getRoles().stream()
					.map(Role::name)
					.collect(Collectors.toList());
		
		return roles;
	}

	private Key buildSecretKey(){

		return new SecretKeySpec(SECRET_KEY.getBytes(),
			SignatureAlgorithm.HS256.getJcaName());
		
	}
    
}