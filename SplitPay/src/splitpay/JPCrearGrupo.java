package splitpay;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Conecciones.ConeccionDatos;
import Controladores.GrupoJpaController;
import Controladores.PerteneceAJpaController;
import Controladores.UsuarioJpaController;
import Negocio.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class JPCrearGrupo extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TF_nombreG;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private String[] columnSerC = { "User Name" };
	// contactos
	private Vector rowDataSerC;
	private Vector columSerVC;
	// integrantes
	private Vector rowDataSerG;
	private Vector columSerVG;
	private JTable table_2;

	private List<String> usernamesN;
	private List<String> usernamesNC;

	private List<Integer> usernameI;
	private List<Integer> usernameIC;

	/**
	 * Create the panel.
	 */
	public JPCrearGrupo(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		usernameIC = new ArrayList<Integer>();
		usernameI = new ArrayList<Integer>();
		usernamesNC = new ArrayList<String>();
		usernamesN = new ArrayList<String>();
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 790, 460);
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
				// TODO AGREGAR USUARIO
				AgregarGrupo();
			}
		});
		btnAgregar.setBounds(335, 246, 154, 45);
		panel.add(btnAgregar);

		JLabel lblSeleccioneLosIntegrantes = new JLabel(
				"Seleccione los integrantes");
		lblSeleccioneLosIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSeleccioneLosIntegrantes.setBounds(10, 105, 214, 27);
		panel.add(lblSeleccioneLosIntegrantes);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 164, 279, 285);
		panel.add(scrollPane);

		table_2 = getTableIntegrantes();
		scrollPane.setViewportView(table_2);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO REMOVER DE USUARIOS A AGREGAR
				QuitarGrupo();
			}
		});
		btnRemover.setBounds(335, 302, 154, 45);
		panel.add(btnRemover);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(532, 170, 221, 279);
		panel.add(scrollPane_1);

		table_1 = getTableContactos();
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton = new JButton("Crear Grupo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO crear grupo
				String nombre = TF_nombreG.getText();
				if (nombre != null) {
					int id = principal.userActual.getId();
					ConeccionDatos conn = new ConeccionDatos();
					conn.CrearGrupo(nombre, id);
					agregarMiembros();
				} else {
					JOptionPane.showMessageDialog(null,
							"No ha eligido un nombre valido");
				}
			}
		});
		btnNewButton.setBounds(536, 44, 205, 50);
		panel.add(btnNewButton);
		
		JLabel lblMiembrosDelGrupo = new JLabel("Miembros del grupo");
		lblMiembrosDelGrupo.setBounds(26, 143, 132, 14);
		panel.add(lblMiembrosDelGrupo);
		
		JLabel lblContactos = new JLabel("Contactos");
		lblContactos.setBounds(536, 145, 69, 14);
		panel.add(lblContactos);
		llenarListas();
		mostrarDatosGrupo();
		mostrarDatosContactos();

	}

	private JTable getTableContactos() {
		// TODO Auto-generated method stub
		if (table_1 == null) {
			columSerVC = new Vector(Arrays.asList(this.columnSerC));
			rowDataSerC = new Vector();
			table_1 = new JTable(this.rowDataSerC, columSerVC);
			scrollPane_1.setViewportView(table_1);

		}

		return table_1;
	}

	private JTable getTableIntegrantes() {
		// TODO Auto-generated method stub
		if (table_2 == null) {
			columSerVG = new Vector(Arrays.asList(this.columnSerC));
			rowDataSerG = new Vector();
			table_2 = new JTable(this.rowDataSerG, columSerVG);
			scrollPane.setViewportView(table_2);

		}

		return table_2;
	}

	private void AgregarGrupo() {
		int filaS = table_1.getSelectedRow();
		if (filaS != -1) {
			String nombre = usernamesN.get(filaS);
			int identificacion = usernameI.get(filaS);
			usernamesN.remove(filaS);
			usernameI.remove(filaS);
			usernamesNC.add(nombre);
			usernameIC.add(identificacion);
			mostrarDatosContactos();
			mostrarDatosGrupo();

		}else {
			JOptionPane.showMessageDialog(null,
					"No ha seleccionado ningun contacto");
		}
	}

	private void QuitarGrupo() {
		// TODO Auto-generated method stub
		int filaS = table_2.getSelectedRow();
		if (filaS != -1) {
			String nombre = usernamesNC.get(filaS);
			int identificacion = usernameIC.get(filaS);
			usernamesNC.remove(filaS);
			usernameIC.remove(filaS);
			usernamesN.add(nombre);
			usernameI.add(identificacion);
			mostrarDatosContactos();
			mostrarDatosGrupo();

		}else {
			JOptionPane.showMessageDialog(null,
					"No ha seleccionado ningun contacto");
		}
	}
	private void mostrarDatosGrupo() {
		this.rowDataSerG = new Vector(); // datos de toda la tabla

		for (int i = 0; i < usernamesNC.size(); i++) {
			Vector fila = new Vector();
			fila.add(usernamesNC.get(i));
			this.rowDataSerG.add(fila);
		}

		// refrescar el JTable dentro del JScrollPane:
		table_2 = new JTable(this.rowDataSerG, this.columSerVG);
		scrollPane.setViewportView(table_2);// refresca el JTable

	}

	private void mostrarDatosContactos() {
		this.rowDataSerC = new Vector(); // datos de toda la tabla

		for (int i = 0; i < usernamesN.size(); i++) {
			Vector fila = new Vector();
			fila.add(usernamesN.get(i));
			this.rowDataSerC.add(fila);
		}

		// refrescar el JTable dentro del JScrollPane:
		table_1 = new JTable(this.rowDataSerC, this.columSerVC);
		scrollPane_1.setViewportView(table_1);// refresca el JTable
	}

	private void llenarListas() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SplitPayPU");
		UsuarioJpaController contro = new UsuarioJpaController(emf);
		List<Usuario> usuarios = contro
				.contactos(principal.userActual.getId());
		for (Usuario dato : usuarios) {
			if (!principal.userActual.getUserName().equals(dato.getUserName())) {
				usernamesN.add(dato.getUserName());
				usernameI.add(dato.getId());
			}

		}

	}
	
	private void agregarMiembros() {
		// TODO Auto-generated method stub NC IC
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		PerteneceAJpaController contro = new PerteneceAJpaController(emf);
		for(int identificacion : usernameIC){
			contro.agregarUsuarioAGrupo(identificacion, principal.grupoActual);
		}
	}
	
	public void limpiar() {

	}

	public JScrollPane getScrollPane() {
		return scrollPane;
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

	public JTable getTable_2() {
		return table_2;
	}
}
