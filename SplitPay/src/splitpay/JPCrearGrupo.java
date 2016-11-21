package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Conecciones.ConeccionDatos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPCrearGrupo extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TF_nombreG;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

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
				//TODO AGREGAR USUARIO 
			}
		});
		btnAgregar.setBounds(335, 246, 154, 45);
		panel.add(btnAgregar);
		
		JLabel lblSeleccioneLosIntegrantes = new JLabel("Seleccione los integrantes");
		lblSeleccioneLosIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSeleccioneLosIntegrantes.setBounds(10, 105, 214, 27);
		panel.add(lblSeleccioneLosIntegrantes);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 164, 279, 285);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO REMOVER DE USUARIOS A AGREGAR
			}
		});
		btnRemover.setBounds(335, 302, 154, 45);
		panel.add(btnRemover);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(532, 146, 221, 303);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Crear Grupo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO crear grupo
				String nombre = TF_nombreG.getText();
				if(nombre != null){
					int id = principal.userActual.getId();
					ConeccionDatos conn = new ConeccionDatos();
					conn.CrearGrupo(nombre, id);
				}
				else{
					//TODO INFORMA QUE PRIMERO SE DEBE ELEGIR UN NOMBRE 
				}
			}
		});
		btnNewButton.setBounds(536, 44, 205, 50);
		panel.add(btnNewButton);

	} 
	public void limpiar(){
		
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
	public JTextField getTF_nombreG() {
		return TF_nombreG;
	}
}
