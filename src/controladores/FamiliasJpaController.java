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
import modelos.Articulos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Familias;

/**
 *
 * @author Administrador
 */
public class FamiliasJpaController implements Serializable {

    public FamiliasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Familias familias) throws PreexistingEntityException, Exception {
        if (familias.getArticulosCollection() == null) {
            familias.setArticulosCollection(new ArrayList<Articulos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Articulos> attachedArticulosCollection = new ArrayList<Articulos>();
            for (Articulos articulosCollectionArticulosToAttach : familias.getArticulosCollection()) {
                articulosCollectionArticulosToAttach = em.getReference(articulosCollectionArticulosToAttach.getClass(), articulosCollectionArticulosToAttach.getCodarticulo());
                attachedArticulosCollection.add(articulosCollectionArticulosToAttach);
            }
            familias.setArticulosCollection(attachedArticulosCollection);
            em.persist(familias);
            for (Articulos articulosCollectionArticulos : familias.getArticulosCollection()) {
                Familias oldCodfamiliaOfArticulosCollectionArticulos = articulosCollectionArticulos.getCodfamilia();
                articulosCollectionArticulos.setCodfamilia(familias);
                articulosCollectionArticulos = em.merge(articulosCollectionArticulos);
                if (oldCodfamiliaOfArticulosCollectionArticulos != null) {
                    oldCodfamiliaOfArticulosCollectionArticulos.getArticulosCollection().remove(articulosCollectionArticulos);
                    oldCodfamiliaOfArticulosCollectionArticulos = em.merge(oldCodfamiliaOfArticulosCollectionArticulos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFamilias(familias.getCodfamilia()) != null) {
                throw new PreexistingEntityException("Familias " + familias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Familias familias) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Familias persistentFamilias = em.find(Familias.class, familias.getCodfamilia());

            Collection<Articulos> articulosCollectionOld = persistentFamilias.getArticulosCollection();
            Collection<Articulos> articulosCollectionNew = familias.getArticulosCollection();
            List<String> illegalOrphanMessages = null;
            for (Articulos articulosCollectionOldArticulos : articulosCollectionOld) {
                if (!articulosCollectionNew.contains(articulosCollectionOldArticulos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulos " + articulosCollectionOldArticulos + " since its codfamilia field is not nullable.");
                }
            }

            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Articulos> attachedArticulosCollectionNew = new ArrayList<Articulos>();
            for (Articulos articulosCollectionNewArticulosToAttach : articulosCollectionNew) {
                articulosCollectionNewArticulosToAttach = em.getReference(articulosCollectionNewArticulosToAttach.getClass(), articulosCollectionNewArticulosToAttach.getCodarticulo());
                attachedArticulosCollectionNew.add(articulosCollectionNewArticulosToAttach);
            }
            articulosCollectionNew = attachedArticulosCollectionNew;
            familias.setArticulosCollection(articulosCollectionNew);
            familias = em.merge(familias);
            for (Articulos articulosCollectionNewArticulos : articulosCollectionNew) {
                if (!articulosCollectionOld.contains(articulosCollectionNewArticulos)) {
                    Familias oldCodfamiliaOfArticulosCollectionNewArticulos = articulosCollectionNewArticulos.getCodfamilia();
                    articulosCollectionNewArticulos.setCodfamilia(familias);
                    articulosCollectionNewArticulos = em.merge(articulosCollectionNewArticulos);
                    if (oldCodfamiliaOfArticulosCollectionNewArticulos != null && !oldCodfamiliaOfArticulosCollectionNewArticulos.equals(familias)) {
                        oldCodfamiliaOfArticulosCollectionNewArticulos.getArticulosCollection().remove(articulosCollectionNewArticulos);
                        oldCodfamiliaOfArticulosCollectionNewArticulos = em.merge(oldCodfamiliaOfArticulosCollectionNewArticulos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = familias.getCodfamilia();
                if (findFamilias(id) == null) {
                    throw new NonexistentEntityException("The familias with id " + id + " no longer exists.");
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
            Familias familias;
            try {
                familias = em.getReference(Familias.class, id);
                familias.getCodfamilia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The familias with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Articulos> articulosCollectionOrphanCheck = familias.getArticulosCollection();
            for (Articulos articulosCollectionOrphanCheckArticulos : articulosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Familias (" + familias + ") cannot be destroyed since the Articulos " + articulosCollectionOrphanCheckArticulos + " in its articulosCollection field has a non-nullable codfamilia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(familias);
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
            Familias familias;
            try {
                familias = em.getReference(Familias.class, id);
                familias.getCodfamilia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The familias with id " + id + " no longer exists.", enfe);
            }
            //Para borrar la familia, primero necesito borrar los artículos que contiene
            Collection<Articulos> articulosEnFamilia = familias.getArticulosCollection();
            for (Articulos articulos : articulosEnFamilia) {
                em.remove(articulos);
            }
            em.remove(familias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Familias> findFamiliasEntities() {
        return findFamiliasEntities(true, -1, -1);
    }

    public List<Familias> findFamiliasEntities(int maxResults, int firstResult) {
        return findFamiliasEntities(false, maxResults, firstResult);
    }

    private List<Familias> findFamiliasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Familias.class));
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

    public Familias findFamilias(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Familias.class, id);
        } finally {
            em.close();
        }
    }

    public int getFamiliasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Familias> rt = cq.from(Familias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
