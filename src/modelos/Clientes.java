/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "CLIENTES")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByCodcliente", query = "SELECT c FROM Clientes c WHERE c.codcliente = :codcliente")
    , @NamedQuery(name = "Clientes.findByNomcliente", query = "SELECT c FROM Clientes c WHERE c.nomcliente = :nomcliente")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    /*Estado inicial:*/
    @Id
    @Basic(optional = false)
    @Column(name = "CODCLIENTE")
    private String codcliente;
    @Basic(optional = false)
    @Column(name = "NOMCLIENTE")
    private String nomcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcliente")
    private Collection<Facturas> facturasCollection;
    
    /*Modificado
    @Id
    @Basic(optional = false)
    @Column(name = "CODCLIENTE")
    private String codcliente;
    @Basic(optional = false)
    @Column(name = "NOMCLIENTE")
    private String nomcliente;
    @JoinColumn(name = "codcliente", referencedColumnName = "codcliente")//nuevo
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcliente", fetch = FetchType.EAGER)//nuevo
    private Collection<Facturas> facturasCollection;
*/
    public Clientes() {
    }

    public Clientes(String codcliente) {
        this.codcliente = codcliente;
    }

    public Clientes(String codcliente, String nomcliente) {
        this.codcliente = codcliente;
        this.nomcliente = nomcliente;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcliente != null ? codcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.codcliente == null && other.codcliente != null) || (this.codcliente != null && !this.codcliente.equals(other.codcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getCodcliente();
    }
    
}
