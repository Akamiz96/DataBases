package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPCrearGrupo extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TF_nombreG;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public JPCrearGrupo(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		panel.setLayout(null);
		add(panel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 67, 62, 27);
		panel.add(lblNombre);
		
		JLabel lblCrearGrupo = new JLabel("Crear grupo");
		lblCrearGrupo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCrearGrupo.setBounds(10, 11, 250, 45);
		panel.add(lblCrearGrupo);
		
		TF_nombreG = new JTextField();
		TF_nombreG.setBounds(66, 67, 148, 27);
		panel.add(TF_nombreG);
		TF_nombreG.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar ");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Agregar usuario
			}
		});
		btnAgregar.setBounds(531, 309, 154, 33);
		panel.add(btnAgregar);
		
		JLabel lblSeleccioneLosIntegrantes = new JLabel("Seleccione los integrantes");
		lblSeleccioneLosIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSeleccioneLosIntegrantes.setBounds(10, 105, 214, 27);
		panel.add(lblSeleccioneLosIntegrantes);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 160, 231, 276);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.setBounds(404, 188, 355, 60);
		panel.add(comboBox);

	} 
	public void limpiar(){
		
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		return table;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
}
