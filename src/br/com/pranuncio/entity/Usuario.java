package br.com.pranuncio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@NamedQuery(name="Usuario.findByNomeSenha", query="select u from Usuario u "
		+ "where u.usuario=:usuario and u.senha=:senha")
public class Usuario implements Serializable {
	
	@Transient
	public static final String FIND_BY_NOME_SENHA = "Usuario.findByNomeSenha";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idusuario;
	@Column
	private String tipo; 
	@Column
	private String nome; 
	@Column
	private String usuario; 
	@Column
	private String senha;
	@Column
	private String email;
	@Column
	private Date datacadastro;
	@Transient
	private String confirmarsenha;
 

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	} 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	public String getConfirmarsenha() {
		return confirmarsenha;
	}

	public void setConfirmarsenha(String confirmarsenha) {
		this.confirmarsenha = confirmarsenha;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pranuncio.entity.Usuario[ idusuario=" + idusuario + " ]";
    } 
}
