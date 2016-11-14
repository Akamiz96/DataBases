/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

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
public class EsAprobadaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TRANSACCION_ID")
    private int transaccionId;
    @Basic(optional = false)
    @Column(name = "TRANSACCION_DEUDA_CUENTA_ID")
    private long transaccionDeudaCuentaId;
    @Basic(optional = false)
    @Column(name = "TRANSACCION_DEUDA_USUARIO_ID")
    private int transaccionDeudaUsuarioId;
    @Basic(optional = false)
    @Column(name = "TRANSACCION_DEUDA_ID_DEUDA")
    private short transaccionDeudaIdDeuda;
    @Basic(optional = false)
    @Column(name = "LIDERGRUPO_FECHAINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lidergrupoFechaingreso;
    @Basic(optional = false)
    @Column(name = "LIDERGRUPO_GRUPO_ID")
    private int lidergrupoGrupoId;
    @Basic(optional = false)
    @Column(name = "LIDERGRUPO_USUARIO_ID")
    private int lidergrupoUsuarioId;

    public EsAprobadaPK() {
    }

    public EsAprobadaPK(int transaccionId, long transaccionDeudaCuentaId, int transaccionDeudaUsuarioId, short transaccionDeudaIdDeuda, Date lidergrupoFechaingreso, int lidergrupoGrupoId, int lidergrupoUsuarioId) {
        this.transaccionId = transaccionId;
        this.transaccionDeudaCuentaId = transaccionDeudaCuentaId;
        this.transaccionDeudaUsuarioId = transaccionDeudaUsuarioId;
        this.transaccionDeudaIdDeuda = transaccionDeudaIdDeuda;
        this.lidergrupoFechaingreso = lidergrupoFechaingreso;
        this.lidergrupoGrupoId = lidergrupoGrupoId;
        this.lidergrupoUsuarioId = lidergrupoUsuarioId;
    }

    public int getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(int transaccionId) {
        this.transaccionId = transaccionId;
    }

    public long getTransaccionDeudaCuentaId() {
        return transaccionDeudaCuentaId;
    }

    public void setTransaccionDeudaCuentaId(long transaccionDeudaCuentaId) {
        this.transaccionDeudaCuentaId = transaccionDeudaCuentaId;
    }

    public int getTransaccionDeudaUsuarioId() {
        return transaccionDeudaUsuarioId;
    }

    public void setTransaccionDeudaUsuarioId(int transaccionDeudaUsuarioId) {
        this.transaccionDeudaUsuarioId = transaccionDeudaUsuarioId;
    }

    public short getTransaccionDeudaIdDeuda() {
        return transaccionDeudaIdDeuda;
    }

    public void setTransaccionDeudaIdDeuda(short transaccionDeudaIdDeuda) {
        this.transaccionDeudaIdDeuda = transaccionDeudaIdDeuda;
    }

    public Date getLidergrupoFechaingreso() {
        return lidergrupoFechaingreso;
    }

    public void setLidergrupoFechaingreso(Date lidergrupoFechaingreso) {
        this.lidergrupoFechaingreso = lidergrupoFechaingreso;
    }

    public int getLidergrupoGrupoId() {
        return lidergrupoGrupoId;
    }

    public void setLidergrupoGrupoId(int lidergrupoGrupoId) {
        this.lidergrupoGrupoId = lidergrupoGrupoId;
    }

    public int getLidergrupoUsuarioId() {
        return lidergrupoUsuarioId;
    }

    public void setLidergrupoUsuarioId(int lidergrupoUsuarioId) {
        this.lidergrupoUsuarioId = lidergrupoUsuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) transaccionId;
        hash += (int) transaccionDeudaCuentaId;
        hash += (int) transaccionDeudaUsuarioId;
        hash += (int) transaccionDeudaIdDeuda;
        hash += (lidergrupoFechaingreso != null ? lidergrupoFechaingreso.hashCode() : 0);
        hash += (int) lidergrupoGrupoId;
        hash += (int) lidergrupoUsuarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsAprobadaPK)) {
            return false;
        }
        EsAprobadaPK other = (EsAprobadaPK) object;
        if (this.transaccionId != other.transaccionId) {
            return false;
        }
        if (this.transaccionDeudaCuentaId != other.transaccionDeudaCuentaId) {
            return false;
        }
        if (this.transaccionDeudaUsuarioId != other.transaccionDeudaUsuarioId) {
            return false;
        }
        if (this.transaccionDeudaIdDeuda != other.transaccionDeudaIdDeuda) {
            return false;
        }
        if ((this.lidergrupoFechaingreso == null && other.lidergrupoFechaingreso != null) || (this.lidergrupoFechaingreso != null && !this.lidergrupoFechaingreso.equals(other.lidergrupoFechaingreso))) {
            return false;
        }
        if (this.lidergrupoGrupoId != other.lidergrupoGrupoId) {
            return false;
        }
        if (this.lidergrupoUsuarioId != other.lidergrupoUsuarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.EsAprobadaPK[ transaccionId=" + transaccionId + ", transaccionDeudaCuentaId=" + transaccionDeudaCuentaId + ", transaccionDeudaUsuarioId=" + transaccionDeudaUsuarioId + ", transaccionDeudaIdDeuda=" + transaccionDeudaIdDeuda + ", lidergrupoFechaingreso=" + lidergrupoFechaingreso + ", lidergrupoGrupoId=" + lidergrupoGrupoId + ", lidergrupoUsuarioId=" + lidergrupoUsuarioId + " ]";
    }
    
}
