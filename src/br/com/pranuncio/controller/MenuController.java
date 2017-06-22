package br.com.pranuncio.controller;
  
import java.io.Serializable; 
import java.util.HashMap; 
import java.util.Map; 
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext; 
import javax.inject.Inject;
import javax.inject.Named; 
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.pranuncio.controller.LoginController; 

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L; 
	@Inject
	private LoginController loginController; 
  
	
	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public String inicial() {
		return "inicial";
	} 
	
	public String meusAnuncios() {
		return "meusAnuncios";
	} 
    
    public String anunciosDisponiveis() {
		return "anunciosDisponiveis";
	}
     
    public String dadosVendedor() {  
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 480); 
		RequestContext.getCurrentInstance().openDialog("cadDadosAnunciante", options, null);
		return "";
	}   
}
