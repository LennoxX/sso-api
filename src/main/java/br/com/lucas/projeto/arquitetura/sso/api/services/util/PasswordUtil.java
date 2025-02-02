package br.com.lucas.projeto.arquitetura.sso.api.services.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

	private PasswordUtil() {
		
	}
	
	public static String encodePassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return hashedPassword;
	}

	public static boolean matches(String rawPassword, String hashedPassword) {
		return BCrypt.checkpw(rawPassword, hashedPassword);
	}
}
