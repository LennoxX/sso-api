package br.com.lucas.projeto.arquitetura.sso.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.projeto.arquitetura.sso.api.dto.UserDTO;
import br.com.lucas.projeto.arquitetura.sso.api.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Void> create(@RequestBody UserDTO user) {
		userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> update(@RequestBody UserDTO user) {
		userService.update(user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping()
	public String teste() {
		return "OK";
	}
	
}
