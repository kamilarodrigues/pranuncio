package br.com.pranuncio.controller;

import java.io.Serializable; 
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pranuncio.entity.Usuario;
import br.com.pranuncio.service.UsuarioService; 
import br.com.pranuncio.util.Mensagem; 

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginController implements Serializable {

	@Inject
	private UsuarioService usuarioService;
	private Usuario usuario; 
	private boolean logado;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	 
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	public String cadastroUsuario(){
		return "cadUsuario";
	}

	public String login() { 
		usuario = usuarioService.pesquisarPorNomeSenha(usuario);
		if (usuario!=null) {
			logado = true;
			return "inicial";
		} else {
			usuario = new Usuario();
			logado = false;
			Mensagem.lancarMensagemErro("Atenção!", "Usuário ou senha inválidos."); 
	        return "";
		} 
	}
	
	public String deslogar() {
		Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.clear();
		logado=false;
		return "index";
	}
 
}
