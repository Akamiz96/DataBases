/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "PERTENECE_A")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerteneceA.findAll", query = "SELECT p FROM PerteneceA p")
    , @NamedQuery(name = "PerteneceA.findByUsuarioId", query = "SELECT p FROM PerteneceA p WHERE p.perteneceAPK.usuarioId = :usuarioId")
    , @NamedQuery(name = "PerteneceA.findByGrupoId", query = "SELECT p FROM PerteneceA p WHERE p.perteneceAPK.grupoId = :grupoId")
    , @NamedQuery(name = "PerteneceA.findByFechaIngreso", query = "SELECT p FROM PerteneceA p WHERE p.perteneceAPK.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "PerteneceA.findByFechaSalida", query = "SELECT p FROM PerteneceA p WHERE p.fechaSalida = :fechaSalida")})
public class PerteneceA implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerteneceAPK perteneceAPK;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @JoinColumn(name = "GRUPO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupo grupo;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public PerteneceA() {
    }

    public PerteneceA(PerteneceAPK perteneceAPK) {
        this.perteneceAPK = perteneceAPK;
    }

    public PerteneceA(int usuarioId, int grupoId, Date fechaIngreso) {
        this.perteneceAPK = new PerteneceAPK(usuarioId, grupoId, fechaIngreso);
    }

    public PerteneceAPK getPerteneceAPK() {
        return perteneceAPK;
    }

    public void setPerteneceAPK(PerteneceAPK perteneceAPK) {
        this.perteneceAPK = perteneceAPK;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
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
        hash += (perteneceAPK != null ? perteneceAPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerteneceA)) {
            return false;
        }
        PerteneceA other = (PerteneceA) object;
        if ((this.perteneceAPK == null && other.perteneceAPK != null) || (this.perteneceAPK != null && !this.perteneceAPK.equals(other.perteneceAPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.PerteneceA[ perteneceAPK=" + perteneceAPK + " ]";
    }
    
}
