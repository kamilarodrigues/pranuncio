package br.com.pranuncio.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

	private String criptografiaMd5(String valor) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public void incluir(Anuncio anuncio) {
		entityManager.persist(anuncio);
	}
	
	@Transactional
	public void alterar(Anuncio anuncio) {
		entityManager.merge(anuncio);
	}
	
	@Transactional
	public void excluir(Anuncio anuncio) {
		entityManager.remove(entityManager.merge(anuncio));
	}

}
