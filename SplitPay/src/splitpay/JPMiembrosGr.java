package splitpay;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controladores.GrupoJpaController;

public class JPMiembrosGr extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private String[] columnSer = { "Nombre Usuario", "Balance"};
	private JTable table;
	private JScrollPane scrollPane;
	private Vector rowDataSer;
	private Vector columSerV;
	public JPMiembrosGr(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(-20, -21,840, 550);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Miembros");
		lblNewLabel.setBounds(28, 22, 296, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 76, 777, 410);
		panel.add(scrollPane);
		
		table = getTable();
		scrollPane.setColumnHeaderView(table);
		mostrarDatos();
	}
	
	private void mostrarDatos() {
		this.rowDataSer = new Vector();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		GrupoJpaController contro =  new GrupoJpaController(emf);
		List<String> balance = contro.RealizarBalancedeUsuariosporGrupos(principal.grupoActual);
		this.rowDataSer = new Vector();
		for(String bal: balance)
		{
			Vector fila = new Vector();
			//fila.add(1+i);
			//fila.add("a"+i);
			
			StringTokenizer st = new StringTokenizer(bal, "$");
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());
			this.rowDataSer.add(fila);
		}
		scrollPane.setViewportView(table);
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		if(table == null){
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			table = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(table);
		}
		return table;
	}
}
