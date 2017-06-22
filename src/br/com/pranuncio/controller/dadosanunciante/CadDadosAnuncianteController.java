package br.com.pranuncio.controller.dadosanunciante;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct; 
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;  

import org.primefaces.context.RequestContext; 

import br.com.pranuncio.controller.LoginController; 
import br.com.pranuncio.entity.Cidade;
import br.com.pranuncio.entity.Dadosanunciante;
import br.com.pranuncio.entity.Estado; 
import br.com.pranuncio.service.CidadeService;
import br.com.pranuncio.service.DadosAnuncianteService;
import br.com.pranuncio.service.EstadoService;
import br.com.pranuncio.util.Mensagem;

@Named
@ViewScoped
public class CadDadosAnuncianteController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private DadosAnuncianteService dadosAnuncianteService;
	@Inject
	private EstadoService estadoService;
	@Inject
	private CidadeService cidadeService; 
	@Inject
	private LoginController loginController;
	private Dadosanunciante dadosanunciante;
	private Estado estado;
	private Cidade cidade;
	private List<Estado> listaEstado;
	private List<Cidade> listaCidade;

	@PostConstruct
	public void init() {
		dadosanunciante = dadosAnuncianteService.consultar("select d from Dadosanunciante d where d.usuario.idusuario="
				+loginController.getUsuario().getIdusuario()); 
		listaEstado = estadoService.listarEstados();
		listaCidade = new ArrayList<Cidade>();
		if (dadosanunciante == null) {
			dadosanunciante = new Dadosanunciante();
			dadosanunciante.setUsuario(loginController.getUsuario());
		}else{
			estado=dadosanunciante.getCidade().getEstado();
			listarCidade();
			cidade=dadosanunciante.getCidade();
		}
	}

	public DadosAnuncianteService getDadosAnuncianteService() {
		return dadosAnuncianteService;
	}

	public void setDadosAnuncianteService(DadosAnuncianteService dadosAnuncianteService) {
		this.dadosAnuncianteService = dadosAnuncianteService;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public Dadosanunciante getDadosanunciante() {
		return dadosanunciante;
	}

	public void setDadosanunciante(Dadosanunciante dadosanunciante) {
		this.dadosanunciante = dadosanunciante;
	}

	public EstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	} 

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public String salvar() {
		if (validarDados()) { 
			dadosanunciante.setCidade(cidade);
			dadosanunciante = dadosAnuncianteService.salvar(dadosanunciante); 
			Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
			RequestContext.getCurrentInstance().closeDialog(null);
		}
		return "";
	}

	public String cancelar() {
		RequestContext.getCurrentInstance().closeDialog(null);
		return "";
	}

	public boolean validarDados() {
		if (estado==null || estado.getIdestado()==null) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo estado não preenchido.");
			return false;
		}else if (cidade==null || cidade.getIdcidade()==null) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo cidade não preenchido.");
			return false;
		}else if (dadosanunciante.getBairro()==null || dadosanunciante.getBairro().length()==0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo bairro não preenchido.");
			return false;
		}else if (dadosanunciante.getEntrega()==null || dadosanunciante.getEntrega().length()==0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo entrega não preenchido.");
			return false;
		}else if (dadosanunciante.getTelefone()==null || dadosanunciante.getTelefone().length()==0) {
			Mensagem.lancarMensagemErro("Atenção!", "Campo telefone não preenchido.");
			return false;
		}         
		return true;
	}
 
	public void listarCidade() {
		if (estado != null) {
			listaCidade = cidadeService.listarCidadesPorEstado(estado.getIdestado());
		} else {
			listaCidade = new ArrayList<>();
		}
	}
}
