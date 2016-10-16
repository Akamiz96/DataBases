package presentacion;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class CrearUsuario extends JPanel {
	private JPanel panel;
	private JLabel lblNull;
	private JTextField TF_nombres;
	private JTextField TF_apellidos;
	private JTextField TF_genero;
	private JTextField TF_nombreUsuario;
	private JTextField TF_email;
	private JTextField TF_fechaNac;
	private JTextField textField;
	private JTextField textField_1;
	private GUIPrincipal principal;
	private Timer t;
	private ActionListener al;
	private JPasswordField TF_contrasena;
	private JPasswordField TF_confirmarCon;
	
	/**
	 * Create the panel.
	 */
	public CrearUsuario(GUIPrincipal principal) {
		this.principal = principal;
		
		al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(TF_contrasena.getText().length() != 0)
				{
					System.out.println(TF_contrasena.getText());
					System.out.println("NO ES NULL");
					if(TF_contrasena.getText().equals(TF_confirmarCon.getText()))
							{
						System.out.println("ENTRO");
						lblNull.setText("SII");
						lblNull.setIcon(new ImageIcon("C:\\Users\\andre\\Documents\\java\\splitpay\\src\\presentacion\\img\\ok.png"));
						lblNull.revalidate();
						lblNull.repaint();
						
							}
					else
					{
						lblNull.setText("Noo");
						lblNull.setIcon(new ImageIcon("C:\\Users\\andre\\Documents\\java\\splitpay\\src\\presentacion\\img\\fall.png"));
						lblNull.revalidate();
						lblNull.repaint();
						
					
					}
				}
				else
				{
					System.out.println(" NULL");
				}
			}
		};
		t = new Timer(2000,al);
		t.start();
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 780, 610);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCrearCuenta = new JLabel("Crear Cuenta");
		lblCrearCuenta.setBackground(UIManager.getColor("Button.highlight"));
		lblCrearCuenta.setForeground(Color.WHITE);
		lblCrearCuenta.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCrearCuenta.setBounds(320, 11, 215, 52);
		panel.add(lblCrearCuenta);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(95, 133, 85, 32);
		panel.add(lblNewLabel_1);
		
		JLabel titulo = new JLabel("");
		titulo.setIcon(new ImageIcon("C:\\Users\\andre\\Documents\\java\\splitpay\\src\\presentacion\\img\\titulomenu2.png"));
		titulo.setForeground(new Color(0, 204, 102));
		titulo.setBackground(new Color(51, 102, 0));
		titulo.setBounds(0, 11, 780, 63);
		panel.add(titulo);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidos.setBounds(450, 133, 85, 32);
		panel.add(lblApellidos);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenero.setBounds(95, 429, 85, 32);
		panel.add(lblGenero);
		
		TF_nombres = new JTextField();
		TF_nombres.setBounds(190, 133, 158, 32);
		panel.add(TF_nombres);
		TF_nombres.setColumns(10);
		
		TF_apellidos = new JTextField();
		TF_apellidos.setColumns(10);
		TF_apellidos.setBounds(545, 135, 158, 32);
		panel.add(TF_apellidos);
		
		TF_genero = new JTextField();
		TF_genero.setColumns(10);
		TF_genero.setBounds(190, 429, 158, 32);
		panel.add(TF_genero);
		
		JLabel lblNombreUsuario = new JLabel("Nombre usuario");
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreUsuario.setBounds(50, 188, 130, 32);
		panel.add(lblNombreUsuario);
		
		TF_nombreUsuario = new JTextField();
		TF_nombreUsuario.setColumns(10);
		TF_nombreUsuario.setBounds(190, 188, 158, 32);
		panel.add(TF_nombreUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(95, 242, 85, 32);
		panel.add(lblContrasea);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmarContrasea.setBounds(363, 242, 172, 32);
		panel.add(lblConfirmarContrasea);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(450, 298, 85, 32);
		panel.add(lblEmail);
		
		TF_email = new JTextField();
		TF_email.setColumns(10);
		TF_email.setBounds(545, 300, 158, 32);
		panel.add(TF_email);
		
		JLabel lblFechaNaciemiento = new JLabel("Fecha naciemiento");
		lblFechaNaciemiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNaciemiento.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaNaciemiento.setBounds(22, 298, 158, 32);
		panel.add(lblFechaNaciemiento);
		
		TF_fechaNac = new JTextField();
		TF_fechaNac.setColumns(10);
		TF_fechaNac.setBounds(190, 304, 158, 32);
		panel.add(TF_fechaNac);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(95, 353, 85, 32);
		panel.add(lblTelefono);
		
		JLabel lblNombreUsuarioPaypal = new JLabel("Nombre \r\nusuario Paypal");
		lblNombreUsuarioPaypal.setToolTipText("");
		lblNombreUsuarioPaypal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreUsuarioPaypal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreUsuarioPaypal.setBounds(358, 348, 177, 42);
		panel.add(lblNombreUsuarioPaypal);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(190, 359, 158, 32);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(545, 355, 158, 32);
		panel.add(textField_1);
		
		JButton btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverInicio();
			}
		});
		btnVolver.setBounds(190, 534, 136, 42);
		panel.add(btnVolver);
		
		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverInicio();
			}
		});
		btnCrearCuenta.setBounds(545, 534, 136, 42);
		panel.add(btnCrearCuenta);
		
		lblNull = new JLabel("null");
		lblNull.setFont(new Font("Trajan Pro 3", Font.BOLD, 14));
		lblNull.setBounds(713, 239, 48, 43);
		panel.add(lblNull);
		
		TF_contrasena = new JPasswordField();
		TF_contrasena.setBounds(190, 244, 163, 32);
		panel.add(TF_contrasena);
		
		TF_confirmarCon = new JPasswordField();
		TF_confirmarCon.setBounds(545, 244, 163, 32);
		panel.add(TF_confirmarCon);
		
		
		
		

	}
	
	public void volverInicio(){
		principal.pasarInicio();
	}
}
