package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPTransacciones extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public JPTransacciones(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transacciones");
		lblNewLabel.setBounds(10, 11, 296, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		JButton btnRealizarTransaccion = new JButton("Realizar transaccion");
		btnRealizarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO MEMBER TO MEMBER TRANSACCION
			}
		});
		btnRealizarTransaccion.setBounds(593, 389, 176, 44);
		panel.add(btnRealizarTransaccion);
		
		comboBox = new JComboBox();
		comboBox.setBounds(552, 279, 217, 44);
		panel.add(comboBox);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(475, 294, 46, 14);
		panel.add(lblTipo);
		
		textField = new JTextField();
		textField.setBounds(552, 208, 217, 44);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(475, 223, 46, 14);
		panel.add(lblCantidad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 116, 358, 317);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCuentas = new JLabel("Cuentas");
		lblCuentas.setBounds(21, 91, 46, 14);
		panel.add(lblCuentas);

	} 
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		return table;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
