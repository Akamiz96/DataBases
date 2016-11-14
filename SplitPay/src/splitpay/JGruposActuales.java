package splitpay;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class JGruposActuales extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private String[] columnSer = { "Nombre", "Balance" };
	private Vector rowDataSer;
	private Vector columSerV;
	private JTable tablaGrupos;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public JGruposActuales(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
	
		
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblGruposActuales = new JLabel("Grupos Actuales");
		lblGruposActuales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGruposActuales.setBounds(65, 29, 354, 32);
		panel.add(lblGruposActuales);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 91, 625, 199);
		panel.add(scrollPane);
		
		tablaGrupos = getTableG();
		scrollPane.setViewportView(tablaGrupos);
		
		JButton btnNewButton = new JButton("Seleccionar Grupo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(542, 363, 148, 45);
		panel.add(btnNewButton);
		mostrarDatos();

	}
	public JTable getTableG() {
		System.out.println("ENTRO GET SERV");
		if (tablaGrupos == null) {
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			tablaGrupos = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(tablaGrupos);

		}

		return tablaGrupos;
	}
	
	public JTable mostrarDatos() {
		
		//datosDefectos(empresa);
		System.out.println("SERVICIOS");
		this.rowDataSer = new Vector(); // datos de toda la tabla
		/*
		String nomIPS,codAmb;
		List<Servicio> servicios = empresa.getServicios();
		List<Servicio> newServicios = new ArrayList<Servicio>();
		*/
		
		 

		/*for (String ser : newServicios) {
			
			Vector fila = new Vector();
			// llenar los datos de una fila:
			fila.add(ser.getCodigo());
			fila.add(Utils.convertirHoraString(ser.getHoraSolicitud()));
			fila.add(ser.getPaciente());
			fila.add(ser.getTipoServicio());
			fila.add(ser.getTelefono());
			fila.add(ser.getDireccion().toString());
			fila.add(ser.getEstado());
			if(ser.getEstado()== EstadoServicio.ASIGNADO){
				codAmb =Integer.toString(ser.getAmbulancia().getCodigo());
				if(ser.getTipoServicio()!= TipoServicio.DOMICILIO){
				nomIPS = ser.getIps().getNombre();
				}
				else{
					nomIPS="    -----";	
				}
			}
			else{
				codAmb="    -----";
				nomIPS="    -----";		
			}
			fila.add(nomIPS);
			fila.add(codAmb);

			// agregar fila al vector que representa la tabla:
			this.rowDataSer.add(fila);
			System.out.println(fila.toString());
		}
		*/
		for(int i=0;i<15;i++)
		{
			Vector fila = new Vector();
			fila.add(1+i);
			fila.add("a"+i);
			this.rowDataSer.add(fila);
			System.out.println(fila.toString());
		}
		// refrescar el JTable dentro del JScrollPane:
		tablaGrupos = new JTable(this.rowDataSer, this.columSerV);
		System.out.println("#ser");
		scrollPane.setViewportView(tablaGrupos);// refresca el JTable

		return tablaGrupos;
	}
}
