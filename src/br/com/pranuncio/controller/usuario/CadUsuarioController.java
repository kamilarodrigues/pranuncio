package br.com.pranuncio.controller.usuario;
 
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct; 
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import br.com.pranuncio.entity.Usuario; 
import br.com.pranuncio.service.UsuarioService; 
import br.com.pranuncio.util.Mensagem;

@Named
@ViewScoped
public class CadUsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioService usuarioService;
	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String salvar() {
		if (validarDados()) {
			usuario.setDatacadastro(new Date());
			String senha = usuarioService.criptografiaMd5(usuario.getSenha());
			usuario.setSenha(senha);
			usuario = usuarioService.salvar(usuario);
			Mensagem.lancarMensagemInfo("Bem vindo ao PR Anuncio!", "faça seu login para acessar os anúncios.");
			return "index";
		}
		return "";
	}

	public String cancelar() {
		return "index";
	}

	public boolean validarDados() {
		if (usuario.getEmail() == null || usuario.getEmail().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção! Campo e-mail não preenchido.", "");
			return false;
		} else if (usuario.getNome() == null || usuario.getNome().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção! Campo nome não preenchido.", "");
			return false;
		} else if (usuario.getUsuario() == null || usuario.getUsuario().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção! Campo usuario não preenchido.", "");
			return false;
		} else if (usuario.getSenha() == null || usuario.getSenha().length() == 0 || usuario.getConfirmarsenha() == null
				|| usuario.getConfirmarsenha().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção! Campo senha não preenchido.", "");
			return false;
		} else {
			if(!usuario.getSenha().equals(usuario.getConfirmarsenha())){
				Mensagem.lancarMensagemErro("Atenção! Senhas não conferem.", "");
				return false;
			}
		}
		return true;
	} 
}
