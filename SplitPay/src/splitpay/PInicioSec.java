package splitpay;

import java.awt.Color;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import Controladores.UsuarioJpaController;
import Negocio.Usuario;

public class PInicioSec extends JPanel {
	private JTextField TF_usuario;
	private JTextField TF_pass;
	private GUIPrincipal principal;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public PInicioSec(GUIPrincipal principal) {
		this.principal = principal;

		setBackground(Color.WHITE);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 780, 610);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("SplitPay");
		lblNewLabel.setIcon(new ImageIcon("./img/inicio.png"));
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 25));
		lblNewLabel.setBounds(243, 63, 280, 100);
		panel.add(lblNewLabel);

		JLabel lblUsuairo = new JLabel("Usuario");
		lblUsuairo.setForeground(Color.WHITE);
		lblUsuairo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuairo.setFont(new Font("Segoe UI Historic", Font.BOLD, 18));
		lblUsuairo.setBounds(231, 165, 84, 27);
		panel.add(lblUsuairo);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setFont(new Font("Segoe UI Historic", Font.BOLD, 18));
		lblContrasea.setBounds(196, 234, 119, 27);
		panel.add(lblContrasea);

		TF_usuario = new JTextField();
		TF_usuario.setBounds(325, 166, 172, 27);
		panel.add(TF_usuario);
		TF_usuario.setColumns(10);

		TF_pass = new JTextField();
		TF_pass.setColumns(10);
		TF_pass.setBounds(325, 238, 172, 27);
		panel.add(TF_pass);

		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearcuenta();
			}
		});
		btnCrearCuenta.setBounds(209, 310, 132, 37);
		panel.add(btnCrearCuenta);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarc();
			}
		});
		btnIniciar.setBounds(396, 310, 132, 37);
		panel.add(btnIniciar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./img/fondoini.png"));
		lblNewLabel_1.setBounds(127, 63, 507, 328);
		panel.add(lblNewLabel_1);

	}

	private void iniciarc() {
		// principal.pasarMenu();
		// se revisa con la base de datos si los datos son correctos
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("SplitPayPU");
			UsuarioJpaController usuario = new UsuarioJpaController(emf);
			Usuario user = usuario.signIn(TF_usuario.getText(),TF_pass.getText());
			principal.userActual = principal.userActual;
			principal.pasarMenu();
			System.out.println(user.toString());
			
		} catch ( SQLException e) {
			JOptionPane.showMessageDialog(null, "USUARIO INVALIDO");
		}

		

	}

	private void crearcuenta() {
		principal.pasarCrearC();

	}
}
