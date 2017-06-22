package br.com.pranuncio.service;
  
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
 
import br.com.pranuncio.entity.Dadosanunciante; 

@Named
@ApplicationScoped
public class DadosAnuncianteService {

	@Inject
	private EntityManager entityManager; 
	
	public Dadosanunciante consultar(String sql) {
		Query q = entityManager.createQuery(sql);  
		List<Dadosanunciante> dadosanunciantes = q.getResultList();  
		Dadosanunciante dadosanunciante = null;
		if (dadosanunciantes.size()>0) {
			dadosanunciante = dadosanunciantes.get(0);
		} 
		return dadosanunciante; 
	} 
	
	@Transactional
	public Dadosanunciante salvar(Dadosanunciante dadosanunciante) {
		entityManager.merge(dadosanunciante);
		return dadosanunciante;
	}
	
	@Transactional
	public void excluir(Dadosanunciante dadosanunciante) {
		entityManager.remove(entityManager.merge(dadosanunciante));
	}

}
