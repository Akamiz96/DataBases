package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class JPTutoMain extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	public JPTutoMain(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblGruposActuales = new JLabel("Grupos Actuales:");
		lblGruposActuales.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGruposActuales.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGruposActuales.setBounds(50, 63, 156, 28);
		panel.add(lblGruposActuales);
		
		JLabel lblCrearGruop = new JLabel("Crear grupo:");
		lblCrearGruop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCrearGruop.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCrearGruop.setBounds(82, 141, 124, 28);
		panel.add(lblCrearGruop);
		
		JLabel lblCuentas = new JLabel("Cuentas:");
		lblCuentas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCuentas.setBounds(82, 251, 124, 28);
		panel.add(lblCuentas);
		
		JLabel lblContactos = new JLabel("Contactos:");
		lblContactos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContactos.setBounds(82, 361, 124, 28);
		panel.add(lblContactos);
		
		JTextPane txtpnEnEstaOpcin = new JTextPane();
		txtpnEnEstaOpcin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin.setText("En esta opci\u00F3n el usuario puede ver los grupos a los que pertenece  y puede escoger el grupo al que quiera entrar");
		txtpnEnEstaOpcin.setBounds(247, 63, 400, 51);
		panel.add(txtpnEnEstaOpcin);
		
		JTextPane txtpnEnEstaOpcin_1 = new JTextPane();
		txtpnEnEstaOpcin_1.setText("En esta opci\u00F3n el usuario tiene la oportunidad de crear un grupo ingresando el nombre los contactos que quiere que pertenezcan al grupo.  Obligatoriamente el usuario a ingresar debe ser contacto del lider");
		txtpnEnEstaOpcin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_1.setBounds(247, 141, 400, 99);
		panel.add(txtpnEnEstaOpcin_1);
		
		JTextPane txtpnEnEstaOpcin_2 = new JTextPane();
		txtpnEnEstaOpcin_2.setText("En esta opci\u00F3n el usuario puede ver todas las cuentas a las que pertenece, adem\u00E1s de ver a que grupo es dicha cuenta. Esta opci\u00F3n solo muestra las cuentas, para hacer una transacci\u00F3n a una cuenta es necesario entrar al grupo al que pertenece la cuenta.");
		txtpnEnEstaOpcin_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_2.setBounds(247, 251, 400, 99);
		panel.add(txtpnEnEstaOpcin_2);
		
		JTextPane txtpnEnEstaOpcin_3 = new JTextPane();
		txtpnEnEstaOpcin_3.setText("En esta opci\u00F3n el usuario puede ver sus contactos actuales, adem\u00E1s puede agregar nuevos contactos escribiendo el username del contacto a ingresar.");
		txtpnEnEstaOpcin_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_3.setBounds(247, 361, 400, 99);
		panel.add(txtpnEnEstaOpcin_3);
		
		JLabel lblDescripcionDeOpciones = new JLabel("Descripcion de opciones");
		lblDescripcionDeOpciones.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescripcionDeOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionDeOpciones.setBounds(0, 11, 790, 28);
		panel.add(lblDescripcionDeOpciones);

	}
}
