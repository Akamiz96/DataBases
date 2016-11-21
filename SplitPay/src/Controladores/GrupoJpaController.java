/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conecciones.ConeccionDatos;
import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
    public List<BigDecimal> UsuariosdeGrupo(int idGrupo) {
        EntityManager em = getEntityManager();
        Query buscarNombres;
        buscarNombres = em.createNativeQuery("SELECT distinct u.user_name,u.id, FROM Usuario u ,Pertenece_a p ,Grupo g WHERE p.Usuario_id =u.id and p.Grupo_id= ?").setParameter(1, idGrupo); //Probar de esta forma
        UsuarioJpaController controUsu = new UsuarioJpaController(emf) ;
        List<Object[]> listaNombres = buscarNombres.getResultList();
        List<BigDecimal> idsUsuario = new ArrayList<BigDecimal>();
        for (int i = 0; i < listaNombres.size(); i++) {
            BigDecimal idUsu = (BigDecimal) listaNombres.get(i)[1];
            if(controUsu.fechaSalidaUsuario(idUsu.intValueExact(), idGrupo)==true){
                idsUsuario.add(idUsu);
            System.out.println(idUsu);
            }
            
        }
        return idsUsuario;
    }
    
    public List<String> RealizarBalancedeUsuariosporGrupos(int idGrupo) {
        // Este debe devolver una lista de los usuario de tal grupo con sus deudas      
        EntityManager em = getEntityManager();
        Query buscaridGrupo;
        CuentaJpaController controCuenta = new CuentaJpaController(emf);
        // Si ocurre un error puede ser que el emf este en un null, tocaria volver a asignarle el EntityManagerFactory
        buscaridGrupo = em.createNativeQuery("Select g.id,g.nombre from Grupo g Where g.id=?  ").setParameter(1, idGrupo);
        List<Object[]> listidGr = buscaridGrupo.getResultList();
        List<BigDecimal> listaUsuarios = UsuariosdeGrupo(idGrupo);
        String devolver, nombCuenta, balanceS;
        int total = 0, balance;
        List<String> listaDevolver = new ArrayList<String>();
        UsuarioJpaController controUsu = new UsuarioJpaController(emf) ;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Query buscarnombre = em.createNativeQuery("Select u.id,u.user_name from Usuario u Where u.id=?  ").setParameter(1, listaUsuarios.get(i));
            List<Object[]> listidUsu = buscarnombre.getResultList();
            BigDecimal idUsu = (BigDecimal) listidUsu.get(0)[0];
            if(controUsu.fechaSalidaUsuario(idUsu.intValueExact(), idGrupo)==true){
                // IF IMPORTANTE
                
                List<String> cuentasUsu = controCuenta.RealizarBalanceCuentasdeUsuario(idGrupo, idUsu.intValueExact());
            // Me va a llegar una lista con datos como "almuerzo$300" tengo que tokenizarlo para sacar el balance de las cuentas y sumarlas todas
            for (int j = 0; j < cuentasUsu.size(); j++) {
                StringTokenizer st = new StringTokenizer(cuentasUsu.get(j), "$");
                nombCuenta = (st.nextToken()).trim();
                balanceS = (st.nextToken()).trim();
                balance = Integer.parseInt(balanceS);
                total = total + balance;
                System.out.println("Este es el nombre de la cuenta " + nombCuenta);
                System.out.println("Este es el balance de la cuenta " + balance);
                
            }
            String nomUsu = (String) listidUsu.get(0)[1];
            devolver = nomUsu + "$" + total;
            System.out.println("Esto es devolver "+ devolver);
            listaDevolver.add(devolver);
        }
            }
            
        
        return listaDevolver;
    }
    public void CambiarLider(int idGrupo, int idLider){
        EntityManager em = getEntityManager();
        //Averiguar como se hace un dato Date con la fecha actual  
        GregorianCalendar date = new GregorianCalendar() ; // Esto me da la fecha actual
        int anio = date.get(Calendar.YEAR) ;
        int mes = date.get(Calendar.MONTH)   ;
        int dia = date.get(Calendar.DAY_OF_MONTH)  ; 
        String union = anio+"$" + mes + "$"+ dia ;
        System.out.println(union);
            Date fecha = new Date(anio, mes, dia);
        ConeccionDatos ccn = new ConeccionDatos();
        try
        {   EntityTransaction et = em.getTransaction();
            et.begin();
            Query cambiarEstadoFechaSalida = em.createNativeQuery( "UPDATE LiderGrupo l SET l.fechaSalida=? WHERE l.Grupo_id = ? ").setParameter(1,fecha).setParameter(2,idGrupo);
            cambiarEstadoFechaSalida.executeUpdate();
            ccn.CrearLiderGrupo( idGrupo, idLider,fecha);
            Query cambiarIdLiderGrupo = em.createNativeQuery( "UPDATE Grupo g SET g.UsuarioDueno_id=? WHERE g.id = ? ").setParameter(1,idLider).setParameter(2,idGrupo) ;
            cambiarIdLiderGrupo.executeUpdate();
            et.commit();
        }
        catch( Exception e )
        {
            System.out.println(e);
        }
        finally
        {
            em.close();      
        }
    }
    
    public int revisarDueno(int id)
  {
	  EntityManager em = getEntityManager();
      Query buscaridGrupo;
      buscaridGrupo = em.createNativeQuery("select UsuarioDueno_id from grupo where id = ?").setParameter(1, id);
      Object xd = buscaridGrupo.getSingleResult();
      BigDecimal id2 = (BigDecimal)xd ; 
      int idUser = id2.intValueExact();
      return idUser;
}
    public void cambiarNombreGrupo(int idGrupo, String nombre){
        EntityManager em = getEntityManager();
        try{
        EntityTransaction et = em.getTransaction();
        et.begin();
        Query cambiarNombre = em.createNativeQuery("UPDATE GRUPO SET nombre=? WHERE id = ?").setParameter(1,nombre).setParameter(2,idGrupo) ;
        cambiarNombre.executeUpdate();
        et.commit() ;
        }catch( Exception e )
        {
            System.out.println(e);
        }
        finally
        {
            em.close();      
        }
        
    }
    
    public List<String> UsuariosdeGrupoConUsername(int idGrupo) {
        EntityManager em = getEntityManager();
        Query buscarNombres;
        buscarNombres = em.createNativeQuery("SELECT distinct u.user_name,u.id FROM Usuario u ,Pertenece_a p ,Grupo g WHERE p.Usuario_id =u.id and p.Grupo_id= ?").setParameter(1, idGrupo); //Probar de esta forma
        List<Object[]> listaNombres = buscarNombres.getResultList();
        List<String> datosUsuario = new ArrayList<String>();
        UsuarioJpaController controUsu = new UsuarioJpaController(emf) ;
        for (int i = 0; i < listaNombres.size(); i++) {           
            BigDecimal idUsu = (BigDecimal) listaNombres.get(i)[1];
            if(controUsu.fechaSalidaUsuario(idUsu.intValueExact(), idGrupo)==true){
            String username = (String)listaNombres.get(i)[0] ;
            String devolver = username + "$" + idUsu ;
            datosUsuario.add(devolver);
            System.out.println(devolver);    
            }
            
        }
        em.close();
        return datosUsuario;
    }
   
    public List<BigDecimal> listaID()
    {
        EntityManager em = getEntityManager();
        List<BigDecimal> resultado;
        try {   
            Query insertar = em.createNativeQuery("select ID from grupo");
            resultado = insertar.getResultList();
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return resultado;
    }
    
    public List<String> listaNombre()
    {
        EntityManager em = getEntityManager();
        List<String> resultado;
        try {   
            Query insertar = em.createNativeQuery("select nombre from grupo");
            resultado = insertar.getResultList();
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return resultado;
 
   }
    
     public void crearGrupo(String nombre, int duenoID){
        ConeccionDatos cn = new ConeccionDatos();
         EntityManager em = getEntityManager();
        try
        {   
            EntityTransaction et = em.getTransaction();
            et.begin();
            cn.CrearGrupo(nombre, duenoID);
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
