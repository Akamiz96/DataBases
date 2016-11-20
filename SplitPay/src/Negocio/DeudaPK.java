/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pablo
 */
@Embeddable
public class DeudaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private int usuarioId;
    @Basic(optional = false)
    @Column(name = "CUENTA_ID")
    private long cuentaId;
    @Basic(optional = false)
    @Column(name = "ID_DEUDA")
    private short idDeuda;

    public DeudaPK() {
    }

    public DeudaPK(int usuarioId, long cuentaId, short idDeuda) {
        this.usuarioId = usuarioId;
        this.cuentaId = cuentaId;
        this.idDeuda = idDeuda;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public short getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(short idDeuda) {
        this.idDeuda = idDeuda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioId;
        hash += (int) cuentaId;
        hash += (int) idDeuda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeudaPK)) {
            return false;
        }
        DeudaPK other = (DeudaPK) object;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.cuentaId != other.cuentaId) {
            return false;
        }
        if (this.idDeuda != other.idDeuda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.DeudaPK[ usuarioId=" + usuarioId + ", cuentaId=" + cuentaId + ", idDeuda=" + idDeuda + " ]";
    }
    
}
