package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPCambiarLider extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JComboBox comboBox;
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
		lblNewLabel.setBounds(10, 11, 296, 44);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(60, 102, 432, 75);
		panel.add(comboBox);
		
		JButton btnConfirmarCambio = new JButton("Confirmar cambio");
		btnConfirmarCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Cambiar lider 
			}
		});
		btnConfirmarCambio.setBounds(623, 112, 115, 55);
		panel.add(btnConfirmarCambio);

	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
