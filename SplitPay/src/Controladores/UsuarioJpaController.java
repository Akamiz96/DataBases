/*
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

import Negocio.Usuario;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Negocio.Grupo;
import Negocio.Ubicacion;
import Negocio.Cuenta;
import Negocio.PerteneceA;
import Negocio.Lidergrupo;
import Negocio.Deuda;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Pablo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getUsuarioList() == null) {
            usuario.setUsuarioList(new ArrayList<Usuario>());
        }
        if (usuario.getUsuarioList1() == null) {
            usuario.setUsuarioList1(new ArrayList<Usuario>());
        }
        if (usuario.getGrupoList() == null) {
            usuario.setGrupoList(new ArrayList<Grupo>());
        }
        if (usuario.getUbicacionList() == null) {
            usuario.setUbicacionList(new ArrayList<Ubicacion>());
        }
        if (usuario.getCuentaList() == null) {
            usuario.setCuentaList(new ArrayList<Cuenta>());
        }
        if (usuario.getPerteneceAList() == null) {
            usuario.setPerteneceAList(new ArrayList<PerteneceA>());
        }
        if (usuario.getLidergrupoList() == null) {
            usuario.setLidergrupoList(new ArrayList<Lidergrupo>());
        }
        if (usuario.getDeudaList() == null) {
            usuario.setDeudaList(new ArrayList<Deuda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : usuario.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getId());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            usuario.setUsuarioList(attachedUsuarioList);
            List<Usuario> attachedUsuarioList1 = new ArrayList<Usuario>();
            for (Usuario usuarioList1UsuarioToAttach : usuario.getUsuarioList1()) {
                usuarioList1UsuarioToAttach = em.getReference(usuarioList1UsuarioToAttach.getClass(), usuarioList1UsuarioToAttach.getId());
                attachedUsuarioList1.add(usuarioList1UsuarioToAttach);
            }
            usuario.setUsuarioList1(attachedUsuarioList1);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : usuario.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getId());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            usuario.setGrupoList(attachedGrupoList);
            List<Ubicacion> attachedUbicacionList = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListUbicacionToAttach : usuario.getUbicacionList()) {
                ubicacionListUbicacionToAttach = em.getReference(ubicacionListUbicacionToAttach.getClass(), ubicacionListUbicacionToAttach.getUbicacionPK());
                attachedUbicacionList.add(ubicacionListUbicacionToAttach);
            }
            usuario.setUbicacionList(attachedUbicacionList);
            List<Cuenta> attachedCuentaList = new ArrayList<Cuenta>();
            for (Cuenta cuentaListCuentaToAttach : usuario.getCuentaList()) {
                cuentaListCuentaToAttach = em.getReference(cuentaListCuentaToAttach.getClass(), cuentaListCuentaToAttach.getId());
                attachedCuentaList.add(cuentaListCuentaToAttach);
            }
            usuario.setCuentaList(attachedCuentaList);
            List<PerteneceA> attachedPerteneceAList = new ArrayList<PerteneceA>();
            for (PerteneceA perteneceAListPerteneceAToAttach : usuario.getPerteneceAList()) {
                perteneceAListPerteneceAToAttach = em.getReference(perteneceAListPerteneceAToAttach.getClass(), perteneceAListPerteneceAToAttach.getPerteneceAPK());
                attachedPerteneceAList.add(perteneceAListPerteneceAToAttach);
            }
            usuario.setPerteneceAList(attachedPerteneceAList);
            List<Lidergrupo> attachedLidergrupoList = new ArrayList<Lidergrupo>();
            for (Lidergrupo lidergrupoListLidergrupoToAttach : usuario.getLidergrupoList()) {
                lidergrupoListLidergrupoToAttach = em.getReference(lidergrupoListLidergrupoToAttach.getClass(), lidergrupoListLidergrupoToAttach.getLidergrupoPK());
                attachedLidergrupoList.add(lidergrupoListLidergrupoToAttach);
            }
            usuario.setLidergrupoList(attachedLidergrupoList);
            List<Deuda> attachedDeudaList = new ArrayList<Deuda>();
            for (Deuda deudaListDeudaToAttach : usuario.getDeudaList()) {
                deudaListDeudaToAttach = em.getReference(deudaListDeudaToAttach.getClass(), deudaListDeudaToAttach.getDeudaPK());
                attachedDeudaList.add(deudaListDeudaToAttach);
            }
            usuario.setDeudaList(attachedDeudaList);
            em.persist(usuario);
            for (Usuario usuarioListUsuario : usuario.getUsuarioList()) {
                usuarioListUsuario.getUsuarioList().add(usuario);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            for (Usuario usuarioList1Usuario : usuario.getUsuarioList1()) {
                usuarioList1Usuario.getUsuarioList().add(usuario);
                usuarioList1Usuario = em.merge(usuarioList1Usuario);
            }
            for (Grupo grupoListGrupo : usuario.getGrupoList()) {
                Usuario oldUsuarioduenoIdOfGrupoListGrupo = grupoListGrupo.getUsuarioduenoId();
                grupoListGrupo.setUsuarioduenoId(usuario);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldUsuarioduenoIdOfGrupoListGrupo != null) {
                    oldUsuarioduenoIdOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldUsuarioduenoIdOfGrupoListGrupo = em.merge(oldUsuarioduenoIdOfGrupoListGrupo);
                }
            }
            for (Ubicacion ubicacionListUbicacion : usuario.getUbicacionList()) {
                Usuario oldUsuarioOfUbicacionListUbicacion = ubicacionListUbicacion.getUsuario();
                ubicacionListUbicacion.setUsuario(usuario);
                ubicacionListUbicacion = em.merge(ubicacionListUbicacion);
                if (oldUsuarioOfUbicacionListUbicacion != null) {
                    oldUsuarioOfUbicacionListUbicacion.getUbicacionList().remove(ubicacionListUbicacion);
                    oldUsuarioOfUbicacionListUbicacion = em.merge(oldUsuarioOfUbicacionListUbicacion);
                }
            }
            for (Cuenta cuentaListCuenta : usuario.getCuentaList()) {
                Usuario oldUsuarioIdOfCuentaListCuenta = cuentaListCuenta.getUsuarioId();
                cuentaListCuenta.setUsuarioId(usuario);
                cuentaListCuenta = em.merge(cuentaListCuenta);
                if (oldUsuarioIdOfCuentaListCuenta != null) {
                    oldUsuarioIdOfCuentaListCuenta.getCuentaList().remove(cuentaListCuenta);
                    oldUsuarioIdOfCuentaListCuenta = em.merge(oldUsuarioIdOfCuentaListCuenta);
                }
            }
            for (PerteneceA perteneceAListPerteneceA : usuario.getPerteneceAList()) {
                Usuario oldUsuarioOfPerteneceAListPerteneceA = perteneceAListPerteneceA.getUsuario();
                perteneceAListPerteneceA.setUsuario(usuario);
                perteneceAListPerteneceA = em.merge(perteneceAListPerteneceA);
                if (oldUsuarioOfPerteneceAListPerteneceA != null) {
                    oldUsuarioOfPerteneceAListPerteneceA.getPerteneceAList().remove(perteneceAListPerteneceA);
                    oldUsuarioOfPerteneceAListPerteneceA = em.merge(oldUsuarioOfPerteneceAListPerteneceA);
                }
            }
            for (Lidergrupo lidergrupoListLidergrupo : usuario.getLidergrupoList()) {
                Usuario oldUsuarioOfLidergrupoListLidergrupo = lidergrupoListLidergrupo.getUsuario();
                lidergrupoListLidergrupo.setUsuario(usuario);
                lidergrupoListLidergrupo = em.merge(lidergrupoListLidergrupo);
                if (oldUsuarioOfLidergrupoListLidergrupo != null) {
                    oldUsuarioOfLidergrupoListLidergrupo.getLidergrupoList().remove(lidergrupoListLidergrupo);
                    oldUsuarioOfLidergrupoListLidergrupo = em.merge(oldUsuarioOfLidergrupoListLidergrupo);
                }
            }
            for (Deuda deudaListDeuda : usuario.getDeudaList()) {
                Usuario oldUsuarioOfDeudaListDeuda = deudaListDeuda.getUsuario();
                deudaListDeuda.setUsuario(usuario);
                deudaListDeuda = em.merge(deudaListDeuda);
                if (oldUsuarioOfDeudaListDeuda != null) {
                    oldUsuarioOfDeudaListDeuda.getDeudaList().remove(deudaListDeuda);
                    oldUsuarioOfDeudaListDeuda = em.merge(oldUsuarioOfDeudaListDeuda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getId()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Usuario> usuarioListOld = persistentUsuario.getUsuarioList();
            List<Usuario> usuarioListNew = usuario.getUsuarioList();
            List<Usuario> usuarioList1Old = persistentUsuario.getUsuarioList1();
            List<Usuario> usuarioList1New = usuario.getUsuarioList1();
            List<Grupo> grupoListOld = persistentUsuario.getGrupoList();
            List<Grupo> grupoListNew = usuario.getGrupoList();
            List<Ubicacion> ubicacionListOld = persistentUsuario.getUbicacionList();
            List<Ubicacion> ubicacionListNew = usuario.getUbicacionList();
            List<Cuenta> cuentaListOld = persistentUsuario.getCuentaList();
            List<Cuenta> cuentaListNew = usuario.getCuentaList();
            List<PerteneceA> perteneceAListOld = persistentUsuario.getPerteneceAList();
            List<PerteneceA> perteneceAListNew = usuario.getPerteneceAList();
            List<Lidergrupo> lidergrupoListOld = persistentUsuario.getLidergrupoList();
            List<Lidergrupo> lidergrupoListNew = usuario.getLidergrupoList();
            List<Deuda> deudaListOld = persistentUsuario.getDeudaList();
            List<Deuda> deudaListNew = usuario.getDeudaList();
            List<String> illegalOrphanMessages = null;
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupo " + grupoListOldGrupo + " since its usuarioduenoId field is not nullable.");
                }
            }
            for (Ubicacion ubicacionListOldUbicacion : ubicacionListOld) {
                if (!ubicacionListNew.contains(ubicacionListOldUbicacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ubicacion " + ubicacionListOldUbicacion + " since its usuario field is not nullable.");
                }
            }
            for (Cuenta cuentaListOldCuenta : cuentaListOld) {
                if (!cuentaListNew.contains(cuentaListOldCuenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuenta " + cuentaListOldCuenta + " since its usuarioId field is not nullable.");
                }
            }
            for (PerteneceA perteneceAListOldPerteneceA : perteneceAListOld) {
                if (!perteneceAListNew.contains(perteneceAListOldPerteneceA)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PerteneceA " + perteneceAListOldPerteneceA + " since its usuario field is not nullable.");
                }
            }
            for (Lidergrupo lidergrupoListOldLidergrupo : lidergrupoListOld) {
                if (!lidergrupoListNew.contains(lidergrupoListOldLidergrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lidergrupo " + lidergrupoListOldLidergrupo + " since its usuario field is not nullable.");
                }
            }
            for (Deuda deudaListOldDeuda : deudaListOld) {
                if (!deudaListNew.contains(deudaListOldDeuda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Deuda " + deudaListOldDeuda + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getId());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            usuario.setUsuarioList(usuarioListNew);
            List<Usuario> attachedUsuarioList1New = new ArrayList<Usuario>();
            for (Usuario usuarioList1NewUsuarioToAttach : usuarioList1New) {
                usuarioList1NewUsuarioToAttach = em.getReference(usuarioList1NewUsuarioToAttach.getClass(), usuarioList1NewUsuarioToAttach.getId());
                attachedUsuarioList1New.add(usuarioList1NewUsuarioToAttach);
            }
            usuarioList1New = attachedUsuarioList1New;
            usuario.setUsuarioList1(usuarioList1New);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getId());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            usuario.setGrupoList(grupoListNew);
            List<Ubicacion> attachedUbicacionListNew = new ArrayList<Ubicacion>();
            for (Ubicacion ubicacionListNewUbicacionToAttach : ubicacionListNew) {
                ubicacionListNewUbicacionToAttach = em.getReference(ubicacionListNewUbicacionToAttach.getClass(), ubicacionListNewUbicacionToAttach.getUbicacionPK());
                attachedUbicacionListNew.add(ubicacionListNewUbicacionToAttach);
            }
            ubicacionListNew = attachedUbicacionListNew;
            usuario.setUbicacionList(ubicacionListNew);
            List<Cuenta> attachedCuentaListNew = new ArrayList<Cuenta>();
            for (Cuenta cuentaListNewCuentaToAttach : cuentaListNew) {
                cuentaListNewCuentaToAttach = em.getReference(cuentaListNewCuentaToAttach.getClass(), cuentaListNewCuentaToAttach.getId());
                attachedCuentaListNew.add(cuentaListNewCuentaToAttach);
            }
            cuentaListNew = attachedCuentaListNew;
            usuario.setCuentaList(cuentaListNew);
            List<PerteneceA> attachedPerteneceAListNew = new ArrayList<PerteneceA>();
            for (PerteneceA perteneceAListNewPerteneceAToAttach : perteneceAListNew) {
                perteneceAListNewPerteneceAToAttach = em.getReference(perteneceAListNewPerteneceAToAttach.getClass(), perteneceAListNewPerteneceAToAttach.getPerteneceAPK());
                attachedPerteneceAListNew.add(perteneceAListNewPerteneceAToAttach);
            }
            perteneceAListNew = attachedPerteneceAListNew;
            usuario.setPerteneceAList(perteneceAListNew);
            List<Lidergrupo> attachedLidergrupoListNew = new ArrayList<Lidergrupo>();
            for (Lidergrupo lidergrupoListNewLidergrupoToAttach : lidergrupoListNew) {
                lidergrupoListNewLidergrupoToAttach = em.getReference(lidergrupoListNewLidergrupoToAttach.getClass(), lidergrupoListNewLidergrupoToAttach.getLidergrupoPK());
                attachedLidergrupoListNew.add(lidergrupoListNewLidergrupoToAttach);
            }
            lidergrupoListNew = attachedLidergrupoListNew;
            usuario.setLidergrupoList(lidergrupoListNew);
            List<Deuda> attachedDeudaListNew = new ArrayList<Deuda>();
            for (Deuda deudaListNewDeudaToAttach : deudaListNew) {
                deudaListNewDeudaToAttach = em.getReference(deudaListNewDeudaToAttach.getClass(), deudaListNewDeudaToAttach.getDeudaPK());
                attachedDeudaListNew.add(deudaListNewDeudaToAttach);
            }
            deudaListNew = attachedDeudaListNew;
            usuario.setDeudaList(deudaListNew);
            usuario = em.merge(usuario);
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getUsuarioList().remove(usuario);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getUsuarioList().add(usuario);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            for (Usuario usuarioList1OldUsuario : usuarioList1Old) {
                if (!usuarioList1New.contains(usuarioList1OldUsuario)) {
                    usuarioList1OldUsuario.getUsuarioList().remove(usuario);
                    usuarioList1OldUsuario = em.merge(usuarioList1OldUsuario);
                }
            }
            for (Usuario usuarioList1NewUsuario : usuarioList1New) {
                if (!usuarioList1Old.contains(usuarioList1NewUsuario)) {
                    usuarioList1NewUsuario.getUsuarioList().add(usuario);
                    usuarioList1NewUsuario = em.merge(usuarioList1NewUsuario);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Usuario oldUsuarioduenoIdOfGrupoListNewGrupo = grupoListNewGrupo.getUsuarioduenoId();
                    grupoListNewGrupo.setUsuarioduenoId(usuario);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldUsuarioduenoIdOfGrupoListNewGrupo != null && !oldUsuarioduenoIdOfGrupoListNewGrupo.equals(usuario)) {
                        oldUsuarioduenoIdOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldUsuarioduenoIdOfGrupoListNewGrupo = em.merge(oldUsuarioduenoIdOfGrupoListNewGrupo);
                    }
                }
            }
            for (Ubicacion ubicacionListNewUbicacion : ubicacionListNew) {
                if (!ubicacionListOld.contains(ubicacionListNewUbicacion)) {
                    Usuario oldUsuarioOfUbicacionListNewUbicacion = ubicacionListNewUbicacion.getUsuario();
                    ubicacionListNewUbicacion.setUsuario(usuario);
                    ubicacionListNewUbicacion = em.merge(ubicacionListNewUbicacion);
                    if (oldUsuarioOfUbicacionListNewUbicacion != null && !oldUsuarioOfUbicacionListNewUbicacion.equals(usuario)) {
                        oldUsuarioOfUbicacionListNewUbicacion.getUbicacionList().remove(ubicacionListNewUbicacion);
                        oldUsuarioOfUbicacionListNewUbicacion = em.merge(oldUsuarioOfUbicacionListNewUbicacion);
                    }
                }
            }
            for (Cuenta cuentaListNewCuenta : cuentaListNew) {
                if (!cuentaListOld.contains(cuentaListNewCuenta)) {
                    Usuario oldUsuarioIdOfCuentaListNewCuenta = cuentaListNewCuenta.getUsuarioId();
                    cuentaListNewCuenta.setUsuarioId(usuario);
                    cuentaListNewCuenta = em.merge(cuentaListNewCuenta);
                    if (oldUsuarioIdOfCuentaListNewCuenta != null && !oldUsuarioIdOfCuentaListNewCuenta.equals(usuario)) {
                        oldUsuarioIdOfCuentaListNewCuenta.getCuentaList().remove(cuentaListNewCuenta);
                        oldUsuarioIdOfCuentaListNewCuenta = em.merge(oldUsuarioIdOfCuentaListNewCuenta);
                    }
                }
            }
            for (PerteneceA perteneceAListNewPerteneceA : perteneceAListNew) {
                if (!perteneceAListOld.contains(perteneceAListNewPerteneceA)) {
                    Usuario oldUsuarioOfPerteneceAListNewPerteneceA = perteneceAListNewPerteneceA.getUsuario();
                    perteneceAListNewPerteneceA.setUsuario(usuario);
                    perteneceAListNewPerteneceA = em.merge(perteneceAListNewPerteneceA);
                    if (oldUsuarioOfPerteneceAListNewPerteneceA != null && !oldUsuarioOfPerteneceAListNewPerteneceA.equals(usuario)) {
                        oldUsuarioOfPerteneceAListNewPerteneceA.getPerteneceAList().remove(perteneceAListNewPerteneceA);
                        oldUsuarioOfPerteneceAListNewPerteneceA = em.merge(oldUsuarioOfPerteneceAListNewPerteneceA);
                    }
                }
            }
            for (Lidergrupo lidergrupoListNewLidergrupo : lidergrupoListNew) {
                if (!lidergrupoListOld.contains(lidergrupoListNewLidergrupo)) {
                    Usuario oldUsuarioOfLidergrupoListNewLidergrupo = lidergrupoListNewLidergrupo.getUsuario();
                    lidergrupoListNewLidergrupo.setUsuario(usuario);
                    lidergrupoListNewLidergrupo = em.merge(lidergrupoListNewLidergrupo);
                    if (oldUsuarioOfLidergrupoListNewLidergrupo != null && !oldUsuarioOfLidergrupoListNewLidergrupo.equals(usuario)) {
                        oldUsuarioOfLidergrupoListNewLidergrupo.getLidergrupoList().remove(lidergrupoListNewLidergrupo);
                        oldUsuarioOfLidergrupoListNewLidergrupo = em.merge(oldUsuarioOfLidergrupoListNewLidergrupo);
                    }
                }
            }
            for (Deuda deudaListNewDeuda : deudaListNew) {
                if (!deudaListOld.contains(deudaListNewDeuda)) {
                    Usuario oldUsuarioOfDeudaListNewDeuda = deudaListNewDeuda.getUsuario();
                    deudaListNewDeuda.setUsuario(usuario);
                    deudaListNewDeuda = em.merge(deudaListNewDeuda);
                    if (oldUsuarioOfDeudaListNewDeuda != null && !oldUsuarioOfDeudaListNewDeuda.equals(usuario)) {
                        oldUsuarioOfDeudaListNewDeuda.getDeudaList().remove(deudaListNewDeuda);
                        oldUsuarioOfDeudaListNewDeuda = em.merge(oldUsuarioOfDeudaListNewDeuda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Grupo> grupoListOrphanCheck = usuario.getGrupoList();
            for (Grupo grupoListOrphanCheckGrupo : grupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Grupo " + grupoListOrphanCheckGrupo + " in its grupoList field has a non-nullable usuarioduenoId field.");
            }
            List<Ubicacion> ubicacionListOrphanCheck = usuario.getUbicacionList();
            for (Ubicacion ubicacionListOrphanCheckUbicacion : ubicacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Ubicacion " + ubicacionListOrphanCheckUbicacion + " in its ubicacionList field has a non-nullable usuario field.");
            }
            List<Cuenta> cuentaListOrphanCheck = usuario.getCuentaList();
            for (Cuenta cuentaListOrphanCheckCuenta : cuentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cuenta " + cuentaListOrphanCheckCuenta + " in its cuentaList field has a non-nullable usuarioId field.");
            }
            List<PerteneceA> perteneceAListOrphanCheck = usuario.getPerteneceAList();
            for (PerteneceA perteneceAListOrphanCheckPerteneceA : perteneceAListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the PerteneceA " + perteneceAListOrphanCheckPerteneceA + " in its perteneceAList field has a non-nullable usuario field.");
            }
            List<Lidergrupo> lidergrupoListOrphanCheck = usuario.getLidergrupoList();
            for (Lidergrupo lidergrupoListOrphanCheckLidergrupo : lidergrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Lidergrupo " + lidergrupoListOrphanCheckLidergrupo + " in its lidergrupoList field has a non-nullable usuario field.");
            }
            List<Deuda> deudaListOrphanCheck = usuario.getDeudaList();
            for (Deuda deudaListOrphanCheckDeuda : deudaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Deuda " + deudaListOrphanCheckDeuda + " in its deudaList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> usuarioList = usuario.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getUsuarioList().remove(usuario);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Usuario> usuarioList1 = usuario.getUsuarioList1();
            for (Usuario usuarioList1Usuario : usuarioList1) {
                usuarioList1Usuario.getUsuarioList().remove(usuario);
                usuarioList1Usuario = em.merge(usuarioList1Usuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Usuario signIn( String userName, String contrasena )throws SQLException
    {
        EntityManager em = getEntityManager();
        Usuario user = null;
        try
        {   EntityTransaction et = em.getTransaction();
            et.begin();
            Query consulta = em.createNativeQuery( "SELECT * FROM Usuario u WHERE u.user_name = ? AND u.contrasena = ?", Usuario.class );
            consulta.setParameter( 1, userName );
            consulta.setParameter( 2, contrasena );
            user = (Usuario)consulta.getSingleResult();
            Query update = em.createNativeQuery( "UPDATE Usuario SET \"online\" = 'Y' WHERE contrasena = ? and user_name = ?" );
            update.setParameter(2, userName);
            update.setParameter(1, contrasena);
            update.executeUpdate();
            et.commit();
            return user;
            
        }
        catch( Exception e )
        {
            
            throw new SQLException();
        }
        finally
        {
            em.close(); 
            
        }
       
    }
    
    public void signOut( Usuario user )
    {
        EntityManager em = getEntityManager();
        try
        {   
            EntityTransaction et = em.getTransaction();
            et.begin();
            Query update = em.createNativeQuery( "UPDATE Usuario SET \"online\" = 'N' WHERE user_name = ?" );
            update.setParameter(1, user.getUserName());
            update.executeUpdate();
            et.commit();
        }
        finally
        {
            em.close();
        }
        
    }
    public List<BigDecimal> GruposdeUsuario(int idUsuario) {
        EntityManager em = getEntityManager();
        Query buscarNombres;
        //buscarNombres = em.createNativeQuery("SELECT u.nombre,u.id FROM Cuenta u" ); //Probar de esta forma
        // buscarNombres = em.createNativeQuery("SELECT g.nombre,g.id,p.Usuario_id FROM Grupo g,Usuario u,Pertenece_a p");
        buscarNombres = em.createNativeQuery("SELECT distinct g.nombre,g.id FROM Usuario u ,Pertenece_a p ,Grupo g WHERE p.Usuario_id =? and g.id = p.Grupo_id").setParameter(1, idUsuario);
        List<Object[]> gruponombres = buscarNombres.getResultList();
        int x = 0;
        List<BigDecimal> idGrupos = new ArrayList<BigDecimal>();
        for (int i = 0; i < gruponombres.size(); i++) {
            BigDecimal idGrupo = (BigDecimal) gruponombres.get(x)[1];
            idGrupos.add(idGrupo);
            x++;
        }
        for (int j = 0; j < idGrupos.size(); j++) {
            System.out.println(idGrupos.get(j));
        }
        return idGrupos;

    }
    public List<String> RealizarBalanceGruposdeUsuario(int idUsu) {
        EntityManager em = getEntityManager();
        Query buscarCuentas;
        Query buscarDeudas;
        Query buscarTransaccion;
        Query buscarDuenoCuenta;
        Query buscarNombreGrupo;
        int i, j, z;
        BigDecimal total, total2;
        List<String> listaDevolver = new ArrayList<String>();
        List<BigDecimal> Grupos = GruposdeUsuario(idUsu);
        BigDecimal costoCuenta ;
        UsuarioJpaController controUsu= new UsuarioJpaController(emf);
        for (z = 0; z < Grupos.size(); z++) {
            total2 = new BigDecimal("0");
            buscarCuentas = em.createNativeQuery("Select c.id,c.nombre from Cuenta c Where c.Grupo_id=? ").setParameter(1, Grupos.get(z));
            List<Object[]> listaCuentas = buscarCuentas.getResultList();
            List<BigDecimal> idCuentas = new ArrayList<BigDecimal>();
            buscarNombreGrupo = em.createNativeQuery("Select g.nombre,g.id from Grupo g Where g.id=? ").setParameter(1, Grupos.get(z));
            List<Object[]> nombreGrupo = buscarNombreGrupo.getResultList();
            String nombreGr = (String) nombreGrupo.get(0)[0];
            String devolver = String.valueOf(nombreGr);
            System.out.println("Este es el nombre del grupo: " + devolver);
            BigDecimal idGru = (BigDecimal)nombreGrupo.get(0)[1]; 
            //devolver =  ; //Devolver seria igual al nombre del grupo, cuando lo busque ;
            for (i = 0; i < listaCuentas.size(); i++) {
                BigDecimal id_cuenta = (BigDecimal) listaCuentas.get(i)[0];
                idCuentas.add(id_cuenta);
                System.out.println("Este es el id de la cuenta : " + id_cuenta);
                buscarDeudas = em.createNativeQuery("Select d.cantidad,d.Id_Deuda from Deuda d Where d.Usuario_id=? and d.Cuenta_id=? ").setParameter(1, idUsu).setParameter(2, idCuentas.get(i));
                List<Object[]> deuda_cuenta = buscarDeudas.getResultList();
                if (deuda_cuenta.size() > 0) {
                    BigDecimal deuda_cantidad = (BigDecimal) deuda_cuenta.get(0)[0];
                    BigDecimal id_deuda = (BigDecimal) deuda_cuenta.get(0)[1];
                    System.out.println("Este es el id de la deuda "+id_deuda );
                    buscarTransaccion = em.createNativeQuery("Select t.cantidad,t.id from Transaccion t Where t.Deuda_Usuario_id=? and t.Deuda_Cuenta_id=? and t.Deuda_Id_Deuda= ? ").setParameter(1, idUsu).setParameter(2, idCuentas.get(i)).setParameter(3, id_deuda);
                    // Un posible error cuando pase este codigo al proyecto, verificar el nombre de los atributos en la busqueda de la Transaccion como t.Deuda_Id_Deuda
                    List<Object[]> listaTransacciones = buscarTransaccion.getResultList();
                    BigDecimal sumaTransacciones = new BigDecimal("0");                    
                    BigDecimal mult = new BigDecimal("-1");
                    total = deuda_cantidad.multiply(mult);
                    for (j = 0; j < listaTransacciones.size(); j++) {
                        BigDecimal cant_Transaccion = (BigDecimal) listaTransacciones.get(j)[0];
                        System.out.println("Este es la cantidad de la transaccion "+cant_Transaccion );
                        sumaTransacciones = sumaTransacciones.add(cant_Transaccion);
                        total = sumaTransacciones.subtract(deuda_cantidad);
                    }
                    total2 = total2.add(total);
                }
                if (deuda_cuenta.size() == 0) {
                    System.out.println("Entro a que la deuda esta vacia");
                	System.out.println("Este es el id del usuario " + idUsu );
                	System.out.println("Este es el id del grupo "+ idGru);
                    buscarDuenoCuenta = em.createNativeQuery("Select c.costo,c.nombre from Cuenta c Where c.Grupo_id=? and c.Usuario_id=?").setParameter(1, idGru).setParameter(2, idUsu);
                    List<Object[]> listaCosto = buscarDuenoCuenta.getResultList();
                    if(listaCosto.size()==0){
                    	total = new BigDecimal(0) ;
                    	total2 = total2.add(total);     	
                    }
                    else{
                    	costoCuenta = (BigDecimal) listaCosto.get(0)[0];
                        System.out.println("Es dueno de la cuenta y vale "+costoCuenta );
                        total = costoCuenta;
                        System.out.println("Esto es total "+total);
                        total2 = total2.add(total);
                       System.out.println("Esto es TOTAL 2  "+total2);
                    }
                    
                }
            }
           
            
            devolver = devolver + "$" + total2 + "$" + idGru;
            listaDevolver.add(devolver);
            System.out.println("Este es valor que tiene en el grupo: " + devolver);

        }
        return listaDevolver;
    }
     
     public List<String> ContactosUsuario(int idUsuario) {
        // Recibo id y devuelvo los nombres con el correo de los contactos del usario, telefono, username y estado online
        EntityManager em = getEntityManager();
        int i = 0;
        List<String> listaDevolver = new ArrayList<String>();
        String devolver;
        Query buscarIdContacto = em.createNativeQuery("Select d.Usuario_id1,d.Usuario_id from contacto_de d Where d.Usuario_id=? ").setParameter(1, idUsuario);
        List<Object[]> idContactoUsuario = buscarIdContacto.getResultList();
        for (i = 0; i < idContactoUsuario.size(); i++) {
            BigDecimal idContacto = (BigDecimal) idContactoUsuario.get(i)[0];
            Query buscarUsuario = em.createNativeQuery("Select u.nombre,u.email,u.numeroTelefono,u.user_name,\"online\" from Usuario u Where u.id=? ").setParameter(1, idContacto.intValueExact());
            // En el Select colocar el estado online, cuando se actualice la tabla de Usuario con ese atributo
            List<Object[]> Usuario = buscarUsuario.getResultList();
            String nomContacto = (String) Usuario.get(0)[0];
            String emailContacto = (String) Usuario.get(0)[1];
            BigDecimal telefonoContacto = (BigDecimal) Usuario.get(0)[2];
            String usernameContacto = (String) Usuario.get(0)[3];
            String telefono = String.valueOf(telefonoContacto);
            String online = (String)Usuario.get(0)[4] ;
            devolver = nomContacto + "$" + emailContacto + "$" + usernameContacto + "$" + telefono+"$"+online;
            listaDevolver.add(devolver);
        }
        return listaDevolver;
    }
      
     public List<String> TransaccionesdeUsuario(int idUsu){
         // Como posibilidad, mostrar tambien si la transaccion fue aceptada o no 
         EntityManager em = getEntityManager();
         Query buscarTransacciones = em.createNativeQuery("Select t.fecha,t.cantidad,t.tipo  from Transaccion t where t.Deuda_Usuario_Id= ?").setParameter(1,idUsu) ;
         List<Object[]> transaccion = buscarTransacciones.getResultList();
         List<String> devolver = new ArrayList<String>();
         BigDecimal cantidad ;
         String tipo ;
         
         for(int i=0;i<transaccion.size();i++){
             GregorianCalendar fecha = (GregorianCalendar)transaccion.get(i)[0] ;
             cantidad = (BigDecimal)transaccion.get(i)[1];
             tipo = (String)transaccion.get(i)[2];
             String fechaS= fecha.toString();
             System.out.println(fechaS);
         }
         return devolver ;
     }
     
     public boolean fechaSalidaUsuario(int idUsu, int idGrupo){
         EntityManager em = getEntityManager();
         Query buscarFechaSalida = em.createNativeQuery("Select p.fecha_salida,p.fecha_ingreso from Pertenece_a p where p.Usuario_id=? and p.Grupo_id=?").setParameter(1,idUsu).setParameter(2,idGrupo) ;
         List<Object[]> fechas = buscarFechaSalida.getResultList();
         Timestamp fechaSalida = (Timestamp)fechas.get(0)[0] ;
         if(fechaSalida==null){
             return true ;
         }
         else{
             return false ;
         }
     }
     public void EliminarUsuarioGrupo(int idUsuario, int idGrupo){
         EntityManager em = getEntityManager();
         GregorianCalendar date = new GregorianCalendar() ; // Esto me da la fecha actual
        int anio = date.get(Calendar.YEAR) ;
        int mes = date.get(Calendar.MONTH)   ;
        int dia = date.get(Calendar.DAY_OF_MONTH)  ; 
        String union = anio+"$" + mes + "$"+ dia ;
            Date fecha = new Date(anio, mes, dia);
         Query colocarFechaSalida = em.createNativeQuery("UPDATE Pertenece_a SET fecha_salida=? WHERE Usuario_id = ? and Grupo_id = ?").setParameter(1,fecha).setParameter(2,idUsuario).setParameter(3,idGrupo) ;
         colocarFechaSalida.executeUpdate();
     }
     
}