package splitpay;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Conecciones.ConeccionDatos;
import Controladores.CuentaJpaController;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPCuentas extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable tablaC;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel imagen;
	private String[] columnSer = { "Nombre", "Grupo", "Balance" };
	private Vector rowDataSer;
	private Vector columSerV;
	private List<Integer> idCuenta;

	/**
	 * Create the panel.
	 */
	public JPCuentas(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		idCuenta = new ArrayList<Integer>();
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
		lblCuentasALas.setBounds(10, 85, 345, 35);
		panel.add(lblCuentasALas);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 371, 254);
		panel.add(scrollPane);

		tablaC = getTableC();
		scrollPane.setViewportView(tablaC);

		tablaC = getTableC();
		scrollPane.setViewportView(tablaC);

		mostrarDatos();
		
		JButton btnVerFactura = new JButton("Ver factura");
		btnVerFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVerFactura.setBounds(217, 398, 157, 46);
		panel.add(btnVerFactura);
		
		JLabel lblFacturaDeLa = new JLabel("Factura de la cuenta seleccionada");
		lblFacturaDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFacturaDeLa.setBounds(475, 85, 274, 35);
		panel.add(lblFacturaDeLa);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(430, 128, 345, 248);
		panel.add(scrollPane_1);
		
		 imagen = new JLabel("");
		scrollPane_1.setViewportView(imagen);

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
		idCuenta.clear();
		String nombrecuenta,balanceC,idC,nombreG;
		for (String grupo : grupos) {
			Vector fila = new Vector();
			StringTokenizer st = new StringTokenizer(grupo, "$");
			nombrecuenta= st.nextToken().trim();
			balanceC = st.nextToken().trim();
			idC = st.nextToken().trim();
			nombreG = st.nextToken().trim();
			
			fila.add(nombrecuenta);
			fila.add(nombreG);
			fila.add(balanceC);
			idCuenta.add(Integer.parseInt(idC));

			this.rowDataSer.add(fila);
			System.out.println(fila.toString());
		}

		// refrescar el JTable dentro del JScrollPane:
		tablaC = new JTable(this.rowDataSer, this.columSerV);
		System.out.println("#ser");
		scrollPane.setViewportView(tablaC);// refresca el JTable

		return tablaC;
	}
	
	private void seleccionar() throws SQLException{
		int filaS = tablaC.getSelectedRow();
		if(filaS != -1){
				int id = idCuenta.get(filaS);
				ConeccionDatos d = new ConeccionDatos();
				ImageIcon img = d.imagen1(id);
				if(img != null)
					imagen.setIcon(img);
				else{
					imagen.setIcon(null);
				}
				scrollPane_1.setViewportView(imagen);// refresca el JTable
			}
			else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningun servicio");
			}
	}
}
