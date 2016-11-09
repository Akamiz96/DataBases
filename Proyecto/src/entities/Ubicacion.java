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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "UBICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u")
    , @NamedQuery(name = "Ubicacion.findByFecha", query = "SELECT u FROM Ubicacion u WHERE u.ubicacionPK.fecha = :fecha")
    , @NamedQuery(name = "Ubicacion.findByLongitudgrados", query = "SELECT u FROM Ubicacion u WHERE u.longitudgrados = :longitudgrados")
    , @NamedQuery(name = "Ubicacion.findByLongitudminutos", query = "SELECT u FROM Ubicacion u WHERE u.longitudminutos = :longitudminutos")
    , @NamedQuery(name = "Ubicacion.findByLongitudsegundos", query = "SELECT u FROM Ubicacion u WHERE u.longitudsegundos = :longitudsegundos")
    , @NamedQuery(name = "Ubicacion.findByLatitudgrados", query = "SELECT u FROM Ubicacion u WHERE u.latitudgrados = :latitudgrados")
    , @NamedQuery(name = "Ubicacion.findByLatitudminutos", query = "SELECT u FROM Ubicacion u WHERE u.latitudminutos = :latitudminutos")
    , @NamedQuery(name = "Ubicacion.findByLatitudsegundos", query = "SELECT u FROM Ubicacion u WHERE u.latitudsegundos = :latitudsegundos")
    , @NamedQuery(name = "Ubicacion.findByUsuarioId", query = "SELECT u FROM Ubicacion u WHERE u.ubicacionPK.usuarioId = :usuarioId")})
public class Ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UbicacionPK ubicacionPK;
    @Basic(optional = false)
    @Column(name = "LONGITUDGRADOS")
    private short longitudgrados;
    @Basic(optional = false)
    @Column(name = "LONGITUDMINUTOS")
    private short longitudminutos;
    @Basic(optional = false)
    @Column(name = "LONGITUDSEGUNDOS")
    private short longitudsegundos;
    @Basic(optional = false)
    @Column(name = "LATITUDGRADOS")
    private short latitudgrados;
    @Basic(optional = false)
    @Column(name = "LATITUDMINUTOS")
    private short latitudminutos;
    @Basic(optional = false)
    @Column(name = "LATITUDSEGUNDOS")
    private short latitudsegundos;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Ubicacion() {
    }

    public Ubicacion(UbicacionPK ubicacionPK) {
        this.ubicacionPK = ubicacionPK;
    }

    public Ubicacion(UbicacionPK ubicacionPK, short longitudgrados, short longitudminutos, short longitudsegundos, short latitudgrados, short latitudminutos, short latitudsegundos) {
        this.ubicacionPK = ubicacionPK;
        this.longitudgrados = longitudgrados;
        this.longitudminutos = longitudminutos;
        this.longitudsegundos = longitudsegundos;
        this.latitudgrados = latitudgrados;
        this.latitudminutos = latitudminutos;
        this.latitudsegundos = latitudsegundos;
    }

    public Ubicacion(Date fecha, int usuarioId) {
        this.ubicacionPK = new UbicacionPK(fecha, usuarioId);
    }

    public UbicacionPK getUbicacionPK() {
        return ubicacionPK;
    }

    public void setUbicacionPK(UbicacionPK ubicacionPK) {
        this.ubicacionPK = ubicacionPK;
    }

    public short getLongitudgrados() {
        return longitudgrados;
    }

    public void setLongitudgrados(short longitudgrados) {
        this.longitudgrados = longitudgrados;
    }

    public short getLongitudminutos() {
        return longitudminutos;
    }

    public void setLongitudminutos(short longitudminutos) {
        this.longitudminutos = longitudminutos;
    }

    public short getLongitudsegundos() {
        return longitudsegundos;
    }

    public void setLongitudsegundos(short longitudsegundos) {
        this.longitudsegundos = longitudsegundos;
    }

    public short getLatitudgrados() {
        return latitudgrados;
    }

    public void setLatitudgrados(short latitudgrados) {
        this.latitudgrados = latitudgrados;
    }

    public short getLatitudminutos() {
        return latitudminutos;
    }

    public void setLatitudminutos(short latitudminutos) {
        this.latitudminutos = latitudminutos;
    }

    public short getLatitudsegundos() {
        return latitudsegundos;
    }

    public void setLatitudsegundos(short latitudsegundos) {
        this.latitudsegundos = latitudsegundos;
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
        hash += (ubicacionPK != null ? ubicacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.ubicacionPK == null && other.ubicacionPK != null) || (this.ubicacionPK != null && !this.ubicacionPK.equals(other.ubicacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ubicacion[ ubicacionPK=" + ubicacionPK + " ]";
    }
    
}
