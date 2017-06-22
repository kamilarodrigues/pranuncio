package br.com.pranuncio.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.pranuncio.entity.Cidade; 
 
@ApplicationScoped
@SuppressWarnings("unchecked")
public class CidadeService {
	 
	@Inject
	private EntityManager em;
	  
	@Transactional
	public List<Cidade> listarCidades() {
		Query q = em.createNamedQuery("Cidade.findAll");
		return q.getResultList();
	}
 
	@Transactional
	public Cidade pesquisarPorId(Integer id) {
		Query q = em.createNamedQuery("Cidade.findById");
		q.setParameter("id", id);
		return (Cidade)q.getSingleResult();
	}
	
	public List<Cidade> listarCidadesPorEstado(Integer idEstado) {
		Query q = em.createNamedQuery("Cidade.findAllByEstadoId");
		q.setParameter("idEstado", idEstado);
		return q.getResultList();
	}
}
