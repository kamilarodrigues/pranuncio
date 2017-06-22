package br.com.pranuncio.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.pranuncio.entity.Estado; 
 
@ApplicationScoped
@SuppressWarnings("unchecked")
public class EstadoService {
	 
	@Inject
	private EntityManager em;
	   
	@Transactional
	public List<Estado> listarEstados() {
		Query q = em.createNamedQuery("Estado.findAll");
		return q.getResultList();
	}
 
	@Transactional
	public Estado pesquisarPorId(Integer id) {
		Query q = em.createNamedQuery("Estado.findById");
		q.setParameter("id", id);
		return (Estado)q.getSingleResult();
	}
}
