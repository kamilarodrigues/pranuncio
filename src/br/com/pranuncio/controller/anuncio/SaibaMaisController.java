package br.com.pranuncio.controller.anuncio;
  
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext; 
import br.com.pranuncio.entity.Anuncio;
import br.com.pranuncio.service.AnuncioService; 

@Named
@ViewScoped
public class SaibaMaisController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private AnuncioService anuncioService; 
	private Anuncio anuncio; 

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		anuncio = (Anuncio) session.getAttribute("anuncio");
		session.removeAttribute("anuncio"); 
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
	 
	public String cancelar() {
		RequestContext.getCurrentInstance().closeDialog(null);
		return "";
	}
	
	public String retornarValorTotal(){
		NumberFormat format = new DecimalFormat("#,###.##");
		format.setMinimumFractionDigits(2);
		String valorFormatado = format.format(anuncio.getValor());
		valorFormatado = "R$ "+valorFormatado;
		return valorFormatado;
	}
}
