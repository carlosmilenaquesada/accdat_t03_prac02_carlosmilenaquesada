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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "FAMILIAS")
@NamedQueries({
    @NamedQuery(name = "Familias.findAll", query = "SELECT f FROM Familias f")
    , @NamedQuery(name = "Familias.findByCodfamilia", query = "SELECT f FROM Familias f WHERE f.codfamilia = :codfamilia")
    , @NamedQuery(name = "Familias.findByNomfamilia", query = "SELECT f FROM Familias f WHERE f.nomfamilia = :nomfamilia")
    ,@NamedQuery(name = "Familias.findOrderByCodfamilia", query = "SELECT f FROM Familias f ORDER BY f.codfamilia")//Creada por mí
})
public class Familias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFAMILIA")
    private String codfamilia;
    @Basic(optional = false)
    @Column(name = "NOMFAMILIA")
    private String nomfamilia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codfamilia")
    private Collection<Articulos> articulosCollection;

    public Familias() {
    }

    public Familias(String codfamilia) {
        this.codfamilia = codfamilia;
    }

    public Familias(String codfamilia, String nomfamilia) {
        this.codfamilia = codfamilia;
        this.nomfamilia = nomfamilia;
    }

    public String getCodfamilia() {
        return codfamilia;
    }

    public void setCodfamilia(String codfamilia) {
        this.codfamilia = codfamilia;
    }

    public String getNomfamilia() {
        return nomfamilia;
    }

    public void setNomfamilia(String nomfamilia) {
        this.nomfamilia = nomfamilia;
    }

    public Collection<Articulos> getArticulosCollection() {
        return articulosCollection;
    }

    public void setArticulosCollection(Collection<Articulos> articulosCollection) {
        this.articulosCollection = articulosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfamilia != null ? codfamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familias)) {
            return false;
        }
        Familias other = (Familias) object;
        if ((this.codfamilia == null && other.codfamilia != null) || (this.codfamilia != null && !this.codfamilia.equals(other.codfamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getCodfamilia();
    }

}
