package br.com.pranuncio.controller.anuncio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.pranuncio.controller.LoginController;
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
	private UploadedFile file;
	private String nomeArquivo;
	private FileUploadEvent ex;
	private String caminho;

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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public FileUploadEvent getEx() {
		return ex;
	}

	public void setEx(FileUploadEvent ex) {
		this.ex = ex;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String salvar() {
		if (validarDados()) {
			if (anuncio.getIdanuncio() == null) {
				anuncio.setUsuario(loginController.getUsuario());
				anuncio.setDataanuncio(new Date());
				anuncio = anuncioService.incluir(anuncio);
			} else {
				anuncio = anuncioService.alterar(anuncio);
			}
			if (file != null) {
				anuncio.setImagem(anuncio.getIdanuncio() + ".png");
				anuncio = anuncioService.alterar(anuncio);
			}
			salvarArquivo();
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

	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		Mensagem.lancarMensagemInfo("Upload efetuado!", "");
	}

	public void salvarArquivo() {
		nomeArquivo = anuncio.getIdanuncio() + ".png";
		enviarArquivo();
	}

	public void enviarArquivo() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext request = (ServletContext) facesContext.getExternalContext().getContext();
		String pasta = request.getRealPath("");
		pasta = pasta + "\\resources\\img\\anuncios\\" + nomeArquivo; 
		try { 
			FileOutputStream arquivo = new FileOutputStream(pasta); 
			arquivo.flush();
			arquivo.write(file.getContents()); 
			arquivo.close();
			Mensagem.lancarMensagemInfo("Upload salvo com sucesso!", "");
		} catch (IOException e) {
			e.printStackTrace();
			Mensagem.lancarMensagemInfo("Erro ao fazer upload!", "");
		}
	}

}
