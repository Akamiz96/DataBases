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
import Negocio.Usuario;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "SplitPayPU" );
        UsuarioJpaController uControl = new UsuarioJpaController( emf );
        Usuario user = uControl.signIn( "andres111", "468946431");
        if( user != null)
            System.out.println("splitpay.SplitPay.main()");
        uControl.signOut(user);      

        System.out.println(" Hola Mundo") ;
        int idGrupo = 1 ;
        int idUsu= 1 ;
        CuentaJpaController controCuenta = new CuentaJpaController(emf);
        //controCuenta.GruposdeUsuario(idUsu);
       // List<String>usu=contro.UsuariosdeGrupo(idGrupo);
     /*  controCuenta.RealizarBalanceGruposdeUsuario();*/
    }
    
    
    public static void memberToMemberTrans(int idPK, long deudaCuentaId, int deudaUsuarioId, short deudaIdDeuda, EntityManagerFactory emf, long cantidad, char tipo)
    {
        TransaccionJpaController controTrans = new TransaccionJpaController(emf);
        DeudaJpaController controDeuda = new DeudaJpaController(emf);
        TransaccionPK referencias = new TransaccionPK(idPK, deudaCuentaId, deudaUsuarioId, deudaIdDeuda);
        Deuda deuda = controDeuda.findDeuda(new DeudaPK());
        controTrans.memberToMemberTrans(referencias, cantidad, tipo);
        if(deuda.getCantidad() - cantidad < 0)
        {
            //TODO crear una deuda si la transaccion es mayor a la deuda a la persona que se le hizo la transaccion
        }
    }
}
