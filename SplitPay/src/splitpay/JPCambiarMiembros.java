package splitpay;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Vector;

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
	private String[] columnSer = { "Nombre de Usuario" };
	private Vector rowDataSer;
	private Vector columSerV;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table_2;
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 130, 235, 384);
		panel.add(scrollPane);
		
		table = getTable();
		scrollPane.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(515, 130, 235, 384);
		panel.add(scrollPane_1);
		
		table_2 = getTable_2();
		scrollPane_1.setViewportView(table_2);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO ADICIONAR UN MIEMBRO
			}
		});
		btnAdicionar.setBounds(378, 261, 89, 23);
		panel.add(btnAdicionar);
		
		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO RETIRAR UN MIEMBRO 
			}
		});
		btnRetirar.setBounds(378, 349, 89, 23);
		panel.add(btnRetirar);
		
		JLabel lblMiembros = new JLabel("Miembros");
		lblMiembros.setBounds(123, 79, 192, 28);
		panel.add(lblMiembros);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setBounds(532, 77, 192, 33);
		panel.add(lblContactos);
	}
	private JTable getTable() {
		if (table == null) {
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			table = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(table);

		}
		return table;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}
	public JTable getTable_2() {
		if (table_2 == null) {
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			table_2 = new JTable(this.rowDataSer, columSerV);
			scrollPane_1.setViewportView(table_2);

		}
		return table_2;
	}
}
