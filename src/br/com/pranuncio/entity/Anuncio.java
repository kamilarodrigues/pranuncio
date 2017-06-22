package br.com.pranuncio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "Anuncio.consultarHabilitados", query = "select a from Anuncio a "
		+ "where a.habilitado=TRUE and a.vendido=FALSE") })
public class Anuncio implements Serializable {

	@Transient
	public static final String CONSULTAR_HABILITADOS = "Usuario.consultarHabilitados";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idanuncio;
	@Column
	private float valor;
	@Column
	private int estoque;
	@Column
	private boolean habilitado;
	@Column
	private boolean vendido;
	@Column
	private String titulo;
	@Column
	private String descricao;
	@Column
	private Date dataanuncio;
	@Column
	private String categoria;
	@Column
	private String imagem;
	@ManyToOne
	@JoinColumn(name = "usuario_idusuario")
	private Usuario usuario;
	@Transient
	private boolean responsavelanuncio;

	public Integer getIdanuncio() {
		return idanuncio;
	}

	public void setIdanuncio(Integer idanuncio) {
		this.idanuncio = idanuncio;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean isVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataanuncio() {
		return dataanuncio;
	}

	public void setDataanuncio(Date dataanuncio) {
		this.dataanuncio = dataanuncio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isResponsavelanuncio() {
		return responsavelanuncio;
	}

	public void setResponsavelanuncio(boolean responsavelanuncio) {
		this.responsavelanuncio = responsavelanuncio;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idanuncio != null ? idanuncio.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Anuncio)) {
			return false;
		}
		Anuncio other = (Anuncio) object;
		if ((this.idanuncio == null && other.idanuncio != null)
				|| (this.idanuncio != null && !this.idanuncio.equals(other.idanuncio))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pranuncio.entity.Anuncio[ idanuncio=" + idanuncio + " ]";
	}
}
