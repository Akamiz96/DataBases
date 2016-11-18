/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Negocio.Cuenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Grupo;
import Negocio.Usuario;
import Negocio.Deuda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class CuentaJpaController implements Serializable {

    public CuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuenta cuenta) throws PreexistingEntityException, Exception {
        if (cuenta.getDeudaList() == null) {
            cuenta.setDeudaList(new ArrayList<Deuda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupoId = cuenta.getGrupoId();
            if (grupoId != null) {
                grupoId = em.getReference(grupoId.getClass(), grupoId.getId());
                cuenta.setGrupoId(grupoId);
            }
            Usuario usuarioId = cuenta.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getId());
                cuenta.setUsuarioId(usuarioId);
            }
            List<Deuda> attachedDeudaList = new ArrayList<Deuda>();
            for (Deuda deudaListDeudaToAttach : cuenta.getDeudaList()) {
                deudaListDeudaToAttach = em.getReference(deudaListDeudaToAttach.getClass(), deudaListDeudaToAttach.getDeudaPK());
                attachedDeudaList.add(deudaListDeudaToAttach);
            }
            cuenta.setDeudaList(attachedDeudaList);
            em.persist(cuenta);
            if (grupoId != null) {
                grupoId.getCuentaList().add(cuenta);
                grupoId = em.merge(grupoId);
            }
            if (usuarioId != null) {
                usuarioId.getCuentaList().add(cuenta);
                usuarioId = em.merge(usuarioId);
            }
            for (Deuda deudaListDeuda : cuenta.getDeudaList()) {
                Cuenta oldCuentaOfDeudaListDeuda = deudaListDeuda.getCuenta();
                deudaListDeuda.setCuenta(cuenta);
                deudaListDeuda = em.merge(deudaListDeuda);
                if (oldCuentaOfDeudaListDeuda != null) {
                    oldCuentaOfDeudaListDeuda.getDeudaList().remove(deudaListDeuda);
                    oldCuentaOfDeudaListDeuda = em.merge(oldCuentaOfDeudaListDeuda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuenta(cuenta.getId()) != null) {
                throw new PreexistingEntityException("Cuenta " + cuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuenta cuenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, cuenta.getId());
            Grupo grupoIdOld = persistentCuenta.getGrupoId();
            Grupo grupoIdNew = cuenta.getGrupoId();
            Usuario usuarioIdOld = persistentCuenta.getUsuarioId();
            Usuario usuarioIdNew = cuenta.getUsuarioId();
            List<Deuda> deudaListOld = persistentCuenta.getDeudaList();
            List<Deuda> deudaListNew = cuenta.getDeudaList();
            List<String> illegalOrphanMessages = null;
            for (Deuda deudaListOldDeuda : deudaListOld) {
                if (!deudaListNew.contains(deudaListOldDeuda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Deuda " + deudaListOldDeuda + " since its cuenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (grupoIdNew != null) {
                grupoIdNew = em.getReference(grupoIdNew.getClass(), grupoIdNew.getId());
                cuenta.setGrupoId(grupoIdNew);
            }
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getId());
                cuenta.setUsuarioId(usuarioIdNew);
            }
            List<Deuda> attachedDeudaListNew = new ArrayList<Deuda>();
            for (Deuda deudaListNewDeudaToAttach : deudaListNew) {
                deudaListNewDeudaToAttach = em.getReference(deudaListNewDeudaToAttach.getClass(), deudaListNewDeudaToAttach.getDeudaPK());
                attachedDeudaListNew.add(deudaListNewDeudaToAttach);
            }
            deudaListNew = attachedDeudaListNew;
            cuenta.setDeudaList(deudaListNew);
            cuenta = em.merge(cuenta);
            if (grupoIdOld != null && !grupoIdOld.equals(grupoIdNew)) {
                grupoIdOld.getCuentaList().remove(cuenta);
                grupoIdOld = em.merge(grupoIdOld);
            }
            if (grupoIdNew != null && !grupoIdNew.equals(grupoIdOld)) {
                grupoIdNew.getCuentaList().add(cuenta);
                grupoIdNew = em.merge(grupoIdNew);
            }
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getCuentaList().remove(cuenta);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getCuentaList().add(cuenta);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            for (Deuda deudaListNewDeuda : deudaListNew) {
                if (!deudaListOld.contains(deudaListNewDeuda)) {
                    Cuenta oldCuentaOfDeudaListNewDeuda = deudaListNewDeuda.getCuenta();
                    deudaListNewDeuda.setCuenta(cuenta);
                    deudaListNewDeuda = em.merge(deudaListNewDeuda);
                    if (oldCuentaOfDeudaListNewDeuda != null && !oldCuentaOfDeudaListNewDeuda.equals(cuenta)) {
                        oldCuentaOfDeudaListNewDeuda.getDeudaList().remove(deudaListNewDeuda);
                        oldCuentaOfDeudaListNewDeuda = em.merge(oldCuentaOfDeudaListNewDeuda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cuenta.getId();
                if (findCuenta(id) == null) {
                    throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta cuenta;
            try {
                cuenta = em.getReference(Cuenta.class, id);
                cuenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Deuda> deudaListOrphanCheck = cuenta.getDeudaList();
            for (Deuda deudaListOrphanCheckDeuda : deudaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuenta (" + cuenta + ") cannot be destroyed since the Deuda " + deudaListOrphanCheckDeuda + " in its deudaList field has a non-nullable cuenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Grupo grupoId = cuenta.getGrupoId();
            if (grupoId != null) {
                grupoId.getCuentaList().remove(cuenta);
                grupoId = em.merge(grupoId);
            }
            Usuario usuarioId = cuenta.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getCuentaList().remove(cuenta);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(cuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuenta> findCuentaEntities() {
        return findCuentaEntities(true, -1, -1);
    }

    public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
        return findCuentaEntities(false, maxResults, firstResult);
    }

    private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuenta.class));
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

    public Cuenta findCuenta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuenta> rt = cq.from(Cuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
