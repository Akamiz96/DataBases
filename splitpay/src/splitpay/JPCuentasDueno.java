package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class JPCuentasDueno extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;

	/**
	 * Create the panel.
	 */
	public JPCuentasDueno(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cuentas Dueno");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(253, 170, 274, 44);
		panel.add(lblNewLabel);

	} 

}
