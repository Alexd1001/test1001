/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Mao
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByCuentaid", query = "SELECT c FROM Cuenta c WHERE c.cuentaid = :cuentaid"),
    @NamedQuery(name = "Cuenta.findByTipocuenta", query = "SELECT c FROM Cuenta c WHERE c.tipocuenta = :tipocuenta")})
public class Cuenta implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaId")
    private Collection<Transacciones> transaccionesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuentaid")
    private Integer cuentaid;
    @Basic(optional = false)
    @Column(name = "tipocuenta")
    private int tipocuenta;
    @JoinColumn(name = "documento_id", referencedColumnName = "documentoid")
    @ManyToOne(optional = false)
    private Cliente documentoId;

    public Cuenta() {
    }

    public Cuenta(Integer cuentaid) {
        this.cuentaid = cuentaid;
    }

    public Cuenta(Integer cuentaid, int tipocuenta) {
        this.cuentaid = cuentaid;
        this.tipocuenta = tipocuenta;
    }

    public Integer getCuentaid() {
        return cuentaid;
    }

    public void setCuentaid(Integer cuentaid) {
        this.cuentaid = cuentaid;
    }

    public int getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(int tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Cliente getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Cliente documentoId) {
        this.documentoId = documentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaid != null ? cuentaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.cuentaid == null && other.cuentaid != null) || (this.cuentaid != null && !this.cuentaid.equals(other.cuentaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cuenta[ cuentaid=" + cuentaid + " ]";
    }

    @XmlTransient
    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }
    
}
