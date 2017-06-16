package br.com.pranuncio.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void lancarMensagemErro(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo+" "+msg, null));
	}
	
	public static void lancarMensagemFatal(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo+" "+msg, null));
	}
	
	public static void lancarMensagemInfo(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo+" "+msg, null));
	}
	
	public static void lancarMensagemWarn(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo+" "+msg, null));
	}

}
