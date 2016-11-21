package splitpay;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controladores.CuentaJpaController;
import Controladores.GrupoJpaController;
import Controladores.UsuarioJpaController;

public class JPMiembrosGr extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JScrollPane scrollPane;
	private String[] columnSer = {"User_name", "Presupuesto"};
	private Vector rowDataSer;
	private Vector columSerV;
	private JTable tableMiembro;
	public JPMiembrosGr(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Miembros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(92, 24, 296, 44);
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 79, 358, 317);
		panel.add(scrollPane);
		
		tableMiembro = getTable();
		scrollPane.setViewportView(tableMiembro);
		mostrarDatos();
	}
	public JTable getTable() {
		if (tableMiembro == null) {			
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			tableMiembro = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(tableMiembro);
		}
		return tableMiembro;
	}
	 public void mostrarDatos(){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
			GrupoJpaController contro = new GrupoJpaController(emf);
			UsuarioJpaController controUsu = new UsuarioJpaController(emf) ;
	                rowDataSer = new Vector();
			List<String> informacion = contro.UsuariosdeGrupoConUsername(principal.grupoActual);
			this.rowDataSer = new Vector(); // datos de toda la tabla
			for( String infUsu : informacion )
			{
				Vector fila = new Vector();
	                        System.out.println(infUsu);
				StringTokenizer st = new StringTokenizer(infUsu, "$");
				String nombUsuario = st.nextToken().trim() ;
				String idUsuario = st.nextToken().trim() ;
				int idUsuarioInt = Integer.parseInt(idUsuario) ;
				fila.add(nombUsuario);
				List<String> listaGrupos = controUsu.RealizarBalanceGruposdeUsuario(idUsuarioInt) ;
				for(int i=0 ;i<listaGrupos.size();i++){
					StringTokenizer st2 = new StringTokenizer(listaGrupos.get(i), "$");
					String nombGru= st2.nextToken().trim();
					String totalGru = st2.nextToken().trim();
					String idGru = st2.nextToken().trim();
					int idGruInt = Integer.parseInt(idGru) ;
					if(idGruInt==principal.grupoActual){
						fila.add(totalGru);
					}
				}
				this.rowDataSer.add(fila);				
	                        //System.out.println(st.nextToken().trim());
	                       // cuentaID.add(Integer.parseInt(st.nextToken().trim()));
			}
			tableMiembro = new JTable(this.rowDataSer, this.columSerV);
			System.out.println("#ser");
			scrollPane.setViewportView(tableMiembro);

		}
}