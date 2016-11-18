package splitpay;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class Navegacion extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu ;
	/**
	 * Create the panel.
	 */
	public Navegacion(GUIPrincipal principal, PMenu menu ) {
		
		this.principal = principal;
		this.menu = menu;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 780, 87);
		
		add(panel);
		panel.setLayout(null);
		
		JButton BT_gruposActuales = new JButton("Grupos Actuales");
		BT_gruposActuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gruposActuales();
			}
		});
		BT_gruposActuales.setHorizontalAlignment(SwingConstants.LEFT);
		BT_gruposActuales.setBounds(10, 11, 140, 52);
		panel.add(BT_gruposActuales);
		
		JButton BT_crearGrupo = new JButton("Crear Grupo");
		BT_crearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearGrupo();
			}
		});
		BT_crearGrupo.setBounds(160, 11, 140, 52);
		panel.add(BT_crearGrupo);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteMenu();
			}
		});
		button.setBounds(659, 11, 111, 52);
		panel.add(button);
		
		JButton BT_cuentas = new JButton("Cuentas");
		BT_cuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cuentas();
			}
		});
		BT_cuentas.setBounds(310, 11, 140, 52);
		panel.add(BT_cuentas);
		
		JButton BT_contactos = new JButton("Contactos");
		BT_contactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contactos();
			}
		});
		BT_contactos.setBounds(460, 11, 140, 52);
		panel.add(BT_contactos);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./img/fondoOpciones.png"));
		label.setBounds(0, 0, 780, 87);
		panel.add(label);

	}
	
	private void siguienteMenu(){
		
	}
	
	private void gruposActuales(){
		menu.gruposActuales();
	}
	
	private void crearGrupo(){
		menu.crearGrupo();
	}
	private void cuentas()
	{
		menu.cuentas();
	}
	private void contactos()
	{
		menu.contactos();
	}
}
