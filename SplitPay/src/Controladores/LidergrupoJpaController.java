/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Grupo;
import Negocio.Usuario;
import Negocio.EsAprobada;
import Negocio.Lidergrupo;
import Negocio.LidergrupoPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class LidergrupoJpaController implements Serializable {

    public LidergrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lidergrupo lidergrupo) throws PreexistingEntityException, Exception {
        if (lidergrupo.getLidergrupoPK() == null) {
            lidergrupo.setLidergrupoPK(new LidergrupoPK());
        }
        if (lidergrupo.getEsAprobadaList() == null) {
            lidergrupo.setEsAprobadaList(new ArrayList<EsAprobada>());
        }
        lidergrupo.getLidergrupoPK().setGrupoId(lidergrupo.getGrupo().getId());
        lidergrupo.getLidergrupoPK().setUsuarioId(lidergrupo.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo = lidergrupo.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getId());
                lidergrupo.setGrupo(grupo);
            }
            Usuario usuario = lidergrupo.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                lidergrupo.setUsuario(usuario);
            }
            List<EsAprobada> attachedEsAprobadaList = new ArrayList<EsAprobada>();
            for (EsAprobada esAprobadaListEsAprobadaToAttach : lidergrupo.getEsAprobadaList()) {
                esAprobadaListEsAprobadaToAttach = em.getReference(esAprobadaListEsAprobadaToAttach.getClass(), esAprobadaListEsAprobadaToAttach.getEsAprobadaPK());
                attachedEsAprobadaList.add(esAprobadaListEsAprobadaToAttach);
            }
            lidergrupo.setEsAprobadaList(attachedEsAprobadaList);
            em.persist(lidergrupo);
            if (grupo != null) {
                grupo.getLidergrupoList().add(lidergrupo);
                grupo = em.merge(grupo);
            }
            if (usuario != null) {
                usuario.getLidergrupoList().add(lidergrupo);
                usuario = em.merge(usuario);
            }
            for (EsAprobada esAprobadaListEsAprobada : lidergrupo.getEsAprobadaList()) {
                Lidergrupo oldLidergrupoOfEsAprobadaListEsAprobada = esAprobadaListEsAprobada.getLidergrupo();
                esAprobadaListEsAprobada.setLidergrupo(lidergrupo);
                esAprobadaListEsAprobada = em.merge(esAprobadaListEsAprobada);
                if (oldLidergrupoOfEsAprobadaListEsAprobada != null) {
                    oldLidergrupoOfEsAprobadaListEsAprobada.getEsAprobadaList().remove(esAprobadaListEsAprobada);
                    oldLidergrupoOfEsAprobadaListEsAprobada = em.merge(oldLidergrupoOfEsAprobadaListEsAprobada);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLidergrupo(lidergrupo.getLidergrupoPK()) != null) {
                throw new PreexistingEntityException("Lidergrupo " + lidergrupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lidergrupo lidergrupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        lidergrupo.getLidergrupoPK().setGrupoId(lidergrupo.getGrupo().getId());
        lidergrupo.getLidergrupoPK().setUsuarioId(lidergrupo.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lidergrupo persistentLidergrupo = em.find(Lidergrupo.class, lidergrupo.getLidergrupoPK());
            Grupo grupoOld = persistentLidergrupo.getGrupo();
            Grupo grupoNew = lidergrupo.getGrupo();
            Usuario usuarioOld = persistentLidergrupo.getUsuario();
            Usuario usuarioNew = lidergrupo.getUsuario();
            List<EsAprobada> esAprobadaListOld = persistentLidergrupo.getEsAprobadaList();
            List<EsAprobada> esAprobadaListNew = lidergrupo.getEsAprobadaList();
            List<String> illegalOrphanMessages = null;
            for (EsAprobada esAprobadaListOldEsAprobada : esAprobadaListOld) {
                if (!esAprobadaListNew.contains(esAprobadaListOldEsAprobada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EsAprobada " + esAprobadaListOldEsAprobada + " since its lidergrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getId());
                lidergrupo.setGrupo(grupoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                lidergrupo.setUsuario(usuarioNew);
            }
            List<EsAprobada> attachedEsAprobadaListNew = new ArrayList<EsAprobada>();
            for (EsAprobada esAprobadaListNewEsAprobadaToAttach : esAprobadaListNew) {
                esAprobadaListNewEsAprobadaToAttach = em.getReference(esAprobadaListNewEsAprobadaToAttach.getClass(), esAprobadaListNewEsAprobadaToAttach.getEsAprobadaPK());
                attachedEsAprobadaListNew.add(esAprobadaListNewEsAprobadaToAttach);
            }
            esAprobadaListNew = attachedEsAprobadaListNew;
            lidergrupo.setEsAprobadaList(esAprobadaListNew);
            lidergrupo = em.merge(lidergrupo);
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getLidergrupoList().remove(lidergrupo);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getLidergrupoList().add(lidergrupo);
                grupoNew = em.merge(grupoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getLidergrupoList().remove(lidergrupo);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getLidergrupoList().add(lidergrupo);
                usuarioNew = em.merge(usuarioNew);
            }
            for (EsAprobada esAprobadaListNewEsAprobada : esAprobadaListNew) {
                if (!esAprobadaListOld.contains(esAprobadaListNewEsAprobada)) {
                    Lidergrupo oldLidergrupoOfEsAprobadaListNewEsAprobada = esAprobadaListNewEsAprobada.getLidergrupo();
                    esAprobadaListNewEsAprobada.setLidergrupo(lidergrupo);
                    esAprobadaListNewEsAprobada = em.merge(esAprobadaListNewEsAprobada);
                    if (oldLidergrupoOfEsAprobadaListNewEsAprobada != null && !oldLidergrupoOfEsAprobadaListNewEsAprobada.equals(lidergrupo)) {
                        oldLidergrupoOfEsAprobadaListNewEsAprobada.getEsAprobadaList().remove(esAprobadaListNewEsAprobada);
                        oldLidergrupoOfEsAprobadaListNewEsAprobada = em.merge(oldLidergrupoOfEsAprobadaListNewEsAprobada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                LidergrupoPK id = lidergrupo.getLidergrupoPK();
                if (findLidergrupo(id) == null) {
                    throw new NonexistentEntityException("The lidergrupo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(LidergrupoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lidergrupo lidergrupo;
            try {
                lidergrupo = em.getReference(Lidergrupo.class, id);
                lidergrupo.getLidergrupoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lidergrupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EsAprobada> esAprobadaListOrphanCheck = lidergrupo.getEsAprobadaList();
            for (EsAprobada esAprobadaListOrphanCheckEsAprobada : esAprobadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lidergrupo (" + lidergrupo + ") cannot be destroyed since the EsAprobada " + esAprobadaListOrphanCheckEsAprobada + " in its esAprobadaList field has a non-nullable lidergrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Grupo grupo = lidergrupo.getGrupo();
            if (grupo != null) {
                grupo.getLidergrupoList().remove(lidergrupo);
                grupo = em.merge(grupo);
            }
            Usuario usuario = lidergrupo.getUsuario();
            if (usuario != null) {
                usuario.getLidergrupoList().remove(lidergrupo);
                usuario = em.merge(usuario);
            }
            em.remove(lidergrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lidergrupo> findLidergrupoEntities() {
        return findLidergrupoEntities(true, -1, -1);
    }

    public List<Lidergrupo> findLidergrupoEntities(int maxResults, int firstResult) {
        return findLidergrupoEntities(false, maxResults, firstResult);
    }

    private List<Lidergrupo> findLidergrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lidergrupo.class));
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

    public Lidergrupo findLidergrupo(LidergrupoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lidergrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getLidergrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lidergrupo> rt = cq.from(Lidergrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
