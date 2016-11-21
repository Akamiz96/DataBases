package splitpay;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;














import Conecciones.ConeccionDatos;
import Controladores.GrupoJpaController;
import Controladores.UsuarioJpaController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JPCrearCuenta extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TFNombre;
	private JTextField TFCosto;
	private JTextField TFComentarios;
	private JFileChooser fc;
	private JButton BT_Recibo;
	private String ruta;
	private List<String> usernamesN;
	private List<Integer> usernameI;
	
	// para cuenta
	private String[] columnSerC = { "User Name" };
	private Vector rowDataSerC;
	private Vector columSerVC;
	
	// para grupo
		private Vector rowDataSerG;
		
		
	private JTable tablaCuenta;
	private JTable tablaMiembros;
	private JScrollPane panelCuenta;
	private JScrollPane panelGrupo;

	/**
	 * Create the panel.
	 */
	public JPCrearCuenta(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crear Cuenta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(237, 0, 238, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombre.setBounds(15, 71, 115, 20);
		panel.add(lblNombre);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCosto.setBounds(61, 107, 69, 20);
		panel.add(lblCosto);
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComentarios.setBounds(15, 146, 126, 20);
		panel.add(lblComentarios);
		
		JLabel lblReci = new JLabel("recibo");
		lblReci.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReci.setBounds(462, 55, 69, 20);
		panel.add(lblReci);
		
		TFNombre = new JTextField();
		TFNombre.setBounds(156, 60, 146, 26);
		panel.add(TFNombre);
		TFNombre.setColumns(10);
		
		TFCosto = new JTextField();
		TFCosto.setColumns(10);
		TFCosto.setBounds(156, 108, 146, 26);
		panel.add(TFCosto);
		
		TFComentarios = new JTextField();
		TFComentarios.setColumns(10);
		TFComentarios.setBounds(156, 144, 146, 26);
		panel.add(TFComentarios);
		
		BT_Recibo = new JButton("B");
		BT_Recibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				explorador();
			}
		});
		BT_Recibo.setBounds(593, 52, 115, 29);
		panel.add(BT_Recibo);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\bibliotecapuj\\Downloads\\WhatsApp Image 2016-11-20 at 1.59.38 PM.jpeg"));
		lblNewLabel_1.setBounds(462, 94, 313, 170);
		panel.add(lblNewLabel_1);
		
		JButton btnTerminar = new JButton("terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertar();
				} catch (NumberFormatException | FileNotFoundException
						| SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTerminar.setBounds(660, 415, 115, 29);
		panel.add(btnTerminar);
		
		JLabel lblMiembrosDeCuenta = new JLabel("miembros de cuenta");
		lblMiembrosDeCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMiembrosDeCuenta.setBounds(15, 196, 115, 20);
		panel.add(lblMiembrosDeCuenta);
		
		JLabel label = new JLabel("miembros de cuenta");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(309, 195, 115, 20);
		panel.add(label);
		
		panelCuenta = new JScrollPane();
		panelCuenta.setBounds(15, 232, 115, 196);
		panel.add(panelCuenta);
		
		tablaCuenta = getTableC();
		panelCuenta.setViewportView(tablaCuenta);
		
		panelGrupo = new JScrollPane();
		panelGrupo.setBounds(309, 231, 115, 196);
		panel.add(panelGrupo);
		
		tablaMiembros = getTableG();
		panelGrupo.setViewportView(tablaMiembros);
		
		JButton button = new JButton("<< Agregar");
		button.setBounds(145, 245, 131, 29);
		panel.add(button);
		
		JButton btnQuitar = new JButton(" Quitar>>");
		btnQuitar.setBounds(145, 311, 131, 29);
		panel.add(btnQuitar);
		
		mostrarDatosC();
		mostrarDatosG();

	} 
	private void explorador(){
		fc = new JFileChooser();
		fc.setDialogTitle("Ingresar archivos IPS");
		fc.setCurrentDirectory(new File("./"));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		File inFile = fc.getSelectedFile();
		if (fc.showOpenDialog(BT_Recibo) == fc.APPROVE_OPTION) {
			inFile = fc.getSelectedFile();

			try {
				 ruta = inFile.getPath();
				System.out.println(ruta);
				
					/*ManejoArchivos.ingresarIPS(empresa, nombreArchivo);
					JOptionPane.showMessageDialog(null, "Exito cargando archivo")*/;

			}catch (Exception e1) {

				JOptionPane.showMessageDialog(null, "excepcion inesperada:" + e1.getMessage());
			}
		}
	}
	private void insertar() throws NumberFormatException, FileNotFoundException, SQLException{
		ConeccionDatos coneccion = new ConeccionDatos();
		coneccion.agregarRecibo(ruta, TFNombre.getText(), TFComentarios.getText(),Integer.parseInt(TFCosto.getText()), principal.userActual.getId(), principal.grupoActual);
		
	}
	
	public JTable getTableC() {
		System.out.println("ENTRO GET SERV");
		if (tablaCuenta == null) {
			columSerVC = new Vector(Arrays.asList(this.columnSerC));
			rowDataSerC = new Vector();
			tablaCuenta = new JTable(this.rowDataSerC, columSerVC);
			panelCuenta.setViewportView(tablaCuenta);

		}

		return tablaCuenta;
	}
	
	public JTable getTableG() {
		System.out.println("ENTRO GET SERV");
		if (tablaMiembros == null) {
			columSerVC = new Vector(Arrays.asList(this.columnSerC));
			rowDataSerC = new Vector();
			tablaMiembros = new JTable(this.rowDataSerC, columSerVC);
			panelGrupo.setViewportView(tablaMiembros);

		}

		return tablaMiembros;
	}
	
	public JTable mostrarDatosC() {

		
		System.out.println("SERVICIOS");
		
		this.rowDataSerC = new Vector(); // datos de toda la tabla

		/*for (int i = 0; i < 15; i++) {
			Vector fila = new Vector();
			fila.add("C"+1 + i);
			fila.add("C" + i);
			fila.add("C" + (i + 100));
			fila.add("C" + (i + 50));
			fila.add("C" + (i + 27));
			this.rowDataSerC.add(fila);
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
		tablaCuenta = new JTable(this.rowDataSerC, this.columSerVC);
		System.out.println("#ser");
		panelCuenta.setViewportView(tablaCuenta);// refresca el JTable

		return tablaCuenta;
	}
	
public JTable mostrarDatosG() {

		
		System.out.println("SERVICIOS");
		
		this.rowDataSerG = new Vector(); // datos de toda la tabla

		for (int i = 0; i < 15; i++) {
			Vector fila = new Vector();
			fila.add("G"+1 + i);
			fila.add("G" + i);
			fila.add("G" + (i + 100));
			fila.add("G" + (i + 50));
			fila.add("G" + (i + 27));
			this.rowDataSerG.add(fila);
			System.out.println(fila.toString());
		}
		/*
		 * for(String grupo: grupos) {
		 * 
		 * StringTokenizer st = new StringTokenizer(grupo, "$");
		 * fila.add(st.nextToken().trim()); fila.add(st.nextToken().trim());
		 * 
		 * this.rowDataSer.add(fila); System.out.println(fila.toString()); }
		 */
		// refrescar el JTable dentro del JScrollPane:
		tablaMiembros = new JTable(this.rowDataSerG, this.columSerVC);
		System.out.println("#ser");
		panelGrupo.setViewportView(tablaMiembros);// refresca el JTable

		return tablaMiembros;
	}
	private void llenarListas(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		GrupoJpaController contro = new GrupoJpaController(emf);
		List<String> usuarios=contro.UsuariosdeGrupoConUsername(principal.grupoActual);
		for(String dato:usuarios){
		StringTokenizer st = new StringTokenizer(dato, "$");
		/*st.nextToken().trim()
		st.nextToken().trim()
		fila.add(st.nextToken().trim());
		*/
		}
		
		
	}
	
	private void agregarCuenta(){
		
	}
	
	private void agregarGrupo(){
		
	}
}
