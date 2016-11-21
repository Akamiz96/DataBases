package splitpay;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import Conecciones.ConeccionDatos;
import Controladores.UsuarioJpaController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JPCrearUsuario extends JPanel {
	private JPanel panel;
	private JLabel lblNull;
	private JTextField TF_nombres;
	private JTextField TF_apellidos;
	private JTextField TF_nombreUsuario;
	private JTextField TF_email;
	private JTextField TF_telefono;
	private JTextField TF_paypal;
	private GUIPrincipal principal;
	private Timer t;
	private ActionListener al;
	private JPasswordField TF_contrasena;
	private JPasswordField TF_confirmarCon;
	private JTextField TF_dia;
	private JTextField TF_mes;
	private JTextField TF_anio;
	private JLabel LB_usernme;
	private JComboBox comboBox;
	private List<String> usernames;

	/**
	 * Create the panel.
	 */
	public JPCrearUsuario(GUIPrincipal principal) {
		this.principal = principal;

		al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(TF_contrasena.getText().length() != 0 || TF_confirmarCon.getText().length() != 0)
				{
					System.out.println(TF_contrasena.getText());
					System.out.println("NO ES NULL");
					if(TF_contrasena.getText().equals(TF_confirmarCon.getText()))
							{
						System.out.println("ENTRO");
						lblNull.setText("SII");
						lblNull.setIcon(new ImageIcon("./img//ok.png"));
						lblNull.revalidate();
						lblNull.repaint();

							}
					else
					{
						lblNull.setText("Noo");
						lblNull.setIcon(new ImageIcon("./img/fall.png"));
						lblNull.revalidate();
						lblNull.repaint();


					}
				}

				else
				{
					System.out.println(" NULL");
				}

				if(TF_nombreUsuario.getText().length() != 0)
				{
					if(!usernames.contains(TF_nombreUsuario.getText())){
						LB_usernme.setIcon(new ImageIcon("./img//ok.png"));
						lblNull.revalidate();
						lblNull.repaint();
						System.out.println("BIEN");
					}
					else
					{
						LB_usernme.setIcon(new ImageIcon("./img//fall.png"));
						lblNull.revalidate();
						lblNull.repaint();
					}
				}
				else
				{
					System.out.println("USERNAME 0");
				}
			}
		};
		t = new Timer(2000,al);
		t.start();

		setBackground(Color.WHITE);
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 780, 610);
		panel.setBackground(Color.WHITE);
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
		titulo.setIcon(new ImageIcon("./img/titulomenu2.png"));
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
		lblGenero.setBounds(95, 353, 85, 32);
		panel.add(lblGenero);

		TF_nombres = new JTextField();
		TF_nombres.setBounds(190, 133, 158, 32);
		panel.add(TF_nombres);
		TF_nombres.setColumns(10);

		TF_apellidos = new JTextField();
		TF_apellidos.setColumns(10);
		TF_apellidos.setBounds(545, 135, 158, 32);
		panel.add(TF_apellidos);

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
		lblFechaNaciemiento.setBounds(8, 413, 158, 32);
		panel.add(lblFechaNaciemiento);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(95, 290, 85, 32);
		panel.add(lblTelefono);

		JLabel lblNombreUsuarioPaypal = new JLabel("Nombre \r\nusuario Paypal");
		lblNombreUsuarioPaypal.setToolTipText("");
		lblNombreUsuarioPaypal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreUsuarioPaypal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreUsuarioPaypal.setBounds(358, 348, 177, 42);
		panel.add(lblNombreUsuarioPaypal);

		TF_telefono = new JTextField();
		TF_telefono.setColumns(10);
		TF_telefono.setBounds(190, 290, 158, 32);
		panel.add(TF_telefono);

		TF_paypal = new JTextField();
		TF_paypal.setColumns(10);
		TF_paypal.setBounds(545, 355, 158, 32);
		panel.add(TF_paypal);

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

		 LB_usernme = new JLabel("New label");
		LB_usernme.setBounds(363, 189, 48, 43);
		panel.add(LB_usernme);

		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(181, 419, 69, 20);
		panel.add(lblDia);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(342, 419, 69, 20);
		panel.add(lblMes);

		JLabel lblAnio = new JLabel("Anio");
		lblAnio.setBounds(529, 419, 67, 20);
		panel.add(lblAnio);

		TF_dia = new JTextField();
		TF_dia.setBounds(211, 416, 85, 26);
		panel.add(TF_dia);
		TF_dia.setColumns(10);

		TF_mes = new JTextField();
		TF_mes.setColumns(10);
		TF_mes.setBounds(388, 416, 85, 26);
		panel.add(TF_mes);

		TF_anio = new JTextField();
		TF_anio.setColumns(10);
		TF_anio.setBounds(614, 416, 85, 26);
		panel.add(TF_anio);

		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"","F", "M"}));
		comboBox.setBounds(198, 356, 98, 32);
		panel.add(comboBox);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");

		UsuarioJpaController contro = new UsuarioJpaController(emf);
		usernames = contro.usernames();


	}

	public void volverInicio(){
		if(!usernames.contains(TF_nombreUsuario.getText()) && (TF_contrasena.getText().length() != 0 || TF_confirmarCon.getText().length() != 0)){
		Date fechaDate = new Date( Integer.parseInt(TF_anio.getText()),Integer.parseInt(TF_mes.getText()),Integer.parseInt(TF_dia.getText())) ;
		ConeccionDatos conn = new ConeccionDatos();
		conn.CrearUsuario(TF_nombreUsuario.getText(), TF_nombres.getText(), Integer.parseInt(TF_telefono.getText()), TF_email.getText(), TF_paypal.getText(), TF_apellidos.getText(), fechaDate, comboBox.getSelectedItem().toString(), TF_contrasena.getText());
		principal.pasarInicio();
		t.stop();
		System.out.println("INGRESO USUARIO");
		}
		else
		{
			System.out.println("NO SE PUDO INGRESAR");
		}
	}
	// activa el timer despues de que se llame una segunda vez a este panel
	public void timer()
	{
		t.start();
	}

	public void limpiar(){
		TF_nombres.setText("");
		TF_apellidos.setText("");
		TF_anio.setText("");
		TF_confirmarCon.setText("");
		TF_contrasena.setText("");
		TF_dia.setText("");
		TF_email.setText("");
		TF_mes.setText("");
		TF_nombreUsuario.setText("");
		TF_paypal.setText("");
		TF_telefono.setText("");



	}
}
