package br.com.lucas.projeto.arquitetura.sso.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.lucas.projeto.arquitetura.sso.api.dao.UserDAO;
import br.com.lucas.projeto.arquitetura.sso.api.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void create(User user) {
		entityManager.persist(user);

	}

	@Override
	public void update(User user) {
		entityManager.merge(user);

	}

	@Override
	public User findByUsername(String username) throws Exception {

		String jpql = "SELECT u FROM User u WHERE u.username = :username";

		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("username", username);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// throw new BusinessException(e);
			throw new Exception("Usuário ou senha inválidos!");
		} catch (Exception e) {
			// Lidar com a exceção quando não encontrar o usuário
			return null;
		}
	}

}
