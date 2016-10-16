package presentacion;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class JCrearGrupo extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;

	/**
	 * Create the panel.
	 */
	public JCrearGrupo(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		panel.setLayout(null);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Crear Grupo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(195, 134, 500, 102);
		panel.add(lblNewLabel);

	} 

}
