package br.com.pranuncio.service;
 
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.pranuncio.entity.Anuncio; 

@Named
@ApplicationScoped
public class AnuncioService {

	@Inject
	private EntityManager entityManager;

	public List<Anuncio> listarHabilitados() {
		Query q = entityManager.createNamedQuery(Anuncio.CONSULTAR_HABILITADOS); 
		List<Anuncio> anuncios = q.getResultList(); 
		if (anuncios!=null) {
			return anuncios;
		}else{
			return anuncios = new ArrayList<>();
		} 
	}
	
	public List<Anuncio> meusAnuncios(String sql) {
		Query q = entityManager.createQuery(sql);  
		List<Anuncio> anuncios = q.getResultList(); 
		if (anuncios!=null) {
			return anuncios;
		}else{
			return anuncios = new ArrayList<>();
		} 
	}
 
	@Transactional
	public Anuncio incluir(Anuncio anuncio) {
		entityManager.persist(anuncio);
		return anuncio;
	}
	
	@Transactional
	public Anuncio alterar(Anuncio anuncio) {
		entityManager.merge(anuncio);
		return anuncio;
	}
	
	@Transactional
	public void excluir(Anuncio anuncio) {
		entityManager.remove(entityManager.merge(anuncio));
	}

}
