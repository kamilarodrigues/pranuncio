package br.com.pranuncio.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void lancarMensagemErro(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, msg));
	}
	
	public static void lancarMensagemFatal(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, msg));
	}
	
	public static void lancarMensagemInfo(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg));
	}
	
	public static void lancarMensagemWarn(String titulo, String msg){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, msg));
	}
	
	public static String ConvercaoData(Date data) {
		if (data == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = df.format(data);
		return dataFormatada;
	}
	
	public static String ConvercaoDataSQL(Date data) {
		if (data == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = df.format(data);
		return dataFormatada;
	}

}
