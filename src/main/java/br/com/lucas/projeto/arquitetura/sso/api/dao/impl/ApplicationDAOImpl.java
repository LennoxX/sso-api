package br.com.lucas.projeto.arquitetura.sso.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lucas.projeto.arquitetura.sso.api.dao.ApplicationDAO;
import br.com.lucas.projeto.arquitetura.sso.api.entities.Application;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Application> findApplicationsByUser(User user) throws Exception {
		List<Application> availableApplications;
		String jpql = "SELECT new br.com.lucas.projeto.arquitetura.sso.api.entities.Application(a.id, a.name, a.description, a.apiPath, a.appPath, a.icon) "
				+ "FROM Application a "
				+ "JOIN a.users u "
				+ "WHERE u.id = :userId "
				+ "and a.selectable = true";

		TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
		query.setParameter("userId", user.getId());

		try {
			availableApplications = query.getResultList();
			
			if(availableApplications != null && availableApplications.isEmpty()) {
				throw new Exception("Usuário sem aplicações cadastradas");
			}
		} catch (Exception e) {
			throw new Exception("Aplicação inválida ou o usuário não possui acesso à aplicação informada");
		}
		return availableApplications;
	}

	@Override
	public Application findByApplicationIdAndUser(Long applicationId, User user) throws Exception {
		String jpql = "SELECT new br.com.lucas.projeto.arquitetura.sso.api.entities.Application(a.id, a.name, a.description) "
				+ "FROM Application a "
				+ "JOIN a.users u "
				+ "WHERE u.id = :userId "
				+ "AND a.id = :applicationId";

		TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
		query.setParameter("applicationId", applicationId);
		query.setParameter("userId", user.getId());

		try {
			return query.getSingleResult();
		} catch (Exception e) {
			throw new Exception("Aplicação inválida ou o usuário não possui acesso à aplicação informada");
		}
	}
	
}
