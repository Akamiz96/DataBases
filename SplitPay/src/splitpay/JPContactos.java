package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controladores.CuentaJpaController;
import Controladores.UsuarioJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;

public class JPContactos extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JScrollPane scrollPane;
	private JTable tablaC;
	private String[] columnSer = { "Nombres", "Telefono", "Email", "Username", "Conectado" };
	private Vector rowDataSer;
	private Vector columSerV;
	private JTextField TF_username;

	/**
	 * Create the panel.
	 */
	public JPContactos(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;

		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Contactos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 181, 44);
		panel.add(lblNewLabel);

		JLabel lblContactosActuales = new JLabel("Contactos actuales");
		lblContactosActuales.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContactosActuales.setBounds(10, 50, 170, 27);
		panel.add(lblContactosActuales);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 76, 747, 197);
		panel.add(scrollPane);

		tablaC = getTableC();
		scrollPane.setViewportView(tablaC);
		mostrarDatos();

		JLabel lblAgregarContacto = new JLabel("Agregar contacto");
		lblAgregarContacto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgregarContacto.setBounds(20, 302, 181, 35);
		panel.add(lblAgregarContacto);

		JLabel lblIngreseUsername = new JLabel("Ingrese username");
		lblIngreseUsername.setBounds(20, 356, 117, 27);
		panel.add(lblIngreseUsername);

		TF_username = new JTextField();
		TF_username.setBounds(138, 357, 140, 24);
		panel.add(TF_username);
		TF_username.setColumns(10);

		JButton btnAgregar = new JButton("agregar");
		btnAgregar.setBounds(161, 402, 117, 35);
		panel.add(btnAgregar);

	}

	public JTable getTableC() {
		System.out.println("ENTRO GET SERV");
		if (tablaC == null) {
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			tablaC = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(tablaC);

		}

		return tablaC;
	}

	public JTable mostrarDatos() {

		// datosDefectos(empresa);
		System.out.println("SERVICIOS");

		 EntityManagerFactory emf =Persistence.createEntityManagerFactory("SplitPayPU");
		 UsuarioJpaController contro = new UsuarioJpaController(emf);

		  List<String> contactos = contro.ContactosUsuario(principal.userActual.getId());

		this.rowDataSer = new Vector(); // datos de toda la tabla

		for(String datos:contactos){
			StringTokenizer st = new StringTokenizer(datos, "$");
			Vector fila = new Vector();
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());


			this.rowDataSer.add(fila);
			System.out.println(fila.toString());

		}
		/*for (int i = 0; i < 15; i++) {
			Vector fila = new Vector();
			fila.add("nombre "+1 + i);
			fila.add("telefono" + i);
			fila.add("email" + (i + 100));
			fila.add("username" + (i + 50));
			fila.add("contacto" + (i + 27));
			this.rowDataSer.add(fila);
			System.out.println(fila.toString());
		}*/
		/*
		 * for(String grupo: grupos) {
		 *
		 * StringTokenizer st = new StringTokenizer(grupo, "$");
		 * fila.add(st.nextToken().trim()); fila.add(st.nextToken().trim());
		 *
		 * this.rowDataSer.add(fila); System.out.println(fila.toString()); }
		 */
		// refrescar el JTable dentro del JScrollPane:
		tablaC = new JTable(this.rowDataSer, this.columSerV);
		System.out.println("#ser");
		scrollPane.setViewportView(tablaC);// refresca el JTable

		return tablaC;
	}

}
