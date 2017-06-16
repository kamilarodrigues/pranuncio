/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    	FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 225); 
		RequestContext.getCurrentInstance().openDialog("dadosVendedor", options, null);
		return "";
	}   
}
