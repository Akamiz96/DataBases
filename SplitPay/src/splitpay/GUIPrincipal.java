package splitpay;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Controladores.CuentaJpaController;
import Controladores.UsuarioJpaController;
import Negocio.Grupo;
import Negocio.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIPrincipal extends JFrame {

	public JPanel contentPane;
	public int num;
	public JPanel panel_p;
	private JPCrearUsuario crear = null;
	private PInicioSec inicio = null;
	private PMenu menu = null;
	public Usuario userActual;
	public int grupoActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIPrincipal frame = new GUIPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIPrincipal() {
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_p = new JPanel();
		panel_p.setBounds(0, 0, 780, 620);
		contentPane.add(panel_p);
		panel_p.setLayout(null);
		
		PInicioSec inicio = new PInicioSec(this);
		inicio.setSize(780, 610); // tamano del jpanel
		inicio.setLocation(5, 5); // posicion dentro del panel principal
		 
		panel_p.removeAll();
		panel_p.add(inicio, BorderLayout.CENTER);
		panel_p.revalidate();
		panel_p.repaint();
		
		
		
	}
	
	public void pasarMenu(){
		System.out.println("ENTRO");
		if(menu == null)
		{
		menu = new PMenu(this);
		}
		else
		{
			menu.tutoMain();
		}
		
		menu.setSize(780, 610); // tamano del jpanel
		menu.setLocation(5, 5); // posicion dentro del panel principal
		 
		panel_p.removeAll();
		panel_p.add(menu, BorderLayout.CENTER);
		panel_p.revalidate();
		panel_p.repaint();
	}
	
	public void pasarCrearC(){
		// se reviza antes si el objeto existe
		if(crear == null)
		{
			// si no existe se cre solo una vez
		crear = new JPCrearUsuario(this);
		System.out.println("CREO USUARIO");
		}
		else
		{
			// si ya exixte solo se activa el timer 
			System.out.println("entro else");
			crear.timer();
		}
		crear.setSize(780, 610); // tamano del jpanel
		crear.setLocation(5, 5); // posicion dentro del panel principal
		 
		panel_p.removeAll();
		panel_p.add(crear, BorderLayout.CENTER);
		panel_p.revalidate();
		panel_p.repaint();
	}
	
	// cuando el usuario da clic en iniciar sesion 
	public void singnOut(){
		if(inicio == null)
		{
			inicio = new PInicioSec(this);
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		
		UsuarioJpaController contro = new UsuarioJpaController(emf);
		System.out.println("USUARIO ACTUAL:_"+userActual);
		contro.signOut(userActual);
		userActual = null;
		System.out.println("cerrar cesion");
		
		inicio.setSize(780, 610); // tamano del jpanel
		inicio.setLocation(5, 5); // posicion dentro del panel principal
		 
		panel_p.removeAll();
		panel_p.add(inicio, BorderLayout.CENTER);
		panel_p.revalidate();
		panel_p.repaint();
	}
	
	


public void pasarInicio(){
	if(inicio == null)
	{
		inicio = new PInicioSec(this);
	}
	inicio.setSize(780, 610); // tamano del jpanel
	inicio.setLocation(5, 5); // posicion dentro del panel principal
	 
	panel_p.removeAll();
	panel_p.add(inicio, BorderLayout.CENTER);
	panel_p.revalidate();
	panel_p.repaint();
}


}
