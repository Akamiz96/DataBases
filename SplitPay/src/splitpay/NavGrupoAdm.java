package splitpay;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import splitpay.GUIPrincipal;
import splitpay.PMenu;

public class NavGrupoAdm extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	public NavGrupoAdm(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 780, 87);
		
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Opc. Basicas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorMenu();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(422, 11, 128, 52);
		panel.add(btnNewButton);
		
		JButton btnCuentasDueo = new JButton("Cambiar miembros");
		btnCuentasDueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCuentasDueo.setHorizontalAlignment(SwingConstants.LEFT);
		btnCuentasDueo.setBounds(10, 11, 125, 52);
		panel.add(btnCuentasDueo);
		
		JButton btnDeudas = new JButton("Cambiar lider");
		btnDeudas.setBounds(145, 11, 129, 52);
		panel.add(btnDeudas);
		
		JButton btnCrearGrupo = new JButton("limpiar deudas");
		btnCrearGrupo.setBounds(284, 11, 128, 52);
		panel.add(btnCrearGrupo);
		
		JButton btnCrearCuenta = new JButton("salir de grupo");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
	
	private void anteriorMenu()
	{
		menu.navGruposNor();;
	}
	
	private void salirGrupo(){
		menu.salirGrupo();
	}

}
