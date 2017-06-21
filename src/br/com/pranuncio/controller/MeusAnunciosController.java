package br.com.pranuncio.controller;
   
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.pranuncio.controller.LoginController;
import br.com.pranuncio.entity.Anuncio;
import br.com.pranuncio.service.AnuncioService; 

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class MeusAnunciosController implements Serializable {

	private static final long serialVersionUID = 1L; 
	@Inject
	private LoginController loginController; 
	@Inject
	private AnuncioService anuncioService;
	private List<Anuncio> listaAnuncios;
	
	@PostConstruct
	public void init() {
		listarAnuncios();
	}
  
	
	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}  
       
    public List<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	public void setListaAnuncios(List<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}

	public AnuncioService getAnuncioService() {
		return anuncioService;
	}


	public void setAnuncioService(AnuncioService anuncioService) {
		this.anuncioService = anuncioService;
	}


	public String cadAnuncio() {  
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 550); 
		RequestContext.getCurrentInstance().openDialog("cadAnuncio", options, null);
		return "";
	}   
	
	public void listarAnuncios(){
		String sql = "Select a From Anuncio a"
				+ " where a.usuario.idusuario="+loginController.getUsuario().getIdusuario()+" order by a.dataanuncio desc";
		listaAnuncios = anuncioService.meusAnuncios(sql);
		if(listaAnuncios==null){
			listaAnuncios = new ArrayList<>();
		}
	} 
	
	public String editar(Anuncio anuncio) { 
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("anuncio", anuncio);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 550); 
		RequestContext.getCurrentInstance().openDialog("cadAnuncio", options, null);
		return "";
	}
	
	public String visualizacoes(Anuncio anuncio) { 
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("anuncio", anuncio); 
		return "";
	}
}
