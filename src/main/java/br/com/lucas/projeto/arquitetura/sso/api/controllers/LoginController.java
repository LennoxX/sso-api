package br.com.lucas.projeto.arquitetura.sso.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginRequestDTO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginResponseDTO;
import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.services.UserService;

@RestController
@RequestMapping(value = "auth")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/pre-authenticate")
	public ResponseEntity<LoginResponseDTO> preAuthenticate(@RequestBody LoginRequestDTO loginRequest) throws Exception {
		LoginResponseDTO response = userService.preAuthenticate(loginRequest);
		return ResponseEntity.ok().header("Authorization", response.getJwt()).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> authenticate(@RequestHeader(name = "Authorization") String preToken, @RequestBody LoginRequestDTO loginRequest) throws Exception {
		LoginResponseDTO response = userService.authenticateApplication(preToken, loginRequest);
		
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Cabeçalhos antes: " + headers);
		headers.set("Authorization", response.getJwt());
		System.out.println("Cabeçalhos depois: " + headers);

		return ResponseEntity.ok()
		    .headers(headers)
		    .build();
		
	}
	
	@GetMapping("/applications")
	public ResponseEntity<List<Application>> getApplicationsAvaiable(@RequestHeader(name = "Authorization") String preToken) throws Exception {
		return ResponseEntity.ok().body(userService.getApplicationsAvaiable(preToken));
	}
	
	@PostMapping("/refresh")
	public void refreshAuth() {
		
	}
}
