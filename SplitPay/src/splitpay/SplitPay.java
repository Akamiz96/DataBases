
package splitpay;

import Controladores.CuentaJpaController;
import Controladores.GrupoJpaController;
import Controladores.UsuarioJpaController;
import Negocio.Grupo;

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
		GrupoJpaController gr = new GrupoJpaController(emf);
	
		
    
}
}
