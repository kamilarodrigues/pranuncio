package br.com.pranuncio.service;
 
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.pranuncio.entity.Acessoanuncio; 

@Named
@ApplicationScoped
public class AcessoAnuncioService {

	@Inject
	private EntityManager entityManager;

	public List<Acessoanuncio> listar(String sql) {
		Query q = entityManager.createQuery(sql); 
		List<Acessoanuncio> lista = q.getResultList(); 
		if (lista!=null) {
			return lista;
		}else{
			return lista = new ArrayList<>();
		} 
	} 
 
	@Transactional
	public Acessoanuncio incluir(Acessoanuncio acessoanuncio) {
		entityManager.persist(acessoanuncio);
		return acessoanuncio;
	}
	  
	@Transactional
	public void excluir(Acessoanuncio acessoanuncio) {
		entityManager.remove(entityManager.merge(acessoanuncio));
	}

}
