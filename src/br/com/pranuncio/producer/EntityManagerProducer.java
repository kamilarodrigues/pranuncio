package br.com.pranuncio.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceContext(name = "pranuncioPU")
	private EntityManager entityManager;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return entityManager;
	}

}