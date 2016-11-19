package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class JPTutoGrupo extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	public JPTutoGrupo(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblGruposActuales = new JLabel("Crear ceunta");
		lblGruposActuales.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGruposActuales.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGruposActuales.setBounds(50, 63, 156, 28);
		panel.add(lblGruposActuales);
		
		JLabel lblCrearGruop = new JLabel("Transacciones");
		lblCrearGruop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCrearGruop.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCrearGruop.setBounds(82, 125, 124, 28);
		panel.add(lblCrearGruop);
		
		JLabel lblCuentas = new JLabel("Miembros");
		lblCuentas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCuentas.setBounds(82, 174, 124, 28);
		panel.add(lblCuentas);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContactos.setBounds(82, 224, 124, 28);
		panel.add(lblContactos);
		
		JTextPane txtpnEnEstaOpcin = new JTextPane();
		txtpnEnEstaOpcin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin.setText("El usuario actual pude crear cuentas  para el grupo actual. El usuario puede agregar usuarios a dicha cuenta, teniendo en cuenta que esos usuarios deben estar en el grupo.");
		txtpnEnEstaOpcin.setBounds(247, 63, 400, 51);
		panel.add(txtpnEnEstaOpcin);
		
		JTextPane txtpnEnEstaOpcin_1 = new JTextPane();
		txtpnEnEstaOpcin_1.setText("el usuario puede realizar una transacci\u00F3n para pagar una deuda que tenga ");
		txtpnEnEstaOpcin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_1.setBounds(247, 125, 400, 39);
		panel.add(txtpnEnEstaOpcin_1);
		
		JTextPane txtpnEnEstaOpcin_2 = new JTextPane();
		txtpnEnEstaOpcin_2.setText("el usuario puede ver el balance de los integrantes de ese grupo");
		txtpnEnEstaOpcin_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_2.setBounds(247, 174, 400, 39);
		panel.add(txtpnEnEstaOpcin_2);
		
		JTextPane txtpnEnEstaOpcin_3 = new JTextPane();
		txtpnEnEstaOpcin_3.setText("En esta opci\u00F3n el usuario puede ver sus contactos actuales, adem\u00E1s puede agregar nuevos contactos escribiendo el username del contacto a ingresar.");
		txtpnEnEstaOpcin_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnEnEstaOpcin_3.setBounds(247, 224, 400, 59);
		panel.add(txtpnEnEstaOpcin_3);
		
		JLabel lblDescripcionDeOpciones = new JLabel("Descripcion de opciones de grupo");
		lblDescripcionDeOpciones.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescripcionDeOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionDeOpciones.setBounds(0, 11, 790, 28);
		panel.add(lblDescripcionDeOpciones);
		
		JLabel lblOpcionesAdministrador = new JLabel("Opciones administrador");
		lblOpcionesAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcionesAdministrador.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOpcionesAdministrador.setBounds(0, 277, 790, 28);
		panel.add(lblOpcionesAdministrador);
		
		JLabel lblCambiarMiembros = new JLabel("Cambiar Miembros");
		lblCambiarMiembros.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambiarMiembros.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCambiarMiembros.setBounds(0, 316, 206, 28);
		panel.add(lblCambiarMiembros);
		
		JLabel lblCambiarLider = new JLabel("Cambiar lider");
		lblCambiarLider.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCambiarLider.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCambiarLider.setBounds(92, 355, 124, 28);
		panel.add(lblCambiarLider);
		
		JLabel lblLimpiarDeudas = new JLabel("Limpiar Deudas");
		lblLimpiarDeudas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimpiarDeudas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLimpiarDeudas.setBounds(50, 409, 166, 28);
		panel.add(lblLimpiarDeudas);
		
		JTextPane txtpnElAdministradorPuede_1 = new JTextPane();
		txtpnElAdministradorPuede_1.setText("El administrador puede agregar o eliminar miembros al grupo");
		txtpnElAdministradorPuede_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnElAdministradorPuede_1.setBounds(247, 316, 400, 39);
		panel.add(txtpnElAdministradorPuede_1);
		
		JTextPane txtpnElAdministradosPuede = new JTextPane();
		txtpnElAdministradosPuede.setText("El administrados puede escoger a un usuario para que sea nuevo lider del grupo.");
		txtpnElAdministradosPuede.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnElAdministradosPuede.setBounds(247, 367, 400, 39);
		panel.add(txtpnElAdministradosPuede);
		
		JTextPane txtpnElAdministradorPuede = new JTextPane();
		txtpnElAdministradorPuede.setText("El administrador puede eliminar cuentas con el fin de eliminar las deudas de esa ceunta");
		txtpnElAdministradorPuede.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnElAdministradorPuede.setBounds(247, 410, 400, 39);
		panel.add(txtpnElAdministradorPuede);


	}

}
