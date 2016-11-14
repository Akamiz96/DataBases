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

public class JOpcionesDos extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	/**
	 * Create the panel.
	 */
	public JOpcionesDos(GUIPrincipal principal, PMenu menu) {
		
		this.principal = principal;
		this.menu = menu;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 780, 87);
		
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("<<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorMenu();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(10, 11, 111, 52);
		panel.add(btnNewButton);
		
		JButton btnCuentasDueo = new JButton("Contactos");
		btnCuentasDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contactos();
			}
		});
		btnCuentasDueo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCuentasDueo.setBounds(131, 11, 111, 52);
		panel.add(btnCuentasDueo);
		
		JButton btnDeudas = new JButton("Espacio 1");
		btnDeudas.setBounds(252, 11, 111, 52);
		panel.add(btnDeudas);
		
		JButton btnCrearGrupo = new JButton("Espacio 2");
		btnCrearGrupo.setBounds(373, 11, 111, 52);
		panel.add(btnCrearGrupo);
		
		JButton btnCrearCuenta = new JButton("Espacio 3");
		btnCrearCuenta.setBounds(494, 11, 111, 52);
		panel.add(btnCrearCuenta);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./img/fondoOpciones.png"));
		label.setBounds(0, 0, 780, 87);
		panel.add(label);

	}
	
	private void anteriorMenu()
	{
		menu.pasarOpcion1();
	}
	
	private void contactos(){
		menu.contactos();
	}
}
