package splitpay;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

import Controladores.CuentaJpaController;

public class JPCuentas extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable tablaC;
	private JScrollPane scrollPane;
	private String[] columnSer = { "Nombre", "Grupo", "Balance" };
	private Vector rowDataSer;
	private Vector columSerV;

	/**
	 * Create the panel.
	 */
	public JPCuentas(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;

		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 790, 460);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cuentas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 274, 44);
		panel.add(lblNewLabel);

		JLabel lblCuentasALas = new JLabel(
				"Cuentas a las que pertenece actualmente");
		lblCuentasALas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCuentasALas.setBounds(141, 81, 345, 35);
		panel.add(lblCuentasALas);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 128, 490, 254);
		panel.add(scrollPane);

		tablaC = getTableC();
		scrollPane.setViewportView(tablaC);

		tablaC = getTableC();
		scrollPane.setViewportView(tablaC);

		mostrarDatos();

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
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SplitPayPU");
		CuentaJpaController contro = new CuentaJpaController(emf);

		List<String> grupos = contro.TablaUsuarioCuentaGrupo(1);

		this.rowDataSer = new Vector(); // datos de toda la tabla

		/*
		 * for (int i = 0; i < 15; i++) { Vector fila = new Vector(); fila.add(1
		 * + i); fila.add("grupo " + i); fila.add("balance" + (i + 100));
		 * this.rowDataSer.add(fila); System.out.println(fila.toString()); }
		 */
		String uno,dos,tres;
		for (String grupo : grupos) {
			Vector fila = new Vector();
			StringTokenizer st = new StringTokenizer(grupo, "$");
			uno = st.nextToken().trim();
			tres = st.nextToken().trim();
			dos = st.nextToken().trim();
			fila.add(uno);
			fila.add(dos);
			fila.add(tres);

			this.rowDataSer.add(fila);
			System.out.println(fila.toString());
		}

		// refrescar el JTable dentro del JScrollPane:
		tablaC = new JTable(this.rowDataSer, this.columSerV);
		System.out.println("#ser");
		scrollPane.setViewportView(tablaC);// refresca el JTable

		return tablaC;
	}
}
