/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pablo
 */
@Embeddable
public class PerteneceAPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private int usuarioId;
    @Basic(optional = false)
    @Column(name = "GRUPO_ID")
    private int grupoId;
    @Basic(optional = false)
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    public PerteneceAPK() {
    }

    public PerteneceAPK(int usuarioId, int grupoId, Date fechaIngreso) {
        this.usuarioId = usuarioId;
        this.grupoId = grupoId;
        this.fechaIngreso = fechaIngreso;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioId;
        hash += (int) grupoId;
        hash += (fechaIngreso != null ? fechaIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerteneceAPK)) {
            return false;
        }
        PerteneceAPK other = (PerteneceAPK) object;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.grupoId != other.grupoId) {
            return false;
        }
        if ((this.fechaIngreso == null && other.fechaIngreso != null) || (this.fechaIngreso != null && !this.fechaIngreso.equals(other.fechaIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PerteneceAPK[ usuarioId=" + usuarioId + ", grupoId=" + grupoId + ", fechaIngreso=" + fechaIngreso + " ]";
    }
    
}
