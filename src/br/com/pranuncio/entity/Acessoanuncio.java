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
import javax.persistence.NamedQuery; 

@SuppressWarnings("serial")
@Entity
@NamedQuery(name="Acessoanuncio.listar", query="select a from Acessoanuncio a "
		+ "where a.acesso.idacesso=:idacesso and a.dataacesso=':data'")
public class Acessoanuncio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idacessoanuncio;   
	@Column
	private Date dataacesso;
	@ManyToOne
	@JoinColumn(name="usuario_idusuario")
	private Usuario usuario;  
	@ManyToOne
	@JoinColumn(name="anuncio_idanuncio")
	private Anuncio anuncio;   

	public Integer getIdacessoanuncio() {
		return idacessoanuncio;
	}

	public void setIdacessoanuncio(Integer idacessoanuncio) {
		this.idacessoanuncio = idacessoanuncio;
	}

	public Date getDataacesso() {
		return dataacesso;
	}

	public void setDataacesso(Date dataacesso) {
		this.dataacesso = dataacesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idacessoanuncio != null ? idacessoanuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acessoanuncio)) {
            return false;
        }
        Acessoanuncio other = (Acessoanuncio) object;
        if ((this.idacessoanuncio == null && other.idacessoanuncio != null) || (this.idacessoanuncio != null && !this.idacessoanuncio.equals(other.idacessoanuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pranuncio.entity.Acessoanuncio[ idacessoanuncio=" + idacessoanuncio + " ]";
    } 
}
