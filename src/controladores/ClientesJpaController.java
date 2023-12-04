/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Facturas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Clientes;

public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) throws PreexistingEntityException, Exception {
        if (clientes.getFacturasCollection() == null) {
            clientes.setFacturasCollection(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : clientes.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getNumfactura());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            clientes.setFacturasCollection(attachedFacturasCollection);
            em.persist(clientes);
            for (Facturas facturasCollectionFacturas : clientes.getFacturasCollection()) {
                Clientes oldCodclienteOfFacturasCollectionFacturas = facturasCollectionFacturas.getCodcliente();
                facturasCollectionFacturas.setCodcliente(clientes);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldCodclienteOfFacturasCollectionFacturas != null) {
                    oldCodclienteOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldCodclienteOfFacturasCollectionFacturas = em.merge(oldCodclienteOfFacturasCollectionFacturas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientes(clientes.getCodcliente()) != null) {
                throw new PreexistingEntityException("Clientes " + clientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Clientes persistentClientes = em.find(Clientes.class, clientes.getCodcliente());
            Collection<Facturas> facturasCollectionOld = persistentClientes.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = clientes.getFacturasCollection();

            List<String> illegalOrphanMessages = null;
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturas " + facturasCollectionOldFacturas + " since its codcliente field is not nullable.");
                }
            }

            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getNumfactura());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            clientes.setFacturasCollection(facturasCollectionNew);
            clientes = em.merge(clientes);
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Clientes oldCodclienteOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getCodcliente();
                    facturasCollectionNewFacturas.setCodcliente(clientes);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldCodclienteOfFacturasCollectionNewFacturas != null && !oldCodclienteOfFacturasCollectionNewFacturas.equals(clientes)) {
                        oldCodclienteOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldCodclienteOfFacturasCollectionNewFacturas = em.merge(oldCodclienteOfFacturasCollectionNewFacturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clientes.getCodcliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getCodcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Facturas> facturasCollectionOrphanCheck = clientes.getFacturasCollection();
            for (Facturas facturasCollectionOrphanCheckFacturas : facturasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Facturas " + facturasCollectionOrphanCheckFacturas + " in its facturasCollection field has a non-nullable codcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyEnCascada(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getCodcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }   
            Collection<Facturas> facturasDelCliente = clientes.getFacturasCollection();
            for (Facturas factura : facturasDelCliente) {                
                em.remove(factura);
            }
            //clientes.getFacturasCollection().clear();
            em.remove(clientes);            
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
