/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "LIDERGRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lidergrupo.findAll", query = "SELECT l FROM Lidergrupo l")
    , @NamedQuery(name = "Lidergrupo.findByFechaingreso", query = "SELECT l FROM Lidergrupo l WHERE l.lidergrupoPK.fechaingreso = :fechaingreso")
    , @NamedQuery(name = "Lidergrupo.findByFechasalida", query = "SELECT l FROM Lidergrupo l WHERE l.fechasalida = :fechasalida")
    , @NamedQuery(name = "Lidergrupo.findByGrupoId", query = "SELECT l FROM Lidergrupo l WHERE l.lidergrupoPK.grupoId = :grupoId")
    , @NamedQuery(name = "Lidergrupo.findByUsuarioId", query = "SELECT l FROM Lidergrupo l WHERE l.lidergrupoPK.usuarioId = :usuarioId")})
public class Lidergrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LidergrupoPK lidergrupoPK;
    @Column(name = "FECHASALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lidergrupo", fetch = FetchType.LAZY)
    private List<EsAprobada> esAprobadaList;
    @JoinColumn(name = "GRUPO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupo grupo;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Lidergrupo() {
    }

    public Lidergrupo(LidergrupoPK lidergrupoPK) {
        this.lidergrupoPK = lidergrupoPK;
    }

    public Lidergrupo(Date fechaingreso, int grupoId, int usuarioId) {
        this.lidergrupoPK = new LidergrupoPK(fechaingreso, grupoId, usuarioId);
    }

    public LidergrupoPK getLidergrupoPK() {
        return lidergrupoPK;
    }

    public void setLidergrupoPK(LidergrupoPK lidergrupoPK) {
        this.lidergrupoPK = lidergrupoPK;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    @XmlTransient
    public List<EsAprobada> getEsAprobadaList() {
        return esAprobadaList;
    }

    public void setEsAprobadaList(List<EsAprobada> esAprobadaList) {
        this.esAprobadaList = esAprobadaList;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lidergrupoPK != null ? lidergrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lidergrupo)) {
            return false;
        }
        Lidergrupo other = (Lidergrupo) object;
        if ((this.lidergrupoPK == null && other.lidergrupoPK != null) || (this.lidergrupoPK != null && !this.lidergrupoPK.equals(other.lidergrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.Lidergrupo[ lidergrupoPK=" + lidergrupoPK + " ]";
    }
    
}
