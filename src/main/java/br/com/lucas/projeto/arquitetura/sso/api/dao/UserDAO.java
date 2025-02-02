package br.com.lucas.projeto.arquitetura.sso.api.dao;

import br.com.lucas.projeto.arquitetura.sso.api.entities.User;

public interface UserDAO {

	public void create(User user);
	
	public void update(User user);

	public User findByUsername(String username) throws Exception;
}
