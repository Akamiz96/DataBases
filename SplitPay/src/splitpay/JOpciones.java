package presentacion;

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

public class JOpciones extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu ;
	/**
	 * Create the panel.
	 */
	public JOpciones(GUIPrincipal principal, PMenu menu ) {
		
		this.principal = principal;
		this.menu = menu;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 780, 87);
		
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Grupos Actuales");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gruposActuales();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(10, 11, 111, 52);
		panel.add(btnNewButton);
		
		JButton btnCuentasDueo = new JButton("Cuentas Due\u00F1o");
		btnCuentasDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentasDueno();
			}
		});
		btnCuentasDueo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCuentasDueo.setBounds(131, 11, 111, 52);
		panel.add(btnCuentasDueo);
		
		JButton btnDeudas = new JButton("Deudas");
		btnDeudas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deudas();
			}
		});
		btnDeudas.setBounds(252, 11, 111, 52);
		panel.add(btnDeudas);
		
		JButton btnCrearGrupo = new JButton("Crear Grupo");
		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearGrupo();
			}
		});
		btnCrearGrupo.setBounds(373, 11, 111, 52);
		panel.add(btnCrearGrupo);
		
		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		btnCrearCuenta.setBounds(494, 11, 111, 52);
		panel.add(btnCrearCuenta);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteMenu();
			}
		});
		button.setBounds(659, 11, 111, 52);
		panel.add(button);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./img/fondoOpciones.png"));
		label.setBounds(0, 0, 780, 87);
		panel.add(label);

	}
	
	private void siguienteMenu(){
		menu.pasarOpcion2();
	}
	
	private void gruposActuales(){
		menu.gruposActuales();
	}
	
	private void cuentasDueno(){
		menu.cuentasDueno();
	}
	
	private void deudas(){
		menu.deudas();
	}
	
	private void crearGrupo(){
		menu.crearGrupo();
	}
	
	private void crearCuenta(){
		menu.crearCuenta();
	}
}
