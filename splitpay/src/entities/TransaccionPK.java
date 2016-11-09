/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pablo
 */
@Embeddable
public class TransaccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @Column(name = "DEUDA_CUENTA_ID")
    private long deudaCuentaId;
    @Basic(optional = false)
    @Column(name = "DEUDA_USUARIO_ID")
    private int deudaUsuarioId;
    @Basic(optional = false)
    @Column(name = "DEUDA_ID_DEUDA")
    private short deudaIdDeuda;

    public TransaccionPK() {
    }

    public TransaccionPK(int id, long deudaCuentaId, int deudaUsuarioId, short deudaIdDeuda) {
        this.id = id;
        this.deudaCuentaId = deudaCuentaId;
        this.deudaUsuarioId = deudaUsuarioId;
        this.deudaIdDeuda = deudaIdDeuda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDeudaCuentaId() {
        return deudaCuentaId;
    }

    public void setDeudaCuentaId(long deudaCuentaId) {
        this.deudaCuentaId = deudaCuentaId;
    }

    public int getDeudaUsuarioId() {
        return deudaUsuarioId;
    }

    public void setDeudaUsuarioId(int deudaUsuarioId) {
        this.deudaUsuarioId = deudaUsuarioId;
    }

    public short getDeudaIdDeuda() {
        return deudaIdDeuda;
    }

    public void setDeudaIdDeuda(short deudaIdDeuda) {
        this.deudaIdDeuda = deudaIdDeuda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) deudaCuentaId;
        hash += (int) deudaUsuarioId;
        hash += (int) deudaIdDeuda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionPK)) {
            return false;
        }
        TransaccionPK other = (TransaccionPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.deudaCuentaId != other.deudaCuentaId) {
            return false;
        }
        if (this.deudaUsuarioId != other.deudaUsuarioId) {
            return false;
        }
        if (this.deudaIdDeuda != other.deudaIdDeuda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TransaccionPK[ id=" + id + ", deudaCuentaId=" + deudaCuentaId + ", deudaUsuarioId=" + deudaUsuarioId + ", deudaIdDeuda=" + deudaIdDeuda + " ]";
    }
    
}
