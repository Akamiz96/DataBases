package splitpay;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;






import Conecciones.ConeccionDatos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class JPCrearCuenta extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TFNombre;
	private JTextField TFCosto;
	private JTextField TFComentarios;
	private JFileChooser fc;
	private JButton BT_Recibo;
	private String ruta;

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
		lblCosto.setBounds(25, 110, 69, 20);
		panel.add(lblCosto);
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComentarios.setBounds(15, 146, 96, 20);
		panel.add(lblComentarios);
		
		JLabel lblReci = new JLabel("recibo");
		lblReci.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReci.setBounds(462, 55, 69, 20);
		panel.add(lblReci);
		
		TFNombre = new JTextField();
		TFNombre.setBounds(130, 69, 146, 26);
		panel.add(TFNombre);
		TFNombre.setColumns(10);
		
		TFCosto = new JTextField();
		TFCosto.setColumns(10);
		TFCosto.setBounds(130, 108, 146, 26);
		panel.add(TFCosto);
		
		TFComentarios = new JTextField();
		TFComentarios.setColumns(10);
		TFComentarios.setBounds(130, 146, 146, 26);
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
}
