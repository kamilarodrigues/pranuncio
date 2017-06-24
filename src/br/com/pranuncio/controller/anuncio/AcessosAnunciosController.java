package br.com.pranuncio.controller.anuncio;

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries; 

import br.com.pranuncio.entity.Acessoanuncio;
import br.com.pranuncio.entity.Anuncio;
import br.com.pranuncio.service.AcessoAnuncioService;
import br.com.pranuncio.util.Mensagem; 

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class AcessosAnunciosController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private AcessoAnuncioService acessoAnuncioService; 
	private Anuncio anuncio;
	private List<AcessosBean> listaAcessosAnuncio;
	private LineChartModel areaModel;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		anuncio = (Anuncio) session.getAttribute("anuncio");
		listarAcessos();
		criarGrafico();
	}

	public AcessoAnuncioService getAcessoAnuncioService() {
		return acessoAnuncioService;
	}

	public void setAcessoAnuncioService(AcessoAnuncioService acessoAnuncioService) {
		this.acessoAnuncioService = acessoAnuncioService;
	}
 
	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public List<AcessosBean> getListaAcessosAnuncio() {
		return listaAcessosAnuncio;
	}

	public void setListaAcessosAnuncio(List<AcessosBean> listaAcessosAnuncio) {
		this.listaAcessosAnuncio = listaAcessosAnuncio;
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	public void setAreaModel(LineChartModel areaModel) {
		this.areaModel = areaModel;
	}

	public void listarAcessos() {
		listaAcessosAnuncio = new ArrayList<>();
		String sql = "Select a From Acessoanuncio a"
				+ " where a.anuncio.idanuncio="+anuncio.getIdanuncio()
				+ " group by a.dataacesso";
		List<Acessoanuncio> listaDatas = acessoAnuncioService.listar(sql);
		if (listaDatas != null) {
			List<Acessoanuncio> listaAcessos = new ArrayList<>();
			for (int i = 0; i < listaDatas.size(); i++) {
				sql = "Select a From Acessoanuncio a"
						+ " where a.anuncio.idanuncio="+anuncio.getIdanuncio()
						+ " and a.dataacesso='"+Mensagem.ConvercaoDataSQL(listaDatas.get(i).getDataacesso())+"'";
				listaAcessos = acessoAnuncioService.listar(sql);
				if (listaAcessos != null && listaAcessos.size()>0) {
					AcessosBean acessosBean = new AcessosBean();
					acessosBean.setData(Mensagem.ConvercaoData(listaDatas.get(i).getDataacesso()));
					acessosBean.setNum(listaAcessos.size());
					listaAcessosAnuncio.add(acessosBean);
				}
			} 
		}
	}
	
	public void criarGrafico(){
		if(listaAcessosAnuncio!=null && listaAcessosAnuncio.size()>0){
			areaModel = new LineChartModel();
			 
	        LineChartSeries acesso = new LineChartSeries();
	        acesso.setFill(true);
	        acesso.setLabel("Acessos");
	        for (int i = 0; i < listaAcessosAnuncio.size(); i++) {
				acesso.set(listaAcessosAnuncio.get(i).getData(), listaAcessosAnuncio.get(i).getNum());
			}
	        areaModel.addSeries(acesso); 
	         
	        areaModel.setTitle("Acessos no anuncio: "+anuncio.getTitulo());
	        areaModel.setLegendPosition("ne");
	        areaModel.setStacked(true);
	        areaModel.setShowPointLabels(true);
	         
	        Axis xAxis = new CategoryAxis("Data"); 
	        areaModel.getAxes().put(AxisType.X, xAxis);
	        Axis yAxis = areaModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Nº de acessos");
	        yAxis.setMin(0);
	        if(listaAcessosAnuncio.size()>50){
	        	yAxis.setMax(100);
	        }else if(listaAcessosAnuncio.size()>30){
	        	yAxis.setMax(50);
	        }else if(listaAcessosAnuncio.size()>15){
	        	yAxis.setMax(30);
	        }else{
	        	yAxis.setMax(15);
	        }
		}
	}

	public String voltar(){
		return "meusAnuncios";
	}
}
