
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitpay;

import Controladores.CuentaJpaController;
import Controladores.DeudaJpaController;
import Controladores.TransaccionJpaController;
import Controladores.UsuarioJpaController;
import Negocio.Deuda;
import Negocio.DeudaPK;
import Negocio.TransaccionPK;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pablo
 */
public class SplitPay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
        UsuarioJpaController contro = new UsuarioJpaController(emf) ;
        //contro.signIn( "hola","12345" );     
        DeudaPK deuda;
        deuda = new DeudaPK(2, (long)1, (short)1);
        memberToMemberTrans(45, deuda, emf, 50000, 'C');
        System.out.println(" Hola Mundo") ;
        int idGrupo = 1 ;
        int idUsu= 1 ;
        CuentaJpaController controCuenta = new CuentaJpaController(emf);
       // controCuenta.GruposdeUsuario(idUsu);
       // List<String>usu=contro.UsuariosdeGrupo(idGrupo);
     /*  controCuenta.RealizarBalanceGruposdeUsuario();*/
    }
    
    public static void memberToMemberTrans(int idPK, DeudaPK deudap, EntityManagerFactory emf, long cantidad, char tipo)
    {
        TransaccionJpaController controTrans = new TransaccionJpaController(emf);
        DeudaJpaController controDeuda = new DeudaJpaController(emf);
        TransaccionPK referencias = new TransaccionPK(idPK, deudap.getCuentaId(), deudap.getUsuarioId(), deudap.getIdDeuda());
        //Deuda deuda = controDeuda.findDeuda( deudap );
        System.out.println( controTrans.memberToMemberTrans( deudap.getUsuarioId(), deudap.getCuentaId(), deudap.getIdDeuda(), cantidad, tipo) );
        /*if(deuda.getCantidad() - cantidad < 0)
        {
            //TODO crear una deuda si la transaccion es mayor a la deuda a la persona que se le hizo la transaccion
        }*/
    }
}

