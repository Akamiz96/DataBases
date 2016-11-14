package presentacion;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class JGruposActuales extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;

	/**
	 * Create the panel.
	 */
	public JGruposActuales(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblGruposActuales = new JLabel("Grupos Actuales");
		lblGruposActuales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGruposActuales.setBounds(55, 26, 354, 32);
		panel.add(lblGruposActuales);

	} 
}
