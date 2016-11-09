/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "DEUDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deuda.findAll", query = "SELECT d FROM Deuda d")
    , @NamedQuery(name = "Deuda.findByCantidad", query = "SELECT d FROM Deuda d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Deuda.findByUsuarioId", query = "SELECT d FROM Deuda d WHERE d.deudaPK.usuarioId = :usuarioId")
    , @NamedQuery(name = "Deuda.findByCuentaId", query = "SELECT d FROM Deuda d WHERE d.deudaPK.cuentaId = :cuentaId")
    , @NamedQuery(name = "Deuda.findByIdDeuda", query = "SELECT d FROM Deuda d WHERE d.deudaPK.idDeuda = :idDeuda")})
public class Deuda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeudaPK deudaPK;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deuda", fetch = FetchType.LAZY)
    private List<Transaccion> transaccionList;
    @JoinColumn(name = "CUENTA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta cuenta;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Deuda() {
    }

    public Deuda(DeudaPK deudaPK) {
        this.deudaPK = deudaPK;
    }

    public Deuda(int usuarioId, long cuentaId, short idDeuda) {
        this.deudaPK = new DeudaPK(usuarioId, cuentaId, idDeuda);
    }

    public DeudaPK getDeudaPK() {
        return deudaPK;
    }

    public void setDeudaPK(DeudaPK deudaPK) {
        this.deudaPK = deudaPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
        hash += (deudaPK != null ? deudaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deuda)) {
            return false;
        }
        Deuda other = (Deuda) object;
        if ((this.deudaPK == null && other.deudaPK != null) || (this.deudaPK != null && !this.deudaPK.equals(other.deudaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Deuda[ deudaPK=" + deudaPK + " ]";
    }
    
}
