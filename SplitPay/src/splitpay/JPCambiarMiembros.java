package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class JPCambiarMiembros extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable table;
	private JTable table_1;
	public JPCambiarMiembros(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11,834, 525);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("cambiar Miembros");
		lblNewLabel.setBounds(0, 0, 353, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 130, 235, 384);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(515, 130, 235, 384);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(378, 228, 89, 23);
		panel.add(btnAdicionar);
		
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.setBounds(378, 297, 89, 23);
		panel.add(btnRetirar);
		
		JLabel lblMiembros = new JLabel("Miembros");
		lblMiembros.setBounds(123, 79, 192, 28);
		panel.add(lblMiembros);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setBounds(532, 77, 192, 33);
		panel.add(lblContactos);
	}
}
