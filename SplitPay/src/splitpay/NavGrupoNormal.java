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

public class NavGrupoNormal extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	/**
	 * Create the panel.
	 */
	public NavGrupoNormal(GUIPrincipal principal, PMenu menu) {
		
		this.principal = principal;
		this.menu = menu;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 780, 87);
		
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Opc. Administrador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcionAdm();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(422, 11, 128, 52);
		panel.add(btnNewButton);
		
		JButton btnCuentasDueo = new JButton("Crear cuenta");
		btnCuentasDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		btnCuentasDueo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCuentasDueo.setBounds(10, 11, 125, 52);
		panel.add(btnCuentasDueo);
		
		JButton btnDeudas = new JButton("Transacciones");
		btnDeudas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				transacciones();
			}
		});
		btnDeudas.setBounds(145, 11, 129, 52);
		panel.add(btnDeudas);
		
		JButton btnCrearGrupo = new JButton("Miembros");
		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miembros();
			}
		});
		btnCrearGrupo.setBounds(284, 11, 128, 52);
		panel.add(btnCrearGrupo);
		
		JButton btnCrearCuenta = new JButton("salir de grupo");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salirGrupo();
			}
		});
		btnCrearCuenta.setBackground(new Color(250, 128, 114));
		btnCrearCuenta.setBounds(642, 11, 128, 52);
		panel.add(btnCrearCuenta);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./img/fondoOpciones.png"));
		label.setBounds(0, 0, 780, 87);
		panel.add(label);

	}
	
	private void opcionAdm()
	{
		menu.navGruposAdm();
	}
	
	private void salirGrupo(){
		menu.salirGrupo();
	}
	private void crearCuenta(){
		menu.crearCuenta();
	}
	
	private void transacciones(){
		
		menu.trasacciones();
	}
	
	private void miembros(){
		menu.miembros();
	}
}
