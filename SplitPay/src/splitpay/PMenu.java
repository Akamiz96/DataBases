package splitpay;

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
	
	private NavegacionDos opcionesdos = null;
	private Navegacion opciones = null;
	private JPGruposActuales grupos = null;
	private JPCuentas cuentas = null;
	private JPDeudas deudas = null;
	private JPCrearGrupo crearGrupo = null;
	private JPCrearCuenta crearCuenta = null;
	private JPContactos contactos = null;
	 
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
		lblNewLabel_1.setIcon(new ImageIcon("./img/cerrarCesion.png"));
		lblNewLabel_1.setBounds(673, 0, 107, 63);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 780, 63);
		lblNewLabel.setIcon(new ImageIcon("./img/titulomenu4.png"));
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
		
		Navegacion opciones = new Navegacion(principal,this);
		opciones.setSize(780, 87); // tamano del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
		
		
		
	}
	
	public void pasarOpcion2(){
		if(opcionesdos == null)
		{
			System.out.println("creo navegacion dos");
			opcionesdos = new NavegacionDos(principal,this);
		}
		
		
		opcionesdos.setSize(780, 87); // tamano del jpanel
		opcionesdos.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opcionesdos, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void pasarOpcion1()
	{
		if(opciones == null)
		{
			System.out.println("creo navegacion");
			 opciones = new Navegacion(principal,this);
		}
		
		opciones.setSize(780, 87); // tamano del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void gruposActuales(){
		if(grupos == null)
		{
			System.out.println("creo grupos actutuales");
			grupos = new JPGruposActuales(principal,this);
		}
		else
		{
			grupos.limpiar();
		}
		grupos.setSize(780, 460); // tamano del jpanel
		grupos.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(grupos, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void cuentas(){
		if(cuentas == null){
			cuentas = new JPCuentas(principal,this);
		}
		else
		{
			cuentas.mostrarDatos();
		}
		
		cuentas.setSize(780, 460); // tamano del jpanel
		cuentas.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(cuentas, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void deudas(){
		if(deudas == null)
		{
			deudas = new JPDeudas(principal,this);
		}
		
	
		
		deudas.setSize(780, 460); // tamano del jpanel
		deudas.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(deudas, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void crearGrupo(){
		if(crearGrupo == null)
		{
			System.out.println("crear grupo");
			crearGrupo = new JPCrearGrupo(principal,this);
		}
		else
		{
			crearGrupo.limpiar();
			// falta actualizar datos
		}
		
		crearGrupo.setSize(780, 460); // tamano del jpanel
		crearGrupo.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(crearGrupo, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void crearCuenta(){
		if(crearCuenta == null)
		{
			crearCuenta = new JPCrearCuenta(principal,this);
		}
		else
		{
			// TODO revizar que se hace
		}
		
		crearCuenta.setSize(780, 460); // tamano del jpanel
		crearCuenta.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(crearCuenta, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
	public void contactos(){
		if(contactos == null)
		{
			contactos = new JPContactos(principal,this);
		}
		else
		{
			contactos.mostrarDatos();
		}
		
		contactos.setSize(780, 460); // tamano del jpanel
		contactos.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(contactos, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_informacion");
	}
	
}
