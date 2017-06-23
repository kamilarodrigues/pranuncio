package br.com.pranuncio.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
 
import br.com.pranuncio.entity.Usuario;

@Named
@ApplicationScoped
public class UsuarioService {

	@Inject
	private EntityManager entityManager;

	public Usuario pesquisarPorNomeSenha(Usuario usuario) {
		Query q = entityManager.createNamedQuery(Usuario.FIND_BY_NOME_SENHA);
		q.setParameter("usuario", usuario.getUsuario());
		q.setParameter("senha", criptografiaMd5(usuario.getSenha()));
		List<Usuario> usuarios = q.getResultList();
		Usuario usuarioRetorno = null;
		if (usuarios.size() == 1) {
			usuarioRetorno = usuarios.get(0);
		}
		return usuarioRetorno;
	}

	public String criptografiaMd5(String valor) {
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
	public Usuario salvar(Usuario usuario) {
		entityManager.merge(usuario);
		return usuario;
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}


}
