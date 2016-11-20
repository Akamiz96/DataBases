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
	
	private NavGrupoNormal navGruposNor = null;
	private NavGrupoAdm navGruposAdm = null;
	private NavegacionPrin opciones = null;
	private JPGruposActuales grupos = null;
	private JPCuentas cuentas = null;
	private JPTransacciones transacciones = null;
	private JPCrearGrupo crearGrupo = null;
	private JPCrearCuenta crearCuenta = null;
	private JPContactos contactos = null;
	private JPMiembrosGr miembros = null;
	private JPCambiarMiembros cambiarMiem = null;
	private JPCambiarLider cambiarLid = null;
	private JPLimpiarDeudas limpiarDeu = null;
	private JPTutoMain tutoMain = null;
	private JPTutoGrupo tutoGrupo = null;
	 
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
				principal.singnOut();;
				
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
		
		NavegacionPrin opciones = new NavegacionPrin(principal,this);
		opciones.setSize(780, 87); // tamano del jpanel
		opciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(opciones, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
		tutoMain();
		
		
	}
	
	public void navGruposNor(){
		if(navGruposNor == null)
		{
			System.out.println("creo navegacion dos");
			navGruposNor = new NavGrupoNormal(principal,this);
		}
		else
		{
			navGruposNor.RevisarDueno();
		}
		
		
		navGruposNor.setSize(780, 87); // tamano del jpanel
		navGruposNor.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(navGruposNor, BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void navGruposAdm(){
		if(navGruposAdm  == null)
		{
			System.out.println("creo navegacion dos");
			navGruposAdm  = new NavGrupoAdm(principal,this);
		}
		
		
		navGruposAdm .setSize(780, 87); // tamano del jpanel
		navGruposAdm .setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_opciones.removeAll();
		PA_opciones.add(navGruposAdm , BorderLayout.CENTER);
		PA_opciones.revalidate();
		PA_opciones.repaint();
	}
	
	public void pasarOpcion1()
	{
		if(opciones == null)
		{
			System.out.println("creo navegacion");
			 opciones = new NavegacionPrin(principal,this);
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
			grupos.mostrarDatos();
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
		if(transacciones == null)
		{
			transacciones = new JPTransacciones(principal,this);
		}
		
	
		
		transacciones.setSize(780, 460); // tamano del jpanel
		transacciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(transacciones, BorderLayout.CENTER);
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
	
	public void trasacciones(){
		if(transacciones == null)
		{
			transacciones = new JPTransacciones(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		transacciones.setSize(780, 460); // tamano del jpanel
		transacciones.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(transacciones, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_trasaccines");
	}
	
	public void miembros(){
		if(miembros == null)
		{
			miembros = new JPMiembrosGr(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		miembros.setSize(780, 460); // tamano del jpanel
		miembros.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(miembros, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_miembros");
	}
	public void cambiarmiem(){
		if(cambiarMiem == null)
		{
			cambiarMiem = new JPCambiarMiembros(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		cambiarMiem.setSize(780, 460); // tamano del jpanel
		cambiarMiem.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(cambiarMiem, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_miembros");
	}
	
	public void cambiarLid(){
		if(cambiarLid == null)
		{
			cambiarLid = new JPCambiarLider(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		cambiarLid.setSize(780, 460); // tamano del jpanel
		cambiarLid.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(cambiarLid, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_cambiar due");
	}
	
	public void limpiarDeu(){
		if(limpiarDeu == null)
		{
			limpiarDeu = new JPLimpiarDeudas(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		limpiarDeu.setSize(780, 460); // tamano del jpanel
		limpiarDeu.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(limpiarDeu, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_limpiar");
	}
	
	public void tutoMain(){
		if(tutoMain == null)
		{
			tutoMain = new JPTutoMain(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		tutoMain.setSize(780, 460); // tamano del jpanel
		tutoMain.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(tutoMain, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_limpiar");
	}
	
	public void tutoGrupo(){
		if(tutoGrupo == null)
		{
			tutoGrupo = new JPTutoGrupo(principal,this);
		}
		else
		{
			//contactos.mostrarDatos();
		}
		
		tutoGrupo.setSize(780, 460); // tamano del jpanel
		tutoGrupo.setLocation(0, 0); // posicion dentro del panel principal
		 
		PA_informacion.removeAll();
		PA_informacion.add(tutoGrupo, BorderLayout.CENTER);
		PA_informacion.revalidate();
		PA_informacion.repaint();
		System.out.println("PA_limpiar");
	}
	public void salirGrupo(){
		gruposActuales();
		pasarOpcion1();
	}
	
}
