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

public class JPCambiarMiembros extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public JPCambiarMiembros(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("cambiar Miembros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 359, 44);
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 105, 254, 344);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(480, 105, 271, 344);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(339, 205, 115, 44);
		panel.add(btnNewButton);
		
		JLabel lblMiembrosDeGrupo = new JLabel("Miembros de grupo");
		lblMiembrosDeGrupo.setBounds(55, 80, 115, 14);
		panel.add(lblMiembrosDeGrupo);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setBounds(480, 80, 82, 14);
		panel.add(lblContactos);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.setBounds(339, 304, 115, 44);
		panel.add(btnAgregar);
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		return table;
	}
	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}
	public JTable getTable_1() {
		return table_1;
	}
}
