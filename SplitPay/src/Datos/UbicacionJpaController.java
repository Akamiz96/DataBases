/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Negocio.Ubicacion;
import Negocio.UbicacionPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class UbicacionJpaController implements Serializable {

    public UbicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ubicacion ubicacion) throws PreexistingEntityException, Exception {
        if (ubicacion.getUbicacionPK() == null) {
            ubicacion.setUbicacionPK(new UbicacionPK());
        }
        ubicacion.getUbicacionPK().setUsuarioId(ubicacion.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = ubicacion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                ubicacion.setUsuario(usuario);
            }
            em.persist(ubicacion);
            if (usuario != null) {
                usuario.getUbicacionList().add(ubicacion);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUbicacion(ubicacion.getUbicacionPK()) != null) {
                throw new PreexistingEntityException("Ubicacion " + ubicacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ubicacion ubicacion) throws NonexistentEntityException, Exception {
        ubicacion.getUbicacionPK().setUsuarioId(ubicacion.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ubicacion persistentUbicacion = em.find(Ubicacion.class, ubicacion.getUbicacionPK());
            Usuario usuarioOld = persistentUbicacion.getUsuario();
            Usuario usuarioNew = ubicacion.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                ubicacion.setUsuario(usuarioNew);
            }
            ubicacion = em.merge(ubicacion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUbicacionList().remove(ubicacion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUbicacionList().add(ubicacion);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UbicacionPK id = ubicacion.getUbicacionPK();
                if (findUbicacion(id) == null) {
                    throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UbicacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ubicacion ubicacion;
            try {
                ubicacion = em.getReference(Ubicacion.class, id);
                ubicacion.getUbicacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubicacion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = ubicacion.getUsuario();
            if (usuario != null) {
                usuario.getUbicacionList().remove(ubicacion);
                usuario = em.merge(usuario);
            }
            em.remove(ubicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ubicacion> findUbicacionEntities() {
        return findUbicacionEntities(true, -1, -1);
    }

    public List<Ubicacion> findUbicacionEntities(int maxResults, int firstResult) {
        return findUbicacionEntities(false, maxResults, firstResult);
    }

    private List<Ubicacion> findUbicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ubicacion.class));
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

    public Ubicacion findUbicacion(UbicacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ubicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getUbicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ubicacion> rt = cq.from(Ubicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
