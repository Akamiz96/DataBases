package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPLimpiarDeudas extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable table;
	private JScrollPane scrollPane;
	public JPLimpiarDeudas(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("limpiar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(0, 0, 296, 44);
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 55, 722, 171);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
		
		JButton btnLimpiarDeudasDe = new JButton("Limpiar Deudas de cuenta");
		btnLimpiarDeudasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Limpiar deudas de la cuenta
			}
		});
		btnLimpiarDeudasDe.setBounds(34, 251, 192, 68);
		panel.add(btnLimpiarDeudasDe);
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		return table;
	}
}
