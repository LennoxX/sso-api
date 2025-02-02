package br.com.lucas.projeto.arquitetura.sso.api.services;

import java.util.List;

import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginRequestDTO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginResponseDTO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.UserDTO;
import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;

public interface UserService {

	public void create(UserDTO user);
	
	public void update(UserDTO user);
	
	public User findByUsername(String username) throws Exception;

	public LoginResponseDTO preAuthenticate(LoginRequestDTO loginRequest) throws Exception;

	LoginResponseDTO authenticateApplication(String preToken, LoginRequestDTO loginRequest) throws Exception;

	List<Application> getApplicationsAvaiable(String preToken) throws Exception;
}
