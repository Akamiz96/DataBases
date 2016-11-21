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

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Negocio.Grupo;
import Negocio.Usuario;
import Negocio.Deuda;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public List<String> RealizarBalanceCuentasdeUsuario(int idGrupo, int idUsu) {
        EntityManager em = getEntityManager();
        Query buscarCuentas;
        Query buscarDeudas;
        Query buscarTransaccion;
        Query buscarDuenoCuenta;
        int i, j, z;
        BigDecimal total;
        List<String> listaDevolver = new ArrayList<String>();
        buscarCuentas = em.createNativeQuery("Select c.id,c.nombre from Cuenta c Where c.Grupo_id=? ").setParameter(1, idGrupo);
        List<Object[]> listaCuentas = buscarCuentas.getResultList();
        List<BigDecimal> idCuentas = new ArrayList<BigDecimal>();
        String devolver = "";
        for (i = 0; i < listaCuentas.size(); i++) {
            BigDecimal id_cuenta = (BigDecimal) listaCuentas.get(i)[0];
            idCuentas.add(id_cuenta);
            System.out.println(id_cuenta);
            devolver = (String) listaCuentas.get(i)[1];
            System.out.println(devolver);
            buscarDeudas = em.createNativeQuery("Select d.cantidad,d.Id_Deuda from Deuda d Where d.Usuario_id=? and d.Cuenta_id=? ").setParameter(1, idUsu).setParameter(2, idCuentas.get(i));
            List<Object[]> deuda_cuenta = buscarDeudas.getResultList();
            if (deuda_cuenta.size() > 0) {
                BigDecimal deuda_cantidad = (BigDecimal) deuda_cuenta.get(0)[0];
                BigDecimal id_deuda = (BigDecimal) deuda_cuenta.get(0)[1];
                buscarTransaccion = em.createNativeQuery("Select t.cantidad,t.id from Transaccion t Where t.Deuda_Usuario_id=? and t.Deuda_Cuenta_id=? and t.Deuda_Id_Deuda= ? ").setParameter(1, idUsu).setParameter(2, idCuentas.get(i)).setParameter(3, id_deuda);
                // Un posible error cuando pase este codigo al proyecto, verificar el nombre de los atributos en la busqueda de la Transaccion como t.Deuda_Id_Deuda
                List<Object[]> listaTransacciones = buscarTransaccion.getResultList();
                BigDecimal sumaTransacciones = new BigDecimal("0");
                BigDecimal mult = new BigDecimal("-1");
                total = deuda_cantidad.multiply(mult);
                for (j = 0; j < listaTransacciones.size(); j++) {
                    BigDecimal cant_Transaccion = (BigDecimal) listaTransacciones.get(j)[0];
                    sumaTransacciones = sumaTransacciones.add(cant_Transaccion);
                    total = sumaTransacciones.subtract(deuda_cantidad);
                }
                devolver = devolver + "$" + total + "$" + id_cuenta;
                System.out.println("Esto es total : " + devolver);
                listaDevolver.add(devolver);
            }
            if (deuda_cuenta.size() == 0) {
                buscarDuenoCuenta = em.createNativeQuery("Select c.costo,c.nombre from Cuenta c Where c.Grupo_id=? and c.Usuario_id=?").setParameter(1, idGrupo).setParameter(2, idUsu);
                List<Object[]> listaCosto = buscarDuenoCuenta.getResultList();
                if(listaCosto.size()!=0){
                    BigDecimal costoCuenta = (BigDecimal) listaCosto.get(0)[0];
                    total = costoCuenta;
                devolver = devolver + "$" + total+ "$"+ id_cuenta;
                listaDevolver.add(devolver);
                System.out.println(" Esto es el costo de la cuenta en la que es dueño : " + devolver);
                }
                
            }
        }
        return listaDevolver;
    }
    public List<String> TablaUsuarioCuentaGrupo(int idUsu) {
        // Busco los grupos del usuario
        // Le mando el id del grupo y el id del usuario al metodo RealizarBalanceCuentasdeUsuario
        //Esto me manda una lista de las cuentas junto con su balance
        EntityManager em = getEntityManager();
        UsuarioJpaController controUsu = new UsuarioJpaController(emf);
        // Si ocurre un error puede ser que el emf este en un null, tocaria volver a asignarle el EntityManagerFactory
        List<BigDecimal> gruposUsu = controUsu.GruposdeUsuario(idUsu);
        List<String> listaDevolver = new ArrayList<String>();
        String devolver;
        for (int i = 0; i < gruposUsu.size(); i++) {
            List<String> cuentaUsu = RealizarBalanceCuentasdeUsuario(gruposUsu.get(i).intValueExact(), idUsu);
            for (int j = 0; j < cuentaUsu.size(); j++) {
                Query nombreGrupo = em.createNativeQuery("Select g.nombre,g.id from Grupo g Where g.id=? ").setParameter(1, gruposUsu.get(i));
                List<Object[]> nGrupo = nombreGrupo.getResultList();
                String grupo = (String) nGrupo.get(0)[0];
                System.out.println("Esto es el nombre del grupo : " + grupo);
                BigDecimal decgrupo = (BigDecimal) nGrupo.get(0)[1];
                int id_grupo = decgrupo.intValueExact();
                devolver = cuentaUsu.get(j) + "$" + grupo+ "$"+id_grupo;
                listaDevolver.add(devolver);
            }

        }
        return listaDevolver;
    }

    public List<String> fechas()
    {
        EntityManager em = getEntityManager();
        List<String> resultado;
        try {
            Query insertar = em.createNativeQuery("select to_char(cuenta.FECHA_CREACION, 'yyyy-month') as fecha from cuenta group by to_char(cuenta.FECHA_CREACION, 'yyyy-month')");
            resultado = insertar.getResultList();
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return resultado;
    }

    public int numBillsPorFecha( int grupoId, String fecha )
    {
        EntityManager em = getEntityManager();
        int resultado = 0;
        try {
            Query insertar = em.createNativeQuery("select count(*) from cuenta where ? = cuenta.GRUPO_ID AND to_char(cuenta.FECHA_CREACION, 'yyyy-month') like '%'|| ? ||'%' group by to_char(cuenta.FECHA_CREACION, 'yyyy-month')");
            insertar.setParameter(1, grupoId);
            insertar.setParameter(2, fecha);
            resultado = ((BigDecimal) insertar.getSingleResult()).intValue();
        } catch(Exception e) {
            System.out.println(e);
            return -1;
        } finally {
            em.close();
        }
    	return resultado;
    }

    public int totalPorGrupo( int grupoId )
    {
        EntityManager em = getEntityManager();
        int resultado = 0;
        try {
            Query insertar = em.createNativeQuery("select count(*) from cuenta where ? = cuenta.GRUPO_ID");
            insertar.setParameter(1, grupoId);
            resultado = ((BigDecimal) insertar.getSingleResult()).intValue();
        } catch(Exception e) {
            System.out.println(e);
            return -1;
        } finally {
            em.close();
        }
    	return resultado;
    }
     public void crearCuenta(String username,int cantidad) throws SQLException{
   	 EntityManager em = getEntityManager();
   	 Query q = em.createNativeQuery("select Max(id) FROM Cuenta");
   	 BigDecimal id = (BigDecimal)q.getSingleResult();
   	 int idMax = id.intValueExact();

   	  q = em.createNativeQuery("select id FROM Usuario where user_name = ?").setParameter(1, username);
   	 id = (BigDecimal)q.getSingleResult();
   	 int idUser = id.intValueExact();

   	 try
        {
            EntityTransaction et = em.getTransaction();
            et.begin();
            Query insertar = em.createNativeQuery("INSERT INTO deuda values(?,?,?,CU_id_SEQ.NEXTVAL)");
            insertar.setParameter(1, cantidad);
            insertar.setParameter(2, idUser);
            insertar.setParameter(3, idMax);

            insertar.executeUpdate();
            et.commit();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            em.close();
        }
    }
}
