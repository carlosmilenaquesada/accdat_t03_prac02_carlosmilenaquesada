/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Familias;
import modelos.Facturas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Articulos;

/**
 *
 * @author Administrador
 */
public class ArticulosJpaController implements Serializable {

    public ArticulosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulos articulos) throws PreexistingEntityException, Exception {
        if (articulos.getFacturasCollection() == null) {
            articulos.setFacturasCollection(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Familias codfamilia = articulos.getCodfamilia();
            if (codfamilia != null) {
                codfamilia = em.getReference(codfamilia.getClass(), codfamilia.getCodfamilia());
                articulos.setCodfamilia(codfamilia);
            }
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : articulos.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getNumfactura());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            articulos.setFacturasCollection(attachedFacturasCollection);
            em.persist(articulos);
            if (codfamilia != null) {
                codfamilia.getArticulosCollection().add(articulos);
                codfamilia = em.merge(codfamilia);
            }
            for (Facturas facturasCollectionFacturas : articulos.getFacturasCollection()) {
                facturasCollectionFacturas.getArticulosCollection().add(articulos);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticulos(articulos.getCodarticulo()) != null) {
                throw new PreexistingEntityException("Articulos " + articulos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulos articulos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulos persistentArticulos = em.find(Articulos.class, articulos.getCodarticulo());
            Familias codfamiliaOld = persistentArticulos.getCodfamilia();
            Familias codfamiliaNew = articulos.getCodfamilia();
            Collection<Facturas> facturasCollectionOld = persistentArticulos.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = articulos.getFacturasCollection();
            if (codfamiliaNew != null) {
                codfamiliaNew = em.getReference(codfamiliaNew.getClass(), codfamiliaNew.getCodfamilia());
                articulos.setCodfamilia(codfamiliaNew);
            }
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getNumfactura());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            articulos.setFacturasCollection(facturasCollectionNew);
            articulos = em.merge(articulos);
            if (codfamiliaOld != null && !codfamiliaOld.equals(codfamiliaNew)) {
                codfamiliaOld.getArticulosCollection().remove(articulos);
                codfamiliaOld = em.merge(codfamiliaOld);
            }
            if (codfamiliaNew != null && !codfamiliaNew.equals(codfamiliaOld)) {
                codfamiliaNew.getArticulosCollection().add(articulos);
                codfamiliaNew = em.merge(codfamiliaNew);
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    facturasCollectionOldFacturas.getArticulosCollection().remove(articulos);
                    facturasCollectionOldFacturas = em.merge(facturasCollectionOldFacturas);
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    facturasCollectionNewFacturas.getArticulosCollection().add(articulos);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = articulos.getCodarticulo();
                if (findArticulos(id) == null) {
                    throw new NonexistentEntityException("The articulos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulos articulos;
            try {
                articulos = em.getReference(Articulos.class, id);
                articulos.getCodarticulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulos with id " + id + " no longer exists.", enfe);
            }
            Familias codfamilia = articulos.getCodfamilia();
            if (codfamilia != null) {
                codfamilia.getArticulosCollection().remove(articulos);
                codfamilia = em.merge(codfamilia);
            }
            Collection<Facturas> facturasCollection = articulos.getFacturasCollection();
            for (Facturas facturasCollectionFacturas : facturasCollection) {
                facturasCollectionFacturas.getArticulosCollection().remove(articulos);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
            }
            em.remove(articulos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulos> findArticulosEntities() {
        return findArticulosEntities(true, -1, -1);
    }

    public List<Articulos> findArticulosEntities(int maxResults, int firstResult) {
        return findArticulosEntities(false, maxResults, firstResult);
    }

    private List<Articulos> findArticulosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Articulos findArticulos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulos.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticulosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulos> rt = cq.from(Articulos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
