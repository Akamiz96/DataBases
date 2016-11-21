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
import Negocio.Deuda;
import Negocio.EsAprobada;
import Negocio.Transaccion;
import Negocio.TransaccionPK;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Pablo
 */
public class TransaccionJpaController implements Serializable {

    public TransaccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transaccion transaccion) throws PreexistingEntityException, Exception {
        if (transaccion.getTransaccionPK() == null) {
            transaccion.setTransaccionPK(new TransaccionPK());
        }
        if (transaccion.getEsAprobadaList() == null) {
            transaccion.setEsAprobadaList(new ArrayList<EsAprobada>());
        }
        transaccion.getTransaccionPK().setDeudaIdDeuda(transaccion.getDeuda().getDeudaPK().getIdDeuda());
        transaccion.getTransaccionPK().setDeudaCuentaId(transaccion.getDeuda().getDeudaPK().getCuentaId());
        transaccion.getTransaccionPK().setDeudaUsuarioId(transaccion.getDeuda().getDeudaPK().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deuda deuda = transaccion.getDeuda();
            if (deuda != null) {
                deuda = em.getReference(deuda.getClass(), deuda.getDeudaPK());
                transaccion.setDeuda(deuda);
            }
            List<EsAprobada> attachedEsAprobadaList = new ArrayList<EsAprobada>();
            for (EsAprobada esAprobadaListEsAprobadaToAttach : transaccion.getEsAprobadaList()) {
                esAprobadaListEsAprobadaToAttach = em.getReference(esAprobadaListEsAprobadaToAttach.getClass(), esAprobadaListEsAprobadaToAttach.getEsAprobadaPK());
                attachedEsAprobadaList.add(esAprobadaListEsAprobadaToAttach);
            }
            transaccion.setEsAprobadaList(attachedEsAprobadaList);
            em.persist(transaccion);
            if (deuda != null) {
                deuda.getTransaccionList().add(transaccion);
                deuda = em.merge(deuda);
            }
            for (EsAprobada esAprobadaListEsAprobada : transaccion.getEsAprobadaList()) {
                Transaccion oldTransaccionOfEsAprobadaListEsAprobada = esAprobadaListEsAprobada.getTransaccion();
                esAprobadaListEsAprobada.setTransaccion(transaccion);
                esAprobadaListEsAprobada = em.merge(esAprobadaListEsAprobada);
                if (oldTransaccionOfEsAprobadaListEsAprobada != null) {
                    oldTransaccionOfEsAprobadaListEsAprobada.getEsAprobadaList().remove(esAprobadaListEsAprobada);
                    oldTransaccionOfEsAprobadaListEsAprobada = em.merge(oldTransaccionOfEsAprobadaListEsAprobada);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransaccion(transaccion.getTransaccionPK()) != null) {
                throw new PreexistingEntityException("Transaccion " + transaccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transaccion transaccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        transaccion.getTransaccionPK().setDeudaIdDeuda(transaccion.getDeuda().getDeudaPK().getIdDeuda());
        transaccion.getTransaccionPK().setDeudaCuentaId(transaccion.getDeuda().getDeudaPK().getCuentaId());
        transaccion.getTransaccionPK().setDeudaUsuarioId(transaccion.getDeuda().getDeudaPK().getUsuarioId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion persistentTransaccion = em.find(Transaccion.class, transaccion.getTransaccionPK());
            Deuda deudaOld = persistentTransaccion.getDeuda();
            Deuda deudaNew = transaccion.getDeuda();
            List<EsAprobada> esAprobadaListOld = persistentTransaccion.getEsAprobadaList();
            List<EsAprobada> esAprobadaListNew = transaccion.getEsAprobadaList();
            List<String> illegalOrphanMessages = null;
            for (EsAprobada esAprobadaListOldEsAprobada : esAprobadaListOld) {
                if (!esAprobadaListNew.contains(esAprobadaListOldEsAprobada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EsAprobada " + esAprobadaListOldEsAprobada + " since its transaccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (deudaNew != null) {
                deudaNew = em.getReference(deudaNew.getClass(), deudaNew.getDeudaPK());
                transaccion.setDeuda(deudaNew);
            }
            List<EsAprobada> attachedEsAprobadaListNew = new ArrayList<EsAprobada>();
            for (EsAprobada esAprobadaListNewEsAprobadaToAttach : esAprobadaListNew) {
                esAprobadaListNewEsAprobadaToAttach = em.getReference(esAprobadaListNewEsAprobadaToAttach.getClass(), esAprobadaListNewEsAprobadaToAttach.getEsAprobadaPK());
                attachedEsAprobadaListNew.add(esAprobadaListNewEsAprobadaToAttach);
            }
            esAprobadaListNew = attachedEsAprobadaListNew;
            transaccion.setEsAprobadaList(esAprobadaListNew);
            transaccion = em.merge(transaccion);
            if (deudaOld != null && !deudaOld.equals(deudaNew)) {
                deudaOld.getTransaccionList().remove(transaccion);
                deudaOld = em.merge(deudaOld);
            }
            if (deudaNew != null && !deudaNew.equals(deudaOld)) {
                deudaNew.getTransaccionList().add(transaccion);
                deudaNew = em.merge(deudaNew);
            }
            for (EsAprobada esAprobadaListNewEsAprobada : esAprobadaListNew) {
                if (!esAprobadaListOld.contains(esAprobadaListNewEsAprobada)) {
                    Transaccion oldTransaccionOfEsAprobadaListNewEsAprobada = esAprobadaListNewEsAprobada.getTransaccion();
                    esAprobadaListNewEsAprobada.setTransaccion(transaccion);
                    esAprobadaListNewEsAprobada = em.merge(esAprobadaListNewEsAprobada);
                    if (oldTransaccionOfEsAprobadaListNewEsAprobada != null && !oldTransaccionOfEsAprobadaListNewEsAprobada.equals(transaccion)) {
                        oldTransaccionOfEsAprobadaListNewEsAprobada.getEsAprobadaList().remove(esAprobadaListNewEsAprobada);
                        oldTransaccionOfEsAprobadaListNewEsAprobada = em.merge(oldTransaccionOfEsAprobadaListNewEsAprobada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TransaccionPK id = transaccion.getTransaccionPK();
                if (findTransaccion(id) == null) {
                    throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TransaccionPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transaccion transaccion;
            try {
                transaccion = em.getReference(Transaccion.class, id);
                transaccion.getTransaccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EsAprobada> esAprobadaListOrphanCheck = transaccion.getEsAprobadaList();
            for (EsAprobada esAprobadaListOrphanCheckEsAprobada : esAprobadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transaccion (" + transaccion + ") cannot be destroyed since the EsAprobada " + esAprobadaListOrphanCheckEsAprobada + " in its esAprobadaList field has a non-nullable transaccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Deuda deuda = transaccion.getDeuda();
            if (deuda != null) {
                deuda.getTransaccionList().remove(transaccion);
                deuda = em.merge(deuda);
            }
            em.remove(transaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transaccion> findTransaccionEntities() {
        return findTransaccionEntities(true, -1, -1);
    }

    public List<Transaccion> findTransaccionEntities(int maxResults, int firstResult) {
        return findTransaccionEntities(false, maxResults, firstResult);
    }

    private List<Transaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transaccion.class));
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

    public Transaccion findTransaccion(TransaccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transaccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransaccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transaccion> rt = cq.from(Transaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public boolean memberToMemberTrans( int usuarioId, long cuentaId, short idDeuda, long cantidad, char tipo )
    {
        EntityManager em = getEntityManager();
        try
        {   
            EntityTransaction et = em.getTransaction();
            et.begin();
            Query insertar = em.createNativeQuery("INSERT INTO transaccion(id,FECHA,CANTIDAD,DEUDA_CUENTA_ID,DEUDA_USUARIO_ID,DEUDA_ID_DEUDA,TIPO) values(TR_id_SEQ.NEXTVAL,CURRENT_DATE,?,?,?,?,?)");
            insertar.setParameter(1, cantidad);
            insertar.setParameter(2, cuentaId);
            insertar.setParameter(3, usuarioId);
            insertar.setParameter(4, idDeuda);
            insertar.setParameter(5, tipo);
            insertar.executeUpdate();
            et.commit();
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        finally
        {
            em.close();
        }
        return true;
    }
}
