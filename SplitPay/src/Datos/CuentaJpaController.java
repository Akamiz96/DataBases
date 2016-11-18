/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Negocio.Cuenta;

import java.io.Serializable;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Negocio.Grupo;
import Negocio.Usuario;
import Negocio.Deuda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
    public List<BigDecimal> GruposdeUsuario(int idUsuario){
        EntityManager em = getEntityManager() ;
             Query buscarNombres ;
           //buscarNombres = em.createNativeQuery("SELECT u.nombre,u.id FROM Cuenta u" ); //Probar de esta forma
           // buscarNombres = em.createNativeQuery("SELECT g.nombre,g.id,p.Usuario_id FROM Grupo g,Usuario u,Pertenece_a p");
            buscarNombres = em.createNativeQuery("SELECT distinct g.nombre,g.id FROM Usuario u ,Pertenece_a p ,Grupo g WHERE p.Usuario_id =? and g.id = p.Grupo_id").setParameter(1,idUsuario);
            List<Object[]> gruponombres = buscarNombres.getResultList() ;
             int x=0;
             List<BigDecimal>idGrupos= new ArrayList<BigDecimal>();
              for(int i=0;i<gruponombres.size();i++){
                     BigDecimal idGrupo = (BigDecimal)gruponombres.get(x)[1] ;
                    idGrupos.add(idGrupo) ; 
                    x++;
             }
                  for(int j=0;j<idGrupos.size();j++) {
                        System.out.println(idGrupos.get(j));
                    }
                  return idGrupos ;
         
    }
         
         public List<String> UsuariosdeGrupo(int idGrupo){
             EntityManager em = getEntityManager() ;
             Query buscarNombres ;
             buscarNombres = em.createNativeQuery("SELECT distinct u.nombre,u.id FROM Usuario u ,Pertenece_a p ,Grupo g WHERE p.Usuario_id =u.id and p.Grupo_id= ?").setParameter(1,idGrupo); //Probar de esta forma
             List<Object[]> listaNombres = buscarNombres.getResultList() ;
             List<String>nombresUsuario= new ArrayList<String>();
             for(int i=0;i<listaNombres.size();i++){
                 String nombre = (String)listaNombres.get(i)[0] ;
                    nombresUsuario.add(nombre) ; 
                 System.out.println(nombre);
             }
             return nombresUsuario;
         }
        
         public List<String> RealizarBalanceCuentasdeUsuario(int idGrupo, int idUsu){
             EntityManager em = getEntityManager() ;
             Query buscarCuentas ;
             Query buscarDeudas ;
             Query buscarTransaccion ;
             Query buscarDuenoCuenta ;
             int i,j,z ;
              BigDecimal total;
              List<String> listaDevolver = new ArrayList<String>();       
             buscarCuentas = em.createNativeQuery("Select c.id,c.nombre from Cuenta c Where c.Grupo_id=? " ).setParameter(1, idGrupo);
             List<Object[]> listaCuentas = buscarCuentas.getResultList() ;
             List<BigDecimal>idCuentas= new ArrayList<BigDecimal>();
             String devolver ="";
             for(i=0;i<listaCuentas.size();i++){
                 BigDecimal id_cuenta = (BigDecimal)listaCuentas.get(i)[0] ;
                    idCuentas.add(id_cuenta) ; 
                 System.out.println(id_cuenta); 
                  devolver = (String)listaCuentas.get(i)[1] ;
                 System.out.println(devolver);
                 buscarDeudas=  em.createNativeQuery("Select d.cantidad,d.Id_Deuda from Deuda d Where d.Usuario_id=? and d.Cuenta_id=? " ).setParameter(1, idUsu).setParameter(2,idCuentas.get(i) );
                 List<Object[]> deuda_cuenta = buscarDeudas.getResultList() ;
                 if(deuda_cuenta.size()>0){
                     BigDecimal deuda_cantidad = (BigDecimal)deuda_cuenta.get(0)[0] ;
                     BigDecimal id_deuda = (BigDecimal) deuda_cuenta.get(0)[1] ;
                 buscarTransaccion= em.createNativeQuery("Select t.cantidad,t.id from Transaccion t Where t.Deuda_Usuario_id=? and t.Deuda_Cuenta_id=? and t.id_Deuda= ? " ).setParameter(1,idUsu).setParameter(2,idCuentas.get(i)).setParameter(3,id_deuda) ;
                // Un posible error cuando pase este codigo al proyecto, verificar el nombre de los atributos en la busqueda de la Transaccion como t.Deuda_Id_Deuda
                 List<Object[]> listaTransacciones = buscarTransaccion.getResultList() ;
                 BigDecimal sumaTransacciones= new BigDecimal("0");
                 BigDecimal mult = new BigDecimal("-1") ;
                 total= deuda_cantidad.multiply(mult);
                 for(j=0;j<listaTransacciones.size();j++){
                      BigDecimal cant_Transaccion = (BigDecimal)listaTransacciones.get(j)[0] ;                   
                      sumaTransacciones= sumaTransacciones.add(cant_Transaccion) ;   
                 total= sumaTransacciones.subtract(deuda_cantidad) ;           
                 }
                 devolver = devolver + "$" + total ;
                 System.out.println("Esto es total : "+ devolver) ; 
                 listaDevolver.add(devolver);
                 } 
                 if(deuda_cuenta.size()==0){
                      buscarDuenoCuenta = em.createNativeQuery("Select c.costo,c.nombre from Cuenta c Where c.Grupo_id=? and c.Usuario_id=?" ).setParameter(1, idGrupo).setParameter(2,idUsu);
                      List<Object[]> listaCosto = buscarDuenoCuenta.getResultList() ;
                      BigDecimal costoCuenta = (BigDecimal)listaCosto.get(0)[0] ;
                      total= costoCuenta ;
                      devolver = devolver + "$" + total ;
                      listaDevolver.add(devolver);
                      System.out.println(" Esto es el costo de la cuenta en la que es dueño : "+devolver) ;     
                 }
             } 
             return listaDevolver ;
         }
    
    
    public List<String> RealizarBalanceGruposdeUsuario(int idGrupo,int idUsu){
             EntityManager em = getEntityManager() ;
             Query buscarCuentas ;
             Query buscarDeudas ;
             Query buscarTransaccion ;
             Query buscarDuenoCuenta ;
             Query buscarNombreGrupo ;
             int i,j,z ;
              BigDecimal total,total2;
              List<String> listaDevolver = new ArrayList<String>();
              List<BigDecimal>Grupos= GruposdeUsuario(idUsu) ;
              for(z=0;z<Grupos.size();z++){  
                  total2= new BigDecimal("0") ;
             buscarCuentas = em.createNativeQuery("Select c.id,c.nombre from Cuenta c Where c.Grupo_id=? " ).setParameter(1, Grupos.get(z));
             List<Object[]> listaCuentas = buscarCuentas.getResultList() ;
             List<BigDecimal>idCuentas= new ArrayList<BigDecimal>();
             buscarNombreGrupo = em.createNativeQuery("Select g.nombre,g.id from Grupo g Where g.id=? " ).setParameter(1, Grupos.get(z)) ;
             List<Object[]> nombreGrupo = buscarNombreGrupo.getResultList();
             String nombreGr = (String)nombreGrupo.get(0)[0] ;
             String devolver = String.valueOf(nombreGr);
             System.out.println("Este es el nombre del grupo: "+ devolver);
             //devolver =  ; //Devolver seria igual al nombre del grupo, cuando lo busque ;
             for(i=0;i<listaCuentas.size();i++){
                 BigDecimal id_cuenta = (BigDecimal)listaCuentas.get(i)[0] ;
                    idCuentas.add(id_cuenta) ; 
                 System.out.println("Este es el id de la cuenta : "+id_cuenta); 
                 buscarDeudas=  em.createNativeQuery("Select d.cantidad,d.Id_Deuda from Deuda d Where d.Usuario_id=? and d.Cuenta_id=? " ).setParameter(1, idUsu).setParameter(2,idCuentas.get(i) );
                 List<Object[]> deuda_cuenta = buscarDeudas.getResultList() ;
                 if(deuda_cuenta.size()>0){
                     BigDecimal deuda_cantidad = (BigDecimal)deuda_cuenta.get(0)[0] ;
                     BigDecimal id_deuda = (BigDecimal) deuda_cuenta.get(0)[1] ;
                 buscarTransaccion= em.createNativeQuery("Select t.cantidad,t.id from Transaccion t Where t.Deuda_Usuario_id=? and t.Deuda_Cuenta_id=? and t.id_Deuda= ? " ).setParameter(1,idUsu).setParameter(2,idCuentas.get(i)).setParameter(3,id_deuda) ;
                // Un posible error cuando pase este codigo al proyecto, verificar el nombre de los atributos en la busqueda de la Transaccion como t.Deuda_Id_Deuda
                 List<Object[]> listaTransacciones = buscarTransaccion.getResultList() ;
                 BigDecimal sumaTransacciones= new BigDecimal("0");
                 BigDecimal mult = new BigDecimal("-1") ;
                 total= deuda_cantidad.multiply(mult);
                 for(j=0;j<listaTransacciones.size();j++){
                      BigDecimal cant_Transaccion = (BigDecimal)listaTransacciones.get(j)[0] ;                   
                      sumaTransacciones= sumaTransacciones.add(cant_Transaccion) ;   
                 total= sumaTransacciones.subtract(deuda_cantidad) ;           
                 }
                 total2= total2.add(total) ;
                 } 
                 if(deuda_cuenta.size()==0){
                      buscarDuenoCuenta = em.createNativeQuery("Select c.costo,c.nombre from Cuenta c Where c.Grupo_id=? and c.Usuario_id=?" ).setParameter(1, idGrupo).setParameter(2,idUsu);
                      List<Object[]> listaCosto = buscarDuenoCuenta.getResultList() ;
                      BigDecimal costoCuenta = (BigDecimal)listaCosto.get(0)[0] ;
                      total= costoCuenta ;
                      total2= total2.add(total) ;    
                 }
             } 
                        devolver = devolver + "$" + total2 ;
                        listaDevolver.add(devolver);
                        System.out.println("Este es valor que tiene en el grupo: "+devolver);
             }
             return listaDevolver ;
         }
    public List<String> RealizarBalancedeUsuariosporGrupos(String nombGrupo){
        // Este debe devolver una lista de los usuario de tal grupo con sus deudas      
        EntityManager em = getEntityManager() ;
             Query buscaridGrupo ;
             buscaridGrupo= em.createNativeQuery("Select g.id,g.nombre from Grupo g Where g.nombre=?  " ).setParameter(1,nombGrupo) ;
             List<Object[]> listidGr = buscaridGrupo.getResultList();
             BigDecimal idG=(BigDecimal)listidGr.get(0)[0];
             int idGrupo =idG.intValueExact() ;
             List<String> listaUsuarios = UsuariosdeGrupo(idGrupo) ;
             String devolver,nombCuenta,balanceS ;
             int total=0,balance ;
             List<String> listaDevolver = new ArrayList<String>() ;
             for(int i=0;i<listaUsuarios.size();i++){
                 Query buscarnombre=  em.createNativeQuery("Select u.id,u.nombre from Usuario u Where u.nombre=?  " ).setParameter(1,listaUsuarios.get(i)) ;
                 List<Object[]> listidUsu = buscarnombre.getResultList();
                 BigDecimal idUsu=(BigDecimal)listidUsu.get(i)[0];
                 List<String>cuentasUsu=RealizarBalanceCuentasdeUsuario(idGrupo,idUsu.intValueExact() ); 
                 // Me va a llegar una lista con datos como "almuerzo$300" tengo que tokenizarlo para sacar el balance de las cuentas y sumarlas todas
                for(int j=0;j<cuentasUsu.size();j++){
                    StringTokenizer st = new StringTokenizer(cuentasUsu.get(j),"$");
                    nombCuenta = (st.nextToken()).trim() ;
                    balanceS= (st.nextToken()).trim() ;
                    balance= Integer.parseInt(balanceS) ;
                    total = total + balance ;
                    System.out.println("Este es el nombre de la cuenta "+ nombCuenta);
                    System.out.println("Este es el balance de la cuenta "+balance);
                }
                String nomUsu= (String)listidUsu.get(i)[1] ;
                devolver = nomUsu + "$"+ total ;
                 listaDevolver.add(devolver);
             }
             
             return listaDevolver ;
         }
    
    public List<String> ContactosUsuario(int idUsuario){
        // Recibo id y devuelvo los nombres con el correo de los contactos del usario, telefono, username y estado online
        EntityManager em = getEntityManager() ;
        int i=0;
        List<String>listaDevolver = new ArrayList<String>();
        String devolver ;
        Query buscarIdContacto = em.createNativeQuery("Select d.Usuario_id1,d.Usuario_id from contacto_de d Where d.Usuario_id=? " ).setParameter(1,idUsuario ) ;
             List<Object[]> idContactoUsuario = buscarIdContacto.getResultList();
             for(i=0;i<idContactoUsuario.size();i++){
                 BigDecimal idContacto = (BigDecimal)idContactoUsuario.get(i)[0] ;
                 Query buscarUsuario = em.createNativeQuery("Select u.nombre,u.email,u.numeroTelefono,u.user_name from Usuario u Where u.id=? " ).setParameter(1,idContacto.intValueExact() ) ;
                 // En el Select colocar el estado online, cuando se actualice la tabla de Usuario con ese atributo
                 List<Object[]> Usuario = buscarUsuario.getResultList();
                 String nomContacto = (String)Usuario.get(i)[0] ;
                 String emailContacto = (String)Usuario.get(i)[1] ;
                 BigDecimal telefonoContacto = (BigDecimal)Usuario.get(i)[2] ;
                 String usernameContacto = (String)Usuario.get(i)[3] ;
                 String telefono = String.valueOf(telefonoContacto) ;
                 devolver=nomContacto +"$"+emailContacto+"$"+usernameContacto+"$"+telefono ;
                 listaDevolver.add(devolver);
             }
             return listaDevolver ;
    }
    public List<String> TablaUsuarioCuentaGrupo(int idUsu ){
        
        // Busco los grupos del usuario 
        // Le mando el id del grupo y el id del usuario al metodo RealizarBalanceCuentasdeUsuario
        //Esto me manda una lista de las cuentas junto con su balance
        EntityManager em = getEntityManager() ;
        List<BigDecimal>gruposUsu=GruposdeUsuario(idUsu) ;
        List<String> listaDevolver = new ArrayList<String>();
        String devolver ;
        for(int i=0;i<gruposUsu.size();i++){
          List<String>cuentaUsu=RealizarBalanceCuentasdeUsuario(gruposUsu.get(i).intValueExact(),idUsu ) ;
          for(int j=0;j<cuentaUsu.size();j++){
          Query nombreGrupo = em.createNativeQuery("Select g.nombre,g.id from Grupo g Where g.id=? " ).setParameter(1,gruposUsu.get(i)) ;
          List<Object[]> nGrupo= nombreGrupo.getResultList();
          String grupo =(String)nGrupo.get(0)[0];
          System.out.println("Esto es el nombre del grupo : "+grupo) ;
          devolver = cuentaUsu.get(j)+"$"+ grupo ;
          listaDevolver.add(devolver) ;
          }
         
        }
        return listaDevolver ;      
      }

    public void AgregarContacto(int idUsu, String username){
        //Me envian la id del usuario y el username 
        //Agregar contacto de un usuario
        // Recibo el username, reviso si existe y lo agrego a la la tabla de contactos de con el id de los dos usuarios
        EntityManager em = getEntityManager() ;
        Query revisarUsuario= em.createNamedQuery("Select u.id,u.user_name from Usuario u where u.user_name=?").setParameter( 1,username ) ;
        if(revisarUsuario!=null){
            // Llamar una funcion que haga en la base de datos que me haga el insert values en la tabla de Contacto_de
        }
        else{
            System.out.println("El usuario no existe");
        }
    }
}
