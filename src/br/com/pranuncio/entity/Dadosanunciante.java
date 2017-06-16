package br.com.pranuncio.entity;

import java.io.Serializable; 

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
@NamedQuery(name="Dadosanunciante.listar", query="select d from Dadosanunciante d "
		+ "where d.usuario.idusuario=:idusuario")
public class Dadosanunciante implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer iddadosanunciante;   
	@Column
	private String bairro;
	@Column
	private String telefone;
	@Column
	private String entrega;
	@ManyToOne
	@JoinColumn(name="usuario_idusuario")
	private Usuario usuario;  
	@ManyToOne
	@JoinColumn(name="cidade_idcidade")
	private Cidade cidade;    

	public Integer getIddadosanunciante() {
		return iddadosanunciante;
	}

	public void setIddadosanunciante(Integer iddadosanunciante) {
		this.iddadosanunciante = iddadosanunciante;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (iddadosanunciante != null ? iddadosanunciante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dadosanunciante)) {
            return false;
        }
        Dadosanunciante other = (Dadosanunciante) object;
        if ((this.iddadosanunciante == null && other.iddadosanunciante != null) || (this.iddadosanunciante != null && !this.iddadosanunciante.equals(other.iddadosanunciante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pranuncio.entity.Dadosanunciante[ iddadosanunciante=" + iddadosanunciante + " ]";
    } 
}
