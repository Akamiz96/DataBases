package presentacion;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PMenu extends JPanel {

	private GUIPrincipal principal;
	private JPanel PA_informacion;
	private JPanel PA_opciones;
	 
	/**
	 * Create the panel.
	 */
	public PMenu(GUIPrincipal principal) {
		this.principal = principal;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 780, 610);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 0, 780, 63);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				principal.pasarInicio();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\andre\\Documents\\java\\splitpay\\src\\presentacion\\img\\cerrarCesion.png"));
		lblNewLabel_1.setBounds(673, 0, 107, 63);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 780, 63);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\andre\\Documents\\java\\splitpay\\src\\presentacion\\img\\titulomenu4.png"));
		panel_1.add(lblNewLabel);
		
		PA_opciones = new JPanel();
		PA_opciones.setLayout(null);
		PA_opciones.setBackground(Color.PINK);
		PA_opciones.setBounds(0, 63, 780, 87);
		panel.add(PA_opciones);
		
		PA_informacion = new JPanel();
		PA_informacion.setBackground(Color.WHITE);
		PA_informacion.setBounds(0, 150, 780, 460);
		panel.add(PA_informacion);
		PA_informacion.setLayout(null);
		
		JOpciones opciones = new JOpciones(principal,this);
		opciones.setSize(780, 87); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
		
		
		
	}
	
	public void pasarOpcion2(){
		JOpcionesDos opciones = new JOpcionesDos(principal,this);
		opciones.setSize(780, 87); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void pasarOpcion1()
	{
		JOpciones opciones = new JOpciones(principal,this);
		opciones.setSize(780, 87); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void gruposActuales(){
		JGruposActuales opciones = new JGruposActuales(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void cuentasDueno(){
		JCuentasDueno opciones = new JCuentasDueno(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void deudas(){
		JDeudas opciones = new JDeudas(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void crearGrupo(){
		JCrearGrupo opciones = new JCrearGrupo(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void crearCuenta(){
		JCrearCuenta opciones = new JCrearCuenta(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void contactos(){
		JContactos opciones = new JContactos(principal,this);
		opciones.setSize(780, 460); // tamaño del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(opciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
}
