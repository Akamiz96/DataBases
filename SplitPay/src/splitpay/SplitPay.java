/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitpay;

import Controladores.UsuarioJpaController;
import Negocio.Usuario;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "SplitPayPU" );
        UsuarioJpaController uControl = new UsuarioJpaController( emf );
        Usuario user = uControl.signIn( "andres111", "468946431");
        if( user != null)
            System.out.println("splitpay.SplitPay.main()");
        uControl.signOut(user);
    }
    
}
