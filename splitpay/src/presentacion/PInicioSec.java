package presentacion;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PInicioSec extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
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
		
		textField = new JTextField();
		textField.setBounds(325, 166, 172, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(325, 238, 172, 27);
		panel.add(textField_1);
		
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
	
	private void iniciarc()
	{
		// se revisa con la base de datos si los datos son correctos
		
		principal.pasarMenu();	
	}
	
	private void crearcuenta()
	{
		principal.pasarCrearC();
	}
}
