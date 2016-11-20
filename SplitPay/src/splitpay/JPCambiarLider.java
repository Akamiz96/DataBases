package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPCambiarLider extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	public JPCambiarLider(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("cambiar lider");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(227, 174, 296, 44);
		panel.add(lblNewLabel);

	}

}
