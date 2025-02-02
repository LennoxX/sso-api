package br.com.lucas.projeto.arquitetura.sso.api.dao;

import java.util.List;

import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;

public interface ApplicationDAO {

	public List<Application> findApplicationsByUser(User user) throws Exception;

	public Application findByApplicationIdAndUser(Long applicationId, User user) throws Exception;

}