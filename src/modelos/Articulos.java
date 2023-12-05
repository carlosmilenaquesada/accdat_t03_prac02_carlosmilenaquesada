/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ARTICULOS")
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a")
    , @NamedQuery(name = "Articulos.findByCodarticulo", query = "SELECT a FROM Articulos a WHERE a.codarticulo = :codarticulo")
    , @NamedQuery(name = "Articulos.findByNomarticulo", query = "SELECT a FROM Articulos a WHERE a.nomarticulo = :nomarticulo")
    , @NamedQuery(name = "Articulos.findOrderByCodarticulo", query = "SELECT a FROM Articulos a ORDER BY a.codarticulo")//Creado por mí
})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODARTICULO")
    private String codarticulo;
    @Basic(optional = false)
    @Column(name = "NOMARTICULO")
    private String nomarticulo;
    @JoinTable(name = "DETALLE_FACTURAS", joinColumns = {
        @JoinColumn(name = "CODARTICULO", referencedColumnName = "CODARTICULO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMFACTURA", referencedColumnName = "NUMFACTURA")})
    @ManyToMany
    private Collection<Facturas> facturasCollection;
    @JoinColumn(name = "CODFAMILIA", referencedColumnName = "CODFAMILIA")
    @ManyToOne(optional = false)
    private Familias codfamilia;

    public Articulos() {
    }

    public Articulos(String codarticulo) {
        this.codarticulo = codarticulo;
    }

    public Articulos(String codarticulo, String nomarticulo) {
        this.codarticulo = codarticulo;
        this.nomarticulo = nomarticulo;
    }

    //Constructor creado por mí
    public Articulos(String codarticulo, String nomarticulo, Familias codFamilia) {
        this.codarticulo = codarticulo;
        this.nomarticulo = nomarticulo;
        this.codfamilia = codFamilia;
    }

    public String getCodarticulo() {
        return codarticulo;
    }

    public void setCodarticulo(String codarticulo) {
        this.codarticulo = codarticulo;
    }

    public String getNomarticulo() {
        return nomarticulo;
    }

    public void setNomarticulo(String nomarticulo) {
        this.nomarticulo = nomarticulo;
    }

    public Collection<Facturas> getFacturasCollection() {
        return facturasCollection;
    }

    public void setFacturasCollection(Collection<Facturas> facturasCollection) {
        this.facturasCollection = facturasCollection;
    }

    public Familias getCodfamilia() {
        return codfamilia;
    }

    public void setCodfamilia(Familias codfamilia) {
        this.codfamilia = codfamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codarticulo != null ? codarticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.codarticulo == null && other.codarticulo != null) || (this.codarticulo != null && !this.codarticulo.equals(other.codarticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getCodarticulo();
    }

}
