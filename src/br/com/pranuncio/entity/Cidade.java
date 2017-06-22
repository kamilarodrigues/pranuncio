package br.com.pranuncio.entity;

import java.io.Serializable; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 

@SuppressWarnings("serial")
@Entity 
@NamedQueries({ 
	@NamedQuery(name="Cidade.findAll", query="select c from Cidade c order by c.nome"),
	@NamedQuery(name="Cidade.findById", query="select c from Cidade c where c.idcidade = :id"),
	@NamedQuery(name="Cidade.findAllByEstadoId", query="select c from Cidade c where c.estado.idestado = :idEstado")
})
public class Cidade implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcidade;   
	@Column
	private String nome; 
	@ManyToOne
	@JoinColumn(name="estado_idestado")
	private Estado estado;  
 

	public Integer getIdcidade() {
		return idcidade;
	}

	public void setIdcidade(Integer idcidade) {
		this.idcidade = idcidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idcidade != null ? idcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.idcidade == null && other.idcidade != null) || (this.idcidade != null && !this.idcidade.equals(other.idcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pranuncio.entity.Cidade[ idcidade=" + idcidade + " ]";
    } 
}
