/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Negocio.EsAprobada;
import Negocio.EsAprobadaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Lidergrupo;
import Negocio.Transaccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class EsAprobadaJpaController implements Serializable {

    public EsAprobadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EsAprobada esAprobada) throws PreexistingEntityException, Exception {
        if (esAprobada.getEsAprobadaPK() == null) {
            esAprobada.setEsAprobadaPK(new EsAprobadaPK());
        }
        esAprobada.getEsAprobadaPK().setLidergrupoFechaingreso(esAprobada.getLidergrupo().getLidergrupoPK().getFechaingreso());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaCuentaId(esAprobada.getTransaccion().getTransaccionPK().getDeudaCuentaId());
        esAprobada.getEsAprobadaPK().setLidergrupoUsuarioId(esAprobada.getLidergrupo().getLidergrupoPK().getUsuarioId());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaUsuarioId(esAprobada.getTransaccion().getTransaccionPK().getDeudaUsuarioId());
        esAprobada.getEsAprobadaPK().setTransaccionId(esAprobada.getTransaccion().getTransaccionPK().getId());
        esAprobada.getEsAprobadaPK().setLidergrupoGrupoId(esAprobada.getLidergrupo().getLidergrupoPK().getGrupoId());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaIdDeuda(esAprobada.getTransaccion().getTransaccionPK().getDeudaIdDeuda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lidergrupo lidergrupo = esAprobada.getLidergrupo();
            if (lidergrupo != null) {
                lidergrupo = em.getReference(lidergrupo.getClass(), lidergrupo.getLidergrupoPK());
                esAprobada.setLidergrupo(lidergrupo);
            }
            Transaccion transaccion = esAprobada.getTransaccion();
            if (transaccion != null) {
                transaccion = em.getReference(transaccion.getClass(), transaccion.getTransaccionPK());
                esAprobada.setTransaccion(transaccion);
            }
            em.persist(esAprobada);
            if (lidergrupo != null) {
                lidergrupo.getEsAprobadaList().add(esAprobada);
                lidergrupo = em.merge(lidergrupo);
            }
            if (transaccion != null) {
                transaccion.getEsAprobadaList().add(esAprobada);
                transaccion = em.merge(transaccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEsAprobada(esAprobada.getEsAprobadaPK()) != null) {
                throw new PreexistingEntityException("EsAprobada " + esAprobada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsAprobada esAprobada) throws NonexistentEntityException, Exception {
        esAprobada.getEsAprobadaPK().setLidergrupoFechaingreso(esAprobada.getLidergrupo().getLidergrupoPK().getFechaingreso());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaCuentaId(esAprobada.getTransaccion().getTransaccionPK().getDeudaCuentaId());
        esAprobada.getEsAprobadaPK().setLidergrupoUsuarioId(esAprobada.getLidergrupo().getLidergrupoPK().getUsuarioId());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaUsuarioId(esAprobada.getTransaccion().getTransaccionPK().getDeudaUsuarioId());
        esAprobada.getEsAprobadaPK().setTransaccionId(esAprobada.getTransaccion().getTransaccionPK().getId());
        esAprobada.getEsAprobadaPK().setLidergrupoGrupoId(esAprobada.getLidergrupo().getLidergrupoPK().getGrupoId());
        esAprobada.getEsAprobadaPK().setTransaccionDeudaIdDeuda(esAprobada.getTransaccion().getTransaccionPK().getDeudaIdDeuda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EsAprobada persistentEsAprobada = em.find(EsAprobada.class, esAprobada.getEsAprobadaPK());
            Lidergrupo lidergrupoOld = persistentEsAprobada.getLidergrupo();
            Lidergrupo lidergrupoNew = esAprobada.getLidergrupo();
            Transaccion transaccionOld = persistentEsAprobada.getTransaccion();
            Transaccion transaccionNew = esAprobada.getTransaccion();
            if (lidergrupoNew != null) {
                lidergrupoNew = em.getReference(lidergrupoNew.getClass(), lidergrupoNew.getLidergrupoPK());
                esAprobada.setLidergrupo(lidergrupoNew);
            }
            if (transaccionNew != null) {
                transaccionNew = em.getReference(transaccionNew.getClass(), transaccionNew.getTransaccionPK());
                esAprobada.setTransaccion(transaccionNew);
            }
            esAprobada = em.merge(esAprobada);
            if (lidergrupoOld != null && !lidergrupoOld.equals(lidergrupoNew)) {
                lidergrupoOld.getEsAprobadaList().remove(esAprobada);
                lidergrupoOld = em.merge(lidergrupoOld);
            }
            if (lidergrupoNew != null && !lidergrupoNew.equals(lidergrupoOld)) {
                lidergrupoNew.getEsAprobadaList().add(esAprobada);
                lidergrupoNew = em.merge(lidergrupoNew);
            }
            if (transaccionOld != null && !transaccionOld.equals(transaccionNew)) {
                transaccionOld.getEsAprobadaList().remove(esAprobada);
                transaccionOld = em.merge(transaccionOld);
            }
            if (transaccionNew != null && !transaccionNew.equals(transaccionOld)) {
                transaccionNew.getEsAprobadaList().add(esAprobada);
                transaccionNew = em.merge(transaccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EsAprobadaPK id = esAprobada.getEsAprobadaPK();
                if (findEsAprobada(id) == null) {
                    throw new NonexistentEntityException("The esAprobada with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EsAprobadaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EsAprobada esAprobada;
            try {
                esAprobada = em.getReference(EsAprobada.class, id);
                esAprobada.getEsAprobadaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esAprobada with id " + id + " no longer exists.", enfe);
            }
            Lidergrupo lidergrupo = esAprobada.getLidergrupo();
            if (lidergrupo != null) {
                lidergrupo.getEsAprobadaList().remove(esAprobada);
                lidergrupo = em.merge(lidergrupo);
            }
            Transaccion transaccion = esAprobada.getTransaccion();
            if (transaccion != null) {
                transaccion.getEsAprobadaList().remove(esAprobada);
                transaccion = em.merge(transaccion);
            }
            em.remove(esAprobada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsAprobada> findEsAprobadaEntities() {
        return findEsAprobadaEntities(true, -1, -1);
    }

    public List<EsAprobada> findEsAprobadaEntities(int maxResults, int firstResult) {
        return findEsAprobadaEntities(false, maxResults, firstResult);
    }

    private List<EsAprobada> findEsAprobadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsAprobada.class));
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

    public EsAprobada findEsAprobada(EsAprobadaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsAprobada.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsAprobadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsAprobada> rt = cq.from(EsAprobada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
