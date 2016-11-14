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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "ES_APROBADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsAprobada.findAll", query = "SELECT e FROM EsAprobada e")
    , @NamedQuery(name = "EsAprobada.findByTransaccionId", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.transaccionId = :transaccionId")
    , @NamedQuery(name = "EsAprobada.findByTransaccionDeudaCuentaId", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.transaccionDeudaCuentaId = :transaccionDeudaCuentaId")
    , @NamedQuery(name = "EsAprobada.findByTransaccionDeudaUsuarioId", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.transaccionDeudaUsuarioId = :transaccionDeudaUsuarioId")
    , @NamedQuery(name = "EsAprobada.findByTransaccionDeudaIdDeuda", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.transaccionDeudaIdDeuda = :transaccionDeudaIdDeuda")
    , @NamedQuery(name = "EsAprobada.findByLidergrupoFechaingreso", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.lidergrupoFechaingreso = :lidergrupoFechaingreso")
    , @NamedQuery(name = "EsAprobada.findByLidergrupoGrupoId", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.lidergrupoGrupoId = :lidergrupoGrupoId")
    , @NamedQuery(name = "EsAprobada.findByLidergrupoUsuarioId", query = "SELECT e FROM EsAprobada e WHERE e.esAprobadaPK.lidergrupoUsuarioId = :lidergrupoUsuarioId")
    , @NamedQuery(name = "EsAprobada.findByFechaAprobacion", query = "SELECT e FROM EsAprobada e WHERE e.fechaAprobacion = :fechaAprobacion")})
public class EsAprobada implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EsAprobadaPK esAprobadaPK;
    @Basic(optional = false)
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @JoinColumns({
        @JoinColumn(name = "LIDERGRUPO_FECHAINGRESO", referencedColumnName = "FECHAINGRESO", insertable = false, updatable = false)
        , @JoinColumn(name = "LIDERGRUPO_GRUPO_ID", referencedColumnName = "GRUPO_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "LIDERGRUPO_USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Lidergrupo lidergrupo;
    @JoinColumns({
        @JoinColumn(name = "TRANSACCION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
        , @JoinColumn(name = "TRANSACCION_DEUDA_CUENTA_ID", referencedColumnName = "DEUDA_CUENTA_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "TRANSACCION_DEUDA_USUARIO_ID", referencedColumnName = "DEUDA_USUARIO_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "TRANSACCION_DEUDA_ID_DEUDA", referencedColumnName = "DEUDA_ID_DEUDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Transaccion transaccion;

    public EsAprobada() {
    }

    public EsAprobada(EsAprobadaPK esAprobadaPK) {
        this.esAprobadaPK = esAprobadaPK;
    }

    public EsAprobada(EsAprobadaPK esAprobadaPK, Date fechaAprobacion) {
        this.esAprobadaPK = esAprobadaPK;
        this.fechaAprobacion = fechaAprobacion;
    }

    public EsAprobada(int transaccionId, long transaccionDeudaCuentaId, int transaccionDeudaUsuarioId, short transaccionDeudaIdDeuda, Date lidergrupoFechaingreso, int lidergrupoGrupoId, int lidergrupoUsuarioId) {
        this.esAprobadaPK = new EsAprobadaPK(transaccionId, transaccionDeudaCuentaId, transaccionDeudaUsuarioId, transaccionDeudaIdDeuda, lidergrupoFechaingreso, lidergrupoGrupoId, lidergrupoUsuarioId);
    }

    public EsAprobadaPK getEsAprobadaPK() {
        return esAprobadaPK;
    }

    public void setEsAprobadaPK(EsAprobadaPK esAprobadaPK) {
        this.esAprobadaPK = esAprobadaPK;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Lidergrupo getLidergrupo() {
        return lidergrupo;
    }

    public void setLidergrupo(Lidergrupo lidergrupo) {
        this.lidergrupo = lidergrupo;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esAprobadaPK != null ? esAprobadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsAprobada)) {
            return false;
        }
        EsAprobada other = (EsAprobada) object;
        if ((this.esAprobadaPK == null && other.esAprobadaPK != null) || (this.esAprobadaPK != null && !this.esAprobadaPK.equals(other.esAprobadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.EsAprobada[ esAprobadaPK=" + esAprobadaPK + " ]";
    }
    
}
