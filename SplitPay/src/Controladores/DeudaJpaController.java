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
import Negocio.Cuenta;
import Negocio.Deuda;
import Negocio.DeudaPK;
import Negocio.Usuario;
import Negocio.Transaccion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pablo
 */
public class DeudaJpaController implements Serializable {

    public DeudaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deuda deuda) throws PreexistingEntityException, Exception {
        if (deuda.getDeudaPK() == null) {
            deuda.setDeudaPK(new DeudaPK());
        }
        if (deuda.getTransaccionList() == null) {
            deuda.setTransaccionList(new ArrayList<Transaccion>());
        }
        deuda.getDeudaPK().setCuentaId(deuda.getCuenta().getId());
        deuda.getDeudaPK().setUsuarioId(deuda.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta cuenta = deuda.getCuenta();
            if (cuenta != null) {
                cuenta = em.getReference(cuenta.getClass(), cuenta.getId());
                deuda.setCuenta(cuenta);
            }
            Usuario usuario = deuda.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                deuda.setUsuario(usuario);
            }
            List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
            for (Transaccion transaccionListTransaccionToAttach : deuda.getTransaccionList()) {
                transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(), transaccionListTransaccionToAttach.getTransaccionPK());
                attachedTransaccionList.add(transaccionListTransaccionToAttach);
            }
            deuda.setTransaccionList(attachedTransaccionList);
            em.persist(deuda);
            if (cuenta != null) {
                cuenta.getDeudaList().add(deuda);
                cuenta = em.merge(cuenta);
            }
            if (usuario != null) {
                usuario.getDeudaList().add(deuda);
                usuario = em.merge(usuario);
            }
            for (Transaccion transaccionListTransaccion : deuda.getTransaccionList()) {
                Deuda oldDeudaOfTransaccionListTransaccion = transaccionListTransaccion.getDeuda();
                transaccionListTransaccion.setDeuda(deuda);
                transaccionListTransaccion = em.merge(transaccionListTransaccion);
                if (oldDeudaOfTransaccionListTransaccion != null) {
                    oldDeudaOfTransaccionListTransaccion.getTransaccionList().remove(transaccionListTransaccion);
                    oldDeudaOfTransaccionListTransaccion = em.merge(oldDeudaOfTransaccionListTransaccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDeuda(deuda.getDeudaPK()) != null) {
                throw new PreexistingEntityException("Deuda " + deuda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Deuda deuda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        deuda.getDeudaPK().setCuentaId(deuda.getCuenta().getId());
        deuda.getDeudaPK().setUsuarioId(deuda.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deuda persistentDeuda = em.find(Deuda.class, deuda.getDeudaPK());
            Cuenta cuentaOld = persistentDeuda.getCuenta();
            Cuenta cuentaNew = deuda.getCuenta();
            Usuario usuarioOld = persistentDeuda.getUsuario();
            Usuario usuarioNew = deuda.getUsuario();
            List<Transaccion> transaccionListOld = persistentDeuda.getTransaccionList();
            List<Transaccion> transaccionListNew = deuda.getTransaccionList();
            List<String> illegalOrphanMessages = null;
            for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
                if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transaccion " + transaccionListOldTransaccion + " since its deuda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cuentaNew != null) {
                cuentaNew = em.getReference(cuentaNew.getClass(), cuentaNew.getId());
                deuda.setCuenta(cuentaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                deuda.setUsuario(usuarioNew);
            }
            List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
                transaccionListNewTransaccionToAttach = em.getReference(transaccionListNewTransaccionToAttach.getClass(), transaccionListNewTransaccionToAttach.getTransaccionPK());
                attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
            }
            transaccionListNew = attachedTransaccionListNew;
            deuda.setTransaccionList(transaccionListNew);
            deuda = em.merge(deuda);
            if (cuentaOld != null && !cuentaOld.equals(cuentaNew)) {
                cuentaOld.getDeudaList().remove(deuda);
                cuentaOld = em.merge(cuentaOld);
            }
            if (cuentaNew != null && !cuentaNew.equals(cuentaOld)) {
                cuentaNew.getDeudaList().add(deuda);
                cuentaNew = em.merge(cuentaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getDeudaList().remove(deuda);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getDeudaList().add(deuda);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
                if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
                    Deuda oldDeudaOfTransaccionListNewTransaccion = transaccionListNewTransaccion.getDeuda();
                    transaccionListNewTransaccion.setDeuda(deuda);
                    transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
                    if (oldDeudaOfTransaccionListNewTransaccion != null && !oldDeudaOfTransaccionListNewTransaccion.equals(deuda)) {
                        oldDeudaOfTransaccionListNewTransaccion.getTransaccionList().remove(transaccionListNewTransaccion);
                        oldDeudaOfTransaccionListNewTransaccion = em.merge(oldDeudaOfTransaccionListNewTransaccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DeudaPK id = deuda.getDeudaPK();
                if (findDeuda(id) == null) {
                    throw new NonexistentEntityException("The deuda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DeudaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deuda deuda;
            try {
                deuda = em.getReference(Deuda.class, id);
                deuda.getDeudaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deuda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Transaccion> transaccionListOrphanCheck = deuda.getTransaccionList();
            for (Transaccion transaccionListOrphanCheckTransaccion : transaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Deuda (" + deuda + ") cannot be destroyed since the Transaccion " + transaccionListOrphanCheckTransaccion + " in its transaccionList field has a non-nullable deuda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cuenta cuenta = deuda.getCuenta();
            if (cuenta != null) {
                cuenta.getDeudaList().remove(deuda);
                cuenta = em.merge(cuenta);
            }
            Usuario usuario = deuda.getUsuario();
            if (usuario != null) {
                usuario.getDeudaList().remove(deuda);
                usuario = em.merge(usuario);
            }
            em.remove(deuda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Deuda> findDeudaEntities() {
        return findDeudaEntities(true, -1, -1);
    }

    public List<Deuda> findDeudaEntities(int maxResults, int firstResult) {
        return findDeudaEntities(false, maxResults, firstResult);
    }

    private List<Deuda> findDeudaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Deuda.class));
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

    public Deuda findDeuda(DeudaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deuda.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeudaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Deuda> rt = cq.from(Deuda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public int DeudasdeUsuario(int  idCuenta, int idUsu){
         
         EntityManager em = getEntityManager();
         int idDeuda;
         try {
            Query buscarDeuda = em.createNativeQuery("Select d.id_deuda, d.cantidad from Deuda d where d.Usuario_id= ? and d.Cuenta_id = ? ").setParameter(1,idUsu).setParameter(2,idCuenta) ;
            List<Object[]> deuda = buscarDeuda.getResultList();
            BigDecimal idDeudaBig = (BigDecimal)deuda.get(0)[0] ;
            idDeuda = idDeudaBig.intValueExact();
         }catch(Exception e){
             return -1;
         }
         finally
         {
             em.close();
         }
         return idDeuda ;
     }
    public void resolverDeudas(int idCuenta){
        //Me toca crear Transacciones para cada usuario que debe es esa cuenta para dejar la deuda en 0
        //Debo sumar las sumas de las Transaccion que haya hecho un usuario para despues restarlo con la cantidad cuenta para saber si tengo que crear otra Transaccion
        EntityManager em = getEntityManager();
        Query buscarCostoCuenta = em.createNativeQuery("Select c.costo,c.nombre from Cuenta c where c.id= ?").setParameter(1,idCuenta) ;
        List<Object[]> datosCuenta = buscarCostoCuenta.getResultList();
        BigDecimal costCuenta = (BigDecimal)datosCuenta.get(0)[0] ;
        int costoCuenta = costCuenta.intValueExact();
        Query buscarDeuda = em.createNativeQuery("Select d.cantidad,d.Usuario_id,d.Id_Deuda from Deuda d where d.Cuenta_id = ?").setParameter(1,idCuenta) ;
        List<Object[]> listaDeudas = buscarDeuda.getResultList();
        TransaccionJpaController controTrans = new TransaccionJpaController(emf) ;
        BigDecimal idUsu = new BigDecimal(0);
        for(int i=0;i<listaDeudas.size();i++){
            int totalDeuda=0;
            int totalTransaccion =0 ;
           BigDecimal deudac= (BigDecimal)listaDeudas.get(i)[0] ;
           BigDecimal deuda_id_usuario = (BigDecimal)listaDeudas.get(i)[1] ;
           BigDecimal deudaID = (BigDecimal)listaDeudas.get(i)[2] ;
           System.out.println("Este es el id de la deuda "+ deudaID);
           System.out.println("Este es el id del usuario "+ deuda_id_usuario);
           System.out.println("Esta es la cantidad de la deuda " + deudac);
           int deudaCant = deudac.intValueExact();
            Query buscarTransacciones = em.createNativeQuery("Select t.cantidad,t.id,t.Deuda_Usuario_Id  from Transaccion t,Usuario u where t.Deuda_Cuenta_id=? and t.Deuda_Id_Deuda=? and u.id = t.Deuda_Usuario_Id").setParameter(1,idCuenta).setParameter(2,deudaID.intValueExact());
            List<Object[]>listaTrans = buscarTransacciones.getResultList();
            if(listaTrans.size()!=0){
             for(int j=0;j<listaTrans.size();j++){
                BigDecimal cantT= (BigDecimal)listaTrans.get(j)[0] ;
                int cantTransac = cantT.intValueExact();
                totalTransaccion = totalTransaccion + cantTransac ;
                BigDecimal mismoUsuario = (BigDecimal) listaTrans.get(j)[2] ;
                System.out.println("Este tiene que ser el mismo Usuario "+mismoUsuario );
                 idUsu=(BigDecimal)listaTrans.get(0)[2];              
            }   
            }// Cuando el usuario no hay hecho ninguna transaccion, se hace una transaccion con el total de la deuda         
            System.out.println("Este es el total de las transacciones "+ totalTransaccion) ;
            int idUsuInt = deuda_id_usuario.intValueExact();
            totalDeuda= deudaCant - totalTransaccion ;
            System.out.println("Este es el usuario "+ idUsuInt);
            System.out.println("Este es total deuda "+ totalDeuda);
            if(totalDeuda>0){     
              System.out.println("Entra al if ");
                long idCuentaLong=(long)idCuenta ;
                short idDeudashort = deudaID.shortValueExact() ;
                controTrans.memberToMemberTrans(idUsuInt, idCuenta, idDeudashort, totalDeuda, 'C') ; 
                          
}
        }
    }

}
