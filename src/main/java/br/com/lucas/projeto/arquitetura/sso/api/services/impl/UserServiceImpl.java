package br.com.lucas.projeto.arquitetura.sso.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.projeto.arquitetura.sso.api.dao.ApplicationDAO;
import br.com.lucas.projeto.arquitetura.sso.api.dao.UserDAO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginRequestDTO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.LoginResponseDTO;
import br.com.lucas.projeto.arquitetura.sso.api.dto.UserDTO;
import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;
import br.com.lucas.projeto.arquitetura.sso.api.services.UserService;
import br.com.lucas.projeto.arquitetura.sso.api.services.util.JwtUtil;
import br.com.lucas.projeto.arquitetura.sso.api.services.util.PasswordUtil;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userRepository;

	@Autowired
	private ApplicationDAO applicationDAO;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	@Transactional
	public void create(UserDTO user) {
		this.userRepository.create(new User(user));
	}

	@Override
	@Transactional
	public void update(UserDTO user) {
		this.userRepository.update(new User(user));
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userRepository.findByUsername(username);

	}

	@Override
	public LoginResponseDTO preAuthenticate(LoginRequestDTO loginRequest) throws Exception {
		User user = this.findByUsername(loginRequest.getUsername());
		LoginResponseDTO responseDTO;
		if (user != null && PasswordUtil.matches(loginRequest.getPassword(), user.getPassword())) {
			responseDTO = new LoginResponseDTO();
			responseDTO.setJwt(jwtUtil.generatePreToken(user));
			return responseDTO;
		} else {
			throw new Exception("Usuário ou senha inválidos!");
		}

	}

	@Override
	public LoginResponseDTO authenticateApplication(String preToken, LoginRequestDTO loginRequest) throws Exception {

		String username = jwtUtil.validateToken(preToken);
		User user;
		LoginResponseDTO responseDTO;
		if (username != null) {
			user = this.findByUsername(username);
		} else {
			throw new Exception("Usuário ou senha inválidos!");
		}

		Application application = null;
		if (loginRequest.getApplicationId() != null) {
			application = applicationDAO.findByApplicationIdAndUser(loginRequest.getApplicationId(), user);
			
			responseDTO = new LoginResponseDTO();
			responseDTO.setJwt(jwtUtil.generateToken(user, application));
			
			return responseDTO;
		}

		throw new Exception("Aplicação inválida ou o usuário não possui acesso à aplicação informada");
	}
	
	@Override
	public List<Application> getApplicationsAvaiable(String preToken) throws Exception {

		String username = jwtUtil.validateToken(preToken);
		User user = this.findByUsername(username);

		return applicationDAO.findApplicationsByUser(user);
		
	}

}
