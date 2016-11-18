/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Negocio.Usuario;
import Negocio.Cuenta;
import Negocio.Grupo;
import java.util.ArrayList;
import java.util.List;
import Negocio.PerteneceA;
import Negocio.Lidergrupo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) throws PreexistingEntityException, Exception {
        if (grupo.getCuentaList() == null) {
            grupo.setCuentaList(new ArrayList<Cuenta>());
        }
        if (grupo.getPerteneceAList() == null) {
            grupo.setPerteneceAList(new ArrayList<PerteneceA>());
        }
        if (grupo.getLidergrupoList() == null) {
            grupo.setLidergrupoList(new ArrayList<Lidergrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioduenoId = grupo.getUsuarioduenoId();
            if (usuarioduenoId != null) {
                usuarioduenoId = em.getReference(usuarioduenoId.getClass(), usuarioduenoId.getId());
                grupo.setUsuarioduenoId(usuarioduenoId);
            }
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : grupo.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getId());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            grupo.setCuentaList(attachedCuentaList);
            List<PerteneceA> attachedPerteneceAList = new ArrayList<PerteneceA>();
            for (PerteneceA perteneceAListPerteneceAToAttach : grupo.getPerteneceAList()) {
                perteneceAListPerteneceAToAttach = em.getReference(perteneceAListPerteneceAToAttach.getClass(), perteneceAListPerteneceAToAttach.getPerteneceAPK());
                attachedPerteneceAList.add(perteneceAListPerteneceAToAttach);
            }
            grupo.setPerteneceAList(attachedPerteneceAList);
            List<Lidergrupo> attachedLidergrupoList = new ArrayList<Lidergrupo>();
            for (Lidergrupo lidergrupoListLidergrupoToAttach : grupo.getLidergrupoList()) {
                lidergrupoListLidergrupoToAttach = em.getReference(lidergrupoListLidergrupoToAttach.getClass(), lidergrupoListLidergrupoToAttach.getLidergrupoPK());
                attachedLidergrupoList.add(lidergrupoListLidergrupoToAttach);
            }
            grupo.setLidergrupoList(attachedLidergrupoList);
            em.persist(grupo);
            if (usuarioduenoId != null) {
                usuarioduenoId.getGrupoList().add(grupo);
                usuarioduenoId = em.merge(usuarioduenoId);
            }
            for (Cuenta cuentaListCuenta : grupo.getCuentaList()) {
                Grupo oldGrupoIdOfCuentaListCuenta = cuentaListCuenta.getGrupoId();
                cuentaListCuenta.setGrupoId(grupo);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldGrupoIdOfCuentaListCuenta != null) {
                    oldGrupoIdOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldGrupoIdOfCuentaListCuenta = em.merge(oldGrupoIdOfCuentaListCuenta);
                }
            }
            for (PerteneceA perteneceAListPerteneceA : grupo.getPerteneceAList()) {
                Grupo oldGrupoOfPerteneceAListPerteneceA = perteneceAListPerteneceA.getGrupo();
                perteneceAListPerteneceA.setGrupo(grupo);
                perteneceAListPerteneceA = em.merge(perteneceAListPerteneceA);
                if (oldGrupoOfPerteneceAListPerteneceA != null) {
                    oldGrupoOfPerteneceAListPerteneceA.getPerteneceAList().remove(perteneceAListPerteneceA);
                    oldGrupoOfPerteneceAListPerteneceA = em.merge(oldGrupoOfPerteneceAListPerteneceA);
                }
            }
            for (Lidergrupo lidergrupoListLidergrupo : grupo.getLidergrupoList()) {
                Grupo oldGrupoOfLidergrupoListLidergrupo = lidergrupoListLidergrupo.getGrupo();
                lidergrupoListLidergrupo.setGrupo(grupo);
                lidergrupoListLidergrupo = em.merge(lidergrupoListLidergrupo);
                if (oldGrupoOfLidergrupoListLidergrupo != null) {
                    oldGrupoOfLidergrupoListLidergrupo.getLidergrupoList().remove(lidergrupoListLidergrupo);
                    oldGrupoOfLidergrupoListLidergrupo = em.merge(oldGrupoOfLidergrupoListLidergrupo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupo(grupo.getId()) != null) {
                throw new PreexistingEntityException("Grupo " + grupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getId());
            Usuario usuarioduenoIdOld = persistentGrupo.getUsuarioduenoId();
            Usuario usuarioduenoIdNew = grupo.getUsuarioduenoId();
            List<Cuenta> cuentaListOld = persistentGrupo.getCuentaList();
            List<Cuenta> cuentaListNew = grupo.getCuentaList();
            List<PerteneceA> perteneceAListOld = persistentGrupo.getPerteneceAList();
            List<PerteneceA> perteneceAListNew = grupo.getPerteneceAList();
            List<Lidergrupo> lidergrupoListOld = persistentGrupo.getLidergrupoList();
            List<Lidergrupo> lidergrupoListNew = grupo.getLidergrupoList();
            List<String> illegalOrphanMessages = null;
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuenta " + cuentaListOldCuenta + " since its grupoId field is not nullable.");
                }
            }
            for (PerteneceA perteneceAListOldPerteneceA : perteneceAListOld) {
                if (!perteneceAListNew.contains(perteneceAListOldPerteneceA)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PerteneceA " + perteneceAListOldPerteneceA + " since its grupo field is not nullable.");
                }
            }
            for (Lidergrupo lidergrupoListOldLidergrupo : lidergrupoListOld) {
                if (!lidergrupoListNew.contains(lidergrupoListOldLidergrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lidergrupo " + lidergrupoListOldLidergrupo + " since its grupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioduenoIdNew != null) {
                usuarioduenoIdNew = em.getReference(usuarioduenoIdNew.getClass(), usuarioduenoIdNew.getId());
                grupo.setUsuarioduenoId(usuarioduenoIdNew);
            }
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getId());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            grupo.setCuentaList(cuentaListNew);
            List<PerteneceA> attachedPerteneceAListNew = new ArrayList<PerteneceA>();
            for (PerteneceA perteneceAListNewPerteneceAToAttach : perteneceAListNew) {
                perteneceAListNewPerteneceAToAttach = em.getReference(perteneceAListNewPerteneceAToAttach.getClass(), perteneceAListNewPerteneceAToAttach.getPerteneceAPK());
                attachedPerteneceAListNew.add(perteneceAListNewPerteneceAToAttach);
            }
            perteneceAListNew = attachedPerteneceAListNew;
            grupo.setPerteneceAList(perteneceAListNew);
            List<Lidergrupo> attachedLidergrupoListNew = new ArrayList<Lidergrupo>();
            for (Lidergrupo lidergrupoListNewLidergrupoToAttach : lidergrupoListNew) {
                lidergrupoListNewLidergrupoToAttach = em.getReference(lidergrupoListNewLidergrupoToAttach.getClass(), lidergrupoListNewLidergrupoToAttach.getLidergrupoPK());
                attachedLidergrupoListNew.add(lidergrupoListNewLidergrupoToAttach);
            }
            lidergrupoListNew = attachedLidergrupoListNew;
            grupo.setLidergrupoList(lidergrupoListNew);
            grupo = em.merge(grupo);
            if (usuarioduenoIdOld != null && !usuarioduenoIdOld.equals(usuarioduenoIdNew)) {
                usuarioduenoIdOld.getGrupoList().remove(grupo);
                usuarioduenoIdOld = em.merge(usuarioduenoIdOld);
            }
            if (usuarioduenoIdNew != null && !usuarioduenoIdNew.equals(usuarioduenoIdOld)) {
                usuarioduenoIdNew.getGrupoList().add(grupo);
                usuarioduenoIdNew = em.merge(usuarioduenoIdNew);
            }
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Grupo oldGrupoIdOfCuentaListNewCuenta = cuentaListNewCuenta.getGrupoId();
                    cuentaListNewCuenta.setGrupoId(grupo);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldGrupoIdOfCuentaListNewCuenta != null && !oldGrupoIdOfCuentaListNewCuenta.equals(grupo)) {
                        oldGrupoIdOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldGrupoIdOfCuentaListNewCuenta = em.merge(oldGrupoIdOfCuentaListNewCuenta);
                    }
                }
            }
            for (PerteneceA perteneceAListNewPerteneceA : perteneceAListNew) {
                if (!perteneceAListOld.contains(perteneceAListNewPerteneceA)) {
                    Grupo oldGrupoOfPerteneceAListNewPerteneceA = perteneceAListNewPerteneceA.getGrupo();
                    perteneceAListNewPerteneceA.setGrupo(grupo);
                    perteneceAListNewPerteneceA = em.merge(perteneceAListNewPerteneceA);
                    if (oldGrupoOfPerteneceAListNewPerteneceA != null && !oldGrupoOfPerteneceAListNewPerteneceA.equals(grupo)) {
                        oldGrupoOfPerteneceAListNewPerteneceA.getPerteneceAList().remove(perteneceAListNewPerteneceA);
                        oldGrupoOfPerteneceAListNewPerteneceA = em.merge(oldGrupoOfPerteneceAListNewPerteneceA);
                    }
                }
            }
            for (Lidergrupo lidergrupoListNewLidergrupo : lidergrupoListNew) {
                if (!lidergrupoListOld.contains(lidergrupoListNewLidergrupo)) {
                    Grupo oldGrupoOfLidergrupoListNewLidergrupo = lidergrupoListNewLidergrupo.getGrupo();
                    lidergrupoListNewLidergrupo.setGrupo(grupo);
                    lidergrupoListNewLidergrupo = em.merge(lidergrupoListNewLidergrupo);
                    if (oldGrupoOfLidergrupoListNewLidergrupo != null && !oldGrupoOfLidergrupoListNewLidergrupo.equals(grupo)) {
                        oldGrupoOfLidergrupoListNewLidergrupo.getLidergrupoList().remove(lidergrupoListNewLidergrupo);
                        oldGrupoOfLidergrupoListNewLidergrupo = em.merge(oldGrupoOfLidergrupoListNewLidergrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getId();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cuenta> cuentaListOrphanCheck = grupo.getCuentaList();
            for (Cuenta cuentaListOrphanCheckCuenta : cuentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the Cuenta " + cuentaListOrphanCheckCuenta + " in its cuentaList field has a non-nullable grupoId field.");
            }
            List<PerteneceA> perteneceAListOrphanCheck = grupo.getPerteneceAList();
            for (PerteneceA perteneceAListOrphanCheckPerteneceA : perteneceAListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the PerteneceA " + perteneceAListOrphanCheckPerteneceA + " in its perteneceAList field has a non-nullable grupo field.");
            }
            List<Lidergrupo> lidergrupoListOrphanCheck = grupo.getLidergrupoList();
            for (Lidergrupo lidergrupoListOrphanCheckLidergrupo : lidergrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the Lidergrupo " + lidergrupoListOrphanCheckLidergrupo + " in its lidergrupoList field has a non-nullable grupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioduenoId = grupo.getUsuarioduenoId();
            if (usuarioduenoId != null) {
                usuarioduenoId.getGrupoList().remove(grupo);
                usuarioduenoId = em.merge(usuarioduenoId);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
