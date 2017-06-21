package br.com.pranuncio.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.pranuncio.entity.Anuncio;
import br.com.pranuncio.service.AnuncioService;
import br.com.pranuncio.util.Mensagem;

@Named
@ViewScoped
public class CadAnuncioController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private AnuncioService anuncioService;
	@Inject
	private LoginController loginController;
	private Anuncio anuncio;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		anuncio = (Anuncio) session.getAttribute("anuncio");
		session.removeAttribute("anuncio");
		if (anuncio == null) {
			anuncio = new Anuncio();
		}
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public AnuncioService getAnuncioService() {
		return anuncioService;
	}

	public void setAnuncioService(AnuncioService anuncioService) {
		this.anuncioService = anuncioService;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public String salvar() {
		if (validarDados()) {  
			if (anuncio.getIdanuncio() == null) {
				anuncio.setUsuario(loginController.getUsuario());
				anuncio.setDataanuncio(new Date());
				anuncioService.incluir(anuncio);
			}else{
				anuncioService.alterar(anuncio);
			} 
			Mensagem.lancarMensagemInfo("Anuncio Cadastrado com sucesso!", "");
			RequestContext.getCurrentInstance().closeDialog(null);
		}
		return "";
	}

	public String cancelar() {
		RequestContext.getCurrentInstance().closeDialog(null);
		return "";
	}

	public boolean validarDados() {
		if (anuncio.getDescricao() == null || anuncio.getDescricao().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo descrição não preenchido.");
			return false;
		} else if (anuncio.getTitulo() == null || anuncio.getTitulo().length() == 0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo titulo não preenchido.");
			return false;
		} else if (anuncio.getValor() < 0 || anuncio.getValor() == 0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo valor não pode ser zero.");
			return false;
		}
		return true;
	}

}
