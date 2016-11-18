/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Grupo;
import Negocio.PerteneceA;
import Negocio.PerteneceAPK;
import Negocio.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class PerteneceAJpaController implements Serializable {

    public PerteneceAJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerteneceA perteneceA) throws PreexistingEntityException, Exception {
        if (perteneceA.getPerteneceAPK() == null) {
            perteneceA.setPerteneceAPK(new PerteneceAPK());
        }
        perteneceA.getPerteneceAPK().setGrupoId(perteneceA.getGrupo().getId());
        perteneceA.getPerteneceAPK().setUsuarioId(perteneceA.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo = perteneceA.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getId());
                perteneceA.setGrupo(grupo);
            }
            Usuario usuario = perteneceA.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                perteneceA.setUsuario(usuario);
            }
            em.persist(perteneceA);
            if (grupo != null) {
                grupo.getPerteneceAList().add(perteneceA);
                grupo = em.merge(grupo);
            }
            if (usuario != null) {
                usuario.getPerteneceAList().add(perteneceA);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerteneceA(perteneceA.getPerteneceAPK()) != null) {
                throw new PreexistingEntityException("PerteneceA " + perteneceA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerteneceA perteneceA) throws NonexistentEntityException, Exception {
        perteneceA.getPerteneceAPK().setGrupoId(perteneceA.getGrupo().getId());
        perteneceA.getPerteneceAPK().setUsuarioId(perteneceA.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerteneceA persistentPerteneceA = em.find(PerteneceA.class, perteneceA.getPerteneceAPK());
            Grupo grupoOld = persistentPerteneceA.getGrupo();
            Grupo grupoNew = perteneceA.getGrupo();
            Usuario usuarioOld = persistentPerteneceA.getUsuario();
            Usuario usuarioNew = perteneceA.getUsuario();
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getId());
                perteneceA.setGrupo(grupoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                perteneceA.setUsuario(usuarioNew);
            }
            perteneceA = em.merge(perteneceA);
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getPerteneceAList().remove(perteneceA);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getPerteneceAList().add(perteneceA);
                grupoNew = em.merge(grupoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPerteneceAList().remove(perteneceA);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPerteneceAList().add(perteneceA);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PerteneceAPK id = perteneceA.getPerteneceAPK();
                if (findPerteneceA(id) == null) {
                    throw new NonexistentEntityException("The perteneceA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PerteneceAPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerteneceA perteneceA;
            try {
                perteneceA = em.getReference(PerteneceA.class, id);
                perteneceA.getPerteneceAPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perteneceA with id " + id + " no longer exists.", enfe);
            }
            Grupo grupo = perteneceA.getGrupo();
            if (grupo != null) {
                grupo.getPerteneceAList().remove(perteneceA);
                grupo = em.merge(grupo);
            }
            Usuario usuario = perteneceA.getUsuario();
            if (usuario != null) {
                usuario.getPerteneceAList().remove(perteneceA);
                usuario = em.merge(usuario);
            }
            em.remove(perteneceA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerteneceA> findPerteneceAEntities() {
        return findPerteneceAEntities(true, -1, -1);
    }

    public List<PerteneceA> findPerteneceAEntities(int maxResults, int firstResult) {
        return findPerteneceAEntities(false, maxResults, firstResult);
    }

    private List<PerteneceA> findPerteneceAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerteneceA.class));
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

    public PerteneceA findPerteneceA(PerteneceAPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerteneceA.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerteneceACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerteneceA> rt = cq.from(PerteneceA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
