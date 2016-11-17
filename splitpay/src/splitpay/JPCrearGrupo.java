package splitpay;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JPCrearGrupo extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField TF_nombreG;

	/**
	 * Create the panel.
	 */
	public JPCrearGrupo(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
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
		btnAgregar.setBounds(296, 358, 154, 33);
		panel.add(btnAgregar);
		
		JLabel lblSeleccioneLosIntegrantes = new JLabel("Seleccione los integrantes");
		lblSeleccioneLosIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSeleccioneLosIntegrantes.setBounds(10, 105, 214, 27);
		panel.add(lblSeleccioneLosIntegrantes);

	} 
	public void limpiar(){
		
	}
}
