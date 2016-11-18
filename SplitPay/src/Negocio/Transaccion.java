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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "TRANSACCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t")
    , @NamedQuery(name = "Transaccion.findById", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.id = :id")
    , @NamedQuery(name = "Transaccion.findByFecha", query = "SELECT t FROM Transaccion t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "Transaccion.findByCantidad", query = "SELECT t FROM Transaccion t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "Transaccion.findByDeudaCuentaId", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.deudaCuentaId = :deudaCuentaId")
    , @NamedQuery(name = "Transaccion.findByDeudaUsuarioId", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.deudaUsuarioId = :deudaUsuarioId")
    , @NamedQuery(name = "Transaccion.findByDeudaIdDeuda", query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.deudaIdDeuda = :deudaIdDeuda")
    , @NamedQuery(name = "Transaccion.findByTipo", query = "SELECT t FROM Transaccion t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Transaccion.findByFechaAprobacion", query = "SELECT t FROM Transaccion t WHERE t.fechaAprobacion = :fechaAprobacion")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionPK transaccionPK;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private long cantidad;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private Character tipo;
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @JoinColumns({
        @JoinColumn(name = "DEUDA_CUENTA_ID", referencedColumnName = "CUENTA_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "DEUDA_USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "DEUDA_ID_DEUDA", referencedColumnName = "ID_DEUDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Deuda deuda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion", fetch = FetchType.LAZY)
    private List<EsAprobada> esAprobadaList;

    public Transaccion() {
    }

    public Transaccion(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Transaccion(TransaccionPK transaccionPK, Date fecha, long cantidad, Character tipo) {
        this.transaccionPK = transaccionPK;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Transaccion(int id, long deudaCuentaId, int deudaUsuarioId, short deudaIdDeuda) {
        this.transaccionPK = new TransaccionPK(id, deudaCuentaId, deudaUsuarioId, deudaIdDeuda);
    }

    public TransaccionPK getTransaccionPK() {
        return transaccionPK;
    }

    public void setTransaccionPK(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public void setDeuda(Deuda deuda) {
        this.deuda = deuda;
    }

    @XmlTransient
    public List<EsAprobada> getEsAprobadaList() {
        return esAprobadaList;
    }

    public void setEsAprobadaList(List<EsAprobada> esAprobadaList) {
        this.esAprobadaList = esAprobadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccionPK != null ? transaccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.transaccionPK == null && other.transaccionPK != null) || (this.transaccionPK != null && !this.transaccionPK.equals(other.transaccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Negocio.Transaccion[ transaccionPK=" + transaccionPK + " ]";
    }
    
}
