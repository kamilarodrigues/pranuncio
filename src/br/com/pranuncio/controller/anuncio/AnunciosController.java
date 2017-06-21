package br.com.pranuncio.controller.anuncio;
   
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class AnunciosController implements Serializable {

	private static final long serialVersionUID = 1L; 
	@Inject
	private LoginController loginController; 
	@Inject
	private AnuncioService anuncioService;
	private List<Anuncio> listaAnuncios; 
	private String categoria;
	private String titulodescricao;
	private Date dataanuncioinicial;
	private Date dataanunciofinal;
	private float valorinicial;
	private float valorfinal;
	
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


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getTitulodescricao() {
		return titulodescricao;
	}


	public void setTitulodescricao(String titulodescricao) {
		this.titulodescricao = titulodescricao;
	}


	public Date getDataanuncioinicial() {
		return dataanuncioinicial;
	}


	public void setDataanuncioinicial(Date dataanuncioinicial) {
		this.dataanuncioinicial = dataanuncioinicial;
	}


	public Date getDataanunciofinal() {
		return dataanunciofinal;
	}


	public void setDataanunciofinal(Date dataanunciofinal) {
		this.dataanunciofinal = dataanunciofinal;
	}


	public float getValorinicial() {
		return valorinicial;
	}


	public void setValorinicial(float valorinicial) {
		this.valorinicial = valorinicial;
	}


	public float getValorfinal() {
		return valorfinal;
	}


	public void setValorfinal(float valorfinal) {
		this.valorfinal = valorfinal;
	}
	
	public String cadAnuncio() {  
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 550); 
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("cadAnuncio", options, null);
		return "";
	}   
	
	public void listarAnuncios(){
		String sql = "Select a From Anuncio a"
				+ " where a.habilitado=TRUE and a.vendido=FALSE order by a.valor, a.dataanuncio desc";
		listaAnuncios = anuncioService.meusAnuncios(sql);
		if(listaAnuncios==null && listaAnuncios.size()==0){
			listaAnuncios = new ArrayList<>();
		}else{
			int idusuario = loginController.getUsuario().getIdusuario();
			for (int i = 0; i < listaAnuncios.size(); i++) {
				if(listaAnuncios.get(i).getUsuario().getIdusuario()==idusuario){
					listaAnuncios.get(i).setResponsavelanuncio(true);
				}
			}
		}
	} 
	
	public void pesquisar(){
		String sql = "Select a From Anuncio a"
				+ " where a.habilitado=TRUE and a.vendido=FALSE";
		if(categoria!=null && categoria.length()>2){
			sql = sql + " and a.categoria='"+categoria+"'";
		}
		if(titulodescricao!=null && titulodescricao.length()>2){
			sql = sql + " and (a.titulo like '%"+titulodescricao+"%' or a.descricao like '%"+titulodescricao+"%')";
		}
		if(dataanuncioinicial!=null && dataanunciofinal!=null){
			sql = sql + " and a.dataanuncio>='"+dataanuncioinicial+"' and a.dataanuncio<='"+dataanunciofinal+"'";
		}
		if(valorinicial>=0 && valorfinal>0){
			sql = sql + " and a.valor>="+valorinicial+" and a.valor<="+valorfinal;
		}
		sql = sql +" order by a.valor, a.dataanuncio desc";
		listaAnuncios = anuncioService.meusAnuncios(sql);
		if(listaAnuncios==null && listaAnuncios.size()==0){
			listaAnuncios = new ArrayList<>();
		}else{
			int idusuario = loginController.getUsuario().getIdusuario();
			for (int i = 0; i < listaAnuncios.size(); i++) {
				if(listaAnuncios.get(i).getUsuario().getIdusuario()==idusuario){
					listaAnuncios.get(i).setResponsavelanuncio(true);
				}
			}
		}
	} 
	
	public String editar(Anuncio anuncio) { 
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("anuncio", anuncio);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 550); 
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("cadAnuncio", options, null);
		return "";
	} 
	
	public String retornarValorTotal(float valor){
		NumberFormat format = new DecimalFormat("#,###.##");
		format.setMinimumFractionDigits(2);
		String valorFormatado = format.format(valor);
		valorFormatado = "R$"+valorFormatado;
		return valorFormatado;
	}
	
	public void limpar(){
		titulodescricao = null;
		dataanunciofinal=null;
		dataanuncioinicial=null;
		valorinicial=0;
		valorinicial=0;
		listarAnuncios();
	}
}
