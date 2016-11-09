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
public class LidergrupoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FECHAINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;
    @Basic(optional = false)
    @Column(name = "GRUPO_ID")
    private int grupoId;
    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private int usuarioId;

    public LidergrupoPK() {
    }

    public LidergrupoPK(Date fechaingreso, int grupoId, int usuarioId) {
        this.fechaingreso = fechaingreso;
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaingreso != null ? fechaingreso.hashCode() : 0);
        hash += (int) grupoId;
        hash += (int) usuarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LidergrupoPK)) {
            return false;
        }
        LidergrupoPK other = (LidergrupoPK) object;
        if ((this.fechaingreso == null && other.fechaingreso != null) || (this.fechaingreso != null && !this.fechaingreso.equals(other.fechaingreso))) {
            return false;
        }
        if (this.grupoId != other.grupoId) {
            return false;
        }
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LidergrupoPK[ fechaingreso=" + fechaingreso + ", grupoId=" + grupoId + ", usuarioId=" + usuarioId + " ]";
    }
    
}
