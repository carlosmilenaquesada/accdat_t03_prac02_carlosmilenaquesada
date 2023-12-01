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
import modelos.Clientes;
import modelos.Articulos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Facturas;

/**
 *
 * @author Administrador
 */
public class FacturasJpaController implements Serializable {

    public FacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturas facturas) throws PreexistingEntityException, Exception {
        if (facturas.getArticulosCollection() == null) {
            facturas.setArticulosCollection(new ArrayList<Articulos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes codcliente = facturas.getCodcliente();
            if (codcliente != null) {
                codcliente = em.getReference(codcliente.getClass(), codcliente.getCodcliente());
                facturas.setCodcliente(codcliente);
            }
            Collection<Articulos> attachedArticulosCollection = new ArrayList<Articulos>();
            for (Articulos articulosCollectionArticulosToAttach : facturas.getArticulosCollection()) {
                articulosCollectionArticulosToAttach = em.getReference(articulosCollectionArticulosToAttach.getClass(), articulosCollectionArticulosToAttach.getCodarticulo());
                attachedArticulosCollection.add(articulosCollectionArticulosToAttach);
            }
            facturas.setArticulosCollection(attachedArticulosCollection);
            em.persist(facturas);
            if (codcliente != null) {
                codcliente.getFacturasCollection().add(facturas);
                codcliente = em.merge(codcliente);
            }
            for (Articulos articulosCollectionArticulos : facturas.getArticulosCollection()) {
                articulosCollectionArticulos.getFacturasCollection().add(facturas);
                articulosCollectionArticulos = em.merge(articulosCollectionArticulos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacturas(facturas.getNumfactura()) != null) {
                throw new PreexistingEntityException("Facturas " + facturas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getNumfactura());
            Clientes codclienteOld = persistentFacturas.getCodcliente();
            Clientes codclienteNew = facturas.getCodcliente();
            Collection<Articulos> articulosCollectionOld = persistentFacturas.getArticulosCollection();
            Collection<Articulos> articulosCollectionNew = facturas.getArticulosCollection();
            if (codclienteNew != null) {
                codclienteNew = em.getReference(codclienteNew.getClass(), codclienteNew.getCodcliente());
                facturas.setCodcliente(codclienteNew);
            }
            Collection<Articulos> attachedArticulosCollectionNew = new ArrayList<Articulos>();
            for (Articulos articulosCollectionNewArticulosToAttach : articulosCollectionNew) {
                articulosCollectionNewArticulosToAttach = em.getReference(articulosCollectionNewArticulosToAttach.getClass(), articulosCollectionNewArticulosToAttach.getCodarticulo());
                attachedArticulosCollectionNew.add(articulosCollectionNewArticulosToAttach);
            }
            articulosCollectionNew = attachedArticulosCollectionNew;
            facturas.setArticulosCollection(articulosCollectionNew);
            facturas = em.merge(facturas);
            if (codclienteOld != null && !codclienteOld.equals(codclienteNew)) {
                codclienteOld.getFacturasCollection().remove(facturas);
                codclienteOld = em.merge(codclienteOld);
            }
            if (codclienteNew != null && !codclienteNew.equals(codclienteOld)) {
                codclienteNew.getFacturasCollection().add(facturas);
                codclienteNew = em.merge(codclienteNew);
            }
            for (Articulos articulosCollectionOldArticulos : articulosCollectionOld) {
                if (!articulosCollectionNew.contains(articulosCollectionOldArticulos)) {
                    articulosCollectionOldArticulos.getFacturasCollection().remove(facturas);
                    articulosCollectionOldArticulos = em.merge(articulosCollectionOldArticulos);
                }
            }
            for (Articulos articulosCollectionNewArticulos : articulosCollectionNew) {
                if (!articulosCollectionOld.contains(articulosCollectionNewArticulos)) {
                    articulosCollectionNewArticulos.getFacturasCollection().add(facturas);
                    articulosCollectionNewArticulos = em.merge(articulosCollectionNewArticulos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = facturas.getNumfactura();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getNumfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            Clientes codcliente = facturas.getCodcliente();
            if (codcliente != null) {
                codcliente.getFacturasCollection().remove(facturas);
                codcliente = em.merge(codcliente);
            }
            Collection<Articulos> articulosCollection = facturas.getArticulosCollection();
            for (Articulos articulosCollectionArticulos : articulosCollection) {
                articulosCollectionArticulos.getFacturasCollection().remove(facturas);
                articulosCollectionArticulos = em.merge(articulosCollectionArticulos);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
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

    public Facturas findFacturas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
