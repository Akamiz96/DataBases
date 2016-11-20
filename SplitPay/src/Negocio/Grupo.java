/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")
    , @NamedQuery(name = "Grupo.findById", query = "SELECT g FROM Grupo g WHERE g.id = :id")
    , @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre")
    , @NamedQuery(name = "Grupo.findByFechaCreacion", query = "SELECT g FROM Grupo g WHERE g.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Grupo.findByDisuelto", query = "SELECT g FROM Grupo g WHERE g.disuelto = :disuelto")
    , @NamedQuery(name = "Grupo.findByRetenido", query = "SELECT g FROM Grupo g WHERE g.retenido = :retenido")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "DISUELTO")
    private Character disuelto;
    @Basic(optional = false)
    @Column(name = "RETENIDO")
    private Character retenido;
    @JoinColumn(name = "USUARIODUENO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioduenoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoId", fetch = FetchType.LAZY)
    private List<Cuenta> cuentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<PerteneceA> perteneceAList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Lidergrupo> lidergrupoList;

    public Grupo() {
    }

    public Grupo(Integer id) {
        this.id = id;
    }

    public Grupo(Integer id, String nombre, Date fechaCreacion, Character disuelto, Character retenido) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.disuelto = disuelto;
        this.retenido = retenido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Character getDisuelto() {
        return disuelto;
    }

    public void setDisuelto(Character disuelto) {
        this.disuelto = disuelto;
    }

    public Character getRetenido() {
        return retenido;
    }

    public void setRetenido(Character retenido) {
        this.retenido = retenido;
    }

    public Usuario getUsuarioduenoId() {
        return usuarioduenoId;
    }

    public void setUsuarioduenoId(Usuario usuarioduenoId) {
        this.usuarioduenoId = usuarioduenoId;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @XmlTransient
    public List<PerteneceA> getPerteneceAList() {
        return perteneceAList;
    }

    public void setPerteneceAList(List<PerteneceA> perteneceAList) {
        this.perteneceAList = perteneceAList;
    }

    @XmlTransient
    public List<Lidergrupo> getLidergrupoList() {
        return lidergrupoList;
    }

    public void setLidergrupoList(List<Lidergrupo> lidergrupoList) {
        this.lidergrupoList = lidergrupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.Grupo[ id=" + id + " ]";
    }
    
}
