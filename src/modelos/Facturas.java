/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "FACTURAS")
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f")
    , @NamedQuery(name = "Facturas.findByNumfactura", query = "SELECT f FROM Facturas f WHERE f.numfactura = :numfactura")
    , @NamedQuery(name = "Facturas.findByFechafactura", query = "SELECT f FROM Facturas f WHERE f.fechafactura = :fechafactura")})
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NUMFACTURA")
    private Long numfactura;
    @Column(name = "FECHAFACTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafactura;
    @ManyToMany(mappedBy = "facturasCollection")
    private Collection<Articulos> articulosCollection;
    @JoinColumn(name = "CODCLIENTE", referencedColumnName = "CODCLIENTE")
    @ManyToOne(optional = false)
    private Clientes codcliente;

    public Facturas() {
    }

    public Facturas(Long numfactura) {
        this.numfactura = numfactura;
    }

    public Long getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(Long numfactura) {
        this.numfactura = numfactura;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    public Clientes getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Clientes codcliente) {
        this.codcliente = codcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numfactura != null ? numfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.numfactura == null && other.numfactura != null) || (this.numfactura != null && !this.numfactura.equals(other.numfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNumfactura().toString();
    }

}
