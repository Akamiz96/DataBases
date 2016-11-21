package splitpay;

import Controladores.PerteneceAJpaController;
import java.awt.Color;
import java.awt.Font;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import Controladores.UsuarioJpaController;
import Negocio.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

public class JPCambiarMiembros extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable tableGrupo;
	private String[] columnSer = {"Nombre","user name"};
	private Vector rowDataSerGrupo;
	private Vector columSerVGrupo;
	private Vector rowDataSerContacto;
	private Vector columSerVContacto;
	private JTable tableContactos;
	private JScrollPane scrollPaneGrupo;
	private JScrollPane scrollPaneContactos;
        private List<Integer> idUsuariosC = new ArrayList<>();
        private List<Integer> idUsuariosG = new ArrayList<>();
	
	public JPCambiarMiembros(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("cambiar Miembros");
		lblNewLabel.setBounds(164, 11, 350, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		scrollPaneGrupo = new JScrollPane();
		scrollPaneGrupo.setBounds(10, 66, 210, 230);
		panel.add(scrollPaneGrupo);
		
		tableGrupo = getTableGrupo();
		scrollPaneGrupo.setColumnHeaderView(tableGrupo);
		
		scrollPaneContactos = new JScrollPane();
		scrollPaneContactos.setBounds(400, 66, 210, 230);
		panel.add(scrollPaneContactos);
		
		tableContactos = getTableContacto();
		scrollPaneContactos.setColumnHeaderView(tableContactos);
		
		JButton button = new JButton("<<Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            agregarUsuarioAGrupo();
                            mostrarDatos();
			}
		});
		button.setBounds(265, 102, 89, 23);
		panel.add(button);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            EliminarUsuarioGrupo();
                            mostrarDatos();
			}
		});
		btnEliminar.setBounds(265, 157, 89, 23);
		panel.add(btnEliminar);
		
		mostrarDatos();
	}
	
	public JTable getTableGrupo()
	{
		if (tableGrupo == null) {			
			columSerVGrupo = new Vector(Arrays.asList(this.columnSer));
			rowDataSerGrupo = new Vector();
			tableGrupo = new JTable(this.rowDataSerGrupo, columSerVGrupo);
			scrollPaneGrupo.setViewportView(tableGrupo);
		}
		return tableGrupo;
	}
	
	public JTable getTableContacto()
	{
		if (tableContactos == null) {			
			columSerVContacto = new Vector(Arrays.asList(this.columnSer));
			rowDataSerContacto = new Vector();
			tableContactos = new JTable(this.rowDataSerContacto, columSerVContacto);
			scrollPaneContactos.setViewportView(tableContactos);
		}
		return tableGrupo;
	}
	
	public JScrollPane getScrollPaneGrupo() {
		return scrollPaneGrupo;
	}
	public JScrollPane getScrollPaneContactos() {
		return scrollPaneContactos;
	}
	
	public void mostrarDatos()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		UsuarioJpaController controUsuario = new UsuarioJpaController(emf);
		List<Usuario> usuariosPorGrupo = controUsuario.usuariosDeUnGrupo(principal.grupoActual);
                rowDataSerGrupo = new Vector();
                rowDataSerContacto = new Vector();
		for( Usuario user : usuariosPorGrupo )
		{
			Vector fila = new Vector();
                        fila.add(user.getNombre()+" "+user.getApellidos());
                        fila.add(user.getUserName());
                        this.rowDataSerGrupo.add(fila);
                        idUsuariosG.add(user.getId());
		}
                tableGrupo = new JTable(this.rowDataSerGrupo, this.columSerVGrupo);
		System.out.println("#ser");
		scrollPaneGrupo.setViewportView(tableGrupo);
                
                List<Usuario> contactos = controUsuario.contactos(principal.userActual.getId());
                for( Usuario user : contactos )
		{
			Vector fila = new Vector();
                        fila.add(user.getNombre()+" "+user.getApellidos());
                        fila.add(user.getUserName());
                        this.rowDataSerContacto.add(fila);
                        idUsuariosC.add(user.getId());
		}
                tableContactos = new JTable(this.rowDataSerContacto, this.columSerVContacto);
		System.out.println("#ser");
		scrollPaneContactos.setViewportView(tableContactos);
	}

    public void agregarUsuarioAGrupo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
        PerteneceAJpaController controPertenece = new PerteneceAJpaController(emf);
        int idUsuario = idUsuariosC.get(tableContactos.getSelectedRow());
        int filas = controPertenece.agregarUsuarioAGrupo(idUsuario, principal.grupoActual);
        if(filas == -1)
        {
            JOptionPane.showMessageDialog(null, "Error en agregar el usuario al grupo", "Error",JOptionPane.ERROR_MESSAGE);
        } else if ( filas == 1) {
            JOptionPane.showMessageDialog(null, "Se ha agregado el usario al grupo", "Agregacion Exitosa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void EliminarUsuarioGrupo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
        PerteneceAJpaController controPertenece = new PerteneceAJpaController(emf);
        int idUsuario = idUsuariosG.get(tableGrupo.getSelectedRow());
        int filas = controPertenece.EliminarUsuarioGrupo(idUsuario, principal.grupoActual);
        if(filas == -1)
        {
            JOptionPane.showMessageDialog(null, "Error en elimminar el usuario del grupo", "Error",JOptionPane.ERROR_MESSAGE);
        } else if ( filas == 1) {
            JOptionPane.showMessageDialog(null, "Se ha eliminado el usario del grupo", "Agregacion Exitosa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
